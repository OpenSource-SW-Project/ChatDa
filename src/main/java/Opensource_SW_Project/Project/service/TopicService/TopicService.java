package Opensource_SW_Project.Project.service.TopicService;

import Opensource_SW_Project.Project.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Opensource_SW_Project.Project.domain.Topic;

import java.util.List;

@Service
public interface TopicService {

    // 전부 가져오기
    public List<Topic> getAllTopics();

    // 아이디로 가져오기
    public Topic findById(Long topic_id);

    // 새로운 토픽 저장
    public Topic saveVector(Topic topic);

    // 모든 토픽을 벡터 리스트로 저장
    public List<String> getAllTopicVectors();
}