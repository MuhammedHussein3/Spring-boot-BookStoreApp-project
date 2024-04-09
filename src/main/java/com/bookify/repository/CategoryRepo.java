package com.bookify.repository;


import com.bookify.enitity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    @Query("""
select c from Category c
where c.name like concat('%',:query,'%')
""")
    List<Category> searchCategoriesBYSearchQuery(String query);


    Category findCategoriesByName(String name);



}
