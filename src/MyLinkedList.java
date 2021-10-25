public class MyLinkedList<E>{
    private Node <E>firstNode = null;
    private Node <E>lastNode = null;
    private int size = 0;


    private static class Node <E>{
        E data;
        Node <E>prev;
        Node <E>next;


        Node(Node prev, E data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
    public void add(E value){

        if(size==0){
            lastNode=new Node(null,value,null);
            firstNode=lastNode;

            size++;
        }else{
            Node newNode=new Node(null,value,null);
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
            Node newNode=this.node(index);
            newNode.next.prev=newNode.prev;
            newNode.prev.next=newNode.next;
            newNode.next=null;
            newNode.data=null;
            newNode.prev=null;
            size--;}

    }
    public void clear(){
        for(Node newNode=firstNode;newNode!=null;size--){
            Node nextNewNode=newNode.next;
            newNode.next=null;
            newNode.data=null;
            newNode.prev=null;
            newNode=nextNewNode;
        }
        firstNode=null;
        size=0;
    }
    public E get(int index) {

        return node(index).data;
    }
    private Node<E> node(int index) {

        checkElementIndex(index);

        if(index<=(size>>1)) {
            Node <E>newNode=firstNode;
            for(int i=0;i<index;i++) {
                newNode=newNode.next;

            }

            return newNode;
        }else {
            Node<E> node=this.lastNode;
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
