``` java
	@Test
        void contextLoads() {
    }
```
- requires source properties for Spring Data + active database connection else will throw `IllegalState Failed to load ApplicationContext for WebMergedContextConfiguration@b3e86d5 ...`
- https://www.baeldung.com/spring-junit-failed-to-load-applicationcontext
- https://www.jvt.me/posts/2022/03/10/spring-failed-applicationcontext/


Creating Users
``` sql
CREATE USER 'use_database_tester'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON use_database.* TO 'use_database_tester'@'localhost';
```


https://www.javadoc.io/doc/org.flywaydb/flyway-core/7.0.0/org/flywaydb/core/Flyway.html
API from which all important Flyway functions such as clean, validate and migrate can be called.

Naming Convention For Migration Scripts:
<Prefix><Version>__<Description>.sql
V1__create_table.sql