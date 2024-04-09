package com.bookify;

import com.bookify.enitity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegrationTestH2CategoryRepo extends JpaRepository<Category,Long> {
}
