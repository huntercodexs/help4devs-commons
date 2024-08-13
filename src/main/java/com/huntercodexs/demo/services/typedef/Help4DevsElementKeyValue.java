package com.huntercodexs.demo.services.typedef;

public class Help4DevsElementKeyValue<K, V> {

    private K key;
    private V value;

    public Help4DevsElementKeyValue() {}

    public Help4DevsElementKeyValue(K key, V value) {
        this.key = key;
        this.value = value;
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

    public void setValue(V value) {
        this.value = value;
    }
}
