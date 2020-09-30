/**
 * 
 */
package com.url.shortner.shorturl.service;

import org.springframework.stereotype.Service;

/**
 * @author HDMI
 *
 */
@Service
public class ConvertorUrlFactory {

	/**
	 * 
	 */
	public ConvertorUrlFactory() {
		// TODO Auto-generated constructor stub
	}

	private String testcharSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private char[] allowedCharacters = testcharSet.toCharArray();
	private int base = allowedCharacters.length;

	static int val(char c) {
		if (c >= '0' && c <= '9')
			return (int) c - '0';
		else
			return (int) c - 'A' + 10;
	}

	static long givenBasetoDeci(String input, int base) {
		int len = input.length();
		int power = 1;
		long decimalNumber = 0;
		for (int i = len - 1; i >= 0; i--) {
			if (val(input.charAt(i)) >= base) {
				System.out.println("Invalid Number");
				return -1;
			}
			decimalNumber += val(input.charAt(i)) * power;
			power = power * base;
		}
		return decimalNumber;

	}

	public static String baseConversion(String number, int sBase, int dBase) {
		return Integer.toString(Integer.parseInt(number, sBase), dBase);
	}

	public String encode(long input) {
		StringBuffer encodedString = new StringBuffer();

		if (input == 0) {
			return String.valueOf(allowedCharacters[0]);
		} else {
			for (; input > 0;) {
				encodedString.append(allowedCharacters[(int) (input % base)]);
				input = input / base;
			}

			return encodedString.reverse().toString();

		}
	}

	public long decode(String input) {
		char[] characters = input.toCharArray();
		int length = characters.length;
		long decoded = 0;
		int counter = 1;
		for (int i = 0; i < length; i++) {
			decoded += testcharSet.indexOf(characters[i]) * Math.pow(base, length - counter);
			counter++;
		}
		return decoded;
	}
}
