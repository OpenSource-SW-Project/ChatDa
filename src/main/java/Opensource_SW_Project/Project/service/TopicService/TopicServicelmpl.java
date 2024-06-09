package Opensource_SW_Project.Project.service.TopicService;

import Opensource_SW_Project.Project.domain.Topic;
import Opensource_SW_Project.Project.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServicelmpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic findById(Long topicId) {
        return topicRepository.findById(topicId).get();
    }

    public Topic saveVector(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<String> getAllTopicVectors() {
        List<Topic> topics = topicRepository.findAll();
        // Topic 객체에서 벡터만 추출하여 리스트로 반환
        return topics.stream().map(Topic::getVector).collect(Collectors.toList());
    }
}