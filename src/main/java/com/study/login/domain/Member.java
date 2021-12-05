package com.study.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
    private Long memberIdx;
    private String memberId;
    private String memberPassword;
    private String memberName;
}
