package LoginServer.packets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 封包类，负责封包的解析，打包，转交处理
 * @author weiguang
 *
 */
public class Packet {
	
	
	public Packet(){
		
	}
	
	/**
	 * 产生怪物的坐标和位置信息
	 * @param id
	 * @param pos
	 * @return
	 */
	public static byte[] posPacket(int id,String[] pos){
		ByteBuffer result = ByteBuffer.allocate(16);
		result.order(ByteOrder.LITTLE_ENDIAN);
		int[] temp = gamePostoServerPos(pos[0],pos[1]);
		result.putInt(id);
		for(int s:temp)
			result.putInt(s);
		float rotation = Float.parseFloat(pos[2]);
		result.putFloat(rotation);
		result.flip();
		return result.array();
	}
	
	/**
	 * 封装主体消息，就是18个字节消息后面的消息。
	 * @param msgtype
	 * @param truedata
	 * @return
	 */
	public static byte[] generateMainPacket(int msgtype,byte[] truedata){
		ByteBuffer bf = ByteBuffer.allocate(2+truedata.length);
		bf.order(ByteOrder.LITTLE_ENDIAN);
		bf.putShort((short)msgtype);
		bf.put(truedata);
		bf.flip();
		return bf.array();
	}
	
	/**
	 * 游戏坐标转化为后台坐标
	 * @param x
	 * @param y
	 * @return
	 */
	public static int[] gamePostoServerPos(String x, String y){
		int[] pos = new int[2];
		pos[0] = (int)((Double.parseDouble(x)-26009.00)*100);
		pos[1] = (int)((Double.parseDouble(y)-26009.00)*100);
		return pos;
	}
	
	public static double serverPostoGamePos(int y){
		double pos = 0;
		pos = ((double)y-26010.00)*100;
		return pos;
	}
	/**
	 *   封装最终要发送的消息消息
	 * @param head  封装的消息头
	 * @param pre    封装消息前面数据包
	 * @param src    消息的主体
	 * @return
	 */
	public static ByteBuffer generatePacket(int head,byte[] pre,byte[] src){
		ByteBuffer result = null;
		//分配空间
		if(pre == null & src!=null)
			result = ByteBuffer.allocate(18+src.length);
		else if(src == null & pre !=null)
			result = ByteBuffer.allocate(18+pre.length);
		else if(src == null & pre == null)
			result = ByteBuffer.allocate(18);
		else
			result = ByteBuffer.allocate(18+pre.length+src.length);
		result.order(ByteOrder.LITTLE_ENDIAN);
		//写入包头
		result.putShort((short)head);
		//写入第一个数据包大小
		if(pre == null)
			result.putShort((short) 0);
		else
			result.putShort((short) pre.length);
		//第二个数据包大小
		if(src == null)
			result.putShort((short) 0);
		else
		result.putShort((short) src.length);
		result.position(16);
		if(pre == null & src!= null)
			result.putShort((short) (head^src.length));
		else if(pre !=null & src == null)
			result.putShort((short) (head^pre.length));
		else if(pre == null & src == null)
			result.putShort((short) head);
		else
			result.putShort((short) (head^pre.length^src.length));
	    if(pre != null & src != null){
	    	result.put(pre);
	    	result.put(src);
	    }
	    else if(pre ==null & src != null)
	    	result.put(src);
	    else if(pre != null & src == null)
	    	result.put(pre);
	    result.flip();
		return result;
	}
	
	///对函数进行测试
	public static void main(String[] args){
	// int[] pos =	gamePostoServerPos("22636", "34448","00000");
/*	 for(int s :pos)
		 System.out.println(s);*/
	}
}
