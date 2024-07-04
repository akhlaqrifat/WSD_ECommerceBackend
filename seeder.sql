-- create table refresh_token
-- (
--     id         bigserial
--         primary key,
--     created_on timestamp with time zone default now(),
--     is_active  boolean                  default true,
--     updated_on timestamp with time zone default now(),
--     token      varchar(255),
--     user_id    bigint
-- );
--
-- alter table refresh_token
--     owner to postgres;
--
-- create table role
-- (
--     id         bigserial
--         primary key,
--     created_on timestamp with time zone default now(),
--     is_active  boolean                  default true,
--     updated_on timestamp with time zone default now(),
--     name       varchar(255)
-- );
--
-- alter table role
--     owner to postgres;
--
-- create table users
-- (
--     id         bigserial
--         primary key,
--     created_on timestamp with time zone default now(),
--     is_active  boolean                  default true,
--     updated_on timestamp with time zone default now(),
--     email      varchar(255),
--     full_name  varchar(255),
--     password   varchar(255),
--     phone      varchar(255),
--     user_name  varchar(255),
--     role_id    bigint
--         constraint fk4qu1gr772nnf6ve5af002rwya
--             references role
-- );
--
-- alter table users
--     owner to postgres;
--
-- create table category
-- (
--     id         bigserial
--         primary key,
--     created_on timestamp with time zone default now(),
--     is_active  boolean                  default true,
--     updated_on timestamp with time zone default now(),
--     name       varchar(255)
-- );
--
-- alter table category
--     owner to postgres;
--
-- create table product
-- (
--     id          bigserial
--         primary key,
--     created_on  timestamp with time zone default now(),
--     is_active   boolean                  default true,
--     updated_on  timestamp with time zone default now(),
--     name        varchar(255),
--     price       double precision,
--     category_id bigint
--         constraint fk1mtsbur82frn64de7balymq9s
--             references category
-- );
--
-- alter table product
--     owner to postgres;
--
-- create table sale
-- (
--     id            bigserial
--         primary key,
--     created_on    timestamp with time zone default now(),
--     is_active     boolean                  default true,
--     updated_on    timestamp with time zone default now(),
--     paid_amount   double precision,
--     product_count integer not null,
--     customer_id   bigint
--         constraint fkrhld4cqdhmv9rmitpeolnqsf
--             references users
-- );
--
-- alter table sale
--     owner to postgres;
--
-- create table sale_product
-- (
--     sale_id    bigint not null
--         constraint fk4dtibi1vwxkx8gjs59nhp0cnq
--             references sale,
--     product_id bigint not null
--         constraint fkrtwiisrmdqeslt86pacdwwn1o
--             references product
-- );
--
-- alter table sale_product
--     owner to postgres;
--
-- create table wishlist
-- (
--     id          bigserial
--         primary key,
--     created_on  timestamp with time zone default now(),
--     is_active   boolean                  default true,
--     updated_on  timestamp with time zone default now(),
--     customer_id bigint
--         constraint uk_i62hn96gwmmykqrbf8j2heo6b
--             unique
--         constraint fke465y0vkoa367ur8olqbw5cm4
--             references users
-- );
--
-- alter table wishlist
--     owner to postgres;
--
-- create table wishlist_product
-- (
--     wishlist_id bigint not null
--         constraint fk6qi207s5p27bm3qmkxpk1fv8o
--             references wishlist,
--     product_id  bigint not null
--         constraint fksqs4r107po6y96en1si6pryx7
--             references product
-- );
--
-- alter table wishlist_product
--     owner to postgres;

INSERT INTO role (name) VALUES ('ROLE_ADMIN'),('ROLE_USER'),('ROLE_CUSTOMER');

INSERT INTO users (user_name, email, password, full_name, phone, role_id) VALUES
                                                                                                  ('janedoe', 'janedoe@example.com', 'password456', 'Jane Doe', '123-456-7891', 3),
                                                                                                  ('alicewonder', 'alice@example.com', 'alice789', 'Alice Wonderland', '123-456-7892', 3),
                                                                                                  ('bobsmith', 'bobsmith@example.com', 'bobpassword', 'Bob Smith', '123-456-7893', 3),
                                                                                                  ('charliebrown', 'charliebrown@example.com', 'charliepwd', 'Charlie Brown', '123-456-7894', 3),
                                                                                                  ('dianaross', 'dianaross@example.com', 'dianar123', 'Diana Ross', '123-456-7895', 3),
                                                                                                  ('eveadams', 'eveadams@example.com', 'evepassword', 'Eve Adams', '123-456-7896', 3),
                                                                                                  ('frankjones', 'frankjones@example.com', 'frankpwd', 'Frank Jones', '123-456-7897', 3),
                                                                                                  ('gracelee', 'gracelee@example.com', 'grace123', 'Grace Lee', '123-456-7898', 3),
                                                                                                  ('hankhill', 'hankhill@example.com', 'hankpassword', 'Hank Hill', '123-456-7899', 3),
                                                                                                  ('Admin_User_88881','admin@email.com','$2a$10$yW0Gk.wzygjST7Uh3F1Rn.SjXU/I4Gjzn6zfb3iRE1UQu6c9bOboy','Admin User','+8801888888888',2);


INSERT INTO Category (name) VALUES
                                ('Electronics'),
                                ('Books'),
                                ('Clothing'),
                                ('Home & Kitchen'),
                                ('Sports & Outdoors'),
                                ('Health & Personal Care'),
                                ('Toys & Games'),
                                ('Automotive'),
                                ('Beauty'),
                                ('Office Supplies');


