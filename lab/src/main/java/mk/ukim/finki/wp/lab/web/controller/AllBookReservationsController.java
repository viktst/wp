package mk.ukim.finki.wp.lab.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/allBookReservations")
public class AllBookReservationsController {

    private final BookReservationService reservationService;

    @GetMapping
    public String getAllReservationsPage(Model model) {
        model.addAttribute("reservations", reservationService.listAll());
        return "allBookReservations";
    }
}
