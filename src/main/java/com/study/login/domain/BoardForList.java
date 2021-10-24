package com.study.login.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class BoardForList {
    //board data
    private Long board_idx;
    private Long member_idx;
    private String board_title;
    private String board_content;
    private Long board_like;
    private Long board_dislike;
    private Date board_create_date;
    private Date board_update_date;
    //member data
    private String member_name;
}
