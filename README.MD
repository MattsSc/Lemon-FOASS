# Lemon-FOASS

## Description
Create an application that consumes [FOAAS](http://www.foaas.com/) and has a limit of 5 request every 10 seconds for each user.

## Stack
* Java 17
* Spring boot
* Redis
* Maven
* Docker

## Start application
If you have docker you can build the image : 

```
docker build -t lemoncash:v1 .     
```

and then run docker-compose:
```
docker-compose up --build
```

In this way you are going to have redis and the application running in local. To use the application just follow the

## How to use
The only service available is: http://localhost:8080/api/lemon-fo/message

### Parameters
* Header: _Authorization_ with any value, is going to be used to determine the user and limit the requests.
* Query params: company, name (are optionals)
