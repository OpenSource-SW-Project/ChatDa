package Opensource_SW_Project.Project.service.ChatgptApiService;

import Opensource_SW_Project.Project.domain.DetailedTalk;
import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.domain.enums.Category;
import Opensource_SW_Project.Project.repository.DetailedTalkRepository;
import Opensource_SW_Project.Project.repository.TalkRepository;
import Opensource_SW_Project.Project.web.dto.TalkRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional
@Slf4j
public class ChatgptApiServiceImpl implements ChatgptApiService{

    private final DetailedTalkRepository detailedTalkRepository;
    private final TalkRepository talkRepository;
    Long checkTopic = 0L;

    public String getUserPrompt(TalkRequestDTO.CreateMessageRequestDTO request){
        return request.getUserPrompt();
    }

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

    String defaultSystemPrompt = "말끝마다 야호를 붙여줘";
            //"당신은 유저의 친구로 그와 자연스럽게 대화한다.";
    public String getDefaultSystemPrompt(TalkRequestDTO.CreateMessageRequestDTO request){
        String systemPrompt = defaultSystemPrompt;

        // 대화내용 DB에서 가져오기
        //List<Talk> allTalk = detailedTalkRepository.findByTalkId(request.getTalkId());

        //allTalk.stream().forEach(System.out::println);

        String plus = "그리고 느낌표도 문장 뒤에 붙여줘!";
        systemPrompt += plus;
        System.out.println(systemPrompt);

        return systemPrompt;
    }



}
