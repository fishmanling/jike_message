package com.jike.commond;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Ling
 * @date: 2022/4/12 8:42
 * @description: 退出命令
 */
@Slf4j
@Service
public class LogoutCommand implements BaseCommand {

  public static final String LOGIN_OUT_KEY = "2";

  public static final String commandTips = "Logout";

  @Override
  public String getCommandType() {
    return LOGIN_OUT_KEY;
  }

  @Override
  public String getCommandTips() {
    return commandTips;
  }

  @Override
  public void exec(Scanner scanner) {
    System.out.println("Log out");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      log.info("Logout thread is interrupted.");
    }
  }
}
