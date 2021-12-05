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
    private String memberName;

    public Member getMember(){
        Member member = new Member();
        member.setMemberId(this.member_id);
        member.setMemberPassword(this.member_password);
        member.setMemberName(this.memberName);
        return member;
    }

}
