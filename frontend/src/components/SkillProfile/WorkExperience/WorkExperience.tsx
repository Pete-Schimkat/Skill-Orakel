import AddIcon from '@mui/icons-material/Add';
import ClearIcon from '@mui/icons-material/Clear';
import { Button } from '@mui/material';
import { Dayjs } from 'dayjs';
import React, { useState } from 'react';
import { WorkExperienceType } from '../../../types/types';
import { NewWorkExperienceEntry } from './NewWorkExperienceEntry';
import { WorkExperienceEntry } from './WorkExperienceEntry';

const mockId = '1';

export const WorkExperience = ({
  workExperiences,
  setWorkExperiences
}: {
  workExperiences: WorkExperienceType[];
  setWorkExperiences: React.Dispatch<React.SetStateAction<WorkExperienceType[]>>
}) => {
  const noDataFallback = <p>Hier könnte Ihr beruflicher Werdegang stehen.</p>;

  const [isAddingNewWorkExperienceEntry, setIsAddingNewWorkExperienceData] =
    useState<boolean>(false);

  const handleAddingClick = () => {
    setIsAddingNewWorkExperienceData((prevState) => !prevState);
  };

  const onEmployerChange = (
    event: React.ChangeEvent<HTMLInputElement>,
    id: string
  ) => {
    const changedWorkExperienceData = [...workExperiences];
    const currentEntry = findWorkExperienceEntry(changedWorkExperienceData, id);
    currentEntry.employer = event?.target.value;
    setWorkExperiences(changedWorkExperienceData);
  };

  const onDescriptionChange = (
    event: React.ChangeEvent<HTMLInputElement>,
    id: string
  ) => {
    const changedWorkExperienceData = [...workExperiences];
    const currentEntry = findWorkExperienceEntry(changedWorkExperienceData, id);
    currentEntry.description = event?.target.value;
    setWorkExperiences(changedWorkExperienceData);
  };

  const onStartDateChange = (event: Dayjs | null, id: string) => {
    const changedWorkExperienceData = [...workExperiences];
    if (event == null) {
      throw new Error('Could not resolve event to a date');
    }
    const currentEntry = findWorkExperienceEntry(changedWorkExperienceData, id);
    currentEntry.startDate = event.toDate();
    setWorkExperiences(changedWorkExperienceData);
  };

  const onEndDateChange = (event: Dayjs | null, id: string) => {
    const changedWorkExperienceData = [...workExperiences];
    if (event == null) {
      throw new Error('Could not resolve event to a date');
    }
    const currentEntry = findWorkExperienceEntry(changedWorkExperienceData, id);
    currentEntry.endDate = event.toDate();
    setWorkExperiences(changedWorkExperienceData);
  };

  const onDelete = (id: string) => {
    let changedWorkExperienceData = [...workExperiences];
    changedWorkExperienceData = changedWorkExperienceData.filter(
      (werdegang) => werdegang.id != id
    );
    setWorkExperiences(changedWorkExperienceData);
  };

  const onSave = (workExperienceEntry: WorkExperienceType) => {
    const changedWorkExperienceData = [...workExperiences];
    changedWorkExperienceData.push(workExperienceEntry);
    setWorkExperiences(changedWorkExperienceData);
    setIsAddingNewWorkExperienceData((prevState) => !prevState);
  };

  return (
    <section className="beruflicher-werdegang-section">
      <h2>Beruflicher Werdegang</h2>
      {workExperiences.length > 0
        ? workExperiences.map((workExperienceEntry) => {
            return (
              <WorkExperienceEntry
                workExperienceEntry={workExperienceEntry}
                changeEmployer={onEmployerChange}
                changeDescription={onDescriptionChange}
                startDateChange={onStartDateChange}
                endDateChange={onEndDateChange}
                deleteEntry={onDelete}
                key={workExperienceEntry.id}
              ></WorkExperienceEntry>
            );
          })
        : noDataFallback}

      {isAddingNewWorkExperienceEntry && (
        <NewWorkExperienceEntry
          saveEntry={onSave}
          id={mockId}
        ></NewWorkExperienceEntry>
      )}

      {isAddingNewWorkExperienceEntry ? (
        <Button
          variant="contained"
          aria-label="clear"
          size="medium"
          className="button-clear"
          onClick={() => handleAddingClick()}
          color={'warning'}
          startIcon={<ClearIcon />}
        >
          Clear
        </Button>
      ) : (
        <Button
          variant="contained"
          aria-label="add"
          size="medium"
          onClick={() => handleAddingClick()}
          startIcon={<AddIcon />}
          className="button-add"
        >
          Add
        </Button>
      )}
    </section>
  );
};

function findWorkExperienceEntry(
  changedWorkExperienceData: WorkExperienceType[],
  id: string
): WorkExperienceType {
  const currentEntry = changedWorkExperienceData.find(
    (eintrag) => eintrag.id === id
  );
  if (currentEntry == null) {
    throw new Error(`Could not find workExperienceEntry mit id ${id} `);
  }
  return currentEntry;
}
