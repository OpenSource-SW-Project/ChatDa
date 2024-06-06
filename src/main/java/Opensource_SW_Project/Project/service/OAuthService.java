//package Opensource_SW_Project.Project.service;
//
//import Opensource_SW_Project.Project.domain.GoogleOauth;
//import com.nimbusds.openid.connect.sdk.claims.UserInfo;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class OAuthService {
//    private final GoogleOauth googleOauth;
//    private final HttpServletResponse response;
//
//    public UserInfo oAuthLogin(String code) throws IOException {
//        UserInfo result;
//
//        //구글로 일회성 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
//        ResponseEntity<String> accessTokenResponse = googleOauth.requestAccessToken(code);
//
//        //BE 서버로 보내 기존에 존재하는 사용자인지 확인
//        //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
//        String response= googleOauth.requestUserInfoToBe(accessTokenResponse.getBody());
//        result = googleOauth.parseCommonResponse(response);
//
//        return result;
//    }
//}
