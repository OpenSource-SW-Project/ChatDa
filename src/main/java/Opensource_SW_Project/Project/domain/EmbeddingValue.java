package Opensource_SW_Project.Project.domain;

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
public class EmbeddingValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long embeddingValueId;

    @Column(columnDefinition = "TEXT")
    private String terminationCondition;

    @Column(columnDefinition = "TEXT")
    private String embeddingValue;
}
