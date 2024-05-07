package Opensource_SW_Project.Project.service.ChatgptApiService;

import Opensource_SW_Project.Project.web.dto.TalkRequestDTO;

public interface ChatgptApiService {
    String getUserPrompt(TalkRequestDTO.CreateMessageRequestDTO request);

    // ChatGPT API부르는 함수 만들어 공통으로 쓰기 <- 2개의 api 응답을 합치는데 쓰일 것임
    String getDefaultSystemPrompt(TalkRequestDTO.CreateMessageRequestDTO request);

    void saveUserPromptAndMessage(TalkRequestDTO.CreateMessageRequestDTO request, String userPrompt, String message);
}
