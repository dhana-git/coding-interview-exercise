package mine.exercise.codetest.easy.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinarySearch {

	private static String[] testData = new String[26 * 26];

	@BeforeClass
	public static void init() {
		int counter = 0;
		for (char i = 97; i <= 122; i++) {
			for (char j = 97; j <= 122; j++) {
				testData[counter++] = (i) + "" + (j);
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionOnInvalidata() {
		binarySearch(null, "abc");
	}

	@Test
	public void checkBestCase() {
		Assert.assertEquals(0, binarySearch(testData, "aa"));
	}

	@Test
	public void checkWorstCase() {
		Assert.assertEquals(675, binarySearch(testData, "zz"));
	}

	@Test
	public void checkAvgCase1() {
		Assert.assertEquals(338, binarySearch(testData, "na"));
	}

	@Test
	public void checkAvgCase2() {
		Assert.assertEquals(339, binarySearch(testData, "nb"));
	}

	@Test
	public void checkAvgCase3() {
		Assert.assertEquals(340, binarySearch(testData, "nc"));
	}

	@Test
	public void checkNoMatch() {
		Assert.assertEquals(-1, binarySearch(testData, "aaa"));
	}

	private static int binarySearch(Object[] array, Object key) {
		if (array == null || key == null) {
			throw new IllegalArgumentException("Invalid input.");
		}
		return binarySearchInternalIterative(array, 0, array.length - 1, key);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static int binarySearchInternalIterative(Object[] array, int startIndex, int endIndex, Object key) {
		int low = startIndex;
		int high = endIndex;

		while (low <= high) {
			int mid = low + high >>> 1;
			int midComp = ((Comparable) array[mid]).compareTo((Comparable) key);
			if (midComp > 0) {
				high = mid - 1;
			} else if (midComp < 0) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	private static int binarySearchInternalRecursive(Object[] array, int startIndex, int endIndex, Object key) {
		return -1;
	}

}
