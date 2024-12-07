import DoneIcon from '@mui/icons-material/Done';
import { IconButton, TextField } from '@mui/material';
import { DatePicker } from '@mui/x-date-pickers';
import dayjs, { Dayjs } from 'dayjs';
import { useState } from 'react';
import { WorkExperienceType } from '../../../types/types';

export const NewWorkExperienceEntry = ({
  saveEntry,
  id,
}: {
  saveEntry: (entry: WorkExperienceType) => void;
  id: string;
}) => {
  const [newEntry, setNewEntry] = useState<WorkExperienceType>({
    id: id,
    employer: '',
    description: '',
    startDate: new Date(),
    endDate: new Date(),
  });

  const onChangeArbeitgeber = (event: React.ChangeEvent<HTMLInputElement>) => {
    const changedEntry = { ...newEntry };
    changedEntry.employer = event?.target.value;
    setNewEntry(changedEntry);
  };

  const onChangeDescription = (event: React.ChangeEvent<HTMLInputElement>) => {
    const changedEntry = { ...newEntry };
    changedEntry.description = event?.target.value;
    setNewEntry(changedEntry);
  };

  const onChangeStartDate = (event: Dayjs | null) => {
    const changedEntry = { ...newEntry };
    if (event) {
      changedEntry.startDate = event?.toDate();
    }
    setNewEntry(changedEntry);
  };

  const onChangeEndDate = (event: Dayjs | null) => {
    const changedEntry = { ...newEntry };
    if (event) {
      changedEntry.endDate = event?.toDate();
    }
    setNewEntry(changedEntry);
  };

  return (
    <section className="beruflicher-werdegang-eintrag">
      <TextField
        className="werdegang-input-field"
        label="Arbeitgeber"
        InputLabelProps={{ shrink: true }}
        disabled={false}
        variant="outlined"
        value={newEntry.employer}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) =>
          onChangeArbeitgeber(event)
        }
        required={true}
      >
        {''}
      </TextField>
      <TextField
        className="werdegang-input-field"
        label="Description"
        InputLabelProps={{ shrink: true }}
        disabled={false}
        variant="outlined"
        value={newEntry.description}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) =>
          onChangeDescription(event)
        }
        required={true}
      >
        {''}
      </TextField>
      <DatePicker
        className="werdegang-input-field"
        label={'Von'}
        disabled={false}
        value={dayjs(newEntry.startDate)}
        onChange={(event: Dayjs | null) => onChangeStartDate(event)}
      ></DatePicker>
      <DatePicker
        className="werdegang-input-field"
        label={'Bis'}
        disabled={false}
        value={dayjs(newEntry.endDate)}
        onChange={(event: Dayjs | null) => onChangeEndDate(event)}
      ></DatePicker>
      <IconButton
        className="button-confirm"
        onClick={() => saveEntry(newEntry)}
      >
        <DoneIcon></DoneIcon>
      </IconButton>
    </section>
  );
};
