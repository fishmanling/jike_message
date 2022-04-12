package com.jike.commond;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Ling
 * @date: 2022/4/12 8:41
 * @description: 聊天命令
 */
@Slf4j
@Service
public class ChatCommand implements BaseCommand {

  private static final String CHAT_KEY = "3";

  private static final String CHAT_COMMAND_TIPS = "chat";

  private String userId;

  private String message;

  @Override
  public String getCommandType() {
    return CHAT_KEY;
  }

  @Override
  public String getCommandTips() {
    return CHAT_COMMAND_TIPS;
  }

  @Override
  public void exec(Scanner scanner) {
    System.out.println("Enter message IN FORMAT: id|message");
    String next = scanner.next();
    String[] infos = null;
    while (true) {
      infos = next.split("\\|");
      if (infos.length != 2) {
        System.out.println("Message format is invalid(id|message),please reenter:");
      } else {
        break;
      }
    }
    userId = infos[0];
    message = infos[1];
  }
}
