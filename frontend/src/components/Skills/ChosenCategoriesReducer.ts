import { _throw } from '../../utils/_throw';
import { Category, UpdateChosenCategoriesAction, Actions } from './SkillTypes';

export function reducer(
  state: Category[],
  action: UpdateChosenCategoriesAction
): Category[] {
  if (action.type === Actions.REMOVE_SKILLKATEGORIE) {
    let neueCategories = [...state];
    neueCategories = neueCategories.filter(
      (category) => category != action.currentCategory
    );

    return neueCategories;
  }

  if (action.type === Actions.DELETE_SKILL_FROM_KATEGORIE) {
    const neueCategories = [...state];
    const zuAenderndeCategory = neueCategories.find(
      (category) => category == action.currentCategory
    ) ?? _throw('No matching category found');

    let newSkills = [...zuAenderndeCategory.skills];
    newSkills = newSkills.filter(
      (skill) => skill != action.skillToEdit
    );
    zuAenderndeCategory.skills = newSkills;

    return neueCategories;
  }

  if (action.type == Actions.CHANGE_GEWAEHLTE_KATEGORIE) {
    const neueCategories = [...state];
    const aktuelleCategoriesIndex = state.indexOf(
      action.currentCategory ?? _throw('No parameter \'category\' was given')
    );
    neueCategories[aktuelleCategoriesIndex] = action.newChosenCategory ?? _throw('No category was picked');
    return neueCategories;
  }

  if (action.type === Actions.ADD_KATEGORIE) {
    return [...state, action.newChosenCategory ?? _throw('No category was picked')]
  }

  if (action.type === Actions.SET_AUSGEWAEHLTE_KATEGORIEN) {
    return action.categoryArray ?? _throw('Missing categories'); 
  }

  return state;
}