 
INSERT INTO Authentication(user,password,enabled) VALUES ('guest','guest', TRUE);
INSERT INTO Authentication(user,password,enabled) VALUES ('admin','admin', TRUE);
 
INSERT INTO authority (username, authority) VALUES ('guest', 'ROLE_USER');
INSERT INTO authority (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authority (username, authority) VALUES ('admin', 'ROLE_USER');

INSERT INTO  `USERS` (firstname, lastname,email, version, is_admin,rank, userid) VALUES ('Curious','George','george@curious.com',0,0,0,'admin');
INSERT INTO `USERS` (firstname, lastname,email,version,is_admin,rank,userid) VALUES ('Allen','Rench','allen@wrench.com',0,0,0,'guest');

					