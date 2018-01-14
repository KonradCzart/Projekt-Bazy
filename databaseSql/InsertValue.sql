INSERT INTO category(Name, Description) VALUES ("Motoryzacja" , "Kategoria związana z motoryzacją");
INSERT INTO category(Name, Description) VALUES ("Elektronika" , "Kategoria związana z elektroniką");
INSERT INTO category(Name, Description) VALUES ("Ubrania" , "Kategoria związana z modą oraz ubraniami");
INSERT INTO category(Name, Description) VALUES ("Zwierzęta" , "Kategoria związana z domowymi/gospodarczymi zwierzetami");
INSERT INTO category(Name, Description) VALUES ("Usługi" , "Kategoria związana z usługami");
INSERT INTO category(Name, Description) VALUES ("Praca" , "Kategoria związana z ofertami pracy");
INSERT INTO category(Name, Description) VALUES ("Książki" , "Kategoria związana z literaturą");
INSERT INTO category(Name, Description) VALUES ("Nieruchomości" , "Kategoria związana z miejscami do zamieszkania");

INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Samochody osobowe" , "", 1);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Samochody ciężarowe" , "", 1);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Motocykle" , "", 1);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Części" , "", 1);

INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Komputery" , "", 2);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Telefony" , "", 2);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Sprzęt AGD" , "", 2);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Telewizory" , "", 2);

INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Ubrania" , "", 3);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Buty" , "", 3);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Dodatki" , "", 3);

INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Domowe" , "", 4);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Gospodarcze" , "", 4);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Akcesoria" , "", 4);

INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Budowlane" , "", 5);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Motoryzacyjne" , "", 5);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Transportowe" , "", 5);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Inne" , "", 5);

INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Umowa o pracę" , "", 6);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Umowa zlecenie" , "", 6);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Umowa o dzieło" , "", 6);

INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Językowe" , "", 7);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Naukowe" , "", 7);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Literatura" , "", 7);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Czasopisma" , "", 7);

INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Działki" , "", 8);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Domy" , "", 8);
INSERT INTO subcategory(Name , Description, ParentCategory) VALUES ("Mieszkania" , "", 8);

INSERT INTO accounts VALUES ( 1, 1 , "Ma wszystkie możliwe prawa, zarządza całą aplikacją");
INSERT INTO accounts VALUES ( 2, 2 , "Ma prawa do zmian oraz do akceptowania ogłoszeń");
INSERT INTO accounts VALUES ( 3, 3 , "Prawo do przeglądania istniejących ogłoszeń i dodawania własnych");

