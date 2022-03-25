INSERT INTO MEDICINE (MEDICINE_ID, MEDICINE_NAME, BRAND, DESCRIPTION, TREATABLE_DISEASES, PRICE, QUANTITY) VALUES (1, 'Albuterol', 'Proair HFA', 'Relaxes muscles in the airways and increase air flow to lungs', 'asthma', 100.00, 123)
INSERT INTO MEDICINE (MEDICINE_ID, MEDICINE_NAME, BRAND, DESCRIPTION, TREATABLE_DISEASES, PRICE, QUANTITY) VALUES (2, 'Amoxicillin', 'Amoxil', 'Helps to control blood sugar levels', 'High Blood Sugar', 42.00, 14)
INSERT INTO MEDICINE (MEDICINE_ID, MEDICINE_NAME, BRAND, DESCRIPTION, TREATABLE_DISEASES, PRICE, QUANTITY) VALUES (3, 'Diphenhydramine', 'Benadryl', 'Treats allergic reactions', 'Allergies', 16.00, 15)
INSERT INTO MEDICINE (MEDICINE_ID, MEDICINE_NAME, BRAND, DESCRIPTION, TREATABLE_DISEASES, PRICE, QUANTITY) VALUES (4, 'Metformin', 'Riomet', 'Treats allergic reactions', 'Allergies', 16.00, 15)

INSERT INTO USERS (USER_ID, USERNAME, FIRST_NAME, LAST_NAME, PASSWORD, BIRTH_DATE, PHONE_NUMBER, EMAIL, ADDRESS) VALUES (34, 'johnDoe', 'John', 'Doe', '123456', '1954-11-09', '505-223-1234', 'johndoe@email.com', '123 pineapple rd')
INSERT INTO USERS (USER_ID, USERNAME, FIRST_NAME, LAST_NAME, PASSWORD, BIRTH_DATE, PHONE_NUMBER, EMAIL, ADDRESS) VALUES (362, 'paulRudd23', 'Paul', 'Rudd', 'antman', '1975-08-09', '512-123-4121', 'paulrudd@email.com', '456 Maple Rd')
INSERT INTO USERS (USER_ID, USERNAME, FIRST_NAME, LAST_NAME, PASSWORD, BIRTH_DATE, PHONE_NUMBER, EMAIL, ADDRESS) VALUES (368, 'robertPattinson', 'Robert', 'Patinson', 'imbatman', '1984-04-12', '428-123-8774', 'robertp@email.com', '5039 Sidewinder Road')

INSERT INTO ROLES (ROLE_ID, ROLE_NAME) VALUES (1, 'ROLE_ADMIN')
INSERT INTO ROLES (ROLE_ID, ROLE_NAME) VALUES (2, 'ROLE_USER')

INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES (34, 1)
INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES (362, 2)
INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES (368, 2)

INSERT INTO USER_ACCOUNT(USER_ID, ACCOUNT_NUMBER, BALANCE) VALUES (34, 12340012, 120.00)
INSERT INTO USER_ACCOUNT(USER_ID, ACCOUNT_NUMBER, BALANCE) VALUES (362, 40015632, 0.00)
INSERT INTO USER_ACCOUNT(USER_ID, ACCOUNT_NUMBER, BALANCE) VALUES (8787, 400678451, 500.00)

INSERT INTO ORDERS (ORDER_ID, USER_ID, ORDER_STATUS, ORDER_DATE) VALUES (12345, 34, 'SHIPPED', '2022-03-15')
INSERT INTO ORDERS (ORDER_ID, USER_ID, ORDER_STATUS, ORDER_DATE) VALUES (23456, 34, 'DELIVERED', '2016-03-15')
INSERT INTO ORDERS (ORDER_ID, USER_ID, ORDER_STATUS, ORDER_DATE) VALUES (10025, 368, 'DELIVERED', '2021-02-15')
INSERT INTO ORDERS (ORDER_ID, USER_ID, ORDER_STATUS, ORDER_DATE) VALUES (10026, 362, 'SHIPPED', '2022-03-23')

INSERT INTO ORDER_ITEMS(ID, QUANTITY, MEDICINE_ID, ORDER_ID) VALUES (2222, 2, 1, 12345)
INSERT INTO ORDER_ITEMS(ID, QUANTITY, MEDICINE_ID, ORDER_ID) VALUES (2544, 1, 2, 23456)
INSERT INTO ORDER_ITEMS(ID, QUANTITY, MEDICINE_ID, ORDER_ID) VALUES (1878, 1, 3, 10025)
INSERT INTO ORDER_ITEMS(ID, QUANTITY, MEDICINE_ID, ORDER_ID) VALUES (1879, 1, 1, 10025)

INSERT INTO CART (CART_ID, CART_COST, USER_ID) VALUES (5454, 0.00, 34)
INSERT INTO CART (CART_ID, CART_COST, USER_ID) VALUES (5455, 0.00, 362)
INSERT INTO CART (CART_ID, CART_COST, USER_ID) VALUES (5456, 0.00, 368)

INSERT INTO CART_ITEM (CART_ITEM_ID, CART_ID, MEDICINE_ID, QUANTITY) VALUES (1, 5454, 1, 1)
INSERT INTO CART_ITEM (CART_ITEM_ID, CART_ID, MEDICINE_ID, QUANTITY) VALUES (2, 5455, 2, 1)
INSERT INTO CART_ITEM (CART_ITEM_ID, CART_ID, MEDICINE_ID, QUANTITY) VALUES (3, 5455, 3, 1)
INSERT INTO CART_ITEM (CART_ITEM_ID, CART_ID, MEDICINE_ID, QUANTITY) VALUES (4, 5456, 3, 1)