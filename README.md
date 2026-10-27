# CeceWash --- Laundry Management System

A simple laundry management web application built with Java and Spring
Boot as a university final project.

## Overview

CeceWash is a CRUD-based application for managing laundry orders. The
project includes a MySQL database schema for storing customer and order
information.

> **Note:** The exact features available in the user interface depend on
> the current source-code implementation. Update this README if your
> implementation differs from the descriptions below.

## Technology Stack

-   **Language:** Java 17
-   **Framework:** Spring Boot 4.1.0
-   **Web:** Spring Boot Web MVC
-   **Template Engine:** Thymeleaf
-   **Persistence:** Spring Data JPA
-   **Database:** MySQL
-   **Validation:** Spring Boot Validation
-   **Build Tool:** Maven

## Features

The database schema is prepared to store:

-   Customer name
-   Phone number
-   Laundry service type
-   Laundry weight
-   Total price
-   Order status

The project is intended for basic laundry-order CRUD operations. Confirm
which create, read, update, and delete actions are implemented in the
application before presenting them as completed features.

## Project Structure

``` text
cecewash/
├── .mvn/
│   └── wrapper/
├── src/
├── .gitattributes
├── .gitignore
├── database.sql
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Prerequisites

Install the following before running the project:

-   Java Development Kit (JDK) 17
-   MySQL Server
-   Git (to clone the repository)

## Getting Started

### 1. Clone the repository

``` bash
git clone https://github.com/Chintya-Wihardiantami/cecewash.git
cd cecewash
```

### 2. Create the database

Run the SQL script included in the repository:

``` bash
mysql -u YOUR_MYSQL_USERNAME -p < database.sql
```

Replace `YOUR_MYSQL_USERNAME` with your MySQL username. The script
creates the `cecewash` database, creates the `pesanan_laundry` table if
it does not exist, and inserts a sample order.

Alternatively, open `database.sql` in your preferred MySQL client and
execute it.

### 3. Configure the database connection

Check the application's configuration files under `src/main/resources`
and configure the datasource to match your local MySQL setup.

A typical Spring datasource configuration looks like this:

``` properties
spring.datasource.url=jdbc:mysql://localhost:3306/cecewash
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Use environment variables for credentials where possible. Do not commit
real database passwords or other secrets to the repository.

### 4. Run the application

On Windows, use the Maven wrapper:

``` bat
mvnw.cmd spring-boot:run
```

On macOS or Linux:

``` bash
./mvnw spring-boot:run
```

If the application does not start, check that Java and MySQL are
running, the database credentials are correct, and the datasource
settings match your local environment.

### 5. Run tests

On Windows:

``` bat
mvnw.cmd test
```

On macOS or Linux:

``` bash
./mvnw test
```

## Database Schema

The `database.sql` script defines the `pesanan_laundry` table with these
columns:

  Column             Type             Description
  ------------------ ---------------- ----------------------------------
  `id`               `BIGINT`         Auto-incrementing primary key
  `nama_pelanggan`   `VARCHAR(255)`   Customer name
  `nomor_hp`         `VARCHAR(15)`    Customer phone number
  `jenis_layanan`    `VARCHAR(100)`   Laundry service type
  `berat`            `DOUBLE`         Laundry weight
  `total_harga`      `INT`            Total price
  `status`           `VARCHAR(50)`    Order status; defaults to `BARU`

The SQL script also inserts one sample order for testing.

## Screenshots

Add screenshots of the running application here after verifying the
current interface. For example:

-   Order list page
-   Create or edit order form
-   Order details or status page

## Possible Future Improvements

-   Add clearer validation and error messages.
-   Improve responsive layout and accessibility.
-   Add automated tests for order-management workflows.
-   Add screenshots and a short demonstration of the application.

## Author

**Chintya Wihardiantami**

GitHub:
[Chintya-Wihardiantami](https://github.com/Chintya-Wihardiantami)

------------------------------------------------------------------------

Academic project created for a university final assignment.
