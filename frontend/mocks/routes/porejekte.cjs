const PROJEKTE = [
  {
    id: 1,
  },
  {
    id: 2,
  },
];

module.exports = [
  {
    id: "get-projects",
    url: "/projects",
    method: "GET",
    variants: [
      {
        id: "all-projects-success",
        type: "json",
        options: {
          status: 200,
          body: PROJEKTE,
        },
      },
      {
        id: "single-project-success",
        type: "json",
        options: {
          status: 200,
          body: PROJEKTE[0],
        },
      },
    ],
  },
];
