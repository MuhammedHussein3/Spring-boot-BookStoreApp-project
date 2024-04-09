package com.bookify.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(staticName = "build")
@RequiredArgsConstructor
@Builder
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    @NotBlank(message = "product name shouldn't null and shouldn't empty")
    @Length(min = 1,max = 100,message = "product name length should >= 1 and <= 100")
    private String name;

    @NotBlank(message = "product description shouldn't null and shouldn't empty")
    private String description;


    @Min(1)
    @Max(100000000)
    private double price;

    @Transient
    private double priceAfterProduct;


    @Column(nullable = false)
    private String edition;

    @Formula("(select count(*) from books)")
    private int booksCount;

    private int quantityInStock;

    @NotBlank(message = "you should add author name")
    private String authorName;

    private LocalDateTime publishBook;



    @Column(name = "category_name")
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private String createDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private String lastModifiedDate;


    @PostLoad
    void discount(){
        setPriceAfterProduct(this.price-(this.price*0.2d));
    }
    public void addReview(Review review){
        reviews.add(review);
    }


}
