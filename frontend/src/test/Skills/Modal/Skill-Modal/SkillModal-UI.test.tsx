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

describe('SkillModal-UI', () => {
  afterEach(() => {
    cleanup();
  });

  const mockClose = vi.fn();
  const mockDispatch = vi.fn();

  it('should display the correct number of vorausgewählte Categories', async () => {
    const vorausGewaehlteCategories = [TEST_KATEGORIEN[0]];
    render(
      <SkillModal
        isModalOpen={true}
        skillToEdit={undefined}
        skillCategories={TEST_KATEGORIEN}
        previouslyChosenCategories={vorausGewaehlteCategories}
        onClose={mockClose}
        dispatchSkillCategoriesState={mockDispatch}
      />
    );

    const modal = screen.getByTestId('modal');
    const autocomplete = within(modal).getByTestId('modal-autocomplete');
    const chips = within(autocomplete).getAllByTestId('CancelIcon');

    expect(chips.length).toEqual(vorausGewaehlteCategories.length);
  });

  it('should dsiplay no category if none was chosen before', () => {
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
    const autocomplete = within(modal).getByTestId('modal-autocomplete');
    const chips = within(autocomplete).queryAllByTestId('CancelIcon');
    expect(chips.length).toEqual(0);
  });

  it('should render Löschen Button, when skill is not undefined', () => {
    render(
      <SkillModal
        isModalOpen={true}
        skillToEdit={{
          skillId: 42,
          skillName: 'Eine  Trilogie in 5 Bänden',
        }}
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
    const buttonsText = buttons.map((button) => button.textContent);
    expect(buttonsText).toContain('Löschen');
  });

  it('should not a render Löschen Button, when skill is undefined', () => {
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
    const buttonsText = buttons.map((button) => button.textContent);
    expect(buttonsText).not.toContain('Löschen');
  });

  it('should render the correct number of dropdown icons', () => {
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
    const autoComplete = within(modal).getByTestId('modal-autocomplete');
    const dropDownIcon = within(autoComplete).getByTestId('ArrowDropDownIcon');
    fireEvent.click(dropDownIcon);

    const dropdown = screen.getByRole('presentation');
    const options = within(dropdown).getAllByRole('option');
    expect(options.length).toEqual(TEST_KATEGORIEN.length);
  });

  it('should highlght the given categorys', () => {
    const vorausGewaehlteCategories = [...TEST_KATEGORIEN];

    render(
      <SkillModal
        isModalOpen={true}
        skillToEdit={undefined}
        skillCategories={TEST_KATEGORIEN}
        previouslyChosenCategories={vorausGewaehlteCategories}
        onClose={mockClose}
        dispatchSkillCategoriesState={mockDispatch}
      />
    );

    const modal = screen.getByTestId('modal');
    const autoComplete = within(modal).getByTestId('modal-autocomplete');
    const dropDownIcon = within(autoComplete).getByTestId('ArrowDropDownIcon');
    fireEvent.click(dropDownIcon);

    const dropdown = screen.getByRole('presentation');
    const options = within(dropdown).getAllByRole('option');
    options.filter((option) => !option.getAttribute('aria-selected'));
    expect(options.length).toEqual(vorausGewaehlteCategories.length);
  });
});
