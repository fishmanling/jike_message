package com.jike.futuretask;

import com.jike.utils.ThreadUtil;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: Ling
 * @date: 2022/4/12 19:13
 * @description: task执行器
 */
public class FutureTaskExecutor {

  public static ThreadPoolExecutor executor;

  static {
    executor = ThreadUtil.getMixedTaskThreadPool();
  }

  private FutureTaskExecutor() {
  }

  /**
   * 处理异步任务
   *
   * @param handler 处理器
   */
  public static void execTask(TaskHandler handler) {
    executor.submit(handler::exec);
  }
}
