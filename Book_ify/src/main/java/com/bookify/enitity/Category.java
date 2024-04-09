package com.bookify.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Data
@Entity
@Builder
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false,unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    private String description;

     public void addBook(Book book){
         books.add(book);
     }

}
