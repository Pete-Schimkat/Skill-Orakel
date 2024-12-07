import { useEffect, useState } from 'react';
import GenericModal from '../GenericModal';
import { Category, Skill, UpdateAction, UpdateActions } from '../../SkillTypes';

const CategoryModal: React.FC<{
  skills: Skill[];
  previouslyChosenSkills: Skill[];
  isModalOpen: boolean;
  skillCategories: Category[];
  categoryToEdit: Category | undefined;
  onClose: () => void;
  dispatchSkillCategories: (action: UpdateAction) => void;
}> = (props) => {
  const [categoryName, setCategoryName] = useState<string>('');
  const [skills, setSkills] = useState<Skill[]>([]);

  useEffect(() => {
    setCategoryName('');

    if(props.categoryToEdit != null){
      setCategoryName(props.categoryToEdit.categoryName);
    }

    setSkills((oldSkills) => [...oldSkills, ...props.previouslyChosenSkills]);
  }, [props.categoryToEdit, props.previouslyChosenSkills]);

  const isEditing = !!props.categoryToEdit;

  function handleClear() {
    setSkills([]);
    setCategoryName('');
    props.onClose();
  }

  function handleDelete() {
    if (!props.categoryToEdit) {
      throw new Error('kann category nicht finden');
    }

    props.dispatchSkillCategories({
      type: UpdateActions.DELETE_KATEGORIE,
      category: props.categoryToEdit,
    });
    handleClear();
  }

  function handleSave() {
    const category: Category = {
      categoryId: 637890,
      categoryName: categoryName,
      skills: skills,
    };
    const callObject = isEditing
      ? {
          type: UpdateActions.UPDATE_KATEGORIE,
          category: props.categoryToEdit,
          categoryName: categoryName,
          skills: skills,
        }
      : {
          type: UpdateActions.ADD_KATEGORIE,
          category,
        };

    props.dispatchSkillCategories(callObject);
    handleClear();
  }

  return (
    <GenericModal<Skill>
      data={props.skills}
      preChosen={skills}
      isModalOpen={props.isModalOpen}
      chosenElement={categoryName}
      isEditing={isEditing}
      getOptionLabel={(skill) => skill.skillName}
      changeOption={setSkills}
      changeText={setCategoryName}
      handleClear={handleClear}
      handleDelete={handleDelete}
      handleSave={handleSave}
    />
  );
};

export default CategoryModal;
