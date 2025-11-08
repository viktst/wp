package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

    // @WebServlet(name = "book-reservation-servlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    //
    //    private final BookReservationService reservationService;
    //    private final SpringTemplateEngine templateEngine;
    //
    //    public BookReservationServlet(BookReservationService reservationService, SpringTemplateEngine templateEngine) {
    //        this.reservationService = reservationService;
    //        this.templateEngine = templateEngine;
    //    }
    //
    //    @Override
    //    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //        var application = JakartaServletWebApplication.buildApplication(req.getServletContext());
    //        var exchange = application.buildExchange(req, resp);
    //        WebContext context = new WebContext(exchange);
    //
    //        String bookTitle = req.getParameter("bookTitle");
    //        String readerName = req.getParameter("readerName");
    //        String readerAddress = req.getParameter("readerAddress");
    //        int numberOfCopies = Integer.parseInt(req.getParameter("numCopies"));
    //
    //        if (readerName == null || readerAddress == null || readerName.isEmpty() || readerAddress.isEmpty()) {
    //            req.getSession().setAttribute("errorMessage", "The name and address cannot be empty.");
    //            resp.sendRedirect("/?error");
    //            return;
    //        }
    //
    //        var reservation = reservationService.placeReservation(bookTitle, readerName, readerAddress, numberOfCopies);
    //        String clientIP = getClientIP(req);
    //
    //        context.setVariable("reservation", reservation);
    //        context.setVariable("clientIP", clientIP);
    //
    //        templateEngine.process("reservationConfirmation", context, resp.getWriter());
    //    }
    //
    //    private String getClientIP(HttpServletRequest request) {
    //        String xForwardedFor = request.getHeader("X-Forwarded-For");
    //        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
    //            return xForwardedFor.split(",")[0];
    //        }
    //        return request.getRemoteAddr();
    //    }
}