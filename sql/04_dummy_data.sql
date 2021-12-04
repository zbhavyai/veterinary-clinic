-- owners ---------------------------------------
INSERT INTO owners (o_ownerid,o_firstname,o_middlename,o_lastname,o_contactnumber,o_emailid,o_address) VALUES
("1","Damian","Bruce","Wayne","4035624970","damian.bruce.wayne@ucalgary.ca","Wayne Manor"),
("2","Yorrick",NULL,"Brown","4035288817","yorrick.brown@ucalgary.ca",NULL),
("3","Clark","Joseph","Kent","4032613398","clark.joseph.kent@ucalgary.ca","Smallville"),
("4","Tintin",NULL,NULL,"4031571609","tintin@ucalgary.ca","Marlinspike Hall, Belgium"),
("5","UCalgary",NULL,NULL,"4032811334","ucalgary@ucalgary.ca","2500 University Dr NW, Calgary, AB T2N 1N4"),
("6","James",NULL,"Gunn","4033203090","james.gunn@ucalgary.ca","9336 Civic Center Drive, Beverly Hills, CA 90210-3604, USA");

UPDATE sequence_owners SET next_val=7;
-- ----------------------------------------------


-- animals --------------------------------------
INSERT INTO animals (a_animalid,a_name,a_species,a_subspecies,a_breed,a_type,a_region,a_sex,a_birthdate,a_status,a_requeststatus,a_ownerid,a_profilepic,a_tattoonum,a_citytattoo,a_rfidnumber,a_microchipnumber,a_coatcolor,a_distinctfeature,a_continuousmedication) VALUES
("1","Ace","Dog","Canine","German shepherd",NULL,"Spain","1","2017-12-01","2","0","1",NULL,"286704680","HOC sha","200779867","300212935","Black","sleeping pills","he can fly"),
("2","Ampersand","Monkey","Ape","Capuchin monkey",NULL,"France","1","2018-12-02","3","0","2",NULL,"224037195","ORE esd","270458816","219009740","Brown",NULL,"high jumps"),
("3","Bat Cow","Cow","Bovine","Unknown","Dairy","US","0","2020-04-03","1","0","1",NULL,"227479807","NKN sds","271415667","270705876","Brown, White",NULL,"bat-shaped patch on face"),
("4","Comet","Horse","Gallopping","Canadian horse",NULL,"Canada","0","2017-12-11","3","0","3",NULL,"232141799","HIS sdm","215373589","160639011","White",NULL,"spotted"),
("5","Krypto","Dog","Canine","Labrador retriever",NULL,"Africa","1","2018-12-31","1","0","3",NULL,"126325196","YSE mlc","300139197","169573012","White",NULL,NULL),
("6","Snowy","Dog","Canine","Wire Fox Terrier",NULL,"London","1","2020-04-07","3","0","4",NULL,"171153107","JSD sda","174511649","295539895","White",NULL,"long ears"),
("7","Streaky","Cat","Feline","Abyssinian",NULL,"Rome","0","2021-04-03","2","0","3",NULL,"137582509","DLF kjs","185233095","188980246","Orange",NULL,NULL);

UPDATE sequence_animals SET next_val=8;
-- ----------------------------------------------


-- users ----------------------------------------
INSERT INTO users (u_userid,u_joiningdate,u_activationdate,u_terminationdate,u_firstname,u_middlename,u_lastname,u_role,u_emailid,u_passwordhash,u_passwordsalt,u_status) VALUES
("1","2017-12-01","2017-12-02",NULL,"Greg",NULL,"Boorman","0","greg.boorman@ucalgary.ca","passw0rd",NULL,"1"),
("2","2018-12-02","2018-12-03",NULL,"Teacher",NULL,"Admin","0","teacher.admin@ucalgary.ca","passw0rd",NULL,"0"),
("3","2020-04-03","2020-04-04",NULL,"Technician",NULL,"A","2","technician.a@ucalgary.ca","passw0rd",NULL,"0"),
("4","2017-12-11","2017-12-12",NULL,"Attendant",NULL,"B","1","attendant.b@ucalgary.ca","passw0rd",NULL,"0"),
("5","2018-12-31","2019-01-01","2021-09-27","Teacher",NULL,"C","3","teacher.c@ucalgary.ca","passw0rd",NULL,"0"),
("6","2020-04-07","2020-04-08","2023-01-03","Student",NULL,"D","4","student.d@ucalgary.ca","passw0rd",NULL,"0"),
("7","2021-04-03","2021-04-04","2023-12-30","Student",NULL,"E","4","student.e@ucalgary.ca","passw0rd",NULL,"0"),
("8","2021-04-04","2021-04-05","2023-12-31","Instructor1",NULL,"E","3","Instructor_1","pt@123",NULL,"1"),
("9","2021-04-05","2021-04-06","2023-11-30","Admin1",NULL,"LName","0","Admin_1","pa",NULL,"1"),
("10","2021-04-06","2021-04-07","2024-01-30","Tech",NULL,"LName","2","Technician","pe",NULL,"1");

