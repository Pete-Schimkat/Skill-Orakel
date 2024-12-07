import { useState } from 'react';

import AddIcon from '@mui/icons-material/Add';
import ClearIcon from '@mui/icons-material/Clear';
import { Button } from '@mui/material';
import { LanguageKnowledge } from '../../../types/types';
import { LanguageEntry } from './LanguageEntry';
import { NewLanguageEntry } from './NewLanguageEntry';
import { LanguageKnowledgeBusinessObjectLanguageLevelEnum } from '../../../generated';

export const Languages = ({ languages, setLanguages }: { languages: LanguageKnowledge[], setLanguages: React.Dispatch<React.SetStateAction<LanguageKnowledge[]>> }) => {

    const [isAddingNewEntry, setIsAddingNewEntry] = useState<boolean>(false);

    const onChangeLanguageEntry = (id: string, event: React.ChangeEvent<HTMLInputElement>) => {
        const newLanguages = [...languages];
        const changedEntry = findLanguageEntry(newLanguages, id);
        changedEntry.languageName = event?.target.value;
        setLanguages(newLanguages);
    }


    const onChangeLanguageLevel = (languageName: string, value: LanguageKnowledgeBusinessObjectLanguageLevelEnum | null) => {
        const newLanguages = [...languages];
        const changedEntry = findLanguageEntry(newLanguages, languageName);

        if (value == null) {
            throw new Error('Could not resolve value to languagelevel');
        }

        changedEntry.languageLevel = value;
        setLanguages(newLanguages);
    }

    const onDeleteLanguageEntry = (id: string) => {
        let newLanguages = [...languages];
        newLanguages = newLanguages.filter(sprachEntry => sprachEntry.languageId != id);
        setLanguages(newLanguages);
    }

    const onEditNewLanguageEntry = () => {
        setIsAddingNewEntry((previousState) => !previousState);
    }

    const addNewLanguage = (newLanguageEntry: LanguageKnowledge) => {
        const newLanguages = [...languages];
        
        if(isLanguageExisting(newLanguages, newLanguageEntry) == false){
            newLanguages.push(newLanguageEntry);
            setLanguages(newLanguages);
        }
        setIsAddingNewEntry(false);
    }

    function findLanguageEntry(newLanguages: LanguageKnowledge[], language: string): LanguageKnowledge {
        const changedEntry = newLanguages.find(eintrag => eintrag.languageName == language);
        if (changedEntry == null) {
            throw new Error(`Could not find languageEntry with id ${language}`);
        }
        return changedEntry;
    }

    function isLanguageExisting(newLanguages: LanguageKnowledge[], newLanguageEntry: LanguageKnowledge): boolean {
        const duplicateLanguage = (newLanguages.filter(eintrag => eintrag.languageName == newLanguageEntry.languageName)) ?? undefined;
        if (duplicateLanguage.length > 0) {
            throw new Error('A LanguageEntry for this sprache does already exist');
        }
        return false;
    }

    const noLanguageDataFallbackMessage = <p>Noch keine Spracheinträge angelegt</p>

    return (
        <>
            <section className="sprachen-section-container">
                <h2>Sprachen</h2>
                {languages.length > 0 ?
                    languages.map((languageEntry: LanguageKnowledge) => {
                        return (
                            <LanguageEntry
                                key={languageEntry.languageId}
                                languageEntry={languageEntry}
                                changeLanguageEntry={onChangeLanguageEntry}
                                changeLanguageLevel={onChangeLanguageLevel}
                                deleteLanguageEntry={onDeleteLanguageEntry}
                            ></LanguageEntry>
                        )
                    }) : noLanguageDataFallbackMessage}

                {isAddingNewEntry && <NewLanguageEntry onAddNewLanguageEntry={addNewLanguage}></NewLanguageEntry>}

                {isAddingNewEntry &&
                    <Button variant="contained" aria-label="clear" size="medium"
                        onClick={() => onEditNewLanguageEntry()}
                        color={'warning'}
                        startIcon={<ClearIcon />}>Clear
                    </Button>
                }

                {!isAddingNewEntry &&
                    <Button variant="contained" aria-label="add" size="medium"
                        onClick={() => onEditNewLanguageEntry()}
                        startIcon={<AddIcon />}>Add
                    </Button>
                }
            </section>
        </>
    );
}


