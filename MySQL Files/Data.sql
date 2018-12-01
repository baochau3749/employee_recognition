
# NOTES: THIS SCRIPT WILL REMOVE ALL EXISTING DATA IN THE DATABASE.

USE `cs467_employee_recognition` ;

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
	("Bao", "Chau", "Bao-Signature"),
    ("Carlos", "Leonard", "Carlos-ignature");
    
    
# Set data in the user table
ALTER TABLE user AUTO_INCREMENT = 1;
INSERT INTO user (email, password, role_id, user_profile_id) VALUES 
	("chaub@oregonstate.edu", "abc", 2,  1),
    ("leonarca@oregonstate.edu", "abc", 2, 2),
    ("allisbri@oregonstate.edu", "abc", 1, NULL),
    ("cs467.project@gmail.com", "abc", 1, NULL);

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
	("Benjamin", "Johnson", "ben@gmail.com", "Male", '1990-01-01', 1, 1, 1),
    ("Jayanna", "Smith", "jayanna@gmail.com", "Female", '1991-02-02', 1, 2, 2),
    ("Michael", "Martinez", "michael@gmail.com", "Male", '1992-03-03', 2, 1, 1),
    ("Britney", "Nguyen", "britney@gmail.com", "Female", '1993-04-04', 3, 3, 3),
    ("Dustin", "Chang", "dustin@gmail.com", "Male", '1994-05-05', 4, 4, 1);
    
# Set data in the award_type table
ALTER TABLE `award_type` AUTO_INCREMENT = 1;
INSERT INTO `award_type` (type) VALUES
	(Employee of the Month),
	(Leadership Skill),
	(Most Productive Employee);
	
# Set data in the award table
ALTER TABLE award AUTO_INCREMENT = 1;
INSERT INTO award (user_id, employee_id, award_type_id, date_time) VALUES 
	("", "", "", ""), 
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", ""),
	("", "", "", "");
	
	




