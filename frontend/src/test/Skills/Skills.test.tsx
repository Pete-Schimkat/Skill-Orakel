import {
  cleanup,
  fireEvent,
  render,
  screen,
  waitFor,
  within,
} from '@testing-library/react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import Skills from '../../components/Skills/Skills';
const KATEGORIEN = [
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
  {
    categoryId: 4,
    categoryName: 'sehr gut gefüllter Skill',
    skills: [],
  },
];

describe('Skills', () => {
  afterEach(() => {
    cleanup();
  });

  it('should display the correct number of skill categories', async () => {
    const mockAPICall = vi.fn().mockResolvedValue(KATEGORIEN);
    await waitFor(() => {
      render(<Skills fetchSkillCategoryData={mockAPICall} />);
    });

    const skillcategories = screen.getByTestId('skills');
    const skillCategories =
      within(skillcategories).getAllByTestId('list-item');

    expect(skillCategories.length).toEqual(KATEGORIEN.length);  
  });

  it('show No Data Available if the API was not successfull', async () => {
    const mockAPICall = vi.fn().mockResolvedValue(undefined);

    await waitFor(() => {
      render(<Skills fetchSkillCategoryData={mockAPICall} />);
    });

    const skillcategories = screen.getByTestId('skills');
    const fallback = within(skillcategories).getByTestId('fallback');
    expect(fallback);
  });

  it ('should delete Skillcategories on Button click', async () => {
    const mockAPICall = vi.fn().mockResolvedValue(KATEGORIEN);
    await waitFor(() => {
      render(<Skills fetchSkillCategoryData={mockAPICall} />);
    });

    const deleteButtons = screen.getAllByTestId('delete-button');
    const pressedDeleteButton = deleteButtons[0];
    fireEvent.click(pressedDeleteButton);

    const numberOfSkillcategories = screen.getAllByTestId('list-item');

    expect(numberOfSkillcategories.length).toEqual(KATEGORIEN.length - 1);
  });
});
