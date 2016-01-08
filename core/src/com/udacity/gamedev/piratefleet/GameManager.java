package com.udacity.gamedev.piratefleet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.piratefleet.grid.Grid;
import com.udacity.gamedev.piratefleet.grid.Cell;
import com.udacity.gamedev.piratefleet.grid.GridObject;
import com.udacity.gamedev.piratefleet.grid.Mine;
import com.udacity.gamedev.piratefleet.grid.Ship;
import com.udacity.gamedev.piratefleet.player.Player;

public class GameManager {

    public static final String TAG = GameManager.class.getName();

    Player humanPlayer;
    Grid humanGrid;

    Player comPlayer;
    Grid comGrid;

    public GameManager() {
        // setup human
        humanPlayer = new Player();
        humanGrid = new Grid(new Vector2(Constants.WORLD_SIZE.x / 4, Constants.WORLD_SIZE.y * 0.55f));
        humanGrid.addObject(new Ship(humanGrid, 4, 0, 3, Ship.Orientation.HORIZONTAL, true));
        humanGrid.addObject(new Ship(humanGrid, 2, 6, 3, Ship.Orientation.VERTICAL, true));
        humanGrid.addObject(new Mine(humanGrid, 0, 3, true));
        // setup com
        comPlayer = new Player();
        comGrid = new Grid(new Vector2(Constants.WORLD_SIZE.x * 3/4, Constants.WORLD_SIZE.y * 0.55f));
        generateComputerObjects(comGrid);
    }

    public void render(float delta, ShapeRenderer renderer) {
        humanGrid.render(delta, renderer);
        comGrid.render(delta, renderer);
    }

    public Array<GridObject> generateComputerObjects(Grid grid) {
        Array<GridObject> objects = new Array<GridObject>();
        for (Constants.ShipSize size: Constants.ShipSize.values()) {
            for (int i = 0; i < size.numRequired; ++i) {
                int length = size.length;
                Cell origin = grid.randomCell();
                Ship.Orientation orientation = MathUtils.random(0, 1) == 0 ? Ship.Orientation.VERTICAL : Ship.Orientation.HORIZONTAL;
                Ship ship = new Ship(grid, origin.getRow(), origin.getColumn(), length, orientation, false);
                while(!validShip(ship)) {
                    origin = grid.randomCell();
                    orientation = MathUtils.random(0, 1) == 0 ? Ship.Orientation.VERTICAL : Ship.Orientation.HORIZONTAL;
                    ship = new Ship(grid, origin.getRow(), origin.getColumn(), length, orientation, false);
                }
                grid.addObject(ship);
            }
        }
        return objects;
    }

    public boolean validShip(Ship ship) {
        // are any cells out of bounds?
        if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            if (ship.getOrigin().getColumn() + ship.getLength() >= Constants.GRID_SIZE) {
                return false;
            }
        } else {
            if (ship.getOrigin().getRow() + ship.getLength() >= Constants.GRID_SIZE) {
                return false;
            }
        }
        // are all cells empty?
        for (Cell cell: ship.locations()) {
            if (cell.getObject() != null) {
                return false;
            }
        }
        return true;
    }

    public void handleTouch(Vector2 worldTouch) {
        if (comGrid.touchInGrid(worldTouch)) {
            Cell targetCell = comGrid.cellAtTouch(worldTouch);
            humanPlayer.attackGrid(comGrid, targetCell);
        }
    }
}
