DROP DATABASE IF EXISTS superheroDB;
CREATE DATABASE superheroDB;
USE superheroDB;
CREATE TABLE address (
	add_id INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(20) NOT NULL,
    city VARCHAR(20) NOT NULL,
    state VARCHAR(20) NOT NULL,
    zipcode INT NOT NULL
);
CREATE TABLE organization (
	org_id INT PRIMARY KEY AUTO_INCREMENT,
	org_name VARCHAR(250) NOT NULL,
	org_description VARCHAR(250) NOT NULL,
	phone VARCHAR(250) NOT NULL,
    add_id INT NULL, -- assume address is optional (like maybe it is a secret)
    CONSTRAINT FOREIGN KEY FK_organization_address (add_id)
		REFERENCES address (add_id)
);
CREATE TABLE powers (
  powers_id int PRIMARY KEY AUTO_INCREMENT,
  powers_name varchar(15) NOT NULL
);
CREATE TABLE member (
	member_id INT PRIMARY KEY AUTO_INCREMENT,
    member_name VARCHAR(20) NOT NULL,
    member_description VARCHAR(100) NOT NULL,
    powers_id INT NOT NULL,
    -- powers VARCHAR(50) NOT NULL
    CONSTRAINT FOREIGN KEY FK_member_powers (powers_id)
		REFERENCES powers (powers_id)
);
CREATE TABLE memberOrg (
	member_id INT NOT NULL,
    org_id INT NOT NULL,
    CONSTRAINT PK_memberOrg PRIMARY KEY (member_id, org_id),
    CONSTRAINT FOREIGN KEY FK_memberOrg_member (member_id)
		REFERENCES member (member_id),
	CONSTRAINT FOREIGN KEY FK_memberOrg_organization (org_id)
		REFERENCES organization (org_id)
);
CREATE TABLE sightings (
	sighting_id INT PRIMARY KEY AUTO_INCREMENT,
    sighting_name VARCHAR(25) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    add_id INT NULL, -- assume address is optional because maybe we don't know the address
    CONSTRAINT FOREIGN KEY FK_sightings_address (add_id)
		REFERENCES address (add_id)
);
CREATE TABLE memberSighting (
	member_id INT NOT NULL,
    sighting_id INT NOT NULL,
    `date` DATE NOT NULL,
    CONSTRAINT PK_memberSighting PRIMARY KEY (member_id, sighting_id),
    CONSTRAINT FOREIGN KEY FK_memberSighting_member (member_id)
		REFERENCES member (member_id),
	CONSTRAINT FOREIGN KEY FK_memberSighting_sightings (sighting_id)
		REFERENCES sightings (sighting_id)
);