package LoginServer.mix;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Message {
	 //消息所属者
	 private SocketChannel who;
	 //消息内容
	private ByteBuffer data;
	 public Message(SocketChannel who,ByteBuffer data){
		 this.who = who;
		 this.data = data;
	 }
	 
	 public SocketChannel getWho() {
		return who;
	}
	public void setWho(SocketChannel who) {
		this.who = who;
	}
	public ByteBuffer getData() {
		return data;
	}
	public void setData(ByteBuffer data) {
		this.data = data;
	}
}
