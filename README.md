# Hibernate Trace Example
## Description 
The configurations shown in this repository can be used to trace the operations performed by hibernate under the hood. This is especially useful when:
- there are suspects of performance issues while querying or updating data in the db
- you want to check what jpa is doing and if the operations performed are optimal compared to your performance constraints
- you need to debug transactions locks

The repository contains: 
- a sample Spring Boot command line application
- a docker compose file to start a test database
- the sql scripts needed to initialize the database

The repository contains two branches:
- postgresql branch
- oracle-db branch 

Depending on the branch you chose, the application configuration, the sql scripts and the docker compose file will contain all the needed configuration to support the chosen database. 

## Hibernet Configuration Overview
### Log the executed statements

```
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
The first property tells to hibernate to log all the statements resulting from the transformation of the high-level operations performed by the application. The latter makes those statements more readable instead of having them on a plain line.
For example: 
```sql
Hibernate: 
    insert 
    into
        contacts
        (phone_number, owned_by, id) 
    values
        (?, ?, ?)
```

### Trace the performance of the statements
```
spring.jpa.properties.hibernate.generate_statistics=true
```
With this property set, for each statement executed a set of useful information regarding the execution will be logged. 
For example: 
```log
2023-02-08T18:41:24.877Z  INFO 9414 --- [           main] i.StatisticalLoggingSessionEventListener : Session Metrics {
    13930 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    166660 nanoseconds spent preparing 2 JDBC statements;
    1240720 nanoseconds spent executing 2 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    15580 nanoseconds spent executing 1 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```
### Trace jdbc transactions
```
logging.level.org.springframework.transaction.interceptor = TRACE
```
Doing so, a log line will be printed when a transaction is created and completed.


## How to Run the example
To run the example simply start the container and run the application. 
```
docker compose up -d
mvn spring-boot:run
```
And in the application logs you'll see something like: 
```log
2023-02-08T18:41:24.811Z TRACE 9414 --- [           main] o.s.t.i.TransactionInterceptor           : Getting transaction for [org.springframework.data.jpa.repository.support.SimpleJpaRepository.findAllById]
Hibernate: 
    select
        u1_0.id,
        u1_0.user_type,
        u1_0.username 
    from
        users u1_0 
    where
        u1_0.id in(?)
Hibernate: 
    select
        c1_0.owned_by,
        c1_0.id,
        c1_0.phone_number 
    from
        contacts c1_0 
    where
        c1_0.owned_by=?
2023-02-08T18:41:24.876Z TRACE 9414 --- [           main] o.s.t.i.TransactionInterceptor           : Completing transaction for [org.springframework.data.jpa.repository.support.SimpleJpaRepository.findAllById]
2023-02-08T18:41:24.877Z  INFO 9414 --- [           main] i.StatisticalLoggingSessionEventListener : Session Metrics {
    13930 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    166660 nanoseconds spent preparing 2 JDBC statements;
    1240720 nanoseconds spent executing 2 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    15580 nanoseconds spent executing 1 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

