package com.wing.mainApp.util;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import org.apache.commons.codec.binary.Base64;

public class ase256 {

	private static volatile ase256 INSTANCE;
   
	final static String secretKey   = "123456789012345678901234567A9012"; //32bit
	static String IV                = ""; //16bit

	public static ase256 getInstance(){
		if(INSTANCE==null){
			synchronized(ase256.class){
				if(INSTANCE==null)
					INSTANCE=new ase256();
			}
		}
		return INSTANCE;
	}

	private ase256(){
		IV = secretKey.substring(0,16);
	}

	//암호화
	public static String AES_Encode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		byte[] keyData = secretKey.getBytes();

		IV = secretKey.substring(0,16);
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));

		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));

		return enStr;
	}

	//복호화
	public static String AES_Decode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		if (str == null || str.isEmpty())
			return ""; 
		 
		
		
		IV = secretKey.substring(0,16);
		byte[] keyData = secretKey.getBytes();
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));

		byte[] byteStr = Base64.decodeBase64(str.getBytes());

		return new String(c.doFinal(byteStr),"UTF-8");
	}

}
