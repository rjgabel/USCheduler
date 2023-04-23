DROP DATABASE IF EXISTS finalprojectdatabase;
CREATE DATABASE finalprojectdatabase;
USE finalprojectdatabase;

CREATE TABLE UserTable (
UserID int NOT NULL AUTO_INCREMENT,
username varchar(40),
password varchar(40),
displayName varchar(40),
email varchar(40),
PRIMARY KEY (UserID)
);

INSERT INTO UserTable (username, password, displayName, email) 
VALUES ("patel42", "asdf1234", "lordpatel", "swapatel@usc.edu");
INSERT INTO UserTable (username, password, displayName, email) 
VALUES ("djli453", "asdf12345", "djli", "devinli@usc.edu");
INSERT INTO UserTable (username, password, displayName, email) 
VALUES ("gigachad", "asdf123456", "thechad", "cpowers@usc.edu");

CREATE TABLE EventTable(
EventID int NOT NULL AUTO_INCREMENT,
UserID int,
EventName varchar(40),
Organizer varchar(40),
EventDescription varchar(500),
EventTime datetime,
EventTimeEnd datetime, 
PRIMARY KEY (EventID),
FOREIGN KEY (UserID) REFERENCES UserTable(UserID)
);

INSERT INTO EventTable (EventName, Organizer, EventDescription, EventTime, EventTimeEnd) 
VALUES ("Club Fundraiser", "USC Club", "Selling portos, balls $3 each, not balls $5 each", 
'2023-03-28 10:00:00' , '2023-03-28 12:00:00');
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventTime, EventTimeEnd) 
VALUES ("ASG Fundraiser", "ASG Club", "Selling boba $5", 
'2023-04-08 1:00:00' , '2023-04-08 3:00:00');
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventTime, EventTimeEnd) 
VALUES ("Sports Fundraiser", "Intramural Sports Club", "Selling donuts $2 each, $5 for 3", 
'2023-03-28 09:00:00' , '2023-03-28 11:00:00');


SELECT * FROM UserTable;
SELECT * FROM EventTable;