package LoginServer.mix;

import java.io.IOException;

public class StartServer {
	
	
	public static void main(String[] args){
		try {
			final SephiorthServer server = new SephiorthServer();
			Thread accept = new Thread(){
			public void run(){
				server.accept();
			}
			};
			Thread handle = new Thread(){
				public void run(){
					server.handleMessage();
				}
			};
			accept.start();
			handle.start();
			server.serivce();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		
	}

}
