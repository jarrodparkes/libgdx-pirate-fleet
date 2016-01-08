package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.piratefleet.Constants;

public class Mine extends GridObject {

    public static final String TAG = Mine.class.getName();

    public Mine(Grid grid, int r, int c, boolean revealed) {
        super(grid, grid.cellAtLocation(r, c), revealed);
    }

    @Override
    public String toString() {
        String debugString = "Mine -- r: " + origin.row + " c: " + origin.col;
        return debugString;
    }

    public void render(float delta, ShapeRenderer renderer) {
        if (revealed) {
            return;
        }

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.MINE_COLOR_1);
        // ^ triangle
        renderer.triangle(
                origin.position.x + Constants.GRID_CELL_SIZE * 3 / 8,
                origin.position.y + Constants.GRID_CELL_SIZE / 2,
                origin.position.x + Constants.GRID_CELL_SIZE * 5 / 8,
                origin.position.y + Constants.GRID_CELL_SIZE / 2,
                origin.position.x + Constants.GRID_CELL_SIZE / 2,
                origin.position.y + Constants.GRID_CELL_SIZE * 7 / 8
        );
        // > triangle
        renderer.triangle(
                origin.position.x + Constants.GRID_CELL_SIZE / 2,
                origin.position.y + Constants.GRID_CELL_SIZE * 5/8,
                origin.position.x + Constants.GRID_CELL_SIZE / 2,
                origin.position.y + Constants.GRID_CELL_SIZE * 3/8,
                origin.position.x + Constants.GRID_CELL_SIZE * 7/8,
                origin.position.y + Constants.GRID_CELL_SIZE / 2
        );
        // v triangle
        renderer.triangle(
                origin.position.x + Constants.GRID_CELL_SIZE * 3/8,
                origin.position.y + Constants.GRID_CELL_SIZE / 2,
                origin.position.x + Constants.GRID_CELL_SIZE * 5/8,
                origin.position.y + Constants.GRID_CELL_SIZE / 2,
                origin.position.x + Constants.GRID_CELL_SIZE / 2,
                origin.position.y + Constants.GRID_CELL_SIZE * 1/8
        );
        // < triangle
        renderer.triangle(
                origin.position.x + Constants.GRID_CELL_SIZE / 2,
                origin.position.y + Constants.GRID_CELL_SIZE * 5/8,
                origin.position.x + Constants.GRID_CELL_SIZE / 2,
                origin.position.y + Constants.GRID_CELL_SIZE * 3/8,
                origin.position.x + Constants.GRID_CELL_SIZE * 1/8,
                origin.position.y + Constants.GRID_CELL_SIZE / 2
        );
        renderer.setColor(Constants.MINE_COLOR_2);
        // circular center
        renderer.circle(
                origin.position.x + Constants.GRID_CELL_SIZE / 2,
                origin.position.y + Constants.GRID_CELL_SIZE / 2,
                Constants.GRID_CELL_SIZE / 5,
                10
        );
        renderer.end();
    }

    public Array<Cell> locations() {
        Array<Cell> cells = new Array<Cell>();
        cells.add(grid.cellAtLocation(origin.getRow(), origin.getColumn()));
        return cells;
    }

    public boolean allLocationsHit() {
        return origin.getState() == Cell.CellState.UNTOUCHED;
    }
}
