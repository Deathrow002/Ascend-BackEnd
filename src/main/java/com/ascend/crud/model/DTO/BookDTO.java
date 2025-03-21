package com.ascend.crud.model.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String publishedDate;  // Buddhist calendar date (String)

    // Convert Gregorian to Buddhist calendar
    public void setPublishedDate(String publishedDate) {
        int gregorianYear = Integer.parseInt(publishedDate.split("-")[0]);
        int buddhistYear = gregorianYear + 543;
        this.publishedDate = buddhistYear + publishedDate.substring(4); // Retain month and day part
    }
}
