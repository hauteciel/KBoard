ALTER TABLE t5_user
ADD COLUMN provider VARCHAR(40);

ALTER TABLE t5_user
ADD COLUMN providerId VARCHAR(200);

DESC t5_user;

SELECT * FROM t5_user ORDER BY id DESC;