public class MyHashMap<T, E> {

    private Node<T, E>[] bucket;
    private int size;
    private float variable;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private static final int DEFAULT_CAPACITY = 16;

    public MyHashMap() {
        bucket = new Node[DEFAULT_CAPACITY];
        variable = bucket.length * DEFAULT_LOAD_FACTOR;
        size = 0;
    }

    public int size() {
        return size;
    }

    private Node<T, E> getNextNode(Node<T, E> current) {
        return current.getNext();
    }

    public void put(T key, E value) {
        if (key == null) {
            putForNullKey(value);
            return;
        }
        int hash = hash(key);
        int index = indexFor(hash, bucket.length);
        Node(key, value, hash, index);
    }

    private void Node(T key, E value, int hash, int index) {
        Node<T, E> newNode = new Node<>(hash, key, value, null);
        Node<T, E> current = bucket[index];
        if (current == null) {
            bucket[index] = newNode;
            size++;
            return;
        } else {
            do {
                if (current.hash == hash && (current.key == key || key.equals(current.key))) {
                    current.value = value;
                    return;
                }
                if (current.getNext() != null) {
                    current = current.getNext();
                } else {
                    break;
                }
            } while (current != null);
            current.setNext(newNode);
            size++;
        }
    }

    private void putForNullKey(E value) {
        Node(null, value, 0, 0);
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private int hash(T key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public E remove(T key) {
        Node<T, E> newNode = null;
        Node<T, E> node = null;
        for (int i = 0; i < bucket.length; i++) {

            if (bucket[i] != null) {
                if (bucket[i].key == key) {
                    newNode = bucket[i];
                    if (bucket[i].getNext() == null) {
                        bucket[i] = null;
                    } else {
                        bucket[i] = bucket[i].getNext();
                    }
                    size--;
                    return newNode.getValue();
                }
                node = bucket[i].getNext();
            }
            while (node != null) {
                if (node.key == key) {
                    newNode = node;
                    if (node.getNext() == null) {
                        node = null;
                    } else {
                        node = node.getNext();
                    }
                    size--;
                    return newNode.getValue();
                }
            }
        }
        return newNode.getValue();
    }

    public E get(T key) {
        for (MyHashMap.Node<T, E> node : bucket) {
            if (node != null) {
                if (node.key == key) {
                    return node.getValue();
                } else {
                    while (node != null) {
                        node = node.getNext();
                        if (node != null) {
                            if (node.key == key) {
                                return node.getValue();
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != null) {
                Node<T, E> node = bucket[i].getNext();
                if (node != null) {
                    while (node != null) {
                        if (node.getNext() != null) {
                            Node<T, E> next = node.getNext();
                            node.setNext(null);
                            node = next;
                        } else {
                            break;
                        }
                    }
                }
            }
            bucket[i] = null;
        }
        size = 0;
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V Value = value;
            value = newValue;
            return Value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }


    }


}

