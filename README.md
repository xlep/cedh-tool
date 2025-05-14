# cedh-tool



## Setup database
*Requirements:* docker & docker compose

```
docker compose up
```
Creates a local postgres database on port 5432 with the tables defined in ./cedh-tool-database/resources/cedh-tool.sql
Also creates a web-based backend for the database avaiable under http://locaalhost:8081

## IDE Setup
We're using IntelliJ with the following adjustments

Necessary plugins:
* google-java-format

### Code style
We're using the 'google-java-format' plugin for code style / formatting.
