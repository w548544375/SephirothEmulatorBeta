package LoginServer.mix;

import LoginServer.mix.Items.WornItemLobbyInfo;

public class PlayerServerInfo {
	 private String name;
	 private String raceName;
	 private int isMale;  //0 女性 1 男性
	 private String jobName;
	 private long lastLoginTime;
	 private String EDI1C;
	 private String EDI28;
	 private String hair;
	 private String EDI38;
	 private String body;//HunterSuit
	 private String castle;//所在的城市
	 private int equipNum;//身上装备数量  中间要跳过16个byte
	 /**接下来是0x19个字符串的对比*/
	 /**一个wornitems的数组*/
	 WornItemLobbyInfo[] wilb;
	 private byte EDI60;
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 /******get 和set 方法******/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRaceName() {
		return raceName;
	}
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}
	public int getIsMale() {
		return isMale;
	}
	public void setIsMale(int isMale) {
		this.isMale = isMale;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getEDI1C() {
		return EDI1C;
	}
	public void setEDI1C(String eDI1C) {
		EDI1C = eDI1C;
	}
	public String getEDI28() {
		return EDI28;
	}
	public void setEDI28(String eDI28) {
		EDI28 = eDI28;
	}
	public String getHair() {
		return hair;
	}
	public void setHair(String hair) {
		this.hair = hair;
	}
	public String getEDI38() {
		return EDI38;
	}
	public void setEDI38(String eDI38) {
		EDI38 = eDI38;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCastle() {
		return castle;
	}
	public void setCastle(String castle) {
		this.castle = castle;
	}
	public int getEquipNum() {
		return equipNum;
	}
	public void setEquipNum(int equipNum) {
		this.equipNum = equipNum;
	}
	public WornItemLobbyInfo[] getWilb() {
		return wilb;
	}
	public void setWilb(WornItemLobbyInfo[] wilb) {
		this.wilb = wilb;
	}
	public byte getEDI60() {
		return EDI60;
	}
	public void setEDI60(byte eDI60) {
		EDI60 = eDI60;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
