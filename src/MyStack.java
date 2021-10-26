import java.util.EmptyStackException;

public class MyStack<E> {
    private Node <E>firstNode = null;
    private Node <E>lastNode = null;
    private int size = 0;

    private static class Node<E> {

        E data;
        Node <E> next;



        Node( E data, Node <E> next) {

            this.data = data;
            this.next = next;
        }
    }

    public void  push(E data) {
        Node<E> newNode = new Node<E>( data, null);
        newNode.data =data;
        if (lastNode == null) {
            firstNode = newNode;
            lastNode = newNode;
            size++;
        } else {
            lastNode.next = newNode;
            lastNode = newNode;
            size++;
        }
    }

    public void remove(int index) {


        checkElementIndex(index);
        if (firstNode == lastNode) {
            firstNode = null;
            lastNode = null;
            size--;
        } else if (index == size - 1) {
            lastNode = node(index - 1);
            size--;
        } else if (index == 0 && size > 1) {
            firstNode = firstNode.next;
            size--;
        } else {
            node(index).data = null;
            node(index - 1).next = node(index + 1);

            size--;
        }
    }

    public void clear() {
        for (Node <E> newFirstNode = this.firstNode; newFirstNode != null; size--) {
            Node newNode = newFirstNode.next;
            newFirstNode.next = null;
            newFirstNode.data = null;
            newFirstNode = newNode;
        }
        lastNode = null;
        firstNode = null;
        size = 0;
    }

    public Object peek() {
        final Node <E>newNode = lastNode;

        if (newNode == null){
            throw new EmptyStackException();
        }
        return newNode.data;
    }

    public Object pop() {
        if (firstNode == null) {
            throw new EmptyStackException();
        } else {
            final Object element = lastNode.data;
           remove(size-1);

            return element;
        }
    }

    private Node node(int index) {
        checkElementIndex(index);
        int tag = 0;

        Node tempNode = null;

        for (Node newNode = this.firstNode; newNode != null; newNode = newNode.next) {

            if (tag == index) {
                tempNode = newNode;
                break;
            }
            tag++;
        }
        return tempNode;
    }

    public Object get(int index) {

        return this.node(index).data;
    }

    public void getAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(get(i));
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    private boolean isElementIndex(int index) {

        return index >= 0 && index <= size;
    }

    public int size() {
        return size;
    }
}
