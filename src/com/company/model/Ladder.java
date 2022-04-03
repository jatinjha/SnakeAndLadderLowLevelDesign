package com.company.model;

public class Ladder {
    private Coordinates coordinates;

    public Ladder(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
