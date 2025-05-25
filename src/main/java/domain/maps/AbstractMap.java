package domain.maps;

import java.util.Iterator;

public abstract class AbstractMap<K,V> implements Map<K,V> {
    public boolean isEmpty() {
        return size() == 0;
    }

    //---------------- nested MapEntry class ----------------
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k; // key
        private V v; // value

        public MapEntry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
        protected void setKey(K key) { k = key; }
        protected V setValue(V value) {
             V old = v;
             v = value;
             return old;
        }
    } //----------- end of nested MapEntry class -----------
    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K,V>> entries = entrySet( ).iterator( ); // reuse entrySet
         public boolean hasNext( ) { return entries.hasNext( ); }
        public K next( ) { return entries.next( ).getKey( ); }//return key!
        public void remove( ) { throw new UnsupportedOperationException( ); }
    }
    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator( ) {return new KeyIterator( );}
    }
    @Override
    public Iterable<K> keySet( ) { return new KeyIterable( ); }

    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K,V>> entries = entrySet( ).iterator( );//reuse entry set
        public boolean hasNext( ) { return entries.hasNext( ); }
        public V next( ) { return entries.next( ).getValue( ); } // return value!
         public void remove( ) { throw new UnsupportedOperationException( );
        }
    }
    private class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator( ) { return new ValueIterator( ); }
 }
 @Override
 public Iterable<V> values( ) { return new ValueIterable( ); }

}