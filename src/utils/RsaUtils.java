package utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class RsaUtils {
	public static final String CHARSET = "UTF-8";//八位元可变长度字符编码
	public static final String RSA_ALGORITHM = "RSA";
	
	//用于封装随机产生的公钥与私钥
	public static Map<Integer,String> keyMap = new HashMap<Integer,String>();
	
	/**
	 * 随机生成密钥对
	 * @throws NoSuchAlgorithmException
	 */
	public static void genKeyPair(int id) throws NoSuchAlgorithmException{
		//为RSA算法创建一个KeyPairGenerator对象
		//KeyPairGenerator类用于生成公钥和私钥，基于RSA算法生成对象
		KeyPairGenerator kPG = KeyPairGenerator.getInstance(RSA_ALGORITHM);
		//初始化密钥对生成器，密钥大小为96-1024位
		kPG.initialize(512, new SecureRandom());//512-bit long，即512是最小的了
		//生成一个密钥对，保存在keyPair中
		KeyPair keyPair = kPG.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		//得到私钥字符串
		String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
		//将公钥和私钥保存到Map
		keyMap.put(2*id-2, publicKeyString);		//2*id-2-公钥
		keyMap.put(2*id-1, privateKeyString);	//2*id-1-私钥
	}
	/**
	 * RSA公钥加密
	 * @param str	加密字符串
	 * @param publicKey		公钥
	 * @return		密文
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String encrypt(String str,String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		//base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA_ALGORITHM).generatePublic(new X509EncodedKeySpec(decoded));
		//RSA加密
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(CHARSET)));
		return outStr;
	}
	/**
	 * RSA私钥解密
	 * @param str		加密字符串
	 * @param privateKey		私钥
	 * @return		铭文
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String decrypt(String str,String privateKey) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		//64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes(CHARSET));
		//base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);
		System.out.println(privateKey);
		RSAPrivateKey priKey=null;
		System.out.println(decoded);
		if(decoded!=null){
			priKey = (RSAPrivateKey)KeyFactory.getInstance(RSA_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decoded));
			//RSA解密
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			String outStr = new String(cipher.doFinal(inputByte));
			return outStr;
		}else{
			return "?";
		}
		
	}
}
