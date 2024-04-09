package Opensource_SW_Project.Project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @RequestMapping(value="/home")
    public String main() {
        return "index";
    }
}
