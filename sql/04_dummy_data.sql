INSERT INTO owners (o_ownerid,o_firstname,o_middlename,o_lastname,o_role,o_contactnumber,o_emailid,o_address) VALUES
(1,"Damian","Bruce","Wayne",NULL,"4036653061","damian.bruce.wayne@ucalgary.ca","Wayne Manor"),
(2,"Yorrick",NULL,"Brown",NULL,"4034097804","yorrick.brown@ucalgary.ca",NULL),
(3,"Clark","Joseph","Kent",NULL,"4032991533","clark.joseph.kent@ucalgary.ca","Smallville"),
(4,"Tintin",NULL,NULL,NULL,"4031678053","tintin@ucalgary.ca","Marlinspike Hall, Belgium"),
(5,"UCalgary",NULL,NULL,0,"4038988801","ucalgary@ucalgary.ca","2500 University Dr NW, Calgary, AB T2N 1N4"),
(6,"James",NULL,"Gunn",NULL,"4035644337","james.gunn@ucalgary.ca","9336 Civic Center Drive, Beverly Hills, CA 90210-3604, USA");

UPDATE sequence_owners SET next_val=7;



INSERT INTO animals (a_animalid,a_name,a_type,a_breed,a_birthdate,a_sex,a_status,a_ownerid,a_tattoonum,a_rfidnumber,a_microchipnumber,a_weight,a_coatcolor,a_continuousmedication) VALUES
(1,"Ace","Dog","German shepherd",NULL,"1",3,1,130110477,"229664665","229348845",NULL,"Black",NULL),
(2,"Ampersand","Monkey","Capuchin monkey",NULL,"0",1,2,204261377,"313829102","233453231",NULL,"Brown",NULL),
(3,"Bat Cow","Cow",NULL,NULL,"0",2,1,171659869,"223023284","202432386",NULL,"Brown, White",NULL),
(4,"Comet","Horse","Canadian horse",NULL,"1",1,3,225931412,"184668886","345541619",NULL,"White",NULL),
(5,"Krypto","Dog","Labrador retriever",NULL,"0",1,3,185030356,"170719459","157412204",NULL,"White",NULL),
(6,"Snowy","Dog","Wire Fox Terrier",NULL,"1",0,4,225864215,"188758885","255400812",NULL,"White",NULL),
(7,"Streaky","Cat","Abyssinian",NULL,"1",1,3,209577225,"177988155","267795413",NULL,"Orange",NULL);

UPDATE sequence_animals SET next_val=8;

INSERT INTO users (u_userid,u_firstname,u_middlename,u_lastname,u_role) VALUES 
(1,"Damian","Bruce","Wayne","1"),
(2,"Tony",NULL,"Stark","2"),
(3,"Clark","Joseph","Kent","3"),
(4,"Billy ","Ray","Cyrus","2"),
(5,"Bob","Homer","Simposon","2"),
(6,"Miley","Ray",NULL,"1"),
(7,"Chris",NULL,"Lighter","2");

UPDATE sequence_users SET next_val=9;

INSERT INTO treatments (t_treatmentid,t_treatmentdesc,t_animalid,t_treatmentdate,t_treatedby) VALUES 
(1,"Physical exam","7","2021-12-08","1"),
(2,"Blood work","1","2021-12-09","2"),
(3,"Da2pp","1","2021-12-10","5"),
(4,"dental cleaning","6","2021-12-08","3"),
(5,"drontal deworm","1","2021-12-09","4"),
(6,"rabies vaccination","7","2021-12-10","2"),
(7,"Revolution treatment","7","2021-12-08","4");

UPDATE sequence_treatments SET next_val=10;

INSERT INTO issues (i_issueid,i_issuedesc,i_animalid,i_detecteddate,i_raisedby,i_isresolved) VALUES 
(1,"Limp Walk","3","2021-12-08","1",1),
(2,NULL,"6","2020-12-12","1",0),
(3,"Diabetes","7","2021-05-16","2",0),
(4,"Inflammed limb","1","2021-05-29","1",NULL),
(5,"Bladder Infection","1",NULL,"5",NULL),
(6,"chronic kidney disease","7","2021-11-17","3",0),
(7,"Upset Stomach","3","2021-11-17","2",NULL);

UPDATE sequence_issues SET next_val=11;

INSERT INTO photos (p_photoid,p_animalid,p_photolink) VALUES 
(1,"7","image1.png"),
(2,"2","image2.png"),
(3,"3","image4.png"),
(4,"2",NULL),
(5,"3","image7.png"),
(6,"5","image6.png"),
(7,"6","image5.png");

UPDATE sequence_photos SET next_val=12;



INSERT INTO comments (c_commentid,c_commentdesc,c_animalid,c_commentdate,c_commenter) VALUES 
(1,"Nighttime terror","2","2021-12-08","1"),
(2,"Howling ","2","2021-12-09","4"),
(3,"Pregnant ","6","2021-12-10","5"),
(4,"No Comment Provided","1","2021-12-08","5"),
(5,"No Comment Provided","1","2021-12-09","1"),
(6,"No Comment Provided","2","2021-12-10","2"),
(7,"Not sleeping","2","2021-12-08","1");

UPDATE sequence_comments SET next_val=13;


