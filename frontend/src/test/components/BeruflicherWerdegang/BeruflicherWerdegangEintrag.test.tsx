import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs/AdapterDayjs';
import { render, screen } from '@testing-library/react';
import { userEvent } from '@testing-library/user-event';
import { describe, expect, test, vi } from 'vitest';
import { WorkExperienceEntry } from '../../../components/SkillProfile/WorkExperience/WorkExperienceEntry';

const testData = {
    id: '12',
    employer: 'Test Corporation',
    description: 'Head Of Testing',
    startDate: new Date('01-01-2000'),
    endDate: new Date('12-31-2003')
}

const fakeFunctionProp = () => null;

describe('test component beruflicherWerdegangEintrag', () => {

    const mockDeleteFunction = vi.fn();
    const mockWriteIntoArbeitgeberInputFieldFunction = vi.fn();
    const mockWriteIntoDescriptionInputFieldFunction = vi.fn();

    render(
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <WorkExperienceEntry
                workExperienceEntry={testData}
                changeEmployer={mockWriteIntoArbeitgeberInputFieldFunction}
                changeDescription={mockWriteIntoDescriptionInputFieldFunction}
                startDateChange={fakeFunctionProp}
                endDateChange={fakeFunctionProp}
                deleteEntry={() => mockDeleteFunction()}
            ></WorkExperienceEntry>
        </LocalizationProvider>)

    test('renders textfield Arbeitgeber with value Test Corporation', () => {
        const label = screen.getByLabelText('Arbeitgeber');
        expect(label).toHaveDisplayValue('Test Corporation');
    });

    test('renders textfield Description with value Head of Testing', () => {
        const label = screen.getByLabelText('Description');
        expect(label).toHaveDisplayValue('Head Of Testing');
    });

    test("renders date picker 'startDate' with value 01-01-2000", () => {
        const label = screen.getByLabelText('Von');
        expect(label).toHaveDisplayValue('01/01/2000');
    });

    test("renders date picker 'endDate' with value 01-01-2000", () => {
        const label = screen.getByLabelText('Bis');
        expect(label).toHaveDisplayValue('12/31/2003');
    });

    test('renders delete button', () => {
        const buttons = screen.getAllByRole('button');
        const deleteButton = buttons.find(button => button.ariaLabel == 'delete');
        expect(deleteButton).toBeInTheDocument();
    })

    test('click on delete button calls delete function', async () => {
        const user = userEvent.setup();
        const buttons = screen.getAllByRole('button');
        const deleteButton = buttons.find(button => button.ariaLabel == 'delete');
        
        if(deleteButton == null){
            throw new Error('Could not find delete button');
        }
        await user.click(deleteButton);
        expect(mockDeleteFunction).toBeCalledTimes(1);
    });

    test('when user types into textfield, display value gets updated', async () => {
        const user = userEvent.setup();
        const label = screen.getByLabelText('Arbeitgeber');

        await user.type(label, 'Anderer Arbeitgeber');
        expect(mockWriteIntoArbeitgeberInputFieldFunction).toBeCalled();
    });

    test('when user types into description textfield, change function gets called', async () => {
        const user = userEvent.setup();
        const label = screen.getByLabelText('Description');

        await user.type(label, 'Andere Description');
        expect(mockWriteIntoDescriptionInputFieldFunction).toBeCalled();
    });
});