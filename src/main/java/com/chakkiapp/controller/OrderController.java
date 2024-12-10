package com.chakkiapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private Map<String, Integer> cart = new HashMap<>(); // Key: Grain name, Value: Quantity

    @GetMapping("/grains")
    public List<String> getAvailableGrains() {
        return List.of("Wheat", "Rice", "Barley", "Oats", "Corn");
    }


    // API to serve the place-order HTML page
    @GetMapping("/place-order")
    public String getPlaceOrderPage(Model model) {
        // Add grains data to the model for Thymeleaf rendering (if required)
        List<Map<String, String>> grains = List.of(
                Map.of("name", "Wheat", "price", "10"),
                Map.of("name", "Rice", "price", "10"),
                Map.of("name", "Barley", "price", "10"),
                Map.of("name", "Oats", "price", "10"),
                Map.of("name", "Corn", "price", "10")
        );
        model.addAttribute("grains", grains);

        // Return the Thymeleaf template name
        return "order-placement";

    }


    @PostMapping("/add-to-cart")
    public Map<String, Object> addToCart(@RequestParam String grain, @RequestParam int quantity) {
        cart.put(grain, cart.getOrDefault(grain, 0) + quantity);
        return calculateCart();
    }

    @GetMapping("/cart")
    public Map<String, Object> getCart() {
        return calculateCart();
    }

    private Map<String, Object> calculateCart() {
        Map<String, Object> cartDetails = new HashMap<>();
        int totalAmount = cart.entrySet().stream().mapToInt(entry -> entry.getValue() * 10).sum();

        cartDetails.put("items", cart);
        cartDetails.put("totalAmount", totalAmount);
        return cartDetails;
    }
}
