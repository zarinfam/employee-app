CREATE TABLE IF NOT EXISTS employee (
   id SERIAL PRIMARY KEY,
   full_name VARCHAR(255) NOT NULL,
   salary float8 NOT NULL
);
