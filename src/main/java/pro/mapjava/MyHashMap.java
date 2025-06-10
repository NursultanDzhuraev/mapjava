package pro.mapjava;

import java.util.Objects;

public class MyHashMap<K, V> {
    static class Node<K, V> {
        final K key;
        V value;
        final int hash;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    Node<K, V>[] table;
    int size;
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    int hash(K key) {
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & (table.length - 1);
    }

    public V put(K key, V value) {
        int hash = hash(key);
        if (table[hash] == null) {
            table[hash] = new Node<>(hash, key, value, null);
            size++;
        } else {
            Node<K, V> current = table[hash];
            while (current != null) {
                if (Objects.equals(current.key, key)) {
                    V oldValue = current.value;
                    current.value = value;
                    return oldValue;
                }
                if (current.next == null) break;
                current = current.next;
            }
            current.next = new Node<>(hash, key, value, null);
            size++;
        }
        return null;
    }

    public V get(K key) {
        int hash = hash(key);
        Node<K, V> current = table[hash];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }
}
