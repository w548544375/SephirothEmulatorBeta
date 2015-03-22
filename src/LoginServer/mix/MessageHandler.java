package LoginServer.mix;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;

import LoginServer.ReadPackage;
import LoginServer.dataCommbine;
import LoginServer.mix.Message;
import LoginServer.packets.Packet;
import LoginServer.utils.ByteUtil;
public class MessageHandler {
	public static final short cznewclientpacket= 0x1401;
	public static final short zcconnectokpacket=0x1403;
	public static final short cztellpacket = 0x1409;
	public static final short zctellpacket =0x140A;
	public static final short czreqmovepacket = 0x140C;
	public static final byte TYPE_DELETE_CHARACTER = (byte) 0xEB ; //3EB  删除角色
	public static final byte TYPE_NEW_CHARACTER = (byte) 0xEA ; //新角色
	public static final byte TYPE_LOGIN = (byte) 0x83;  //unreal 3
	public static final byte TYPE_LOGIN_OLD =(byte) 0xE9;//turfbattles  03E9
	public static final byte TYPE_LOGIN_GAME_OLD =(byte) 0xEC; //turfbattles 03EC
	public static final byte TYPE_DELETE_CHARACTER_OLD= (byte) 0xEB;//turfbattles 0x03EB
	public static final byte TYPE_CREATE_CHARACTER_OLD =(byte)0xEA;//0x03EA;
	public static final byte TYPE_SAY_TB=0x6A;    //说话
	public static final byte TYPE_PROTOCOL_TB =0x70;
	public static final byte TYPE_SERVERINFO = (byte)0xDA;
	public static final byte TYPE_PRE = 0x6B;
	public static final byte TYPE_CHARACTER_INFO = (byte) 0x8E;
	public static final byte TYPE_CREATE_CHARACTER =(byte) 0x84;
	public static final byte TYPE_LOGIN_GAME = (byte) 0x86; //发送的是选择角色的名字
	public static final byte TYPE_FIGHT = 0x25; //切换战斗状态 战斗
	public static final byte TYPE_FIGHT_OLD = 0x64; //turf battles
	public static final byte TYPE_PEACE =0x26; // 和平
	public static final byte TYPE_DROP =0x21;//丢弃
	public static final byte TYPE_MOVEPOINT = 0x15;//查看存储点信息
	public static final byte TYPE_MOVE = 0x16;//移动
	public static final byte TYPE_MOVE_FROM_BAG = 0x35;//背包中移除物品
	private LinkedBlockingQueue<Message> sendQueue = null;
	private ReadPackage rp;
	private ReadPackage move;
	public MessageHandler() {
		// TODO Auto-generated constructor stub
		sendQueue =ProcessConnections.sendQueue;
		rp = new ReadPackage("./Package-空战士.txt");
		move = new ReadPackage("./腿.txt");
	}
	
	
	/**
	 * 解析消息，分发到处理函数
	 * @param message
	 * @return
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public Message parseMessage(Message message) throws InterruptedException, IOException{
		ByteBuffer data = message.getData();
		//忽略大小为256的包，很可能出现其他包大小也为256确被忽略的可能
		if(data.limit() == 256){
			return null;
		}
		if(data.limit() == 274){
			return new Message(message.getWho(),HandleNewClient());
		}
		message.getData().order(ByteOrder.LITTLE_ENDIAN);
		System.out.println("接收数据:"+data.limit());
		for(int i =0;i <data.limit();i++)
			System.out.print(Integer.toHexString(data.get()&0xff).toUpperCase()+",");
			System.out.println();
			//重置data的指针到第一个位置
			data.position(0);
		Short head = this.parse(data);
		switch(head){
		case cznewclientpacket:
			return new Message(message.getWho(),HandleNewClient());
		case cztellpacket:
		  this.cztellPacket(message.getWho(),data);
		  break;
		case czreqmovepacket:
		this.czReqMove(message.getWho(),data);
			break;
		
			}
		return null;
	}
	
	//1401
	public ByteBuffer HandleNewClient(){
		System.out.println("开始传送数据");
		byte[] connectOk = {0x03,0x14,0x04,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x07,0x14,0x01,0x00,0x01,0x00};
		return ByteBuffer.wrap(connectOk);
	}
	
	public void cztellPacket(SocketChannel channel,ByteBuffer data) throws InterruptedException, IOException{
		// data.position(0);
		//将position指向18位之后
		 data.position(18);
		 //复制低18位到0位
		 data.compact();
		 //重新设置缓冲区的大小
		 data.flip();
		 //获得18字节后面的数据包
		 /*System.out.println("数据最大长度:"+data.limit());
			for(int i =0;i <data.limit();i++)
				System.out.print(Integer.toHexString(data.get()&0xff).toUpperCase()+",");
				System.out.println();
				data.flip();		*/
		byte msgType = data.get();
		switch(msgType){
			case TYPE_LOGIN_OLD:
				this.sendQueue.put(new Message(channel,generateSendData(zctellpacket,ServerInfo("Fuck you"))));
				/*ByteBuffer b = generateSendData(zctellpacket,ServerInfo("测试"));
				for(int i=0;i<b.limit();i++){
					System.out.print(Integer.toHexString(b.get()&0xff)+",");
				}*/
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(5))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(6))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(7))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(8))));
				break;
			case TYPE_LOGIN_GAME_OLD:
				for(int i=11;i<19;i++){
					if(i==17){
					 ByteBuffer bbb = ByteBuffer.wrap(rp.readRecv(17));
					 bbb.order(ByteOrder.LITTLE_ENDIAN);
					 bbb.position(4);
					 bbb.putInt(2291);
					 bbb.position(16);
					 bbb.putShort((short) (2291^0x1404^4));
					 bbb.flip();
					 this.sendQueue.put(new Message(channel,bbb));
					 continue;
					}
					this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(i))));
				}
			    Thread.sleep(200);
	/*			  byte[] temp=  dataCommbine.commbineData(rp.readRecv(20), rp.readRecv(21));
				  byte[] result = dataCommbine.commbineData(rp.readRecv(19), temp);
				  this.sendQueue.put(new Message(channel,ByteBuffer.wrap(result)));*/
			     byte[] c1 = rp.readRecv(19);
			     ByteBuffer bb = ByteBuffer.wrap(c1);
			     bb.order(ByteOrder.LITTLE_ENDIAN);
			     bb.position(36*16+12);
			     for(int j=37*16+12;j<c1.length;j++){
			    	  c1[j] = 0;
			      }
			      String[] skillsname ={"VSwing","BladeCombination","Splash","Prod","OH_Combination","OH_Concentration",
			    		  "Absolute","BusterMove","BladeDash","DragonFly","TripleSwing","Butterfly","ComboSplash","FrontSplash",
			    		  "BloodySplash","GushFly"
			      };
			      for(String s:skillsname){
			    	  SephirothSkill ss = new SephirothSkill(s);
			    	  bb.put(ss.toByteArray());
			      }
			      bb.putInt(1);//这个为0就会跳
			      bb.putInt(ByteUtil.getBytes("VSwing").length);
			      bb.put(ByteUtil.getBytes("VSwing"));
			      bb.putInt(ByteUtil.getBytes("OneHand").length);
			      bb.put(ByteUtil.getBytes("OneHand"));
			      bb.put((byte) 0); //1表示不断重复
			      bb.putInt(80); //技能等级
			      bb.put((byte) 1);//这个为0就要跳过  这个表示如果不要sp就跳过sp点数
			      bb.put((byte) 20);  //跳过    |
			      bb.putInt((int)50.0f); //跳过  |    sp点数
			      bb.put((byte) 1);  //为0就没有动作1
			      bb.putInt((int)1.00); //int 转double运算
			      bb.putInt((int)0.00); //double  结束是否立即重置大于1就僵住
			      bb.putInt((int)650.00); //double 动作速度
			      bb.putInt((int)100.00); //double
			      bb.putInt((int)100.00); //int
			      bb.putInt(100);
			      bb.putFloat(1f);   //baseSpeed
			      bb.putFloat(650f); //walkingpct
			      bb.putFloat(100f);//rotation
			      bb.putInt(10);
			      bb.putInt(20);
			      bb.putInt(30);  //sc点数
			      bb.putInt(40);
			     String[] secondSkill={"BlastBlade","SoulBlade","Endure","Might","GuardianProtection","Transformation","UltraEndure","AbsoluteDefense",
			    		  "BattleCry","Assault","BladeStorm","Nightmare"};
			      bb.putInt(secondSkill.length);
			      for(String s:secondSkill){
			    	  SecondSkillBook ssb = new SecondSkillBook(s);
			    	  bb.put(ssb.toByteArray());
			      }
			      bb.put((byte) 1);
			      bb.put((byte) 0);
			      bb.putInt(0);
			      bb.putInt(0);
			      bb.putInt(0);
			      bb.put((byte) 0); //效果
			      bb.put((byte) 0);
			      bb.put((byte) 1);
			      bb.putInt(0);
			      bb.putInt(0);
			      bb.putInt(0);
			      bb.putInt(0);
			      bb.putInt(1);
			      bb.flip();
			     System.out.println(bb.limit());
			      this.sendQueue.put(new Message(channel,bb));
			    
					//this.sendQueue.put(new Message(channel,ByteBuffer.wrap(dataCommbine.commbineData(rp.readRecv(19), rp.readRecv(20)))));
					for(int i=21;i<23;i++){
						this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(i))));
					}
				//	for(int i=11;i<50;i++)
				//	this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(i))));
					break;
			case  TYPE_FIGHT_OLD:
			/*	ReadPackage pos = new ReadPackage("./pos.txt");
				int[] xyz = pos.readPosition();
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(21))));
				ByteBuffer bbc = ByteBuffer.wrap(rp.readRecv(22));
				bbc.order(ByteOrder.LITTLE_ENDIAN);
				bbc.position(6);
				bbc.putInt(xyz[0]);
				bbc.putInt(xyz[1]);
				bbc.putInt(xyz[2]);
				bbc.flip();
				this.sendQueue.put(new Message(channel,bbc));*/
				//this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(27))));
			/*	MonsterServerInfo msi = new MonsterServerInfo("LE_OneEyeFisherman",1);
				ByteBuffer bbmsi = ByteBuffer.allocate(256);
				bbmsi.order(ByteOrder.LITTLE_ENDIAN);
				bbmsi.put(rp.readRecv(26));
				bbmsi.putShort((short) 0x0d49);
				bbmsi.put(msi.toByteArray());
				bbmsi.flip();
				ByteBuffer before = ByteBuffer.wrap(rp.readRecv(25));
				before.order(ByteOrder.LITTLE_ENDIAN);
				before.position(4);
				before.putInt(msi.toByteArray().length+2);
				before.position(16);
				before.putShort((short) (0x1411^0x10^(msi.toByteArray().length+2)));
				before.flip();
				this.sendQueue.put(new Message(channel,before));
				this.sendQueue.put(new Message(channel,bbmsi));*/
			/*	NpcServerInfo nsi = new NpcServerInfo("Methinel",null);
				ByteBuffer bbnsi = ByteBuffer.allocate(256);
				bbnsi.order(ByteOrder.LITTLE_ENDIAN);
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(rp.readRecv(28))));
				Thread.sleep(300);
				bbnsi.put(rp.readRecv(29));
				bbnsi.putShort((short) 0x0d49);
				bbnsi.put(nsi.toByteArray());
				bbnsi.flip();
				this.sendQueue.put(new Message(channel,bbnsi));*/
		/*		this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(3))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(4))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(5))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(6))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(7))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(8))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(9))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(10))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(11))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(12))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(13))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(14))));*/
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(5))));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(move.readRecv(6))));
				break;
				
			case TYPE_SAY_TB:
				 data.get();
				 byte sayType = data.get();//讲话还是呼喊
				 int len = data.getInt();
				 byte[] whatyousay = new byte[len];
				 data.get(whatyousay,0,len);
				 doCommand(channel,ByteUtil.getString(whatyousay));
				
