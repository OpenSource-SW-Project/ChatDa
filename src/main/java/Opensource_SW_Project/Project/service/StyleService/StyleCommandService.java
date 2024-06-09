package Opensource_SW_Project.Project.service.StyleService;

import Opensource_SW_Project.Project.domain.Style;

public interface StyleCommandService {

    Style saveStyle(Long memberId, String content);
    void deleteStyle(Long styleId);
}