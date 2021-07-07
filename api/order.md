#### 1. Place new order

**POST /order**

> request

application/json

```json
{
  "shipmentId": 1
}
```

> response

success

```json
{
  "status": 0,
  "data": {
    "orderNum": 100232211726368,
    "payment": 6150.00,
    "postage": 3,
    "paymentTime": null,
    "shippedTime": null,
    "receivedTime": null,
    "status": 10,
    "createTime": "2021-07-07T00:14:03.000+00:00",
    "updateTime": "2021-07-07T00:14:03.000+00:00",
    "closedTime": null,
    "orderItemVoList": [
      {
        "orderNum": 100232211726368,
        "productId": 4,
        "description": "DIVER 300M\nCO‑AXIAL MASTER CHRONOMETER 42 MM\n",
        "mainImg": "https://www.omegawatches.com/media/catalog/product/cache/a5c37fddc1a529a1a44fea55d527b9a116f3738da3a2cc38006fcc613c37c391/o/m/omega-seamaster-diver-300m-co-axial-master-chronometer-42-mm-52230422003001-l.png",
        "unitPrice": 6150.00,
        "quantity": 1,
        "totalPrice": 6150.00
      }
    ],
    "shippingId": 1,
    "shipping": {
      "id": 1,
      "userId": 1,
      "receiverName": "David",
      "receiverMobile": "3214325432",
      "receiverState": "IN",
      "receiverCity": "Fort Wayne",
      "receiverAddr": "Somewhere on this planet",
      "receiverApt": "APT101",
      "receiverZip": "46774",
      "createTime": "2021-05-27T13:53:34.000+00:00",
      "updateTime": "2021-07-05T23:24:08.000+00:00"
    }
  }
}
```

fail

```json
{
  "status": 10,
  "msg": "NOTHING IS SELECTED IN CART"
}
```

```json
{
  "status": 6,
  "msg": "Cannot find shipping address with ID: 100"
}
```

```json
{
  "status": 8,
  "msg": "No enough stock for product with ID 2"
}
```

```json
{
  "status": 4,
  "msg": "PLEASE LOGIN FIRST"
}
```

----

#### 2. List all orders

**GET /orders**

> request

```text
No arguments required
```

> response

success

```json
{
  "status": 0,
  "data": {
    "content": [
      {
        "orderNum": 100230833897504,
        "payment": 6150.00,
        "postage": 46,
        "paymentTime": null,
        "shippedTime": null,
        "receivedTime": null,
        "status": 10,
        "createTime": "2021-07-07T00:03:11.000+00:00",
        "updateTime": "2021-07-07T00:03:11.000+00:00",
        "closedTime": null,
        "orderItemVoList": [
          {
            "orderNum": 100230833897504,
            "productId": 4,
            "description": "DIVER 300M\nCO‑AXIAL MASTER CHRONOMETER 42 MM\n",
            "mainImg": "https://www.omegawatches.com/media/catalog/product/cache/a5c37fddc1a529a1a44fea55d527b9a116f3738da3a2cc38006fcc613c37c391/o/m/omega-seamaster-diver-300m-co-axial-master-chronometer-42-mm-52230422003001-l.png",
            "unitPrice": 6150.00,
            "quantity": 1,
            "totalPrice": 6150.00
          }
        ],
        "shippingId": 1,
        "shipping": {
          "id": 1,
          "userId": 1,
          "receiverName": "David",
          "receiverMobile": "3214325432",
          "receiverState": "IN",
          "receiverCity": "Fort Wayne",
          "receiverAddr": "Somewhere on this planet",
          "receiverApt": "APT101",
          "receiverZip": "46774",
          "createTime": "2021-05-27T13:53:34.000+00:00",
          "updateTime": "2021-07-05T23:24:08.000+00:00"
        }
      },
      {
        "orderNum": 100232211726368,
        "payment": 6150.00,
        "postage": 3,
        "paymentTime": null,
        "shippedTime": null,
        "receivedTime": null,
        "status": 10,
        "createTime": "2021-07-07T00:14:03.000+00:00",
        "updateTime": "2021-07-07T00:14:03.000+00:00",
        "closedTime": null,
        "orderItemVoList": [
          {
            "orderNum": 100232211726368,
            "productId": 4,
            "description": "DIVER 300M\nCO‑AXIAL MASTER CHRONOMETER 42 MM\n",
            "mainImg": "https://www.omegawatches.com/media/catalog/product/cache/a5c37fddc1a529a1a44fea55d527b9a116f3738da3a2cc38006fcc613c37c391/o/m/omega-seamaster-diver-300m-co-axial-master-chronometer-42-mm-52230422003001-l.png",
            "unitPrice": 6150.00,
            "quantity": 1,
            "totalPrice": 6150.00
          }
        ],
        "shippingId": 1,
        "shipping": {
          "id": 1,
          "userId": 1,
          "receiverName": "David",
          "receiverMobile": "3214325432",
          "receiverState": "IN",
          "receiverCity": "Fort Wayne",
          "receiverAddr": "Somewhere on this planet",
          "receiverApt": "APT101",
          "receiverZip": "46774",
          "createTime": "2021-05-27T13:53:34.000+00:00",
          "updateTime": "2021-07-05T23:24:08.000+00:00"
        }
      }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 0,
      "pageNumber": 0,
      "pageSize": 10,
      "paged": true,
      "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 5,
    "last": true,
    "size": 10,
    "first": true,
    "numberOfElements": 5,
    "number": 0,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "empty": false
  }
}
```

