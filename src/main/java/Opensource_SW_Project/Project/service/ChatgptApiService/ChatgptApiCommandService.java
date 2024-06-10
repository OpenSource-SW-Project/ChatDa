package Opensource_SW_Project.Project.service.ChatgptApiService;

import Opensource_SW_Project.Project.web.dto.Talk.TalkRequestDTO;

public interface ChatgptApiCommandService { // ChatGPT API부르는 함수 만들어 공통으로 쓰기 <- 2개의 api 응답을 합치는데 쓰일 것임

    // userPrompt 받아오기
    String getUserPrompt(TalkRequestDTO.CreateMessageRequestDTO request);

    // ChatGPT API 요청
    String getResponseOfChatGPT_API(String systemPrompt, String userPrompt);

    // 대화 내용 저장
    void saveUserPromptAndMessage(TalkRequestDTO.CreateMessageRequestDTO request, String userPrompt, String message);

    // 최종 SystemPrompt생성 메소드-------------------------------------------------------------
    String generateSystemPrompt(Long memberId, TalkRequestDTO.CreateMessageRequestDTO request);

    // 기본 조건 systemPrompt------------------------------------------------------------------
    String getDefaultSystemPrompt(String name);

    // 대화 기록 systemPrompt-------------------------------------------------------------------
    String getHistorytalk(Long memberId, TalkRequestDTO.CreateMessageRequestDTO request);

    // 조건 SystemPrompt-----------------------------------------------------------------------
    String generateRespondSystemPrompt_condition1(); // 조건 타입 1 : 반응만

    String generateRequestionSystemPrompt_condition2(); // 조건 타입 2 : 질문만

    String generateEndSubjectSystemPrompt_condition3(); // 조건 타입 3 : 반응하고 질문을 하지 않으며 종료

    // 새 화제 생성 SystemPrompt----------------------------------------------------------------
    String generateNewSubjectSystemPrompt_condition4(); // systemPrompt : 새 화제에 대해 질문한다.

    String getRandomSubject(); //화제 랜덤생성


    String generateStyle(String userInput);

    Boolean checkCountTopic(TalkRequestDTO.CreateMessageRequestDTO request);
}
