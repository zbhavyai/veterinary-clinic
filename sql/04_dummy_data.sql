-- owners ---------------------------------------
INSERT INTO owners (o_ownerid,o_firstname,o_middlename,o_lastname,o_contactnumber,o_emailid,o_address) VALUES
("1","Damian","Bruce","Wayne","4039034805","damian.bruce.wayne@ucalgary.ca","Wayne Manor"),
("2","Yorrick",NULL,"Brown","4038141489","yorrick.brown@ucalgary.ca",NULL),
("3","Clark","Joseph","Kent","4032423736","clark.joseph.kent@ucalgary.ca","Smallville"),
("4","Tintin",NULL,NULL,"4036424421","tintin@ucalgary.ca","Marlinspike Hall, Belgium"),
("5","UCalgary",NULL,NULL,"4031415078","ucalgary@ucalgary.ca","2500 University Dr NW, Calgary, AB T2N 1N4"),
("6","James",NULL,"Gunn","4035069917","james.gunn@ucalgary.ca","9336 Civic Center Drive, Beverly Hills, CA 90210-3604, USA"),
("7","Emily",NULL,"Marasco","4034987145","emily.marasco@ucalgary.ca","1042 Hope Street, Edmonton AB"),
("8","Kevin",NULL,"Durant","4033210624","kevin.durant@ucalgary.ca","5463 Wallflower Road, San Francisco, CA 90315, USA"),
("9","Jason","Kamika","Fukuda","4034532008","jason.kamika.fukuda@ucalgary.ca","1354 Douglasdale Blvd, Calgary,AB "),
("10","Jacob","D","Brown","4035422410","jacob.d.brown@ucalgary.ca","30 Building Way, San Francisco, CA 90315, USA"),
("11","Fred",NULL,"Kong","4039568022","fred.kong@ucalgary.ca","6401 Jasper Ave, Edmonton AB"),
("12","Kyrie",NULL,"Irving","4031165724","kyrie.irving@ucalgary.ca","93 Essense Hall, Crawley, London"),
("13","Trinity","Hope","Smith","4034560496","trinity.hope.smith@ucalgary.ca","10 Guardian Manor, Fort McMurray, AB"),
("14","Kailey","Camen","Hoeld","4033872352","kailey.camen.hoeld@ucalgary.ca","172 Silin Forest Road, Fort McMurray, AB"),
("15","Elizabeth",NULL,"Price","4034406265","elizabeth.price@ucalgary.ca","2850 Delancey Crescent, San Francisco, CA 904361, USA"),
("16","Dillon",NULL,"White","4034352974","dillon.white@ucalgary.ca","326 Ricardo Road, Bogota, Colombia "),
("17","Amber","Fair","Polinski","4039639612","amber.fair.polinski@ucalgary.ca","1792 Camille Street, Medellin, Colombia "),
("18","Gurneet","Kaur","Dhillon","4037339463","gurneet.kaur.dhillon@ucalgary.ca","#401 Arera Colony, Bhopal, MP, India");

UPDATE sequence_owners SET next_val=19;
-- ----------------------------------------------


