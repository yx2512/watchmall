#### 1. List all items in cart

**GET /cart**

>request

```text
No arguments required
```

> response

success

```json
{
  "status": 0,
  "data": {
    "cartProductVoList": [
      {
        "productId": 1,
        "quantity": 1,
        "description": "Submariner Date\nOyster, 41 mm, Oystersteel",
        "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610ln-0001.png?imwidth=420",
        "price": 9150.00,
        "status": 1,
        "totalPrice": 9150.00,
        "selected": true
      }
    ],
    "selectAll": true,
    "totalPrice": 9150.00,
    "totalQuantity": 1
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

----

#### 2. Add item to cart

**POST /cart/{productId}**

> request

```json
{
  "quantity": 1,
  "selected": true
}
```

> response

success

```json
{
  "status": 0,
  "data": {
    "cartProductVoList": [
      {
        "productId": 1,
        "quantity": 1,
        "description": "Submariner Date\nOyster, 41 mm, Oystersteel",
        "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610ln-0001.png?imwidth=420",
        "price": 9150.00,
        "status": 1,
        "totalPrice": 9150.00,
        "selected": true
      }
    ],
    "selectAll": true,
    "totalPrice": 9150.00,
    "totalQuantity": 1
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
  "status": 41,
  "msg": "PRODUCT_UNAVAILABLE"
}
```

```json
{
  "status": 12,
  "msg": "PLEASE LOGIN FIRST"
}
```

----

#### 3. Update item in cart

same as above

----

#### 4. Delete item from cart

**DELETE /cart/{productId}**

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
    "cartProductVoList": [
      {
        "productId": 2,
        "quantity": 1,
        "description": "Submariner Date\nOyster, 41 mm, Oystersteel",
        "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610lv-0002.png?imwidth=390",
        "price": 9150.00,
        "status": 1,
        "totalPrice": 9150.00,
        "selected": true
      }
    ],
    "selectAll": true,
    "totalPrice": 9150.00,
    "totalQuantity": 1
  }
}
```

fail

```json
{
  "status": 50,
  "msg": "NO SUCH PRODUCT IN CART"
}
```

```json
{
  "status": 12,
  "msg": "PLEASE LOGIN FIRST"
}
```

----

#### 4. Select all items in cart

**PUT /cart/selectAll**

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
    "cartProductVoList": [
      {
        "productId": 2,
        "quantity": 1,
        "description": "Submariner Date\nOyster, 41 mm, Oystersteel",
        "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610lv-0002.png?imwidth=390",
        "price": 9150.00,
        "status": 1,
        "totalPrice": 9150.00,
        "selected": true
      }
    ],
    "selectAll": true,
    "totalPrice": 9150.00,
    "totalQuantity": 1
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

----

#### 5. Deselect all items in cart

**PUT /cart/deselectAll**

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
    "cartProductVoList": [
      {
        "productId": 2,
        "quantity": 1,
        "description": "Submariner Date\nOyster, 41 mm, Oystersteel",
        "mainImg": "https://content.rolex.com/dam/2021/upright-bba-with-shadow/m126610lv-0002.png?imwidth=390",
        "price": 9150.00,
        "status": 1,
        "totalPrice": 9150.00,
        "selected": false
      }
    ],
    "selectAll": false,
    "totalPrice": 0,
    "totalQuantity": 0
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

----

#### 6. Get total quantity

**GET /cart/products/sum**

> request

```text
No arguments required
```

> response

success

```json
{
  "status": 0,
  "data": 1
}
```

fail

```json
{
  "status": 12,
  "msg": "PLEASE LOGIN FIRST"
}
```
