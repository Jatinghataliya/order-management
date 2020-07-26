# Order Management
Order management is microservice architecture based application.
Following are the microservices.

* <a> Order Service </a>
* <a>Order Item Service</a>

  ## Order Service
  Order service used to create the order, retrieve all order & particular order
  ```
    * http://localhost:8383/order-service/orders 
    * http://localhost:8383/order-service/orders/{orderid}
    * http://localhost:8383/order-service/orders
   ```
  ## Order Item Service
  Order item service contains the all ordered item information with respect to particular order
