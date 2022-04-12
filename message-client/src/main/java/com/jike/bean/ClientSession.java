package com.jike.bean;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.AttributeKey;
import java.util.UUID;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Ling
 * @date: 2022/4/12 14:38
 * @description: session
 */
@Data
@Slf4j
public class ClientSession {

  public static final AttributeKey<ClientSession> SESSION_KEY = AttributeKey.valueOf("SESSION_KEY");

  private Channel channel;
  private User user;

  private String sessionId;

  private boolean isConnected = false;
  private boolean isLogin = false;

  public ClientSession(Channel channel) {
    this.channel = channel;
    this.sessionId = UUID.randomUUID().toString();
    channel.attr(ClientSession.SESSION_KEY).set(this);
  }

  public void close() {
    ChannelFuture closeFuture = channel.close();
    closeFuture.addListener((ChannelFuture listener) -> {
      if (listener.isSuccess()) {
        log.info("connection is break on right way.");
      }
    });
  }
}
