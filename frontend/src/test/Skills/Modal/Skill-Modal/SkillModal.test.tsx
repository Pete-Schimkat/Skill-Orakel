import {
  cleanup,
  fireEvent,
  render,
  screen,
  within,
} from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import userEvent from '@testing-library/user-event';
import SkillModal from '../../../../components/Skills/Modals/SkillModal/SkillModal';
import { UpdateActions } from '../../../../components/Skills/SkillTypes';
import { _throw } from '../../../../utils/_throw';

export const TEST_KATEGORIEN = [
  {
    categoryId: 1,
    categoryName: 'Frontend',
    skills: [
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
    ],
  },
  {
    categoryId: 2,
    categoryName: 'Datenbanken',
    skills: [
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
    ],
  },
];

describe('SkillModal', () => {
  afterEach(() => {
    cleanup();
  });

  const mockClose = vi.fn();
  const mockDispatch = vi.fn();

  it('should display a value that was entered in the input field', async () => {
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
    const container = within(modal).getByTestId('skill-modal');
    const input = within(container).getByRole('form', { hidden: true });
    const inputfield = within(input).getByDisplayValue('');
    await userEvent.type(inputfield, 'Hier könnte ihre Werbung stehen');

    const text = inputfield.getAttribute('value');
    expect(text).toEqual('Hier könnte ihre Werbung stehen');
  });

  it ('should forward an entered name and a number of selected categorys to the dispatch function', async () => {
    const chosenText = 'Hier könnte ihre Werbung stehen'
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
    const container = within(modal).getByTestId('skill-modal');
    
    //enter a new value into the input field
    const input = within(container).getByRole('form', {hidden: true});
    const inputfield = within(input).getByDisplayValue('');
    await userEvent.type(inputfield, chosenText);

    //select some categorys
    const autoComplete = within(modal).getByTestId('modal-autocomplete');
    const dropDownIcon = within(autoComplete).getByTestId('ArrowDropDownIcon');
    fireEvent.click(dropDownIcon);

    const dropdown = screen.getByRole('presentation');
    const options = within(dropdown).getAllByRole('option');
    options.forEach(option => fireEvent.click(option));

    //press the save button
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
      categoryArray: TEST_KATEGORIEN,
      skillName: chosenText
    }

    expect(mockDispatch).toHaveBeenCalledWith(expectedCallObject);
  })  
});
