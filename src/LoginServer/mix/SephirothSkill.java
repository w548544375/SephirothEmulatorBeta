package LoginServer.mix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import LoginServer.utils.ByteUtil;

public class SephirothSkill {
		private String name;
		private String displayName;
		private String description;
		private boolean blearn;
		private int lv;
		private byte attNums;
		private Attrs[] atts;
		private byte needSkillNums;
		private NeedSkill[] skills;
		
		public SephirothSkill(String name){
			this.name = name;
			this.displayName=name;
			this.description="落JJ是傻逼";
			this.blearn = true; //表示是否需要sp  1需要 0不需要
			this.lv =20;  //表示耗蓝
			this.attNums = 0;
			this.atts = null;
			this.needSkillNums = 0;
			this.skills = null;
		}
		
		public SephirothSkill(String name,Attrs[] attrs,NeedSkill[] skills){
			this.name = name;
			this.displayName=name;
			this.description="Not Now";
			this.blearn = false;
			this.lv =0;
			this.attNums = (byte) attrs.length;
			this.atts = attrs;
			this.skills = skills;
		}
		
		public byte[] toByteArray(){
			ByteBuffer bb =ByteBuffer.allocate(128);
			bb.order(ByteOrder.LITTLE_ENDIAN);
			bb.putInt(ByteUtil.getBytes(this.name).length);
			bb.put(ByteUtil.getBytes(this.name));
			bb.putInt(ByteUtil.getBytes(this.displayName).length);
			bb.put(ByteUtil.getBytes(this.displayName));
			bb.putInt(ByteUtil.getBytes(this.description).length);
			bb.put(ByteUtil.getBytes(this.description));
			if(this.blearn)
				bb.put((byte) 1);
			else
				bb.put((byte) 0);
			bb.putInt(this.lv);
			if(this.atts == null){
				bb.put((byte) 0);
				//bb.put((byte) 0);
				//bb.putInt(0);
			}
			else{
			bb.put((byte) atts.length);
				for(Attrs a: atts){
					bb.put(a.type);
					bb.putInt(a.value);
				}
			}
			if(this.skills == null){
				bb.put((byte) 0);
			}
			else{
			  bb.put((byte) skills.length);
			 for(NeedSkill ns:skills){
				 bb.putInt(ByteUtil.getBytes(ns.skillname).length);
				 bb.put(ByteUtil.getBytes(ns.skillname));
				 bb.putInt(ns.skilllevel);
			 }
			}
			bb.flip();
			byte[] result = new byte[bb.limit()];
			for(int i=0;i<result.length;i++){
				result[i] = bb.get();
			}
			return result;
		}
		
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public boolean isBlearn() {
			return blearn;
		}
		public void setBlearn(boolean blearn) {
			this.blearn = blearn;
		}
		public int getLv() {
			return lv;
		}
		public void setLv(int lv) {
			this.lv = lv;
		}
		public byte getAttNums() {
			return attNums;
		}
		public void setAttNums(byte attNums) {
			this.attNums = attNums;
		}
		public Attrs[] getAtts() {
			return atts;
		}
		public void setAtts(Attrs[] atts) {
			this.atts = atts;
		}
		public byte getNeedSkillNums() {
			return needSkillNums;
		}

		public void setNeedSkillNums(byte needSkillNums) {
			this.needSkillNums = needSkillNums;
		}

		public NeedSkill[] getSkills() {
			return skills;
		}

		public void setSkills(NeedSkill[] skills) {
			this.skills = skills;
		}
}

