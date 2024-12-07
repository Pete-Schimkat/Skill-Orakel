import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { describe, expect, test, vi } from 'vitest';
import { WorkExperience } from '../../../components/SkillProfile/WorkExperience/WorkExperience';
import { WorkExperienceType } from '../../../types/types';

describe('test beruflicher werdegang componente', () => {

    const workExperiences = [{
        id: '12',
        employer: 'Test Corporation',
        description: 'Head Of Testing',
        startDate: new Date('01-01-2000'),
        endDate: new Date('12-31-2003')
    }];
    const mockFunction: React.Dispatch<React.SetStateAction<WorkExperienceType[]>> = vi.fn();


    render(
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <WorkExperience workExperiences={workExperiences} setWorkExperiences={mockFunction} ></WorkExperience>
        </LocalizationProvider>
    );

    test('Add button is rendered', () => {
        const buttons = screen.getAllByRole('button');
        const addButton = buttons.find((button) => button.ariaLabel === 'add');
        expect(addButton).toBeInTheDocument();
    })

    test('renders textfield with display value Test Corporation', () => {
        const textfield = screen.getByDisplayValue('Test Corporation');
        expect(textfield).toBeInTheDocument();
    });

    test('when user clicks delete button, then textfield arbeitgeber is not rendered anymore', async () => {
        const user = userEvent.setup();
        const textfield = screen.getByDisplayValue('Test Corporation');

        if(!textfield){
            throw 'Textfield should exist!';
        }

        const buttons = screen.getAllByRole('button');
        const deleteButton = buttons.filter(button => button.ariaLabel === 'delete');
        await user.click(deleteButton[0]);
        expect(textfield).not.toBeInTheDocument();
        
    });

    test('when no data is available no data fallback text gets rendered', async () => {
        const fallbackParagraph = screen.getByText('Hier könnte Ihr beruflicher Werdegang stehen.');
        expect(fallbackParagraph).toBeInTheDocument();
    });

    // test("when add button is clicked, new beruflicher Werdegang is rendered and has default values for arbeitgeber textfield", async () => {
    //     // SETUP
    //     const user = userEvent.setup();

    //     const buttons = screen.getAllByRole("button");
    //     const addButton = buttons.find((button) => button.ariaLabel === "add");

    //     // ACT
    //     if(!addButton){
    //         throw "Problem: Add Button not found!"
    //     }
    //     await user.click(addButton);
    //     // ASSERT

    //     const textfield = screen.getAllByRole("textbox");
    //     const inputField = await screen.findByLabelText("Arbeitgeber");
    //     expect(inputField).toBeInTheDocument();
    // })
})