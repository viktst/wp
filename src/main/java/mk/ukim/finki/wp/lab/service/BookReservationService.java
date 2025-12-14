package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookReservation;
import java.util.List;

public interface BookReservationService {
    BookReservation createReservation(Long bookId, String readerName,
                                      String readerAddress, Integer numberOfCopies);
    List<BookReservation> getAllReservations();
}