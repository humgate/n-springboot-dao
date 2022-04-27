## RESTful application example implementing querying postgreSQL database
Exposes one GET endpoint at /products/fetch-product with query parameter `name`.<br>
To test go to http://localhost:8080/products/fetch-product?name=alexey.

The database initialization done via:
- liquibase migration using yaml and xml datachangelog scripts (master branch)
- liquibase migration using yaml and sql datachangelog scripts (feature/liquibase-migration-sql branch)
- springboot JDBS initialization (feature/springbood-db-init)
