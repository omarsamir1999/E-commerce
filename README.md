
## E-Commerce Microservices Project

Welcome to the E-Commerce Microservices project, a culmination of our journey at JobHacker training. This project represents our collective effort to design and implement a robust and scalable e-commerce system consisting of seven distinct microservices: **User**, **Store**, **Product**, **Order**, **Coupon**, **Notification**, and **Bank**. Leveraging the power of Spring Boot and various best practices, we have developed a versatile and efficient platform to meet the demands of modern e-commerce.

### Microservices Overview

**User Service**:
- Manage user accounts, including administrators.
- Provide user authentication and authorization.
- Maintain user-related operations.

**Store Service**:
- Create and manage stores/warehouses.
- Enable searching for products in stores.
- Track product stock and consumption history.

**Product Service**:
- Retrieve, add, update, and delete products.
- Categorize and brand products.
- Maintain comprehensive product records.

**Order Service**:
- Create and manage customer orders.
- Validate and consume coupons.
- Handle stock management.
- Facilitate financial transactions via the Bank service.
- Send order notifications to relevant parties.

**Coupon Service**:
- Create and manage coupons.
- Control coupon usage and expiration.
- Record coupon consumption history.
- Provide detailed information on available coupons.

**Notification Service**:
- Listen for order notifications.
- Send notifications to customers.

**Bank Service**:
- Manage user accounts with card information.
- Handle financial transactions, including deposits and withdrawals.
- Log transaction details.
- Facilitate account balance inquiries.


### Entity-Relationship Diagram (ERD)

For meticulous project organization and a deeper understanding of our microservices architecture, we have dedicated individual branches for each service. In these branches, you will find the Entity-Relationship Diagram (ERD) that illustrates the data model and relationships specific to each service. Below are the branches and links to access the ERDs:

- User Service: [User Service](https://github.com/omarsamir1999/E-commerce/tree/user-service)

- Store Service: [Store Service](https://github.com/omarsamir1999/E-commerce/tree/store-service)

- Product Service: [Product Service](https://github.com/omarsamir1999/E-commerce/tree/Product-Service)

- Order Service: [Order Service](https://github.com/omarsamir1999/E-commerce/tree/order-service)

- Coupon Service: [Coupon Service](https://github.com/omarsamir1999/E-commerce/tree/coupon-service)

- Notification Service: [Notification Service](https://github.com/omarsamir1999/E-commerce/tree/notification_service)

- Bank Service: [Bank Service](https://github.com/omarsamir1999/E-commerce/tree/bank-service)


### Implementation Goals

- Utilizing clean architecture principles, separating components into layers such as controller, service, repository, DTO, mapper, entity, exception, and security.
- Implement an API design that follows industry best practices for URLs and CRUD operations.
- Employing Postman for thorough API testing.
- Utilize MapStruct for streamlined data mapping.
- Applying Spring Validator for data validation.
- Usign Liquibase for database schema management.
- Implementing RabbitMQ for message queuing.
- Enhanceing project documentation with Swagger.

### Collaborators

- [Mohamed Elkohly](https://github.com/Mohamed-Elkholy)
- [Amira Taha](https://github.com/amira921)
- [Moaz Suliman](https://github.com/MoaazSuliman)
- [Amir Elkased](https://github.com/amirelkased)
- [Omar Samir](https://github.com/omarsamir1999)
- [Mohamed Gamal](https://github.com/Mohamed-Harby)
- [Mohamed Harby](https://github.com/Mohamed-Harby)












