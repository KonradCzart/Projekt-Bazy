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

call RegisterInsert('konrad', 'konrad123', 'aaaaaa', 'Konrad' , 'Czart' , 697203222);