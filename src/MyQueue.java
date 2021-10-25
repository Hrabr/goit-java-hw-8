import java.util.EmptyStackException;


public class MyQueue <E> {
    private Node<E> firstNode = null;
    private Node <E>lastNode = null;
    private int size = 0;


    private static class Node<E>{
        E data;
        Node <E> prev;
        Node <E> next;


        Node(Node prev, E data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
    public void add(E e){

        if(size==0){
            lastNode=new Node<E>(null,e,null);
            firstNode=lastNode;

            size++;
        }else{
            Node<E> newNode=new Node<E>(null,e,null);
            lastNode.next = newNode;
            newNode.prev = this.lastNode;
            lastNode = newNode;
            size++;

        }
    }
    public void remove(int index){
        checkElementIndex(index);

        if(index==0){
            firstNode=firstNode.next;
            size--;
        }else if(index==size-1){
            lastNode=lastNode.prev;
            size--;
        } else{
            Node <E>newNode=this.node(index);
            newNode.next.prev=newNode.prev;
            newNode.prev.next=newNode.next;
            newNode.next=null;
            newNode.data=null;
            newNode.prev=null;
            size--;}

    }
    public void clear(){
        for(Node newNode = firstNode; newNode!=null; size--){
            Node nextNewNode=newNode.next;
            newNode.next=null;
            newNode.data=null;
            newNode.prev=null;
            newNode=nextNewNode;
        }
        firstNode=null;
        size=0;
    }
    public Object get(int index) {

        return node(index).data;
    }
    public Object peek() {
        final Node f = firstNode;

        if (f == null){
            throw new EmptyStackException();
        }
        return f.data;
    }
    public Object poll() {
        if (firstNode == null) {
            throw new EmptyStackException();
        } else {
            final Object element = firstNode.data;
            final Node <E>next = firstNode.next;
            firstNode.data = null;
            firstNode.next = null;
            firstNode = next;
            if (next == null) {
                lastNode = null;
            }
            else {
                next.prev = null;
            }
            size--;

            return element;
        }
    }
    private Node <E>node(int index) {

        checkElementIndex(index);

        if(index<=(size>>1)) {
            Node <E>newNode=firstNode;
            for(int i=0;i<index;i++) {
                newNode=newNode.next;

            }

            return newNode;
        }else {
            Node <E>node=this.lastNode;
            for(int i=size-1;i>index;i--) {
                node=node.prev;

            }
            return node;
        }
    }
    public void getAll(){
        for (int i = 0; i < size; i++) {
            System.out.println(get(i));
        }
    }
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    private boolean isElementIndex(int index) {

        return index >= 0 && index < size;
    }
    public int size() {
        return size;
    }
}
