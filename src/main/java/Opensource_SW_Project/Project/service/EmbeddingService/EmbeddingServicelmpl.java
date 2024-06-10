package Opensource_SW_Project.Project.service.EmbeddingService;

import com.fasterxml.jackson.databind.JsonNode;

public interface EmbeddingServicelmpl {
    String getChatCompletion(String prompt) throws Exception;

    String getEmbeddingResult(String result);

    void saveEmbeddingValue(String terminationCondition, String embeddingArray);

    Boolean checkTermination(String userPrompt) throws Exception;
}
