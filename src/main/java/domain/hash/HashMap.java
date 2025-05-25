package domain.hash;

import domain.maps.Entry;
import domain.maps.UnsortedTableMap;
import java.util.ArrayList;
import java.util.List;


public class HashMap<K,V> extends AbstractHashMap<K, V> {
    private UnsortedTableMap<K, V>[] table;  // array de buckets

    @SuppressWarnings("unchecked")
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }

    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        return bucket.get(k);
    }

    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            bucket = new UnsortedTableMap<>();
            table[h] = bucket;
        }
        int oldSize = bucket.size();
        V oldValue = bucket.put(k, v);
        n += (bucket.size() - oldSize); // aumentar n solo si se insertó nuevo par
        return oldValue;
    }

    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        int oldSize = bucket.size();
        V removedValue = bucket.remove(k);
        n -= (oldSize - bucket.size()); // disminuir n si realmente se removió
        return removedValue;
    }

    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> buffer = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i].entrySet()) {
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }
}