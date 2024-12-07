import { cleanup, render, screen } from '@testing-library/react';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import IndustryKnowledge from '../../../components/SkillProfile/IndustryKnowledge/IndustryKnowledge';
import { Industry } from '../../../types/types';
import userEvent from '@testing-library/user-event';

const testIndustries: Industry[] = [
    { id: '1', name: 'Automobilindustrie' },
    { id: '3', name: 'Informationstechnik'}   
]

describe('test industry knowledge component', () => {

    beforeEach(() => {
        cleanup();
    });

    const amountOfExpectedBranches = 8;
    const mockFunction:React.Dispatch<React.SetStateAction<Industry[]>> = vi.fn();

    it('renders the correct amount of checkboxes', () => {
        render(<IndustryKnowledge industryKnowledge={[]} setIndustries={mockFunction}></IndustryKnowledge>);
        const checkboxes = screen.getAllByRole('checkbox');

        expect(checkboxes.length).toBe(amountOfExpectedBranches);
    });

    it('renders the correct checked checkboxes', () => {
        render(<IndustryKnowledge industryKnowledge={testIndustries} setIndustries={mockFunction} ></IndustryKnowledge>);
       
        expect(screen.getByLabelText('Automobilindustrie')).to.have.property('checked', true);
        expect(screen.getByLabelText('Informationstechnik')).to.have.property('checked', true);
    });

    it('renders the correct amount of checked checkboxes', () => {
        render(<IndustryKnowledge industryKnowledge={testIndustries} setIndustries={mockFunction} ></IndustryKnowledge>);
        
        expect(screen.getAllByRole('checkbox').length).toBe(amountOfExpectedBranches);
    });

    it('render checkbox as checked when user clicks on it', async () => {
        const user = userEvent.setup();
        render(<IndustryKnowledge industryKnowledge={testIndustries} setIndustries={mockFunction}></IndustryKnowledge>);
        const checkboxToClick = screen.getByLabelText('Schienenfahrzeugindustrie');
        
        await user.click(checkboxToClick);
        
        expect(checkboxToClick).to.have.property('checked', true);
    });

    it('renders previously checked Checkboxes as unchecked when user clicks on it', async () => {
        const user = userEvent.setup();
        render(<IndustryKnowledge industryKnowledge={testIndustries} setIndustries={mockFunction}></IndustryKnowledge>);
        const checkboxToClick = screen.getByLabelText('Informationstechnik');

        await user.click(checkboxToClick);

        expect(checkboxToClick).to.have.property('checked', false);
    });
})