package com.study.login.controller;

import com.study.login.domain.Boards;
import com.study.login.domain.Member;
import com.study.login.dto.JoinDto;
import com.study.login.dto.LoginDto;
import com.study.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MemberController {

    private final LoginService loginService;

    @Autowired
    public MemberController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login/loginForm";
    }

    @CrossOrigin
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult, Model model, HttpServletRequest request) throws URISyntaxException {

        if (bindingResult.hasErrors()) {
            log.info("NotBlankError: loginDto={}", loginDto);
            return "login/loginForm";
        }

        Member member = loginService.login(loginDto.getMember());
        if (member == null) {
            bindingResult.reject("loginError", null);
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("memberIdx", member.getMemberIdx());
        log.info("로그인 성공!");

        //게시글 리스트 가져오기
        RestTemplate restTemplate = new RestTemplate();
//        URI uri = new URI("http://58.123.161.218:8086/boards?page=1");
        URI uri = new URI("http://192.168.0.14:8086/boards?page=1");
        List<Boards> boards = restTemplate.getForObject(uri, (new ArrayList<Boards>()).getClass());
        model.addAttribute("boards", boards);
        log.info("Data From Board Server={}", boards);
//        MultiValueMap<String, String> headers = new HttpHeaders();
//        headers.add("Accept", "application/json");
//        headers.add("Content-type", "application/json");
//        RequestEntity<String> requestEntity = new RequestEntity<>(null, headers,HttpMethod.GET, uri);
        return "board/boards";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "login/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute JoinDto joinDto,BindingResult bindingResult ,Model model) {
        //공백 처리
        if (bindingResult.hasErrors()) {
            log.info("NotBlankError: joinDto={}", joinDto);
            return "login/joinForm";
        }
        //아이디 중복검사
        if (loginService.idCheck(joinDto.getMember_id())) {
            log.info("idCheckFail: joinDto={}", joinDto);
            bindingResult.reject("idCheckFail", null);
        }
        //패스워드 확인 불일치
        if (!joinDto.getMember_password().equals(joinDto.getMember_password_check())) {
            log.info("passwordCheckFail: joinDto={}", joinDto);
            bindingResult.reject("passwordCheckFail", null);
        }
        //에러 처리
        if (bindingResult.hasErrors()) {
            return "login/joinForm";
        }
        //회원가입
        loginService.join(joinDto.getMember());
        return "redirect:/login";
    }

    @GetMapping("/members")
    public String members(Model model){
        Member members = loginService.findAll();
        model.addAttribute("members", members);
        return "login/members";
    }
}
