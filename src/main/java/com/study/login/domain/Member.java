package com.study.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
    private Long member_idx;
    private String member_id;
    private String member_password;
    private String member_name;
}
