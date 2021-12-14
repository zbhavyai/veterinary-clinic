USE vetdb;

-- animals basic view ---------------------------
CREATE OR REPLACE VIEW animals_basic_view AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_species 'SPECIES',
a.a_subspecies 'SUBSPECIES',
a.a_breed 'BREED',
a.a_type 'TYPE',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
CASE a.a_requeststatus
    WHEN 0 THEN 'AVAILABLE'
    WHEN 1 THEN 'REQUESTED'
    WHEN 2 THEN 'ACCEPT_BY_ADMIN'
    WHEN 3 THEN 'READY'
    WHEN 4 THEN 'CANCEL'
    WHEN 5 THEN 'REJECT'
END 'REQUEST_STATUS',
CONCAT( (CASE WHEN o.o_firstname IS NULL THEN "" ELSE o.o_firstname END),
        (CASE WHEN o.o_middlename IS NULL THEN "" ELSE CONCAT(" ", o.o_middlename ) END),
        (CASE WHEN o.o_lastname IS NULL THEN "" ELSE CONCAT(" ", o.o_lastname ) END)) 'OWNER',
o.o_address 'LOCATION'
FROM
animals a
LEFT JOIN owners o ON o.o_ownerid = a.a_ownerid
ORDER BY
a.a_animalid;
-- ----------------------------------------------


-- animals profile view -------------------------
CREATE OR REPLACE VIEW animals_profile_view AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_species 'SPECIES',
a.a_subspecies 'SUBSPECIES',
a.a_breed 'BREED',
a.a_type 'TYPE',
a.a_region 'REGION',
a.a_sex 'SEX',
a.a_birthdate 'BIRTH_DATE',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
a.a_profilepic 'PROFILE_PIC',
CONCAT( (CASE WHEN o.o_firstname IS NULL THEN "" ELSE o.o_firstname END),
        (CASE WHEN o.o_middlename IS NULL THEN "" ELSE CONCAT(" ", o.o_middlename ) END),
        (CASE WHEN o.o_lastname IS NULL THEN "" ELSE CONCAT(" ", o.o_lastname ) END)) 'OWNER',
o.o_emailid 'EMAIL',
o.o_contactnumber 'CONTACT',
o.o_address 'LOCATION',
a.a_tattoonum 'TATTOO',
a.a_citytattoo 'CITY_TATTOO',
a.a_rfidnumber 'RFID',
a.a_microchipnumber 'MICROCHIP',
a.a_coatcolor 'COAT_COLOR',
a.a_continuousmedication 'MEDICATION',
a.a_distinctfeature 'DISTINCT_FEATURE'
FROM
animals a
LEFT JOIN owners o ON o.o_ownerid = a.a_ownerid
ORDER BY
a.a_animalid, o.o_ownerid;
-- ----------------------------------------------


-- animals weights view -------------------------
CREATE OR REPLACE VIEW animals_weights_view AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_species 'SPECIES',
a.a_subspecies 'SUBSPECIES',
a.a_breed 'BREED',
-- a.a_type 'TYPE',
-- a.a_region 'REGION',
a.a_distinctfeature 'DISTINCT_FEATURE',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
w.w_massinkg 'WEIGHT_KG',
w.w_recorddate 'RECORD_DATE',
CONCAT( (CASE WHEN u.u_firstname IS NULL THEN "" ELSE u.u_firstname END),
        (CASE WHEN u.u_middlename IS NULL THEN "" ELSE CONCAT(" ", u.u_middlename ) END),
        (CASE WHEN u.u_lastname IS NULL THEN "" ELSE CONCAT(" ", u.u_lastname ) END)) 'RECORDER_NAME',
u.u_emailid 'RECORDER_EMAIL'
FROM
animals a
RIGHT JOIN weights w ON w.w_animalid = a.a_animalid
LEFT JOIN users u ON u.u_userid = w.w_recordedby
ORDER BY
a.a_animalid, w.w_recorddate DESC;
-- ----------------------------------------------


-- animals photos view --------------------------
CREATE OR REPLACE VIEW animals_photos_view AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_species 'SPECIES',
a.a_subspecies 'SUBSPECIES',
a.a_breed 'BREED',
a.a_distinctfeature 'DISTINCT_FEATURE',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
p.p_photolink 'PHOTO_LINK',
p.p_alttext 'ALT_TEXT',
p.p_photodesc 'PHOTO_DESC',
p.p_uploaddate 'UPLOAD_DATE',
CONCAT( (CASE WHEN u.u_firstname IS NULL THEN "" ELSE u.u_firstname END),
        (CASE WHEN u.u_middlename IS NULL THEN "" ELSE CONCAT(" ", u.u_middlename ) END),
        (CASE WHEN u.u_lastname IS NULL THEN "" ELSE CONCAT(" ", u.u_lastname ) END)) 'UPLOADER_NAME',
u.u_emailid 'UPLOADER_EMAIL'
FROM
animals a
RIGHT JOIN photos p ON p.p_animalid = a.a_animalid
LEFT JOIN users u ON u.u_userid = p.p_uploader
ORDER BY
a.a_animalid, p.p_uploaddate DESC;
-- ----------------------------------------------


