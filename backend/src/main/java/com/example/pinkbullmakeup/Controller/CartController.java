package com.example.pinkbullmakeup.Controller;

import com.example.pinkbullmakeup.DTO.ItemToAddInCart;
import com.example.pinkbullmakeup.Entity.Cart;
import com.example.pinkbullmakeup.Entity.CartItem;
import com.example.pinkbullmakeup.Security.JwtUtil;
import com.example.pinkbullmakeup.Service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final JwtUtil jwtUtil;

    public CartController(CartService cartService, JwtUtil jwtUtil) {
        this.cartService = cartService;
        this.jwtUtil = jwtUtil;
    }

    private UUID extractUserId(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // remove "Bearer "
        return UUID.fromString(jwtUtil.extractUserId(token));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getCartItems(HttpServletRequest request) {
        UUID userId = extractUserId(request);
        return ResponseEntity.ok(cartService.getAllCartItems(userId));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@Valid @RequestBody ItemToAddInCart item, HttpServletRequest request) {
        UUID userId = extractUserId(request);
        System.out.println(item.getProductQuantity());
        CartItem savedItem = cartService.addItemInCart(item, userId);
        return ResponseEntity.ok(savedItem);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("/increase/{cartItemId}")
    public ResponseEntity<CartItem> increaseQuantity(
            @PathVariable UUID cartItemId,
            @RequestParam int quantity,
            HttpServletRequest request) {
        return ResponseEntity.ok(cartService.increaseCartItemQuantity(cartItemId, quantity));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("/decrease/{cartItemId}")
    public ResponseEntity<CartItem> decreaseQuantity(
            @PathVariable UUID cartItemId,
            @RequestParam int quantity,
            HttpServletRequest request) {
        return ResponseEntity.ok(cartService.decreaseCartInItemQuantity(cartItemId, quantity));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> deleteItem(@PathVariable UUID cartItemId, HttpServletRequest request) {
        UUID userId = extractUserId(request);
        cartService.deleteItemFromCart(userId, cartItemId);
        return ResponseEntity.ok("Item removed from cart.");
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/total")
    public ResponseEntity<Float> getCartTotal(HttpServletRequest request) {
        UUID userId = extractUserId(request);
        return ResponseEntity.ok(cartService.getCartTotalPrice(userId));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public ResponseEntity<Cart> getCart(HttpServletRequest request) {
        UUID userId = extractUserId(request);
        return ResponseEntity.ok(cartService.findByUserId(userId));
    }
}
