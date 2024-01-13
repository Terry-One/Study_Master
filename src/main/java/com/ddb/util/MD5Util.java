package com.ddb.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
*@author ÊÆµÈÅìÂç?
*@date 2020Âπ?4Êú?12Êó•‰∏äÂç?11:44:09
*
*/
public class MD5Util {
	
	public static String getMD5Value(String value){
		
		if(value==null){
			
			return null;
		}
		
		try {
			MessageDigest instance = MessageDigest.getInstance("MD5");
			
			byte[] md5ValueByTeArray=instance.digest(value.getBytes());
			
			BigInteger bigInteger=new BigInteger(1,md5ValueByTeArray);
			
			return bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
			return value;
		}
		
	}
	
	/*public static void main(String[] args) {
		String md5Value = MD5Util.getMD5Value("1234");
		System.out.println(md5Value);
	}*/

}
