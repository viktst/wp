package mk.ukim.finki.wp.lab.config;

import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.web.BookListServlet;
import mk.ukim.finki.wp.lab.web.BookReservationServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<BookListServlet> bookListServlet(
            BookService bookService,
            SpringTemplateEngine templateEngine) {
        ServletRegistrationBean<BookListServlet> bean = new ServletRegistrationBean<>(
                new BookListServlet(bookService, templateEngine), "/");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean<BookReservationServlet> bookReservationServlet(
            BookReservationService reservationService,
            SpringTemplateEngine templateEngine) {
        ServletRegistrationBean<BookReservationServlet> bean = new ServletRegistrationBean<>(
                new BookReservationServlet(reservationService, templateEngine), "/bookReservation");
        bean.setLoadOnStartup(1);
        return bean;
    }
}