# Day 2 HW

## Create Table

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
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Inserted Data

## Basic Exercises

-- Question 1
SELECT * FROM Customers
JOIN Orders ON Customers.CustomerID = Orders.CustomerID;

-- Question 2
SELECT products.Name, quantity FROM orders
JOIN Products on Orders.productid = Products.productid


-- Question 3
SELECT Customers.Name AS CustomerName, Products.Name AS ProductName, Orders.*
FROM Orders
JOIN Customers ON Orders.CustomerID = Customers.CustomerID
JOIN Products ON Orders.ProductID = Products.ProductID;

-- Question 4
SELECT Customers.Name AS CustomerName, Products.Name AS ProductName, Orders.*
FROM Orders
JOIN Customers ON Orders.CustomerID = Customers.CustomerID
JOIN Products ON Orders.ProductID = Products.ProductID
WHERE Customers.Name = 'John Doe'

-- Question 5
SELECT Customers.Name AS CustomerName, COUNT(Orders.OrderID) AS TotalOrders
FROM Customers
LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID
GROUP BY Customers.Name;

-- Question 6
SELECT Customers.Name AS CustomerName, MAX(Products.Price) AS HighestOrderAmount
FROM Customers
LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID
LEFT JOIN Products ON Orders.ProductID = Products.ProductID
GROUP BY Customers.Name;

-- Question 7
SELECT Orders.OrderID, Customers.Name AS CustomerName, Products.Name AS ProductName, Products.Price
FROM Orders
JOIN Customers ON Orders.CustomerID = Customers.CustomerID
JOIN Products ON Orders.ProductID = Products.ProductID
WHERE Products.Price > 500;

-- Question 8
SELECT Products.Category, COUNT(Orders.ProductID) AS NumOfOrders
FROM Orders
JOIN Products ON Orders.ProductID = Products.ProductID
GROUP BY Products.Category;

-- Question 9
SELECT Products.Name AS ProductName, Orders.OrderDate, Orders.Quantity
FROM Orders
JOIN Products ON Orders.ProductID = Products.ProductID
WHERE EXTRACT(YEAR FROM Orders.OrderDate) = 2023
AND EXTRACT(MONTH FROM Orders.OrderDate) = 12;

## Advanced

-- Question 1
SELECT Customers.CustomerID, Customers.Name AS CustomerName, Orders.OrderID
FROM Customers
LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID;

--Question 2
SELECT Orders.OrderID, Customers.Name AS CustomerName
FROM Orders
RIGHT JOIN Customers ON Orders.CustomerID = Customers.CustomerID;

-- Question 3
SELECT Customers.CustomerID, Customers.Name AS CustomerName, Orders.OrderID
FROM Customers
FULL JOIN Orders ON Customers.CustomerID = Orders.CustomerID;

-- Question 4
SELECT DISTINCT c1.Name AS Customer1, c2.Name AS Customer2
FROM Customers c1
JOIN Customers c2 ON SUBSTRING(c1.Email FROM POSITION('@' IN c1.Email) + 1) = SUBSTRING(c2.Email FROM POSITION('@' IN c2.Email) + 1)
WHERE c1.CustomerID < c2.CustomerID;

-- Question 5
SELECT Customers.*
FROM Customers
LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID
WHERE Orders.CustomerID IS NULL;