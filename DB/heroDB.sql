DROP DATABASE IF EXISTS heroDB;
CREATE DATABASE heroDB;
USE heroDB;

 CREATE TABLE superpower ( 
    superpowerId INT PRIMARY KEY AUTO_INCREMENT ,
    power VARCHAR(40) NOT NULL); 
    
CREATE TABLE hero(
    heroId INT PRIMARY KEY AUTO_INCREMENT,
    heroName varchar(20) NOT NULL,
    heroDescription VARCHAR(255) NOT NULL,
    superpowerId INT,
    FOREIGN KEY fk_superpower_superpowerId(superpowerId)
    REFERENCES superPower(superpowerId),
    memberType VARCHAR(25) NOT NULL
);
CREATE TABLE locations(
    locationId INT PRIMARY KEY AUTO_INCREMENT,
    locationDescription VARCHAR(30) NOT NULL,
    street VARCHAR(30) NOT NULL,
    city VARCHAR(20) NOT NULL,
    state CHAR(2) NOT NULL,
    zip CHAR(5) NOT NULL,
    lattitude decimal(10,8) NOT NULL,
    longitude decimal(11,8) NOT NULL
);
CREATE TABLE sightings(
    sightingsId INT PRIMARY KEY AUTO_INCREMENT,
    heroId INT NOT NULL,
    FOREIGN KEY fk_hero_heroId(heroId)
    REFERENCES hero(heroId),
    locationId INT NOT NULL,
	FOREIGN KEY fk_locations_locationId(locationId)
    REFERENCES locations(locationId),
    sightingDate DATE NOT NULL,
    sightingDescription varChar(255) NOT NULL
);
CREATE TABLE organization(
    organizationId INT PRIMARY KEY AUTO_INCREMENT,
    organizationName VARCHAR(30) NOT NULL,
    organizationDescription VARCHAR(100) NOT NULL,
    street VARCHAR(30) NOT NULL,
    city VARCHAR(20) NOT NULL,
    state CHAR(2) NOT NULL,
    zip CHAR(5) NOT NULL,
    MemberType varChar(30) NOT NULL
    );

CREATE TABLE organizationMembers(
	PRIMARY KEY (heroId, organizationId),
    organizationId INT NOT NULL,
	FOREIGN KEY fk_organization_organizationId(organizationId)
    REFERENCES organization(organizationId),
    heroId INT  NOT NULL,
   FOREIGN KEY fk_hero_heroId(heroId)
    REFERENCES hero(heroId)
    );
    
UPDATE sightings 
SET sightingDate = '2020-03-29',
sightingDescription = 'test'
WHERE sightingsId = 117;
    
    