-- animals --------------------------------------
INSERT INTO animals (a_animalid,a_name,a_species,a_subspecies,a_breed,a_type,a_region,a_sex,a_birthdate,a_status,a_requeststatus,a_ownerid,a_profilepic,a_tattoonum,a_citytattoo,a_rfidnumber,a_microchipnumber,a_coatcolor,a_distinctfeature,a_continuousmedication) VALUES
("1","Ace","Dog","Canine","German shepherd",NULL,"Spain","1","2017-12-01","0","0","13",NULL,"244662717","HOC sha","249005924","343639466","Black","sleeping pills","he can fly"),
("2","Ampersand","Monkey","Ape","Capuchin monkey",NULL,"France","0","2018-12-02","0","0","10",NULL,"158121195","ORE esd","260725030","147771172","Brown",NULL,"high jumps"),
("3","Bat Cow","Cow","Bovine",NULL,"Dairy","US","1","2020-04-03","0","0","11",NULL,"205572497","NKN sds","151131824","231238713","Brown, White",NULL,"bat-shaped patch on face"),
("4","Comet","Horse","Gallopping","Canadian horse",NULL,"Canada","0","2017-12-11","2","0","10",NULL,"339086340","HIS sdm","127736624","134304175","White","heart medication","spotted"),
("5","Krypto","Dog","Canine","Labrador retriever",NULL,"Africa","0","2018-12-31","2","0","13",NULL,"290236317","YSE mlc","272772868","210486439","White",NULL,NULL),
("6","Snowy","Dog","Canine","Wire Fox Terrier",NULL,"London","1","2020-04-07","3","0","10",NULL,"135343357","JSD sda","291244088","268000811","White","ACE Inhibitors","long ears"),
("7","Streaky","Cat","Feline","Abyssinian",NULL,"Rome","0","2021-04-03","2","0","4",NULL,"197429163","DLF kjs","131316177","217747622","Orange","chronic painkillers","he can fly"),
("8","Fluffy","Monkey","Ape","Marmoset",NULL,"London","1","2018-04-04","0","0","14",NULL,"228940211","LEM skd","210018105","173583943","Black",NULL,"high jumps"),
("9","Yogi","Dog","Lupus","Pomeranian",NULL,"London","1","2019-04-05","0","0","3",NULL,"185440668","DKD dks","225193205","343945307","Brown",NULL,"bat-shaped patch on face"),
("10","Hari","Dog","Lupus","Poodle",NULL,"India","1","2021-04-06","0","0","2",NULL,"251761043","DKE kwl","261689853","259843651","Brown, White","SSRIs","spotted"),
("11","Mocha","Monkey","Ape","Baboon",NULL,"China","0","2020-04-07","3","0","17",NULL,"308939358","KDF ker","307896950","129579514","White","sleeping pills",NULL),
("12","Pika","Cow","Zebu","Limousin",NULL,"Rome","1","2021-04-08","0","0","18",NULL,"227325698","ERL ker","280788581","249243052","White",NULL,"long ears"),
("13","Chewey","Dog","Canine","Chihuahua",NULL,"Rome","0","2021-04-09","3","0","17",NULL,"319532275","WEA wle","132299230","322964532","White",NULL,"he can fly"),
("14","Sir Eric","Horse","Tarpan","Shire",NULL,"Canada","1","2021-04-10","1","0","13",NULL,"241655546","VJD dfw","315606022","164378534","Orange","heart medication","high jumps"),
("15","Boomer","Horse","Tarpan","Arabian",NULL,"US","0","2021-08-11","0","0","3",NULL,"171406800","SLD wel","187156291","170514959","Black",NULL,"bat-shaped patch on face"),
("16","Kitty","Cat","Feline","Persian",NULL,"London","1","2021-04-09","3","0","3",NULL,"226042634","JLI oih","340156393","128127109","Brown","ACE Inhibitors","spotted"),
("17","Brewster","Dog","Canine","Golden Retriever",NULL,"Spain","1","2019-04-16","0","0","12",NULL,"212511357","NLK jll","186893525","185848221","Brown, White","chronic painkillers",NULL),
("18","Jack","Dog","Lupus","Bulldog",NULL,"Spain","1","2019-05-21","2","0","9",NULL,"194017653","IEK kdo","185173038","200140661","White",NULL,"long ears");

UPDATE sequence_animals SET next_val=19;
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
("8","2017-12-01","2017-12-02",NULL,"Priscilla",NULL,"Chan","0","priscilla.chan@ucalgary.ca","passw0rd",NULL,"1"),
("9","2018-12-02","2018-12-03",NULL,"Mark ",NULL,"Milin","1","mark .milin@ucalgary.ca","passw0rd",NULL,"1"),
("10","2020-04-03","2020-04-04",NULL,"Melipnos","Tara","Gade","1","melipnos.tara.gade@ucalgary.ca","passw0rd",NULL,"0"),
("11","2017-12-11","2017-12-12",NULL,"Thambe","Julian","Kustriya","4","thambe.julian.kustriya@ucalgary.ca","passw0rd",NULL,"0"),
("12","2018-12-31","2019-01-01",NULL,"Bob",NULL,"Vance","3","bob.vance@ucalgary.ca","passw0rd",NULL,"1"),
("13","2020-04-07","2020-04-08",NULL,"Phyllis",NULL,"Vance","4","phyllis.vance@ucalgary.ca","passw0rd",NULL,"1"),
("14","2021-04-03","2021-04-04",NULL,"Jim",NULL,"Halpert","4","jim.halpert@ucalgary.ca","passw0rd",NULL,"1"),
("15","2017-12-01","2017-12-02",NULL,"Pam",NULL,"Beesley","3","pam.beesley@ucalgary.ca","passw0rd",NULL,"0"),
("16","2018-12-02","2018-12-03",NULL,"Adam","Christopher","Greene","4","adam.christopher.greene@ucalgary.ca","passw0rd",NULL,"1"),
("17","2020-04-03","2020-04-04",NULL,"Demi",NULL,"Babatunde","4","demi.babatunde@ucalgary.ca","passw0rd",NULL,"1"),
("18","2017-12-11","2017-12-12",NULL,"Zach","Smith","Klein","4","zach.smith.klein@ucalgary.ca","passw0rd",NULL,"0");

