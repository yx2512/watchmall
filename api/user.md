#### 1.Login

**POST /user/login**

> request

Content-Type: application/json

```json
{
  "email": "admin@watchmall.com",
  "password": "admin"
}
```

> response

success

```json
{
  "status": 0,
  "data": {
    "id": 1,
    "username": "ADMIN",
    "email": "admin@watchmall.com",
    "phone": "1234567889",
    "role": 1
  }
}
```

fail

```json
{
    "status": 10,
    "msg": "INVALID EMAIL OR PASSWORD"
}
```

-------

#### 2.Register

**POST /user/register**

> request

Content-Type: application/json

```json
{
  "username": "admin",
  "email": "admin@watchmall.com",
  "password": "admin",
  "phone": "1234567788"
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
  "status": 11,
  "msg": "USER EXISTS"
}
```

#### 3.Get Current User Information

**GET /user**

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
    "id": 1,
    "username": "ADMIN",
    "email": "admin@watchmall.com",
    "phone": "1234567889",
    "role": 1
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

-------

#### 4.Logout

**POST /user/logout**

> request

```text
No arguments required
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
    "status": -1,
    "msg": "INTERNAL ERROR"
}
```
