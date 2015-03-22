package LoginServer.mix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import LoginServer.mix.Items.DefenseWeapon;
import LoginServer.packets.Packet;
import LoginServer.utils.ByteUtil;

public class ConsoleCommand {
	  public static int MONSTART_ID = 0x037C25;//怪物的起始id；
	  public static int NPC_START_ID = 0x0004B810;//npc起始的id；
	 public static int  timedelttime = 0;
	 public static int  title = 0;
	//  ./addmonster monitorname lv x:y:z;
		/**
		 * 添加怪物
		 * @param s
		 * @return
		 */
		public static ByteBuffer addMonster(String[] s){
			System.out.println("执行addMonster命令");
			int monlv = Integer.parseInt(s[2]);
			String[] xy = s[3].split(":");
		  MonsterServerInfo msi = new MonsterServerInfo(s[1],monlv);
		  //生成要发送的包。
		  byte[] pos = Packet.posPacket(MONSTART_ID, xy);
		  byte[] moninfo = Packet.generateMainPacket(0x0D49, msi.toByteArray());
		  MONSTART_ID++;
		  return	   Packet.generatePacket(0x1411, pos, moninfo);
	
		}
		
		//添加npc
		//./addnpc signal signal2 meshname float rate pos
		public static  byte[] addNpc(String[] npcinfo){
			byte signal = Byte.parseByte(npcinfo[1]);
			byte signal2 = Byte.parseByte(npcinfo[2]);
			double  height = Double.parseDouble(npcinfo[4]);
			float rate = Float.parseFloat(npcinfo[5]);
			String[] pos = npcinfo[6].split(":");
			byte[] position = Packet.posPacket(NPC_START_ID, pos);
			NpcServerInfo nsi = new NpcServerInfo(signal,signal2,npcinfo[3],height,rate);
			byte[] npc = Packet.generateMainPacket(0x0D4B, nsi.toByteArray());
			NPC_START_ID++;
			return Packet.generatePacket(0x1411, position, npc).array();
			
		}
		
		
		
		//notimsg  channel msg
		public static byte[] notiMsg(String [] message){
			int channel =Integer.parseInt(message[1]);
			String msg =message[2];
			byte[] b = ByteUtil.getBytes(msg);
			ByteBuffer leng = ByteBuffer.allocate(8+b.length);
			leng.order(ByteOrder.LITTLE_ENDIAN);
			leng.putInt(channel);  //@RGB=1-200-250 0只在顶部显示，1只在顶部显示
			leng.putInt(b.length);
			leng.put(b);
			leng.flip();
			byte[] temp = Packet.generateMainPacket(0x0D7B,leng.array());  
			
			return Packet.generatePacket(0x140A, null, temp).array();
		}
		
		/**
		 * @param items
		 * @return   x y meshname lv equipplace racetype jobtype defensepower  key|value,key|value attrs
		 */ // ./additem 0 0 FabulaSandal 11 8 1 40 100+200 3-100,2-200 GodMagicDefense09
		public static byte[] addInventoryItem(String[] items){
			int x = Integer.parseInt(items[1]);
			int y = Integer.parseInt(items[2]);
			String meshName = items[3];
			int lv = Integer.parseInt(items[4]);
			int equipplace = Integer.parseInt(items[5]);
			int racetype = Integer.parseInt(items[6]);
			int jobtype = Integer.parseInt(items[7]);
			String defensepower = items[8];
			String t = items[9];
			String[] powers =t.split(",");
			  t = items[10];
			String[] attrs =t.split(",");
			DefenseWeapon dw = new DefenseWeapon(meshName,lv,equipplace,racetype,jobtype,defensepower,powers,attrs);
			byte  weapon[] = dw.toByteArray();
			ByteBuffer inventoryweapon = ByteBuffer.allocate(weapon.length+8);
			inventoryweapon.order(ByteOrder.LITTLE_ENDIAN);
			inventoryweapon.putInt(x);
			inventoryweapon.putInt(y);
			inventoryweapon.put(weapon);
			inventoryweapon.flip();
			byte[] temp = Packet.generateMainPacket(0x00D4,inventoryweapon.array());  
			
			return Packet.generatePacket(0x140A, null, temp).array();
		}
		
		/** notiCastleinfo
		 * @param s
		 * @return  ./castle hellobaby!
		 */
		public static byte[] notiCastleInfo(String[] s){
			byte[] info =ByteUtil.getBytes(s[1]);
			ByteBuffer f = ByteBuffer.allocate(info.length+4);
			f.order(ByteOrder.LITTLE_ENDIAN);
			f.putInt(info.length);
			f.put(info);
			f.flip();
			byte[] temp = Packet.generateMainPacket(0x0299,f.array());
			
			return Packet.generatePacket(0x140A, null, temp).array();
		}
		
		
		/**
		 * @param s  测试140D
		 * @return
		 */
		public static byte[] moveReq(String[] s){
			
							byte[] result ={0x00,0x00,(byte) 0xac,0x5d,(byte) 0xFb,(byte) 0xFF,(byte) 0xc0,(byte) 0x92,0x0C,0x00,0x00,0x00,0x00,0x00,(byte) 0xAC,(byte) 0x93,

					(byte) 0x80,0x53};
						int type =Integer.parseInt(s[1]);
						result[14]  = (byte) (timedelttime+1);
						result[0] = (byte) ((byte) title+1);
						timedelttime++;
						title++;
						
							
			return Packet.generatePacket(0x140D, result,null).array();
		}
}
