package com.study.login.service;

import com.study.login.domain.Member;
import com.study.login.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginMapper loginMapper;

    @Autowired
    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    /**
     * 로그인
     * @param member
     * @return 성공: true
     */
    public Member login(Member member) {
        Member memberByDB = loginMapper.findMemberByMemberId(member.getMember_id());
        if (memberByDB == null) {
            return null;
        } else if (!memberByDB.getMember_password().equals(member.getMember_password())) {
            return null;
        }
        return memberByDB;
    }

    /**
     * 중복검사
     * @param member_id
     * @return 실패: true
     */
    public Boolean idCheck(String member_id) {
        return loginMapper.findIdById(member_id) != null;
    }

    /**
     * 회원가입
     * @param member
     * @return 성공 true
     */
    public Boolean join(Member member) {
        loginMapper.join(member);
        return true;
    }

    public Member findAll() {
        return loginMapper.findAll();
    }
}
