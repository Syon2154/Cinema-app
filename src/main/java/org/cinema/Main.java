package org.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.cinema.exception.AuthenticationException;
import org.cinema.lib.Injector;
import org.cinema.model.CinemaHall;
import org.cinema.model.Movie;
import org.cinema.model.MovieSession;
import org.cinema.model.ShoppingCart;
import org.cinema.model.User;
import org.cinema.security.AuthenticationService;
import org.cinema.service.CinemaHallService;
import org.cinema.service.MovieService;
import org.cinema.service.MovieSessionService;
import org.cinema.service.OrderService;
import org.cinema.service.ShoppingCartService;

public class Main {
    private static final Injector injector = Injector
            .getInstance("org.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector
                .getInstance(MovieService.class);

        Movie fastAndFurious = new Movie("Avatar 2: The Way of Water");
        fastAndFurious.setDescription("The movie that many are waiting for");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("First hall with capacity 100");

        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("Second hall with capacity 200");

        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        cinemaHallService.add(firstCinemaHall);
        cinemaHallService.add(secondCinemaHall);

        System.out.println(cinemaHallService.getAll());
        System.out.println(cinemaHallService.get(firstCinemaHall.getId()));

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);

        System.out.println(movieSessionService.get(yesterdayMovieSession.getId()));
        System.out.println(movieSessionService.findAvailableSessions(
                        fastAndFurious.getId(), LocalDate.now()));

        AuthenticationService authenticationService = (AuthenticationService) injector
                .getInstance(AuthenticationService.class);
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        try {
            authenticationService.register("matthewmail@localhost", "SuperPass");
        } catch (Exception ex) {
            throw new RuntimeException("Can't register new user ", ex);
        }

        User matthew;
        try {
            matthew = authenticationService.login("matthewmail@localhost", "SuperPass");
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Login or password is incorrect ", ex);
        }

        OrderService orderService = (OrderService) injector
                .getInstance(OrderService.class);

        shoppingCartService.addSession(yesterdayMovieSession, matthew);
        shoppingCartService.addSession(tomorrowMovieSession, matthew);
        ShoppingCart matthewShoppingCart = shoppingCartService.getByUser(matthew);
        orderService.completeOrder(matthewShoppingCart);
        orderService.getOrdersHistory(matthew).forEach(System.out::println);
    }
}
