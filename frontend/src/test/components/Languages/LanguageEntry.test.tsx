import { cleanup, render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import { LanguageEntry } from '../../../components/SkillProfile/Languages/LanguageEntry';
import { LanguageKnowledge } from '../../../types/types';
import { LanguageKnowledgeBusinessObjectLanguageLevelEnum } from '../../../generated';

const testData: LanguageKnowledge = {
    languageId: '12312',
    languageName: 'Klingonisch',
    languageLevel: LanguageKnowledgeBusinessObjectLanguageLevelEnum.Verhandlungssicher,
};

beforeEach(() => {
    cleanup();
})

describe('test language entry component', () => {
    it('renders textfield for language name with correct value', () => {

        render(<LanguageEntry
            languageEntry={testData}
            key={testData.languageId}
            changeLanguageEntry={vi.fn()}
            changeLanguageLevel={vi.fn()}
            deleteLanguageEntry={vi.fn()}></LanguageEntry>)

        const languageNameTextField = screen.getByLabelText('Sprache');
        expect(languageNameTextField).toHaveDisplayValue(testData.languageName);
    });

    it('renders textfield for sprachLevel with correct value', () => {

        render(<LanguageEntry
            languageEntry={testData}
            key={testData.languageId}
            changeLanguageEntry={vi.fn()}
            changeLanguageLevel={vi.fn()}
            deleteLanguageEntry={vi.fn()}></LanguageEntry>)

        const langangeLevelField = screen.getByLabelText('LanguageLevel');
        expect(langangeLevelField).toHaveDisplayValue(testData.languageLevel);
    });

    it('calls changeSprachEintrag when user types in the sprachlevel field', async () => {

        const user = userEvent.setup();
        const changeSpracheintragMock = vi.fn();

        render(<LanguageEntry
            languageEntry={testData}
            key={testData.languageId}
            changeLanguageEntry={changeSpracheintragMock}
            changeLanguageLevel={vi.fn()}
            deleteLanguageEntry={vi.fn()}></LanguageEntry>)
        
        const languageNameTextField = screen.getByLabelText('Sprache');
        await user.type(languageNameTextField, 'Spanisch');
        await user.tab();

        expect(changeSpracheintragMock).toBeCalled();
    });

    it('renders deleteButton', () => {
        render(<LanguageEntry
            languageEntry={testData}
            key={testData.languageId}
            changeLanguageEntry={vi.fn()}
            changeLanguageLevel={vi.fn()}
            deleteLanguageEntry={vi.fn()}></LanguageEntry>)
        
        const deleteButton = screen.getAllByRole('button').find(button => button.ariaLabel == 'delete');

        expect(deleteButton).toBeInTheDocument();
    })

    it('calls changeSprachLevel when user types in the sprachen field', async () => {

        const user = userEvent.setup();
        const changeSprachLevel = vi.fn();

        render(<LanguageEntry
            languageEntry={testData}
            key={testData.languageId}
            changeLanguageEntry={changeSprachLevel}
            changeLanguageLevel={vi.fn()}
            deleteLanguageEntry={vi.fn()}></LanguageEntry>)
        
        const languageNameTextField = screen.getByLabelText('Sprache');
        await user.type(languageNameTextField, 'Ersprache') 
        await user.tab();

        expect(changeSprachLevel).toBeCalled();
    });

    it('calls deleteSpracheintrag when user clicks delete button', async () => {

        const user = userEvent.setup();
        const deleteSprachEintrag = vi.fn();
        
        render(<LanguageEntry
            languageEntry={testData}
            key={testData.languageId}
            changeLanguageEntry={vi.fn()}
            changeLanguageLevel={vi.fn()}
            deleteLanguageEntry={deleteSprachEintrag}></LanguageEntry>)
        
        const deleteButton = screen.getAllByRole('button').find(button => button.ariaLabel == 'delete');
        if(deleteButton){
            await user.click(deleteButton);
        }
        
        expect(deleteSprachEintrag).toBeCalled();
    });
});

