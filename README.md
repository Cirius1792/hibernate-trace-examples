# Hibernate Trace Example
## Description 
The configurations shown in this repository can be used to trace the operations performed by hybernate under the hood. This is expecially useful when:
- there are suspects of performance issues while querying or updating data in the db
- you want to check what jpa is doing and if the operations performed are optimal compared to your performance contraints
- you need to debug transactions locks

The repository contains: 
- a sample Spring Boot command line application
- a docker compose file to start a test database
- the sql scripts needed to initialize the databse

The repository contains two branches:
- postgresql branch
- oracle-db branch 

Depending on the branch you chose, the application configuration, the sql scripts and the docker compose file will contain all the needed configuration to support the chosen database. 

## Hibernet Configuration Overview

## How to Run the example