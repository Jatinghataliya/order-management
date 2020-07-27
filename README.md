# Order Management
Order management is microservice architecture based application.
Following are the microservices.

* <a> Order Service </a>
* <a>Order Item Service</a>

  ## Order Service
  Order service used to create the order, retrieve all order & particular order
  ```
    1. http://localhost:8383/order-service/orders (POST Method)
    2. http://localhost:8383/order-service/orders/{orderid} (GET Method)
    3. http://localhost:8383/order-service/orders (GET Method)
    4. http://localhost:8383/swagger-ui.html (Swagger UI)
    5. http://localhost:8383/actuator (Actuator Endpoints)
   ```
  ## Order Item Service
  Order item service contains the all ordered item information with respect to particular order
  ```
    1. http://localhost:8282/order-item-service/order-items (POST Method)
    2. http://localhost:8282/order-item-service/order-items/{orderitemid} (GET Method)
    3. http://localhost:8282/order-item-service/order-items/orders/{orderid} (GET Method)
    4. http://localhost:8282/order-item-service/order-items (GET Method)
    5. http://localhost:8282/swagger-ui.html (Swagger UI)
    5. http://localhost:8282/actuator (Actuator Endpoints)
   ```
