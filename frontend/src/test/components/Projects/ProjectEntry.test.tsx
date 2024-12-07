import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { cleanup, render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import { ProjectEntry } from '../../../components/SkillProfile/Projects/ProjectEntry';
import { ProjectAssignment } from '../../../types/types';

describe('test project component', () => {
  const testData: ProjectAssignment = {
    projectId: '62321',
    name: 'testProjekt',
    description: 'dieses Projekt dient nur zum Testen',
    responsibilities: 'gute Komponententests schreiben',
    startDate: new Date('01.21.2000'),
    endDate: new Date('01.21.2020'),
    skills: [],
    customer: '',
  };
  beforeEach(() => {
    cleanup();
  });
  it('executes delete function when user clicks on delete button', async () => {
    const user = userEvent.setup();
    const deleteFnMock = vi.fn();

    render(
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <ProjectEntry
          onDelete={deleteFnMock}
          projectAssignment={testData}
          onChangeTitle={vi.fn()}
          onChangeStartDate={vi.fn()}
          onChangeEndDate={vi.fn()}
          onChangeSkills={vi.fn()}
          skillOptions={[]}
          projectOptions={[]}
        ></ProjectEntry>
      </LocalizationProvider>
    );

    const allButtons = await screen.findAllByRole('button');
    const deleteButton = await allButtons.find(
      (button) => button.ariaLabel == 'delete'
    );
    // expect(deleteButton).toBeInTheDocument();

    if (deleteButton == null) {
      throw new Error('Delete button could not be found for Project Entry');
    }

    await user.click(deleteButton);
    expect(deleteFnMock).toBeCalledTimes(1);
  });

  it('renders a textfield with the value of testProjekt', () => {
    render(
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <ProjectEntry
          onDelete={vi.fn()}
          projectAssignment={testData}
          onChangeTitle={vi.fn()}
          onChangeStartDate={vi.fn()}
          onChangeEndDate={vi.fn()}
          onChangeSkills={vi.fn()}
          skillOptions={[]}
          projectOptions={[]}
        ></ProjectEntry>
      </LocalizationProvider>
    );
    const projektTitelTextField = screen.getByDisplayValue('testProjekt');
    expect(projektTitelTextField).toBeInTheDocument();
  });

  it("renders a textfield with the value 'dieses Projekt dient nur zum Testen'", () => {
    render(
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <ProjectEntry
          onDelete={vi.fn()}
          projectAssignment={testData}
          onChangeTitle={vi.fn()}
          onChangeStartDate={vi.fn()}
          onChangeEndDate={vi.fn()}
          onChangeSkills={vi.fn()}
          skillOptions={[]}
          projectOptions={[]}
        ></ProjectEntry>
      </LocalizationProvider>
    );
    const projectDescriptionField = screen.getByDisplayValue(
      'dieses Projekt dient nur zum Testen'
    );
    expect(projectDescriptionField).toBeInTheDocument();
  });

  it('renders startDateField with correct value', () => {
    render(
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <ProjectEntry
          onDelete={vi.fn()}
          projectAssignment={testData}
          onChangeTitle={vi.fn()}
          onChangeStartDate={vi.fn()}
          onChangeEndDate={vi.fn()}
          onChangeSkills={vi.fn()}
          skillOptions={[]}
          projectOptions={[]}
        ></ProjectEntry>
      </LocalizationProvider>
    );
    const datePicker = screen.getByLabelText('Von');
    expect(datePicker).toHaveDisplayValue('01/21/2000');
  });

  it('renders endDateField with correct value', () => {
    render(
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <ProjectEntry
          onDelete={vi.fn()}
          projectAssignment={testData}
          onChangeTitle={vi.fn()}
          onChangeStartDate={vi.fn()}
          onChangeEndDate={vi.fn()}
          onChangeSkills={vi.fn()}
          skillOptions={[]}
          projectOptions={[]}
        ></ProjectEntry>
      </LocalizationProvider>
    );
    const datePicker = screen.getByLabelText('Bis');
    expect(datePicker).toHaveDisplayValue('01/21/2020');
  });

  it('renders delete button', async () => {
    render(
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <ProjectEntry
          onDelete={vi.fn()}
          projectAssignment={testData}
          onChangeTitle={vi.fn()}
          onChangeStartDate={vi.fn()}
          onChangeEndDate={vi.fn()}
          onChangeSkills={vi.fn()}
          skillOptions={[]}
          projectOptions={[]}
        ></ProjectEntry>
      </LocalizationProvider>
    );
    const allButtons = await screen.findAllByRole('button');
    const deleteButton = await allButtons.find(
      (button) => button.ariaLabel == 'delete'
    );
    expect(deleteButton).toBeInTheDocument();
  });
  it('does not render textfield when user clicks on delete', async () => {
    const user = userEvent.setup();

    const onDelete = vi.fn();

    render(
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <ProjectEntry
          onDelete={onDelete}
          projectAssignment={testData}
          onChangeTitle={vi.fn()}
          onChangeStartDate={vi.fn()}
          onChangeEndDate={vi.fn()}
          onChangeSkills={vi.fn()}
          skillOptions={[]}
          projectOptions={[]}
        ></ProjectEntry>
      </LocalizationProvider>
    );

    const deleteButton = screen
      .getAllByRole('button')
      .find((button) => button.ariaLabel == 'delete');
    if (deleteButton == null) {
      throw new Error('Could not find delete button');
    }
    await user.click(deleteButton);

    expect(onDelete).toHaveBeenCalled();
  });
});
