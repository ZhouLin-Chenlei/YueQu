package com.community.yuequ.util;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	public static String decryptByCBC(String input, byte[] key, byte[] iv) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

			byte[] outText = cipher.doFinal(Base64.decode(input, Base64.DEFAULT));
			return new String(outText);
		} catch (Exception e) {
			return "";
		}
	}

	public static String encodeByCBC(String input, byte[] key, byte[] iv) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			byte[] outText = cipher.doFinal(input.getBytes("utf-8"));
			byte[] encode = Base64.encode(outText, Base64.DEFAULT);
			return new String(encode);

		} catch (Exception e) {
			return "";
		}
	}
	
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	public static String encode(String encodeStr){
		StringBuilder builder = new StringBuilder();
		String pass = getRandomString(16);
		String iv = getRandomString(16);
		String encodeResult = "";
		try {
			encodeResult = encodeByCBC(encodeStr,pass.getBytes("utf-8"),iv.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		builder.append(pass);
		builder.append(iv);
		builder.append(encodeResult);
		return builder.toString();
	}
	
	public static String decrypt(String encodeString){
		String pass = encodeString.substring(0, 16);
		String iv = encodeString.substring(16, 32);
		String encodeStr = encodeString.substring(32);
		String decodeResult = "";
		try {
			decodeResult = decryptByCBC(encodeStr,pass.getBytes("utf-8"),iv.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		return decodeResult;
	}

	public static void main(String[] args) {
		
		String line = "我爱中国1243#@@@!!";
		String encodeResult = encode(line);
		System.out.println("encodeResult:" + encodeResult);
		
		String decodeResult = "";
		decodeResult = decrypt(encodeResult);
		System.out.println("decodeResult:" + decodeResult);
	}
}
