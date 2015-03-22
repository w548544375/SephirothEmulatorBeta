package LoginServer.mix;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;
import LoginServer.mix.Message;
public class Client {
	//client发送的缓冲区
	private ByteBuffer sendBuff;
	//client的接收的缓冲区
	private ByteBuffer recvedBuff;
	//client的socketchannel
	private SocketChannel clientChannel;

	//Client的构造方法 需要传入一个socketChannel作为参数
	public Client(SocketChannel clientChannel){
		sendBuff = null;
		recvedBuff=ByteBuffer.allocate(256);
		this.clientChannel = clientChannel;
	}
	public Client(SocketChannel channel,ByteBuffer buff){
		this.clientChannel = channel;
		this.recvedBuff = buff;
	}
/**
 * 客户端接收数据
 */
/*public ByteBuffer recv(){
	try {
		int length = clientChannel.read(recvedBuff);
		recvedBuff.flip();
		if(length == 0)
			return null;
		//输出收到数据包的大小
		//System.out.println(length);
		ByteBuffer result = ByteBuffer.allocate(length);
		for(int i =0;i<length;i++)
			result.put(recvedBuff.get());
	return result;
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}*/
	
	
/**
 * 客户端发送数据
 */
public void send(){
	
}
	
	
	




	
	public ByteBuffer getSendBuff() {
		return sendBuff;
	}
	public void setSendBuff(ByteBuffer sendBuff) {
		this.sendBuff = sendBuff;
	}
	public ByteBuffer getRecvedBuff() {
		return recvedBuff;
	}
	public void setRecvedBuff(ByteBuffer recvedBuff) {
		this.recvedBuff = recvedBuff;
	}
	
	public SocketChannel getClientChannel() {
		return clientChannel;
	}


	public void setClientChannel(SocketChannel clientChannel) {
		this.clientChannel = clientChannel;
	}
}
