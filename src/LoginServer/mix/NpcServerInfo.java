package LoginServer.mix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import LoginServer.utils.ByteUtil;

public class NpcServerInfo {
	  private byte signal;
	  private byte signal2;
	  private String meshName;
	  private String displayName;
	  private double derecition; //高度
	  private String backname;
	  private float rate;  //放大倍数
	  public NpcServerInfo(String meshName,String backname){
		  this.signal = 1;
		  this.signal2 =0;
		  this.meshName = meshName;
		  this.displayName = meshName;
		  this.derecition = 0.0f;
		  this.backname = backname;
		  this.rate =5.0f;
	  }
	  public NpcServerInfo(byte signal,byte signal2,String meshName,double height,float rate){
		  this.signal = signal;
		  this.signal2 =signal2;
		  this.meshName = meshName;
		  this.displayName = meshName;
		  this.derecition = height;
		  this.backname = "";
		  this.rate =rate;
	  }
	  public byte[] toByteArray(){
		  ByteBuffer bb = ByteBuffer.allocate(128);
		  bb.order(ByteOrder.LITTLE_ENDIAN);
		  bb.put(this.signal);
		  bb.put(this.signal2);
		  bb.putInt(ByteUtil.getBytes(this.meshName).length);
		  bb.put(ByteUtil.getBytes(this.meshName));
		  bb.putInt(ByteUtil.getBytes(this.displayName).length);
		  bb.put(ByteUtil.getBytes(this.displayName));
		  bb.putInt((int)this.derecition);
		  if(this.backname != null){
		  bb.putInt(ByteUtil.getBytes(this.backname).length);
		  bb.put(ByteUtil.getBytes(this.backname));
		  }
		  else
		  {
			  bb.putInt(1);
			  bb.put((byte) 0);
		  }
		  bb.putFloat(this.rate);
		  bb.flip();
		  byte[] temp = new byte[bb.limit()];
		  for(int i=0;i<temp.length;i++)
			  temp[i] = bb.get();
		  bb.clear();
		  return temp;
	  }
}
/*0x4a,0x0d,0x01,0x00,0x07,0x00,0x00,0x00,0x4D,0x65,0x6C,0x76,0x69,0x6C,0x00,0x05,

0x00,0x00,0x00,0x4D,0x65,0x6C,0x76,0x00,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00,

0x00,0x00,0x00,0x80,0x3F, */