package Opensource_SW_Project.Project.service.EmbeddingService;
import Opensource_SW_Project.Project.domain.EmbeddingValue;
import Opensource_SW_Project.Project.repository.EmbeddingValueRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Getter
public class EmbeddingService implements EmbeddingServicelmpl {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.embeddings.url}")
    private String apiUrl;

    @Value("${openai.embeddings.model}")
    private String model;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final EmbeddingValueRepository embeddingValueRepository;

    @Override
    public String getChatCompletion(String prompt) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + apiKey);

        String requestBody = "{ \"model\": \"" + model + "\", \"input\": \"" + prompt + "\" }";
        httpPost.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        response.close();

        if (response.getStatusLine().getStatusCode() != 200) {
            return String.format("Failed with HTTP error code : %d, response : %s",
                    response.getStatusLine().getStatusCode(), responseString);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseString);
        return jsonNode.toString();
    }

    @Override
    public String getEmbeddingResult(String result){
        // JSON 객체 생성
        JSONObject jsonObject = new JSONObject(result);

        // "data" 배열 추출
        JSONArray dataArray = jsonObject.getJSONArray("data");

        // 첫 번째 객체의 "embedding" 배열 추출
        JSONObject firstObject = dataArray.getJSONObject(0);
        JSONArray embeddingArray = firstObject.getJSONArray("embedding");

        // "embedding" 배열을 문자열로 변환
        StringBuilder embeddingStringBuilder = new StringBuilder();
        embeddingStringBuilder.append("[");
        for (int i = 0; i < embeddingArray.length(); i++) {
            embeddingStringBuilder.append(embeddingArray.get(i).toString());
            if (i < embeddingArray.length() - 1) {
                embeddingStringBuilder.append(", ");
            }
        }
        embeddingStringBuilder.append("]");
        String embeddingString = embeddingStringBuilder.toString();

        return embeddingString;
    }

    @Override
    public void saveEmbeddingValue(String terminationCondition, String embeddingArray){
        EmbeddingValue embeddingValue = EmbeddingValue.builder()
                .terminationCondition(terminationCondition)
                .embeddingValue(embeddingArray)
                .build();
        embeddingValueRepository.save(embeddingValue);
    }

    @Override
    public Boolean checkTermination(String userPrompt) throws Exception{
        Boolean similarity = false;
        String result = getChatCompletion(userPrompt);
        String embeddingResult = getEmbeddingResult(result);
        double [] embeddingArray;
        embeddingArray = strToDoubleArray(embeddingResult);

        List<EmbeddingValue> embeddingValueList = embeddingValueRepository.findAll();

        double max = 0;
        //EmbeddingValue mostSimilarEmbeddingValue;
        for (int i = 0; i < embeddingValueList.size(); i++) {
            double checkSimilarity = cosineSimilarity(strToDoubleArray(embeddingValueList.get(i).getEmbeddingValue()), embeddingArray);
            if(checkSimilarity > max){
                max = checkSimilarity;
                //mostSimilarEmbeddingValue = embeddingValueList.get(i);
            }
        }
        if(max > 0.91) similarity = true;
        //System.out.println("!!!!!!!!! : " + max);

        return similarity;
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

