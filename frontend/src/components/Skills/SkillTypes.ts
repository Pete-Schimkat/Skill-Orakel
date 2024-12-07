export type Category = {
  categoryId: number;
  categoryName: string;
  skills: Skill[];
};

export type Skill = {
  skillId: number;
  skillName: string;
};

export type UpdateChosenCategoriesAction = {
  type: string;
  currentCategory?: Category;
  skillToEdit?: Skill;
  newChosenCategory?: Category;
  categoryArray?: Category[];
};

export type UpdateAction = {
  type: string;
  categoryArray?: Category[];
  skillName?: string;
  categoryName?: string;
  skillToEdit?: Skill;
  category?: Category;
  skills?: Skill[];
};

export type CategoryModalState = {
  isModalOpen: boolean;
  previouslyChosenSkills: Skill[];
  categoryToEdit: Category | undefined;
};

export type SkillModalAction = {
  type: string;
  skill?: Skill;
  categories?: Category[];
  chosenCategory?: Category;
};

export type ModalPayloadType = {
  categories?: Category[],
  chosenCategory: Category
}

export type SkillModalState = {
  isModalOpen: boolean;
  previouslyChosenCategories: Category[];
  skillToEdit: Skill | undefined;
};

export type CategoryModalAction = {
  type: string;
  category?: Category;
  skills?: Skill[];
};

export type GenericProps<T> = {
  data: T[];
  preChosen: T[];
  chosenElement: string | undefined;
  isModalOpen: boolean;
  isEditing: boolean;
  getOptionLabel: (option: T) => string;
  changeOption: (neuerWert: T[]) => void;
  changeText: (neuerWert: string) => void;
  handleSave: () => void;
  handleDelete: () => void;
  handleClear: () => void;
};

export enum Actions {
  REMOVE_SKILLKATEGORIE = 'removeSkillCategory',
  DELETE_SKILL_FROM_KATEGORIE = 'deleteSkillFromCategory',
  CHANGE_GEWAEHLTE_KATEGORIE = 'changeGewaehlteCategory',
  SET_AUSGEWAEHLTE_KATEGORIEN = 'setAusgewaehlteCategories',
  ADD_KATEGORIE = 'addCategory',
}

export enum UpdateActions {
  Add_SKILL = 'addSkill',
  UPDATE_SKILL = 'updateSkill',
  DELETE_SKILL = 'deleteSkill',
  SET_DATA = 'setData',
  UPDATE_KATEGORIE = 'update Category',
  DELETE_KATEGORIE = 'delete category',
  ADD_KATEGORIE = 'add category',
}
