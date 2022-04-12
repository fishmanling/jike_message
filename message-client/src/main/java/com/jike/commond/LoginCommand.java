package com.jike.commond;

import com.jike.bean.User;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Ling
 * @date: 2022/4/12 8:41
 * @description: 登录命令
 */
public class LoginCommand implements BaseCommand {

  public static final String LOGIN_IN_KEY = "1";

  private static final String commandTips = "Login";

  volatile AtomicReference<User> reference = new AtomicReference<>(null);

  @Override
  public String getCommandType() {
    return LOGIN_IN_KEY;
  }

  @Override
  public String getCommandTips() {
    return commandTips;
  }

  @Override
  public void exec(Scanner scanner) {
    System.out.println("Please Input UserName and PassWord as ’Name@Password‘ format:");
    String[] info = null;
    while (true) {
      String next = scanner.next();
      info = next.split("@");
      if (info.length != 2) {
        System.out.println("Input String format is not ’Name@Password‘,please input again.");
        info = null;
      } else {
        break;
      }
    }
    reference.compareAndSet(null, new User(info[0], null, info[1]));
  }
}
