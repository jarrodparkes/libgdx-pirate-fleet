package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Color;
import com.udacity.gamedev.piratefleet.Constants;

public class Ship extends GridObject {

    public static final String TAG = Ship.class.getName();

    public enum Orientation {
        VERTICAL, HORIZONTAL
    }

    int length;
    Orientation orientation;

    public Ship(Grid grid, int r, int c, int length, Orientation orientation) {
        super(grid, grid.cellAtLocation(r, c));
        this.length = length;
        this.orientation = orientation;
    }

    public int getLength() {
        return length;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        String debugString = "r: " + origin.row + " c: " + origin.col;
        debugString += "\nlength: " + length;
        debugString += "\norientation: " + orientation;
        return debugString;
    }

    public void render(float delta, ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.SHIP_COLOR);
        int index = 0;

        for (Cell location: locations()) {
            if (index == 0) {
                switch(orientation) {
                    case HORIZONTAL:
                        // < triangle
                        renderer.triangle(
                                location.position.x + Constants.GRID_CELL_SIZE,
                                location.position.y + Constants.GRID_CELL_SIZE * 7/8,
                                location.position.x + Constants.GRID_CELL_SIZE,
                                location.position.y + Constants.GRID_CELL_SIZE * 1/8,
                                location.position.x + Constants.GRID_CELL_SIZE / 4,
                                location.position.y + Constants.GRID_CELL_SIZE / 2
                        );
                        break;
                    case VERTICAL:
                        // ^ triangle
                        renderer.triangle(
                            location.position.x + Constants.GRID_CELL_SIZE * 1/8,
                            location.position.y,
                            location.position.x + Constants.GRID_CELL_SIZE * 7/8,
                            location.position.y,
                            location.position.x + Constants.GRID_CELL_SIZE / 2,
                            location.position.y + Constants.GRID_CELL_SIZE * 3/4
                        );
                        break;
                }
            } else if(index != locations().size - 1)
                switch(orientation) {
                    case HORIZONTAL:
                        // middle section left-to-right
                        renderer.rect(location.position.x, location.position.y + Constants.GRID_CELL_SIZE / 8, Constants.GRID_CELL_SIZE, Constants.GRID_CELL_SIZE * 6/8);
                        break;
                    case VERTICAL:
                        // middle section up-and-down
                        renderer.rect(location.position.x + Constants.GRID_CELL_SIZE / 8, location.position.y, Constants.GRID_CELL_SIZE * 6/8, Constants.GRID_CELL_SIZE);
                        break;
                }
            else {
                switch(orientation) {
                    case HORIZONTAL:
                        // > triangle
                        renderer.triangle(
                                location.position.x + Constants.GRID_CELL_SIZE * 3/4,
                                location.position.y + Constants.GRID_CELL_SIZE / 2,
                                location.position.x,
                                location.position.y + Constants.GRID_CELL_SIZE * 7/8,
                                location.position.x,
                                location.position.y + Constants.GRID_CELL_SIZE * 1/8
                        );
                        break;
                    case VERTICAL:
                        // v triangle
                        renderer.triangle(
                                location.position.x + Constants.GRID_CELL_SIZE * 1/8,
                                location.position.y + Constants.GRID_CELL_SIZE,
                                location.position.x + Constants.GRID_CELL_SIZE * 7/8,
                                location.position.y + Constants.GRID_CELL_SIZE,
                                location.position.x + Constants.GRID_CELL_SIZE / 2,
                                location.position.y + Constants.GRID_CELL_SIZE / 4
                        );
                        break;
                }
            }
            index += 1;
        }
        renderer.end();
    }

    public Array<Cell> locations() {
        Array<Cell> cells = new Array<Cell>();
        for (int i = 0; i < length; ++i) {
            Cell location = (orientation == Orientation.HORIZONTAL) ?
                    grid.cellFromOffset(origin, 0, i) : grid.cellFromOffset(origin, i, 0);
            cells.add(location);
        }
        return cells;
    }
}
