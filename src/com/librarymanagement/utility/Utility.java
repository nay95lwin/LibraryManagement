package com.librarymanagement.utility;

import java.security.Key;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Utility {

	public static Scanner scan = new Scanner(System.in);
	private static final String KEY = "!@#12345$%^67890";

	public static String encryptString(String txt) {
		String encryptedString = null;
		Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");

		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(txt.getBytes());
			Base64.Encoder encoder = Base64.getEncoder();
			encryptedString = encoder.encodeToString(encrypted);
		} catch (Exception e) {
			System.out.println("Could not encrypt.");
		}

		return encryptedString;
	}

	public static String decryptString(String encryptedString) {
		String decrypted = null;
		Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");

		try {
			Cipher cipher = Cipher.getInstance("AES");
			Base64.Decoder decoder = Base64.getDecoder();
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(decoder.decode(encryptedString)));
		} catch (Exception e) {
			System.out.println("Could not decrypt.");
		}

		return decrypted;
	}

	public static void exit() {
		System.out.println("Exiting library management system....");
		System.exit(0);
	}

	public static String getUserInput() {
		System.out.print("Enter the input: ");
		return scan.nextLine();
	}

	public static String getUserInput(String inputType) {
		System.out.print("Enter the " + inputType + ": ");
		return scan.nextLine();
	}

}
