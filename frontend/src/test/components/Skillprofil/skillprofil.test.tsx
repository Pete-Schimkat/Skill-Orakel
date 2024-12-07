import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { render } from '@testing-library/react';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import SkillProfile from '../../../components/SkillProfile';

const successData = {

  basicInfo: {
    first_name: 'Max',
    last_name: 'Mustermann',
    id: 'asdfj293-afsk'
  },

  languages: [
    {
      id: '1',
      language: 'Englisch',
      languageLevel: 'Mittelstufe',
    },

    {
      id: '2',
      language: 'Orkisch',
      languageLevel: 'verhandlungssicher'
    },

    {
      id: '3',
      language: 'Französisch',
      languageLevel: 'Elementarkenntnisse',
    }
  ],

  industryKnowledges: [
    {
      id: 1,
      industryName: 'Automobilindustrie'
    },
    {
      id: 3,
      industryName: 'Informationstechnik'
    },
    {
      id: 4,
      industryName: 'Luftfahrtindustrie'
    }
  ],

  qualifications: [
    {
      id: 1,
      issuer: 'Krasse Uni',
      description: 'Softwareentwickler',
      date: new Date(2002, 2, 21)
    },
  ],

  workExperience: [
    {
      id: '1',
      employer: 'Test Inc',
      description: 'QA Tester',
      startDate: new Date(2004, 5, 1),
      endDate: new Date(2009, 4, 30),
      responsabilities: 'asdasd'
    },

    {
      id: '8',
      employer: 'Testooo.com',
      description: 'Chief Testing Engineer',
      startDate: new Date(2004, 5, 1),
      endDate: new Date(2009, 4, 30),
      responsabilities: 'asdasd'
    }
  ],

  projectAssignments: [{
    
  }]
}

describe('test skillprofil component', () => {

  const mockImplementation = async () => Promise.resolve({
    ok: true,
    json() {
      return successData;
    }
  });

  beforeEach(() => {
    globalThis.fetch = vi.fn();
  });

  it('calls fetch function when component is rendered', async () => {
    globalThis.fetch = vi.fn().mockImplementation(mockImplementation);

    render(
      <><LocalizationProvider dateAdapter={AdapterDayjs}>
        <SkillProfile /></LocalizationProvider>
      </>)
    expect(fetch).toBeCalledTimes(1);

    /* MORE TESTS NEEDED */
  });
})