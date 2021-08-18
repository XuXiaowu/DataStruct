public interface Map<K, V> {

    void add(K key, V value);

    V remove(K ket);

    boolean contains(K key);

    void set(K key, V value);

    V get(K key);

    int getSize();

    boolean isEmpty();

}

