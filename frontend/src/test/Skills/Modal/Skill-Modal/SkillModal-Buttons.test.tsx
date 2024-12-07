import {
  cleanup,
  fireEvent,
  render,
  screen,
  within,
} from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { TEST_KATEGORIEN } from './SkillModal.test';
import SkillModal from '../../../../components/Skills/Modals/SkillModal/SkillModal';
import { UpdateActions } from '../../../../components/Skills/SkillTypes';
import { _throw } from '../../../../utils/_throw';

describe('SkillModal Buttons', () => {
  afterEach(() => {
    cleanup();
  });

  const mockClose = vi.fn();
  const mockDispatch = vi.fn();

  it('should call delete when Löschen was pressed', () => {
    const previouslyChosenCategories = [TEST_KATEGORIEN[0]];
    const testSkill = {
      skillId: 42,
      skillName: 'Eine  Trilogie in 5 Bänden',
    };
    render(
      <SkillModal
        isModalOpen={true}
        skillToEdit={testSkill}
        skillCategories={TEST_KATEGORIEN}
        previouslyChosenCategories={previouslyChosenCategories}
        onClose={mockClose}
        dispatchSkillCategoriesState={mockDispatch}
      /> 
    );

    const modal = screen.getByTestId('modal');
    const buttonSection = within(modal).getByTestId('buttons');
    const buttons = within(buttonSection).queryAllByRole('button', {
      hidden: true,
    });
    const deleteButton = buttons.find(
      (button) => button.textContent === 'Löschen'
    );

    if(deleteButton == null){
      _throw('This test should fail if button is null')
    }

    fireEvent.click(deleteButton);

    const expectedCallObject = {
      type: UpdateActions.DELETE_SKILL,
      skillToEdit: testSkill,
      categoryArray: previouslyChosenCategories,
    };

    expect(mockDispatch).toHaveBeenCalledWith(expectedCallObject);
  });

  it('should call update, when Skill is selected and speichern was pressed', () => {
    const previouslyChosenCategories = [TEST_KATEGORIEN[0]];
    const testSkill = {
      skillId: 42,
      skillName: 'Eine  Trilogie in 5 Bänden',
    };
    render(
      <SkillModal
        isModalOpen={true}
        skillToEdit={testSkill}
        skillCategories={TEST_KATEGORIEN}
        previouslyChosenCategories={previouslyChosenCategories}
        onClose={mockClose}
        dispatchSkillCategoriesState={mockDispatch}
      />
    );

    const modal = screen.getByTestId('modal');
    const buttonSection = within(modal).getByTestId('buttons');
    const buttons = within(buttonSection).queryAllByRole('button', {
      hidden: true,
    });
    const updateButton = buttons.find(
      (button) => button.textContent === 'Änderungen Speichern'
    );

    if(updateButton == null){
      _throw('This test should fail if button is null')
    }

    fireEvent.click(updateButton);

    const expectedCallObject = {
      type: UpdateActions.UPDATE_SKILL,
      zuManipulierenderSkill: testSkill,
      categoryArray: previouslyChosenCategories,
      skillName: testSkill.skillName,
    };

    expect(mockDispatch).toHaveBeenCalledWith(expectedCallObject);
  });

  it('should call Speichern, when no Skill is selected and speichern was pressed', () => {
    render(
      <SkillModal
        isModalOpen={true}
        skillToEdit={undefined}
        skillCategories={TEST_KATEGORIEN}
        previouslyChosenCategories={[]}
        onClose={mockClose}
        dispatchSkillCategoriesState={mockDispatch}
      />
    );

    const modal = screen.getByTestId('modal');
    const buttonSection = within(modal).getByTestId('buttons');
    const buttons = within(buttonSection).queryAllByRole('button', {
      hidden: true,
    });
    const speicherButton = buttons.find(
      (button) => button.textContent === 'Speichern'
    );

    if(speicherButton == null){
      _throw('This test should fail if button is null')
    }

    fireEvent.click(speicherButton);

    const expectedCallObject = {
      type: UpdateActions.Add_SKILL,
      categoryArray: [],
      skillName: '',
    };

    expect(mockDispatch).toHaveBeenCalledWith(expectedCallObject);
  });
});
