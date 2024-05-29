package Opensource_SW_Project.Project.web.dto.Topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TopicResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateTopicResultDTO {
        Long topicId;
        String topic;
        String vector;
    }
}
