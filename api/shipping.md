#### 1.Add new shipping address to current user's profile

**POST /shipments**


> request

Content-Type: application/json

```json
{
  "receiverName": "David",
  "receiverMobile": "3215437777",
  "receiverState": "IN",
  "receiverCity": "Lafayette",
  "receiverAddr": "Somewhere on this planet",
  "receiverApt": "APT101",
  "receiverZip": "47901" 
}
```

> response

success

```json
{
    "status": 0,
    "data": {
        "shipmentId": 28
    }
}
```

fail

```json
{
  "status": 12,
  "msg": "PLEASE LOGIN FIRST"
}
```

------

#### 2.Delete one shipping address from current user's profile

**DELETE /shippings/{shippingId}**

> request

```Id of shipping address```

> response

success

```json
{
    "status": 0
}
```

fail

```json
{
  "status": 12,
  "msg": "PLEASE LOGIN FIRST"
}
```

------

#### 3.Update one shipping address of current user

**PUT /shipments/{shippingId}**

> request

Content-Type: application/json

```json
{
  "receiverName": "David",
  "receiverMobile": "3215437777",
  "receiverState": "IN",
  "receiverCity": "Lafayette",
  "receiverAddr": "Somewhere on this planet",
  "receiverApt": "APT101",
  "receiverZip": "47901"
}
```

> response

success

```json
{
    "status": 0
}
```

fail

```json
{
  "status": 12,
  "msg": "PLEASE LOGIN FIRST"
}
```

```json
{
  "status": 30,
  "msg": "NO CORRESPONDING SHIPPING ADDRESS"
}
```

------

#### 4.List all shipping addresses of current user

**GET /shipments**

> request

```
pageNum(default 1),pageSize(default 10)
```

> response

success

```json
{
  "status": 0,
  "data": {
    "content": [
      {
        "id": 1,
        "userId": 1,
        "receiverName": "admin",
        "receiverMobile": "1233334444",
        "receiverState": "IN",
        "receiverCity": "West Lafayette",
        "receiverAddr": "Somewhere on this planet",
        "receiverApt": "APT1503",
        "receiverZip": "47906",
        "createTime": "2021-05-27T13:53:34.000+00:00",
        "updateTime": "2021-05-27T14:14:17.000+00:00"
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
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "numberOfElements": 1,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "number": 0,
    "first": true,
    "size": 10,
    "empty": false
  }
}
```

fail

```json
{
  "status": 12,
  "msg": "PLEASE LOGIN FIRST"
}
```

```json
{
  "status": 30,
  "msg": "NO CORRESPONDING SHIPPING ADDRESS"
}
```
