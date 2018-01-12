DROP TABLE IF EXISTS Announcements;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS LogData;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS ProductCategory;
DROP TABLE IF EXISTS Subcategory;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS productattribute;
DROP TABLE IF EXISTS attributes;
DROP TABLE IF EXISTS Product;

CREATE TABLE LogData (
  UserName VARCHAR(30) NOT NULL,
  PasswordHash CHAR(128) NOT NULL,
  PasswordSalt CHAR(50) NOT NULL,
  PRIMARY KEY (UserName)
  );
  
  
  CREATE TABLE Accounts (
	ID INT AUTO_INCREMENT,
    AccountType ENUM('Administrator', 'Moderator', 'User'),
    AccountDEscription VARCHAR(200),
    PRIMARY KEY(ID)
);


CREATE TABLE Users (
  UserName VARCHAR(30) NOT NULL,
  FirstName VARCHAR(30) NOT NULL,
  LastName VARCHAR(45) NOT NULL,
  AccountID INT NOT NULL,
  PhoneNumber INT NOT NULL,
  PRIMARY KEY (UserName),
  FOREIGN KEY (UserName) REFERENCES LogData (UserName),
  FOREIGN KEY (AccountID) REFERENCES Accounts (ID)
  );
  

CREATE TABLE Product (
	ID INT AUTO_INCREMENT,
    Name VARCHAR(30),			-- KLUCZ OBCY, ALE NA CO?
    Price DECIMAL(10, 2) UNSIGNED NOT NULL,
    ProductCondition ENUM('USED', 'NEW'),
    Description VARCHAR(300),
    PRIMARY KEY (ID)
);


CREATE TABLE Announcements (
	ID INT NOT NULL AUTO_INCREMENT,
    ProductID INT,
    UserName VARCHAR(30),
    Location VARCHAR(30),
    BeginDate DATE,
    EndDate DATE,
    Status ENUM('PENDING', 'ACCEPTED', 'MODERATED'),
    PRIMARY KEY (ID),
    FOREIGN KEY (ProductID) REFERENCES Product (ID),
    FOREIGN KEY (UserName) REFERENCES Users (UserName)
);


CREATE TABLE Category (
	ID INT AUTO_INCREMENT,
    Name VARCHAR(30),
    Description VARCHAR(100),
    PRIMARY KEY (ID)
);


CREATE TABLE Subcategory (
	ID INT AUTO_INCREMENT,
    Name VARCHAR(30) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    ParentCategory INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (ParentCategory) REFERENCES Category (ID)
);


CREATE TABLE ProductCategory (
	ID INT AUTO_INCREMENT,
    ProductID INT,
    SubcategoryID INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (ProductID) REFERENCES Product (ID),
    FOREIGN KEY (SubcategoryID) REFERENCES Subcategory (ID)
);
    
CREATE TABLE Attributes (
	ID INT AUTO_INCREMENT,
	Name VARCHAR(30) NOT NULL,
    Value VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ProductAttribute (
	ID INT AUTO_INCREMENT,
    ProductID INT,
    AttributeID INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (ProductID) REFERENCES Product (ID),
    FOREIGN KEY (AttributeID) REFERENCES Attributes (ID)
);