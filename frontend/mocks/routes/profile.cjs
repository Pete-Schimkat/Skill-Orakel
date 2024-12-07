const { Language } = require("@mui/icons-material");

const PROFIL = {
  basisInfo: {
    surname: "Max",
    name: "Mustermann",
    email: "mmustermann@company.de"
  },

  language: [
    {
      id: 1,
      language: "Englisch",
      languageLevel: {
        label: "Mittelstufe",
        id: 1
      },
    },
    {
      id: 2,
      language: "Orkisch",
      languageLevel: {
        label: "verhandlungssicher",
        id: 3
      }
    },
    {
      id: 3,
      language: "Französisch",
      languageLevel: {
        label: "Elementarkenntnisse",
        id: 0,
      }
    }
  ],

  industryKnowledge: [
    {
      id: 1,
      industryName: "Automobilindustrie"
    },
    {
      id: 3,
      industryName: "Informationstechnik"
    },
    {
      id: 4,
      industryName: "Luftfahrtindustrie"
    } 
  ],

  qualifications: [
    {
      id: 1,
      issuer: "Krasse Uni",
      description: "Softwareentwickler",
      date: new Date(2002, 2, 21)
    },
  ],

  beruflicherWerdegang: [
    {
      id: 1,
      employer: "Test Inc",
      description: "QA Tester",
      startDate: new Date(2004, 5, 1),
      endDate: new Date(2009, 4, 30)
    },

    {
      id: 8,
      employer: "Testooo.com",
      description: "Chief Testing Engineer",
      startDate: new Date(2004, 5, 1),
      endDate: new Date(2009, 4, 30)
    }
  ]
}

module.exports = [
  {
    id: "get-profil",
    url: `/profil/1`,
    method: "GET",
    variants: [
      {
        id: "get-profil-success",
        type: "json",
        options: {
          status: 200,
          body: PROFIL,
        },
      }
    ],
  },
];