DROP TRIGGER IF EXISTS users_before_insert;
DELIMITER $$
CREATE TRIGGER users_before_insert BEFORE INSERT ON users
FOR EACH ROW
BEGIN
	IF char_length(New.PhoneNumber) <> 9 OR (New.PhoneNumber RLIKE '^[0-9]+$' = 0) THEN
        SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = 'Zły numer telefonu!';
    END IF;
    
    IF  New.FirstName RLIKE '^[a-zA-Z]+$' = 0 THEN
        SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = 'Złe imie';
    END IF;
    
    IF  New.LastName RLIKE '^[a-zA-Z]+$' = 0 THEN
        SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = 'Złe nazwisko';
    END IF;
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS users_before_updade;
DELIMITER $$
CREATE TRIGGER users_before_updade BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
	IF char_length(New.PhoneNumber) <> 9 OR (New.PhoneNumber RLIKE '^[0-9]+$' = 0) THEN
        SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = 'Zły numer telefonu!';
    END IF;
    
    IF  New.FirstName RLIKE '^[a-zA-Z]+$' = 0 THEN
        SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = 'Złe imie';
    END IF;
    
    IF  New.LastName RLIKE '^[a-zA-Z]+$' = 0 THEN
        SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = 'Złe nazwisko';
    END IF;
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS logdata_before_insert;
DELIMITER $$
CREATE TRIGGER logdata_before_insert BEFORE INSERT ON logdata
FOR EACH ROW
BEGIN
    IF  New.UserName RLIKE '^[[:alnum:]]+$' = 0 THEN
        SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = 'Złe login';
    END IF;
END$$
DELIMITER ;


DROP TRIGGER IF EXISTS logdata_before_updade;
DELIMITER $$
CREATE TRIGGER logdata_before_updade BEFORE UPDATE ON logdata
FOR EACH ROW
BEGIN
    IF  New.UserName RLIKE '^[[:alnum:]]+$' = 0 THEN
        SIGNAL SQLSTATE '55555'
        SET MESSAGE_TEXT = 'Złe login';
    END IF;
END$$
DELIMITER ;



