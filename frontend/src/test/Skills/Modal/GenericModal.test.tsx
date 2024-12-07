import { afterEach, describe, expect, it, vi } from 'vitest';
import { TEST_KATEGORIEN } from './Skill-Modal/SkillModal.test';
import GenericModal from '../../../components/Skills/Modals/GenericModal';
import { cleanup, render, screen, within } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

describe('Generic Modal', () => {
  afterEach(() => {
    cleanup();
  });
  it('should render Änderungen Speichern, when istAmBearbiten is true', () => {
    const mockChange = vi.fn();
    const mockText = vi.fn();
    const mockClear = vi.fn();
    const mockDelete = vi.fn();
    const mockSave = vi.fn();

    render(
      <GenericModal
        data={TEST_KATEGORIEN}
        preChosen={[]}
        isModalOpen={true}
        chosenElement=""
        isEditing={true}
        getOptionLabel={(category) => category.categoryName}
        changeOption={mockChange}
        changeText={mockText}
        handleClear={mockClear}
        handleDelete={mockDelete}
        handleSave={mockSave}
      />
    );

    const modal = screen.getByTestId('modal');
    const modalContainer = within(modal).getByTestId('skill-modal');
    const buttonContainer = within(modalContainer).getByTestId('buttons');
    const buttonArray = within(buttonContainer).getAllByRole('button', {
      hidden: true,
    });
    const firstButton = buttonArray[0];
    const firstButtonText = firstButton.textContent;
    expect(firstButtonText).toEqual('Änderungen Speichern');
  });

  it('should call save, when speichern was pressed', async () => {
    const mockChange = vi.fn();
    const mockText = vi.fn();
    const mockClear = vi.fn();
    const mockDelete = vi.fn();
    const mockSave = vi.fn();

    render(
      <GenericModal
        data={TEST_KATEGORIEN}
        preChosen={[]}
        isModalOpen={true}
        chosenElement=""
        isEditing={true}
        getOptionLabel={(category) => category.categoryName}
        changeOption={mockChange}
        changeText={mockText}
        handleClear={mockClear}
        handleDelete={mockDelete}
        handleSave={mockSave}
      />
    );

    const modal = screen.getByTestId('modal');
    const modalContainer = within(modal).getByTestId('skill-modal');
    const buttonContainer = within(modalContainer).getByTestId('buttons');
    const buttonArray = within(buttonContainer).getAllByRole('button', {
      hidden: true,
    });
    const firstButton = buttonArray[0];
    await userEvent.click(firstButton);
    expect(mockSave).toHaveBeenCalled();
  });

  it('should display the correct number of prechosen elements', () => {
    const mockChange = vi.fn();
    const mockText = vi.fn();
    const mockClear = vi.fn();
    const mockDelete = vi.fn();
    const mockSave = vi.fn();

    render(
      <GenericModal
        data={TEST_KATEGORIEN}
        preChosen={TEST_KATEGORIEN}
        isModalOpen={true}
        chosenElement=""
        isEditing={true}
        getOptionLabel={(category) => category.categoryName}
        changeOption={mockChange}
        changeText={mockText}
        handleClear={mockClear}
        handleDelete={mockDelete}
        handleSave={mockSave}
      />
    );

    const modal = screen.getByTestId('modal');
    const modalContainer = within(modal).getByTestId('skill-modal');
    const autocomplete =
      within(modalContainer).getByTestId('modal-autocomplete');
    const options = within(autocomplete).getAllByTestId('CancelIcon');
    expect(options.length).toEqual(TEST_KATEGORIEN.length);
  });

  it('should call the chnage function, when an option is selected', async () => {
    const mockChange = vi.fn();
    const mockText = vi.fn();
    const mockClear = vi.fn();
    const mockDelete = vi.fn();
    const mockSave = vi.fn();

    render(
      <GenericModal
        data={TEST_KATEGORIEN}
        preChosen={[]}
        isModalOpen={true}
        chosenElement=""
        isEditing={true}
        getOptionLabel={(category) => category.categoryName}
        changeOption={mockChange}
        changeText={mockText}
        handleClear={mockClear}
        handleDelete={mockDelete}
        handleSave={mockSave}
      />
    );

    const modal = screen.getByTestId('modal');
    const modalContainer = within(modal).getByTestId('skill-modal');
    const autocomplete =
      within(modalContainer).getByTestId('modal-autocomplete');
    const dropdownButton =
      within(autocomplete).getByTestId('ArrowDropDownIcon');
    await userEvent.click(dropdownButton);

    const dropdown = screen.getByRole('presentation');
    const options = within(dropdown).getAllByRole('option');
    await userEvent.click(options[0]);
    
    expect(mockChange).toHaveBeenCalledWith([TEST_KATEGORIEN[0]]);
  });
});
