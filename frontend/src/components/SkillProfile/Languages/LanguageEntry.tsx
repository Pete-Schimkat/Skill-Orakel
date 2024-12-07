import DeleteIcon from '@mui/icons-material/Delete';
import { Autocomplete, TextField } from '@mui/material';
import IconButton from '@mui/material/IconButton';
import { LanguageKnowledge } from '../../../types/types';
import { LanguageKnowledgeBusinessObjectLanguageLevelEnum } from '../../../generated';

export const LanguageEntry = ({
    languageEntry,
    changeLanguageEntry,
    changeLanguageLevel,
    deleteLanguageEntry
}: {
    languageEntry: LanguageKnowledge,
    changeLanguageEntry: (id: string, event: React.ChangeEvent<HTMLInputElement>) => void,
    changeLanguageLevel: (languageName: string, value: LanguageKnowledgeBusinessObjectLanguageLevelEnum | null) => void,
    deleteLanguageEntry: (id: string) => void,
}) => {

    console.log(languageEntry);

    const languageLevelOptions = Object.values(LanguageKnowledgeBusinessObjectLanguageLevelEnum);

    return (
        <div className="sprachen-eintrag">
            <TextField
                InputLabelProps={{ shrink: true }}
                label={'Sprache'}
                disabled={false}
                variant="outlined"
                onChange={(event: React.ChangeEvent<HTMLInputElement>) => changeLanguageEntry(languageEntry.languageId, event)}
                value={languageEntry.languageName}
            >
                {' '}
            </TextField>
            <Autocomplete
                className="sprachen-level"
                id="LanguageLevel"
                renderInput={(params) => <TextField {...params} label={'LanguageLevel'} />}
                fullWidth={true}

                value={languageLevelOptions.find(option => option.toLowerCase() == languageEntry.languageLevel.toLowerCase())}
                options={languageLevelOptions}
                getOptionLabel={option => option.toLowerCase()}
            
                onChange={(_, value) => changeLanguageLevel(languageEntry.languageName, value)}
            ></Autocomplete>
            <IconButton className="sprachen-delete-button"
                aria-label="delete"
                onClick={() => deleteLanguageEntry(languageEntry.languageId)}
                >
                <DeleteIcon />
            </IconButton>
        </div>
    );
}