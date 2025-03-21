package com.ascend.crud.model.DTO;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private LocalDate publishedDate;
}
