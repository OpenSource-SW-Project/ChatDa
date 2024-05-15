package Opensource_SW_Project.Project.apiPayload.code.status;

import Opensource_SW_Project.Project.apiPayload.code.BaseCode;
import Opensource_SW_Project.Project.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // ChatGPT 메시지 관련 응답
    USER_OK(HttpStatus.OK, "USER_1000", "성공입니다."),
    MESSAGE_OK(HttpStatus.OK, "MESSAGE_2000", "성공입니다."),
    TALK_OK(HttpStatus.OK, "TALK_2000", "성공입니다."),
    DIARY_OK(HttpStatus.OK, "DIARY_3000", "성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
