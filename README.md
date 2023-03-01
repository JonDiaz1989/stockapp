# StockApp

StockApp is an application that allows to store new product, list all of them, 
retrieve a product by its SKU, update it and delete it..

## Installation

Use Dockers to set up a database as shown below:

```bash
docker run --detach --env MYSQL_ROOT_PASSWORD=1234567 --env MYSQL_USER=stockapp-user --env MYSQL_PASSWORD=1234567 --env MYSQL_DATABASE=stockapp-database --name mysqlstockapp --publish 3306:3306 mysql:8-oracle
62e5e59877c27bd7d2462371154c59ba4d764cc7eaf81afca90db75aae7aa165
```


Use Maven commands to install StockApp, as example below:

```bash
mvn clean install
```
To change Database target, there is a file into the folder \stockapp\src\main\resources\application.properties and modify the following parameters:

```
spring.datasource.url=jdbc:mysql://localhost:3306/stockapp-database
spring.datasource.username=stockapp-user
spring.datasource.password=1234567

```

## Endpoints and testing the app
The application has 5 rest services that allows to: save, modify, list, list by SKU and delete, examples from postman will be shown below:


# Save Method
```python
POST, URL: http://localhost:8080/v1/stock/saveProduct

Body

    {
        "SKU": "FAL-8406279",
        "Name": "500 Zapatilla Urbana Mujer 1",
        "Brand": "New Balance",
        "Size": "37",
        "Price": "99999999.00",
        "PrincipalUrl": "https://falabella.scene7.com/is/image/Falabella/8406270_1",
        "aditional_images": [
    "https://falabella.scene7.com/is/image/Falabella/8406270_2",
    "https://falabella.scene7.com/is/image/Falabella/8406270_3",
    "https://falabella.scene7.com/is/image/Falabella/8406270_4"
  							]
    }
```

# Modify Method
```python

PUT, URL: http://localhost:8080/v1/stock/editProduct/{id}

URL Example: http://localhost:8080/v1/stock/editProduct/1

Body

    {
        "SKU": "FAL-8406279",
        "Name": "500 Zapatilla Urbana Mujer 1",
        "Brand": "New Balance",
        "Size": "S",
        "Price": "99999999.00",
        "PrincipalUrl": "https://falabella.scene7.com/is/image/Falabella/8406270_1",
        "aditional_images": [
    "https://falabella.scene7.com/is/image/Falabella/8406270_2",
    "https://falabella.scene7.com/is/image/Falabella/8406270_3",
    "https://falabella.scene7.com/is/image/Falabella/8406270_4"
  							]
    }
```

# Delete Method
```python

DELETE, URL: http://localhost:8080/v1/stock/deleteProduct/{id}

URL Example: http://localhost:8080/v1/stock/editProduct/1

Body

    {
        "SKU": "FAL-8406279",
        "Name": "500 Zapatilla Urbana Mujer 1",
        "Brand": "New Balance",
        "Size": "37",
        "Price": "99999999.00",
        "PrincipalUrl": "https://falabella.scene7.com/is/image/Falabella/8406270_1",
        "aditional_images": [
    "https://falabella.scene7.com/is/image/Falabella/8406270_2",
    "https://falabella.scene7.com/is/image/Falabella/8406270_3",
    "https://falabella.scene7.com/is/image/Falabella/8406270_4"
  							]
    }
```
#  List by SKU Method
```python

POST, URL: http://localhost:8080/v1/stock/getProductbySKU/{sku}

URL Example: http://localhost:8080/v1/stock/getProductbySKU/FAL-8406276

```

# List All Products
 ```python

GET, URL: http://localhost:8080/v1/stock/listProducts

```


## Technical Details

Stockapp has been build with Springboot (V 2.7.0) and Java 11, Maven. Database its MySQL Version 8.

Architecture its Restfull API.

The application has hibernate and JPA for persistance, and the language of the services its JSON.

All validations are set into the entities with error messages as was requested.

