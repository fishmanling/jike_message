package com.jike.commond;

import java.util.Map;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Ling
 * @date: 2022/4/12 8:38
 * @description: 客户端命令菜单
 */
@Slf4j
@Service
public class ClientCommandMenu implements BaseCommand {

  public static final String KEY = "0";

  String commandType;
  String allCommandTips;

  @Override
  public String getCommandType() {
    return KEY;
  }

  @Override
  public String getCommandTips() {
    return allCommandTips;
  }

  @Override
  public void exec(Scanner scanner) {
    System.err.println("Command Menu:");
    commandType = scanner.next();
  }

  /**
   * 获取显示的命令选项String
   *
   * @param commandMap 命令map
   */
  public void showAllCommand(Map<String, BaseCommand> commandMap) {
    if (commandMap.isEmpty()) {
      return;
    }
    StringBuilder builder = new StringBuilder();
    for (BaseCommand baseCommand : commandMap.values()) {
      builder.append(baseCommand.getCommandType()).append("->").append(baseCommand.getCommandTips())
          .append("|");
    }
    allCommandTips = builder.toString();
  }
}
