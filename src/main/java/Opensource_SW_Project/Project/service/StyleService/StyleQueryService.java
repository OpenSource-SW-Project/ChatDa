package Opensource_SW_Project.Project.service.StyleService;

import Opensource_SW_Project.Project.domain.Style;

import java.util.List;

public interface StyleQueryService {

    List<Style> getUserStyle(Long memberId);
    Style getUserLastStyle(Long memberId);
}
