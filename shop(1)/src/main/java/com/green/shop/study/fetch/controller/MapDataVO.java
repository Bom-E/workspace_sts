package com.green.shop.study.fetch.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

@Setter
@Getter
@ToString
public class MapDataVO {

    private MapDataMember memberInfo;
    private int cartCode;
    private ItemInfo itemInfo;
}

@Setter
@Getter
@ToString
class MapDataMember{
    private String memberId;
    private String memberName;
}

@Setter
@Getter
@ToString
class ItemInfo{
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private List<ImgInfo> imgList;
}

@Setter
@Getter
@ToString
class ImgInfo{
    private String imgName;
    private int imgCode;
}
