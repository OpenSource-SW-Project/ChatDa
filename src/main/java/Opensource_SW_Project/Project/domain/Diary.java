package Opensource_SW_Project.Project.domain;

import Opensource_SW_Project.Project.domain.enums.Category;
import Opensource_SW_Project.Project.web.dto.DiaryRequestDTO;
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
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talk_id")
    private Talk talk;


    // user와 양방향 매핑하기 <- 양방향??
    public void setUser(User user) {
//        // 기존에 이미 등록되어 있던 관계를 제거
//        if (this.user != null) {
//            this.user.getAnswerList().remove(this);
//        }

        this.user = user;

//        // 양방향 관계 설정
//        if (user != null) {
//            user.getAnswerList().add(this);
//        }
    }

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

    public void update(DiaryRequestDTO.UpdateDiaryDTO request) {
        //this.content = String.valueOf(request.getUserId());
        this.content = request.getContent();
    }
}
