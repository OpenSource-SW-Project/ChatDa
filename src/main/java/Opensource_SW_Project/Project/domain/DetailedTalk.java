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
public class DetailedTalk extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detaliedTalkId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Long checkTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talk_id")
    private Talk talk;

    private Long nextCheckTopic;

    // user와 양방향 매핑하기 <- 양방향??
    public void setTalk(Talk talk) {
//        // 기존에 이미 등록되어 있던 관계를 제거
//        if (this.user != null) {
//            this.user.getAnswerList().remove(this);
//        }

        this.talk = talk;

//        // 양방향 관계 설정
//        if (user != null) {
//            user.getAnswerList().add(this);
//        }
    }

    public void setNextCheckTopic(Long nextCheckTopic){
        this.nextCheckTopic = nextCheckTopic;
    }
}
