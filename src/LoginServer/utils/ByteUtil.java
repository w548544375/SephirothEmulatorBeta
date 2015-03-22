package LoginServer.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import LoginServer.mix.Attrs;
import LoginServer.mix.NeedSkill;
import LoginServer.mix.NpcServerInfo;
import LoginServer.mix.SephirothSkill;

/**
 * byte的工具类，获得一个类型的byte数组,或者将byte数组转换为基本类型\
 * 
 * byte 1字节
 * char 2字节
 * int 4字节
 * float 4字节
 * long 8字节
 * double 8字节
 *  
 * @author weiguang
 *
 */
public class ByteUtil {
	public static byte[] getBytes(short n){
		byte[] bytes = new byte[2];
		bytes[0] =  (byte) (n&0xff);
		bytes[1] = (byte) ((n&0xff00)>>8);
		return bytes;
	}
	
	public static byte[] getBytes(char n){
		byte[] bytes = new byte[2];
		bytes[0] = (byte) n;
		bytes[1] = (byte) (n>>8);
		return bytes;
	}
	
	public static byte[] getBytes(int n){
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (n&0xff);
		bytes[1] = (byte) ((n&0xff00)>>8);
		bytes[2] = (byte) ((n&0xff0000)>>16);
		bytes[3] = (byte) ((n&0xff000000)>>24);
		return bytes;
	}
	
	public static byte[] getBytes(long n){
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (n&0xff);
		bytes[1] = (byte) ((n>>8)&0xff);
		bytes[2] = (byte) ((n>>16)&0xff);
		bytes[3] = (byte) ((n>>24)&0xff);
		bytes[4] = (byte) ((n>>32)&0xff);
		bytes[5] =  (byte) ((n>>40)&0xff);
		bytes[6] = (byte) ((n>>48)&0xff);
		bytes[7] = (byte) ((n>>56)&0xff);
		return bytes;
	}

	public static byte[] getBytes(float n){
		int intBits = Float.floatToIntBits(n);
		return getBytes(intBits);
	}
	
	public static byte[] getBytes(double n){
		long intBits = Double.doubleToLongBits(n);
		return getBytes(intBits);
	}
	
	/**
	 * 这个特殊之处在于java中的字符串getbytes后要变成c格式的字符串，所以要在每个
	 * byte数组后面添加0
	 * @param s
	 * @return
	 */
	public static byte[] getBytes(String s){
		try {
			byte[] str = s.getBytes("gbk");
			byte[] bytes = new byte[str.length+1];
			//System.out.println(str.length);
			for(int i=0;i<str.length;i++)
			bytes[i] =str[i];
			bytes[str.length] =0;
			return bytes;
		} catch (UnsupportedEncodingException e) {
			System.out.println("不支持的解码类型");
			e.printStackTrace();
		}

	 return null;
	}
	
	public static short getShort(byte[] src){
		return (short) ((0xff&src[0])|(0xff00&(src[1]<<8)));
	}
	public static char getChar(byte[] src){
		return (char) ((0xff&src[0])|(0xff00&(src[1]<<8)));
	}
	
	public static int getInt(byte[] src){
		return (0xff&src[0])|(0xff00&src[1]<<8)|(0xff0000&(src[2]<<16))|(0xff000000&(src[3]<<24));
	}
	public static long getLong(byte[] src){
		return (0xffL&(long)src[0])|(0xff00L&(long)(src[1]<<8))|(0xff0000L&(long)(src[2]<<16))|(0xff000000L&(long)(src[3]<<24))| 
				(0xff00000000L & ((long)src[4] << 32)) | (0xff0000000000L & ((long)src[5] << 40)) | 
				(0xff000000000000L & ((long)src[6] << 48)) | (0xff00000000000000L & ((long)src[7] << 56));
	}
	
	public static float getFloat(byte[] b){
   
		return Float.intBitsToFloat(getInt(b));
		
	}
	
	public static double getDouble(byte[] src){
		long l = getLong(src);
		return Double.longBitsToDouble(l);
	}
	
	public static String getString(byte[] src){
		byte[] dst = new byte[src.length-1];
		for(int i =0;i<dst.length;i++){
			dst[i] = src[i];
		}
		return new String(dst,Charset.forName("gbk"));
	}
	
	
	public static byte[] getDouble4Bytes(double b){
		  return getBytes((int)b);
	}
	
	public static void main(String[] args) {
		/*byte[] b= {0x47,0x6C,0x75,0x6D,0x57,0x69,0x6E,0x67,0x00};
	    
	System.out.println(ByteUtil.getString(b));
	byte[] num={(byte) 0xA4,0x6E,(byte) 0xCD,
    
(byte) 0xD0};
	NpcServerInfo nsi = new NpcServerInfo("Methinel",null);
	System.out.println(nsi.toByteArray().length);
	System.out.println();
	System.out.println(getInt(num));*/
	for(byte x:getBytes(-337009.00000000000000d)){
		System.out.print(Integer.toHexString(x&0xff)+",");
	}
	/*byte[]  ftest = {(byte) 0xBC,0x33,0x0C,0x00};
	System.out.println(getDouble(ftest));
	ByteBuffer bb= ByteBuffer.allocate(4);
	bb.order(ByteOrder.LITTLE_ENDIAN);
	bb.putFloat(-6798.312f);
	*/
	byte b[] ={(byte) 0xAC,0x5D,(byte) 0xFB,(byte) 0xFF};
	System.out.println((double)getInt(b));
	double c  = getInt(b);
	byte[] d = getBytes((int)824000);
	Date longago2 = new Date(getInt(b));
	Date longago = new Date(0);
	
	
	
	System.out.println(longago.toString()+":"+longago2.toString());
	for(byte e: d)
		System.out.print(Integer.toHexString(e&0xff)+",");
	byte[] str = {0x42,0x75,0x6C,0x6C,0x64,0x6F,0x7A,0x65,0x72,

0x00};
	//拉蒂阿内斯露营地,NOPVP=1,NOSTAMINA=1
	System.out.println(getString(str));
	String bbbb = "3-100";
	String[] sdb = bbbb.split("-");
	for(String s:sdb)
		System.out.print(s+"--");
	}
	
}