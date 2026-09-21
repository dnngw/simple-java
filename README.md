# Simple Java

A simple java CRUD program based on CLI

## Features

- CRUD
- Auto-runs database migrations on startup

## Prerequisites

- **Java 17+**
- **PostgreSQL**

## Project Structure

```
simple-java/
├── lib/
│   └── postgresql-42.7.13.jar    # PostgreSQL JDBC driver
├── src/
│   ├── Main.java                 # Entry point
│   ├── application.properties
│   ├── model/
│   │   └── Task.java             # Entity
│   ├── dao/
│   │   ├── TaskDAO.java          # DAO interface
│   │   └── impl/
│   │       └── TaskJdbcDAO.java  # JDBC implementation
│   ├── service/
│   │   ├── TaskService.java      # Service interface
│   │   └── impl/
│   │       └── TaskServiceImpl.java
│   ├── db/
│   │   ├── MigrationRunner.java  # Auto-migration runner
│   │   └── migrations/
│   │       └── 202609151037_init.sql
|   |       |__ etc 
│   └── util/
│       ├── DBConfig.java         # Reads application.properties
│       └── DBConnection.java     # JDBC connection
└── README.md
```


## Database Migrations

Migrations live in `src/db/migrations/` and are tracked in a `schema_migrations` table. Files are executed in order based on their filename (timestamp prefix `YYYYMMDDHHmm_description`).