INSERT INTO Product (name, price, category_id) VALUES
                                                   ('Smartphone', 699.99, 1),
                                                   ('Laptop', 999.99, 1),
                                                   ('Tablet', 399.99, 1),
                                                   ('Novel', 19.99, 2),
                                                   ('Textbook', 89.99, 2),
                                                   ('T-Shirt', 14.99, 3),
                                                   ('Jeans', 39.99, 3),
                                                   ('Blender', 49.99, 4),
                                                   ('Vacuum Cleaner', 149.99, 4),
                                                   ('Running Shoes', 79.99, 5),
                                                   ('Yoga Mat', 24.99, 5),
                                                   ('Vitamins', 29.99, 6),
                                                   ('Shampoo', 9.99, 6),
                                                   ('Board Game', 34.99, 7),
                                                   ('Toy Car', 19.99, 7),
                                                   ('Car Oil', 24.99, 8),
                                                   ('Car Battery', 119.99, 8),
                                                   ('Lipstick', 14.99, 9),
                                                   ('Mascara', 12.99, 9),
                                                   ('Notebook', 2.99, 10),
                                                   ('Pen', 1.49, 10);


-- Sales for May 2024
INSERT INTO sale (customer_id, product_count, paid_amount, created_on) VALUES
                                                                           (1, 2, 1399.98, '2024-05-01'),
                                                                           (2, 1, 19.99, '2024-05-03'),
                                                                           (3, 3, 119.97, '2024-05-05'),
                                                                           (4, 1, 149.99, '2024-05-07'),
                                                                           (5, 2, 49.98, '2024-05-09'),
                                                                           (6, 1, 999.99, '2024-05-11'),
                                                                           (7, 4, 39.96, '2024-05-13'),
                                                                           (8, 1, 24.99, '2024-05-15'),
                                                                           (9, 10, 14.90, '2024-05-17'),
                                                                           (9, 2, 29.98, '2024-05-19');

-- Sales for June 2024
INSERT INTO sale (customer_id, product_count, paid_amount, created_on) VALUES
                                                                           (1, 1, 999.99, '2024-06-01'),
                                                                           (2, 2, 179.98, '2024-06-03'),
                                                                           (3, 4, 59.96, '2024-06-05'),
                                                                           (4, 1, 24.99, '2024-06-07'),
                                                                           (5, 10, 14.90, '2024-06-09'),
                                                                           (6, 1, 399.99, '2024-06-11'),
                                                                           (7, 1, 9.99, '2024-06-13'),
                                                                           (8, 1, 34.99, '2024-06-15'),
                                                                           (9, 2, 39.98, '2024-06-17'),
                                                                           (9, 1, 119.99, '2024-06-19');


INSERT INTO sale (customer_id, product_count, paid_amount, created_on) VALUES
                                                                           (1, 2, 1399.98, '2024-07-01'),
                                                                           (2, 1, 19.99, '2024-07-04'),
                                                                           (3, 3, 119.97, '2024-07-04'),
                                                                           (4, 1, 149.99, '2024-07-15'),
                                                                           (5, 2, 49.98, '2024-07-20'),
                                                                           (6, 1, 999.99, '2024-07-22'),
                                                                           (7, 4, 39.96, '2024-07-24'),
                                                                           (8, 1, 24.99, '2024-07-26'),
                                                                           (9, 10, 14.90, '2024-07-28'),
                                                                           (9, 2, 29.98, '2024-07-30');


-- Sale-Product mapping for May 2024
INSERT INTO sale_product (sale_id, product_id) VALUES
                                                   (1, 1), (1, 2),
                                                   (2, 4),
                                                   (3, 7), (3, 8), (3, 9),
                                                   (4, 10),
                                                   (5, 11), (5, 12),
                                                   (6, 3),
                                                   (7, 5), (7, 6),
                                                   (8, 1),
                                                   (9, 2),
                                                   (10, 4), (10, 5);

-- Sale-Product mapping for June 2024
INSERT INTO sale_product (sale_id, product_id) VALUES
                                                   (11, 2),
                                                   (12, 4), (12, 5),
                                                   (13, 7), (13, 8), (13, 9), (13, 10),
                                                   (14, 11),
                                                   (15, 12), (15, 1),
                                                   (16, 3),
                                                   (17, 6),
                                                   (18, 5),
                                                   (19, 2), (19, 3),
                                                   (20, 4);

-- Sale-Product mapping for July 2024
INSERT INTO sale_product (sale_id, product_id) VALUES
                                                   (21, 1), (21, 2),
                                                   (22, 4),
                                                   (23, 7), (23, 8), (23, 9),
                                                   (24, 10),
                                                   (25, 11), (25, 12),
                                                   (26, 3),
                                                   (27, 5), (27, 6),
                                                   (28, 1),
                                                   (29, 2),
                                                   (30, 4), (30, 5);



INSERT INTO wishlist (customer_id, created_on) VALUES
                                                   (1, '2024-06-01'),
                                                   (2, '2024-06-03'),
                                                   (3, '2024-06-05'),
                                                   (4, '2024-06-07'),
                                                   (5, '2024-06-09');


INSERT INTO wishlist_product (wishlist_id, product_id) VALUES
                                                           (1, 1), (1, 2), (1, 3),
                                                           (2, 4), (2, 5),
                                                           (3, 6), (3, 7),
                                                           (4, 8), (4, 9), (4, 10),
                                                           (5, 11), (5, 12);


