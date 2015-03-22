package LoginServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.Date;

public class ReadPackage {
			private  String file=null;
			private RandomAccessFile raf;
		public ReadPackage(String file){
		  this.file = file;
		}
		public byte[] readRecv(int index) throws IOException{
			//File packageWords = new File("Package.txt");
			try {
				raf=new RandomAccessFile(file, "r");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] result;
			int dex =0;
			String resultstr="";
			int length=0;
			do{
			String[] title=raf.readLine().split(":");
			//	System.out.println(title[0]);
			if("Recv".equals(title[0])){
				dex++;
				if(dex==index){
					length=Integer.parseInt(title[1]);
					//System.out.println(dex);
				
					result= new byte[length];
				do{
					String temp = raf.readLine();
					if("".equals(temp)){
						temp=raf.readLine();
					}
					 resultstr+=temp;
					}while(resultstr.split(",").length<length);
					//System.out.println(resultstr.length());
				result=str2byte(resultstr,length);
				raf.close();
				return result;
				}
				//System.out.println(length);
			
			}
			}while(raf.getFilePointer()<raf.length());
			return null;
		}
		
		private static byte[] str2byte(String sb,int length){
			//System.out.println(sb);
			String[] strs=sb.split(",");
			//System.out.println(strs.length);
			 byte[] b=new byte[length];
			for(int i =0;i <strs.length-1;i++){
				String sub=strs[i].substring(2);
				int number = Integer.parseInt(sub, 16);
				b[i] = (byte) number;
				//System.out.print(b[i]+",");
			}
			return b;
			
		}
		public int getTotalRecv(){
			int recvNum =0;//recv个数计数
			try {
				raf=new RandomAccessFile(file, "r");
				String title;
				while(raf.getFilePointer()<raf.length()){
					
				String[] recv = raf.readLine().split(":");//获取recv头
				if("Recv".equals(recv[0])){
					recvNum=recvNum+1;
				}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return recvNum;
		}
		public static void recvMsg(InputStream in){
			System.out.println("-----Start---------");
		try {
			
			int temp =0;
			int i =1;
			while((temp=in.read())!=-1){
				if(i%16 == 0)
					System.out.println();
				System.out.print(Integer.toHexString(temp).toUpperCase()+",");
				i++;
			}
			System.out.println("-------end-----------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public  int[] readPosition(){
		int[] res = new int[3];
		try {
			raf=new RandomAccessFile(file, "r");
			String pos = this.raf.readLine();
			if(	pos == null)
				return null;
			String[] xyz = pos.split(",");
			for(int i=0;i<res.length;i++){
				res[i] = (int)Double.parseDouble(xyz[i]);
			}
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
