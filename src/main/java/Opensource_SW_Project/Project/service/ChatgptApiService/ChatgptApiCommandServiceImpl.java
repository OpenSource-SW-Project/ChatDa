package Opensource_SW_Project.Project.service.ChatgptApiService;

import Opensource_SW_Project.Project.domain.DetailedTalk;
import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.domain.enums.Category;
import Opensource_SW_Project.Project.repository.DetailedTalkRepository;
import Opensource_SW_Project.Project.repository.TalkRepository;
import Opensource_SW_Project.Project.repository.UserRepository;
import Opensource_SW_Project.Project.web.dto.ChatGPT.ChatGPTRequestDTO;
import Opensource_SW_Project.Project.web.dto.ChatGPT.ChatGPTResponseDTO;
import Opensource_SW_Project.Project.web.dto.Talk.TalkRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
//@Transactional
@Slf4j
public class ChatgptApiCommandServiceImpl implements ChatgptApiCommandService { // 첫 대화인지 확인하고 checkTopic값 초기화 해줘야함

    private final DetailedTalkRepository detailedTalkRepository;
    private final TalkRepository talkRepository;
    private final UserRepository userRepository;
    Long checkTopic = 0L;
    int cnt = 1;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    // userPrompt 받아오기
    public String getUserPrompt(TalkRequestDTO.CreateMessageRequestDTO request){
        return request.getUserPrompt();
    }

    // ChatGPT API 요청
    public String getResponseOfChatGPT_API(String systemPrompt, String userPrompt){
        // request를 api로 보내 chatGPT응답받기
        ChatGPTRequestDTO chatGPTrequest = new ChatGPTRequestDTO(model, systemPrompt,userPrompt);
        ChatGPTResponseDTO chatGPTResponse =  template.postForObject(apiURL, chatGPTrequest, ChatGPTResponseDTO.class);

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }


    // 대화 내용 저장
    public void saveUserPromptAndMessage(TalkRequestDTO.CreateMessageRequestDTO request, String userPrompt, String message){
        Talk talk = talkRepository.findById(request.getTalkId()).get(); // 후에 예외처리 해주기, 해당하는 user가 존재하지 않을 때
        DetailedTalk questionDetailedTalk = DetailedTalk.builder()
                .talk(talk)
                .content(userPrompt)
                .category(Category.QUESTION)
                .checkTopic(checkTopic)
                .build();
        DetailedTalk answerDetailedTalk = DetailedTalk.builder()
                .talk(talk)
                .content(message)
                .category(Category.ANSWER)
                .checkTopic(checkTopic)
                .build();
        detailedTalkRepository.save(questionDetailedTalk);
        detailedTalkRepository.save(answerDetailedTalk);
        checkTopic++;
        checkTopic %= 2;
    }

    // 최종 SystemPrompt생성 메소드
    public String generateSystemPrompt(Long userId, TalkRequestDTO.CreateMessageRequestDTO request){
        String userPrompt = request.getUserPrompt();
        String systemPrompt1 = "", systemPrompt2 = "";
        String message, message1, message2;

        // 유저정보 DB에서 가져오기
        User getUser = userRepository.findById(userId).get();
        Talk getTalk = talkRepository.findById(request.getTalkId()).get();

        // 첫 대화인지 확인
        List<DetailedTalk> allTalk = detailedTalkRepository.findByTalk_TalkIdOrderByCreatedAtDesc(request.getTalkId());
        if(allTalk.isEmpty()) {
            checkTopic = 0L;
            cnt = 1;
            DetailedTalk firstDetailedTalk = DetailedTalk.builder()
                    .talk(getTalk)
                    .content("오늘 하루 중 가장 기억에 남는 일은 뭐야?")
                    .category(Category.QUESTION)
                    .checkTopic(checkTopic)
                    .build();
            detailedTalkRepository.save(firstDetailedTalk);
        }

        // 조건마다 systemPrompt 생성
        if(checkTopic == 0) {
            systemPrompt1 = getDefaultSystemPrompt(getUser.getName()) + generateRequestionSystemPrompt_condition2() + "\n\n" + getHistorytalk(userId, request);
            message = getResponseOfChatGPT_API(systemPrompt1, userPrompt);
        }
        else { // ChatGPT 요청 2번 보내기
            // 반응 생성 시스템 프롬프트
            systemPrompt1 = getDefaultSystemPrompt(getUser.getName()) + generateEndSubjectSystemPrompt_condition3() + "\n\n" + getHistorytalk(userId, request);
            message1 = getResponseOfChatGPT_API(systemPrompt1, userPrompt);

            // 새로운 화제에 대한 질문 생성 시스템 프롬프트
            systemPrompt2 = getDefaultSystemPrompt(getUser.getName()) + generateNewSubjectSystemPrompt_condition4() + "\n\n" + getHistorytalk(userId, request);
            message2 = getResponseOfChatGPT_API(systemPrompt1, userPrompt);

            message = message1 + message2;
        }

        System.out.println("SystemPrompt" + systemPrompt1 + "\n" + systemPrompt2);

        return message;
    }

