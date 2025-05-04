# cedh-tool



## Setup database
*Requirements:* docker & docker compose

```
docker compose up
```
Creates a local postgres database on port 5432 with the tables defined in ./cedh-tool-database/resources/cedh-tool.sql
Also creates a web-based backend for the database avaiable under http://locaalhost:8081

