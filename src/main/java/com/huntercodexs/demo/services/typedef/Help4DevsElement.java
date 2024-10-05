package com.huntercodexs.demo.services.typedef;

public class Help4DevsElement<T> {

    private T value;

    public Help4DevsElement() {}

    public Help4DevsElement(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
