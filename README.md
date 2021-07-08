# WatchMall

This is a demo project based on Spring framework, which implements an online watch store. The fundamental tech stack of this project is SpringBoot + Spring Data JPA + Hibernate + Redis.

### Highlights

+ Uniformly formatted front-end friendly response in json format, which only contains two fields, status code and data.
+ Global exception handling, which ensures that all abnormal or error situations lead to stable, manageable responses.
+ Redis based cart implementation. Reduce R&W pressure from databases. Open to further extensions, like hot spot tracking. 
+ Simplified snowflake algorithm for unique ID generation.
+ Fine-grained class-level and method-level user authorization with custom annotation.

### Functionalities

✅&nbsp; user login/logout/registration\
✅&nbsp; list product categories\
✅&nbsp; list products / product details\
✅&nbsp; filter products by attributes\
✅&nbsp; adding items to cart / checkout from cart\
✅&nbsp; place order / cancel order / make payments

### API Reference

[user](./api/user.md)\
[category](./api/category.md)\
[attribute](./api/attribute.md)\
[product](./api/product.md)\
[shipping](./api/shipping.md)\
[cart](./api/cart.md)\
[order](./api/order.md)

### Database Design