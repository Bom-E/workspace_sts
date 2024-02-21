package com.green.shop.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ImgVO {
    private int ImgCode;
    private String originFileName;
    private String attachedFileName;
    private String isMain;
    private int itemCode;
}
