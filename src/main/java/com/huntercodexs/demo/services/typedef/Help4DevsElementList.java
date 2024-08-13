package com.huntercodexs.demo.services.typedef;

public class Help4DevsElementList<L> {

    private int index;
    private String item;
    private L list;

    public Help4DevsElementList() {}

    public Help4DevsElementList(int index, String item, L list) {
        this.index = index;
        this.item = item;
        this.list = list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public L getList() {
        return list;
    }

    public void setList(L list) {
        this.list = list;
    }
}
