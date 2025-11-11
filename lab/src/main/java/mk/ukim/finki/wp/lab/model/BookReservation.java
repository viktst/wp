package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class BookReservation {
    private Long id;
    private String bookTitle;
    private String readerName;
    private String readerAddress;
    private Long numberOfCopies;
    private Book book;
}