import { _throw } from '../../../utils/_throw';
import { SkillModalState, SkillModalAction, CategoryModalState, CategoryModalAction } from '../SkillTypes';

export function skillModalReducer(
  state: SkillModalState,
  action: SkillModalAction
): SkillModalState {
  if (action.type === 'open') {
    const previouslyChosenCategory = [];
    if (action.chosenCategory) {
      previouslyChosenCategory.push(action.chosenCategory);
    }
    return {
      isModalOpen: !state.isModalOpen,
      previouslyChosenCategories: previouslyChosenCategory,
      skillToEdit: undefined,
    };
  }

  if (action.type === 'edit') {
    const skillCategories = action.categories ?? _throw('categories cannot be undefined');
    const skillToEdit = action.skill ?? _throw('skill cannot be undefined');
    const categoriesBelongingToSkills = skillCategories.filter((category) =>
      category.skills.find(
        (otherSkill) => otherSkill.skillName === skillToEdit.skillName
      )
    );
    return {
      isModalOpen: !state.isModalOpen,
      previouslyChosenCategories: [...categoriesBelongingToSkills],
      skillToEdit
    };
  }

  return state;
}

export function categoryModalReducer(
  state: CategoryModalState,
  action: CategoryModalAction
): CategoryModalState {
  if (action.type === 'open') {
    return {
      isModalOpen: !state.isModalOpen,
      previouslyChosenSkills: action.skills ? [...action.skills] : [],
      categoryToEdit: action.category,
    };
  }

  if (action.type === 'edit') {
    return {
      isModalOpen: !state.isModalOpen,
      previouslyChosenSkills: action.skills ? [...action.skills] : [],
      categoryToEdit: action.category,
    };
  }

  return state;
}
