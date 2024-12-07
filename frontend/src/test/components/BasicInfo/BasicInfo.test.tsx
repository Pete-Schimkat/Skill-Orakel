import { cleanup, render, screen } from '@testing-library/react';
import { beforeEach, describe, expect, it } from 'vitest';

import { BasicInfoType } from '../../../types/types';
import userEvent from '@testing-library/user-event';
import { BasicInfo } from '../../../components/SkillProfile/BasicInfo/BasicInfo';

const testData: BasicInfoType = {
    firstName: 'Heinz-Test',
    lastName: 'Testermann',
    id: '124'
}

describe('test basic info component', () => {

    beforeEach(() => {
        cleanup();
    })

    it("renders h2 with text 'Profile'", () => {
        render(<BasicInfo basicInfo={testData}></BasicInfo>);
        const header = screen.getByText('Profile');
        expect(header).toBeInTheDocument();
    });

    it("renders a textfield that shows the prename of the profile owner, 'Heinz-Test", () => {
        render(<BasicInfo basicInfo={testData}></BasicInfo>);
        const nameField = screen.getByDisplayValue('Heinz-Test');
        expect(nameField).toBeInTheDocument();
    });

    it("renders a textfield that shows the family name of the profile owner, 'Testermann'", () => {
        render(<BasicInfo basicInfo={testData}></BasicInfo>);
        const nameField = screen.getByDisplayValue('Testermann');
        expect(nameField).toBeInTheDocument();
    });

    it('renders surname field that does not change its value when a user types in it', async () => {
        
        const newName = 'Testanna';
        const user = userEvent.setup();
        render(<BasicInfo basicInfo={testData}></BasicInfo>);
        
        const preNameField = screen.getByLabelText('Vorname');
        await user.type(preNameField, newName);

        expect(preNameField).toHaveDisplayValue('Heinz-Test');
    });

    it('renders family name field that does not change its value when a user types in it', async () => {
        
        const newName = 'Testhausen';
        const user = userEvent.setup();
        render(<BasicInfo basicInfo={testData}></BasicInfo>);
        
        const familyNameField = screen.getByLabelText('Name');
        await user.type(familyNameField, newName);

        expect(familyNameField).toHaveDisplayValue('Testermann');
    });
});
