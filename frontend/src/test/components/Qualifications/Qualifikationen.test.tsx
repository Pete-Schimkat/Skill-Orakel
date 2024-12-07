import { cleanup, render, screen } from '@testing-library/react';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import { Qualifications, QualificationType } from '../../../components/SkillProfile/Qualifications/Qualifications';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs/AdapterDayjs';
import userEvent from '@testing-library/user-event';

const qualifications = [
    {
        id: '37',
        title: 'My Certificate',
        issuer: 'Wayward Academy',
        description: 'Sorcery',
        date: new Date('01.21.2012')
    },
    {
        id: '12312',
        title: 'My Certificate',
        issuer: 'Testing Collage',
        description: 'Certification',
        date: new Date('04.12.2021')
    }
]

const mockFunction: React.Dispatch<React.SetStateAction<QualificationType[]>> = vi.fn();

describe('test qualifications componente', () => {

    beforeEach(() => {
        cleanup();
    });

    it('does not render textfield when user clicks on delete', async () => {
 
        const user = userEvent.setup();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={qualifications} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );

        const deleteButton = screen.getAllByRole('button').find(button => button.ariaLabel == 'delete')
        const textField = screen.getAllByLabelText('Ausbilder/ Uni/ Fachhochschule')[0];

        if (deleteButton == null) {
            throw new Error('Could not find delete button');
        }

        await user.click(deleteButton);
        expect(textField).not.toBeInTheDocument();
    });

    it('shows updated value in the issuer textfield after user has typed in a new value', async () => {
        const user = userEvent.setup();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={qualifications} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );
        const newValue = 'Berühmte Berufsschule'
        const textfield = screen.getAllByLabelText('Ausbilder/ Uni/ Fachhochschule')[0];

        await user.clear(textfield);
        await user.type(textfield, newValue);

        expect(textfield).toHaveDisplayValue(newValue);
    });

    it('shows updated value in the description textfield after user has typed in a new value', async () => {
        const user = userEvent.setup();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={qualifications} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );
        const newValue = 'QA Engineering Certificate'
        const textfield = screen.getAllByLabelText('Description')[0];

        await user.clear(textfield);
        await user.type(textfield, newValue);

        expect(textfield).toHaveDisplayValue(newValue);
    });

    /* TO DO: Test prüft nur getypte Eingabe, keine Klicks */
    it('shows updated value in the date picker after user has typed in a new value', async () => {
        const user = userEvent.setup();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={qualifications} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );
        const newValue = '06/12/2021'
        const datePickerTextField = screen.getAllByPlaceholderText('MM/DD/YYYY')[0];

        await user.type(datePickerTextField, newValue); 
        await user.tab();

        expect(datePickerTextField).toHaveValue(newValue);
    });

    it("shows new issuer textfield with default value 'issuer' when user clicks on add button", async () => {

        const user = userEvent.setup();
        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={qualifications} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );

        const addButton = await screen.getAllByRole('button').find(button => button.ariaLabel == 'add');

        if (addButton == null) {
            throw new Error('Add Button not found');
        }
        await user.click(addButton);
        const newTextfield = await screen.findByDisplayValue('Issuer');
        expect(newTextfield).toBeInTheDocument();
    });

    it('does NOT render new textfield when add button has not been clicked', async () => {
        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={qualifications} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );

        const newTextfield = await screen.queryByDisplayValue('Issuer');
        expect(newTextfield).not.toBeInTheDocument();
    });

    it('does NOT render clear button when add button has not been clicked', async () => {
        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={qualifications} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );

        const buttons = screen.queryAllByRole('button');
        const clearButton = buttons.find(button => button.ariaLabel == 'clear');
        expect(clearButton).toBeNull();
    });

    it('does render clear button when add button has been clicked', async () => {
        const user = userEvent.setup();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={qualifications} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );

        const addButton = screen.getAllByRole('button').find(button => button.ariaLabel == 'add');

        if (addButton == null) {
            throw new Error('Add Button not found');
        }
        await user.click(addButton);

        const clearButton = screen.getAllByRole('button').find(button => button.ariaLabel == 'clear');
        expect(clearButton).toBeInTheDocument();
    });

    it('renders fallback paragraph element when no data is available', async () => {
        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <Qualifications  qualifications={[]} setQualifictions={mockFunction}></Qualifications>
            </LocalizationProvider>
        );

        const fallbackParagraph = screen.queryByText('Noch keine Qualifikationen angelegt.')
        expect(fallbackParagraph).toBeInTheDocument();
    })
})