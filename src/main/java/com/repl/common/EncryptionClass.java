package com.repl.common;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

public class EncryptionClass extends ReplAction {
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'i', 's',
			'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
//	private static final byte[] keyValue = new byte[] { 'U', 'n', 'm', 'a',
//			't', 'c', 'h', 'e', 'd', 'S', 'o', 'l', 'u', 't', 'i', 'p' };

	public static String encrypt(String valueToEnc) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encValue);
		return encryptedValue;
	}

	public static String decrypt(String encryptedValue) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		// SecretKeyFactory keyFactory =
		// SecretKeyFactory.getInstance(ALGORITHM);
		// key = keyFactory.generateSecret(new DESKeySpec(keyValue));
		return key;
	}

        public static void main(String args[])
        {
        try {
            System.out.println(EncryptionClass.encrypt("Pankaj1$*"));
           // System.out.println("sgn "+EncryptionClass.decrypt("bU6q59s/3    Goxs8AXAP7jGQ=="));
        } catch (Exception ex) {
            Logger.getLogger(EncryptionClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
}
