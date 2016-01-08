package com.udacity.gamedev.piratefleet.player;

import com.badlogic.gdx.Gdx;
import com.udacity.gamedev.piratefleet.grid.Cell;
import com.udacity.gamedev.piratefleet.grid.Grid;

public class Player {

    public static final String TAG = Player.class.getName();

    public boolean attackGrid(Grid grid, Cell atCell) {
        if (grid.cellAtLocation(atCell.getRow(), atCell.getColumn()).getObject() != null) {
            atCell.setState(Cell.CellState.HIT);
            return true;
        } else {
            atCell.setState(Cell.CellState.MISS);
            return false;
        }
    }
}
