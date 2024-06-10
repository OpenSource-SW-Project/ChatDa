package Opensource_SW_Project.Project.web.controller;
import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.converter.TalkConverter;
import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.service.EmbeddingService.EmbeddingService;
import Opensource_SW_Project.Project.web.dto.Talk.TalkResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@Slf4j
public class EmbeddingController {

    private final EmbeddingService embeddingService;

    @Operation(summary = "대화 종료 조건에 대한 임베딩 값 생성 및 DB에 저장"    )
    @PostMapping("/embedding")
    public ApiResponse<?> saveEmbeddingValue(
            @RequestParam(name = "terminationCondition")String terminationCondition
    ) throws Exception{
        String result = embeddingService.getChatCompletion(terminationCondition);
        String embeddingResult = embeddingService.getEmbeddingResult(result);
        double [] embeddingArray;
        embeddingArray = strToDoubleArray(embeddingResult);
        System.out.println(Arrays.toString(embeddingArray));

        embeddingService.saveEmbeddingValue(terminationCondition, Arrays.toString(embeddingArray));
        return ApiResponse.onSuccess(
                SuccessStatus.DIARY_OK,
                null
        );
    }

    //prompt로 넘겨주면 해당 String에 대해 embedding return
    @GetMapping("/api/embedding")
    public String getChatCompletion(@RequestParam String prompt) throws Exception {
        String result = embeddingService.getChatCompletion(prompt);
        String embeddingResult = embeddingService.getEmbeddingResult(result);
        double [] embeddingArray;
        embeddingArray = strToDoubleArray(embeddingResult);
        System.out.println(Arrays.toString(embeddingArray));

        String compare = "일기 써줘";
        String compareResult = embeddingService.getChatCompletion(compare);
        String compareEmbeddingResult = embeddingService.getEmbeddingResult(compareResult);
        double [] compareEmbeddingArray;
        compareEmbeddingArray = strToDoubleArray(compareEmbeddingResult);

        double checkSimilarity = cosineSimilarity(embeddingArray, compareEmbeddingArray);
        System.out.println("@@@@@@@@@@@ 유사도 : " + checkSimilarity);

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

