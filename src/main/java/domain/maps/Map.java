package domain.maps;

public interface Map<K,V> {
 int size( );
 boolean isEmpty( );
 public V get(K key);
 public V put(K key, V value);
 public V remove(K key);
 public Iterable<K> keySet( );
 public Iterable<V> values( );
 public Iterable<Entry<K,V>> entrySet( );

}
