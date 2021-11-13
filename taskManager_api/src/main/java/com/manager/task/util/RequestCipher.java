package com.manager.task.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RequestCipher {

	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String ENCRYPT_KEY = "taskmngEncrypKey";
	private static final String INIT_VECTOR = "taskmngiVector01";

	private static final IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
	private static final SecretKeySpec key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

	public static String encode(String pass) {

		String enc = null;
		try {
			System.out.println(iv.getIV().length);
			System.out.println(key.getEncoded().length);

			Cipher cip = Cipher.getInstance(ALGORITHM);
			cip.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] byteToken = cip.doFinal(pass.getBytes());
			enc = new String(Base64.getEncoder().encode(byteToken));

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		return enc;
	}

	public static String decode(String enc) {

		String pass = null;
		try {

			Cipher cip = Cipher.getInstance(ALGORITHM);
			cip.init(Cipher.DECRYPT_MODE, key, iv);
			pass = new String(cip.doFinal(Base64.getDecoder().decode(enc.getBytes())));

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		return pass;
	}

}
