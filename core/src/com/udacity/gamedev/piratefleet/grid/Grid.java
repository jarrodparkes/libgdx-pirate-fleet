package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Grid {

    Array<Array<Cell>> cells;

    public Grid() {
        cells = new Array<Array<Cell>>();
        for (int r = 0; r < 10; r++) {
            Array<Cell> row = new Array<Cell>();
            for (int c = 0; c < 10; c++) {
                row.add(new Cell());

            }
            cells.add(row);
        }
    }

    public void render(float delta, ShapeRenderer renderer) {
    }
}
