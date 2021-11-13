package com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.param.ParamBean;

public class RequestCipher {

	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String ENCRYPT_KEY = "weblogEncrypKey1";
	private static final String INIT_VECTOR = "webloginiVector1";

	private final IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
	private final SecretKeySpec key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

	public String encode(ParamBean bean) {

		String enc = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonParam = mapper.writeValueAsString(bean);

			System.out.println(iv.getIV().length);
			System.out.println(key.getEncoded().length);

			Cipher cip = Cipher.getInstance(ALGORITHM);
			cip.init(Cipher.ENCRYPT_MODE, this.key, this.iv);
			byte[] byteToken = cip.doFinal(jsonParam.getBytes());
			enc = URLEncoder.encode(new String(Base64.getEncoder().encode(byteToken)), "UTF-8");

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | JsonProcessingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return enc;
	}

	public ParamBean decode(String param) {

		ParamBean bean = null;
		try {

			Cipher cip = Cipher.getInstance(ALGORITHM);
			cip.init(Cipher.DECRYPT_MODE, this.key, this.iv);
			byte[] byteToken = Base64.getDecoder().decode(param);
			String dec = new String(cip.doFinal(byteToken));

			ObjectMapper mapper = new ObjectMapper();
			bean = mapper.readValue(dec, ParamBean.class);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | JsonProcessingException e) {
			e.printStackTrace();
		}
		return bean;
	}

}
