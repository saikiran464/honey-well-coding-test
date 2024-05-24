package com.honeywell.controller;

import com.honeywell.Cart;
import com.honeywell.objects.Seat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/bookmovietickets")
public class MovieTicketController {
    private static Cart cart = new Cart();
    private static List<Seat> seats = Stream.iterate(1, n -> n + 1)
            .limit(10)
            .map(n -> new Seat("Seat" + n))
            .collect(Collectors.toList());

    @GetMapping
    public String showSeats(Model model) {
        model.addAttribute("seats", seats);
        model.addAttribute("cart", cart);
        return "seats";
    }

    @PostMapping("/login")
    public ModelAndView bookSeat(@RequestParam String username) {
        ModelAndView modelAndView = new ModelAndView();
        if ("admin".equals(username)) {
            modelAndView.setViewName("redirect:/booktickets"); // Redirect to a dashboard page if successful
        } else {
            modelAndView.setViewName("redirect:/login"); // Redirect back to login page if fail
            modelAndView.addObject("error", "Invalid username or password");
        }
        return modelAndView;
    }

    @GetMapping("/booktickets/{seatNumber}")
    public String bookSeat(@PathVariable String seatNumber, Model model) {
        seats.stream()
                .filter(seat -> seat.getSeatNumber().equals(seatNumber) && !seat.isBooked())
                .findFirst()
                .ifPresent(cart::addSeat);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        Cart cart = (Cart) model.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();  // Initialize a new cart if none exists
            model.addAttribute("cart", cart);
        }
        return "cart";
    }
}
