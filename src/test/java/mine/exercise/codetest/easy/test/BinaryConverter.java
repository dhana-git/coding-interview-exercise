package mine.exercise.codetest.easy.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class BinaryConverter {

	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionOnNegativeValue() {
		getBinary(-1);
	}

	@Test
	public void assertBinValueFor0() {
		assertEquals("0", getBinary(0));
	}

	@Test
	public void assertBinValueFor1() {
		assertEquals("1", getBinary(1));
	}

	@Test
	public void assertBinValueFor24() {
		assertEquals("11000", getBinary(24));
	}

	@Test
	public void assertBinValueFor1024() {
		assertEquals("10000000000", getBinary(1024));
	}

	private static String getBinary(int num) {
		if (num < 0) {
			throw new IllegalArgumentException("Non-negative base-10 integer is expected.");
		}
		return getBinIteratively(num);
	}

	private static String getBinRecursively(int num) {
		if (num <= 1) {
			return Integer.toString(num);
		}
		return getBinRecursively(num / 2) + num % 2;
	}

	private static String getBinIteratively(int num) {
		if (num <= 1) {
			return Integer.toString(num);
		}
		int[] binResult = new int[32];
		for (int i = binResult.length - 1; i >= 0; i--) {
			binResult[i] = -1;
		}
		int counter = 32;
		while (num > 0) {
			binResult[--counter] = num % 2;
			num = num / 2;
		}
		StringBuilder sb = new StringBuilder(binResult.length);
		for (int i = 0; i < binResult.length; i++) {
			if (binResult[i] == -1) {
				continue;
			}
			sb.append(binResult[i]);

		}
		return sb.toString();
	}
}
