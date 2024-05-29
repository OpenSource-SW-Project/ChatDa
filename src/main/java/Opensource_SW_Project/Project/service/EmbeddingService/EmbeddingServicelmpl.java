package Opensource_SW_Project.Project.service.EmbeddingService;

import com.fasterxml.jackson.databind.JsonNode;

public interface EmbeddingServicelmpl {
    public JsonNode getChatCompletion(String prompt) throws Exception;
}
