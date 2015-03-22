package LoginServer;


import java.net.ServerSocket;

public class LoginServer {

	public static void main(String[] args) {
			try {
				ServerSocket server = new ServerSocket(2287);
				Accepter accepter = new Accepter(server);
				accepter.startAccept();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
}
