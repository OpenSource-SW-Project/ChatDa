package Opensource_SW_Project.Project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @GetMapping("/")   // 첫번째 로컬호스트 8080으로 들어오면 이것이 호출됨
    public String home() {
        return "home";
    }

    @RequestMapping(value="/chat")
    public String main() {
        return "chat";
    }

    @RequestMapping(value="/temp")
    public String temp() {
        return "temp";
    }

    @RequestMapping(value="/calendar")
    public String calendar() {
        return "calendar";
    }
}
