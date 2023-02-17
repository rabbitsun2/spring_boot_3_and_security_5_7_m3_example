CREATE TABLE user_list(

	NAME VARCHAR(20) PRIMARY KEY,
	PASSWORD VARCHAR(100),
	authority VARCHAR(20),
	enabled int(1)
);

INSERT INTO user_list VALUES('user', '$2a$10$Nfns5OSxsop3yzq4hPZTNenYPk6mc3yjyc6FVxlaNfz2NN68x3lwK', 'ROLE_USER', 1);
INSERT INTO user_list VALUES('admin', '$2a$10$Nfns5OSxsop3yzq4hPZTNenYPk6mc3yjyc6FVxlaNfz2NN68x3lwK', 'ROLE_ADMIN', 1);

COMMIT;dyjung