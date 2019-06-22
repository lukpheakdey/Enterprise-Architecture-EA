 
INSERT INTO Authentication(user,password,enabled) VALUES ('guest','guest', TRUE);
INSERT INTO Authentication(user,password,enabled) VALUES ('admin','admin', TRUE);
 
INSERT INTO authority (id,username, authority) VALUES (1,'guest', 'ROLE_USER');
INSERT INTO authority (id,username, authority) VALUES (2,'admin', 'ROLE_ADMIN');
INSERT INTO authority (id,username, authority) VALUES (3,'admin', 'ROLE_USER');

INSERT INTO  `USERS` (user_id,firstname, lastname,email, version, is_admin,rank, userid) VALUES (1,'Curious','George','george@curious.com',0,0,0,'admin');
INSERT INTO `USERS` (user_id,firstname, lastname,email,version,is_admin,rank,userid) VALUES (2,'Allen','Rench','allen@wrench.com',0,0,0,'guest');

					