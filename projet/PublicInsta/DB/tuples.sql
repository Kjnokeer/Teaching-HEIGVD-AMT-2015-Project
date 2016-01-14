USE publicInsta;

INSERT INTO user (email, password, username, profile_photo) VALUES("thibaud.duchoud@heig-vd.ch", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", "Manamiz", "http://findicons.com/files/icons/1072/face_avatars/300/a02.png");
INSERT INTO user (email, password, username, profile_photo) VALUES("mario.ferreira@heig-vd.ch", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", "UnsafeDriving", "http://findicons.com/files/icons/1072/face_avatars/300/a01.png");
INSERT INTO user (email, password, username, profile_photo) VALUES("mathias.dolt@heig-vd.ch", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", "Kjnokeer", "https://cdn0.vox-cdn.com/images/verge/default-avatar.v9899025.gif");
INSERT INTO user (email, password, username, profile_photo) VALUES("jerome.moret@heig-vd.ch", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", "Blairôme", "https://cdn0.vox-cdn.com/images/verge/default-avatar.v9899025.gif");

INSERT INTO image (path, text, user_id) VALUES("img/users/UnsafeDriving/image.jpg", "Beautiful!", 2);
INSERT INTO image (path, text, user_id) VALUES("img/users/Manamiz/rose.jpg", "A red rose.", 1);