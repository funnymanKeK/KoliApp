insert into room (level, number) values (5, 555);

insert into user (username, password, role, email, room_id) values ('admin', '$2a$10$Xtv0umJn1DDZ3ds5FTWHUOHuwJnRQ0qrYpTA1ANaRl8vdiwo1R1iW', 'ROLE_ADMIN', 'viktorvagyok@12.hu', 1);
-- password: admin
insert into user (username, password, role, email, room_id) values ('user', '$2a$10$QUhsliTs8Ufe9nSQgIwzzeDktWOdDw8WY77lN.3AAXL5vVDI2EoVO', 'ROLE_USER', 'viktorvagyok@12.hu', 1);
-- password: user

insert into post (user_id, title, text, room_id) values (1, 'posztteszt', 'ez egy teszt a posztra', 1);

insert into comment (user_id, post_id, text) values (1, 1, 'asdasdasd');

insert into comment (user_id, post_id, text) values (1, 1, 'asdasdasd');