package Opensource_SW_Project.Project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")   // 첫번째 로컬호스트 8080으로 들어오면 이것이 호출됨
    public String home() {
        return "home";
    }
}
