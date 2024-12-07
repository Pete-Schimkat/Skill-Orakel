import { SelectChangeEvent } from '@mui/material';
import Button from '@mui/material/Button';

import CategoryMenu from './SkillCategories/CategoryMenu';
import Skillliste from './SkillCategories/SkillList';
import {
  Category,
  CategoryModalAction,
  SkillModalAction,
  UpdateChosenCategoriesAction,
  Actions,
  ModalPayloadType,
} from '../SkillTypes';
import { _throw } from '../../../utils/_throw';

const Skillcategory: React.FC<{
  verbleibendeSkillcategories: Category[];
  gewaehlteCategory: Category;
  modalPayload: ModalPayloadType;
  dispatchSkillModalState: React.Dispatch<SkillModalAction>;
  dispatchAusgewaehlteCategories: React.Dispatch<UpdateChosenCategoriesAction>;
  dispatchCategoryModalState: React.Dispatch<CategoryModalAction>;
}> = (props) => {
 
  function handleChange(event: SelectChangeEvent): void {
    const neuerSkillCategoryName = event.target.value;
    
    const neueGewaehlteCategory = skillCategories.find(
      (skillCategory) =>
        skillCategory.categoryName === neuerSkillCategoryName
    ) ?? _throw('skill category not found');

    props.dispatchAusgewaehlteCategories({
      type: Actions.CHANGE_GEWAEHLTE_KATEGORIE,
      currentCategory: gewaehlteCategory,
      newChosenCategory: neueGewaehlteCategory,
    });
  }

  const gewaehlteCategory = props.gewaehlteCategory;

  const skillCategories = [
    ...props.verbleibendeSkillcategories,
    gewaehlteCategory,
  ];
  const skillCategoryName = gewaehlteCategory.categoryName;
  const skills = gewaehlteCategory.skills;

  return (
    <li id="list-item" data-testid="list-item">
      <CategoryMenu
        skillCategories={skillCategories}
        handleChange={handleChange}
        openCategoryModal={() =>
          props.dispatchCategoryModalState({
            type: 'edit',
            category: gewaehlteCategory,
            skills: gewaehlteCategory.skills,
          })
        }
        skillCategoryName={skillCategoryName}
      />
      <Skillliste
        skills={skills ? skills : []}
        modalPayload={props.modalPayload}
        dispatchModalState={props.dispatchSkillModalState}
      />
      <Button
        onClick={() =>
          props.dispatchAusgewaehlteCategories({
            type: Actions.REMOVE_SKILLKATEGORIE,
            currentCategory: gewaehlteCategory,
          })
        }
        variant="outlined"
        size="small"
        id="delete-button"
        data-testid="delete-button"
      >
        <strong>X</strong>
      </Button>
    </li>
  );
};

export default Skillcategory;
