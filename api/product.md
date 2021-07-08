#### 1. List all products
**GET /products**

> request

optional:
```text
categoryId
features
dialColor
size
pageNum
pageSize
```
> response

success
```json
{
  "status": 0,
  "data": {
    "content": [
      {
        "category": 20,
        "serialNum": "522.30.42.20.03.001",
        "price": null,
        "mainImg": "https://www.omegawatches.com/media/catalog/product/cache/a5c37fddc1a529a1a44fea55d527b9a116f3738da3a2cc38006fcc613c37c391/o/m/omega-seamaster-diver-300m-co-axial-master-chronometer-42-mm-52230422003001-l.png",
        "dialColor": 4,
        "description": "DIVER 300M\nCO‑AXIAL MASTER CHRONOMETER 42 MM\n",
        "id": 4
      },
      {
        "category": 16,
        "serialNum": "126610LN",
        "price": null,
        "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610ln-0001.png?imwidth=420",
        "dialColor": 2,
        "description": "Submariner Date\nOyster, 41 mm, Oystersteel",
        "id": 1
      },
      {
        "category": 16,
        "serialNum": "126610LV",
        "price": null,
        "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610lv-0002.png?imwidth=390",
        "dialColor": 6,
        "description": "Submariner Date\nOyster, 41 mm, Oystersteel",
        "id": 2
      },
      {
        "category": 17,
        "serialNum": "126711CHNR",
        "price": null,
        "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126711chnr-0002.png?imwidth=390",
        "dialColor": 2,
        "description": "GMT-Master II\nOyster, 40 mm, OysterSteel and Everose gold",
        "id": 3
      }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "pageNumber": 0,
      "pageSize": 10,
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 4,
    "number": 0,
    "first": true,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 4,
    "size": 10,
    "empty": false
  }
}
```
----

#### 2. List product detail
**GET /product/{id}**
> request

```text
productId
```

> response

success

```json
{
  "status": 0,
  "data": {
    "category": 16,
    "serialNum": "126610LV",
    "price": null,
    "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610lv-0002.png?imwidth=390",
    "subImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610lv-0002.png?imwidth=390",
    "size": 41.0,
    "dialColor": 6,
    "movement": "Cal.3235",
    "description": "Submariner Date\nOyster, 41 mm, Oystersteel",
    "status": 1,
    "stock": 3,
    "id": 2
  }
}
```

fail
```json
{
  "status": 40,
  "msg": "NO SUCH PRODUCT"
}
```
```json
{
  "status": -1,
  "msg": "RESOURCE NOT FOUND"
}
```