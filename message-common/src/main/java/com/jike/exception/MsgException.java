package com.jike.exception;

/**
 * @author: Ling
 * @date: 2022/4/10 10:56
 * @description: msg异常类
 */
public class MsgException extends RuntimeException {

  private int errorCode;

  private String errorDesc;

  public MsgException(String message) {
    super(message);
  }

  public MsgException(MsgErrorCode msgErrorCode) {
    super();
    errorCode = msgErrorCode.getErrorCode();
    errorDesc = msgErrorCode.getErrorDesc();
  }

  public MsgException(MsgErrorCode msgErrorCode, String errorDesc) {
    super();
    errorCode = msgErrorCode.getErrorCode();
    this.errorDesc = errorDesc;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorDesc() {
    return errorDesc;
  }
}
