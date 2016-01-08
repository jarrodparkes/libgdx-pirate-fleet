package com.udacity.gamedev.piratefleet.player;

import com.badlogic.gdx.Gdx;
import com.udacity.gamedev.piratefleet.grid.Cell;
import com.udacity.gamedev.piratefleet.grid.Grid;
import com.udacity.gamedev.piratefleet.grid.GridObject;

public class Player {

    public static final String TAG = Player.class.getName();

    int movesRemaining;

    public int getMovesRemaining() {
        return movesRemaining;
    }

    public void addMovesRemaining(int moves) {
        movesRemaining += moves;
    }

    public GridObject makeMove(Grid grid, Cell atCell) {
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

        movesRemaining--;

        return hitObject;
    }

    public GridObject makeRandomMove(Grid grid) {

        Cell targetCell = grid.randomCell();

        while(targetCell.getState() != Cell.CellState.UNTOUCHED) {
            targetCell = grid.randomCell();
        }

        return makeMove(grid, targetCell);
    }
}
