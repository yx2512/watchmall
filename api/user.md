#### 1.Login

**POST /user/login**

> request

Content-Type: application/json

```
{
	"username":"admin",
	"password":"admin",
}
```
> response

fail
```
{
    "status": 1,
    "msg": "INVALID EMAIL OR PASSWORD"
}
```

success
```
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


-------

#### 2.Register
**POST /user/register**

> request

Content-Type: application/json

```
{
	"username":"admin",
	"email":"admin@watchmall.com"
	"password":"admin",
	"phone":"1234567788"
}
```


> response

success
```
{
    "status": 0
}
```


fail
```
{
    "status": 2,
    "msg": "USER EXISTS"
}
```


#### 3.Get Current User Information
**GET /user**

> request

```
No arguments required
```
> response

success
```
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
```
{
    "status": 4,
    "msg": "PLEASE LOGIN FIRST"
}

```

------


#### 4.Logout
**POST /user/logout**

> request

```
No argument required
```

> response

success

```
{
    "status": 0,
    "msg": "退出成功"
}
```

fail
```
{
    "status": -1,
    "msg": "INTERNAL ERROR"
}
```

