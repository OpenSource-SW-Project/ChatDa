package Opensource_SW_Project.Project.domain;

import Opensource_SW_Project.Project.domain.common.BaseEntity;
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
public class Talk extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long talkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // user와 양방향 매핑하기 <- 양방향??
    public void setMember(Member member) {
//        // 기존에 이미 등록되어 있던 관계를 제거
//        if (this.user != null) {
//            this.user.getAnswerList().remove(this);
//        }

        this.member = member;

//        // 양방향 관계 설정
//        if (user != null) {
//            user.getAnswerList().add(this);
//        }
    }
}
