import DeleteIcon from '@mui/icons-material/Delete';
import {
  Autocomplete,
  Chip,
  IconButton,
  TextField,
  Typography,
} from '@mui/material';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import dayjs, { Dayjs } from 'dayjs';
import { SyntheticEvent } from 'react';
import {
  ProjectAssignment,
  ProjectType,
  Skill
} from '../../../types/types';

export const ProjectEntry = ({
  projectAssignment: projectAssignment,
  skillOptions,
  projectOptions, 
  onDelete,
  onChangeTitle,
  onChangeStartDate,
  onChangeEndDate,
  onChangeSkills,
}: {
  projectAssignment: ProjectAssignment;
  skillOptions: Skill[];
  projectOptions: ProjectType[]; 
  onDelete: (id: string) => void;
  onChangeTitle: (event: SyntheticEvent<Element, Event>, id: string) => void;
  onChangeStartDate: (event: dayjs.Dayjs | null, id: string) => void;
  onChangeEndDate: (event: dayjs.Dayjs | null, id: string) => void;
  onChangeSkills: (
    event: SyntheticEvent<Element, Event>,
    value: Skill[],
    id: string
  ) => void;
}) => {

  return (
    <>
      <section className="project-element">
        <div id="firstProjectRow">
          <Autocomplete
            renderInput={(params) => (<TextField {...params} label={'Projektname'} />)}
            options={projectOptions}

            onChange={(event) => onChangeTitle(event, projectAssignment.projectId)}

            value={projectOptions.find((project) => project.id == projectAssignment.projectId)}
            getOptionKey={(project) => project.id}
            getOptionLabel={(project) => project.name}
            isOptionEqualToValue={(option, value) => option.id == value.id}

            fullWidth={true}
            id="projectAutocomplete"
          ></Autocomplete>
        </div>

        <div className="project-date-section">
          <DatePicker
            label={'Von'}
            disabled={false}
            value={dayjs(projectAssignment.startDate)}
            onChange={(event: Dayjs | null) =>
              onChangeStartDate(event, projectAssignment.projectId)
            }
          ></DatePicker>
          <DatePicker
            label={'Bis'}
            disabled={false}
            value={dayjs(projectAssignment.endDate)}
            onChange={(event: Dayjs | null) =>
              onChangeEndDate(event, projectAssignment.projectId)
            }
          ></DatePicker>
        </div>

        <div className="project-description-row">
          <TextField
            className="description-input-field"
            InputLabelProps={{ shrink: true }}
            label={'Beschreibung'}
            variant="outlined"
            value={projectAssignment.description}
            fullWidth
            disabled
          >
            {' '}
          </TextField>
        </div>

        <div id="secondProjectRow">
          <Autocomplete
            renderInput={(params) => <TextField {...params} label="Skills" />}
            options={skillOptions}
            getOptionLabel={(skill) => skill.name}
            multiple={true}
            renderTags={(value, getTagProps) =>
              value.map((option, index) => (
                <Chip
                  label={
                    <Typography style={{ whiteSpace: 'normal' }}>
                      {option.name}
                    </Typography>
                  }
                  {...getTagProps({ index })}
                  key={option.id}
                  style={{ height: '100%' }}
                />
              ))
            }
            id="skillAutocomplete"
            fullWidth={true}

            onChange={(event, value) =>
              onChangeSkills(event, value, projectAssignment.projectId)
            }
            value={projectAssignment.skills}
          ></Autocomplete>
        </div>

      </section>
      <IconButton
        className="projekt-delete-button"
        aria-label="delete"
        onClick={() => onDelete(projectAssignment.projectId)}
      >
        <DeleteIcon />
      </IconButton>
    </>
  );
};
