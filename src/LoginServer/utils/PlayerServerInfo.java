package LoginServer.utils;

/**
 * @author 王伟光
 *  放弃使用这个类
 */
public class PlayerServerInfo {
	
    public static final int ZERO =0;
    public static final int ONE = 1;
	public static final int MALE=1;
	public static final int FEMALE = 0;
	
	//角色姓名
	private String playName;
	//所属种族
	private String raceName;
	//男女
	private int isMale;
	//职业
	private String jobName;
	//标识最后登录时间
	private int lastLoginTimeSignal;
	//最后登录时间
	private int lastLoginTime;
	//标识1
	private byte signal_1;
	//空值1
	private int  unknow_1;
	//标识2
	private byte signal_2;
	//空值2
	private byte unknow_2;
	//头发样式
	private String hair;
	
	private int signal_3;
	//身体名字 都为HunterSuit
	private String bodyName;
	
	private int signal_4;
	//城堡名字Ladianes Veros
	private String castleName;
	//等级
	private int level;
	//level 2个为0的int
	//signal = 5A
	private int signal;
	//3个为0的int
	//武器标识 1
	byte equipSignal; 
  //没有装备就是就是37个0byte
}
