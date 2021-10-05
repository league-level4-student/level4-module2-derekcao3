package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length()>s2.length()) {
			return s1;
		}
		return s2;
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		if(s.contains("underscores")) {
			return s.replaceAll("\\s", "_");
		}
		return s;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		String s1a = s1.trim();
		String s2a = s2.trim();
		String s3a = s3.trim();
		String s1LastName = s1a.substring(s1a.indexOf(" ") + 1, s1a.length());
		String s2LastName = s2a.substring(s2a.indexOf(" ") + 1, s2a.length());
		String s3LastName = s3a.substring(s3a.indexOf(" ") + 1, s3a.length());
		
		if(s1LastName.compareTo(s2LastName)<0 && s1LastName.compareTo(s3LastName)<0) {
			return s1a;
		}
		else if(s1LastName.compareTo(s2LastName)>0 && s2LastName.compareTo(s3LastName)<0) {
			return s2a;
		}
	
		return s3a;
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		String number = "";
		int add = 0;
		for (int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				number = s.charAt(i) + "";
				add += Integer.parseInt(number);
			}
		}
		return add;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int counter = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)==' ') {
				s.replaceAll(" ", "");
			}
		}
		
		for (int i = 0; i < s.length()-substring.length()+1; i++) {
		String sub = s.substring(i, i+substring.length());	
		if(sub.equals(substring)) {
			counter++;
		}
		}
		return counter;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte)key);
		
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte)key);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
			int counter = 0;
			String[] words = s.split(" ");

			
			for (String i : words) {
				if(i.endsWith(substring)) {
					counter++;
				}
			}
			return counter;
		
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		return s.lastIndexOf(substring) - (s.indexOf(substring) + substring.length());

	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		s.trim();
		s = s.replaceAll("[\\W_]", "");
		s = s.toLowerCase();
		String sub1 = s.substring(0, s.length()/2+1);
		String sub2 = s.substring(s.length()/2,s.length());
		String half1 = s.substring(0, s.length()/2-1);
		String half2 = s.substring(s.length()/2+1,s.length());
		String reverse = "";
		for (int i = sub2.length()-1; i >=0; i--) {
			reverse = reverse + sub2.charAt(i);
		}
		//System.out.println(sub1);
		//System.out.println(reverse);
		//System.out.println(s);
		//System.out.println(sub1 + " " + reverse);
		//System.out.println(half1 + " " + half2);
		if((s.length())%2==1) {
			if(sub1.equals(reverse)) {
				return true;
			}
			
		}
		else {
			if(half1.equals(half2)) {
				return true;
			}
		}
		
		return false;	
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
