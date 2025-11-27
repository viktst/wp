package mk.ukim.finki.wp.lab.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class BookReservationController {

    private final BookReservationService reservationService;

    @PostMapping("/create")
    public String createReservation(@RequestParam Long bookId,
                                    @RequestParam String readerName,
                                    @RequestParam String readerAddress,
                                    @RequestParam Integer numberOfCopies) {

        try {
            reservationService.createReservation(bookId, readerName, readerAddress, numberOfCopies);
            return "redirect:/reservations/success";
        } catch (Exception e) {
            return "redirect:/books?error=" + e.getMessage();
        }
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "reservationConfirmation";
    }

    @GetMapping
    public String getAllReservations(Model model) {
        List<BookReservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);

        int totalBooksReserved = 0;
        Set<String> uniqueBookTitles = new HashSet<>();

        for (BookReservation reservation : reservations) {
            totalBooksReserved += reservation.getNumberOfCopies();
            uniqueBookTitles.add(reservation.getBookTitle());
        }

        model.addAttribute("totalBooksReserved", totalBooksReserved);
        model.addAttribute("uniqueBooksCount", uniqueBookTitles.size());

        return "allBookReservations";
    }
}