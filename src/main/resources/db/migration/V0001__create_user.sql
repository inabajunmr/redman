CREATE TABLE `User` (
  `user_id` VARCHAR(36) NOT NULL,
  `idp_user_name` VARCHAR NOT NULL,
  `email` VARCHAR NOT NULL,
  `idp_type` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE(idp_user_name, idp_type)
)