UPDATE sequence_users SET next_val=19;
-- ----------------------------------------------


-- weights --------------------------------------
INSERT INTO weights (w_weightid,w_massinkg,w_recorddate,w_recordedby,w_animalid) VALUES
("1","5","2020-10-01","7","1"),
("2","4","2020-10-01","7","2"),
("3","30","2020-10-01","3","3"),
("4","40","2020-10-01","6","4"),
("5","3","2020-10-01","3","5"),
("6","3","2020-10-01","1","6"),
("7","1","2020-10-01","7","7"),
("8","4","2020-11-03","6","1"),
("9","5.2","2020-11-03","6","2"),
("10","4.2","2020-11-03","2","3"),
("11","30.2","2020-11-03","7","4"),
("12","40.2","2020-11-03","1","5"),
("13","3.2","2020-11-03","1","6"),
("14","3.2","2020-11-03","7","7"),
("15","0.6","2020-11-29","5","1"),
("16","3.6","2020-11-29","1","2"),
("17","4.8","2020-11-29","5","3"),
("18","3.8","2020-11-29","2","4"),
("19","29.8","2020-11-29","6","5"),
("20","39.8","2020-11-29","5","6"),
("21","2.8","2020-11-29","2","7"),
("22","3.8","2021-02-28","7","1"),
("23","1.2","2021-02-28","2","2"),
("24","4.2","2021-02-28","6","3"),
("25","5.4","2021-02-28","1","4"),
("26","4.4","2021-02-28","4","5"),
("27","30.4","2021-02-28","1","6"),
("28","40.4","2021-02-28","2","7");

UPDATE sequence_weights SET next_val=29;
-- ----------------------------------------------


-- photos ---------------------------------------
INSERT INTO photos (p_photoid,p_photodesc,p_animalid,p_photolink,p_alttext,p_uploader,p_uploaddate) VALUES
("1",NULL,"4","image1.png","neck","16","2020-10-01"),
("2",NULL,"16","image2.png",NULL,"8","2020-10-01"),
("3",NULL,"11","image3.png",NULL,"6","2020-10-01"),
("4","leg broken","6","image4.png",NULL,"5","2020-10-01"),
("5",NULL,"2","image5.png",NULL,"1","2020-10-01"),
("6",NULL,"17","image6.png",NULL,"14","2020-10-01"),
("7",NULL,"6","image7.png",NULL,"14","2020-10-01"),
("8","initial photo","13","image8.png","baby","2","2021-05-13"),
("9","initial photo","11","image9.png",NULL,"15","2021-04-29"),
("10","initial photo","12","image10.png","puberty","10","2021-11-11"),
("11","initial photo","2","image11.png",NULL,"16","2021-06-14"),
("12","initial photo","3","image12.png",NULL,"16","2021-06-19"),
("13",NULL,"2","image13.png",NULL,"15","2021-05-05"),
("14","knee replace","7","image14.png","knee","6","2021-09-01"),
("15","losing fur","17","image15.png",NULL,"9","2021-03-19"),
("16",NULL,"9","image16.png",NULL,"17","2021-10-03"),
("17","post neuter ","10","image17.png",NULL,"13","2021-09-04"),
("18",NULL,"9","image18.png",NULL,"2","2021-03-28");

UPDATE sequence_photos SET next_val=19;
-- ----------------------------------------------


