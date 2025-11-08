package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

    // @WebServlet(name = "book-list-servlet", urlPatterns = "")
public class BookListServlet extends HttpServlet {
    //
    //    private final BookService bookService;
    //    private final SpringTemplateEngine templateEngine;
    //
    //    public BookListServlet(BookService bookService, SpringTemplateEngine templateEngine) {
    //        this.bookService = bookService;
    //        this.templateEngine = templateEngine;
    //    }
    //
    //    @Override
    //    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //        var application = JakartaServletWebApplication.buildApplication(req.getServletContext());
    //        var exchange = application.buildExchange(req, resp);
    //
    //        WebContext context = new WebContext(exchange);
    //
    //        String searchText = req.getParameter("searchText");
    //        String ratingStr = req.getParameter("rating");
    //
    //        if (searchText != null && !searchText.isEmpty() || ratingStr != null && !ratingStr.isEmpty()) {
    //            Double rating = ratingStr != null && !ratingStr.isEmpty() ? Double.parseDouble(ratingStr) : null;
    //            context.setVariable("books", bookService.searchBooks(searchText, rating));
    //            context.setVariable("searchText", searchText);
    //            context.setVariable("rating", ratingStr);
    //        } else {
    //            context.setVariable("books", bookService.listAll());
    //        }
    //
    //        templateEngine.process("listBooks", context, resp.getWriter());
    //    }
}