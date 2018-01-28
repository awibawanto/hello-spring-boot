# hello-spring-boot
This is demo project for learning Spring Boot

# How to run
Run the application from the command line with Maven. Go to the root folder of the application and type:
```
./mvnw spring-boot:run
```
Application should running within a few seconds.
Go to the web browser and visit `http://localhost:8080/products`
- Admin : admin/admin
- User : user/password

You can check configuration detail in `/src/main/resources/application.properties`

# Basic function
- List products `http://localhost:8080/products`
- Add product to shopping cart `http://localhost:8080/cart/addProduct/{productId}`
- Remove product from shopping cart `http://localhost:8080/cart/removeProduct/{productId}`
- Check shopping cart `http://localhost:8080/cart`
- Checkout shopping cart `http://localhost:8080/cart/checkout`

During checkout total amount will be deducted from user credit. User credit is predefined in `/src/main/resources/sql/import-h2.sql`

All REST mappings are intentionally set as GET method for easier testing purposes.
