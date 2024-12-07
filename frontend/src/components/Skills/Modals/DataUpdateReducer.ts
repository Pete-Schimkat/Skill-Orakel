import { addSkillToCategories,
  deleteSkill,
  updateSkillsInCategories, } from './SkillModalUpdateLogic';
import { Category } from '../SkillTypes';
import { UpdateAction, UpdateActions } from '../SkillTypes';
import { _throw } from '../../../utils/_throw';

export function updateReducer(
  state: Category[],
  action: UpdateAction
): Category[] {
  if (action.type === UpdateActions.SET_DATA) {
    return action.categoryArray ?? _throw('Category array cannot be undefined');
  }

  if (action.type === UpdateActions.Add_SKILL) {
    if (!action.categoryArray || !action.skillName) {
      throw new Error();
    }

    const categoriesMitNeuemSkill = addSkillToCategories(
      state,
      action.categoryArray,
      action.skillName
    );
    return categoriesMitNeuemSkill;
  }

  if (action.type === UpdateActions.UPDATE_SKILL) {
    if (
      !action.categoryArray ||
      !action.skillName ||
      !action.skillToEdit
    ) {
      throw new Error();
    }

    const updatedSkillcategories = updateSkillsInCategories(
      state,
      action.categoryArray,
      action.skillToEdit,
      action.skillName
    );
    return updatedSkillcategories;
  }

  if (action.type === UpdateActions.DELETE_SKILL) {
    if (!action.categoryArray || !action.skillToEdit) {
      throw new Error();
    }
    const skillCategoriesWithDeletedSkill = deleteSkill(
      state,
      action.categoryArray,
      action.skillToEdit
    );
    return skillCategoriesWithDeletedSkill;
  }

  if (action.type === UpdateActions.UPDATE_KATEGORIE) {
    if (!action.category || !action.skills) {
      throw new Error('category oder skills sind nicht gesetzt');
    }
    const categoryId = action.category.categoryId;
    const newState = [...state];
    const zuAendern = newState.find(
      (category) =>
        category.categoryId === categoryId
    );
    if (!zuAendern || !(zuAendern.skills)) {
      throw new Error('kann zu ändernde Category nicht finden');
    }
    zuAendern.skills = action.skills;
    zuAendern.categoryName = action.categoryName ?? _throw('Category name cannot be undefined');
    return newState;
  }

  if (action.type === UpdateActions.DELETE_KATEGORIE) {
    if (!action.category) {
      throw new Error('category oder skills sind nicht gesetzt');
    }
    const categoryName = action.category.categoryName;
    const newState = [...state];
    const zuAendern = newState.filter(
      (category) =>
        category.categoryName !== categoryName
    );
    return zuAendern;
  }

  if (action.type === UpdateActions.ADD_KATEGORIE) {
    if (!action.category) {
      throw new Error('category oder skills sind nicht gesetzt');
    }
    const newState = [...state, action.category];
    return newState;
  }

  return state;
}