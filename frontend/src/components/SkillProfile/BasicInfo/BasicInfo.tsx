import { TextField } from '@mui/material';
import { BasicInfoType } from '../../../types/types';
import { useEffect, useState } from 'react';

export const BasicInfo = ({
    basicInfo
}: {
    basicInfo: BasicInfoType
}) => {

    const [profilData, setProfilData] = useState<BasicInfoType>(basicInfo);

    useEffect(() => {
        setProfilData(basicInfo);
    }, [basicInfo])

    return (
        <>
            <h2>Profile</h2>
            <section id="profile-basic-info">
                <div className="name-info">
                    <TextField
                        className="name-input-field"
                        InputLabelProps={{ shrink: true }}
                        label={'Vorname'}
                        disabled={true}
                        variant="outlined"
                        value={profilData.firstName}
                    >
                        {' '}
                    </TextField>
                    <TextField
                        className="name-input-field"
                        InputLabelProps={{ shrink: true }}
                        label={'Name'}
                        disabled={true}
                        variant="outlined"
                        value={profilData.lastName}
                    >
                        {' '}
                    </TextField>
                </div>
            </section>
        </>
    );
}