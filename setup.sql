DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INTEGER NOT NULL,
    category VARCHAR(50) NOT NULL
);

INSERT INTO products (name, description, price, stock_quantity, category) 
VALUES 
    ('HB Pencil', 'Standard wooden pencil', 0.50, 200, 'Stationery'),
    ('Lays Classic', 'Salted potato chips', 2.99, 50, 'Snacks'),
    ('Coca-Cola Zero', 'No sugar cola 330ml', 1.20, 100, 'Beverages'),
    ('USB-C Cable', '1m fast charging', 9.99, 30, 'Electronics');