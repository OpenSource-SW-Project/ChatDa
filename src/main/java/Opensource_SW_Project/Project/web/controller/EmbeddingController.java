package Opensource_SW_Project.Project.web.controller;
import Opensource_SW_Project.Project.service.EmbeddingService.EmbeddingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class EmbeddingController {

    private final EmbeddingService embeddingService;

    public EmbeddingController(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    @GetMapping("/api/embedding")
    public String getChatCompletion(@RequestParam String prompt) throws Exception {
        return embeddingService.getChatCompletion(prompt);
    }
}

