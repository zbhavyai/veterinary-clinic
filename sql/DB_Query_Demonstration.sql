USE vetdb;

-- (2) basic retrieval query --------------------
SELECT
o.o_ownerid 'ID',
o.o_firstname 'FIRST_NAME',
o.o_middlename 'MID_NAME',
o.o_lastname 'LAST_NAME',
o.o_emailid 'EMAIL',
o.o_contactnumber 'PHONE_NUMBER',
o.o_address 'LOCATION'
FROM
owners o;
-- ----------------------------------------------


-- (3) retrieval query with ordered results -----
SELECT w_animalid, MAX(w_massinkg) AS max_weight FROM weights GROUP BY w_animalid ORDER BY max_weight DESC;     -- order by max weight of animal over the history
SELECT * FROM comments ORDER BY c_commentdate DESC;                                                             -- order by the latest added comments
SELECT * FROM issues WHERE i_isresolved=0 ORDER BY i_detecteddate ASC;                                          -- order by the oldest unresolved issues
SELECT * FROM treatments WHERE t_animalid IN (14, 18) ORDER BY t_treatmentdate DESC;                            -- order by latest administered treatment
SELECT * FROM photos ORDER BY p_uploaddate DESC;                                                                -- order by latest uploaded photos
-- ----------------------------------------------


-- (4) nested retrieval query -------------------
SELECT
*
FROM
animals
WHERE
a_ownerid IN (SELECT o.o_ownerid FROM owners o WHERE o.o_address LIKE '%manor%');   -- animals belonging to owners living in a Manor
-- ----------------------------------------------


-- (5) retreival query using joined table -------
SELECT
a.a_animalid 'ID',
a.a_name 'NAME',
a.a_species 'SPECIES',
a.a_subspecies 'SUBSPECIES',
a.a_breed 'BREED',
a.a_type 'TYPE',
a.a_region 'REGION',
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


-- (6) update operation -------------------------
SELECT * FROM issues;
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
WHERE 
a.a_animalid=13;

UPDATE issues SET i_issuedesc='Fracture' WHERE i_issueid=2;

SELECT * FROM issues;
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
WHERE 
a.a_animalid=13;
-- ----------------------------------------------


-- (7) delete operation -------------------------
SELECT * FROM animals WHERE a_animalid='5';

SELECT * FROM comments WHERE c_animalid=5;
SELECT * FROM issues WHERE i_animalid=5;
SELECT * FROM photos WHERE p_animalid=5;
SELECT * FROM treatments WHERE t_animalid=5;

DELETE FROM animals WHERE a_animalid='5';
-- ----------------------------------------------
