 
INSERT INTO credentials(username,password,enabled) VALUES ('guest','guest', TRUE);
INSERT INTO credentials(username,password,enabled) VALUES ('admin','admin', TRUE);
 
INSERT INTO authority (id,username, authority,credentials_id) VALUES (1,'guest', 'ROLE_USER','guest');
INSERT INTO authority (id,username, authority,credentials_id) VALUES (2,'admin', 'ROLE_ADMIN','admin');
INSERT INTO authority (id,username, authority,credentials_id) VALUES (3,'admin', 'ROLE_USER','admin');

INSERT INTO  `MEMBER` (id,firstname, lastname,age,title,membernumber, member_id) VALUES (1,'Curious','George',12,'Boy Monkey', 8754,'admin');
INSERT INTO `MEMBER` (id,firstname, lastname,age,title,membernumber,member_id) VALUES (2,'Allen','Rench',123,'Torque Master', 8733,'guest');

INSERT INTO `ADDRESS` (id,city,state,street,member_id) VALUES (1,'Fairfield','Iowa','Main','1');
INSERT INTO `ADDRESS` (id,city,state,street,member_id) VALUES (2,'Batavia','Iowa','Maple','2');
INSERT INTO `ADDRESS` (id,city,state,street,member_id) VALUES (3,'Eldon','Iowa','Gothic','1');

INSERT INTO product(id,name,price) VALUES (1,"Bicycle",22.00);
INSERT INTO product(id,name,price) VALUES (2,"Tricycle",22.00);
INSERT INTO product(id,name,price) VALUES (3,"Unicycle",22.0); 
INSERT INTO product(id,name,price) VALUES (4,"Tandem Bicycle",22.0);
INSERT INTO product(id,name,price) VALUES (5,"Track Bicycle",22.0); 

INSERT INTO category(id,name) VALUES (1,"Toys");
INSERT INTO category(id,name) VALUES (2,"Sports");
INSERT INTO category(id,name) VALUES (3,"Recreation");


INSERT INTO category_product(Category_ID,Product_ID) VALUES (1,1);
INSERT INTO category_product(Category_ID,Product_ID) VALUES (1,2);
INSERT INTO category_product(Category_ID,Product_ID) VALUES (1,3); 
INSERT INTO category_product(Category_ID,Product_ID) VALUES (1,4); 
INSERT INTO category_product(Category_ID,Product_ID) VALUES (2,1);
INSERT INTO category_product(Category_ID,Product_ID) VALUES (2,2);
INSERT INTO category_product(Category_ID,Product_ID) VALUES (2,5);
INSERT INTO category_product(Category_ID,Product_ID) VALUES (3,1);
INSERT INTO category_product(Category_ID,Product_ID) VALUES (3,3);
INSERT INTO category_product(Category_ID,Product_ID) VALUES (3,2); 

