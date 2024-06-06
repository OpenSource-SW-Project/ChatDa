//package Opensource_SW_Project.Project.config.oauth;
//
//import Opensource_SW_Project.Project.domain.enums.MyRole;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@RequiredArgsConstructor
//@Configuration
//@EnableWebSecurity // Spring Security 설정 활성화
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
////        httpx
////                .csrf(csrf -> csrf.disable())
////                .headers(headers -> headers.frameOptions().disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/css/**"), new AntPathRequestMatcher("/images/**"), new AntPathRequestMatcher("/js/**"), new AntPathRequestMatcher("/h2-console/**"), new AntPathRequestMatcher("/profile")).permitAll()
////                        .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).hasRole(MyRole.USER.name())
////                        .anyRequest().authenticated()
////                )
////                .logout(logout -> logout
////                        .logoutSuccessUrl("/")
////                        .permitAll()
////                )
////                .oauth2Login(oauth2 -> oauth2
////                        .loginPage("/oauth2/authorization/messaging-client-oidc")
////                        .userInfoEndpoint(userInfo -> userInfo
////                                .userService(customOAuth2UserService)
////                        )
////                        .defaultSuccessUrl("/", true)
////                );
////
////        return http.build();
////        // h2-console 화면을 사용하기 위해 해당 옵션을 disable
////        http.csrf().disable().headers().frameOptions().disable()
////                .and()// URL 별 권환 관리 설정 (authorizeRequests()가 선언되어야만 anyMatchers옵션 사용가능)
////                .authorizeRequests()
////                // antMatchers를 통해 권환 관리 대상을 지정하고, URL,HTTP 메소드별 관리 가능
////                .antMatchers("/", "/css/**", "/images/**", "/js**", "/h2-console/**", "/profile").permitAll() // "/"등 지정된 URL들은 permitAll() 옵션으로 전체 열람 권한 부여
////                .antMatchers("/api/v1/**").hasRole(MyRole.USER.name()) // 해당 주소는 USER 권한을 가진 사람만 가능
////                .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL들은 모두 인증된 사용자(로그인한)들에게만 허용
////                .and()
////                .logout().logoutSuccessUrl("/") // 로그아웃 성공시 해당 주소로 이동
////                .and()
////                .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
////                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
////                .userService(customOAuth2UserService) // 소셜 로그인 성공 시 후속 조치를 진행할 userService 인터페이스의 구현체 등록
////                // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 가능.
////                .and().defaultSuccessUrl("/", true);
////        return http.build();
////    }
//        http
//                .csrf(csrf -> csrf.disable())
//                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
//                        .requestMatchers("/api/v1/**").hasRole(MyRole.USER.name())
//                        .anyRequest().authenticated()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/")
//                        //.permitAll()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(customOAuth2UserService)
//                        )
//                        .defaultSuccessUrl("/login", true)
//                );
//
//        return http.build();
//    }
//
////        private final CustomOAuth2UserService oAuth2UserService;
////        //private final OAuth2SuccessHandler oAuth2SuccessHandler;
////
////        @Bean
////        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////            http
////                    // rest api 설정
////                    .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화 -> cookie를 사용하지 않으면 꺼도 된다. (cookie를 사용할 경우 httpOnly(XSS 방어), sameSite(CSRF 방어)로 방어해야 한다.)
////                    .cors(AbstractHttpConfigurer::disable) // cors 비활성화 -> 프론트와 연결 시 따로 설정 필요
////                    .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
////                    .formLogin(AbstractHttpConfigurer::disable) // 기본 login form 비활성화
////                    .logout(AbstractHttpConfigurer::disable) // 기본 logout 비활성화
////                    .headers(c -> c.frameOptions(
////                            FrameOptionsConfig::disable).disable()) // X-Frame-Options 비활성화
////                    .sessionManagement(c ->
////                            c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용하지 않음
////
////                    // request 인증, 인가 설정
////                    .authorizeHttpRequests(request ->
////                            request.requestMatchers(
////                                    new AntPathRequestMatcher("/"),
////                                    new AntPathRequestMatcher("/auth/success"),
////                                        ...
////                                ).permitAll()
////                    .anyRequest().authenticated()
////                )
////
////            // oauth2 설정
////                .oauth2Login(oauth -> // OAuth2 로그인 기능에 대한 여러 설정의 진입점
////                    // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
////                    oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
////                            // 로그인 성공 시 핸들러
////                            .successHandler(oAuth2SuccessHandler)
////            )
////
////                    // 인증 예외 핸들링
////                    .exceptionHandling((exceptions) -> exceptions
////                            .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
////                            .accessDeniedHandler(new CustomAccessDeniedHandler()));
////
////            return http.build();
////        }
//}
