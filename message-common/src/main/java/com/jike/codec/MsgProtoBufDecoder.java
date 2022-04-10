package com.jike.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import com.jike.common.MsgCommonConstant.ContentHeader;
import com.jike.common.bean.msg.ProtoMsg.Message;
import com.jike.exception.MsgErrorCode;
import com.jike.exception.MsgException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Ling
 * @date: 2022/4/10 10:33
 * @description: 消息解码器
 */
@Slf4j
public class MsgProtoBufDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    Object obj = decode0(ctx, in);
    if (obj != null) {
      out.add(obj);
    }
  }

  private Object decode0(ChannelHandlerContext ctx, ByteBuf in)
      throws InvalidProtocolBufferException {
    //数据的格式是 ： 2byte魔数 + 2byte版本  + 4byte长度 + content
    //魔数、版本、长度都是int类型

    //mark现在bytebuf的位置，为后面出现异常的时候恢复
    in.markReaderIndex();

    if (in.readableBytes() < 8) {
      log.info("data is not sufficient.");
      return null;
    }
    //取前两位，先获取魔数
    short magicNum = in.readShort();
    if (magicNum != ContentHeader.MAGIC_CODE) {
      log.error("magicNum does not match");
      throw new MsgException(MsgErrorCode.MAGIC_CODE_NOT_MATCH);
    }

    //在取两位，获取版本
    short version = in.readShort();
    if (version != ContentHeader.VERSION_CODE) {
      log.error("version code does not match.");
      throw new MsgException(MsgErrorCode.VERSION_CODE_NOT_MATCH);
    }

    //再取content长度
    int length = in.readInt();
    //长度小于0，非法数据，关闭连接
    if (length < 0) {
      ctx.close();
    }

    //大于可读数据，表示数据还没传输完成，返回null，重置读指针，继续等待
    if (length > in.readableBytes()) {
      in.resetReaderIndex();
      return null;
    }

    //读取content内容
    byte[] array = null;
    if (in.hasArray()) {
      //堆内存
      log.info("this is heap memory.");
      array = new byte[length];
      in.readBytes(array, 0, length);
    } else {
      //堆外内存
      log.info("this is non-heap memory.");
      array = new byte[length];
      in.readBytes(array, 0, length);
    }

    //转换成对象
    return Message.parseFrom(array);
  }
}
