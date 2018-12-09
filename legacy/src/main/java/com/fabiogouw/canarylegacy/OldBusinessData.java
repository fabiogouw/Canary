package com.fabiogouw.canarylegacy;

public class OldBusinessData {

    private String _name;
    public String getName() {
        return _name;
    }
    public void setName(String name) {
        _name = name;
    }

    @Override
    public String toString() {
        return "Old name is " + _name;
    }
}