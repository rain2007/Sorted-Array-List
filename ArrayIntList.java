import java.util.Arrays;

public class ArrayIntList {

    // if you write it here it will go into byte code the same as ArrayIntList
    private static final int DEFAULT_CAPACITY = 100;
    private int[] elementData;
    private int size;


    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }


    public ArrayIntList(int capacity) {
        elementData = new int[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    public void add(int index, int value) {
        //Todo: deal with bad indexes
        for (int pos = size; pos >= index+ 1; pos--) {
            elementData[pos] = elementData[pos - 1];
        }
        elementData[index] = value;
        size++;
    }

    // when you need to have room, guarantees you have room
    public void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity > elementData.length) {
            int newCapacity = elementData.length * 2;
            if (requiredCapacity > newCapacity) { //if they ask us for even more than the doubling
                newCapacity = requiredCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity); //determines datatype from the old array
        }
    }

}
