package com.cloudstudy.entity;

import lombok.Data;

@Data
public class User {
    private static final long serialVersion = 1L;

    private String userName;                            //登录账号
    private String name;                                //用户名称
    private String avator;                              //用户头像
    private String memos;                               //个性签名


    public User() {
    }

    public User(String userName, String name, String avator, String memos) {
        this.userName = userName;
        this.name = name;
        this.avator = avator;
        this.memos = memos;
    }
}
