use library;
INSERT INTO books (author, book_name, book_edition, reliase_date)
values ("Джон Р. Р. Толкин", "Властелин колец", "Allen & Unwin", "1954-01-01" ),
       ("Джейн Остен", "Гордость и предубеждение", "George Allen", "1894-01-02" );

INSERT INTO users ( name , email, sex, telephone, role, ban_list)
values ( "Михальцов Дмитрий", "mikhaltsovda@gmail.com", "man", "380935027130", "user", "0"),
       ( "Михальцова Валерия", "valeriiafatova@gmail.com", "woman", "380954817130", "user", "0");

insert into catalog ( book_id, count ) values ( 1 , "1" );

insert into orders ( id_book, id_user, book_spot, status, return_date ) values ( 2, 2, "abonement", "expected", "2021-12-11");

insert into penalties ( user_id, order_id, penalty_cost ) values ( 2, 1 , "300" )