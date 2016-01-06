package com.udacity.gamedev.piratefleet.grid;

public class GridLocation {

    public static final String TAG = GridLocation.class.getName();

    int row;
    int col;

    public GridLocation(GridLocation position) {
        this.row = position.row;
        this.col = position.col;
    }

    public GridLocation(int r, int c) {
        this.row = r;
        this.col = c;
    }

    @Override
    public String toString() {
        return "r: " + row + ", c: " + col;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public void setRow(int r) {
        this.row = r;
    }

    public void setColumn(int c) {
        this.col = c;
    }

    public GridLocation locationFromOffset(int row, int col) {
        return new GridLocation(this.row + row, this.col + col);
    }
}
