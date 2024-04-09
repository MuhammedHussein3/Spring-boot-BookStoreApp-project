package com.bookify.Projctions;

import com.bookify.enitity.Review;

import java.sql.Date;
import java.util.List;

public interface BookProjection {
     String getTitle();
     String getEdition();
     double getPrice();
     double getPriceAfterDiscount();
     String description();
     String getAuthorName();
     String getPublishBook();
     String categoryName();

}
