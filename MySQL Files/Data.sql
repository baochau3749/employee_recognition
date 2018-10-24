
# NOTES: THIS SCRIPT WILL REMOVE ALL EXISTING DATA IN THE DATABASE.

USE `cs467_employee_recognition` ;

-- DELETE FROM user_role WHERE user_id > 0;
DELETE FROM user WHERE user_id > 0;
DELETE FROM role WHERE role_id > 0;
DELETE FROM user_profile WHERE user_profile_id > 0;

# Set data in the role table
ALTER TABLE role AUTO_INCREMENT = 1;
INSERT INTO role (role) VALUES ("ADMIN"), ("USER");

# Set data in the user_profile table
ALTER TABLE user_profile AUTO_INCREMENT = 1;
INSERT INTO user_profile (first_name, last_name, signature_file) VALUES 
	("Bao", "Chau", "Bao_signature"),
    ("Carlos", "Leonard", "Carlos_signature");
    
    
# Set data in the user table
ALTER TABLE user AUTO_INCREMENT = 1;
INSERT INTO user (email, password, role_id, user_profile_id) VALUES 
	("chaub@oregonstate.edu", "abc", 2,  1),
    ("leonarca@oregonstate.edu", "abc", 2, 2),
    ("allisbri@oregonstate.edu", "abc", 1, NULL),
    ("cs467.project@gmail.com", "abc", 1, NULL);



