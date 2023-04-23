DROP DATABASE IF EXISTS finalprojectdatabase;
CREATE DATABASE finalprojectdatabase;
USE finalprojectdatabase;

DROP TABLE IF EXISTS `UserTable`;

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

DROP TABLE IF EXISTS `EventTable`;

CREATE TABLE EventTable(
EventID int NOT NULL AUTO_INCREMENT,
UserID int,
EventName varchar(40),
Organizer varchar(40),
EventDescription varchar(500),
EventDate date,
EventTime datetime,
EventTimeEnd datetime,
ImgURL varchar(300),
PRIMARY KEY (EventID),
FOREIGN KEY (UserID) REFERENCES UserTable(UserID)
);

INSERT INTO EventTable (EventName, Organizer, EventDescription, EventDate, EventTime, EventTimeEnd, ImgURL)
VALUES ("Club Fundraiser", "USC Club", "Selling portos, balls $3 each, not balls $5 each", '2023-03-28',
'2023-03-28 10:00:00' , '2023-03-28 12:00:00', "https://cdn.vox-cdn.com/thumbor/HKzEesxfKMPrNiSGg8RTLg-wkHQ=/0x0:1936x1212/1200x800/filters:focal(814x452:1122x760)/cdn.vox-cdn.com/uploads/chorus_image/image/53444051/portos_bakery_potato_ball_flickr.0.jpg");
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventDate, EventTime, EventTimeEnd, ImgURL) 
VALUES ("ASG Fundraiser", "ASG Club", "Selling boba $5", '2023-04-08',
'2023-04-08 1:00:00' , '2023-04-08 3:00:00', "https://th.bing.com/th/id/OIP.aTo-dF7BPYOKwuTlfotJSAHaLG?w=128&h=192&c=7&r=0&o=5&dpr=2.5&pid=1.7");
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventTime, EventTimeEnd) 
VALUES ("Sports Fundraiser", "Intramural Sports Club", "Selling donuts $2 each, $5 for 3", '2023-03-28',
'2023-03-28 09:00:00' , '2023-03-28 11:00:00', "https://th.bing.com/th/id/OIP.1qHAtUqttF4YMOE6VOg4PwHaE7?pid=ImgDet&rs=1");


INSERT INTO EventTable (EventName, Organizer, EventDescription, EventDate, EventTime, EventTimeEnd, ImgURL)
VALUES ("Archery Fundraiser", "Archery Club", "Selling cookies $2 each", '2023-04-27',
'2023-04-27 10:00:00' , '2023-04-27 11:00:00', "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzdbTsO_p38L3P1fsIxax-o_OvrYptBrFqMg&usqp=CAU");
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventDate, EventTime, EventTimeEnd, ImgURL) 
VALUES ("Football Fundraiser", "Football Club", "Selling T-shirts $10 each", '2023-04-28',
'2023-04-28 11:00:00' , '2023-04-28 12:00:00', "https://nationaltoday.com/wp-content/uploads/2019/11/american-football-day.jpg.webp");
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventTime, EventTimeEnd) 
VALUES ("Soccer Fundraiser", "Soccer Club", "Selling soccer balls, $10 each", '2023-04-29',
'2023-04-29 10:00:00' , '2023-04-29 11:00:00', "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRLw4h6ornewoPvSvW50SFdabpybW_3ArbNg&usqp=CAU");
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventDate, EventTime, EventTimeEnd, ImgURL)
VALUES ("Esports Fundraiser", "Esports Club", "Selling donuts, $3 each", '2023-05-01',
'2023-05-01 10:00:00' , '2023-05-01 12:00:00', "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_kpAVYH6r39wwyfj7cGZjqozGC2XVhV9GDR81DbS0EXqSFKctKBNAOzfyE2UOZR502Wg&usqp=CAU");
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventDate, EventTime, EventTimeEnd, ImgURL) 
VALUES ("Choir Fundraiser", "Singing Club", "Selling energy drinks, $3 each", '2023-05-01',
'2023-05-01 14:00:00' , '2023-05-01 15:00:00', "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlNkSQivMeDKJM1TFOIhus94-swDfQ5MX35w&usqp=CAU");
INSERT INTO EventTable (EventName, Organizer, EventDescription, EventTime, EventTimeEnd) 
VALUES ("Band Fundraiser", "Band Club", "Selling brownies, $2 each", '2023-05-01',
'2023-05-01 08:00:00' , '2023-05-01 10:00:00', "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqQs9qzyDqsSaDWKUZng3PEd2sRgPjmOJaww&usqp=CAU");