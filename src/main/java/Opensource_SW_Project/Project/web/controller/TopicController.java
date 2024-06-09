package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.domain.Topic;
import Opensource_SW_Project.Project.service.TopicService.TopicService;
import Opensource_SW_Project.Project.web.dto.Topic.TopicRequestDTO;
import Opensource_SW_Project.Project.web.dto.Topic.TopicResponseDTO;
import Opensource_SW_Project.Project.converter.TopicConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/topic")
@Slf4j
public class TopicController {

    private final TopicService topicService;

    // 주제 ID로 검색해서 가져오기
    @GetMapping()
    public Topic getTopic(@RequestParam(name = "topicId")Long topicId) {
        Topic topic = topicService.findById(topicId);
        System.out.println(topic);
        return topic;
    }

//    // 주제 id와 유사한 top N개 가져오기
//    @GetMapping("/search")
//    public List<Topic> searchSimilarTopics(@RequestParam(name = "topicId")Long topicId, @RequestParam(name = "top")int n) {
//        List<Topic> topics = topicService.getAllTopics();
//        String vector = topicService.findById(topicId).getVector();
//
//        List<Integer> resultIndex = getTopNSimilarVectors(vector, topics, n);
//
//        List<Topic> resultTopics = new ArrayList<>();
//
//        for (int i = 0; i < resultIndex.size(); i++) {
//            resultTopics.add(topicService.findById(resultIndex.get(i).longValue()));
//        }
//
//        return resultTopics;
//    }

    private static double cosineSimilarity(String vector1, String vector2) {
        // 여기에 코사인 유사도 계산 로직을 추가하세요.
        return 0.0; // 임시 반환 값
    }

//    public static List<Integer> getTopNSimilarVectors(String targetVector, List<Topic> topics, int n) {
//        List<Topic> topNTopics = new ArrayList<>();
//
//        // 리스트 내의 벡터들과 타겟 벡터의 코사인 유사도 계산
//        List<Double> similarities = new ArrayList<>();
//        for (Topic topic : topics) {
//            double similarity = cosineSimilarity(targetVector, topic.getVector());
//            similarities.add(similarity);
//        }
//
//        List<Double> sortedsimilar = new ArrayList<>(similarities);
//
//        // 코사인 유사도에 따라 정렬
//        Collections.sort(sortedsimilar);
//
//        List<Integer> indices = new ArrayList<>();
//        for (int i = 0; i < similarities.size(); i++) {
//            indices.add(i);
//        }
//
//        Collections.sort(indices, Comparator.comparingDouble(similarities::get));
//
//        // 상위 n개의 벡터 가져오기
//        List<Integer> topNIndices = indices.subList(0, Math.min(n, indices.size()));
//
//        return topNIndices;
//    }

//    @PostMapping("/")
//    public ApiResponse<TopicResponseDTO.CreateTopicResultDTO> createTalk(@RequestParam(name = "topicId")Long topicId) {
//        Topic topic = topicService.findById(topicId);
//        return ApiResponse.onSuccess(
//                SuccessStatus.TALK_OK,
//                TopicConverter.toCreateTopicResultDTO(
//                        topic
//                )
//        );
//    }
}
