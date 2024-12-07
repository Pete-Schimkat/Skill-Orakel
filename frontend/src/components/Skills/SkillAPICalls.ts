import { Category, Skill } from './SkillTypes';

const backendURI = 'http://localhost:3100/';

export async function fetchSkillCategoryData() {
  const loadedSkillcategories = [];
  try {
    const response = await fetch(backendURI + 'categories/');
    if (!response.ok) {
      throw new Error('cannot fetch');
    }

    const receivedData = await response.json();

    for (const key in receivedData) {
      loadedSkillcategories.push({
        categoryId: +key,
        categoryName: receivedData[key].categoryName,
        skills: receivedData[key].skills,
      });
    }
  } catch (error) {
    console.log('some Handling');
    return;
  }
  return loadedSkillcategories;
}

export async function putNewSkill(
  skill: Skill,
  categories: Category[],
  ressourceAdress: string,
  method: string
) {
  const skillAndCategoryJunction = {
    skillId: skill.skillId,
    skillName: skill.skillName,
    categories: [...categories],
  };

  await fetch(backendURI + ressourceAdress, {
    method: method,
    body: JSON.stringify(skillAndCategoryJunction),
    headers: {
      'Content-Type': 'application/json',
    },
  });
}
