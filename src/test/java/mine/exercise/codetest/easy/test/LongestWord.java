package mine.exercise.codetest.easy.test;

import java.util.Scanner;

public class LongestWord {

	public static String longestWord(String sen) {
		String[] inputArray = sen.replaceAll("[!@#$%^&*(]", "").split(" ");
		String longestWord = "";
		for (int i = 0; i < inputArray.length; i++) {
			longestWord = longestWord.length() >= inputArray[i].length() ? longestWord : inputArray[i];
		}

		return longestWord;

	}

	public static void main(String[] args) {
		// keep this function call here
		Scanner s = new Scanner(System.in);
		// System.out.print(LongestWord(s.nextLine()));
		System.out.print(longestWord("came to office worlds1"));
	}
}
