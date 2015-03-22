package LoginServer.mix;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import LoginServer.DataUsed;
import LoginServer.mix.Message;
public class SephiorthServer {
	//
	private Selector selector = null;
	private ServerSocketChannel serverSocketChannel;
	private int PORT= 2287;
	private Object gate =null;
	private ProcessConnections pc;
	/**
	 *   
	 */
	/**
	 *   服务器的构造函数，对私有参数进行初始化。
	 * @throws IOException
	 */
	public SephiorthServer() throws IOException {
		// TODO Auto-generated constructor stub
		pc = new ProcessConnections();
		gate = new Object();
		selector = Selector.open();
		//初始化serversocketchannel
		serverSocketChannel  = ServerSocketChannel.open();
		//绑定服务器
		serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
		System.out.println("服务端启动......");
	}

	/**
	 * 接收连接的请求
	 */
	public void accept(){
		while(true){
			try {
				//接收客户端连接
				SocketChannel client = serverSocketChannel.accept();
				System.out.println("客户端:"+client.socket().getInetAddress()+":"+client.socket().getPort()+"连接.....");
				//设置客户端的模式为非阻塞
				client.configureBlocking(false);
				//
				synchronized(gate){
					//
					selector.wakeup();
					//注册selector为read和write
					client.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
					//发送第一个消息
					client.write(ByteBuffer.wrap(DataUsed.meet));
					pc.addOnline(client);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//#while
	}
	
	public void serivce() throws IOException{
		while(true){
			synchronized(gate){
				
			}
			int n = selector.select();
			//如果没有可选择的就继续下一次循环
			if(n==0)continue;
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator it = readyKeys.iterator();
			while(it.hasNext()){
				SelectionKey key = null;
				try{
					key =(SelectionKey) it.next();
					it.remove();
					//处理函数。
					//System.out.println("开始交互>>>>>>>>");
					pc.startWork(key);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 处理消息
	 */
	public void handleMessage(){
		pc.HandleMessage();
	}
	

}
