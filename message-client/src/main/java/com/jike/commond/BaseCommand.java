package com.jike.commond;

import java.util.Scanner;

/**
 * @author: Ling
 * @date: 2022/4/12 8:48
 * @description: 命令基类
 */
public interface BaseCommand {

  String getCommandType();

  String getCommandTips();

  void exec(Scanner scanner);
}
