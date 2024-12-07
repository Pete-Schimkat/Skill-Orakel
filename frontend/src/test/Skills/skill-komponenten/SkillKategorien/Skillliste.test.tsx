import { afterEach, describe, expect, it, vi } from 'vitest';
import Skillliste from '../../../../components/Skills/SkillComponents/SkillCategories/SkillList';
import { cleanup, fireEvent, render, screen, within } from '@testing-library/react';
import { Skill } from '../../../../components/Skills/SkillTypes';
import { CATEGORIES } from '../Skillkategorie.test';

const SKILLS: Skill[] = [
  {
    skillId: 1,
    skillName: 'Java',
  },
  {
    skillId: 2,
    skillName: 'C#',
  },
  {
    skillId: 3,
    skillName: 'C',
  },
];

describe('Skilliste', () => {
  afterEach(() => {
    cleanup();
  });
  const mockDispatch = vi.fn();

  it('should render the correct number of skills', () => {
    render(
      <Skillliste
        skills={SKILLS}
        dispatchModalState={mockDispatch}
        modalPayload={{categories: CATEGORIES, chosenCategory: CATEGORIES[0] }}
      />
    );

    const select = screen.getByRole('combobox');
    const chips = within(select).getAllByRole('button');

    expect(chips.length).toEqual(SKILLS.length);
  });

  it('should be empty, if no skills are available', () => {
    render(
      <Skillliste
        skills={[]}
        dispatchModalState={mockDispatch}
        modalPayload={{categories: CATEGORIES, chosenCategory: CATEGORIES[0] }}
      />
    );

    const select = screen.getByRole('combobox');
    const chips = within(select).queryAllByRole('button');

    expect(chips.length).toEqual(0);
  });

  it('should call mockHandleChange when delete button was clicked', () => {
    render(
      <Skillliste
        skills={SKILLS}
        dispatchModalState={mockDispatch}
        modalPayload={{categories: CATEGORIES, chosenCategory: CATEGORIES[0] }}
      />
    );

    const select = screen.getByRole('combobox');
    const chip = within(select).getAllByRole('button')[0];
    fireEvent.click(chip);

    expect(mockDispatch).toHaveBeenCalled();
  });
});