fail

```json
{
  "status": 4,
  "msg": "PLEASE LOGIN FIRST"
}
```

----

#### 3. List order detail

**GET /orders/{orderNo}**

> request

```text
Order number
```

> response

success

```json
{
  "status": 0,
  "data": {
    "orderNum": 100232211726368,
    "payment": 6150.00,
    "postage": 3,
    "paymentTime": null,
    "shippedTime": null,
    "receivedTime": null,
    "status": 10,
    "createTime": "2021-07-07T00:14:03.000+00:00",
    "updateTime": "2021-07-07T00:14:03.000+00:00",
    "closedTime": null,
    "orderItemVoList": [
      {
        "orderNum": 100232211726368,
        "productId": 4,
        "description": "DIVER 300M\nCO‑AXIAL MASTER CHRONOMETER 42 MM\n",
        "mainImg": "https://www.omegawatches.com/media/catalog/product/cache/a5c37fddc1a529a1a44fea55d527b9a116f3738da3a2cc38006fcc613c37c391/o/m/omega-seamaster-diver-300m-co-axial-master-chronometer-42-mm-52230422003001-l.png",
        "unitPrice": 6150.00,
        "quantity": 1,
        "totalPrice": 6150.00
      }
    ],
    "shippingId": 1,
    "shipping": {
      "id": 1,
      "userId": 1,
      "receiverName": "David",
      "receiverMobile": "3214325432",
      "receiverState": "IN",
      "receiverCity": "Fort Wayne",
      "receiverAddr": "Somewhere on this planet",
      "receiverApt": "APT101",
      "receiverZip": "46774",
      "createTime": "2021-05-27T13:53:34.000+00:00",
      "updateTime": "2021-07-05T23:24:08.000+00:00"
    }
  }
}
```

fail

```json
{
  "status": 11,
  "msg": "No such order with No.100232211726369"
}
```

```json
{
  "status": 4,
  "msg": "PLEASE LOGIN FIRST"
}
```

----

#### 4. Cancel order

**PUT /orders/{orderNo}

> request

```text
Order Number
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
  "status": 11,
  "msg": "No such order with No.100230833897509"
}
```

```json
{
  "status": 12,
  "msg": "Cannot cancel order 100230833897504"
}
```

```json
{
  "status": 4,
  "msg": "PLEASE LOGIN FIRST"
}
```