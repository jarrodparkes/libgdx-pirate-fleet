package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.udacity.gamedev.piratefleet.Constants;

public class Grid {

    public static final String TAG = Grid.class.getName();

    Array<Array<Cell>> cells;
    Array<GridObject> objects;
    Vector2 center;

    public Grid(Vector2 center) {
        this.center = center;
        cells = new Array<Array<Cell>>();
        objects = new Array<GridObject>();

        // for generating locations...
        float cellSize = Constants.GRID_CELL_SIZE;
        Vector2 topLeftCorner = new Vector2(center.x - cellSize * (Constants.GRID_SIZE / 2),
                center.y + (cellSize * (Constants.GRID_SIZE / 2 - 1)));
        Vector2 offset = new Vector2(topLeftCorner);

        // create cells with location
        for (int r = 0; r < 10; r++) {
            offset.x = topLeftCorner.x;
            Array<Cell> row = new Array<Cell>();
            for (int c = 0; c < 10; c++) {
                row.add(new Cell(new Vector2(offset), r, c));
                offset.x += cellSize;
            }
            cells.add(row);
            offset.y -= cellSize;
        }
    }

    public Array<GridObject> getObjects() {
        return objects;
    }

    public void addObject(GridObject object) {
        Array<Cell> targetLocations = object.locations();
        if (locationsFree(targetLocations)) {
            objects.add(object);
            object.grid = this;
            for (Cell location: targetLocations) {
                Cell targetCell = cells.get(location.row).get(location.col);
                targetCell.object = object;
            }
        } else {
            Gdx.app.log(TAG, "cells unavailable, cannot add object");
        }
    }

    public boolean locationsFree(Array<Cell> locations) {
        for (Cell location: locations) {
            Cell targetCell = cells.get(location.row).get(location.col);
            if (targetCell.object != null) {
                return false;
            }
        }
        return true;
    }

    public void render(float delta, ShapeRenderer renderer) {
        // render cells
        for (Array<Cell> row: cells) {
            for (Cell cell: row) {
                cell.renderCell(delta, renderer);
            }
        }
        // render objects
        for (GridObject object: objects) {
            object.render(delta, renderer);
        }
        // render cells (hit/miss)
        for (Array<Cell> row: cells) {
            for (Cell cell: row) {
                if (cell.getState() != Cell.CellState.UNTOUCHED) {
                    cell.renderState(delta, renderer);
                }
            }
        }
    }

    public Cell cellAtLocation(int r, int c) {
        return cells.get(r).get(c);
    }

    public Cell cellFromOffset(Cell origin, int r, int c) {
        return cells.get(r + origin.getRow()).get(c + origin.getColumn());
    }

    public Cell randomCell() {
        int randomRow = MathUtils.random(0, Constants.GRID_SIZE - 1);
        int randomCol = MathUtils.random(0, Constants.GRID_SIZE - 1);
        return cellAtLocation(randomRow, randomCol);
    }

    public Vector2 getCenter() {
        return center;
    }

    public boolean touchInGrid(Vector2 worldTouch) {
        if (worldTouch.x < (center.x - (Constants.GRID_CELL_SIZE * 5)) ||
                worldTouch.x > (center.x + (Constants.GRID_CELL_SIZE * 5))) {
            return false;
        }

        if (worldTouch.y < (center.y - (Constants.GRID_CELL_SIZE * 5)) ||
                worldTouch.y > (center.y + (Constants.GRID_CELL_SIZE * 5))) {
            return false;
        }
        return true;
    }

    public Cell cellAtTouch(Vector2 worldTouch) {
        float xNormalized = worldTouch.x - (center.x - (Constants.GRID_CELL_SIZE * 5));
        int yIndex = (int)(xNormalized / Constants.GRID_CELL_SIZE);

        float yNormalized = worldTouch.y - (center.y - (Constants.GRID_CELL_SIZE * 5));
        int xIndex = (int)(Constants.GRID_SIZE - (yNormalized / Constants.GRID_CELL_SIZE));

        return cellAtLocation(xIndex, yIndex);
    }
}
