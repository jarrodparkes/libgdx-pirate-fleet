package com.udacity.gamedev.piratefleet.player;

import com.badlogic.gdx.Gdx;
import com.udacity.gamedev.piratefleet.grid.Cell;
import com.udacity.gamedev.piratefleet.grid.Grid;
import com.udacity.gamedev.piratefleet.grid.GridObject;

public class Player {

    public static final String TAG = Player.class.getName();

    public boolean attackGrid(Grid grid, Cell atCell) {
        boolean hit = false;
        GridObject hitObject = null;
        if (grid.cellAtLocation(atCell.getRow(), atCell.getColumn()).getObject() != null) {
            atCell.setState(Cell.CellState.HIT);
            hitObject = atCell.getObject();
            hit = true;
        } else {
            atCell.setState(Cell.CellState.MISS);
        }

        if (hit && hitObject.allLocationsHit()) {
            hitObject.setRevealed(true);
        }

        return hit;
    }
}
