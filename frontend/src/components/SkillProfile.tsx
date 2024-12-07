import { useEffect, useState } from 'react';
import {
  BasicInfoType,
  Industry,
  LanguageKnowledge,
  ProjectAssignment,
  WorkExperienceType,
} from '../types/types';

import { SkillProfileApi } from '../api/skillProfileApi.ts';
import { BasicInfo } from './SkillProfile/BasicInfo/BasicInfo.tsx';
import IndustryKnowledge from './SkillProfile/IndustryKnowledge/IndustryKnowledge.tsx';
import { Languages } from './SkillProfile/Languages/Languages.tsx';
import { Projects } from './SkillProfile/Projects/Projects.tsx';
import {
  Qualifications,
  QualificationType,
} from './SkillProfile/Qualifications/Qualifications.tsx';
import { WorkExperience } from './SkillProfile/WorkExperience/WorkExperience.tsx';

import AddIcon from '@mui/icons-material/Add';
import { Button } from '@mui/material';
import { SkillProfileDTO } from '../generated/index.ts';
import { _throw } from '../utils/_throw.ts';


const userId = '9483a127-d4ba-4f97-b5da-eec97c86036b';

const emptyBasicInfos = {
  id: '',
  firstName: '',
  lastName: '',
};

export default function SkillProfile() {
  const [basicInfo, setBasicInfo] = useState<BasicInfoType>(emptyBasicInfos);
  const [languageKnowledges, setLanguageKnowledges] = useState<
    LanguageKnowledge[]
  >([]);
  const [industries, setIndustries] = useState<Industry[]>([]);
  const [qualifications, setQualifications] = useState<QualificationType[]>([]);
  const [workExperiences, setWorkExperiences] = useState<WorkExperienceType[]>(
    []
  );
  const [projectAssignments, setProjectAssignments] = useState<ProjectAssignment[]>([]);

  function putSkillProfile() {

    const newSkillProfile:SkillProfileDTO = {
      skillProfileId: userId,
      basicInfo: basicInfo,
      qualifications: qualifications,
      industryKnowledges: industries,
      languages: languageKnowledges,
      projectAssignments: projectAssignments,
      workExperience: workExperiences,
      skills: []
    }

    void SkillProfileApi.putSkillProfile({skillProfileId: userId, skillProfileDTO: newSkillProfile});
  }

  useEffect(() =>{
    const fetchProfile = async () => {

      const skillProfile = await SkillProfileApi.getSkillProfile({ skillProfileId: userId });
      if(skillProfile == null){
        _throw('404 - Not found, please create a profile first');
      }

      const basicInfoBusinessObject = skillProfile.basicInfo;
      const LanguageKnowledgeBusinessObject = skillProfile.languages;
      const workExperienceBusinessObject = skillProfile.workExperience;
      const industryBusinessObject = skillProfile.industryKnowledges;
      const qualificationBusinessObject = skillProfile.qualifications;
      const projectAssignmentBusinessObject = skillProfile.projectAssignments;

      setBasicInfo(basicInfoBusinessObject);
      setLanguageKnowledges(LanguageKnowledgeBusinessObject);
      setWorkExperiences(workExperienceBusinessObject);
      setIndustries(industryBusinessObject);
      setQualifications(qualificationBusinessObject);
      setProjectAssignments(projectAssignmentBusinessObject);  
    };

    void fetchProfile();
  }, []);

  return (
    <section id="skill-profil">
      <BasicInfo basicInfo={basicInfo}></BasicInfo>
      <Languages languages={languageKnowledges} setLanguages={setLanguageKnowledges}></Languages>
      <Projects projectAssignments={projectAssignments} setProjectAssignments={setProjectAssignments}></Projects>
      <IndustryKnowledge industryKnowledge={industries} setIndustries={setIndustries}></IndustryKnowledge>
      <Qualifications qualifications={qualifications} setQualifictions={setQualifications}></Qualifications>
      <WorkExperience workExperiences={workExperiences} setWorkExperiences={setWorkExperiences}></WorkExperience>
      <Button
          variant="contained"
          aria-label="add"
          size="medium"
          onClick={() => putSkillProfile()}
          startIcon={<AddIcon/>}
          className="button-add"
        >
          send To Server
        </Button>
    </section>
  );
}
