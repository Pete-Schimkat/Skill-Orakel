import { render, screen, fireEvent, within } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import CategoryMenu from '../../../../components/Skills/SkillComponents/SkillCategories/CategoryMenu';
import { Category } from '../../../../components/Skills/SkillTypes';

const skillCategories: Category[] = [
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
    ],
  },
  {
    categoryId: 3,
    categoryName: 'Programmiersprache',
    skills: [
      {
        skillId: 6,
        skillName: 'Java',
      },
      {
        skillId: 7,
        skillName: 'JavaScript',
      },
    ],
  },
];

describe('CategoryMenu', () => {
  const mockHandleChange = vi.fn();

  it('should render the correct number of MenuItems', async () => {
    render(
      <CategoryMenu
        skillCategories={skillCategories}
        handleChange={mockHandleChange}
        skillCategoryName="Frontend"
        openCategoryModal={function (): void {
          throw new Error('Function not implemented.');
        }}
      />
    );

    const select = screen.getByRole('combobox');
    fireEvent.mouseDown(select);

    //select dropdown is rendered in a diferent part if DOM and has to be accessed the following way
    const listbox = within(screen.getByRole('presentation')).getByRole(
      'listbox'
    );
    const options = within(listbox).getAllByRole('option');

    const optionValues = options.map((li) => li.getAttribute('data-value'));

    expect(optionValues.length).toEqual(skillCategories.length);
  });

  it('should redner no menun Item, when skills are not available', () => {
    render(
      <CategoryMenu
        skillCategories={[]}
        handleChange={mockHandleChange}
        skillCategoryName=""
        openCategoryModal={function (): void {
          throw new Error('Function not implemented.');
        }}
      />
    );

    const select = screen.getByRole('combobox');
    fireEvent.mouseDown(select);

    const dropdown = screen.queryAllByRole('presentation');

    expect(!dropdown);
  });

  it('should call handleChange when a MenuItem was clicked', async () => {
    render(
      <CategoryMenu
        skillCategories={skillCategories}
        handleChange={mockHandleChange}
        skillCategoryName="Frontend"
        openCategoryModal={function (): void {
          throw new Error('Function not implemented.');
        }}
      />
    );

    const select = screen.getByRole('combobox');
    fireEvent.mouseDown(select);

    const listbox = within(screen.getByRole('presentation')).getByRole(
      'listbox'
    );
    const options = within(listbox).getAllByRole('option');
    const datenbankenMenuItem = options.filter(
      (option) => option.getAttribute('data-value') === 'Datenbanken'
    )[0];

    fireEvent.click(datenbankenMenuItem);

    expect(mockHandleChange).toHaveBeenCalled();
  });
});
