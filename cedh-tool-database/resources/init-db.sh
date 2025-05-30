#!/bin/bash
set -e

# Use the first argument as SQL file path, default to /tmp/schema.sql
SQL_FILE="${1:-/tmp/schema.sql}"

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --password "$POSTGRES_PASSWORD" --dbname "$POSTGRES_DB" <<-EOSQL
  -- create user cedh with password;
  CREATE USER cedh WITH PASSWORD 'cedh';
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --password "$POSTGRES_PASSWORD" --dbname "$POSTGRES_DB" -f "$SQL_FILE"
