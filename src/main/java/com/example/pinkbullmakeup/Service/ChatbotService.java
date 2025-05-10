package com.example.pinkbullmakeup.Service;

import com.example.pinkbullmakeup.GroqModel.GroqRequest;
import com.example.pinkbullmakeup.GroqModel.GroqResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class ChatbotService {
    @Value("${groq.api.key}")
    private String groqApiKey;

    @Value("${groq.api.url}")
    private String groqApiUrl;

    @Autowired
    private WebClient webClient;

    public GroqResponse callGroqApi(String userMessage){
        GroqRequest.Message message = new GroqRequest.Message("user", userMessage);
        GroqRequest request = new GroqRequest("llama3-8b-8192", List.of(message));

        return webClient.post()
                .uri(groqApiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + groqApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody ->
                                Mono.error(new RuntimeException("Groq API error: " + errorBody))
                        )
                )
                .bodyToMono(GroqResponse.class)
                .block();
    }
}
