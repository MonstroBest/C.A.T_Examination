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
	public static final String CHARSET = "UTF-8";//��λԪ�ɱ䳤���ַ�����
	public static final String RSA_ALGORITHM = "RSA";
	
	//���ڷ�װ��������Ĺ�Կ��˽Կ
	public static Map<Integer,String> keyMap = new HashMap<Integer,String>();
	
	/**
	 * ���������Կ��
	 * @throws NoSuchAlgorithmException
	 */
	public static void genKeyPair(int id) throws NoSuchAlgorithmException{
		//ΪRSA�㷨����һ��KeyPairGenerator����
		//KeyPairGenerator���������ɹ�Կ��˽Կ������RSA�㷨���ɶ���
		KeyPairGenerator kPG = KeyPairGenerator.getInstance(RSA_ALGORITHM);
		//��ʼ����Կ������������Կ��СΪ96-1024λ
		kPG.initialize(512, new SecureRandom());//512-bit long����512����С����
		//����һ����Կ�ԣ�������keyPair��
		KeyPair keyPair = kPG.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		//�õ�˽Կ�ַ���
		String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
		//����Կ��˽Կ���浽Map
		keyMap.put(2*id-2, publicKeyString);		//2*id-2-��Կ
		keyMap.put(2*id-1, privateKeyString);	//2*id-1-˽Կ
	}
	/**
	 * RSA��Կ����
	 * @param str	�����ַ���
	 * @param publicKey		��Կ
	 * @return		����
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String encrypt(String str,String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		//base64����Ĺ�Կ
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA_ALGORITHM).generatePublic(new X509EncodedKeySpec(decoded));
		//RSA����
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(CHARSET)));
		return outStr;
	}
	/**
	 * RSA˽Կ����
	 * @param str		�����ַ���
	 * @param privateKey		˽Կ
	 * @return		����
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String decrypt(String str,String privateKey) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		//64λ������ܺ���ַ���
		byte[] inputByte = Base64.decodeBase64(str.getBytes(CHARSET));
		//base64�����˽Կ
		byte[] decoded = Base64.decodeBase64(privateKey);
		System.out.println(privateKey);
		RSAPrivateKey priKey=null;
		System.out.println(decoded);
		if(decoded!=null){
			priKey = (RSAPrivateKey)KeyFactory.getInstance(RSA_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decoded));
			//RSA����
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			String outStr = new String(cipher.doFinal(inputByte));
			return outStr;
		}else{
			return "?";
		}
		
	}
}
