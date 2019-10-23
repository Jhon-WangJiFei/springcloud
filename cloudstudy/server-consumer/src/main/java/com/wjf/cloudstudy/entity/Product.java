package com.wjf.cloudstudy.entity;

import lombok.Data;

@Data
public class Product {
    private static final long serialVersionID = 1L;


    private String itemCode;                               //产品货号
    private String name;                                   //产品名称
    private String bandName;                               //产品品牌名称
    private int price;                                     //产品价格


    public Product(){
    }

    public Product(String itemCode, String name, String bandName, int price){

        this.itemCode = itemCode;
        this.name = name ;
        this.bandName = bandName;
        this.price = price;
    }
}
