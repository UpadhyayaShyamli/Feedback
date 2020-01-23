

INSERT IGNORE INTO `role` (`role_id`, `rolename`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');



INSERT IGNORE INTO `users` (`user_id`, `created_on`, `mobile_number`, `password`, `user_email_id`,`user_name`) VALUES
(1, '2019-Dec-28 09:57:48', '98765400', '$2a$10$7sFPuv1oAYlgVQSzzCdQwe5fo28SYUJZ7jsIdJJXtMfccghn7sknq', 'admin@aroha.co.in', 'Admin');

INSERT IGNORE INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1);

