# warehouseJPA:   Spring Boot + JPA (MySQL) + Mustache
Entities:
  - Client (long id, String name, String address)
  - Product (long id, String type, String model, double price)
  - Purchase (long id, long cl_id, long prod_id, int amount)
  - Warehouse (long id, long prod_id, int amount)
  
 
