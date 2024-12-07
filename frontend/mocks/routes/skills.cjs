const KATEGORIEN = [
  {
    categoryId: 1,
    categoryName: "Frontend",
    skills: [
      {
        skillId: 1,
        skillName: "React",
      },
      {
        skillId: 2,
        skillName: "Vue",
      },
      {
        skillId: 3,
        skillName: "Swing",
      },
    ],
  },
  {
    categoryId: 2,
    categoryName: "Datenbanken",
    skills: [
      {
        skillId: 4,
        skillName: "PostgreSQL",
      },
      {
        skillId: 5,
        skillName: "MongoDB",
      },
      {
        skillId: 9,
        skillName: "MySQL'); DROP TABLE SKILLS;--",
      },
    ],
  },
  {
    categoryId: 3,
    categoryName: "Programmiersprache",
    skills: [
      {
        skillId: 6,
        skillName: "Java",
      },
      {
        skillId: 7,
        skillName: "JavaScript",
      },
    ],
  },
  {
    categoryId: 4,
    categoryName: "Janek",
    skills: []
  },
  {
    categoryId: 5,
    categoryName: "Framework",
    skills: [
      {
        skillId: 1,
        skillName: "React",
      },
      {
        skillId: 2,
        skillName: "Vue",
      },
      {
        skillId: 8,
        skillName: "Spring"
      }
    ],
  },
];

module.exports = [
  {
    id: "get-Categories",
    url: "/categories",
    method: "GET",
    variants: [
      {
        id: "success",
        type: "json",
        options: {
          status: 200,
          body: KATEGORIEN,
        },
      },
      {
        id: "error",
        type: "json",
        options: {
          status: 400,
          body: {
            message: "error",
          },
        },
      },
    ],
  },
  {
    id: "add-skill",
    url: "/skills",
    method: "PUT",
    variants: [
      {
        delay: 2000,
        id: "success",
        type: "status",
        options: {
          status: 200,
          
        },
      },
    ],
  },
  {
    id: "update-skill",
    url: "/skills/*",
    method: "PUT",
    variants: [
      {
        delay: 2000,
        id: "success",
        type: "status",
        options: {
          status: 200,
          
        },
      },
    ],
  },
  {
    id: "delete-skill",
    url: "/skills/*",
    method: "DELETE",
    variants: [
      {
        delay: 2000,
        id: "success",
        type: "status",
        options: {
          status: 200,
        },
      },
    ],
  },
];
