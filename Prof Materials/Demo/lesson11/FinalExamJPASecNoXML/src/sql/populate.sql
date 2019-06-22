 INSERT INTO users(username,password,enabled) VALUES ('guest','$2a$10$0.ESlGysrPaiW5HaapKwoehzWt5AibgbPPOvMhDv8D6H26QQ/CwhS', TRUE);
INSERT INTO users(username,password,enabled) VALUES ('admin','$2a$10$S/wlXEo/APzf.Sn1cO2p4.V12EJmaw.uzrHelMvkpuahjmHWnSafe', TRUE);
 
INSERT INTO authorities (ID,username, authority) VALUES (1,'guest', 'ROLE_USER');
INSERT INTO authorities (ID,username, authority) VALUES (2,'admin', 'ROLE_ADMIN');
INSERT INTO authorities (ID,username, authority) VALUES (3,'admin', 'ROLE_USER');
 

INSERT INTO `location` VALUES (1,'442 Glenwood Avenue','Culver City','CA', '87547');
INSERT INTO `location` VALUES (2,'1 Yellow Brook Road','Emerald City','OZ', '12345');
 
INSERT INTO `employee` VALUES (1,12, 8754,'Curious','George','Boy Monkey',1,"guest");
INSERT INTO `employee` VALUES (2,123, 8733,'Allen','Rench','Torque Master',2,"admin");

					