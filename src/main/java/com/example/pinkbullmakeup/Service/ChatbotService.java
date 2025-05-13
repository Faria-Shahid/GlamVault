package com.example.pinkbullmakeup.Service;

import com.example.pinkbullmakeup.GeminiModel.Content;
import com.example.pinkbullmakeup.GeminiModel.GeminiRequest;
import com.example.pinkbullmakeup.GeminiModel.GeminiResponse;
import com.example.pinkbullmakeup.GeminiModel.Part;
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

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.api.base-url}")
    private String geminiApiUrl;

    @Value("${gemini.api.endpoint}")
    private String geminiApiEndpoint;
    private final WebClient webClient;

    public ChatbotService(WebClient.Builder webClientBuilder, @Value("${gemini.api.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public String callGeminiApi(String userMessage) {
        GeminiRequest request = new GeminiRequest(
                List.of(new Content("user", List.of(new Part(userMessage))))
        );

        GeminiResponse response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(geminiApiEndpoint)
                        .queryParam("key", geminiApiKey)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("API Error: " + errorBody)))
                )
                .bodyToMono(GeminiResponse.class)
                .block();

        if (response != null &&
                response.getCandidates() != null &&
                !response.getCandidates().isEmpty() &&
                response.getCandidates().get(0).getContent() != null &&
                response.getCandidates().get(0).getContent().getParts() != null &&
                !response.getCandidates().get(0).getContent().getParts().isEmpty()) {

            return response.getCandidates().get(0).getContent().getParts().get(0).getText();
        }

        return "No response from Gemini.";
    }
}
