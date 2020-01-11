INSERT INTO user (id, username, email, password) VALUES
  (1, 'user', 'user@user.com', 'password'),
  (2, 'admin', 'admin@admin.com', 'password');

INSERT into user_role (id, role, user_id) VALUES
 (1, 'ROLE_USER', 1),
 (2, 'ROLE_ADMIN', 2);
