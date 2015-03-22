package LoginServer.mix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import LoginServer.utils.ByteUtil;

public class SecondSkillBook {
     private String messname;
    
	private String displayname;
	
     private String description;
     
     public SecondSkillBook(String messname){
    	 this.messname = messname;
    	 this.displayname = messname;
    	 this.description =messname;
     }
     
     public byte[] toByteArray(){
    	 
    	 ByteBuffer bb = ByteBuffer.allocate(128);
    	 bb.order(ByteOrder.LITTLE_ENDIAN);
    	 bb.putInt(ByteUtil.getBytes(this.messname).length);
    	 bb.put(ByteUtil.getBytes(this.messname));
    	 bb.putInt(ByteUtil.getBytes(this.displayname).length);
    	 bb.put(ByteUtil.getBytes(this.displayname));
    	 bb.putInt(ByteUtil.getBytes(this.description).length);
    	 bb.put(ByteUtil.getBytes(this.description));
    	 for(int i=0;i<4;i++)
    		 bb.putInt(0);
    	 bb.flip();
    	 byte[] temp = new byte[bb.limit()];
    		for(int j=0;j<temp.length;j++){
				temp[j] = bb.get();
			}
			return temp;
     }
     
     
     public String getMessname() {
 		return messname;
 	}
 	public void setMessname(String messname) {
 		this.messname = messname;
 	}
 	public String getDisplayname() {
 		return displayname;
 	}
 	public void setDisplayname(String displayname) {
 		this.displayname = displayname;
 	}
 	public String getDescription() {
 		return description;
 	}
 	public void setDescription(String description) {
 		this.description = description;
 	}
     
}
