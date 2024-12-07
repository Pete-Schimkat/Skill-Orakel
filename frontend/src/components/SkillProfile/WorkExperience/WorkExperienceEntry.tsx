import { TextField } from '@mui/material';
import { DatePicker } from '@mui/x-date-pickers';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import dayjs, { Dayjs } from 'dayjs';
import { WorkExperienceType } from '../../../types/types';

export const WorkExperienceEntry = ({
  workExperienceEntry,
  deleteEntry,
  changeEmployer,
  changeDescription,
  startDateChange,
  endDateChange,
}: {
  workExperienceEntry: WorkExperienceType;
  deleteEntry: (id: string) => void;
  changeEmployer: (
    event: React.ChangeEvent<HTMLInputElement>,
    id: string
  ) => void;
  changeDescription: (
    event: React.ChangeEvent<HTMLInputElement>,
    id: string
  ) => void;
  startDateChange: (event: dayjs.Dayjs | null, id: string) => void;
  endDateChange: (event: dayjs.Dayjs | null, id: string) => void;
}) => {
  return (
    <section className="beruflicher-werdegang-eintrag">
      <TextField
        className="werdegang-input-field"
        label="Arbeitgeber"
        InputLabelProps={{ shrink: true }}
        disabled={false}
        variant="outlined"
        value={workExperienceEntry.employer}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) =>
          changeEmployer(event, workExperienceEntry.id)
        }
      >
        {''}
      </TextField>
      <TextField
        className="werdegang-input-field"
        label="Description"
        InputLabelProps={{ shrink: true }}
        disabled={false}
        variant="outlined"
        value={workExperienceEntry.description}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) =>
          changeDescription(event, workExperienceEntry.id)
        }
      >
        {''}
      </TextField>
      <DatePicker
        className="werdegang-input-field"
        label={'Von'}
        disabled={false}
        value={dayjs(workExperienceEntry.startDate)}
        onChange={(event: Dayjs | null) =>
          startDateChange(event, workExperienceEntry.id)
        }
      ></DatePicker>
      <DatePicker
        className="werdegang-input-field"
        label={'Bis'}
        disabled={false}
        value={dayjs(workExperienceEntry.endDate)}
        onChange={(event: Dayjs | null) =>
          endDateChange(event, workExperienceEntry.id)
        }
      ></DatePicker>
      <IconButton
        className="sprachen-delete-button"
        aria-label="delete"
        onClick={() => deleteEntry(workExperienceEntry.id)}
      >
        <DeleteIcon />
      </IconButton>
    </section>
  );
};
