package mine.exercise.codetest.easy.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BinarySearch {

    private static String[] testData = new String[26 * 26];

    @BeforeAll
    public static void init() {
        int counter = 0;
        for (char i = 97; i <= 122; i++) {
            for (char j = 97; j <= 122; j++) {
                testData[counter++] = (i) + "" + (j);
            }
        }
    }

    private static int binarySearch(Object[] array, Object key) {
        if (array == null || key == null) {
            throw new IllegalArgumentException("Invalid input.");
        }
        return binarySearchInternalIterative(array, 0, array.length - 1, key);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
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

    @Test
    public void throwExceptionOnInvalidata() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> binarySearch(null, "abc"));
    }

    @Test
    public void checkBestCase() {
        Assertions.assertEquals(0, binarySearch(testData, "aa"));
    }

    @Test
    public void checkWorstCase() {
        Assertions.assertEquals(675, binarySearch(testData, "zz"));
    }

    @Test
    public void checkAvgCase1() {
        Assertions.assertEquals(338, binarySearch(testData, "na"));
    }

    @Test
    public void checkAvgCase2() {
        Assertions.assertEquals(339, binarySearch(testData, "nb"));
    }

    @Test
    public void checkAvgCase3() {
        Assertions.assertEquals(340, binarySearch(testData, "nc"));
    }

    @Test
    public void checkNoMatch() {
        Assertions.assertEquals(-1, binarySearch(testData, "aaa"));
    }

}
