package com.jike.codec;

import com.jike.common.MsgCommonConstant.ContentHeader;
import com.jike.common.bean.msg.ProtoMsg;
import com.jike.common.bean.msg.ProtoMsg.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Ling
 * @date: 2022/4/10 10:34
 * @description: 消息编码器
 */
@Slf4j
public class MsgProtoBufEncoder extends MessageToByteEncoder<ProtoMsg.Message> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
    //写魔数和版本
    out.writeShort(ContentHeader.MAGIC_CODE);
    out.writeShort(ContentHeader.VERSION_CODE);

    //写消息长度和消息体
    byte[] bytes = msg.toByteArray();
    log.info("msg bytes length is:{}", bytes.length);
    out.writeInt(bytes.length);
    out.writeBytes(bytes);
  }


}
