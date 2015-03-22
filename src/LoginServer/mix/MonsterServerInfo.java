package LoginServer.mix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import LoginServer.utils.ByteUtil;

public class MonsterServerInfo {
	   private byte signal;
	   private String meshName;
	   private String displayName;
	   private int undefined1;
	   private float undefined2;
	   private float undefined3;
	   private int monsterLevel;
	   private String undefined4; //后面字符串的个数，这里暂时为1，要与deathLine做对比
	   //构造函数
	   public MonsterServerInfo(String meshName,int monsterLevel){
		   this.signal = 0;
		   this.meshName = meshName;
		   this.displayName = meshName;
		   this.undefined1 = 200;
		   this.undefined2 = 2.0f;  //model的大小倍率
		   this.undefined3 = 100.0f;
		   this.monsterLevel = monsterLevel;
		   this.undefined4 = "";
	   }
	   public MonsterServerInfo(String meshName,String displayname,int monsterLevel){
		   this.signal = 1;
		   this.meshName = meshName;
		   this.displayName = displayname;
		   this.undefined1 = 1;
		   this.undefined2 = 1.0f;
		   this.undefined3 = 1.0f;
		   this.monsterLevel = monsterLevel;
		   this.undefined4 = "";
	   }
	   public byte[] toByteArray(){
		   
		   ByteBuffer bb = ByteBuffer.allocate(128);
			  bb.order(ByteOrder.LITTLE_ENDIAN);
			  bb.put(this.signal);
			  bb.putInt(ByteUtil.getBytes(this.meshName).length);
			  bb.put(ByteUtil.getBytes(this.meshName));
			  bb.putInt(ByteUtil.getBytes(this.displayName).length);
			  bb.put(ByteUtil.getBytes(this.displayName));
			  bb.putInt(this.undefined1);
			  bb.putFloat(this.undefined2);
			  bb.putInt((int)this.undefined3);
			  bb.putInt(this.monsterLevel);
			  bb.putInt(ByteUtil.getBytes(this.undefined4).length);
			  bb.put(ByteUtil.getBytes(this.undefined4));
			  bb.flip();
			  byte[] temp = new byte[bb.limit()];
			  for(int i=0;i<temp.length;i++)
				  temp[i] = bb.get();
			  bb.clear();
			  return temp;
	   }
	   
	public byte getSignal() {
		return signal;
	}
	public void setSignal(byte signal) {
		this.signal = signal;
	}
	public String getMeshName() {
		return meshName;
	}
	public void setMeshName(String meshName) {
		this.meshName = meshName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getUndefined1() {
		return undefined1;
	}
	public void setUndefined1(int undefined1) {
		this.undefined1 = undefined1;
	}
	public float getUndefined2() {
		return undefined2;
	}
	public void setUndefined2(float undefined2) {
		this.undefined2 = undefined2;
	}
	public float getUndefined3() {
		return undefined3;
	}
	public void setUndefined3(float undefined3) {
		this.undefined3 = undefined3;
	}
	public int getMonsterLevel() {
		return monsterLevel;
	}
	public void setMonsterLevel(int monsterLevel) {
		this.monsterLevel = monsterLevel;
	}
	public String getUndefined4() {
		return undefined4;
	}
	public void setUndefined4(String undefined4) {
		this.undefined4 = undefined4;
	}
	   
	   
	   
	   
}
