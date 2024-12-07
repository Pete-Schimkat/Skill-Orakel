import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { cleanup, render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { QualificationType } from '../../../components/SkillProfile/Qualifications/Qualifications';
import { QualificationEntry } from '../../../components/SkillProfile/Qualifications/QualificationEntry';

const testData: QualificationType = {
    id: '2',
    title: 'Certifacte',
    issuer: 'Zertifkationsstelle',
    description: 'Master of Zertifizierung',
    date: new Date('05.21.2021')
}

describe('test qualificationseintrag', () => {
    afterEach(() => {
        cleanup()
    });

    it('renders issuer textfield with correct value', async () => {
        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <QualificationEntry
                    qualification={testData}
                    onDelete={vi.fn()}
                    onChangeIssuer={vi.fn()}
                    onChangeDescription={vi.fn()}
                    onChangeDate={vi.fn()}
                >
                </QualificationEntry>
            </LocalizationProvider>);

        const textFieldIssuer = await screen.findByLabelText('Ausbilder/ Uni/ Fachhochschule');
        expect(textFieldIssuer).toHaveDisplayValue('Zertifkationsstelle');
    });

    it('renders description textfield with correct value', async () => {
        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
            <QualificationEntry
                qualification={testData}
                onDelete={vi.fn()}
                onChangeIssuer={vi.fn()}
                onChangeDescription={vi.fn()}
                onChangeDate={vi.fn()}
            >
            </QualificationEntry>
        </LocalizationProvider>);

        const textFieldDescription = await screen.findByLabelText('Description');
        expect(textFieldDescription).toHaveDisplayValue(testData.description);
    });

    it('renders date field with correct value', async () => {
        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
            <QualificationEntry
                qualification={testData}
                onDelete={vi.fn()}
                onChangeIssuer={vi.fn()}
                onChangeDescription={vi.fn()}
                onChangeDate={vi.fn()}
            >
            </QualificationEntry>
        </LocalizationProvider>);

        const datePicker = screen.getByLabelText('Abschlussdatum');
        expect(datePicker).toHaveDisplayValue('05/21/2021');
    });

    it('renders delete button', async () => {
        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
            <QualificationEntry
                qualification={testData}
                onDelete={vi.fn()}
                onChangeIssuer={vi.fn()}
                onChangeDescription={vi.fn()}
                onChangeDate={vi.fn()}
            >
            </QualificationEntry>
        </LocalizationProvider>);

        const allButtons = await screen.findAllByRole('button');
        const deleteButton = await allButtons.find(button => button.ariaLabel == 'delete');
        expect(deleteButton).toBeInTheDocument();
    });

    it('executes delete function when user clicks on delete button', async () => {

        const user = userEvent.setup();
        const deleteFnMock = vi.fn();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
            <QualificationEntry
                qualification={testData}
                onDelete={deleteFnMock}
                onChangeIssuer={vi.fn()}
                onChangeDescription={vi.fn()}
                onChangeDate={vi.fn()}
            >
            </QualificationEntry>
        </LocalizationProvider>);

        const allButtons = await screen.findAllByRole('button');
        const deleteButton = await allButtons.find(button => button.ariaLabel == 'delete');
        
        
        if(deleteButton == null){
            throw new Error('Delete Button not found in the document');
        }
        await user.click(deleteButton);
        expect(deleteFnMock).toBeCalledTimes(1);
    });

    it('executes change method when user types in issuer text field', async () => {
        const user = userEvent.setup();
        const changeIssuerField = vi.fn();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
            <QualificationEntry
                qualification={testData}
                onDelete={vi.fn()}
                onChangeIssuer={changeIssuerField}
                onChangeDescription={vi.fn()}
                onChangeDate={vi.fn()}
            >
            </QualificationEntry>
        </LocalizationProvider>);

        const textField = await screen.getByLabelText('Ausbilder/ Uni/ Fachhochschule');
        await user.type(textField, 'Anderer Issuer');
        expect(changeIssuerField).toBeCalled();
    })

    it('executes change method when user types in description text field', async () => {
        const user = userEvent.setup();
        const changeDescriptionField = vi.fn();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
            <QualificationEntry
                qualification={testData}
                onDelete={vi.fn()}
                onChangeIssuer={vi.fn()}
                onChangeDescription={changeDescriptionField}
                onChangeDate={vi.fn()}
            >
            </QualificationEntry>
        </LocalizationProvider>);

        const textField = await screen.getByLabelText('Description');
        await user.type(textField, 'Testing Master Cerfificate');
        expect(changeDescriptionField).toBeCalled();
    })

    it('executes change method when user selects new date in datepicker', async () => {
        const user = userEvent.setup();
        const onChangeDate = vi.fn();

        render(
            <LocalizationProvider dateAdapter={AdapterDayjs}>
            <QualificationEntry
                qualification={testData}
                onDelete={vi.fn()}
                onChangeIssuer={vi.fn()}
                onChangeDescription={vi.fn()}
                onChangeDate={onChangeDate()}
            >
            </QualificationEntry>
        </LocalizationProvider>);

        const datePicker = await screen.getByLabelText('Abschlussdatum');

        await user.click(datePicker);
        await user.type(datePicker, '05/25/2021');
        expect(onChangeDate).toBeCalled();
    })


})