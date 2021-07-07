#### 1.List all categories

**GET /categories**

> request

```No arguments required```

> response

success

```json
{
  "status": 0,
  "data": [
    {
      "parentId": 0,
      "category": "Rolex",
      "sortOrder": 1,
      "subCategories": [
        {
          "parentId": 1,
          "category": "Submariner",
          "sortOrder": 1,
          "subCategories": [],
          "id": 16
        },
        {
          "parentId": 1,
          "category": "GMT-Master II",
          "sortOrder": 1,
          "subCategories": [],
          "id": 17
        },
        {
          "parentId": 1,
          "category": "Explorer",
          "sortOrder": 1,
          "subCategories": [],
          "id": 18
        },
        {
          "parentId": 1,
          "category": "Cosmograph Daytona",
          "sortOrder": 1,
          "subCategories": [],
          "id": 19
        }
      ],
      "id": 1
    },
    {
      "parentId": 0,
      "category": "Omega",
      "sortOrder": 2,
      "subCategories": [
        {
          "parentId": 2,
          "category": "Seamaster 300m",
          "sortOrder": 1,
          "subCategories": [],
          "id": 20
        },
        {
          "parentId": 2,
          "category": "Seamaster 600m",
          "sortOrder": 1,
          "subCategories": [],
          "id": 21
        },
        {
          "parentId": 2,
          "category": "Constellation",
          "sortOrder": 1,
          "subCategories": [],
          "id": 22
        }
      ],
      "id": 2
    },
    {
      "parentId": 0,
      "category": "Tudor",
      "sortOrder": 3,
      "subCategories": [
        {
          "parentId": 3,
          "category": "Black Bay Chrono",
          "sortOrder": 1,
          "subCategories": [],
          "id": 23
        },
        {
          "parentId": 3,
          "category": "Black Bay 58",
          "sortOrder": 1,
          "subCategories": [],
          "id": 24
        }
      ],
      "id": 3
    },
    {
      "parentId": 0,
      "category": "IWC",
      "sortOrder": 4,
      "subCategories": [
        {
          "parentId": 4,
          "category": "Portugieser",
          "sortOrder": 1,
          "subCategories": [],
          "id": 25
        }
      ],
      "id": 4
    },
    {
      "parentId": 0,
      "category": "Patek Philippe",
      "sortOrder": 5,
      "subCategories": [],
      "id": 5
    },
    {
      "parentId": 0,
      "category": "Cartier",
      "sortOrder": 6,
      "subCategories": [],
      "id": 6
    },
    {
      "parentId": 0,
      "category": "Audemars Piguet",
      "sortOrder": 7,
      "subCategories": [],
      "id": 7
    },
    {
      "parentId": 0,
      "category": "Zenith",
      "sortOrder": 8,
      "subCategories": [],
      "id": 9
    }
  ]
}
```

----

#### 2. List one category

**Get /category/{id}**

> request

```Category id```

> response

success

```json
{
  "status": 0,
  "data": {
    "parentId": 1,
    "category": "Submariner",
    "sortOrder": 1,
    "subCategories": [],
    "id": 16
  }
}
```

fail

```json
{
  "status": 5,
  "msg": "NO SUCH CATEGORY"
}
```

