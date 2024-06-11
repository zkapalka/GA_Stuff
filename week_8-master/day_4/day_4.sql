-- Create Department table
CREATE TABLE Department (
    DepartmentID SERIAL PRIMARY KEY,
    DepartmentName VARCHAR(255)
);

-- Create Project table
CREATE TABLE Project (
    ProjectID SERIAL PRIMARY KEY,
    ProjectName VARCHAR(255),
	SkillNeeded INT,
    FOREIGN KEY (SkillNeeded) REFERENCES Skill(Skill_ID)
);

-- Create Employee table
CREATE TABLE Employee (
    EmployeeID SERIAL PRIMARY KEY,
    FullName VARCHAR(255),
    DepartmentID INT,
  	FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID)
);

-- Create Skill table
CREATE TABLE Skill (
    Skill_ID SERIAL PRIMARY KEY,
    SkillName VARCHAR(255)
);

-- Create EmployeeDetail table
CREATE TABLE EmployeeDetail (
    Entry SERIAL PRIMARY KEY,
    EmployeeID INT,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
    Address VARCHAR(255),
    PhoneNumber VARCHAR(20),
    LastFourSSN VARCHAR(4),
    SkillID INT,
    FOREIGN KEY (SkillID) REFERENCES Skill(Skill_ID),
    SkillProficiency VARCHAR(50),
    EmployeeSalary DECIMAL(10, 2)
);


-- Create Assignment table
CREATE TABLE Assignment (
    EmployeeID INT,
    ProjectID INT,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (ProjectID) REFERENCES Project(ProjectID)
);


-- Insert into Department table
INSERT INTO Department (DepartmentName) VALUES
    ('Human Resources'),
    ('Finance'),
    ('Marketing'),
    ('IT'),
    ('Operations');
	
-- Insert into Employee table
INSERT INTO Employee (FullName, DepartmentID) VALUES
    ('John Doe', 1),
    ('Jane Smith', 2),
    ('Michael Johnson', 3),
    ('Emily Brown', 4),
    ('David Lee', 5);
	
-- Insert into Skill table
INSERT INTO Skill (SkillName) VALUES
    ('Programming'),
    ('Finance'),
    ('Marketing'),
    ('Web Development'),
    ('Operations Management');

INSERT INTO EmployeeDetail (EmployeeID, Address, PhoneNumber, LastFourSSN, SkillID, SkillProficiency, EmployeeSalary) VALUES
    (1, '123 Main St', '555-1234', '5678', 4, 'Intermediate', 60000.00),
    (2, '456 Elm St', '555-5678', '9012', 2, 'Expert', 80000.00),
    (3, '789 Oak St', '555-9012', '3456', 1, 'Beginner', 55000.00),
    (4, '101 Pine St', '555-3456', '7890', 3, 'Advanced', 70000.00),
    (5, '202 Maple St', '555-7890', '1234', 5, 'Intermediate', 65000.00);
	
INSERT INTO Assignment (EmployeeID, projectID) VALUES
    (1, 2),
    (2, 2),
    (3, 4),
    (4, 1),
    (5, 3);

INSERT INTO Project (ProjectName, SkillNeeded) VALUES
    ('Website Redesign', 1),
    ('Database Migration', 3),
    ('Mobile App Development', 2),
    ('Data Analysis', 4),
    ('Marketing Campaign', 5);
	


-- Join Operations
-- #1
--This allows you to select all employees from Human Resources
SELECT Employee.EmployeeID, Employee.FullName
FROM Employee
JOIN Department ON Employee.DepartmentID = Department.DepartmentID
WHERE Department.DepartmentName = 'Human Resources';

-- #2
-- This allows you to ID the projects that employees are working on
SELECT Project.ProjectID, Project.ProjectName, Employee.FullName
FROM Project
JOIN Assignment ON Project.ProjectID = Assignment.ProjectID
JOIN Employee ON Assignment.EmployeeID = Employee.EmployeeID

--#3
-- This allows you to find all employee who has the skill 'coding'
SELECT Employee.FullName, Skill.SkillName
FROM Employee
JOIN Assignment ON Employee.EmployeeID = Assignment.EmployeeID
JOIN Project ON Assignment.ProjectID = Project.ProjectID
JOIN Skill ON Project.SkillNeeded = Skill.Skill_ID
WHERE Skill.SkillName = 'Programming';

-- #4.1
-- Identify the employee in each department and their skills or if they have no skills
-- or the skill name is null
SELECT Department.DepartmentName, Employee.FullName, EmployeeDetail.SkillID, Skill.SkillName
FROM Department
FULL JOIN Employee ON Department.DepartmentID = Employee.DepartmentID
FULL JOIN EmployeeDetail ON Employee.EmployeeID = EmployeeDetail.EmployeeID
LEFT JOIN Skill ON Skill.Skill_ID = EmployeeDetail.SkillID


-- 4.2
-- Show the employee's name and their salary
SELECT Employee.FullName, EmployeeDetail.EmployeeSalary
FROM Employee
JOIN EmployeeDetail ON Employee.EmployeeID = EmployeeDetail.EmployeeID;

-- 4.3
-- This shows all projects that each department is working on
SELECT Department.DepartmentName, COALESCE(Project.ProjectName, 'No Project') AS ProjectName
FROM Department
LEFT JOIN Employee ON Department.DepartmentID = Employee.DepartmentID
LEFT JOIN Assignment ON Employee.EmployeeID = Assignment.EmployeeID
LEFT JOIN Project ON Assignment.ProjectID = Project.ProjectID;






