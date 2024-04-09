package com.bookify;

import com.bookify.enitity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegrationTestH2BookRepo extends JpaRepository<Book,Long> {
}
