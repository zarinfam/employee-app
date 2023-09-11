CREATE TABLE IF NOT EXISTS salary (
   id SERIAL PRIMARY KEY,
   employee_id BIGINT NOT Null,
   salary float8 NOT NULL
);
