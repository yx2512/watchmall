#### 1. List all features

**GET /features**

> request

```text
No arguments required
```

> response

success

```json
{
  "status": 0,
  "data": [
    {
      "feature": "Chronometer",
      "id": 1
    },
    {
      "feature": "Chronograph",
      "id": 2
    },
    {
      "feature": "Transparent Back",
      "id": 3
    },
    {
      "feature": "GMT",
      "id": 4
    },
    {
      "feature": "Date",
      "id": 5
    },
    {
      "feature": "Day",
      "id": 6
    },
    {
      "feature": "Rotating bezel",
      "id": 7
    }
  ]
}
```

----

#### 2. List all dial colors

**GET /dial_colors**

> request

```text
No arguments required
```

> response

```json
{
  "status": 0,
  "data": [
    {
      "color": "White",
      "id": 1
    },
    {
      "color": "Grey",
      "id": 2
    },
    {
      "color": "Black",
      "id": 3
    },
    {
      "color": "Blue",
      "id": 4
    },
    {
      "color": "Gold",
      "id": 5
    },
    {
      "color": "Green",
      "id": 6
    }
  ]
}
```