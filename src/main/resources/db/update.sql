CREATE TABLE IF NOT EXISTS passport (
id serial PRIMARY KEY,
series int,
number int,
expiration_date timestamp,
UNIQUE(series, number)
);

