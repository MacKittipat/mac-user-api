INSERT INTO user (username, email, password) VALUES
    ('user', 'user@user.com', 'password'),
    ('user2', 'user2@admin.com', 'password'),
    ('user3', 'user3@admin.com', 'password'),
    ('user4', 'user4@admin.com', 'password'),
    ('user5', 'user5@admin.com', 'password'),
    ('user6', 'user6@admin.com', 'password'),
    ('admin', 'admin@admin.com', 'password'),
    ('admin2', 'admin2@admin.com', 'password'),
    ('admin3', 'admin3@admin.com', 'password'),
    ('admin4', 'admin4@admin.com', 'password')
;

INSERT INTO role (name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN')
;

INSERT INTO user_role(user_id, role_id) VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1),
    (6, 1),
    (7, 2),
    (8, 2),
    (9, 2),
    (10, 2)
;

