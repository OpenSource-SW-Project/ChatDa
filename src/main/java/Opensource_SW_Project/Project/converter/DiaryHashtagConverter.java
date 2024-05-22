package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Hashtag;
import Opensource_SW_Project.Project.domain.enums.mapping.DiaryHashTag;

import java.util.List;
import java.util.stream.Collectors;

public class DiaryHashtagConverter {

    public static List<Hashtag> toHashtagList(List<DiaryHashTag> list) {
        return list.stream()
                .map(DiaryHashTag::getHashtag)
                .collect(Collectors.toList());
    }
}
