package Opensource_SW_Project.Project.web.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/api/DB")
@Slf4j

public class DBController {

    @GetMapping("/diary")
    public String getDiary(@RequestParam("date")String date, @RequestParam("user")String user) {
//        Diary diary = searchDiary(date, user);
//        if (diary)
//            return diary.getContent();
        return "Test_Content~~~~";
    }

    public boolean searchDiary(String date, String user) {
        // date, user 받아서 DB 검색 쿼리 해서 있으면 리턴, 아님 리턴 안하기
        return true;
    }
}

class Diary {
    private String content;

    public Diary(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
