import java.util.*;

/**
 * Provides a sorted version of ArrayList using natural ordering with additional methods
 *
 * @author Raingsey Tevy
 * @version 2024-10-13
 *
 * @param <E> type of element in the list; must be comparable inorder to be properly sorted
 */
public class SortedArrayList<E extends Comparable<E>> implements SortedArrayListInterface<E>, Iterable<E>{

    private final ArrayList<E> elementList;

    /**
     * Creates a sorted array list using ArrayList
     */
    public SortedArrayList() {
        elementList = new ArrayList<>();
    }

    /**
     * Retrieves the number of elements being maintained by the list
     *
     * @return the number of elements being maintained
     */
    @Override
    public int size() {
        return elementList.size();
    }

    /**
     * Retrieves whether the list is empty
     *
     * @return true, if there are no elements in the list; false, if there are elements
     */
    @Override
    public boolean isEmpty() {
        return elementList.isEmpty();
    }

    /**
     * Clears the list; no elements will remain after the call, and size will be 0
     */
    @Override
    public void clear() {
        elementList.clear();
    }

    /**
     * Retrieves whether the specified element is in the list
     *
     * @param value the value to search for
     * @return true, if the element is in the list; false, if not
     */
    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    /**
     * Uses a binary search to find the index of the first occurrence of the specified value,
     * or, if not found, the place that value should be
     *
     * @param value the value to search for
     * @return if found, the index of the value in the list (range 0 to size - 1);
     * if not found, an index representing where the value would go, if added, returned
     * as -(position+1), e.g., -1 means it goes at index 0, -5 means it goes at index 4
     */
    @Override
    public int indexOf(E value) {
        int min = 0;
        int max = elementList.size() - 1;
        int mid;

        while (min <= max) {
            mid = (max + min) / 2;
            int compare = elementList.get(mid).compareTo(value);
            if (compare == 0) {
                while (mid > 0 && elementList.get(mid - 1).compareTo(value) == 0) {
                    mid--;
                }
                return mid;     // found it!
            } else if (compare < 0) {
                min = mid + 1;  // too small
            } else {   // numbers[mid] > target
                max = mid - 1;  // too large
            }
            //mid = (max + min) / 2;
        }

        return - min - 1;   // not found
    }

    /**
     * Retrieves the element at the specified position in the list
     *
     * @param index the index (position) in the list; must be 0 to size-1
     * @return the element at the specified position
     */
    @Override
    public E get(int index) {
        return elementList.get(index);
    }

    /**
     * Retrieves an array of elements that are compare themselves equally to the specified value (via compareTo),
     * with results being stored in the array specified.
     *
     * @param value    the element being sought; will be used to compareTo() other elements
     * @param template a template array used to create results; pass in a 0-sized array
     * @return a new array that is right-sized and contains element references, if any
     */
    @Override
    public E[] get(E value, E[] template) {

        //gets how much of that value is in the sorted array list
        int count = 0;
        for (int i = 0; i < elementList.size(); i++) {
            int compare =  elementList.get(i).compareTo(value);
            if (compare == 0) {
                count++;
            }
        }

        //makes template the right size
        template = Arrays.copyOf(template, count);

        //copies that amount of the value into the template
        for (int i = 0; i < count; i++) {
            template[i] = value;
        }

        return template;
    }

    /**
     * Adds a new element to the list, maintaining sorting via natural order (via compareTo)
     *
     * @param value the value to add to the list
     */
    @Override
    public void add(E value) {
        //find where to put it
        int findIndex = indexOf(value);

        //if it finds the same value, add it there
        if (findIndex < 0) {
            elementList.add(-(findIndex +1), value);
        }
    }

    /**
     * Removes from the list the element at the specified index
     *
     * @param index the index in the list; must be in range  0 to size-1
     */
    @Override
    public void remove(int index) {
        elementList.remove(index);
    }

    /**
     * Retrieves an iterator over list elements; for/each loop are also supported
     *
     * @return a strongly typed iterator over list elements
     */
    @Override
    public Iterator<E> iterator() {
        return new SortedArrayListIterator();
    }

    /**
     * Retrieves an array representing the contents of the list
     *
     * @param template a template list of the proper type, e.g., if E is String,
     *                 the caller can pass in as an argument: new String[0]
     * @return an array containing object references to list elements
     */
    @Override
    public E[] toArray(E[] template) {
        return elementList.toArray(template);
    }

    private class SortedArrayListIterator implements Iterator<E> {
        private int position;           // current position within the list
        private boolean removeOK;       // whether it's okay to remove now

        // post: constructs an iterator for the given list
        public SortedArrayListIterator() {
            position = 0;
            removeOK = false;
        }

        // post: returns true if there are more elements left, false otherwise
        public boolean hasNext() {
            return position < size();
        }

        // pre : hasNext() (throws NoSuchElementException if not)
        // post: returns the next element in the iteration
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elementList.get(position);
            position++;
            removeOK = true;
            return result;
        }
    }
}
