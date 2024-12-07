// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { LanguageKnowledgeBusinessObjectLanguageLevelEnum } from '../generated';

export type LanguageLevel = {
    languageLevel: string;
};

export type LanguageKnowledge = {
    languageName: string;
    languageLevel: LanguageKnowledgeBusinessObjectLanguageLevelEnum;
    languageId: string
}

export type BasicInfoType = {
    id: string,
    firstName: string,
    lastName: string,
}

export type Industry = {
    id: string,
    name: string
}

export type WorkExperienceType = {
    id: string,
    employer: string,
    description: string,
    startDate: Date,
    endDate: Date
}

export const INDUSTRIES: Industry[] = [
    { id: '1', name: 'Automobilindustrie' },
    { id: '2', name: 'Maschinen- und Anlagenbau' },
    { id: '3', name: 'Informationstechnik' },
    { id: '4', name: 'Luftfahrtindustrie' },
    { id: '5', name: 'Hausgeräteindustrie' },
    { id: '6', name: 'Zulieferindustrie' },
    { id: '7', name: 'Schienenfahrzeugindustrie' },
    { id: '8', name: 'Nutzfahrzeugindustrie' }];


export type Skill = {
    id: string;
    name: string;
}
export type ProjectType = {
    id: string;
    name: string;
    description: string;
    customer: string;
};
export type ProjectAssignment = {
    projectId: string,
    name: string,
    description: string,
    responsibilities: string,
    customer: string, 
    startDate: Date,
    endDate?: Date
    skills: Skill[]
};
// export const skillOptions: Skill[] = [
//     { id: '1', name: 'JavaScript' },
//     { id: '2', name: 'Python' },
//     { id: '3', name: 'Java' },
//     { id: '4', name: 'C#' },
//     { id: '5', name: 'SQL' },
//     { id: '6', name: 'HTML' },
//     { id: '7', name: 'CSS' },
//     { id: '8', name: 'React' },
//     { id: '9', name: 'Node.js' },
//     { id: '10', name: 'Git' },


