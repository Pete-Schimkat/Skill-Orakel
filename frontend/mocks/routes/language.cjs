const LANGUAGES = [
    {
        id: 1,
        name: "Englisch",
        sprachLevelId: 1
    },
    {
        id: 2,
        name: "Orkisch",
        sprachLevelId: 2
    },
    {
        id: 3,
        name: "Französisch",
        sprachLevelId: 3
    }
]

module.exports = [
    {
        id: "get-sprachen",
        url: "/sprachen",
        method: "GET",
        variants: [
            {
                id: "success",
                type: "json",
                options: {
                    status: 200,
                    body: LANGUAGES,
                },
            },
            {
                id: "error",
                type: "json",
                options: {
                    status: 400,
                    body: {
                        message: "Could not fetch sprachen",
                    },
                },
            },
        ],
    },
];
