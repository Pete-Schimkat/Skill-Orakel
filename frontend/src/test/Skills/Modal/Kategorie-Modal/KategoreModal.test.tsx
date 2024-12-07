import { cleanup, render, screen, within } from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import CategoryModal from '../../../../components/Skills/Modals/CategoryModal/CategoryModal';
import userEvent from '@testing-library/user-event';
import { TEST_KATEGORIEN } from '../Skill-Modal/SkillModal.test';

describe('Category Modal', async () => {
  afterEach(() => {
    cleanup();
  });

  it('should display a value, that was entered into the input field', async () => {
    const mockOnClose = vi.fn();
    const mockDisptatch = vi.fn();

    render(
      <CategoryModal
        skills={[]}
        previouslyChosenSkills={[]}
        isModalOpen
        skillCategories={[]}
        categoryToEdit={undefined}
        onClose={mockOnClose}
        dispatchSkillCategories={mockDisptatch}
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

  it('should display Änderungen speichern, when editing a skill', async () => {
    const mockOnClose = vi.fn();
    const mockDisptatch = vi.fn();

    render(
      <CategoryModal
        skills={[]}
        previouslyChosenSkills={[]}
        isModalOpen
        skillCategories={[]}
        categoryToEdit={TEST_KATEGORIEN[0]}
        onClose={mockOnClose}
        dispatchSkillCategories={mockDisptatch}
      />
    );

    const modal = screen.getByTestId('modal');
    const buttonSection = within(modal).getByTestId('buttons');
    const buttons = within(buttonSection).getAllByRole('button', {
      hidden: true,
    });
    const buttonsText = buttons.map((button) => button.textContent);

    expect(buttonsText).toContain('Änderungen Speichern');
  });

  it('should display Löschen, when editing a category', async () => {
    const mockOnClose = vi.fn();
    const mockDisptatch = vi.fn();

    render(
      <CategoryModal
        skills={[]}
        previouslyChosenSkills={[]}
        isModalOpen
        skillCategories={[]}
        categoryToEdit={TEST_KATEGORIEN[0]}
        onClose={mockOnClose}
        dispatchSkillCategories={mockDisptatch}
      />
    );

    const modal = screen.getByTestId('modal');
    const buttonsSection = within(modal).getByTestId('buttons');
    const buttons = within(buttonsSection).getAllByRole('button', {
      hidden: true,
    });
    const buttonsText = buttons.map((button) => button.textContent);

    expect(buttonsText).toContain('Löschen');
  });

  it('should not display Löschen, when not editing a category', async () => {
    const mockOnClose = vi.fn();
    const mockDispatch = vi.fn();

    render(
      <CategoryModal
        skills={[]}
        previouslyChosenSkills={[]}
        isModalOpen
        skillCategories={[]}
        categoryToEdit={undefined}
        onClose={mockOnClose}
        dispatchSkillCategories={mockDispatch}
      />
    );

    const modal = screen.getByTestId('modal');
    const buttonsSection = within(modal).getByTestId('buttons');
    const buttons = within(buttonsSection).getAllByRole('button', {
      hidden: true,
    });
    const buttonsText = buttons.map((button) => button.textContent);

    expect(buttonsText).not.toContain('Löschen');
  });
});
