package LoginServer.mix.Items;

public abstract class SephirothItem implements Item {
	    protected String meshName;
	    protected int itemLv;
	    protected byte showType; //属性的显示方式
	    protected String icon;//iconname
	    protected String name;
	    protected String showname;
	    protected String subdescription;
	    protected int  zero;
	    protected int widht; 
	    protected int height; 
	    String category; //Use,DfnWpn,Application,Etc
	    protected int obj_9c;
	    protected int obj_a0;
	    protected double Durability;
	    protected double maxDurability;
	    protected byte obj_ac;
	    protected long str_1;
	    protected long str_2;
	    protected int   obj;//通常为0
	    
}
