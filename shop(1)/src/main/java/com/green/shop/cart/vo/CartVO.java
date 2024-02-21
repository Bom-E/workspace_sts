package com.green.shop.cart.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class CartVO {

    private int cartCode;
    private int itemCode;
    private String memberId;
    private int cartCnt;
    private String cartDate;
    private List<Integer> cartCodeList;
}
