package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReplyVO {
    private int replyNum;
    private String content;
    private String createDate;
    private String writer;
    private int boardNum;
}
