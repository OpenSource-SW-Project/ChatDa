package Opensource_SW_Project.Project.service.ModerationService;

public interface ModerationService {

    // OpenAI에 요청 보내서 Moderation 정보 받아옴
    // 0.03 넘는 값 있으면 가장 높은 값의 field (violence, self-harm, etc...) 하나 return
    // 0.03 넘는 값이 없으면 null return
    String getChatCompletion(String prompt) throws Exception;
}
