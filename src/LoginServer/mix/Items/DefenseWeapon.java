package LoginServer.mix.Items;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import LoginServer.utils.ByteUtil;

public class DefenseWeapon extends SephirothItem{
	
	private byte itemtype;//05
	private float rate;
	private String defense;//防具防御力
    private byte canEquip=1;//是否能装备
    private int equipPlace;
    private int racetype;  //02
    private int jobtype;//04
    private String[] powers;//这之前有个byte是powers的长度,byte,int的方式存储，表示需要的属性
    private byte zero1 =0; //这2位暂时省略
    private int zero2 = 0;
    private String[] attrs;//物品的属性值，同样前面也有个byte是attrs的长度
    private int endsignal = 0x47;//具体含义暂时不知道 0x47
	public DefenseWeapon(String meshName,int lv,int equipplace,int racetype,int jobtype,String defensepower,String[] powers,String[] attrs){
		this.meshName = meshName;
		this.icon = meshName;
		this.name = meshName;
		this.showname= meshName;
		this.subdescription ="Lv 11 defenseWeapon"+meshName;
		this.zero = 0;
		this.widht = 2;
		this.height = 3;
		this.category ="DfnWpn";
		this.obj_9c = 0x16;
		this.obj_a0 = 0x2;
		this.obj_ac = 0;
		this.Durability = 298.00;
		this.maxDurability = 298.00;
		this.str_1 = 98575;//buy price
		this.str_2 = 8875; //sell price
		this.itemLv = lv;
		this.obj = 0;
		this.itemtype = 5;
		this.rate = 1.0f;
		this.defense = defensepower;
		switch(this.itemLv){
		case 0xA:
			this.showType = 1;
			break;
		case 0xB:
			this.showType = 2;
			break;
		case 0xC:
			this.showType = 3;
			break;
		default:
			this.showType = 1;
			break;
		}
		
		this.equipPlace = equipplace;
		this.racetype = racetype;
		this.jobtype = jobtype;
		this.powers = powers;
		this.attrs = attrs;
		this.obj =0;
	}
	
	
	
	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		ByteBuffer b = ByteBuffer.allocate(512);
		b.order(ByteOrder.LITTLE_ENDIAN);
		b.putInt(ByteUtil.getBytes(this.meshName).length);
		b.put(ByteUtil.getBytes(this.meshName));
		b.putInt(this.itemLv);
		b.put(this.showType);
		b.putInt(ByteUtil.getBytes(this.icon).length);
		b.put(ByteUtil.getBytes(this.icon));
		b.putInt(ByteUtil.getBytes(this.name).length);
		b.put(ByteUtil.getBytes(this.name));
		b.putInt(ByteUtil.getBytes(this.showname).length);
		b.put(ByteUtil.getBytes(this.showname));
		b.putInt(ByteUtil.getBytes(this.subdescription).length);
		b.put(ByteUtil.getBytes(this.subdescription));
		b.putInt(this.zero);
		b.putInt(this.widht);
		b.putInt(this.height);
		b.putInt(ByteUtil.getBytes(this.category).length);
		b.put(ByteUtil.getBytes(this.category));
		b.putInt(this.obj_9c);
		b.putInt(this.obj_a0);
		b.putInt((int)this.Durability);
		b.putInt((int)this.maxDurability);
		b.put(this.obj_ac);
		b.putLong(this.str_1);
		b.putLong(this.str_2);
		b.putInt(this.obj);
		b.put(this.itemtype);
		b.putFloat(this.rate);
		b.putInt(ByteUtil.getBytes(this.defense).length);
		b.put(ByteUtil.getBytes(this.defense));
		b.put(this.canEquip);
		b.putInt(this.equipPlace);
		b.putInt(this.racetype);
		b.putInt(this.jobtype);
		int nums = this.powers.length;
		b.put((byte)nums);
		for(String s:this.powers){
			String[] p = s.split("-");
			System.out.println(p[0]+":"+p[1]);
			byte  t = Byte.parseByte(p[0]);
			int v = Integer.parseInt(p[1]);
			b.put(t);
			b.putInt(v);
		}
		b.put(this.zero1);
		b.putInt(this.zero2);
		b.put((byte)this.attrs.length);
		for(String attr: attrs){
			b.putInt(ByteUtil.getBytes(attr).length);
			b.put(ByteUtil.getBytes(attr));
			b.putInt(ByteUtil.getBytes(attr).length);
			b.put(ByteUtil.getBytes(attr));
		}
		b.putInt(this.endsignal);
		b.flip();
		byte[] temp = new byte[b.limit()];
		  for(int i=0;i<temp.length;i++)
			  temp[i] = b.get();
		  b.clear();
		  return temp;
	}

}
