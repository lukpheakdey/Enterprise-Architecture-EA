 
DROP TABLE IF EXISTS EMPLOYEE;

create table employee( ID int primary key AUTO_INCREMENT NOT NULL, 
						firstName varchar(25),
						lastName varchar(25),
						age BIGINT,
						employeeNumber BIGINT,
						title varchar(25)
					);
					
DROP TABLE IF EXISTS location;

create table location( ID int primary key AUTO_INCREMENT NOT NULL, 
						street varchar(25),
						city varchar(25),
						state varchar(25),
 						zip varchar(25)
					);
					
ALTER TABLE employee ADD  COLUMN addressId BIGINT;
ALTER TABLE employee ADD CONSTRAINT ADDRESSREL FOREIGN KEY (addressId) REFERENCES location (id);
					