UPDATE sequence_users SET next_val=11;
-- ----------------------------------------------


-- weights --------------------------------------
INSERT INTO weights (w_weightid,w_massinkg,w_recorddate,w_recordedby,w_animalid) VALUES
("1","5","2020-10-01","7","1"),
("2","4","2020-10-01","7","2"),
("3","30","2020-10-01","1","3"),
("4","40","2020-10-01","3","4"),
("5","3","2020-10-01","4","5"),
("6","3","2020-10-01","4","6"),
("7","1","2020-10-01","4","7"),
("8","4","2020-11-03","1","1"),
("9","5.2","2020-11-03","7","2"),
("10","4.2","2020-11-03","2","3"),
("11","30.2","2020-11-03","6","4"),
("12","40.2","2020-11-03","1","5"),
("13","3.2","2020-11-03","1","6"),
("14","3.2","2020-11-03","2","7"),
("15","0.6","2020-11-29","5","1"),
("16","3.6","2020-11-29","5","2"),
("17","4.8","2020-11-29","3","3"),
("18","3.8","2020-11-29","7","4"),
("19","29.8","2020-11-29","7","5"),
("20","39.8","2020-11-29","7","6"),
("21","2.8","2020-11-29","3","7"),
("22","3.8","2021-02-28","2","1"),
("23","1.2","2021-02-28","1","2"),
("24","4.2","2021-02-28","1","3"),
("25","5.4","2021-02-28","4","4"),
("26","4.4","2021-02-28","5","5"),
("27","30.4","2021-02-28","4","6"),
("28","40.4","2021-02-28","7","7");

UPDATE sequence_treatments SET next_val=29;
-- ----------------------------------------------


-- photos ---------------------------------------
INSERT INTO photos (p_photoid,p_photodesc,p_animalid,p_photolink,p_alttext,p_uploader,p_uploaddate) VALUES
("1",NULL,"2","image1.png","neck","3","2020-10-01"),
("2",NULL,"4","image2.png",NULL,"7","2020-10-01"),
("3",NULL,"1","image4.png",NULL,"6","2020-10-01"),
("4","leg broken","7",NULL,NULL,"2","2020-10-01"),
("5",NULL,"2","image7.png",NULL,"6","2020-10-01"),
("6",NULL,"4","image6.png",NULL,"4","2020-10-01"),
("7",NULL,"5","image5.png",NULL,"3","2020-10-01");

UPDATE sequence_photos SET next_val=8;
-- ----------------------------------------------


-- comments -------------------------------------
INSERT INTO comments (c_commentid,c_commentdesc,c_animalid,c_commentdate,c_commenter) VALUES
("1","Nighttime terror","4","2021-12-08","3"),
("2","Howling ","6","2021-12-09","5"),
("3","Pregnant ","2","2021-12-10","3"),
("4",NULL,"4","2021-12-08","7"),
("5",NULL,"6","2021-12-09","3"),
("6",NULL,"6","2021-12-10","7"),
("7","Not sleeping","1","2021-12-08","6");

UPDATE sequence_comments SET next_val=8;
-- ----------------------------------------------


-- issues ---------------------------------------
INSERT INTO issues (i_issueid,i_issuedesc,i_detecteddate,i_animalid,i_raisedby,i_isresolved) VALUES
("1","Limp Walk","2021-12-08","1","1",1),
("2",NULL,"2020-12-12","1","1",1),
("3","Diabetes","2021-05-16","6","2",0),
("4","Inflammed limb","2021-05-29","5","1",0),
("5","Bladder Infection","2021-05-16","5","5",0),
("6","chronic kidney disease","2021-11-17","5","3",0),
("7","Upset Stomach","2021-11-17","4","2",0);

UPDATE sequence_issues SET next_val=8;
-- ----------------------------------------------


-- treatments -----------------------------------
INSERT INTO treatments (t_treatmentid,t_treatmentdesc,t_drugname,t_drugdose,t_deliverymethod,t_animalid,t_treatmentdate,t_treatedby) VALUES
("1","Physical exam",NULL,NULL,NULL,"7","2021-12-08","1"),
("2","Blood work",NULL,NULL,NULL,"7","2021-12-09","2"),
("3","Da2pp",NULL,NULL,NULL,"4","2021-12-10","5"),
("4","dental cleaning",NULL,NULL,NULL,"6","2021-12-08","3"),
("5","drontal deworm",NULL,NULL,NULL,"6","2021-12-09","4"),
("6","rabies vaccination",NULL,NULL,NULL,"4","2021-12-10","2"),
("7","Revolution treatment",NULL,NULL,NULL,"5","2021-12-08","4");

UPDATE sequence_treatments SET next_val=8;
-- ----------------------------------------------
