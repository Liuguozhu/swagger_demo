package org.sidao.jdbc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtils {
	/** Encrypt algorithm */
	public final static String DES = "DES";

	public final static String MD5 = "MD5";

	public final static String SHA_1 = "SHA-1";

	/** Encrytp encoding */
	public final static String UTF8 = "UTF8";

	/**
	 * The key format: password-based encrypt with MD5 and DES.
	 * 
	 * @value
	 * @see javax.crypto.spec.PBEKeySpec
	 */
	public static final String PBE_WITH_MD5_AND_DES = "PBEWithMD5AndDES";

	public static final String HMAC_WITH_MD5 = "HmacMD5";

	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 8-byte Salt. <br>
	 * In general, the salt and the iteration count can be transmitted "in the
	 * clear," since their primary purpose is to prevent a person from using a
	 * dictionary attack, where they take a dictionary of words and pre-generate
	 * keys for those words. Adding salt and iteration count to the mix means
	 * that the attacker needs to wait until he/she gets those values before
	 * generating any keys.
	 * 
	 * @see http://forum.java.sun.com/thread.jspa?forumID=9&threadID=257536
	 * @see http
	 *      ://www.osborne.com/networking_comm/007213139X/007213139X_ch03.pdf
	 * 
	 */
	private static byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8,
			(byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };

	/** Number of interaction used to prepare the parameter to the ciphers. */
	private static final int INTERACTION = 19;

	/**
	 * That's the system backdoor. - IMPORTANT </b>: change these bytes before
	 * release the software to the server. If someone with server access knows
	 * about this passphrase, he or she can easly hack your passwords.
	 */
	private static String passphrase = "s3nh@s3cr3t@";

	/**
	 * <ul>
	 * <li>Create the key</li>
	 * <li>Prepare the parameter to the ciphers</li>
	 * <li>Create the ciphers</li>
	 * <li>Encode the string into bytes using utf-8</li>
	 * <li>Encrypt</li>
	 * <li>Encode bytes to base64 to get a string</li>
	 * </ul>
	 * 
	 * @param secret
	 *            The password to be decrypted.
	 * @return a plain password.
	 * @throws Exception
	 *             a generic exception caused during the decrypt process.
	 */
	public static String encrypt(String plain) throws Exception {
		KeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(), salt,
				INTERACTION);
		SecretKey key = SecretKeyFactory.getInstance(PBE_WITH_MD5_AND_DES)
				.generateSecret(keySpec);
		Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
		AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
				INTERACTION);
		ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		byte[] utf8 = plain.getBytes(UTF8);
		byte[] enc = ecipher.doFinal(utf8);
		return new String(Base64.encode(enc));
	}

	/**
	 * <ul>
	 * <li>Create the key</li>
	 * <li>Decode base64 to get bytes of the secret word</li>
	 * <li>Prepare the parameter to the ciphers</li>
	 * <li>Decrypt</li>
	 * <li>Decode using utf-8</li>
	 * </ul>
	 * 
	 * @param secret
	 *            The password to be decrypted.
	 * @return a plain password.
	 * @throws Exception
	 *             a generic exception caused during the decrypt process.
	 */
	public static String decrypt(String secret) throws Exception {
		KeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(), salt,
				INTERACTION);
		SecretKey key = SecretKeyFactory.getInstance(PBE_WITH_MD5_AND_DES)
				.generateSecret(keySpec);
		byte[] dec = Base64.decode(secret.getBytes());
		Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
		AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
				INTERACTION);
		dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		byte[] utf8 = dcipher.doFinal(dec);
		return new String(utf8, UTF8);
	}

	public static String md5(String plain) throws Exception {
		return md5(plain, MD5);
	}

	public static String md5Hex(String data) {
		if (data == null) {
			throw new IllegalArgumentException("data must not be null");
		}

		byte[] bytes = digest("MD5", data);

		return toHexString(bytes);
	}

	public static String sha1Hex(String data) {
		if (data == null) {
			throw new IllegalArgumentException("data must not be null");
		}

		byte[] bytes = digest("SHA1", data);

		return toHexString(bytes);
	}

	private static String toHexString(byte[] bytes) {
		int l = bytes.length;

		char[] out = new char[l << 1];

		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & bytes[i]) >>> 4];
			out[j++] = DIGITS[0x0F & bytes[i]];
		}

		return new String(out);
	}

	private static byte[] digest(String algorithm, String data) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		return digest.digest(data.getBytes());
	}

	public static String md5(String plain, String algorithm) throws Exception {
		if (algorithm == null)
			algorithm = MD5;
		char[] charArray = plain.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		MessageDigest md5 = MessageDigest.getInstance(algorithm);
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuilder hexValue = null;
		if (MD5.equals(algorithm))
			hexValue = new StringBuilder(32);
		else if (SHA_1.equals(algorithm))
			hexValue = new StringBuilder(40);
		hexValue = new StringBuilder(40);
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = (md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}

	public static byte[] encryptByMd5(String plain) throws Exception {
		// get a key for the HmacMD5 algorithm
		byte[] key = { 'a', 's', 'g', 'c', 'k', 'e', 'y', 'g' };
		SecretKey secKey = new SecretKeySpec(key, HMAC_WITH_MD5);
		SecretKey MD5key = secKey;
		Mac mac = Mac.getInstance(HMAC_WITH_MD5);
		mac.init(MD5key);
		byte[] utf8 = plain.getBytes(UTF8);
		mac.update(utf8);
		return mac.doFinal();
	}

	public static void main(String[] args) throws Exception {
        System.err.println(CipherUtils.encrypt("root"));
		System.err.println(CipherUtils.encrypt("654321"));
//		System.err.println(CipherUtils.encrypt("chineseplatzz#2015^3z%3y*5x"));
//		 System.err.println(CipherUtils.decrypt("1wxjB2NUof0="));
//		 System.err.println(CipherUtils.md5("XZG9J=RMC"));
//        System.err.println(CipherUtils.encrypt("android143!@#."));
//        System.err.println(CipherUtils.encrypt("androiddb143!@#."));
	}
}
