package com.jike.bean;

import lombok.Data;

/**
 * @author: Ling
 * @date: 2022/4/12 10:50
 * @description: 用户类
 */
@Data
public class User {

  private String userName;

  private String userId;

  private String passWord;

  public User(String userName, String userId, String passWord) {
    this.userName = userName;
    this.userId = userId;
    this.passWord = passWord;
  }
}