-- animals comments view ------------------------
CREATE OR REPLACE VIEW animals_comments_view AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_species 'SPECIES',
a.a_subspecies 'SUBSPECIES',
a.a_breed 'BREED',
-- a.a_type 'TYPE',
-- a.a_region 'REGION',
a.a_distinctfeature 'DISTINCT_FEATURE',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
c.c_commentdesc 'COMMENT',
c.c_commentdate 'COMMENT_DATE',
CONCAT( (CASE WHEN u.u_firstname IS NULL THEN "" ELSE u.u_firstname END),
        (CASE WHEN u.u_middlename IS NULL THEN "" ELSE CONCAT(" ", u.u_middlename ) END),
        (CASE WHEN u.u_lastname IS NULL THEN "" ELSE CONCAT(" ", u.u_lastname ) END)) 'COMMENTER_NAME',
u.u_emailid 'COMMENTER_EMAIL'
FROM
animals a
RIGHT JOIN comments c ON c.c_animalid = a.a_animalid
LEFT JOIN users u ON u.u_userid = c.c_commenter
ORDER BY
a.a_animalid, c.c_commentdate DESC;
-- ----------------------------------------------


-- animals issues view --------------------------
CREATE OR REPLACE VIEW animals_issues_view AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_species 'SPECIES',
a.a_subspecies 'SUBSPECIES',
a.a_breed 'BREED',
-- a.a_type 'TYPE',
-- a.a_region 'REGION',
a.a_distinctfeature 'DISTINCT_FEATURE',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
i.i_issuedesc 'ISSUE',
i.i_detecteddate 'DETECTION_DATE',
CONCAT( (CASE WHEN u.u_firstname IS NULL THEN "" ELSE u.u_firstname END),
        (CASE WHEN u.u_middlename IS NULL THEN "" ELSE CONCAT(" ", u.u_middlename ) END),
        (CASE WHEN u.u_lastname IS NULL THEN "" ELSE CONCAT(" ", u.u_lastname ) END)) 'RAISEDBY_NAME',
u.u_emailid 'RAISEDBY_EMAIL'
FROM
animals a
RIGHT JOIN issues i ON i.i_animalid = a.a_animalid
LEFT JOIN users u ON u.u_userid = i.i_raisedby
ORDER BY
a.a_animalid, i.i_detecteddate DESC;
-- ----------------------------------------------


-- animals treatments view ----------------------
CREATE OR REPLACE VIEW animals_treatments_view AS
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_species 'SPECIES',
a.a_subspecies 'SUBSPECIES',
a.a_breed 'BREED',
-- a.a_type 'TYPE',
-- a.a_region 'REGION',
a.a_distinctfeature 'DISTINCT_FEATURE',
CASE a.a_status
    WHEN 0 THEN 'GREEN'
    WHEN 1 THEN 'YELLOW'
    WHEN 2 THEN 'RED'
    WHEN 3 THEN 'INACTIVE'
END 'STATUS',
t.t_treatmentdesc 'TREATMENT',
t.t_drugname 'DRUG_USED',
t.t_drugdose 'DRUG_DOSE',
t.t_deliverymethod 'DRUG_DELIVERY',
t.t_treatmentdate 'TREATMENT_DATE',
CONCAT( (CASE WHEN u.u_firstname IS NULL THEN "" ELSE u.u_firstname END),
        (CASE WHEN u.u_middlename IS NULL THEN "" ELSE CONCAT(" ", u.u_middlename ) END),
        (CASE WHEN u.u_lastname IS NULL THEN "" ELSE CONCAT(" ", u.u_lastname ) END)) 'TREATEDBY_NAME',
u.u_emailid 'TREATEDBY_EMAIL'
FROM
animals a
RIGHT JOIN treatments t ON t.t_animalid = a.a_animalid
LEFT JOIN users u ON u.u_userid = t.t_treatedby
ORDER BY
a.a_animalid, t.t_treatmentdate DESC;
-- ----------------------------------------------


-- owners view ----------------------------------
CREATE OR REPLACE VIEW owners_view AS
SELECT
o.o_ownerid 'ID',
o.o_firstname 'FIRST_NAME',
o.o_middlename 'MID_NAME',
o.o_lastname 'LAST_NAME',
o.o_emailid 'EMAIL',
o.o_contactnumber 'PHONE_NUMBER',
o.o_address 'LOCATION'
FROM
owners o
ORDER BY o.o_ownerid;
-- ----------------------------------------------


-- users view -----------------------------------
CREATE OR REPLACE VIEW users_view AS
SELECT
u.u_userid 'ID',
u.u_firstname 'FIRST_NAME',
u.u_middlename 'MID_NAME',
u.u_lastname 'LAST_NAME',
u.u_emailid 'EMAIL',
u.u_passwordhash 'PASSWORD',
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
END 'STATUS',
u.u_joiningdate 'ACC_CREATION_DATE',
u.u_activationdate 'ACC_ACTIVATION_DATE',
u.u_terminationdate 'ACC_TERMINATION_DATE'
FROM
users u
ORDER BY u.u_userid;
-- ----------------------------------------------
