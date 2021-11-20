-- users view -----------------------------------
CREATE OR REPLACE VIEW VIEW_USERS AS
SELECT
u.u_userid 'ID',
u.u_firstname 'FIRST_NAME',
u.u_middlename 'MID_NAME',
u.u_lastname 'LAST_NAME',
u.u_emailid 'EMAIL',
CASE u.u_role
    WHEN 0 THEN 'ADMIN'
    WHEN 1 THEN 'ATTENDANT'
    WHEN 2 THEN 'TECHNICIAN'
    WHEN 3 THEN 'TEACHER'
    WHEN 4 THEN 'STUDENT'
END 'ROLE',
CASE u.u_status
    WHEN 0 THEN 'INACTIVE'
    WHEN 1 THEN 'ACTIVE'
END 'STATUS'
FROM
users u;
-- ----------------------------------------------


-- animals view ---------------------------------
CREATE OR REPLACE VIEW VIEW_ANIMALS AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_type 'TYPE',
a.a_breed 'BREED',
a.a_birthdate 'BIRTH_DATE', -- to be replaced with age in months
a.a_sex 'SEX',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
o.o_lastname 'OWNER',
o.o_emailid 'EMAIL',
o.o_contactnumber 'CONTACT',
o.o_address 'ADDRESS',
a.a_tattoonum 'TATTOO',
a.a_rfidnumber 'RFID',
a.a_microchipnumber 'MICROCHIP',
a.a_coatcolor 'COAT_COLOR',
a.a_continuousmedication 'MEDICATION'
FROM
animals a
LEFT JOIN owners o ON a.a_ownerid = o.o_ownerid;
-- ----------------------------------------------


-- animals view simple --------------------------
CREATE OR REPLACE VIEW VIEW_ANIMALS_SIMPLE AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_type 'TYPE',
a.a_breed 'BREED',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
o.o_firstname 'OWNER'
-- CONCAT(o.o_firstname, " ", (CASE o.o_middlename WHEN IS NULL THEN " " ELSE o.o_middlename END ), " ", o.o_lastname) 'OWNER'
FROM
animals a
LEFT JOIN owners o ON a.a_ownerid = o.o_ownerid;
-- ----------------------------------------------
