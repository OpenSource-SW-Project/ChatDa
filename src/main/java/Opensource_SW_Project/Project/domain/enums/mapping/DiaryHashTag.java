package Opensource_SW_Project.Project.domain.enums.mapping;


import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.Hashtag;
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
public class DiaryHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DiaryHashtagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")

    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}