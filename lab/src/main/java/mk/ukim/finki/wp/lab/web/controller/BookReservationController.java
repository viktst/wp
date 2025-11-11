package mk.ukim.finki.wp.lab.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping("/bookReservation")
public class BookReservationController {

    private final BookReservationService reservationService;

    @PostMapping
    public String makeReservation(@RequestParam String bookTitle,
                                  @RequestParam String readerName,
                                  @RequestParam String readerAddress,
                                  @RequestParam Long numCopies,
                                  HttpServletRequest request,
                                  Model model) {

        if (readerName == null || readerAddress == null || readerName.isEmpty() || readerAddress.isEmpty()) {
            return "redirect:/books?error=EmptyFields";
        }

        var reservation = reservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);
        String clientIP = getClientIP(request);

        model.addAttribute("reservation", reservation);
        model.addAttribute("clientIP", clientIP);

        return "reservationConfirmation";
    }

    private String getClientIP(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
