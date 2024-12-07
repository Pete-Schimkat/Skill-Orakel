import { Category, Skill } from '../SkillTypes';
import { putNewSkill as callAPIForSkills } from '../SkillAPICalls';
import { _throw } from '../../../utils/_throw';

export function addSkillToCategories(
  skillCategories: Category[],
  categories: Category[],
  skillName: string
) {
  const lastSkillId = 200123;
  const newSkill = { skillId: lastSkillId, skillName: skillName };
  void callAPIForSkills(newSkill, categories, 'skills/', 'PUT');
  const deepCopy = JSON.parse(JSON.stringify(skillCategories));
  for (let i = 0; i < deepCopy.length; i++) {
    for (let j = 0; j < categories.length; j++) {
      if (deepCopy[i].categoryName === categories[j].categoryName) {
        deepCopy[i].skills.push(newSkill);
      }
    }
  }
  return deepCopy;
}

export function updateSkillsInCategories(
  skillCategories: Category[],
  categories: Category[],
  skillToEdit: Skill,
  skillName: string
): Category[] {
  if (!skillToEdit) {
    return skillCategories;
  }

  const newSkill = {
    skillId: skillToEdit.skillId,
    skillName: skillName,
  };

  void callAPIForSkills(newSkill, categories, `skills/${newSkill.skillId}`, 'PUT');

  let deepCopy: Category[] = JSON.parse(JSON.stringify(skillCategories));

  deepCopy = removeGeloeschteCategoriesFromSkill(
    deepCopy,
    categories,
    skillToEdit
  );
  deepCopy = addNeueCategoriesZuSkill(
    categories,
    deepCopy,
    skillToEdit,
    newSkill
  );

  return deepCopy;
}

function removeGeloeschteCategoriesFromSkill(
  deepCopy: Category[],
  categories: Category[],
  skillToChange: Skill
): Category[] {
  const allCategorysWithThisSkill = deepCopy.filter((category) =>
    category.skills.find(
      (skill) => skill.skillName === skillToChange.skillName
    )
  );

  if (!allCategorysWithThisSkill) {
    return deepCopy;
  }

  for (let i = 0; i < allCategorysWithThisSkill.length; i++) {
    const category = allCategorysWithThisSkill[i];
    const doesSkillInSkilllistOfCategorysAndInSkillcategories =
      !categories.find(
        (categoryVonSkill) =>
          categoryVonSkill.categoryName === category.categoryName
      );
    if (doesSkillInSkilllistOfCategorysAndInSkillcategories) {
      category.skills = category.skills.filter(
        (skill) => skill.skillName !== skillToChange.skillName
      );
    }
  }

  return deepCopy;
}

function addNeueCategoriesZuSkill(
  categories: Category[],
  deepCopy: Category[],
  skillToChange: Skill,
  newSkill: { skillId: number; skillName: string }
): Category[] {
  for (let i = 0; i < categories.length; i++) {
    const category = categories[i];
    const zuAendern = deepCopy.find(
      (zuAenderndeCategory) => zuAenderndeCategory.categoryName === category.categoryName
    ) ?? _throw('Category needed but not found');

    const skill = zuAendern.skills.find(
      (skill) => skill.skillName === skillToChange.skillName
    );

    if (skill) {
      skill.skillName = newSkill.skillName;
    } else {
      zuAendern.skills.push(newSkill);
    }
  }
  return deepCopy;
}

export function deleteSkill(
  skillCategories: Category[],
  previouslyChosenCategories: Category[],
  skillToEdit: Skill,
) {
  void callAPIForSkills(
    skillToEdit,
    previouslyChosenCategories,
    `skills/${skillToEdit.skillId}`,
    'DELETE'
  );
  const deepCopy: Category[] = JSON.parse(JSON.stringify(skillCategories));
  for (const key in previouslyChosenCategories) {
    const categoryId = previouslyChosenCategories[key].categoryId;
    const zuLoeschenderSkill = deepCopy.find((category) => category.categoryId === categoryId) ?? _throw('Cannot find the skill we are supposed to delete here');
    zuLoeschenderSkill.skills = zuLoeschenderSkill.skills.filter(
      (skillInListe) => skillInListe.skillName !== skillToEdit.skillName
    );
  }
  return deepCopy;
}
