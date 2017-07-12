package com.web.soulyogaadmin.util;

import java.io.UnsupportedEncodingException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;






public class Des {
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
	private static final String key = "0002000200020002";
	// DES,DESede,Blowfish
	// src为被加密的数据缓冲区（源）
	private static final Logger LOGGER=Logger.getLogger(Des.class);
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte,
					Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte,
					Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + "";
		}
		return hs.toUpperCase();
	}

	// 16 进制 转 2 进制
	public static byte[] hex2byte(String hex)
			throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	// 加密
	public static String Encrypt(String str, byte[] key) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] encrypt = encryptMode(key, str.getBytes());
		return byte2hex(encrypt);
	}

	// 加密
	public static byte[] EncryptRetByte(byte[] src, byte[] key) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] encrypt = encryptMode(key, src);
		return encrypt;
	}

	// 解密
	public static String Decrypt(String str, byte[] key) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] decrypt = decryptMode(key, hex2byte(str));
		return new String(decrypt);
	}

	/*
	      * 根据字符串生成密钥字节数组 
	      * @param keyStr 密钥字符串
	      * @return 
	      * @throws UnsupportedEncodingException
	      */
	     public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException{
	         byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
	         byte[] temp = keyStr.getBytes("UTF-8");    //将字符串转成字节数组
	         
	         /*
	          * 执行数组拷贝
	          * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
	          */
	         if(key.length > temp.length){
	             //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
	             System.arraycopy(temp, 0, key, 0, temp.length);
	         }else{
	             //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
	             System.arraycopy(temp, 0, key, 0, key.length);
	         }
	         return key;
	     } 
	     
	     public static String desPassword(String keyStorePassword ){
			String passwordVal ="";
			try {
				Des des = new Des();
				//System.out.println("加密的連接密碼爲."+rkPassword);
				if (UtilValidate.isNotEmpty(keyStorePassword)) {
					passwordVal = des.Decrypt(keyStorePassword,
							des.build3DesKey(key));			
				}
				
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			return passwordVal;
		}


	     
	public static void main(String arg[]) throws UnsupportedEncodingException {
		String str = "EA96264D1AED9173554D2C01DE9F8B21";// 要经过解密的信息
		String strKey = "0002000200020002";
		String passwordVal = Decrypt(str, build3DesKey(strKey));
		System.out.println("解密后的信息     " + passwordVal);
		
//		String temp = "Admin";			//1B64EE8E1AA39891
		String temp = "123456";			//C78BC159945F6CAE
		temp = "123456";			//5F946AB62EC2736B
		String tempValue = Encrypt(temp, build3DesKey(strKey));// 加密
		System.out.println("加密后的信息     " + tempValue);
		
		
	}
	     
}
