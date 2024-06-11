# Day 1 Lab

-- Question 1

CREATE TABLE Customers (
    CustomerID SERIAL PRIMARY KEY,
    Name VARCHAR(255),
    Email VARCHAR(255),
    RegistrationDate DATE
);


-- Question 2

CREATE TABLE Products (
    ProductID SERIAL PRIMARY KEY,
    Name VARCHAR(255),
    Price DECIMAL(5,2),
    Category VARCHAR(100),
    Stock INTEGER
);


-- Question 3

CREATE TABLE Orders (
    OrderID SERIAL PRIMARY KEY,
    CustomerID INTEGER,
    ProductID INTEGER,
    OrderDate DATE,
    Quantity INTEGER,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
)

## Tasks

### Basic CRUD Operations

-- 1

INSERT INTO Customers (Name, Email, RegistrationDate) VALUES ( 'Alex Turner', 'alex.turner@gmail.com', CURRENT_DATE);

-- 2

SELECT * FROM products
WHERE category = 'Electronics'

-- 3

UPDATE products
SET stock = 35
WHERE name = 'Espresso Machine'

-- 4

-- To identify who haven't placed an order yet, it was identifyed that people with customerID of 11 and beyond have not placed an order yet.
SELECT DISTINCT customerid from orders

--Actual deletion, deleting Susan Jones
DELETE FROM customers
WHERE customerid = 17

### Intermediate Read Options

-- 1

SELECT * from orders
WHERE EXTRACT(MONTH FROM orderdate) = 2

-- 2

SELECT COUNT(registrationdate) from customers 
WHERE registrationdate < '2023-01-10'

-- 3

SELECT * FROM products
WHERE stock BETWEEN 50 AND 100

-- 4

SELECT * FROM customers
WHERE email LIKE '%gmail.com'

--5

SELECT COUNT(email) FROM customers
WHERE email LIKE '%gmail.com'

#### Advanced Filtering and Aggregation

-- 1

SELECT category FROM products
WHERE stock < 2

-- 2

SELECT * FROM products
WHERE stock > 5

-- 3

SELECT COUNT(productid) FROM orders
WHERE quantity > 2

-- 4

UPDATE products
SET stock = stock -5
WHERE category = 'Books'

-- 5

DELETE FROM orders
WHERE EXTRACT(MONTH FROM orderdate) = 2
AND quantity = 1

-- 6

SELECT category, SUM(stock) AS TotalStock from products
WHERE stock > 20
GROUP BY category

-- 7

SELECT name, email FROM customers
LEFT JOIN orders
ON orders.customerid = customers.customerid
GROUP BY customers.customerid, customers.name
HAVING COUNT(orders.customerid) > 1