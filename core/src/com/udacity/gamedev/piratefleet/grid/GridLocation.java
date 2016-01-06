package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.math.MathUtils;
import com.udacity.gamedev.piratefleet.Constants;

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

    public static GridLocation randomLocation() {
        int randomRow = MathUtils.random(0, Constants.GRID_SIZE - 1);
        int randomCol = MathUtils.random(0, Constants.GRID_SIZE - 1);
        return new GridLocation(randomRow, randomCol);
    }
}
