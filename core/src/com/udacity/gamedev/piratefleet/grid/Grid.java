package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.udacity.gamedev.piratefleet.Constants;

public class Grid {

    public static final String TAG = Grid.class.getName();

    Array<Array<Cell>> cells;
    Array<GridObject> objects;
    Vector2 center;

    public Grid(Vector2 center, Array<GridObject> objects) {
        this.center = center;
        // create cells
        cells = new Array<Array<Cell>>();
        for (int r = 0; r < 10; r++) {
            Array<Cell> row = new Array<Cell>();
            for (int c = 0; c < 10; c++) {
                row.add(new Cell());
            }
            cells.add(row);
        }
        // add objects to cells
        this.objects = objects;
        for (GridObject object: objects) {
            addObject(object);
        }
    }

    public void addObject(GridObject object) {

        Array<GridLocation> targetLocations = object.locations();

        if (locationsFree(targetLocations)) {
            for (GridLocation location: targetLocations) {
                Cell targetCell = cells.get(location.row).get(location.col);
                targetCell.object = object;
            }
        } else {
            Gdx.app.log(TAG, "cells unavailable, cannot add object");
        }
    }

    public boolean locationsFree(Array<GridLocation> locations) {
        for (GridLocation location: locations) {
            Cell targetCell = cells.get(location.row).get(location.col);
            if (targetCell.object != null) {
                return false;
            }
        }
        return true;
    }

    public void render(float delta, ShapeRenderer renderer) {

        float cellSize = Constants.GRID_CELL_SIZE;
        Vector2 topLeftCorner = new Vector2(center.x - cellSize * (Constants.GRID_SIZE / 2),
                center.y + (cellSize * (Constants.GRID_SIZE / 2 - 1)));
        Vector2 offset = new Vector2(topLeftCorner);

        for (Array<Cell> row: cells) {
            offset.x = topLeftCorner.x;
            for (Cell cell: row) {
                cell.render(delta, renderer, new Vector2(offset.x, offset.y));
                offset.x += cellSize;
            }
            offset.y -= cellSize;
        }
    }
}
