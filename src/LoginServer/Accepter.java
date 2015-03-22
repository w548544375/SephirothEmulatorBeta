package LoginServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Accepter /* implements Runnable */{
	/*
	 * private List<Client> clientList; // private Socket client; ������ģʽ��
	 * //private ServerSocket server; private static Accepter obj = new
	 * Accepter(); private Accepter() {
	 * 
	 * } public static Accepter getInstance(){ return obj; } // @Override public
	 * void run() {
	 * 
	 * }
	 */
	private List<ClientStructs> clients; // �ͻ��˼���
	private ServerSocket server; // ������socket
	private BlockingDeque<ClientStructs> ClientMsg; // ���ڴ�����Կͻ��˵���Ϣ����

	public Accepter(ServerSocket server) {
		this.server = server;
		clients = new ArrayList<ClientStructs>();
		ClientMsg = new LinkedBlockingDeque<ClientStructs>();
		
	}

	public void startAccept() throws Exception {
		while (true) {
			Socket client = server.accept();
			ClientStructs cs = new ClientStructs(client);
			clients.add(cs);
			Utils.newClientConnect(cs);
		}
	}

	/**
	 * @author ����ͻ�����Ϣ 
	 * 4
	 */
   @SuppressWarnings("unused")
private class ClientMsgHandler implements Runnable{
	   
		@Override
		public void run() {
			
		}
	   
   }

}