/*				//通知时间
					byte[]  pre ={0x0A,0x14,0x00,0x00,0x07,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x0D,0x14};
					byte[]  post ={(byte) 0x7A,0x0D,0x41,(byte) 0x4a,(byte) 0x83,0x53,0x00};
					this.sendQueue.put(new Message(channel,ByteBuffer.wrap(pre)));
					this.sendQueue.put(new Message(channel,ByteBuffer.wrap(post)));*/
					//zoneinfo
				   /* ByteBuffer  bzone = ByteBuffer.allocate(256);
				    bzone.order(ByteOrder.LITTLE_ENDIAN);
				    bzone.putShort((short) 0x55F0);
				    bzone.putInt(ByteUtil.getBytes("Ladianes").length);
				    bzone.put(ByteUtil.getBytes("Ladianes"));
				    String str="The Battle Ground,NOPVP=1,NOSTAMINA=1";
				    *//**,NOATTACT=1,UNDERSIEGE=1,NOBOOTH=1,NOMINIMAP=1*//*
				    bzone.putInt(ByteUtil.getBytes(str).length);
				    bzone.put(ByteUtil.getBytes(str));
				    bzone.flip();
				    byte[]  pre ={0x0A,0x14,0x00,0x00,0x07,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x0D,0x14};
				    ByteBuffer presend =ByteBuffer.wrap(pre);
				    presend.order(ByteOrder.LITTLE_ENDIAN);
				    presend.position(4);
				    presend.putInt(bzone.limit());
				    presend.position(16);
				    presend.putShort((short) (0x140A^bzone.limit()));
				    presend.flip();
				    this.sendQueue.put(new Message(channel,presend));
				    this.sendQueue.put(new Message(channel,bzone));*/
				break;
			case TYPE_PROTOCOL_TB:
				 ///game manager info
			    String music = "HAmb_LadianesVille_Night";
			    int length = ByteUtil.getBytes(music).length;
			    ByteBuffer bgminfo = ByteBuffer.allocate(128);
			    bgminfo.order(ByteOrder.LITTLE_ENDIAN);
			    bgminfo.putShort((short) 0x55f1);
			    bgminfo.putInt(01);
			    bgminfo.putInt(length);
			    bgminfo.put(ByteUtil.getBytes(music));
			    bgminfo.putInt(01);
			    bgminfo.putInt(ByteUtil.getBytes("HAmb_LadianesVille_Day").length);
			    bgminfo.put(ByteUtil.getBytes("HAmb_LadianesVille_Day"));
			    bgminfo.flip();
			    byte[]  pre1 ={0x0A,0x14,0x00,0x00,0x07,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x0D,0x14};
			    ByteBuffer bgmpre = ByteBuffer.wrap(pre1);
			    bgmpre.order(ByteOrder.LITTLE_ENDIAN);
			    bgmpre.position(4);
			    bgmpre.putInt(bgminfo.limit());
			    bgmpre.position(16);
			    bgmpre.putShort((short) (0x140A^bgminfo.limit()));
			    bgmpre.flip();
			    this.sendQueue.put(new Message(channel,bgmpre));
			    this.sendQueue.put(new Message(channel,bgminfo));
				break;
			case TYPE_NEW_CHARACTER: //创建新角色
				byte[] newbefore ={0x0A,0x14,0x00,0x00,0x07,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,

0x0D,0x14};
				byte[] newafter={(byte) 0x8A,0x13,0,0,0,0,0};
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(newbefore)));
				this.sendQueue.put(new Message(channel,ByteBuffer.wrap(newafter)));
				break;
				
			
			case TYPE_DELETE_CHARACTER://删除角色
				byte[] delbefore ={0x0A,0x14,0x00,0x00,0x07,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
						0x0D,0x14};
										byte[] delafter={(byte) 0x8B,0x13,0,0,0,0,0};
										this.sendQueue.put(new Message(channel,ByteBuffer.wrap(delbefore)));
										this.sendQueue.put(new Message(channel,ByteBuffer.wrap(delafter)));
				break;
			}
		
	}
	
	/**
	 *   产生要发送的数据包
	 * @param packettype
	 * @param info
	 * @return
	 */
	public ByteBuffer generateSendData(short packettype,ByteBuffer info){
		ByteBuffer serverinfo = ByteBuffer.allocate(256);
		serverinfo.order(ByteOrder.LITTLE_ENDIAN);
		serverinfo.putShort(packettype);
		serverinfo.putShort((short) 0);
		serverinfo.putShort((short) info.limit());
		serverinfo.putShort(16, (short) (packettype^info.limit()));
		serverinfo.position(18);
		serverinfo.put(info);
		serverinfo.flip();
		return serverinfo;
	}
	//玩家角色服务器信息
	public ByteBuffer ServerInfo(String name){
		ByteBuffer b = ByteBuffer.allocate(128);
		//System.out.println(name.toCharArray().toString());
		b.order(ByteOrder.LITTLE_ENDIAN);
		b.put(TYPE_SERVERINFO);
		b.putShort((short)1);
		b.putInt(1);
		try {
			b.putInt(name.getBytes("gbk").length+1);
			b.put(name.getBytes("gbk"));
			b.put((byte) 0);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.putInt(1);
		b.put((byte) 0);
		b.flip();
		return b;
	}
	
	public ByteBuffer PlayerServerInfo(){
		ByteBuffer rs = ByteBuffer.allocate(128);
		rs.put(TYPE_CHARACTER_INFO);
		rs.put((byte) 0);
		rs.put((byte) 0);
		rs.putInt(0);
		rs.flip();
		return rs;
	}
	
	
	
	/**
	 *   解析数据包，进行处理
	 * @param data
	 */
	public Short parse(ByteBuffer data){
		short head = data.getShort();
		short length1 = data.getShort();
		short length2 = data.getShort();
		short last = data.getShort(16);
		
		//System.out.println("压缩后的大小:"+data.limit());
		if((head^length1^length2) != last)
			return -1;
		return head;
	}
	
	
	/**  通过说话来发送命令
	 * @param commond
	 * @throws InterruptedException 
	 */
	public void doCommand(SocketChannel channel,String commond) throws InterruptedException{
		if(!commond.startsWith("./"))
			return;
	String	whattodo = commond.substring(2);
	String[] commonds =whattodo.split(" ");
	          whattodo = commonds[0];
	          //增加monster
	       if("addmonster".equals(whattodo)){
	    	   ByteBuffer bresult = ConsoleCommand.addMonster(commonds).duplicate();
	    	   bresult.limit(18);
	    	   this.sendQueue.put(new Message(channel,bresult));
	    	   bresult.limit(bresult.capacity());
	    	   this.sendQueue.put(new Message(channel,bresult));
	       }
	       //增加npc
	       else  if("addnpc".equals(whattodo)){
	    	   incisionPacket(channel,ConsoleCommand.addNpc(commonds));
	      }
	       else if("notimsg".equals(whattodo)){
	    	   incisionPacket(channel,ConsoleCommand.notiMsg(commonds));
	       }
	       else if("additem".equals(whattodo)){
	    	 incisionPacket(channel,ConsoleCommand.addInventoryItem(commonds));
	       }
	       else if("castle".equals(whattodo)){
	    	 incisionPacket(channel,ConsoleCommand.notiCastleInfo(commonds));
	       }
	       else if("movereq".equals(whattodo)){
	    	incisionPacket(channel,ConsoleCommand.moveReq(commonds));
	       }
	       else
	    	   System.out.println(whattodo);
	}
	
	///////客户端发送请求move的要求处理函数
	public void czReqMove(SocketChannel socketChannel, ByteBuffer data) throws InterruptedException{
			data.position(18);
			int index = data.getShort();
			double x = (double)data.getInt()/100+26009;
			double y =(double)data.getInt()/100+26009;
			System.out.println("Index:"+index+",X="+Math.round(x)+",Y="+Math.round(y));
			String[] b = {"req","1"};
			incisionPacket(socketChannel,ConsoleCommand.moveReq(b));
	}
	
	/** 切割消息进行发送
	 * @param channel
	 * @param msg
	 * @throws InterruptedException
	 */
	public void incisionPacket(SocketChannel channel,byte[] src) throws InterruptedException{
		ByteBuffer msg = ByteBuffer.wrap(src);
		msg.limit(18);
 	   this.sendQueue.put(new Message(channel,msg));
 	   msg.limit(msg.capacity());
 	   this.sendQueue.put(new Message(channel,msg));
	}
	
	
	
}
