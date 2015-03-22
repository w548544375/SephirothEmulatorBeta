package LoginServer.mix.Items;

/**
 * @author 王伟光
 *  角色的装备在Lobby-menu的存储格式
 */
public class WornItemLobbyInfo {
	private String meshName; 
	private int lv;
	private byte showType; //为0就直接读取2个string，不为0就要循环读取
    private	String className;  //
	private String showName; //显示的名字
	private int  unknow1;
	private byte unknow2;
	private int equipPlace;
	private int start;//起始位置
	private int width;//占用的宽度
	private int height;//占用的高度
	private int desNum;//可能是描述后面数量的，为0就跳过读取后面2个string；
	private byte unknow3; //1
	private byte unknow4; //1
	private byte unknow5;//这个为0就会跳过读取
	private int unknow6;
}
