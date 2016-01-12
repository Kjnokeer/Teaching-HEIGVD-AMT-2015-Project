DROP SCHEMA IF EXISTS easygoing;
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
   UNIQUE(email),
   PRIMARY KEY(id)
);


CREATE TABLE image
(
   id INT NOT NULL AUTO_INCREMENT,
   path VARCHAR(64) NOT NULL,
   text VARCHAR(256),
   creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   PRIMARY KEY(id)
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


INSERT INTO user (email, password, username, profile_photo) VALUES("thibaud.duchoud@heig-vd.ch", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", "Manamiz", "http://findicons.com/files/icons/1072/face_avatars/300/a02.png");
INSERT INTO user (email, password, username, profile_photo) VALUES("mario.ferreira@heig-vd.ch", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", "UnsafeDriving", "http://findicons.com/files/icons/1072/face_avatars/300/a01.png");
INSERT INTO user (email, password, username, profile_photo) VALUES("mathias.dolt@heig-vd.ch", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", "Kjnokeer", "https://cdn0.vox-cdn.com/images/verge/default-avatar.v9899025.gif");
INSERT INTO user (email, password, username, profile_photo) VALUES("jerome.moret@heig-vd.ch", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", "Blairôme", "https://cdn0.vox-cdn.com/images/verge/default-avatar.v9899025.gif");