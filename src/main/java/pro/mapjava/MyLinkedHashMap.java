package pro.mapjava;

public class MyLinkedHashMap<K, V> extends MyHashMap<K, V> {
    static class Entry<K, V> extends Node<K, V> {
        Entry<K, V> before, after;

        Entry(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }
    }

    private Entry<K, V> head, tail;

    private void linkNodeLast(Entry<K, V> node) {
        if (tail == null) {
            head = tail = node;
        } else {
            node.before = tail;
            tail.after = node;
            tail = node;
        }
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        Node<K, V> current = getTable()[hash];
        Entry<K, V> newNode = new Entry<>(hash, key, value, null);

        if (current == null) {
            getTable()[hash] = newNode;
            linkNodeLast(newNode);
            incrementSize();
        } else {
            while (current != null) {
                if (current.key == key || (key != null && key.equals(current.key))) {
                    V oldValue = current.value;
                    current.value = value;
                    return oldValue;
                }
                if (current.next == null) break;
                current = current.next;
            }
            current.next = newNode;
            linkNodeLast(newNode);
            incrementSize();
        }
        return null;
    }

    private Node<K, V>[] getTable() {
        return super.table;
    }

    private void incrementSize() {
        super.size++;
    }

    public void printOrder() {
        Entry<K, V> current = head;
        while (current != null) {
            System.out.println("Key: " + current.key + ", Value: " + current.value);
            current = current.after;
        }
    }
}
