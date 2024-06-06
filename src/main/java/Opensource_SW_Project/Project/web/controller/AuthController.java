//package Opensource_SW_Project.Project.web.controller;
//
//import Opensource_SW_Project.Project.service.OAuthService;
//import com.nimbusds.openid.connect.sdk.claims.UserInfo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Controller
//@RequestMapping("/app/accounts")
//@Slf4j
//public class AuthController {
//
//    private final OAuthService oAuthService;
//
//    @GetMapping(value = "/auth/google/callback")
//    public String callback(
//            @RequestParam(name = "code") String code,
//            RedirectAttributes re) throws IOException {
//        log.info(">> 소셜 로그인 API 서버로부터 받은 Access Token :" + code);
//        UserInfo userInfo = oAuthService.oAuthLogin(code);
//        re.addAttribute("email", userInfo.getEmail());
//        re.addAttribute("username", userInfo.getUserName());
//        re.addAttribute("picture", userInfo.getPictureUrl());
//        return "redirect:/home";
//    }
//}
