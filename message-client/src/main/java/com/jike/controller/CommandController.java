package com.jike.controller;

import com.jike.bean.ClientSession;
import com.jike.commond.ChatCommand;
import com.jike.commond.LoginCommand;
import com.jike.commond.LogoutCommand;
import com.jike.futuretask.FutureTaskExecutor;
import com.jike.service.TaskService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Ling
 * @date: 2022/4/12 14:04
 * @description:
 */
@Slf4j
@Service
public class CommandController {

  @Autowired
  private LoginCommand loginCommand;

  @Autowired
  private LogoutCommand logoutCommand;

  @Autowired
  private ChatCommand chatCommand;

  @Autowired
  private TaskService taskService;

  private ClientSession clientSession;


  private boolean connectFlag = false;

  private Channel channel;

  GenericFutureListener<ChannelFuture> closeListener = (ChannelFuture listener) -> {
    channel = listener.channel();
    ClientSession session = channel.attr(ClientSession.SESSION_KEY).get();
    session.close();
    log.info(new Date() + "channel is closed.");

    notifyCurrentThread();
  };


  GenericFutureListener<ChannelFuture> connectedListener = (ChannelFuture listener) -> {
    EventLoop eventLoop = listener.channel().eventLoop();
    if (!listener.isSuccess()) {
      log.debug("Connect failed and try after 10s");
      eventLoop.schedule(() -> taskService.doConnect(), 10, TimeUnit.SECONDS);

      connectFlag = false;
    } else {
      log.info("Connect success and choose next command");
      connectFlag = true;

      channel = listener.channel();
      //设置session
      clientSession = new ClientSession(channel);
      clientSession.setConnected(true);

      channel.closeFuture().addListener(closeListener);

      notifyCurrentThread();
    }
  };


  public void runCommandServer() {
    Thread.currentThread().setName("command thread");
    while (true) {
      //建立连接
      while (!connectFlag) {
        //执行登录任务
        startConnectServer();
        //当前线程阻塞
        waitCurrentThread();
      }
      //开始连接
    }
  }

  public void startConnectServer() {
    FutureTaskExecutor.execTask(() -> {
      //执行登录任务

    });
  }

  /**
   * 唤醒当前任务
   */
  public synchronized void notifyCurrentThread() {
    this.notify();
  }

  /**
   * 切换当前任务为等待状态
   */
  public synchronized void waitCurrentThread() {
    try {
      this.wait();
    } catch (InterruptedException e) {
      log.error(Thread.currentThread().getName() + "is interrupted exceptionally.");
    }
  }
}
