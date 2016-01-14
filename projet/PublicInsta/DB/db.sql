DROP SCHEMA IF EXISTS publicInsta;
CREATE SCHEMA publicInsta;

/* Creation of tables */
USE publicInsta;

CREATE TABLE user
(
   id INT NOT NULL AUTO_INCREMENT,
   email VARCHAR(64) NOT NULL,
   password VARCHAR(64) NOT NULL,
   username VARCHAR(64) NOT NULL,
   profile_photo VARCHAR(64),
   creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   session_id CHAR(32) NULL,
   ip CHAR(32) NULL,
   UNIQUE(email),
   UNIQUE(username),
   PRIMARY KEY(id)
);


CREATE TABLE image
(
   id INT NOT NULL AUTO_INCREMENT,
   path VARCHAR(64) NOT NULL,
   text VARCHAR(256),
   creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   
   user_id INT NOT NULL,
   
   PRIMARY KEY(id),
   FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE comment
(
   id INT NOT NULL AUTO_INCREMENT,
   text VARCHAR(256) NOT NULL,
   creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   user_id INT NOT NULL,
   image_id INT NOT NULL,

   FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE,
   FOREIGN KEY(image_id) REFERENCES image(id) ON DELETE CASCADE,

   PRIMARY KEY(id)
);

CREATE TABLE opinion
(
   id INT NOT NULL AUTO_INCREMENT,
   positive INT DEFAULT 0,
   negative INT DEFAULT 0,
   creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   user_id INT NOT NULL,
   image_id INT NOT NULL,

   FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE,
   FOREIGN KEY(image_id) REFERENCES image(id) ON DELETE CASCADE,

   PRIMARY KEY(id)
);