package org.fintecy.md.bitstamp.model;

public enum Grouping {
    GROUP_0(0),
    GROUP_1(1),
    GROUP_2(2),
    ;

    private final short value;

    Grouping(int value) {
        this.value = (short) value;
    }

    public short getValue() {
        return value;
    }
}
