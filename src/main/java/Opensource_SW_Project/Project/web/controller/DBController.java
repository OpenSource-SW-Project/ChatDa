package Opensource_SW_Project.Project.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/api/DB")
@Slf4j

@Component
public class DBController {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username; // 데이터베이스 사용자 이름
    @Value("${spring.datasource.password}")
    private String password; // 데이터베이스 암호

    @GetMapping("/diary")
    public Diary getDiary(@RequestParam("date")String date, @RequestParam("userId")String user) {

        // System.out.println(date);
        return searchDiary(date, user);
        // return searchDiary(date, month, year, user);
    }

    @GetMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getChat(@RequestParam("date")String date, @RequestParam("talkId")String talk_id) {

        String result = searchChats(date, talk_id);
        System.out.println(result);
        return result;
        // return searchDiary(date, month, year, user);
    }

    public Diary searchDiary(String date, String user) {
        String title = "";
        String content = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            // SQL 문 작성
            // 여기에 검색하는 table만 바꿔주면 적용됨!!!!
            String sql = "SELECT title, diary FROM diary_test WHERE date_info = ? AND user = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, date);
            statement.setString(2, user);

            // 쿼리 실행 및 결과 처리
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                title = resultSet.getString("title");
                content = resultSet.getString("diary");

                // 여기서 title과 content를 처리할 수 있음
                System.out.println("Title: " + title);
                System.out.println("Content: " + content);
            }

            // 연결 종료
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (title.equals(""))
            return null;

        Diary diary = new Diary(title, content, date);

        return diary;
    }

    public String searchChats (String date, String talk_id) {
        List<String> contentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, username, password);

            // 쿼리 작성
            String query = "SELECT content FROM detailed_talk WHERE talk_id = ?"; //  AND created_at = ?

            // PreparedStatement 생성
            stmt = conn.prepareStatement(query);
            stmt.setString(1, talk_id);
            //stmt.setString(2, date);

            // 쿼리 실행 및 결과 처리
            rs = stmt.executeQuery();
            while (rs.next()) {
                String content = rs.getString("content");
                contentList.add(content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(contentList);
        return gson.toJson(json);
    }

    public class Diary {
        public String title;
        public String content;
        public String date;

        Diary(String title, String content, String date) {
            this.title = title;
            this.content = content;
            this.date = date;
        }
    }
}