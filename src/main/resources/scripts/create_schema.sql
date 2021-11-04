Drop database if exists library;

create database if not exists library;
use library;

CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(30) DEFAULT NULL,
                         `email` varchar(40) DEFAULT NULL,
                         `sex` enum ('man','woman') DEFAULT NULL,
                         `telephone` varchar (15) DEFAULT NULL,
                         `role` enum ('admin','user','librarian') default null,
                         `ban_list` tinyint (1),
                         PRIMARY KEY (`id`));


CREATE TABLE `books`(
                        `id` int (11) NOT NULL auto_increment,
                        `author` varchar(30) DEFAULT NULL,
                        `book_name` varchar(30) DEFAULT NULL,
                        `book_edition` varchar (30) DEFAULT NULL,
                        `reliase_date` date DEFAULT NULL,
                        PRIMARY KEY (`id`));

CREATE TABLE `orders`(
                         `id` int (11) NOT NULL auto_increment,
                         `id_book` int (11) DEFAULT NULL,
                         `id_user` int (11) DEFAULT NULL,
                         `book_spot` enum ('abonement', 'library hall') DEFAULT NULL,
                         `status` enum ('expected','checked out' ,'returned'),
                         `return_date` date DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (id_book) REFERENCES books (id),
                         FOREIGN KEY (id_user) REFERENCES users (id));

CREATE TABLE `penalties`(
                            `id` int (11) NOT NULL auto_increment,
                            `user_id` int (11) DEFAULT NULL ,
                            `order_id` int (11) DEFAULT NULL ,
                            `penalty_cost` int (11) DEFAULT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (user_id) REFERENCES users (id),
                            FOREIGN KEY (order_id) REFERENCES orders (id));


CREATE TABLE `catalog`(
                          `id` int (11) NOT NULL auto_increment,
                          `book_id` int (11) DEFAULT NULL,
                          `count` int (11) DEFAULT NULL,
                          PRIMARY KEY (id),
                          FOREIGN KEY (book_id) REFERENCES books (id));








