package com.study.login.mapper;

import com.study.login.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT * FROM member WHERE member_id=#{member_id}")
    public Member findMemberByMemberId(String member_id);

    @Select("SELECT member_id FROM member WHERE member_id=#{member_id}")
    public String findIdById(String member_id);

    @Insert("INSERT INTO member (member_id,member_password,member_name) " +
            "values(#{member_id},#{member_password},#{member_name})")
    public void join(Member member);

    @Select("SELECT * FROM member")
    public Member findAll();
}
