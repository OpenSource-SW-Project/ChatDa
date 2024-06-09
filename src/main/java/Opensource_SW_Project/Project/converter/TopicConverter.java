package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Topic;
import Opensource_SW_Project.Project.web.dto.Topic.TopicResponseDTO;

public class TopicConverter {
    public static TopicResponseDTO.CreateTopicResultDTO toCreateTopicResultDTO(Topic topic) {
        return TopicResponseDTO.CreateTopicResultDTO.builder()
                .topicId(topic.getTopicId())
                .topic(topic.getTopic())
                .vector(topic.getVector())
                .build();
    }
}
