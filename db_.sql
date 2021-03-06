CREATE DATABASE PIZZA_ORDERING_SYSTEM;
USE PIZZA_ORDERING_SYSTEM;

CREATE TABLE FELHASZNALO(
FID CHAR(3) PRIMARY KEY,
NEV VARCHAR(20) NOT NULL,
CIM VARCHAR(30) NOT NULL,
TELEFON VARCHAR(12));

CREATE TABLE PIZZA(
PID CHAR(4) PRIMARY KEY,
AR INT,
MERET CHAR(5),
LEIRAS VARCHAR(50),
SUTIDO INT NOT NULL);

CREATE TABLE SUTOK(
SID CHAR(3) PRIMARY KEY,
FOGLALT INT);


CREATE TABLE RENDELES(
RID CHAR(5) PRIMARY KEY,
FID CHAR(3) NOT NULL,
PID CHAR(4) NOT NULL,
SID CHAR(3) NOT NULL,
RSZAM INT NOT NULL,
AR INT,
FELVETELIDO TIMESTAMP NOT NULL,
KESZIDO TIMESTAMP NOT NULL,
FOREIGN KEY (FID) REFERENCES FELHASZNALO(FID),
FOREIGN KEY (PID) REFERENCES PIZZA(PID),
FOREIGN KEY (SID) REFERENCES SUTOK(SID));



INSERT INTO SUTOK VALUES('S01',0);
INSERT INTO SUTOK VALUES('S02',0);
INSERT INTO SUTOK VALUES('S03',0);
INSERT INTO SUTOK VALUES('S04',0);
INSERT INTO SUTOK VALUES('S05',0);
INSERT INTO SUTOK VALUES('S06',0);
INSERT INTO SUTOK VALUES('S07',0);
INSERT INTO SUTOK VALUES('S08',0);
INSERT INTO SUTOK VALUES('S09',0);
INSERT INTO SUTOK VALUES('S10',0);