package com.weiguang.test;

import com.weiguang.sephiroth.util.ByteUtil;
import org.apache.ibatis.io.Resources;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by wWX313869 on 2017/1/9.
 */
public class TestSize {
    public static String path = "D:\\MarsWorkSpace\\Spring-Netty\\src\\com\\weiguang\\test\\";
    @Test
    public void TestReadBareData() {
        int length1 = Data1.array.length;
        int length2 = Data2.array.length;
        int length3 = Data3.array.length;
        int total = length1 + length2 + length3;
        System.out.println("1段：" + length1 + ",2段：" + length2 + ",3段：" + length3 + ",合计:" + total);
        ByteBuffer buffer = ByteBuffer.allocate(total);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(Data1.array);
        buffer.put(Data2.array);
        buffer.put(Data3.array);
        buffer.flip();
        try {
            PlayerServerInfo(buffer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestReadLobbyInfo() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path+"PlayerLobbyInfoTxt.txt"));
        String line = reader.readLine();
        String[] arrys = line.split(" ");
        byte[] byteArray = new byte[arrys.length];
        for (int i = 0; i < arrys.length; i++) {
            byteArray[i] = (byte) ((byte) Integer.parseInt(arrys[i], 16) & 0xFF);
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        PlayerLobbyInfo(buffer);
    }


    public void PlayerLobbyInfo(ByteBuffer buffer) throws UnsupportedEncodingException {
        int characterNumber = buffer.get();
        for(int i =0;i<characterNumber;i++){
            System.out.println("Name："+ByteUtil.getString(buffer));
            System.out.println("Race："+ByteUtil.getString(buffer));
            System.out.println( "Sex:"+(buffer.getInt()== 0 ? "女":"男"));
            System.out.println("JobName："+ByteUtil.getString(buffer));
            System.out.println( "NeedJobChange:"+buffer.getInt());
            Calendar calendar =  Calendar.getInstance();
            calendar.setTimeInMillis(buffer.getInt());
            System.out.println("LastLoginTime:"+calendar.getTime().toString());
            System.out.println("Trible："+ByteUtil.getString(buffer));
            System.out.println("TribleDesc："+ByteUtil.getString(buffer));
            System.out.println("HairName："+ByteUtil.getString(buffer));
            System.out.println("FaceName："+ByteUtil.getString(buffer));
            System.out.println("BodyName："+ByteUtil.getString(buffer));
            System.out.println("Alingnment:"+buffer.getInt());
            System.out.println("CastleName："+ByteUtil.getString(buffer));
            System.out.println("PlayerLv:"+buffer.getInt());
            System.out.println("CurExp/MaxExp:"+buffer.getLong()+"/"+buffer.getLong());
            String wornItemsCase = "";
            for (int j = 0; j < 25; j++) {
                byte hasEquip = buffer.get();
                if (hasEquip == 1) {
                    System.out.println("=========================================");
                    System.out.println("ItemNameId："+ByteUtil.getString(buffer));
                    System.out.println("ItemLv:"+buffer.getInt());
                    byte attr = buffer.get();
                    for(int k=0;k<attr;k++){
                        System.out.println("EnglishAttr："+k+":"+ByteUtil.getString(buffer));
                        System.out.println("CNAttr："+k+":"+ByteUtil.getString(buffer));
                    }
                    System.out.println("ModelName:"+ByteUtil.getString(buffer));
                    System.out.println("ShowNameLocalized:"+ByteUtil.getString(buffer));
                    System.out.println("Int:"+buffer.getInt());
                    System.out.println("Byte:"+buffer.get());
                    System.out.println("EquipPlace:"+buffer.getInt());
                    System.out.println("Int:"+buffer.getInt());
                    System.out.println("Width:"+buffer.getInt());
                    System.out.println("Height:"+buffer.getInt());
                    int num = buffer.getInt();
                    for(int k=0;k<num;k++){
                        System.out.println("String:"+ByteUtil.getString(buffer));
                    }
                    int bool1 = buffer.get();
                    if(bool1 != 0){
                        System.out.println("Int:"+buffer.getInt());
                    }
                    int bool2= buffer.get();
                    if(bool2 != 0){
                        System.out.println("Int:"+buffer.getInt());
                    }
                    wornItemsCase += "1";
                }else {
                    wornItemsCase += "0";
                }
            }
            System.out.println(wornItemsCase);
            buffer.position(buffer.position()+8);
        }
    }



    @Test
    public void TestReadRedData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path+"RedMagicData.txt"));
        String line = reader.readLine();
        String[] arrys = line.split(" ");
        byte[] byteArray = new byte[arrys.length];
        for (int i = 0; i < arrys.length; i++) {
            byteArray[i] = (byte) ((byte) Integer.parseInt(arrys[i], 16) & 0xFF);
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        PlayerServerInfo(buffer);
    }


    public void PlayerServerInfo(ByteBuffer buffer) throws UnsupportedEncodingException {
        String name = ByteUtil.getString(buffer);
        System.out.println("PlayerName:" + name);
        String raceName = ByteUtil.getString(buffer);
        OutString("RaceName", raceName);
        byte sex = buffer.get();
        System.out.println("Sex:" + (sex != 0 ? "男" : "女"));
        String job = ByteUtil.getString(buffer);
        OutString("JobName:", job);
        System.out.println("UnknowInt:" + buffer.getInt());
        System.out.println("UnknowByte:" + buffer.get());
        System.out.println("Trible:" + ByteUtil.getString(buffer));
        System.out.println("TribleDes:" + ByteUtil.getString(buffer));
        System.out.println("Unknow:" + ByteUtil.getString(buffer));
        System.out.println("TribleName:" + ByteUtil.getString(buffer));
        System.out.println("Unknow:" + ByteUtil.getString(buffer));
        System.out.println("HairColor:" + ByteUtil.getString(buffer));
        System.out.println("Unknow:" + ByteUtil.getString(buffer));
        System.out.println("BodyName:" + ByteUtil.getString(buffer));
        System.out.println("Aligment:" + buffer.getInt());
        System.out.println("UnknowByte:" + buffer.get());
        System.out.println("UnknowByte:" + buffer.get());
        System.out.println("UnknowByte:" + buffer.get());
        System.out.println("CastleName:" + ByteUtil.getString(buffer));
        System.out.println("Int:" + buffer.getInt());
        System.out.println("等级:" + buffer.getInt());
        System.out.println("Long:" + buffer.getLong());
        System.out.println("当前经验:" + buffer.getLong());
        System.out.println("升级所需经验:" + buffer.getLong());
        System.out.println("HP:" + buffer.getInt());
        System.out.println("MAXHP:" + buffer.getInt());
        System.out.println("MP:" + buffer.getInt());
        System.out.println("MAXMP:" + buffer.getInt());
        System.out.println("Strength:" + buffer.getInt());
        System.out.println("敏捷:" + buffer.getInt());
        System.out.println("精神:" + buffer.getInt());
        System.out.println("白魔法:" + buffer.getInt());
        System.out.println("红魔法:" + buffer.getInt());
        System.out.println("黄魔法:" + buffer.getInt());
        System.out.println("蓝魔法:" + buffer.getInt());
        System.out.println("黑魔法:" + buffer.getInt());
        System.out.println("剩余点数:" + buffer.getInt());
        System.out.println("Leni:" + buffer.getLong());
        String wornItemsCase = "";
        for (int i = 0; i < 0x19; i++) {
            byte hasEquip = buffer.get();
            if (hasEquip == 1) {
                WornItem(buffer);
                wornItemsCase += "1";
            }
            wornItemsCase += "0";
        }
        //中间有6个多余的0
        System.out.println(wornItemsCase);
        buffer.position(buffer.position() + 7);
        System.out.println(">>>>>>>===============Inventory=================<<<<<<<<<");
        TransInventry(buffer);
        //技能
        System.out.println(">>>>>>>===============QuickKey=================<<<<<<<<<");
        QuickKey(buffer);
        //技能
        System.out.println(">>>>>>>===============SkillBook=================<<<<<<<<<");
        SkillBook(buffer);
        //人物属性
        System.out.println("属性Float:" + buffer.getFloat());
        System.out.println("移动速度:" + buffer.getFloat());
        System.out.println("属性Float:" + buffer.getFloat());
        System.out.println("属性Int:" + buffer.getInt());
        System.out.println("属性Int:" + buffer.getInt());
        System.out.println("属性Int:" + buffer.getInt());
        System.out.println("属性Int:" + buffer.getInt());
        //高级技能技能
        System.out.println(">>>>>>>===============SecondSkillBook=================<<<<<<<<<");
        SecondSkillBook(buffer);
        //人物属性
        System.out.println("属性Byte:" + buffer.get());
        System.out.println("属性Bool:" + buffer.get());
        System.out.println("属性String:" + ByteUtil.getString(buffer));
        System.out.println("属性String:" + ByteUtil.getString(buffer));
        System.out.println("属性Int:" + buffer.getInt());
        System.out.println("属性Int:" + buffer.getInt());
        System.out.println("属性Bool:" + buffer.get());
        System.out.println("属性Byte:" + buffer.get());
    }

    public void TransInventry(ByteBuffer buffer) throws UnsupportedEncodingException {
        System.out.println("InventryID:" + buffer.getInt());
        int goodsCount = buffer.getInt();
        System.out.println("NumberOfGoods:" + goodsCount);
        for (int i = 0; i < goodsCount; i++) {
            System.out.println("Position:(X:" + buffer.getInt() + ",Y:" + buffer.getInt() + ")");
            WornItem(buffer);
        }
    }


    public void OutString(String type, Object msg) {
        System.out.println(type + ":" + msg);
    }

    @Test
    public void TestWrapWornItem() throws UnsupportedEncodingException {
        ByteBuffer buffer = ByteBuffer.wrap(WornItem1.array);
        WornItem(buffer);
        //WornItem(WornItem2.array);
        //WornItem(WornItem3.array);
    }

    public void judgeMentType(byte type,ByteBuffer buffer) throws UnsupportedEncodingException {
        switch (type) {
            case 5: {
                System.out.println("防御率:" + buffer.getFloat());
                System.out.println("防御力:" + ByteUtil.getString(buffer));
                break;
            }
            case 4: {
                System.out.println("Float:" + buffer.getFloat());
                System.out.println("Int:" + buffer.getInt());
                System.out.println("Float:" + buffer.getFloat());
                for (int j = 0; j < 4; j++) {
                    System.out.println("Int:" + buffer.getInt());
                }
                int count = 6;
                do {
                    System.out.println("Int:" + buffer.getInt());
                    System.out.println("Int:" + buffer.getInt());
                    count--;
                } while (count >= 0);
                break;
            }
            case 6: {
                System.out.println("Float:" + buffer.getFloat());
                System.out.println("Float:" + buffer.getFloat());
                break;
            }
            case 7: {
                System.out.println("传声筒频道:" + ByteUtil.getString(buffer));
                break;
            }
            case 2: {
                System.out.println("Int:" + buffer.getInt());
                System.out.println("Int:" + buffer.getInt());
                System.out.println("Int:" + buffer.getInt());
                break;
            }
            case 25:{
                System.out.println("Float:"+buffer.getFloat());
                System.out.println("String:"+ByteUtil.getString(buffer));
                for (int j = 0; j < 7; j++) {
                    System.out.println("Int:" + buffer.getInt());
                }
                break;
            }
            default:
                break;
        }
    }

    public void WornItem(ByteBuffer buffer) throws UnsupportedEncodingException {
        System.out.println("====================================================");
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println("ModelName:" + ByteUtil.getString(buffer));
        System.out.println("物品等级:" + buffer.getInt());
        byte raceType = buffer.get();
        System.out.println("职业:" + raceType);
        System.out.println("AttachMentModelName:" + ByteUtil.getString(buffer));
        System.out.println("AttatchMentShowName:" + ByteUtil.getString(buffer));
        System.out.println("ShowName:" + ByteUtil.getString(buffer));
        System.out.println("物品说明:" + ByteUtil.getString(buffer));
        System.out.println("Int:" + buffer.getInt());
        System.out.println("Width:" + buffer.getInt());
        System.out.println("Height:" + buffer.getInt());
        System.out.println("物品类型名:" + ByteUtil.getString(buffer));

        System.out.println("物品类型ID:" + buffer.getInt());
        System.out.println("Int:" + buffer.getInt());
        System.out.println("耐久:" + buffer.getFloat() + "/" + buffer.getFloat());
        byte shouldBeIntRead = buffer.get();
        if (shouldBeIntRead != 0) {
            System.out.println("个数:" + buffer.getInt());
        }
        System.out.println("卖出价格:" + buffer.getLong());
        System.out.println("修理价格:" + buffer.getLong());
        buffer.position(buffer.position() + 9);
        int intVla = buffer.getInt();
        if (intVla != 0) {
            System.out.println("SpecialInt:" + intVla);
        }
        byte type = buffer.get();
        System.out.println("GearType>>>>>>>>>:" + type);
        judgeMentType(type,buffer);
        byte boolVal = buffer.get();
        if (boolVal != 0) {
            System.out.println("Int1:" + buffer.getInt());
            System.out.println("Int2:" + buffer.getInt());
            System.out.println("Int3:" + buffer.getInt());
            byte needs = buffer.get();
            for (int j = 0; j < needs; j++) {
                System.out.println("需求:" + buffer.get() + "=" + buffer.getInt());
            }
            int bMultAttr = buffer.get();
            if (bMultAttr != 0) {
                System.out.println("Int5:" + buffer.getInt());
                System.out.println("AttrEffect5:" + ByteUtil.getString(buffer));
            }
        }

        int intVal2 = buffer.getInt();
        if (intVal2 != 0) {
            System.out.println("SingleEffect:" + ByteUtil.getString(buffer));
        }
        byte attrs = buffer.get();
        for (int j = 0; j < attrs; j++) {
            System.out.println("AttrEffect" + j + ":" + ByteUtil.getString(buffer));
            System.out.println("AttrEffectName:" + j + ":" + ByteUtil.getString(buffer));
        }
    }

    public String stringToHexString(String strPart) {
        String hexString = "";
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch).toUpperCase();
            hexString = hexString  + strHex + " ";
        }
        return hexString;
    }

    public void QuickKey(ByteBuffer buffer) throws UnsupportedEncodingException {
        System.out.println("蓝色技能:" + ByteUtil.getString(buffer));
        System.out.println("红色色技能:" + ByteUtil.getString(buffer));
        System.out.println("绿法技能:" + ByteUtil.getString(buffer));
        for (int i = 0; i < 36; i++) {
            byte key = buffer.get();
            if (key > 0) {
                System.out.println("物品栏" + key + ":" + ByteUtil.getString(buffer));
            }
        }
    }

    //读取技能
    public void SkillBook(ByteBuffer buffer) throws UnsupportedEncodingException {
        int skillTypeMount = buffer.get();
        System.out.println("基础技能种类数量:" + skillTypeMount);
        for (int i = 0; i < skillTypeMount; i++) {
            System.out.println("==========================================================");
            System.out.println("技能种类名字:" + ByteUtil.getString(buffer));
            int skillMount = buffer.getInt();
            System.out.println("技能数量:" + skillMount);
            for (int j = 0; j < skillMount; j++) {
                System.out.println("技能名字:" + ByteUtil.getString(buffer));
                System.out.println("技能描述:" + ByteUtil.getString(buffer));
                System.out.println("技能描述:" + ByteUtil.getString(buffer));
                System.out.println("是否需要技能点数:" + (buffer.get() != 0 ? "Yes" : "No"));
                System.out.println("技能耗蓝:" + buffer.getInt());
                byte needle = buffer.get();
                String skillNeed = "技能需要点数:";
                for (int k = 0; k < needle; k++) {
                    skillNeed += buffer.get() + "->" + buffer.getInt() + "  ";
                }
                System.out.println(skillNeed);
                byte effect = buffer.get();
                String effectNeed = "前置技能:";
                for (int k = 0; k < effect; k++) {
                    effectNeed += ByteUtil.getString(buffer) + "->" + buffer.getInt() + "级  ";
                }
                System.out.println(effectNeed);
            }
            System.out.println("==========================================================");
        }
        int learnedSkill = buffer.getInt();
        System.out.println("学会的技能数量:" + learnedSkill);
        for (int z = 0; z < learnedSkill; z++) {
            System.out.println("学会的技能名:" + ByteUtil.getString(buffer) + "，职业:" + ByteUtil.getString(buffer));
            System.out.println("Byte:" + buffer.get());
            System.out.println("技能等级:" + buffer.getInt());
            byte b0 = buffer.get();
            if (b0 != 0) {
                System.out.println("Byte:" + buffer.get());
                System.out.println("Int:" + buffer.getInt());
            }
            byte b = buffer.get();
            if (b != 0) {
                System.out.println("Float:" + buffer.getFloat());
            }
            System.out.println("Int:" + buffer.getInt());
            System.out.println("Int:" + buffer.getInt());
            System.out.println("Int:" + buffer.getInt());
            System.out.println("Int:" + buffer.getInt());
            System.out.println("Int:" + buffer.getInt());
        }
    }

    //SecondSkillBook
    public void SecondSkillBook(ByteBuffer buffer) throws UnsupportedEncodingException {
        int SecondSkillNumber = buffer.getInt();
        for (int i = 0; i < SecondSkillNumber; i++) {
            System.out.println("SeconkSkillBookName:" + ByteUtil.getString(buffer));
            System.out.println("SeconkSkillBookLocalizeName:" + ByteUtil.getString(buffer));
            System.out.println("SeconkSkillDescription:" + ByteUtil.getString(buffer));
            System.out.println("Int:" + buffer.getInt());
            System.out.println("Int:" + buffer.getInt());
            System.out.println("Int:" + buffer.getInt());
            System.out.println("Int:" + buffer.getInt());
        }
    }

    @Test
    public void TestTransString() throws UnsupportedEncodingException {
        String text = "LifeSp";
        byte[] arrsy = text.getBytes("GB2312");
        System.out.println(Arrays.toString(arrsy));
        System.out.println(stringToHexString(text));
    }
}

