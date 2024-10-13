import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedArrayListTest {

    private SortedArrayList<String> testArrayList;

    @BeforeEach
    public void setup() {
        testArrayList = new SortedArrayList<>();
        testArrayList.add("1"); testArrayList.add("2"); testArrayList.add("3"); testArrayList.add("4");
    }

    @Test
    public void testAddAndIndexOf() {
        SortedArrayList<String> testArrayList = new SortedArrayList<>();
        testArrayList.add("ABW2000"); testArrayList.add("AIA2000"); testArrayList.add("AFG2000"); testArrayList.add("AGO2000");

        //testing basic indexOf
        assertEquals(2, testArrayList.indexOf("AGO2000"));
        assertEquals(1, testArrayList.indexOf("AFG2000"));
        assertEquals(0, testArrayList.indexOf("ABW2000"));
        assertEquals(3, testArrayList.indexOf("AIA2000"));

        //testing the location of where an object would go
        assertEquals(-1, testArrayList.indexOf("ABC2000"));
        assertEquals(-2, testArrayList.indexOf("ACD2000"));
        assertEquals(-5, testArrayList.indexOf("AZX2000"));

        //dealing with duplicates
        testArrayList.add("AIA2000");
        testArrayList.add("AIA2000");
        assertEquals(3, testArrayList.indexOf("AIA2000"));
        testArrayList.add("AEE2000");
        testArrayList.add("AEE2000");
        testArrayList.add("AEE2000");
        assertEquals(1, testArrayList.indexOf("AEE2000"));
    }

    @Test
    public void testSize() {
        assertEquals(4, testArrayList.size());
    }

    @Test
    public void testClearAndIsEmpty() {
        assertFalse(testArrayList.isEmpty());

        testArrayList.clear();
        assertTrue(testArrayList.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(testArrayList.contains("3"));
        assertFalse(testArrayList.contains("10"));
    }

    @Test
    public void testGet() {
        assertEquals("1", testArrayList.get(0));
        assertEquals("4", testArrayList.get(3));
    }
/*
    @Test
    public void testGetWithValueTemplate() {
        SortedArrayList<String> testArrayList = new SortedArrayList<>();
        testArrayList.add("2"); testArrayList.add("3"); testArrayList.add("3"); testArrayList.add("4");

        String[] stringArray = testArrayList.get("3", new String[0]);

        assertEquals(2, stringArray.length);
    }

 */

    @Test
    public void testRemove() {
        SortedArrayList<String> testArrayList = new SortedArrayList<>();
        testArrayList.add("0"); testArrayList.add("1"); testArrayList.add("2"); testArrayList.add("3");

        testArrayList.remove(2);
        assertEquals("3", testArrayList.get(2));
    }


    @Test
    public void testIterator() {
        SortedArrayList<String> testArrayList = new SortedArrayList<>();
        testArrayList.add("0"); testArrayList.add("1"); testArrayList.add("2"); testArrayList.add("3");

        SortedArrayList<String> testArrayListIterator = new SortedArrayList<>();
        for (String item : testArrayList) {
            testArrayListIterator.add(item);
        }

        assertEquals(4, testArrayListIterator.size());
    }

    @Test
    public void testToArray() {
        SortedArrayList<String> testArrayList = new SortedArrayList<>();
        testArrayList.add("0"); testArrayList.add("1"); testArrayList.add("2"); testArrayList.add("3");

        String[] testArray = testArrayList.toArray(new String[0]);
        assertEquals(4, testArray.length);

    }

}