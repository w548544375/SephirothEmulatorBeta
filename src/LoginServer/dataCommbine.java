package LoginServer;

public class dataCommbine {

	public dataCommbine() {
		// TODO Auto-generated constructor stub
	}
	public static byte[] commbineData(byte[] dataBig,byte[] dataSmall){
		int lengthB = dataBig.length;
		int lengthS = dataSmall.length;
		int tempLength = lengthB -lengthS;
		for(int i =0;i<lengthS;i++){
			dataBig[tempLength+i] = dataSmall[i];
		}
		return dataBig;
	}
	public static void main(String[] args){
		byte[] b={1,2,3,4};
		byte[] c={0,8};
		byte[] d =commbineData(b,c);
		for(int i=0;i<d.length;i++)
			System.out.println(d[i]);
	}
}
