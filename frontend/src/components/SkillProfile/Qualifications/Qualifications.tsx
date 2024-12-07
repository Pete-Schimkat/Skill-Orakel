import AddIcon from '@mui/icons-material/Add';
import ClearIcon from '@mui/icons-material/Clear';
import { Button } from '@mui/material';
import { Dayjs } from 'dayjs';
import { useState } from 'react';
import { NewQualificationEntry } from './NewQualificationEntry';
import { QualificationEntry } from './QualificationEntry';

export type QualificationType = {
  id: string;
  issuer: string;
  title: string; 
  description: string;
  date: Date;
};

export const Qualifications = ({
  qualifications,
  setQualifictions
}: {
  qualifications: QualificationType[];
  setQualifictions: React.Dispatch<React.SetStateAction<QualificationType[]>>
}) => {

  const deleteEntry = (id: string) => {
    let newQualifications = [...qualifications];
    newQualifications = newQualifications.filter(
      (qualification) => qualification.id != id
    );
    setQualifictions(newQualifications);
  };

  const changeIssuer = (
    event: React.ChangeEvent<HTMLInputElement>,
    id: string
  ) => {
    const newQualifications = [...qualifications];
    const changedQualification = findQualificationsEntry(newQualifications, id);
    changedQualification.issuer = event.target.value;
    setQualifictions(newQualifications);
  };

  const changeDescription = (
    event: React.ChangeEvent<HTMLInputElement>,
    id: string
  ) => {
    const newQualifications = [...qualifications];
    const changedQualification = findQualificationsEntry(newQualifications, id);
    changedQualification.description = event.target.value;
    setQualifictions(newQualifications);
  };

  const changeDate = (event: Dayjs | null, id: string) => {
    const newQualifications = [...qualifications];
    if (event == null) {
      throw new Error('Unable to find date parameter');
    }
    const changedQualification = findQualificationsEntry(newQualifications, id);
    changedQualification.date = event.toDate();
    setQualifictions(newQualifications);
  };

  const addNewQualification = (qualification: QualificationType) => {
    const currentQualifications = [...qualifications];
    if (
      isDuplicatequalificationstry(currentQualifications, qualification) ==
      false
    ) {
      currentQualifications.push(qualification);
      setQualifictions(currentQualifications);
    }
    setIsAddingNewQualification(false);
  };

  const [isAddingNewQualification, setIsAddingNewQualification] =
    useState<boolean>(false);

  const noqualificationDataFallbackMessage = (
    <p>Noch keine Qualifikationen angelegt.</p>
  );

  return (
    <section className="qualifications-section">
      <h2>Qualifikationen</h2>
      {qualifications.length > 0
        ? qualifications?.map((qualification) => {
            return (
              <QualificationEntry
                key={qualification.id}
                qualification={qualification}
                onDelete={deleteEntry}
                onChangeIssuer={changeIssuer}
                onChangeDescription={changeDescription}
                onChangeDate={changeDate}
              ></QualificationEntry>
            );
          })
        : noqualificationDataFallbackMessage}

      {isAddingNewQualification ? (
        <>
          <NewQualificationEntry
            addQualification={addNewQualification}
          ></NewQualificationEntry>

          <Button
            variant="contained"
            aria-label="clear"
            size="medium"
            color={'warning'}
            onClick={() => setIsAddingNewQualification((state) => !state)}
            startIcon={<ClearIcon />}
          >
            Clear
          </Button>
        </>
      ) : (
        <Button
          variant="contained"
          aria-label="add"
          size="medium"
          onClick={() => setIsAddingNewQualification((state) => !state)}
          startIcon={<AddIcon />}
        >
          Add
        </Button>
      )}
    </section>
  );
};

function findQualificationsEntry(
  newQualifications: QualificationType[],
  id: string
) {
  const changedQualification = newQualifications.find(
    (qualification) => qualification.id === id
  );
  if (changedQualification == null) {
    throw new Error(`Unable to find qualification entry with id ${id}`);
  }
  return changedQualification;
}

function isDuplicatequalificationstry(
  qualifications: QualificationType[],
  entry: QualificationType
): boolean {
  const qualificationExists = qualifications.some(
    (existingEntry) => existingEntry.issuer == entry.issuer
  );
  return qualificationExists;
}
