package com.example.pinkbullmakeup.Controller;

import com.example.pinkbullmakeup.DTO.MessageReceived;
import com.example.pinkbullmakeup.Entity.Message;
import com.example.pinkbullmakeup.Service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // POST /api/messages - receive a new message
    @PostMapping
    public ResponseEntity<Void> receiveMessage(@Valid @RequestBody MessageReceived messageReceived) {
        messageService.saveMessageFromClient(messageReceived);
        return ResponseEntity.ok().build(); // Or use created() if returning location
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Message> updateMessageStatus(@PathVariable UUID id) {
        Message updatedMessage = messageService.changeMessageStatusToChecked(id);
        return ResponseEntity.ok(updatedMessage);
    }
}
