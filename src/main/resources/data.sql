INSERT INTO CLIENTS (name, login, password, address, active) VALUES ('Jonh Smith', 'log1', 'pass','Ukraine, Kiev, 24 Buchmy str., apt.19', 1);
INSERT INTO CLIENTS (name, login, password, address, active) VALUES ('Meri Brass', 'meri', 'pass', 'Ukraine, Kiev, 18 Zoologicheskaya str., apt.88', 1);
INSERT INTO CLIENTS (name, login, password, address, active) VALUES ('Olga Novik', 'olg33', 'pass', 'Ukraine, Kiev, 3A Grishka str., apt.56', 1);
INSERT INTO CLIENTS (name, login, password, address, active) VALUES ('Viktor Fedorenko', 'fedor', 'pass', 'Ukraine, Odessa, 23/1 Hmelitskogo str., apt.157', 1);
INSERT INTO CLIENTS (name, login, password, address, active) VALUES ('Ivan Sidorov', 'sidor44', 'pass','Ukraine, Kharkov, Centralnaya str. 32, apt.78', 1);

INSERT INTO PRODUCTS (type, model, price) VALUES ('fridge', 'BOSCH KGN3', 17299);
INSERT INTO PRODUCTS (type, model, price) VALUES ('fridge', 'SAMSUNG RB29FS', 12999);
INSERT INTO PRODUCTS (type, model, price) VALUES ('conditioner', 'HYUNDAI ARN09', 6099);
INSERT INTO PRODUCTS (type, model, price) VALUES ('Smart TV', 'SAMSUNG UE32J', 8500);

INSERT INTO WAREHOUSE (PROD_ID, AMOUNT) VALUES (1,38);
INSERT INTO WAREHOUSE (PROD_ID, AMOUNT) VALUES (2,345);
INSERT INTO WAREHOUSE (PROD_ID, AMOUNT) VALUES (3,47);
INSERT INTO WAREHOUSE (PROD_ID, AMOUNT) VALUES (4,89);

INSERT INTO PURCHASES (CL_ID, PROD_ID, AMOUNT) VALUES (1, 2, 1);
INSERT INTO PURCHASES (CL_ID, PROD_ID, AMOUNT) VALUES (2, 3, 2);
INSERT INTO PURCHASES (CL_ID, PROD_ID, AMOUNT) VALUES (2, 1, 1);
INSERT INTO PURCHASES (CL_ID, PROD_ID, AMOUNT) VALUES (4, 1, 1);

SELECT * FROM CLIENTS;
SELECT * FROM PRODUCTS;

SELECT PU.ID AS ID, CL.NAME, PR.TYPE, PR.MODEL, PR.PRICE, PU.AMOUNT
FROM PURCHASES PU INNER JOIN PRODUCTS PR
ON PU.PROD_ID = PR.ID
INNER JOIN CLIENTS CL ON PU.CL_ID = CL.ID
ORDER BY PU.ID;

SELECT W.ID AS ID, PR.TYPE, PR.MODEL, PR.PRICE, W.AMOUNT
FROM WAREHOUSE W INNER JOIN PRODUCTS PR
ON W.PROD_ID = PR.ID;
