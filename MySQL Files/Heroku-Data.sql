
# NOTES: THIS SCRIPT WILL REMOVE ALL EXISTING DATA IN THE DATABASE.

USE `heroku_73fe7765a7c5676` ;

-- DELETE FROM user_role WHERE user_id > 0;
DELETE FROM `award` WHERE award_id > 0;
DELETE FROM user WHERE user_id > 0;
DELETE FROM role WHERE role_id > 0;
DELETE FROM user_profile WHERE user_profile_id > 0;
DELETE FROM employee WHERE employee_id > 0;
DELETE FROM state WHERE state_id > 0;
DELETE FROM department WHERE department_id > 0;
DELETE FROM `position` WHERE position_id > 0;
DELETE FROM `award_type` WHERE award_type_id > 0;


# Set data in the role table
ALTER TABLE role AUTO_INCREMENT = 1;
INSERT INTO role (role) VALUES ("ADMIN"), ("USER");

# Set data in the user_profile table
ALTER TABLE user_profile AUTO_INCREMENT = 1;
INSERT INTO user_profile (first_name, last_name, signature_file) VALUES 
	("Bao", "Chau", "Bao-Signature.png"),
    ("Carlos", "Leonard", "Carlos-Signature.png"),
    ("John", "Doe", "signature.png");
    
    
# Set data in the user table
ALTER TABLE user AUTO_INCREMENT = 1;
INSERT INTO user (email, password, role_id, user_profile_id) VALUES 
	("chaub@oregonstate.edu", "abc", 11,  1),
    ("leonarca@oregonstate.edu", "abc", 11, 11),
    ("allisbri@oregonstate.edu", "abc", 1, NULL),
    ("cs467.project@gmail.com", "abc", 1, NULL);
    ("baochau7954@gmail.com", "abc", 11, 21);


# Set data in the state table
ALTER TABLE state AUTO_INCREMENT = 1;
INSERT INTO state (state) VALUES 
	("California"),
    ("Delaware"),
    ("New Jersey"),
    ("New York"),
    ("North Dakota"),
    ("Oregon"),
    ("Pennsylvania"),
    ("South Dakota"),
    ("Texas"),    
    ("Washington");
    
# Set data in the department table
ALTER TABLE department AUTO_INCREMENT = 1;
INSERT INTO department (department) VALUES 
	("Engineering"),
    ("Human Resources"),
    ("Marketing"),    
    ("Production"),
    ("Public Relations"),
    ("Research");
    
# Set data in the position table
ALTER TABLE `position` AUTO_INCREMENT = 1;
INSERT INTO `position` (position) VALUES 
	("Application Developer"),
    ("Assembler"),
    ("Engineer"),
    ("Manager"),        
    ("Recruiter"),
    ("Researcher"),
    ("Secretary"),                
    ("Systems Analyst"),
    ("Technician");
    
# Set data in the employee table
ALTER TABLE employee AUTO_INCREMENT = 1;
INSERT INTO employee (first_name, last_name, email, gender, birth_date, state_id, position_id, department_id) VALUES 
	("Benjamin", "Johnson", "ben@gmail.com", 		"Male", 	'1990-01-01', 1,  31,  1),
    ("Jayanna", "Smith", "jayanna@gmail.com",   	"Female", 	'1991-02-02', 21, 1,  11),
    ("Michael", "Martinez", "michael@gmail.com",	"Male", 	'1992-03-03', 31, 21, 21),
    ("Britney", "Nguyen", "britney@gmail.com", 		"Female", 	'1993-04-04', 81, 1,  31),
    ("Dustin", "Chang", "dustin@gmail.com", 		"Male", 	'1994-05-05', 81, 31,  1),
    ("Sofia", "Lee", "sofia@gmail.com", 			"Female",	'1995-06-06', 91, 21, 11),
    ("Kelvin", "Kumar", "kelvin@gmail.com",			"Male",		'1996-07-07', 1,  1,  21),
    ("Rachael", "Simon", "rachael@gmail.com",		"Female", 	'1997-08-08', 1,  1,  31),
    ("Nathan", "Patel", "nathan@gmail.com", 		"Male", 	'1998-09-09', 21, 21, 41),
    ("Isabella", "Wong", "isabella@gmail.com",		"Female", 	'1999-10-10', 31, 61, 51);
    
# Set data in the award_type table
ALTER TABLE `award_type` AUTO_INCREMENT = 1;
INSERT INTO `award_type` (type) VALUES
	(Employee of the Month),
	(Leadership Skill),
	(Most Productive Employee);
	
# Set data in the award table
ALTER TABLE award AUTO_INCREMENT = 1;
INSERT INTO award (user_id, employee_id, award_type_id, date_time) VALUES 
	("1", 	"1", 	"1", 	"2018-10-01 12:00 AM"), 
	("11", 	"11", 	"21", 	"2018-10-02 12:00 AM"),
	("41", 	"21", 	"31", 	"2018-10-03 12:00 AM"),
	("1", 	"31", 	"1", 	"2018-10-04 12:00 AM"), 
	("11", 	"41", 	"21", 	"2018-10-05 12:00 AM"),
	("41", 	"51", 	"31", 	"2018-10-06 12:00 AM"),
	("1", 	"61", 	"1", 	"2018-10-07 12:00 AM"), 
	("11", 	"71", 	"21", 	"2018-10-08 12:00 AM"),
	("41", 	"81", 	"31", 	"2018-10-09 12:00 AM"),
	("1", 	"91", 	"1", 	"2018-11-01 12:00 AM"), 
	("11", 	"1", 	"21", 	"2018-11-02 12:00 PM"),
	("41", 	"11", 	"31", 	"2018-11-03 12:00 PM"),
	("1", 	"21", 	"1", 	"2018-11-04 12:00 PM"), 
	("11", 	"31", 	"21", 	"2018-11-05 12:00 PM"),
	("41", 	"41", 	"31", 	"2018-11-06 12:00 PM"),
	("1", 	"51", 	"1", 	"2018-11-07 12:00 PM"), 
	("11", 	"61", 	"21", 	"2018-11-08 12:00 PM"),
	("1", 	"71", 	"31", 	"2018-11-09 12:00 PM"),		
	("11", 	"81", 	"1", 	"2018-11-10 12:00 PM"),
	("1", 	"91", 	"21", 	"2018-11-11 12:00 PM");    
    
