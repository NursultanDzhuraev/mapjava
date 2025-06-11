package pro.mapjava;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {
    private MyHashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    void testPutAndGet() {
        assertNull(map.put("a", 1));
        assertEquals(1, map.get("a"));
        assertEquals(1, map.size());

        assertEquals(1, map.put("a", 2));
        assertEquals(2, map.get("a"));
        assertEquals(1, map.size());
    }

    @Test
    void testCollisionHandling() {
        String key1 = "Aa";
        String key2 = "BB";
        assertEquals(key1.hashCode(), key2.hashCode());

        map = new MyHashMap<>();
        map.put(key1, 10);
        map.put(key2, 20);
        assertEquals(10, map.get(key1));
        assertEquals(20, map.get(key2));
        assertEquals(2, map.size());
    }
}

class MyLinkedHashMapTest {
    private MyLinkedHashMap<String, Integer> linkedMap;

    @BeforeEach
    void setUp() {
        linkedMap = new MyLinkedHashMap<>();
    }

    @Test
    void testPutGetAndOrder() {
        linkedMap.put("first", 1);
        linkedMap.put("second", 2);
        linkedMap.put("third", 3);

        assertEquals(3, linkedMap.size());
        assertEquals(1, linkedMap.get("first"));
        assertEquals(2, linkedMap.get("second"));
        assertEquals(3, linkedMap.get("third"));

        StringBuilder order = new StringBuilder();
        linkedMap.printOrder();
    }

    @Test
    void testUpdateValuePreservesOrder() {
        linkedMap.put("one", 1);
        linkedMap.put("two", 2);
        linkedMap.put("one", 11);

        assertEquals(2, linkedMap.size());
        assertEquals(11, linkedMap.get("one"));

    }
}

class MyTreeMapTest {
    private MyTreeMap<Integer, String> treeMap;

    @BeforeEach
    void setUp() {
        treeMap = new MyTreeMap<>();
    }

    @Test
    void testPutAndGet() {
        assertNull(treeMap.put(2, "two"));
        assertNull(treeMap.put(1, "one"));
        assertNull(treeMap.put(3, "three"));

        assertEquals("one", treeMap.get(1));
        assertEquals("two", treeMap.get(2));
        assertEquals("three", treeMap.get(3));
        assertEquals(3, treeMap.size());
    }

    @Test
    void testUpdateValue() {
        treeMap.put(5, "five");
        assertEquals("five", treeMap.get(5));
        assertEquals(1, treeMap.size());

        treeMap.put(5, "FIVE");
        assertEquals("FIVE", treeMap.get(5));
        assertEquals(1, treeMap.size());
    }

    @Test
    void testRemove() {
        treeMap.put(10, "ten");
        treeMap.put(5, "five");
        treeMap.put(15, "fifteen");

        assertEquals("five", treeMap.remove(5));
        assertNull(treeMap.get(5));
        assertEquals(2, treeMap.size());

        assertEquals("ten", treeMap.remove(10));
        assertNull(treeMap.get(10));
        assertEquals(1, treeMap.size());
    }

    @Test
    void testSortedOrder() {
        treeMap.put(3, "three");
        treeMap.put(1, "one");
        treeMap.put(2, "two");
        treeMap.put(5, "five");
        treeMap.put(4, "four");

    }
}

