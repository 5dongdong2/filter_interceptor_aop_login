package com.study.login.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Boards {
    //board
    private Long boardIdx;
    private String boardTitle;
    private String boardContent;
    private Long boardLike;
    private Long boardDislike;
    private Long boardViews;
    private Date boardCreateDate;
    private Date boardUpdateDate;

    //member
    private Long memberIdx;
    private String memberName;
}
