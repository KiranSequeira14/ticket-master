# All Commands

### Redis Commands
```bash
### List all keys in the current database
KEYS *

### Get the value of a specific key
GET <key>

### Check the Time-to-Live (TTL) of a specific key
TTL <key>

### Select a different Redis database (default is 0-15)
SELECT <db_index>

### Get all keys matching a specific pattern
KEYS <pattern>
```
### Docker Commands
```bash
### List all running containers
docker ps

### Run a Redis container and expose port 6379
docker run -p 6379:6379 --name my-redis -d redis

### Access the Redis CLI inside the Redis container
docker exec -it <container_id_or_name> redis-cli

### Stop a running container
docker stop <container_id_or_name>

### Remove a stopped container
docker rm <container_id_or_name>

### List all images
docker images

### Remove an image
docker rmi <image_id>
```
## PostgreSQL Commands
```bash
### Login to PostgreSQL
psql -U <username> -d <database>

### List all databases
\l

### List all tables in the current database
\dt

### Describe the structure of a specific table
\d <table_name>

### Execute a SQL query to select all data from a table
SELECT * FROM <table_name>;

### Create a new database
CREATE DATABASE <database_name>;

### Create a new table
CREATE TABLE <table_name> (
id SERIAL PRIMARY KEY,
name VARCHAR(100),
age INT
);

### Exit PostgreSQL
\q
```