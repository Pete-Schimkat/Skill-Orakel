import DoneIcon from '@mui/icons-material/Done';
import { Autocomplete, TextField } from '@mui/material';
import IconButton from '@mui/material/IconButton';
import { useState } from 'react';
import { LanguageKnowledge, LanguageLevel } from '../../../types/types';
import { LanguageKnowledgeBusinessObjectLanguageLevelEnum } from '../../../generated';


const PLACEHOLDER_ID = '73c7a4ac-6469-42c7-873c-4d650d9aaaa2';
const languageLevelOptions = Object.values(LanguageKnowledgeBusinessObjectLanguageLevelEnum);

export const NewLanguageEntry = ({onAddNewLanguageEntry}: {onAddNewLanguageEntry: (newLanguageEntry: LanguageKnowledge) => void}) => {

    const [newLanguageEntry, setNewLanguageEntry] = useState<LanguageKnowledge>(
        {
            languageName: 'Orkisch', 
            languageId: PLACEHOLDER_ID,
            languageLevel: LanguageKnowledgeBusinessObjectLanguageLevelEnum.Erstsprache
        }
    );

    const onChangeNewLanguageEntry = (event: React.ChangeEvent<HTMLInputElement>) => {
        const addedLanguageEntry = { ...newLanguageEntry };
        addedLanguageEntry.languageName = event.target.value;
        setNewLanguageEntry(addedLanguageEntry);
    }

    const onChangeNewLanguageEntryLevel = (value: string | LanguageLevel | null) => {
        const addedLanguageEntry = { ...newLanguageEntry };
        const level = languageLevelOptions.find((element) => element == value);
        
        if(level != null){
            addedLanguageEntry.languageLevel = level;
        }

        setNewLanguageEntry(addedLanguageEntry);
    }

    return (
        <div className="sprachen-eintrag">
            <TextField
                InputLabelProps={{ shrink: true }}
                label={'Sprache'}
                disabled={false}
                variant="outlined"
                onChange={(event: React.ChangeEvent<HTMLInputElement>) => onChangeNewLanguageEntry(event)}
                value={newLanguageEntry.languageName}
            >
                {' '}
            </TextField>
            <Autocomplete
                className="sprachen-level"
                id="added-sprachlevel"
                renderInput={(params) => <TextField {...params} value={newLanguageEntry.languageLevel} label={'Sprachlevel'} />}
                onChange={(_event, value) => onChangeNewLanguageEntryLevel(value)}
                options={languageLevelOptions}
                fullWidth={true}
            ></Autocomplete>

            <IconButton
                className="sprachen-add-button"
                onClick={() => onAddNewLanguageEntry(newLanguageEntry)}>
                <DoneIcon></DoneIcon>
            </IconButton>
        </div>
    );
}