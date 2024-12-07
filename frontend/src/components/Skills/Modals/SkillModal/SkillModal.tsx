import { useEffect, useState } from 'react';
import GenericModal from '../GenericModal';
import { Category, Skill } from '../../SkillTypes';
import { UpdateAction, UpdateActions } from '../../SkillTypes';

const SkillModal: React.FC<{
  isModalOpen: boolean;
  skillCategories: Category[];
  previouslyChosenCategories: Category[];
  skillToEdit: Skill | undefined;
  onClose: () => void;
  dispatchSkillCategoriesState: React.Dispatch<UpdateAction>;
}> = (props) => {
  const [category, setCategories] = useState<Category[]>([]);
  const [skillName, setSkillName] = useState<string>('');

  useEffect(() => {
    setSkillName('');

    if(props.skillToEdit != null) {
      setSkillName(props.skillToEdit.skillName);
    }

    setCategories((oldCategories) => [
      ...oldCategories,
      ...props.previouslyChosenCategories,
    ]);
  }, [
    props.skillToEdit,
    props.previouslyChosenCategories,
    setCategories,
  ]);

  function handleSave() {
    const callObject = props.skillToEdit
      ? {
          type: UpdateActions.UPDATE_SKILL,
          zuManipulierenderSkill: props.skillToEdit,
        }
      : {
          type: UpdateActions.Add_SKILL,
        };
    props.dispatchSkillCategoriesState({
      ...callObject,
      categoryArray: category,
      skillName,
    });
    handleClear();
  }

  function handleDelete() {
    props.dispatchSkillCategoriesState({
      type: UpdateActions.DELETE_SKILL,
      skillToEdit: props.skillToEdit,
      categoryArray: props.previouslyChosenCategories,
    });
    handleClear();
  }

  function handleClear() {
    setCategories([]);
    setSkillName('');
    props.onClose();
  }

  return (
    <GenericModal<Category>
      isModalOpen={props.isModalOpen}
      data={props.skillCategories}
      preChosen={category}
      getOptionLabel={(category) => category.categoryName}
      chosenElement={skillName}
      isEditing={!!props.skillToEdit}
      changeOption={setCategories}
      changeText={setSkillName}
      handleClear={handleClear}
      handleDelete={handleDelete}
      handleSave={handleSave}
    />
  );
};

export default SkillModal;
