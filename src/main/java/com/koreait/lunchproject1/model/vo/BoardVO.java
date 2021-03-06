package com.koreait.lunchproject1.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardVO {
    private int no;
    private String store;
    private String title;
    private String content;
    private String id;
    private String nickname;
    private String pw;
    private String category;
    private int readcount;
    private String reg_dt;
    private int star;
    private String picture;
    private double mapX;
    private double mapY;
    private int isFav;

    private String sign;
}
