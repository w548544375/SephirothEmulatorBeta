package LoginServer;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

/**
 * @author Wangweiguang
 * 
 */
public class RSAUtils {
	public static final String PUBLIC_KEY = "public";
	public static final String PRIVATE_KEY = "private";
	public static final int KEY_SIZE = 1024;
	public static final String KEY_ALGORITHM = "RSA";

	public static HashMap<String, Object> getKeys() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		KeyPairGenerator keyPair = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPair.initialize(KEY_SIZE);
		KeyPair key = keyPair.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) key.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) key.getPrivate();
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);
		return map;
	}

	/**
	 * 使用模和指数生成公匙
	 * 
	 * @param modulus
	 * @param privateExponent
	 * @return
	 */
	public static RSAPublicKey getPublicKey(String modulus,
			String privateExponent) throws Exception {
		BigInteger m = new BigInteger(modulus);
		BigInteger e = new BigInteger(privateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		RSAPublicKeySpec rsapublicKey = new RSAPublicKeySpec(m, e);
		return (RSAPublicKey) keyFactory.generatePublic(rsapublicKey);
	}

	public static RSAPrivateKey getPrivateKey(String modulus,
			String privateExponent) throws Exception {
		BigInteger m = new BigInteger(modulus);
		BigInteger e = new BigInteger(privateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(m, e);

		return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
	}

	public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey pubkey)
			throws Exception {
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, pubkey);
		return cipher.doFinal(data);
	}
public static byte[] decryptByPrivateKey(byte[] data,RSAPrivateKey priKey) throws Exception{
	Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
	cipher.init(Cipher.DECRYPT_MODE, priKey);
	return cipher.doFinal(data);
}
public static void printBytes(byte[] source){
	String str = "";
	String temp ="";
	 for(byte b:source){
		 temp = String.format("%02X,", b);
		 str+=temp;
	 }
	 System.out.println(str+"\r\n长度:"+source.length);
}
}
