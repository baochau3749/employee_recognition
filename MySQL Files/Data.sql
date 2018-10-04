
# NOTES: THIS SCRIPT WILL REMOVE ALL EXISTING DATA IN THE DATABASE.

USE `cs467_employee_recognition` ;



# Set data in the role table
DELETE FROM role WHERE role_id > 0;
ALTER TABLE role AUTO_INCREMENT = 1;
INSERT INTO role (role) VALUES ("ADMIN"), ("USER");

# Set data in the user_profile table
DELETE FROM user_profile WHERE user_profile_id > 0;
ALTER TABLE user_profile AUTO_INCREMENT = 1;
INSERT INTO user_profile (first_name, last_name, signature_file) VALUES 
	("Bao", "Chau", "Bao_signature"),
    ("Carlos", "Leonard", "Carlos_signature");

# Set data in the user table
DELETE FROM user WHERE user_id > 0;
ALTER TABLE user AUTO_INCREMENT = 1;
INSERT INTO user (email, password, user_profile_id) VALUES 
	("chaub@oregonstate.edu", "{noop}abc", 1),
    ("leonarca@oregonstate.edu", "{noop}abc", 2),
    ("allisbri@oregonstate.edu", "{noop}abc", NULL);

# Set data in the user_role table
DELETE FROM user_role WHERE user_id > 0;
ALTER TABLE role AUTO_INCREMENT = 1;
INSERT INTO user_role (user_id, role_id) VALUES 
	(1, 2),
    (2, 2),
    (3, 1);
    

