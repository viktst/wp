package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String genre;
    private double averageRating;
    private Author author;
}