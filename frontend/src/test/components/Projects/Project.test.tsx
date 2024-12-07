// import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
// import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
// import { cleanup, render, screen } from '@testing-library/react';
// import { beforeEach, describe, expect, it } from 'vitest';
// import { Projects } from '../../../components/SkillProfile/Projects/Projects';
// import { ProjectAssignment } from '../../../types/types';
// import userEvent from '@testing-library/user-event';

// describe('test project component', () => {
//   const testData: ProjectAssignment[] = [
//     {
//       projectId: '62321',
//       name: 'testProjekt',
//       description: 'dieses Projekt dient nur zum Testen',
//       responsibilities: 'gute Komponententests schreiben',
//       startDate: new Date('01.21.2000'),
//       endDate: new Date('01.21.2020'),
//       skills: [],
//     },
//   ];

//   beforeEach(() => {
//     cleanup();
//   });

//   it('should delete a project assignment if the delete event has been invoked', async () => {
//     render(
//       <LocalizationProvider dateAdapter={AdapterDayjs}>
//         <Projects fetchedProjects={testData} />
//       </LocalizationProvider>
//     );
//   });
// });
