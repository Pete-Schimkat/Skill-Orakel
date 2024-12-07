/* eslint-disable @typescript-eslint/no-unused-vars */
import AddIcon from '@mui/icons-material/Add';
import ClearIcon from '@mui/icons-material/Clear';
import { Button } from '@mui/material';
import { Dayjs } from 'dayjs';
import { SyntheticEvent, useEffect, useState } from 'react';
import { NewProjectEntry } from './NewProjectEntry';

import { ProjectAssignment, ProjectType, Skill } from '../../../types/types';
import { ProjectEntry } from './ProjectEntry';
import { SkillApi } from '../../../api/skillApi';
import { ProjectApi } from '../../../api/projectApi';

const PLACEHOLDER_ID = '73c7a4ac-6469-42c7-873c-4d650d9aaaa2';

export const Projects = ({
  projectAssignments,
  setProjectAssignments,
}: {
  projectAssignments: ProjectAssignment[];
  setProjectAssignments: React.Dispatch<
    React.SetStateAction<ProjectAssignment[]>
  >;
}) => {
  const [isAddingNewEntry, setIsAddingNewEntry] = useState<boolean>(false);

  const noEntryFallbackMessage = <p>Noch keine Projekteinträge angelegt.</p>;

  const [skillOptions, setSkillOptions] = useState<Skill[]>([]);

  const [projectOptions, setProjectOptions] = useState<ProjectType[]>([]);

  useEffect(() => {
    const fetchSkillOptions = async () => {
      const skills = await SkillApi.getSkills();
      setSkillOptions(skills);
    };
    const fetchProjectOptions = async () => {
      const projects = await ProjectApi.getAllProjects();
      setProjectOptions(projects);
    };

    void fetchSkillOptions();
    void fetchProjectOptions();
  }, []);

  /* react to fetched data from parent component */

  const handleAddingClick = () => {
    setIsAddingNewEntry((prevState) => !prevState);
  };

  const onTitleChange = (
    event: SyntheticEvent<Element, Event>,
    _id: string
  ) => {
    console.log(event.target);
  };
  const onDeleteProjectEntry = (id: string) => {
    let newProjectAssignments: ProjectAssignment[] = [...projectAssignments];
    newProjectAssignments = newProjectAssignments.filter(
      (_project: ProjectAssignment) => _project.projectId != id
    );
    setProjectAssignments(newProjectAssignments);
  };

  //TODO: Irgendwie an eine Profil-ID kommen, um die Zugehörigkeiten ordentlich zuzuordnen
  const changeStartDate = (event: Dayjs | null, id: string) => {
    const newProjectAssignments: ProjectAssignment[] = [...projectAssignments];
    if (event) {
      const currentEntry = newProjectAssignments.find(
        (entry) => entry.projectId === id
      );
      if (currentEntry) {
        currentEntry.startDate = event.toDate();
      } else {
        console.log('Error: entry for changing startDate not found');
      }
    }
    setProjectAssignments(newProjectAssignments);
  };
  const changeEndDate = (event: Dayjs | null, id: string) => {
    const newProjectAssignments: ProjectAssignment[] = [...projectAssignments];
    if (event) {
      const currentEntry = newProjectAssignments.find(
        (entry) => entry.projectId === id
      );
      if (currentEntry) {
        currentEntry.endDate = event.toDate();
      } else {
        console.log('Error: entry for changing endDate not found');
      }
    }
    setProjectAssignments(newProjectAssignments);
  };

  const onSaveEntry = (NewProjectEntry: ProjectAssignment) => {
    const newProjectAssignments = [...projectAssignments, NewProjectEntry];
    setProjectAssignments(newProjectAssignments);
    setIsAddingNewEntry((prevState) => !prevState);
  };

  const onChangeSkills = (
    event: SyntheticEvent<Element, Event>,
    value: Skill[],
    id: string
  ) => {
    const newProjectAssignments: ProjectAssignment[] = [...projectAssignments];
    if (event) {
      const currentEntry = newProjectAssignments.find(
        (entry) => entry.projectId === id
      );
      if (currentEntry) {
        currentEntry.skills = value;
      } else {
        console.log('Error: entry for changing SkillData not found');
      }
    }
    setProjectAssignments(newProjectAssignments);
  };

  return (
    <section>
      <h2>Projekte</h2>
      {projectAssignments.length > 0
        ? projectAssignments.map((newProjectAssignment: ProjectAssignment) => {
            return (
              <ProjectEntry
                projectOptions={projectOptions}
                skillOptions={skillOptions}
                key={newProjectAssignment.projectId}
                projectAssignment={newProjectAssignment}
                onDelete={onDeleteProjectEntry}
                onChangeTitle={onTitleChange}
                onChangeStartDate={changeStartDate}
                onChangeEndDate={changeEndDate}
                onChangeSkills={onChangeSkills}
              ></ProjectEntry>
            );
          })
        : noEntryFallbackMessage}
      {isAddingNewEntry && (
        <NewProjectEntry
          saveEntry={onSaveEntry}
          projectOptions={projectOptions}
          skillOptions={skillOptions}
          id={PLACEHOLDER_ID}
        ></NewProjectEntry>
      )}
      {isAddingNewEntry ? (
        <Button
          variant="contained"
          aria-label="clear"
          size="medium"
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
        >
          Add
        </Button>
      )}
    </section>
  );
};
