package Opensource_SW_Project.Project.web.dto.Topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TopicRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateTopicRequestDTO {
        private String topic;
        private String vector;
    }
}
