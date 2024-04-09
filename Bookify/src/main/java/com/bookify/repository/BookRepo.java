package com.bookify.repository;

import com.bookify.Projctions.BookProjection;
import com.bookify.enitity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface BookRepo extends JpaRepository<Book,Long> {

    @Transactional
    @Modifying()
    @Query("""
UPDATE Book b
SET b.quantityInStock = :q
WHERE b.name like :name AND b.edition like :edition
""")
    int plusSameBook(String name,String edition,int q);

    @Query(value = """
select b.name as title,b.edition, b.price,b.price - (b.price * 0.2) as priceAfterDiscount
,b.description
,b.author_name as 'authorName',b.publish_book as 'publishBook',b.category_name
from books b
where b.name like concat('%',:q,'%')
""",nativeQuery = true)
    List<BookProjection> searchProducts(String q);

   @Query("""
SELECT b FROM Book b
WHERE b.name LIKE :name AND b.edition LIKE :edition
""")
   Book SearchForAdd(String name, String edition);
    Book findProductByName(String name);

   @Transactional
    @Modifying()
    @Query("""
DELETE Book b
WHERE b.name LIKE :name AND b.edition LIKE :edition
""")
   void deleteProduct(String name,String edition);

   }
