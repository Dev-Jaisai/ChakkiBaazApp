package com.chakkiapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class GrainShopController {

    @GetMapping
    public String showShop(Model model) {
        return "grainshop";  // returns the template name
    }

    @PostMapping("/place-order")
    @ResponseBody
    public Map<String, String> placeOrder(@RequestBody Map<String, Object> orderData) {
        // Here you would typically:
        // 1. Validate the order
        // 2. Save to database
        // 3. Process payment
        // 4. Send confirmation

        Map<String, String> response = new HashMap<>();
        try {
            // Simulate order processing
            response.put("status", "success");
            response.put("message", "Order placed successfully! Your order ID is: " + generateOrderId());
            return response;
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to place order. Please try again.");
            return response;
        }
    }

    private String generateOrderId() {
        // Simple order ID generation - in practice, use a more robust method
        return "ORD-" + System.currentTimeMillis();
    }
}