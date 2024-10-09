-- Таблиця для путівок
CREATE TABLE IF NOT EXISTS tours (
    id SERIAL PRIMARY KEY,
    country VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

-- Таблиця для менеджерів
CREATE TABLE IF NOT EXISTS managers (
    id SERIAL PRIMARY KEY,
    agency_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

-- Таблиця для кліентов
CREATE TABLE IF NOT EXISTS clients (
    id SERIAL PRIMARY KEY,
    agency_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    tour_id INT REFERENCES tours(id) ON DELETE SET NULL
);