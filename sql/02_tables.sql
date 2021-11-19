USE vetdb;

CREATE TABLE animals (
    a_animalid bigint not null,
    a_name varchar(255),
    a_type varchar(255),
    a_breed varchar(255),
    a_birthdate date,
    a_sex varchar(255),
    a_status integer,
    a_ownerid bigint,
    a_tattoonum integer,
    a_rfidnumber varchar(255),
    a_microchipnumber varchar(255),
    a_weight tinyblob,
    a_coatcolor varchar(255),
    a_continuousmedication varchar(255),
    primary key (a_animalid)
);

CREATE TABLE users (
    u_userid bigint not null,
    u_firstname varchar(255),
    u_lastname varchar(255),
    u_middlename varchar(255),
    u_role integer,
    u_emailid varchar(255),
    u_password text,
    u_status integer,
    primary key (u_userid)
);

CREATE TABLE owners (
    o_ownerid bigint not null,
    o_firstname varchar(255),
    o_lastname varchar(255),
    o_middlename varchar(255),
    o_role integer,
    o_contactnumber varchar(255),
    o_emailid varchar(255),
    o_address varchar(255),
    primary key (o_ownerid)
);

CREATE TABLE comments (
    c_commentid bigint not null,
    c_commentdesc varchar(255),
    c_animalid bigint,
    c_commentdate date,
    c_commenter bigint,
    primary key (c_commentid)
);

CREATE TABLE treatments (
    t_treatmentid bigint not null,
    t_treatmentdesc varchar(255),
    t_animalid bigint,
    t_treatmentdate date,
    t_treatedby bigint,
    primary key (t_treatmentid)
);

CREATE TABLE issues (
    i_issueid bigint not null,
    i_issuedesc varchar(255),
    i_detecteddate date,
    i_animalid bigint,
    i_raisedby bigint,
    i_isresolved bit,
    primary key (i_issueid)
);

CREATE TABLE photos (
    p_photoid bigint not null,
    p_animalid bigint,
    p_photolink varchar(255),
    primary key (p_photoid)
);

CREATE TABLE sequence_animals (
    next_val bigint
);

CREATE TABLE sequence_users (
    next_val bigint
);

CREATE TABLE sequence_owners (
    next_val bigint
);

CREATE TABLE sequence_comments (
    next_val bigint
);

CREATE TABLE sequence_treatments (
    next_val bigint
);

CREATE TABLE sequence_issues (
    next_val bigint
);

CREATE TABLE sequence_photos (
    next_val bigint
);

INSERT INTO sequence_animals values (1);
INSERT INTO sequence_users values (1);
INSERT INTO sequence_owners values (1);
INSERT INTO sequence_comments values (1);
INSERT INTO sequence_treatments values (1);
INSERT INTO sequence_issues values (1);
INSERT INTO sequence_photos values (1);

ALTER TABLE animals
    add constraint fk_a_ownerid_animals
    foreign key (a_ownerid)
    references owners (o_ownerid);

ALTER TABLE comments
    add constraint fk_c_commenter_comments
    foreign key (c_commenter)
    references users (u_userid);

ALTER TABLE comments
    add constraint fk_c_animalid_comments
    foreign key (c_animalid)
    references animals (a_animalid);

ALTER TABLE issues
    add constraint fk_i_raisedby_issues
    foreign key (i_raisedby)
    references users (u_userid);

ALTER TABLE issues
    add constraint fk_i_animalid_issues
    foreign key (i_animalid)
    references animals (a_animalid);

ALTER TABLE photos
    add constraint fk_p_animalid_photos
    foreign key (p_animalid)
    references animals (a_animalid);

ALTER TABLE treatments
    add constraint fk_t_animalid_treatments
    foreign key (t_animalid)
    references animals (a_animalid);

ALTER TABLE treatments
    add constraint fk_t_treatedby_treatments
    foreign key (t_treatedby)
    references users (u_userid);
