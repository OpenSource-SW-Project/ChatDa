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
    public String getDiary(@RequestParam("date")String date, @RequestParam("month")String month,
                           @RequestParam("year")String year, @RequestParam("user")String user) {
        // 여긴 테스트 코드
        if (date.equals("15"))
                return "My Diary Content!!";
        return null;
        // return searchDiary(date, month, year, user);
    }

    public String searchDiary(String date, String month, String year, String user) {
        // date, user 받아서 DB 검색 쿼리 해서 있으면 리턴, 아님 리턴 안하기
        // 리턴값은 String으로 주면 달력에선 있으면 링크 생성
        // 일기에선 링크 타고 들어가면 쿼리로 다시 요청해서 스트링 뿌려주는 방식
        return "test";
    }
}