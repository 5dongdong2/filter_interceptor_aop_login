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

    public Member login(Member member) {
        Member memberByDB = loginMapper.findMemberByMemberId(member.getMemberId());
        if (memberByDB == null) {
            return null;
        } else if (!memberByDB.getMemberPassword().equals(member.getMemberPassword())) {
            return null;
        }
        return memberByDB;
    }

    public Boolean idCheck(String member_id) {
        return loginMapper.findIdById(member_id) != null;
    }

    public Boolean join(Member member) {
        loginMapper.join(member);
        return true;
    }

    public Member findAll() {
        return loginMapper.findAll();
    }
}
