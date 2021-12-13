USE vetdb;

CREATE TABLE animals (
    a_animalid BIGINT NOT NULL,
    a_name VARCHAR(255),
    a_species VARCHAR(255),
    a_subspecies VARCHAR(255),
    a_breed VARCHAR(255),
    a_type VARCHAR(255),
    a_region VARCHAR(255),
    a_sex INTEGER,
    a_birthdate DATE,
    a_status INTEGER,
    a_requeststatus INTEGER,
    a_ownerid BIGINT,
    a_profilepic BIGINT,
    a_tattoonum INTEGER,
    a_citytattoo VARCHAR(255),
    a_rfidnumber VARCHAR(255),
    a_microchipnumber VARCHAR(255),
    a_coatcolor VARCHAR(255),
    a_continuousmedication VARCHAR(255),
    a_distinctfeature VARCHAR(255),
    PRIMARY KEY (a_animalid)
);

CREATE TABLE users (
    u_userid BIGINT NOT NULL,
    u_joiningdate DATE NOT NULL,
    u_activationdate DATE,
    u_terminationdate DATE,
    u_firstname VARCHAR(255),
    u_middlename VARCHAR(255),
    u_lastname VARCHAR(255),
    u_role INTEGER,
    u_emailid VARCHAR(255),
    u_passwordhash TEXT,
    u_passwordsalt VARCHAR(255),
    u_status INTEGER NOT NULL,
    PRIMARY KEY (u_userid)
);

CREATE TABLE owners (
    o_ownerid BIGINT NOT NULL,
    o_firstname VARCHAR(255),
    o_middlename VARCHAR(255),
    o_lastname VARCHAR(255),
    o_contactnumber VARCHAR(255),
    o_emailid VARCHAR(255),
    o_address VARCHAR(255),
    PRIMARY KEY (o_ownerid)
);

CREATE TABLE weights (
    w_weightid BIGINT NOT NULL,
    w_massinkg DOUBLE PRECISION NOT NULL,
    w_recorddate DATE NOT NULL,
    w_recordedby BIGINT,
    w_animalid BIGINT NOT NULL,
    PRIMARY KEY (w_weightid)
);

CREATE TABLE photos (
    p_photoid BIGINT NOT NULL,
    p_photodesc VARCHAR(255),
    p_animalid BIGINT NOT NULL,
    p_photolink VARCHAR(255),
    p_alttext VARCHAR(255),
    p_uploader BIGINT,
    p_uploaddate DATE NOT NULL,
    PRIMARY KEY (p_photoid)
);

CREATE TABLE comments (
    c_commentid BIGINT NOT NULL,
    c_commentdesc VARCHAR(255),
    c_animalid BIGINT NOT NULL,
    c_commentdate DATE NOT NULL,
    c_commenter BIGINT,
    PRIMARY KEY (c_commentid)
);

CREATE TABLE issues (
    i_issueid BIGINT NOT NULL,
    i_issuedesc VARCHAR(255),
    i_detecteddate DATE NOT NULL,
    i_animalid BIGINT NOT NULL,
    i_raisedby BIGINT,
    i_isresolved BIT,
    PRIMARY KEY (i_issueid)
);

CREATE TABLE treatments (
    t_treatmentid BIGINT NOT NULL,
    t_treatmentdesc VARCHAR(255),
    t_drugname VARCHAR(255),
    t_drugdose VARCHAR(255),
    t_deliverymethod VARCHAR(255),
    t_animalid BIGINT NOT NULL,
    t_treatmentdate DATE NOT NULL,
    t_treatedby BIGINT,
    PRIMARY KEY (t_treatmentid)
);

CREATE TABLE sequence_animals (
    next_val BIGINT
);

CREATE TABLE sequence_users (
    next_val BIGINT
);

CREATE TABLE sequence_owners (
    next_val BIGINT
);

CREATE TABLE sequence_weights (
    next_val BIGINT
);

CREATE TABLE sequence_photos (
    next_val BIGINT
);

CREATE TABLE sequence_comments (
    next_val BIGINT
);

CREATE TABLE sequence_issues (
    next_val BIGINT
);

CREATE TABLE sequence_treatments (
    next_val BIGINT
);

INSERT INTO sequence_animals VALUES (1);
INSERT INTO sequence_users VALUES (1);
INSERT INTO sequence_owners VALUES (1);
INSERT INTO sequence_weights VALUES (1);
INSERT INTO sequence_photos VALUES (1);
INSERT INTO sequence_comments VALUES (1);
INSERT INTO sequence_issues VALUES (1);
INSERT INTO sequence_treatments VALUES (1);

ALTER TABLE animals
    ADD CONSTRAINT fk_a_ownerid_animals
    FOREIGN KEY (a_ownerid)
    REFERENCES owners (o_ownerid);

ALTER TABLE animals
    ADD CONSTRAINT fk_a_profilepic_animals
    FOREIGN KEY (a_profilepic)
    REFERENCES photos (p_photoid);

ALTER TABLE weights
    ADD CONSTRAINT fk_w_animalid_weights
    FOREIGN KEY (w_animalid)
    REFERENCES animals (a_animalid) ON DELETE CASCADE;

ALTER TABLE weights
    ADD CONSTRAINT fk_w_recordedby_weights
    FOREIGN KEY (w_recordedby)
    REFERENCES users (u_userid) ON DELETE SET NULL;

ALTER TABLE photos
    ADD CONSTRAINT fk_p_animalid_photos
    FOREIGN KEY (p_animalid)
    REFERENCES animals (a_animalid) ON DELETE CASCADE;

ALTER TABLE photos
    ADD CONSTRAINT fk_p_uploader_photos
    FOREIGN KEY (p_uploader)
    REFERENCES users (u_userid) ON DELETE SET NULL;

ALTER TABLE comments
    ADD CONSTRAINT fk_c_commenter_comments
    FOREIGN KEY (c_commenter)
    REFERENCES users (u_userid) ON DELETE SET NULL;

ALTER TABLE comments
    ADD CONSTRAINT fk_c_animalid_comments
    FOREIGN KEY (c_animalid)
    REFERENCES animals (a_animalid) ON DELETE CASCADE;

ALTER TABLE issues
    ADD CONSTRAINT fk_i_raisedby_issues
    FOREIGN KEY (i_raisedby)
    REFERENCES users (u_userid) ON DELETE SET NULL;

ALTER TABLE issues
    ADD CONSTRAINT fk_i_animalid_issues
    FOREIGN KEY (i_animalid)
    REFERENCES animals (a_animalid) ON DELETE CASCADE;

ALTER TABLE treatments
    ADD CONSTRAINT fk_t_animalid_treatments
    FOREIGN KEY (t_animalid)
    REFERENCES animals (a_animalid) ON DELETE CASCADE;

ALTER TABLE treatments
    ADD CONSTRAINT fk_t_treatedby_treatments
    FOREIGN KEY (t_treatedby)
    REFERENCES users (u_userid) ON DELETE SET NULL;
