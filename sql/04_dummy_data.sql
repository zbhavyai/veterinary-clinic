INSERT INTO owners (o_ownerid,o_firstname,o_middlename,o_lastname,o_role,o_contactnumber,o_emailid,o_address) VALUES
(1,"Damian","Bruce","Wayne",NULL,"4036653061","damian.bruce.wayne@ucalgary.ca","Wayne Manor"),
(2,"Yorrick","","Brown",NULL,"4034097804","yorrick.brown@ucalgary.ca",""),
(3,"Clark","Joseph","Kent",NULL,"4032991533","clark.joseph.kent@ucalgary.ca","Smallville"),
(4,"Tintin","","",NULL,"4031678053","tintin@ucalgary.ca","Marlinspike Hall, Belgium"),
(5,"UCalgary","","",0,"4038988801","ucalgary@ucalgary.ca","2500 University Dr NW, Calgary, AB T2N 1N4"),
(6,"James","","Gunn",NULL,"4035644337","james.gunn@ucalgary.ca","9336 Civic Center Drive, Beverly Hills, CA 90210-3604, USA");

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
(1,"Damian","Bruce","Wayne", 1),
(2,"Tony","","Stark",2),
(3,"Clark","Joseph","Kent",3);