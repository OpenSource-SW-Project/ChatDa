package Opensource_SW_Project.Project.domain;

import Opensource_SW_Project.Project.domain.common.BaseEntity;
import Opensource_SW_Project.Project.domain.enums.mapping.DiaryHashTag;
import Opensource_SW_Project.Project.web.dto.Diary.DiaryRequestDTO;
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
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @Column(columnDefinition = "TEXT")
    private String title; // 제목 필드 추가

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talk_id")
    private Talk talk;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    @Builder.Default
    private List<DiaryHashTag> diaryHashTagList = new ArrayList<>();


    // user와 양방향 매핑
    public void setMember(Member member) {
        this.member = member;
    }

    // talk와 양방향 매핑
    public void setTalk(Talk talk) {
        this.talk = talk;
    }

    public void update(DiaryRequestDTO.UpdateDiaryDTO request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
