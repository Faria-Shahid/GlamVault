package com.example.pinkbullmakeup.Controller;

import com.example.pinkbullmakeup.Exceptions.ChatbotResponseFailureException;
import com.example.pinkbullmakeup.GroqModel.GroqResponse;
import com.example.pinkbullmakeup.Service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatbotController {
    @Autowired
    private ChatbotService chatbotService;

    @PostMapping
    public ResponseEntity<?> getChatResponse(@RequestBody Map<String, String> body) {
        try {
            String prompt = body.get("prompt");
            GroqResponse response = chatbotService.callGroqApi(prompt);
            return ResponseEntity.ok(response.getChoices().get(0).getMessage().getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ChatbotResponseFailureException("Failed to get response from chatbot."));
        }
    }
}
