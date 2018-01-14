DROP PROCEDURE IF EXISTS ValidataLog;

DELIMITER //

CREATE PROCEDURE ValidataLog ( in login VARCHAR(30) , in password VARCHAR(128) , out res int )

BEGIN

   DECLARE oldPassword varchar(128);
	
    SELECT PasswordHash INTO oldPassword FROM  logdata WHERE UserName = login;
    
    
   IF oldPassword = password THEN
      SET res = 1;
   ELSE
      SET res = 0;

   END IF;
END; //

DELIMITER ;


DROP PROCEDURE IF EXISTS RegisterInsert;

DELIMITER //

CREATE PROCEDURE RegisterInsert ( in login VARCHAR(30) , in pasword VARCHAR(128) , in salt VARCHAR(128), in name VARCHAR(30), lastname VARCHAR(45), in number INT )

BEGIN
	INSERT INTO logdata VALUES (login, pasword, salt);
    INSERT INTO users VALUES (login, name, lastname, 3, number);
END; //

DELIMITER ;

DROP PROCEDURE IF EXISTS ChangePassword;

DELIMITER //

CREATE PROCEDURE ChangePassword (in login VARCHAR(30), in pasword VARCHAR(128) , in newpasword VARCHAR(128), in salt VARCHAR(128))

BEGIN
	DECLARE oldPassword varchar(128);
	
    SELECT PasswordHash INTO oldPassword FROM  logdata WHERE UserName = login;
    
	IF oldPassword = pasword THEN
      UPDATE logdata SET PasswordHash = newpasword WHERE UserName = login;
      UPDATE logdata SET PasswordSalt = salt WHERE UserName = login;
   ELSE
		SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = '11111';
   END IF;
END; //

DELIMITER ;

DROP PROCEDURE IF EXISTS InsertProduct;

DELIMITER //

CREATE PROCEDURE InsertProduct (in login VARCHAR(30), in locationP VARCHAR(30), in title VARCHAR(60), in productName VARCHAR(30) , in descriptionProduct VARCHAR(300), in priceProduct DECIMAL(10, 2),in useStatus int, in subcategoryI INT, in yearP INT, out res int )

BEGIN
	DECLARE idProduct int;
    DECLARE sDate DATE;
    DECLARE eDate DATE;
    
    SET sDate = current_date();
    SET eDate = date_add(sDate , INTERVAL 14 DAY);
	
    INSERT INTO product(Name, Price, ProductYear, ProductCondition, Description) VALUES	( productName, priceProduct, yearP, useStatus, descriptionProduct);
    SET idProduct = LAST_INSERT_ID();
    INSERT INTO productcategory ( ProductID, SubcategoryID) VALUES ( idProduct, subcategoryI);
    INSERT INTO announcements ( ProductID, TitleName, UserName, Location, BeginDate, EndDate, Status) VALUES (idProduct, title, login,   locationP, sDate, eDate, 1);
    SET res = idProduct;
END; //

DELIMITER ;

DROP PROCEDURE IF EXISTS InsertProductAttribute;

DELIMITER //

CREATE PROCEDURE InsertProductAttribute (in idProduct INT, in idAttribute INT)

BEGIN
    INSERT INTO productattribute (ProductID, AttributeID) VALUES (idProduct, idAttribute );
END; //

DELIMITER ;
call RegisterInsert ( 'kon', '123', 'aaaaaaa', 'konrad', 'czart', 123123123);
 call InsertProduct ( 'kon','Michałówkfa', 'Sprzedfam auto osobowe' , 'aufdi b5bd', 'dasfasffasdasfasfdasda',  158545.25, 1, 1,2008, @res);