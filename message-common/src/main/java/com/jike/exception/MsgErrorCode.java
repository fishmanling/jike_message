package com.jike.exception;

/**
 * @author: Ling
 * @date: 2022/4/10 10:53
 * @description: 错误码
 */
public enum MsgErrorCode {
  SUCCESS(0, "success."),
  MAGIC_CODE_NOT_MATCH(0x0101, "magic code does not match"),
  VERSION_CODE_NOT_MATCH(0x0102, "version code does not match");

  private final int errorCode;

  private final String errorDesc;

  MsgErrorCode(int errorCode, String errorDesc) {
    this.errorCode = errorCode;
    this.errorDesc = errorDesc;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorDesc() {
    return errorDesc;
  }
}
