package com.udacity.gamedev.piratefleet.grid;

public class GridLocation {

    public static final String TAG = GridLocation.class.getName();

    int r;
    int c;

    public GridLocation(GridLocation position) {
        this.r = position.r;
        this.c = position.c;
    }

    public GridLocation(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "r: " + r + ", c: " + c;
    }

    public int getRow() {
        return r;
    }

    public int getColumn() {
        return c;
    }

    public void setRow(int r) {
        this.r = r;
    }

    public void setColumn(int c) {
        this.c = c;
    }
}
