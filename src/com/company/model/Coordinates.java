package com.company.model;

public class Coordinates {
    private final int start;
    private final int end;

    public Coordinates(int start , int end){
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
