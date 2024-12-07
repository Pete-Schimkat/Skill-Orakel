import { Box, Tab, Tabs } from '@mui/material';
import SkillProfile from '../SkillProfile.tsx';
import React from 'react';
import Skills from '../Skills/Skills.tsx';
import CustomTabPanel from './TabPanel.tsx';
import { fetchSkillCategoryData } from '../Skills/SkillAPICalls.ts';

export default function NavigationBar() {
  const [value, setValue] = React.useState(0);

  const handleChange = (_event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  return (
    <div>
      <Box sx={{ width: '100%' }} className="tab-bar-top">
        <Box sx={{ borderBottom: 1, borderColor: 'divider' }} className="tab-bar-navbar">
          <Tabs
            value={value}
            onChange={handleChange}
            aria-label="basic tabs example"
          >
            <Tab label="Eigenes Profil" {...a11yProps(0)} className="tab-element" />
            <Tab label="Skill-Profile" {...a11yProps(1)} className="tab-element"/>
            <Tab label="Projekte" {...a11yProps(2)} className="tab-element"/>
            <Tab label="Skills" {...a11yProps(3)} className="tab-element" />
          </Tabs>
        </Box>
        <CustomTabPanel value={value} index={0}>
          <SkillProfile></SkillProfile>
        </CustomTabPanel>
        <CustomTabPanel value={value} index={1}>
          Skill-Profile
        </CustomTabPanel>
        <CustomTabPanel value={value} index={2}>
        </CustomTabPanel>
        <CustomTabPanel value={value} index={3}>
          <Skills fetchSkillCategoryData={fetchSkillCategoryData} />
        </CustomTabPanel>
      </Box>
    </div>
  );
}


function a11yProps(index: number) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}
