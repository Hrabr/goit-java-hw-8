import java.util.Arrays;
import java.util.Objects;

public class MyArrayList <E> {
    private final int CONSTANT_SIZE = 10;
    private Object[] array;
    private int counter = 0;

    public MyArrayList() {
        this.array = new Object[CONSTANT_SIZE];
    }

    public MyArrayList(int index) {
        if (index > 0) {
            this.array = new Object[index];
        } else if (index == 0) {
            this.array = new Object[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    index+"size: "+counter);
        }
    }

    public void  add(E value) {
        if (array.length == counter) {
            resize(array.length + (array.length >> 1));

        }
        array[counter++] = value;
    }

    public void remove(int index) {
        Objects.checkIndex(index, counter);
        for (int i = index; i < counter; i++) {
            array[i] = array[i + 1];
        }
        array[counter] = null;
        counter--;

    }

    public int size() {
        return counter;
    }

    public void clear() {
        for (int i = 0; i < counter; i++) {
            array[i] = null;
        }
        counter = 0;
        array = new Object[CONSTANT_SIZE];

    }
    public E get(int index) {
        Objects.checkIndex(index, counter);
        return (E) array[index];
    }

    private void resize(int newLength) {
        array = Arrays.copyOf(array, newLength);
    }
}