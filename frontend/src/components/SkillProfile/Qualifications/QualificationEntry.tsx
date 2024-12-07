import DeleteIcon from '@mui/icons-material/Delete';
import { IconButton, TextField } from '@mui/material';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { QualificationType } from './Qualifications';
import dayjs from 'dayjs';

export const QualificationEntry = ({
  qualification,
  onDelete,
  onChangeIssuer,
  onChangeDescription,
  onChangeDate,
}: {
  qualification: QualificationType;
  onDelete: (id: string) => void;
  onChangeIssuer: (event: React.ChangeEvent<HTMLInputElement>, id: string) => void;
  onChangeDescription: (event: React.ChangeEvent<HTMLInputElement>, id: string) => void;
  onChangeDate: (event: dayjs.Dayjs | null, id: string) => void;
}) => {
  return (
    <section className="qualification-element">
      <TextField
        className="qualification-input-field"
        InputLabelProps={{ shrink: true }}
        label={'Ausbilder/ Uni/ Fachhochschule'}
        disabled={false}
        variant="outlined"
        value={qualification.issuer}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => onChangeIssuer(event, qualification.id)}
      >
        {' '}
      </TextField>
      <TextField
        className="qualification-input-field"
        InputLabelProps={{ shrink: true }}
        label={'Description'}
        disabled={false}
        variant="outlined"
        value={qualification.description}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => onChangeDescription(event, qualification.id)}
      >
        {' '}
      </TextField>
      <DatePicker
        className="qualification-input-field"
        label={'Abschlussdatum'}
        disabled={false}
        onChange={(event) => onChangeDate(event, qualification.id)}
        value={dayjs(qualification.date)}
      ></DatePicker>

      <IconButton
        className="button-confirm"
        aria-label="delete"
        onClick={() => onDelete(qualification.id)}
      >
        <DeleteIcon></DeleteIcon>
      </IconButton>
    </section>
  );
};
