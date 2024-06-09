package Opensource_SW_Project.Project.domain;

import Opensource_SW_Project.Project.domain.common.BaseEntity;
import Opensource_SW_Project.Project.domain.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @Column(name = "vector", nullable = false)
    private String vector;

    @Column(name = "topic", nullable = false)
    private String topic;

    // Getters and setters omitted for brevity
}
