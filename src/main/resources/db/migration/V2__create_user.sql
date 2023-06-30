CREATE TABLE IF NOT EXISTS sport_club.users(
    id INTEGER CONSTRAINT user_id_pk PRIMARY KEY,
    phone_number VARCHAR(16) NOT NULL,
    firstname VARCHAR(32),
    lastname VARCHAR(32),
    CONSTRAINT phone_number UNIQUE(phone_number)
)