package LoginServer.mix;

import java.io.UnsupportedEncodingException;

public class TEST {

	public TEST() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("测试 ".getBytes("gbk").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
