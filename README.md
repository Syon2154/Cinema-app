# Hi, pour a coffee before reading ツ

## Contents
+ [Description](#description)
+ [Features for Admin role](#features-for-admin-role)
+ [Features for User role](#features-for-user-role)
+ [Technologies stack](#technologies)
+ [How to run project](#how-to-run-project)

<a name="description"></a>
## Description (⌐■_■)
This application is a simple implementation of the cinema functionality, where you can buy tickets for movies that are screenings in a particular room. Built on popular Java technologies such as Spring and Hibernate. Inlcludes authentication/authorization, REST and global expection handler.

<a name="features-for-admin-role"></a>
## **Features for Admin role**
- Registration of a user (`POST: /register`)
- Viewing: 
     - cinema halls (`GET: /cinema-halls`)
     - movies (`GET: /movies`)
     - available movie sessions (`GET: /movie-sessions/available`)
     - some specific movie session (`GET: /movie-sessions/{id}`)
- Add new: 
     - cinema halls (`POST: /cinema-halls`)
     - movies (`POST: /movies`)
     - movie sessions (`POST: /movie-sessions`)
- Update and delete movie session (`PUT: /movie-sessions/{id}`, `DELETE: /movie-sessions/{id}`)
- Get some user by email (`GET: /users/by-email`)
- Logout (`GET: /logout`)

<a name="features-for-user-role"></a>
## Features for User role
- Viewing:
  - cinema halls (`GET: /cinema-halls`)
  - movies (`GET: /movies`)
  - available movie sessions (`GET: /movie-sessions/available`)
  - some specific movie session (`GET: /movie-sessions/{id}`)
- Get orders and shopping carts (`GET: /orders`, `GET: /shopping-carts/by-user`)
- Complete orders (`POST: /orders/complete`) 
- Add tickets  to shopping cart for some movie session (`PUT: /shopping-carts/movie-sessions`)
- Logout (`GET: /logout`)

<a name="technologies"></a>
## **Technologies stack**
- Java 11
- Hibernate
- Spring (Spring Core, Spring Web, Spring Security)
- MySQL
- Tomcat

## How to run project
1. Download and install Java 11 SDK.
2. Download and install Tomcat (version 9.0.63).
3. Download and install MySQL.
4. Create schema (`cinema`)
5. Clone it to your IDE.
6. Add your username and password from database in `db.properties` file.
7. Use Postman to send requests.

There is two injected profiles you can use to test application:
+ Admin with email `admin@i.ua` and password `12345`
+ User with email `user@i.ua` and password `12345`
