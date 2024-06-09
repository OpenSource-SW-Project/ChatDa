package Opensource_SW_Project.Project.domain;

import Opensource_SW_Project.Project.domain.common.BaseEntity;
import Opensource_SW_Project.Project.domain.enums.mapping.DiaryHashTag;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Hashtag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtagId;

    @Column(nullable = false, length = 20)
    private String hashtag;

    @OneToMany(mappedBy = "hashtag")
    @Builder.Default
    private List<DiaryHashTag> diaryHashTagList = new ArrayList<>();
}