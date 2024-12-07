import { useEffect, useReducer } from 'react';
import './Skills.css';
import Skillcategory from './SkillComponents/SkillCategory';
import { reducer } from './ChosenCategoriesReducer';
import { Button } from '@mui/material';
import {
  categoryModalReducer,
  skillModalReducer,
} from './Modals/ModalReducer';
import {
  updateReducer,
} from './Modals/DataUpdateReducer';
import SkillModal from './Modals/SkillModal/SkillModal';
import CategoryModal from './Modals/CategoryModal/CategoryModal';
import { Category, UpdateActions, Actions } from './SkillTypes';

const harte_zwischenloesung = [
  {
    skillId: 125,
    skillName: 'dumme Bemerkungen',
  },
  {
    skillId: 126,
    skillName: 'Hyperaktivität',
  },
  {
    skillId: 127,
    skillName: 'mit Rauchen aufhören',
  },
  {
    skillId: 1,
    skillName: 'React',
  },
  {
    skillId: 2,
    skillName: 'Vue',
  },
  {
    skillId: 3,
    skillName: 'Swing',
  },
  {
    skillId: 4,
    skillName: 'PostgreSQL',
  },
  {
    skillId: 5,
    skillName: 'MongoDB',
  },
  {
    skillId: 9,
    skillName: "MySQL'); DROP TABLE SKILLS;--",
  },
  {
    skillId: 6,
    skillName: 'Java',
  },
  {
    skillId: 7,
    skillName: 'JavaScript',
  },
  {
    skillId: 8,
    skillName: 'Spring'
  }
];

const Skills: React.FC<{
  fetchSkillCategoryData: () => Promise<Category[] | undefined>;
}> = (props) => {
  const [ausgewaehlteCategories, dispatchAusgewaehlteCategories] = useReducer(
    reducer,
    []
  );
  const [skillCategories, dispatchSkillCategories] = useReducer(
    updateReducer,
    []
  );
  const [skillModalState, dispatchModalState] = useReducer(
    skillModalReducer,
    {
      isModalOpen: false,
      previouslyChosenCategories: [],
      skillToEdit: undefined,
    }
  );

  const [categoryModalState, dispatchCategoryModalState] = useReducer(
    categoryModalReducer,
    {
      isModalOpen: false,
      previouslyChosenSkills: [],
      categoryToEdit: undefined,
    }
  );

  useEffect(() => {
    const fetchSkillCategories = async () => {
      const loadedSkillCategories = await props.fetchSkillCategoryData();

      if (!loadedSkillCategories) {
        return;
      }

      dispatchSkillCategories({
        type: UpdateActions.SET_DATA,
        categoryArray: loadedSkillCategories,
      });

      dispatchAusgewaehlteCategories({
        type: Actions.SET_AUSGEWAEHLTE_KATEGORIEN,
        categoryArray: loadedSkillCategories,
      });
    };
    void fetchSkillCategories();
  }, [props]);

  useEffect(() => {
    dispatchAusgewaehlteCategories({
      type: Actions.SET_AUSGEWAEHLTE_KATEGORIEN,
      categoryArray: skillCategories,
    });
  }, [skillCategories]);

  const verbleibendeCategories = skillCategories.filter(
    (category) => !ausgewaehlteCategories.includes(category)
  );

  return (
    <div id="skill">
      <ul data-testid="skills" id="skills">
        {skillCategories && skillCategories.length !== 0 ? (
          ausgewaehlteCategories.map((category) => (
            <Skillcategory
              key={category.categoryId}
              verbleibendeSkillcategories={verbleibendeCategories}
              gewaehlteCategory={category}
              modalPayload={{
                categories: skillCategories,
                chosenCategory: category,
              }}
              dispatchSkillModalState={dispatchModalState}
              dispatchCategoryModalState={dispatchCategoryModalState}
              dispatchAusgewaehlteCategories={dispatchAusgewaehlteCategories}
            />
          ))
        ) : (
          <p data-testid="fallback">No Data Available</p>
        )}
      </ul>
      {verbleibendeCategories && verbleibendeCategories.length > 0 && (
        <Button
          id="add-button"
          variant="contained"
          size="small"
          data-testid="add-button"
          onClick={() =>
            dispatchAusgewaehlteCategories({
              type: Actions.ADD_KATEGORIE,
              newChosenCategory: verbleibendeCategories[0],
            })
          }
        >
          <strong>+</strong>
        </Button>
      )}
      <SkillModal
        isModalOpen={skillModalState.isModalOpen}
        skillCategories={skillCategories}
        previouslyChosenCategories={skillModalState.previouslyChosenCategories}
        skillToEdit={skillModalState.skillToEdit}
        onClose={() => dispatchModalState({ type: 'open' })}
        dispatchSkillCategoriesState={dispatchSkillCategories}
      />
      <CategoryModal
        skills={harte_zwischenloesung}
        previouslyChosenSkills={categoryModalState.previouslyChosenSkills}
        categoryToEdit={categoryModalState.categoryToEdit}
        isModalOpen={categoryModalState.isModalOpen}
        skillCategories={skillCategories}
        onClose={() => dispatchCategoryModalState({ type: 'open' })}
        dispatchSkillCategories={dispatchSkillCategories}
      />
      <Button id ="add-button" variant="contained" onClick={() => dispatchCategoryModalState({ type: 'open' })}>
        <strong>Add Category</strong>
      </Button>
    </div>
  );
};

export default Skills;
