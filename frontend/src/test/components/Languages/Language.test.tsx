import { cleanup, render, screen } from '@testing-library/react';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import { Languages } from '../../../components/SkillProfile/Languages/Languages';
import { LanguageKnowledge } from '../../../types/types';
import { LanguageKnowledgeBusinessObjectLanguageLevelEnum } from '../../../generated';

describe('test language component', () => {

    const languageKnowledges: LanguageKnowledge[] = [
        {
            languageId: '12351',
            languageName: 'Französisch',
            languageLevel: LanguageKnowledgeBusinessObjectLanguageLevelEnum.Verhandlungssicher
        },
    ];

    const setLanguageKnowledges:React.Dispatch<React.SetStateAction<LanguageKnowledge[]>> = vi.fn();

    beforeEach(() => {
        cleanup();
    })

    it('renders a textfield with the value of Französisch', () => {

        render(<Languages languages={languageKnowledges} setLanguages={setLanguageKnowledges}></Languages>);
        const sprachNameTextField = screen.getByDisplayValue('Französisch');
        
        expect(sprachNameTextField).toBeInTheDocument();
    });

    it('renders a textfield with verhandlungssicher', () => {

        render(<Languages languages={languageKnowledges} setLanguages={setLanguageKnowledges}></Languages>);
        const languageLevelTextField = screen.getByDisplayValue(languageKnowledges[0].languageLevel);
        
        expect(languageLevelTextField).toBeInTheDocument();
    });
})