-- comments -------------------------------------
INSERT INTO comments (c_commentid,c_commentdesc,c_animalid,c_commentdate,c_commenter) VALUES
("1","Nighttime terror","8","2021-12-08","1"),
("2","Howling ","18","2021-12-09","8"),
("3","Pregnant ","16","2021-12-10","9"),
("4",NULL,"16","2021-12-08","6"),
("5",NULL,"13","2021-12-09","12"),
("6",NULL,"14","2021-12-10","12"),
("7","Not sleeping","17","2021-12-08","18"),
("8",NULL,"4","2020-10-01","2"),
("9",NULL,"1","2020-10-01","6"),
("10",NULL,"10","2021-05-13","11"),
("11",NULL,"7","2021-04-29","16"),
("12",NULL,"8","2021-11-11","12"),
("13","Excessive scratching","18","2021-06-14","9"),
("14",NULL,"14","2021-06-19","16"),
("15","Neuterization consult","10","2021-05-05","16"),
("16",NULL,"1","2021-09-01","11"),
("17",NULL,"3","2021-03-19","14"),
("18",NULL,"5","2021-10-03","5");

UPDATE sequence_comments SET next_val=19;
-- ----------------------------------------------


-- issues ---------------------------------------
INSERT INTO issues (i_issueid,i_issuedesc,i_detecteddate,i_animalid,i_raisedby,i_isresolved) VALUES
("1","Limp Walk","2021-12-08","1","5",1),
("2",NULL,"2020-12-12","13","11",1),
("3","Diabetes","2021-05-16","7","12",0),
("4","Inflammed limb","2021-05-29","10","14",0),
("5","Bladder Infection","2021-05-16","4","18",0),
("6","chronic kidney disease","2021-11-17","9","5",0),
("7","Upset Stomach","2021-11-17","11","17",0),
("8",NULL,"2021-12-08","6","6",0),
("9",NULL,"2021-12-09","1","9",0),
("10",NULL,"2021-12-10","16","9",1),
("11",NULL,"2021-12-08","9","12",0),
("12",NULL,"2020-10-01","15","17",0),
("13",NULL,"2020-10-01","11","4",0),
("14",NULL,"2021-05-13","18","9",1),
("15",NULL,"2021-04-29","11","4",1),
("16",NULL,"2021-11-11","16","14",1),
("17",NULL,"2021-06-14","6","10",1),
("18",NULL,"2021-06-19","15","9",0);

UPDATE sequence_issues SET next_val=19;
-- ----------------------------------------------


-- treatments -----------------------------------
INSERT INTO treatments (t_treatmentid,t_treatmentdesc,t_drugname,t_drugdose,t_deliverymethod,t_animalid,t_treatmentdate,t_treatedby) VALUES
("1","Physical exam","Pfizer","0.5mg","transdermal","12","2021-12-08","1"),
("2","Blood work",NULL,NULL,NULL,"5","2021-12-09","11"),
("3","Da2pp","Astrazeneca","0.02mg",NULL,"14","2021-12-10","4"),
("4","dental cleaning",NULL,NULL,NULL,"10","2021-12-08","3"),
("5","drontal deworm","Moderna","0.2mg","intramuscular","9","2021-12-09","1"),
("6","rabies vaccination",NULL,NULL,NULL,"14","2021-12-10","9"),
("7","Revolution treatment","Pfizer",NULL,NULL,"18","2021-12-08","17"),
("8",NULL,NULL,NULL,NULL,"4","2021-11-17","11"),
("9","knee replacement","Astrazeneca","0.1mg","injection","8","2021-11-17","12"),
("10","leg amputation",NULL,NULL,NULL,"9","2021-12-08","9"),
("11",NULL,NULL,NULL,NULL,"8","2021-12-09","9"),
("12",NULL,NULL,NULL,NULL,"10","2021-12-10","1"),
("13","Physical exam",NULL,NULL,NULL,"5","2021-12-08","4"),
("14","Vaccine","Astrazeneca","0.1mg","injection","18","2021-12-08","7"),
("15","Da2pp",NULL,NULL,NULL,"12","2020-10-01","2"),
("16","dental cleaning","Astrazeneca",NULL,NULL,"7","2021-05-13","15"),
("17","drontal deworm",NULL,NULL,NULL,"5","2021-04-29","5");

UPDATE sequence_treatments SET next_val=18;
-- ----------------------------------------------
