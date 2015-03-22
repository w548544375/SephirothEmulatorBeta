package LoginServer;

import java.net.Socket;

/**
 * 这个类用于存储client的信息
 * 
 * @author Wangweiguang
 * 
 */
public class ClientStructs {
	private Socket client = null;					// client的socket信息
	private int CLIENT_PORT = 0; 				//客服端端口
	private String address = null;				//客户端地址
    private int _ID = 0;									//客户ID
    private byte[] msg;								//客户端的消息
	public ClientStructs(Socket client) {
		this.client = client;
		this.CLIENT_PORT = client.getPort();
		this.address = client.getInetAddress().getHostAddress();
		msg = new byte[Utils.BUFF_LENGTH];
	}
public String toString(){
	return "Current Socket Address: "+address+":"+this.CLIENT_PORT;
}
public Socket getClientSocket() {
	return client;
}
//public void setClient(Socket client) {
//	this.client = client;
//}
public int getClientPort() {
	return CLIENT_PORT;
}
//public void setCLIENT_PORT(int cLIENT_PORT) {
//	CLIENT_PORT = cLIENT_PORT;
//}
public String getClientAddress() {
	return address;
}
//public void setAddress(InetAddress address) {
//	this.address = address;
//}
public int getId(){
	return _ID;
}
public void setId(int id){
	this._ID = id;
}
public byte[] getMsg() {
	return msg;
}
public void setMsg(byte[] msg) {
	this.msg = msg;
}
public Socket getClient() {
	return client;
}
public void setClient(Socket client) {
	this.client = client;
}

}
