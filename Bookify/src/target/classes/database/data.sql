use book_ify;

SELECT * FROM products;


select * from products
   where name like 'Java with NoSql'
     and edition like 'First Edition';

select * from authors;

ALTER TABLE authors
    DROP COLUMN lived;

ALTER TABLE products
      DROP COLUMN create_on;

ALTER TABLE products
   MODIFY COLUMN disc_count INT;

ALTER TABLE products
    DROP COLUMN disc_count;

UPDATE products
SET quantity_in_stock = 2
WHERE name LIKE 'The Catcher in the Rye' AND edition LIKE 'First Edition';

ALTER TABLE products
        DROP COLUMN quantity_in_stock;

DROP TABLE products;

select p.name,p.price,p.price - (p.price * 0.2) as priceAfterDiscount,p.description
        ,a.name,a.birthdate,
       c.name,c.description
from products p
         inner join authors a on p.author_id = a.id
         inner join categories c on p.category_id = c.category_id
where p.name like 'The Da Vinci Code' and p.edition like 'First Edition';

ALTER TABLE authors
   DROP COLUMN email;

ALTER TABLE authors
    DROP COLUMN ip_address;

ALTER TABLE products
   RENAME books;
select *
from books;

ALTER TABLE books
DROP COLUMN category_id;

