package com.jike.common;

/**
 * @author: Ling
 * @date: 2022/4/10 10:48
 * @description: 常量类
 */
public class MsgCommonConstant {

  /**
   * Msg头
   */
  public interface ContentHeader {

    /**
     * 魔数
     */
    short MAGIC_CODE = 0x89;

    /**
     * 版本
     */
    short VERSION_CODE = 0x01;
  }

  /**
   * 客户端平台
   */
  public interface Platform {

    /**
     * windwos
     */
    public static final int WINDOWS = 1;

    /**
     * mac
     */
    public static final int MAC = 2;
    /**
     * android端
     */
    public static final int ANDROID = 3;
    /**
     * IOS端
     */
    public static final int IOS = 4;
    /**
     * WEB端
     */
    public static final int WEB = 5;
    /**
     * 未知
     */
    public static final int UNKNOWN = 6;


  }

}
