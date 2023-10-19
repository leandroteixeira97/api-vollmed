CREATE TABLE patients(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    street VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    zipCode VARCHAR(9) NOT NULL,
    complement VARCHAR(100),
    number VARCHAR(20),
    state CHAR(2) NOT NULL,
    city VARCHAR(100) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    expired BOOLEAN NOT NULL DEFAULT false,
    PRIMARY KEY(id)
);