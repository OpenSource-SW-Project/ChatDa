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

    //prompt로 넘겨주면 해당 String에 대해 embedding return
    @GetMapping("/api/embedding")
    public String getChatCompletion(@RequestParam String prompt) throws Exception {
        return embeddingService.getChatCompletion(prompt);
    }

    // Vector 배열 Double[]로 변환
    public static double[] strToDoubleArray(String str) {
        str = str.replace("[", "").replace("]", "");
        String[] stringArray = str.split(",");

        double[] arr = new double[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            arr[i] = Double.parseDouble(stringArray[i]);
        }
        return arr;
    }

    // Double[] 배열 2개 consin 유사도 검사
    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException("Vectors must be of the same length");
        }

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}

