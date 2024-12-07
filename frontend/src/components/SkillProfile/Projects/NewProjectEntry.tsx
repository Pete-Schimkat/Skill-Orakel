import {
  Autocomplete,
  Chip,
  IconButton,
  TextField,
  Typography,
} from '@mui/material';
import { DatePicker } from '@mui/x-date-pickers';
import dayjs, { Dayjs } from 'dayjs';
import { useState } from 'react';
import DoneIcon from '@mui/icons-material/Done';
import { ProjectAssignment, ProjectType, Skill } from '../../../types/types';

export const NewProjectEntry = ({
  saveEntry,
  skillOptions,
  projectOptions, 
  id
}: {
  saveEntry: (Entry: ProjectAssignment) => void;
  skillOptions: Skill[];
  projectOptions: ProjectType[]; 
  id: string;
}) => {
  const [newEntry, setNewEntry] = useState<ProjectAssignment>({
    projectId: id,
    startDate: new Date(),
    endDate: new Date(),
    skills: [],
    name: '',
    description: '',
    responsibilities: '',
    customer: ''
  });

  const onChangeTitle = (value: string) => {
    setNewEntry((currentEntry) => {
      const changedEntry = { ...currentEntry };
      const projectAssignment = projectOptions.find(
        (element) => element.name === value
      );

      if (projectAssignment != null) {
        const projectId = projectAssignment.id;
        changedEntry.projectId = projectId;
      }
      return changedEntry;
    });
  };

  const onChangeStartDate = (event: dayjs.Dayjs | null) => {
    if (event) {
      const addedProjectEntry = { ...newEntry };
      addedProjectEntry.startDate = event.toDate();
      setNewEntry(addedProjectEntry);
    }
  };
  const onChangeEndDate = (event: dayjs.Dayjs | null) => {
    if (event) {
      const addedProjectEntry = { ...newEntry };
      addedProjectEntry.endDate = event.toDate();
      setNewEntry(addedProjectEntry);
    }
  };

  const onChangeSkills = (
    _event: React.ChangeEvent<object>,
    value: Skill[]
  ) => {
    console.log(value);
    const changedEntry = { ...newEntry };
    changedEntry.skills = value;
    setNewEntry(changedEntry);
  };

  return (
    <div className="project-Entry">
      <div id="firstProjectRow">
        <Autocomplete
          className="project-titel"
          id="projectAutocomplete"
          renderInput={(params) => (
            <TextField
              {...params}
              value={null}
              label={'Projekttitel'}
              id="projectTitleTextField"
            />
          )}
          onInputChange={(_event, value) => onChangeTitle(value)}
          options={projectOptions}
          getOptionLabel={(project: ProjectType) => project.name}
        ></Autocomplete>
      </div>

        <div className="project-date-section">    
            <DatePicker
              label={'Von'}
              onChange={(event: Dayjs | null) => onChangeStartDate(event)}
              value={dayjs(newEntry.startDate)}
            ></DatePicker>
            <DatePicker
              label={'Bis'}
              onChange={(event: Dayjs | null) => onChangeEndDate(event)}
              value={dayjs(newEntry.endDate)}
            ></DatePicker>


      </div>

      <div className="project-description-row">
          <TextField
            className="description-input-field"
            InputLabelProps={{ shrink: true }}
            label={'Beschreibung'}
            variant="outlined"
            value={newEntry.description}
            fullWidth={true}
          >
            {' '}
          </TextField>
        </div>


      <div id="secondProjectRow">
        <Autocomplete
          renderInput={(params) => <TextField {...params} label="Skills" />}
          options={skillOptions}
          getOptionLabel={(skill) => skill.name}
          multiple
          renderTags={(value, getTagProps) =>
            value.map((option, index) => (
              <Chip
                label={
                  <Typography style={{ whiteSpace: 'normal' }}>
                    {option.name}
                  </Typography>
                }
                {...getTagProps({ index })}
                style={{ height: '100%' }}
              />
            ))
          }
          id="projectSkillAutocomplete"
          fullWidth
          onChange={(event, value) => onChangeSkills(event, value)}
        ></Autocomplete>
      </div>
      <IconButton
        className="button-confirm"
        onClick={() => saveEntry(newEntry)}
      >
        <DoneIcon></DoneIcon>
      </IconButton>
    </div>
  );
};
