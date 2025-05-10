package com.example.pinkbullmakeup.DTO;

import lombok.Data;

@Data
public class AddReview {
    private String customerId;
    private String productId;
    private String reviewMessage;
    private int rating;
}
