import { cleanup, render, screen, within } from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import SkillCategory from '../../../components/Skills/SkillComponents/SkillCategory';
import { Category } from '../../../components/Skills/SkillTypes';
import { _throw } from '../../../utils/_throw';

export const CATEGORIES: Category[]= [
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
  {
    categoryId: 4,
    categoryName: 'sehr gut gefüllter Skill',
    skills: [],
  },
];

const gewaehlteCategory = {
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
};

describe('Skillcategory', () => {
  afterEach(() => {
    cleanup();
  });

  const mockDispatchModal = vi.fn();
  const mockDispatchCategory = vi.fn();

  it('should render the chosen chosen category with the correct number of associated skills', () => {
    render(
      <SkillCategory
        verbleibendeSkillcategories={CATEGORIES}
        gewaehlteCategory={gewaehlteCategory}
        dispatchAusgewaehlteCategories={mockDispatchCategory}
        dispatchSkillModalState={mockDispatchModal}
        modalPayload={{categories: CATEGORIES, chosenCategory: CATEGORIES[0] }} dispatchCategoryModalState={function (): void {
          throw new Error('Function not implemented.');
        } }      />
    );

    const skillCategory = screen.getByTestId('list-item');
    const dropdowns = within(skillCategory).getAllByRole('combobox');

    const category = dropdowns.find(
      (select) => select.getAttribute('id') === 'select'
    );

    if(category == null){
      _throw('test should fail if category is null')
    }

    const categoryName = within(category).queryByText(
      gewaehlteCategory.categoryName
    );

    if(categoryName == null){
      _throw('test should fail if categoryName is null')
    }

    const skills = dropdowns.find(
      (select) => select.getAttribute('id') === 'skill-liste'
    );

    if(skills == null){
      _throw('test should fail if categoryName is null')
    }
  
    const chips = within(skills).getAllByRole('button');

    expect(categoryName && chips.length === gewaehlteCategory.skills.length);
  });
});
 