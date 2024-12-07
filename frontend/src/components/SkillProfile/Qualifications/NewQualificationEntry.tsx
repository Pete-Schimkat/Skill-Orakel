import { useState } from 'react';
import { TextField } from '@mui/material';
import { QualificationType } from './Qualifications';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { IconButton } from '@mui/material';
import DoneIcon from '@mui/icons-material/Done';
import dayjs from 'dayjs';

const PLACEHOLDER_UUID = 'e1a714e0-abba-4bed-9ebd-fe1afc223842';

export const NewQualificationEntry = ({
  addQualification,
}: {
  addQualification: (entry: QualificationType) => void;
}) => {
  const [newEntry, setNewEntry] = useState<QualificationType>({
    id: PLACEHOLDER_UUID,
    issuer: '',
    title: '',
    description: 'Description',
    date: new Date('01.01.2000'),
  });

  const onChangeIssuer = (
    event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>
  ) => {
    const newIssuer = { ...newEntry };
    newIssuer.issuer = event.target.value;
    setNewEntry(newIssuer);
  };

  const onChangeDescription = (
    event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>
  ) => {
    const newIssuer = { ...newEntry };
    newIssuer.description = event.target.value;
    setNewEntry(newIssuer);
  };

  const onChangeDate = (event: dayjs.Dayjs | null) => {
    const newIssuer = { ...newEntry };
    if (event) {
      newIssuer.date = event?.toDate();
    }
    setNewEntry(newIssuer);
  };

  return (
    <section className="qualification-element">
      <TextField
        className="qualification-input-field"
        InputLabelProps={{ shrink: true }}
        label={'Ausbilder/ Uni/ Fachhochschule'}
        disabled={false}
        variant="outlined"
        value={newEntry.issuer}
        onChange={(event) => onChangeIssuer(event)}
      >
        {' '}
      </TextField>
      <TextField
        className="qualification-input-field"
        InputLabelProps={{ shrink: true }}
        label={'Description'}
        disabled={false}
        variant="outlined"
        value={newEntry.description}
        onChange={(event) => onChangeDescription(event)}
      >
        {' '}
      </TextField>
      <DatePicker
        className="qualification-input-field"
        label={'Abschlussdatum'}
        disabled={false}
        onChange={(event) => onChangeDate(event)}
        value={dayjs(newEntry.date)}
      ></DatePicker>

      <IconButton
        className="button-confirm"
        onClick={() => addQualification(newEntry)}
      >
        <DoneIcon></DoneIcon>
      </IconButton>
    </section>
  );
};