    // 기본 조건 systemPrompt------------------------------------------------------------------
    public String getDefaultSystemPrompt(String name){
        String defaultSystemPrompt = "당신은 " + name + "의 친구로 그와 자연스럽게 대화한다.\n\n";
        return defaultSystemPrompt;
    }

    // 대화 기록 systemPrompt------------------------------------------------------------------
    public String getHistorytalk(Long userId, TalkRequestDTO.CreateMessageRequestDTO request){
        String talkHistory = "";
        String userName;

        // 유저정보 DB에서 가져오기
        User getUser = userRepository.findById(userId).get();
        userName = getUser.getName();

        // 대화내용 DB에서 가져오기
        Talk getTalk = talkRepository.findById(request.getTalkId()).get();
        List<DetailedTalk> allTalk = detailedTalkRepository.findByTalk_TalkIdOrderByCreatedAtDesc(request.getTalkId());

        if(allTalk.size() == 1) return talkHistory += "GPT : " + allTalk.get(0).getContent() + "\n";

        String title = "대화 기록\n";
        talkHistory += title;
        //allTalk.stream().forEach(System.out::println);

        talkHistory += "GPT : " + allTalk.get(0).getContent() + "\n";
        for (int i = 1; i < allTalk.size(); i++) {
            if(i % 2 == 1) talkHistory += userName + " : " + allTalk.get(i).getContent() + "\n";
            else talkHistory += "GPT : " + allTalk.get(i).getContent() + "\n";
        }

        //System.out.println("systemPrompt : " + systemPrompt);

        return talkHistory;
    }

    // 조건 SystemPrompt-----------------------------------------------------------------------

    public String generateRespondSystemPrompt_condition1() {
        String respondPrompt = "조건\n1. 자연스럽게 이어지도록 적절하게 반응한다.\n2. 한문장으로 짧게 반응한다.";
        return respondPrompt;
    }

    public String generateRequestionSystemPrompt_condition2() {
        String requestionPrompt = "조건\n1. 자연스럽게 이어지도록 적절하게 반응한다.\n2. 한문장으로 짧게 질문한다.";
        return requestionPrompt;
    }

    public String generateEndSubjectSystemPrompt_condition3() {
        String endSubjectPrompt = "조건\n1. 자연스럽게 이어지도록 적절하게 반응한다.\n2. 한문장으로 짧게 반응한다\n3. 질문하지 않는다.";
        return endSubjectPrompt;
    }

    // 새 화제 생성 systemPrompt---------------------------------------------------------------
    public String generateNewSubjectSystemPrompt_condition4() {
        return "조건\n1. 새 화제 " + getRandomSubject() + "에 관하여 질문한다. \n2. 한문장으로 짧게 질문만 한다.";
    }

    public String getRandomSubject() {
        String[] subjects = {
                "고민", "학업", "날씨", "점심 메뉴", "주말 할일", "최근 본 영화"
                //"요즘들어 가장 큰 고민은 뭐야?",
                //"학교 공부는 요즘 어떤거 같아?",
                //"오늘 날씨 어떤거 같아?",
                //"오늘 점심은 어떤것을 먹었어?",
                //"이번 주말에는 뭐할거야?",
                //"최근에 재밌게 본 영화 있어?"
        };
        Random random = new Random();
        int index = random.nextInt(subjects.length);
        String newSubject = subjects[index];
        return newSubject;
    }
}
