USE vetdb;

CREATE TABLE animals (
    a_animalid bigint not null,
    a_name varchar(255),
    a_species varchar(255),
    a_subspecies varchar(255),
    a_breed varchar(255),
    a_type varchar(255),
    a_region varchar(255),
    a_sex integer,
    a_birthdate date,
    a_status integer,
    a_requeststatus integer,
    a_ownerid bigint,
    a_profilepic bigint,
    a_tattoonum integer,
    a_citytattoo varchar(255),
    a_rfidnumber varchar(255),
    a_microchipnumber varchar(255),
    a_coatcolor varchar(255),
    a_continuousmedication varchar(255),
    a_distinctfeature varchar(255),
    primary key (a_animalid)
);

CREATE TABLE users (
    u_userid bigint not null,
    u_joiningdate date not null,
    u_activationdate date,
    u_terminationdate date,
    u_firstname varchar(255),
    u_middlename varchar(255),
    u_lastname varchar(255),
    u_role integer,
    u_emailid varchar(255),
    u_passwordhash text,
    u_passwordsalt varchar(255),
    u_status integer not null,
    primary key (u_userid)
);

CREATE TABLE owners (
    o_ownerid bigint not null,
    o_firstname varchar(255),
    o_middlename varchar(255),
    o_lastname varchar(255),
    o_contactnumber varchar(255),
    o_emailid varchar(255),
    o_address varchar(255),
    primary key (o_ownerid)
);

create table weights (
    w_weightid bigint not null,
    w_massinkg double precision not null,
    w_recorddate date not null,
    w_recordedby bigint,
    w_animalid bigint,
    primary key (w_weightid)
);

CREATE TABLE photos (
    p_photoid bigint not null,
    p_photodesc varchar(255),
    p_animalid bigint,
    p_photolink varchar(255),
    p_alttext varchar(255),
    p_uploader bigint,
    p_uploaddate date not null,
    primary key (p_photoid)
);

CREATE TABLE comments (
    c_commentid bigint not null,
    c_commentdesc varchar(255),
    c_animalid bigint,
    c_commentdate date not null,
    c_commenter bigint,
    primary key (c_commentid)
);

CREATE TABLE issues (
    i_issueid bigint not null,
    i_issuedesc varchar(255),
    i_detecteddate date not null,
    i_animalid bigint,
    i_raisedby bigint,
    i_isresolved bit,
    primary key (i_issueid)
);

CREATE TABLE treatments (
    t_treatmentid bigint not null,
    t_treatmentdesc varchar(255),
    t_drugname varchar(255),
    t_drugdose varchar(255),
    t_deliverymethod varchar(255),
    t_animalid bigint,
    t_treatmentdate date not null,
    t_treatedby bigint,
    primary key (t_treatmentid)
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

CREATE TABLE sequence_weights (
    next_val bigint
);

CREATE TABLE sequence_photos (
    next_val bigint
);

CREATE TABLE sequence_comments (
    next_val bigint
);

CREATE TABLE sequence_issues (
    next_val bigint
);

CREATE TABLE sequence_treatments (
    next_val bigint
);

INSERT INTO sequence_animals values (1);
INSERT INTO sequence_users values (1);
INSERT INTO sequence_owners values (1);
INSERT INTO sequence_weights values (1);
INSERT INTO sequence_photos values (1);
INSERT INTO sequence_comments values (1);
INSERT INTO sequence_issues values (1);
INSERT INTO sequence_treatments values (1);

ALTER TABLE animals
    add constraint fk_a_ownerid_animals
    foreign key (a_ownerid)
    references owners (o_ownerid);

ALTER TABLE animals
    add constraint fk_a_profilepic_animals
    foreign key (a_profilepic)
    references photos (p_photoid);

ALTER TABLE weights
    add constraint fk_w_animalid_weights
    foreign key (w_animalid)
    references animals (a_animalid) on delete cascade;

ALTER TABLE weights
    add constraint fk_w_recordedby_weights
    foreign key (w_recordedby)
    references users (u_userid) on delete set null;

ALTER TABLE photos
    add constraint fk_p_animalid_photos
    foreign key (p_animalid)
    references animals (a_animalid) on delete cascade;

ALTER TABLE photos
    add constraint fk_p_uploader_photos
    foreign key (p_uploader)
    references users (u_userid) on delete set null;

ALTER TABLE comments
    add constraint fk_c_commenter_comments
    foreign key (c_commenter)
    references users (u_userid) on delete set null;

ALTER TABLE comments
    add constraint fk_c_animalid_comments
    foreign key (c_animalid)
    references animals (a_animalid) on delete cascade;

ALTER TABLE issues
    add constraint fk_i_raisedby_issues
    foreign key (i_raisedby)
    references users (u_userid) on delete set null;

ALTER TABLE issues
    add constraint fk_i_animalid_issues
    foreign key (i_animalid)
    references animals (a_animalid) on delete cascade;

ALTER TABLE treatments
    add constraint fk_t_animalid_treatments
    foreign key (t_animalid)
    references animals (a_animalid) on delete cascade;

ALTER TABLE treatments
    add constraint fk_t_treatedby_treatments
    foreign key (t_treatedby)
    references users (u_userid) on delete set null;
