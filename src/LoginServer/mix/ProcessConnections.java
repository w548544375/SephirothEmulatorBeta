package LoginServer.mix;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import LoginServer.mix.Message;
public class ProcessConnections {
	
		//管理当前在线的客户端
		private List<Client> onlineClient;
		//收到消息的队列
		public static LinkedBlockingQueue<Message> recvQueue;
		//发送消息的队列
		public static LinkedBlockingQueue<Message> sendQueue;
		//处理消息对象
		MessageHandler messageHandler ;
		
	public ProcessConnections(){
		onlineClient = new ArrayList<Client>();
		
		recvQueue = new LinkedBlockingQueue<Message>();
		
		sendQueue = new  LinkedBlockingQueue<Message>();
		
		messageHandler = new MessageHandler();
	}
	
	/**
	 * 开始进行服务端和客户端的交互，管理客户端
	 * @param key
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void startWork(SelectionKey key) throws IOException, InterruptedException{
		//根据socketchannel 查找当前的client
		//currentClient = this.getClient((SocketChannel) key.channel());
		//System.out.println(currentClient.getClientChannel().socket().getInetAddress());
		
		if(key.isReadable()){
			Message message = this.recv((SocketChannel) key.channel());
			if(message==null)
				return;
			//System.out.println(message.getData().toString());
				recvQueue.put(message);
		//	System.out.println(recvQueue.size());
		}
		if(key.isWritable())
		{
		//currentClient.send();
			this.send(sendQueue);
		}
	//	currentClient=null;
	}
		


	/** 向客户端发送数据
	 * @param client
	 * @throws IOException
	 */
	public void send(LinkedBlockingQueue<Message> queue) throws IOException{
			if(queue.isEmpty())
				return;
			Message senddata = queue.poll();
			ByteBuffer temp =senddata.getData();
			//while(temp.hasRemaining()){
			senddata.getWho().write(temp);
			/*if(length!=0)
				System.out.println("发送数据，大小:"+length);*/
			//}
		}
		
	
	
	/**  接收客户端数据包,压入消息队列
	 * @param client
	 * @throws IOException
	 */
	public Message recv(SocketChannel  channel) throws IOException{
		ByteBuffer temp = ByteBuffer.allocate(576);
		try {
			int length = channel.read(temp);
			temp.flip();
			if(length <= 0) {
				channel.socket().close();
				channel.close();
				System.out.println("客户端断开连接....");
				return null;
			}
			//输出收到数据包的大小
			//System.out.println("收到数据包:"+length);
			ByteBuffer result = ByteBuffer.allocate(length);
			for(int i =0;i<length;i++)
				result.put(temp.get());
			result.flip();
			return new Message(channel,result);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			temp.clear();
			
		}
		return null;
	}
	
	/**
	 *   添加到onlinelist
	 * @param sc
	 */
	public void addOnline(SocketChannel sc){
		Client client = new Client(sc);
		onlineClient.add(client);
	}
	
	
	
	
	
	/**  根据socketchannel获得当前client
	 * @param sc
	 * @return
	 */
	public Client getClient(SocketChannel sc){
		if(onlineClient.size()==0)
		return null;
		for(Client c:onlineClient){
			if(c.getClientChannel() == sc)
				return c;
		}
		return null;
	}
	
	
	
	
	
	/**
	 * 处理消息函数
	 */
	public void HandleMessage(){
		while(true){
			Message message = recvQueue.poll();
			if(message == null)
				continue;
		try {
			Message send = messageHandler.parseMessage(message);
			if(send==null)
				continue;
			sendQueue.put(send);
		} catch (Exception e) {
			 e.printStackTrace();
			System.out.println("封包错误");
		}
			
		}
	}
/*	public static void readFromClient(SelectionKey key) throws IOException{
		//获得socketchannel
		SocketChannel clientChannel = (SocketChannel) key.channel();
		//分配接收的缓冲区
		ByteBuffer temp = ByteBuffer.allocate(256);
		//读取数据
		int Nums = clientChannel.read(temp);
		if(Nums == -1 && Nums == 0)
			return ;
		temp.flip();
		//把temp的极限设置为收取到的的容量
		temp.limit(Nums);
		PraseClientData.prase(temp);
	}*/
	
	
	/*public static void writeToClient(SelectionKey key) throws Exception{
			SocketChannel client = (SocketChannel) key.channel();
			//ByteBuffer test = ByteBuffer.wrap(DataUsed.meet);
		//	int length = client.write(test);
		//	System.out.println("发送数据长度:"+length);
		
			
	}
	*/

}






