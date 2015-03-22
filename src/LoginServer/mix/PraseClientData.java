package LoginServer.mix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import LoginServer.mix.Message;
public class PraseClientData {

	public PraseClientData() {
		// TODO Auto-generated constructor stub
	}

	
	public static void prase(ByteBuffer buff){
		System.out.println("收到的封包长度:"+buff.limit());
		buff.order(ByteOrder.LITTLE_ENDIAN);
		Short head = buff.getShort();
		
		
	}
}
