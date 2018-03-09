CREATE TABLE `User` (
  `user_id` VARCHAR(36) NOT NULL,
  `idp_user_name` VARCHAR,
  `idp_type` VARCHAR(20),
  PRIMARY KEY (`user_id`),
  UNIQUE(idp_user_name, idp_type)
)
