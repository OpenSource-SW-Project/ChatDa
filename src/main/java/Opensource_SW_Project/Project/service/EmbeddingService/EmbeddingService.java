package Opensource_SW_Project.Project.service.EmbeddingService;
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

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class EmbeddingService implements EmbeddingServicelmpl {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.embeddings.url}")
    private String apiUrl;

    @Value("${openai.embeddings.model}")
    private String model;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public EmbeddingService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getChatCompletion(String prompt) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + apiKey);

        String requestBody = "{\"input\": \"" + prompt + "\" }";
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
}

