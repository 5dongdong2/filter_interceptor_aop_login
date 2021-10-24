package com.study.login.dto;

import com.study.login.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class JoinDto {

    @NotBlank
    private String member_id;

    @NotBlank
    private String member_password;

    @NotBlank
    private String member_password_check;

    @NotBlank
    private String member_name;

    public Member getMember(){
        Member member = new Member();
        member.setMember_id(this.member_id);
        member.setMember_password(this.member_password);
        member.setMember_name(this.member_name);
        return member;
    }

}
