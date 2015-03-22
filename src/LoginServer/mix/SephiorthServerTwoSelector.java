package LoginServer.mix;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import LoginServer.DataUsed;
import LoginServer.mix.Message;
public class SephiorthServerTwoSelector {
	private Selector selector = null;
	private ServerSocketChannel serverSocketChannel;
	private int PORT= 2287;
	private Object gate =null;
	//消息队列，发送数据的队列
	private LinkedBlockingQueue sendQueue;
	//消息队列，接收数据的对列
	private LinkedBlockingQueue recvQueue;
	
	private List clients;
	/**
	 * @throws IOException
	 */
	public SephiorthServerTwoSelector() throws IOException {
		// TODO Auto-generated constructor stub
		gate = new Object();
		selector = Selector.open();
		
		clients = new LinkedList<Client>();
		//   开启服务端的channel
		serverSocketChannel  = ServerSocketChannel.open();
		//绑定服务器
		serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
		System.out.println("服务端启动......");
	}
	
	public void accpet() throws IOException{
		while(true){
			SocketChannel clientChannel = serverSocketChannel.accept();
			System.out.println("客户端："+clientChannel.socket().getInetAddress());
			synchronized(gate){
				//
				selector.wakeup();
				//注册selector为read和write
				clientChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
				//发送第一个消息
				//client.write(ByteBuffer.wrap(DataUsed.meet));
			}
			Client client = new Client(clientChannel);
				clients.add(client);
		}
	}
	/**
	 * 
	 */
	public void service(){
		while(true){
		if(clients.size()==0)
			continue;
			
		}
		
	}
}
