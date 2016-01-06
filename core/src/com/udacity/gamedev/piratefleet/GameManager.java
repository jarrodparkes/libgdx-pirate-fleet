package com.udacity.gamedev.piratefleet;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.piratefleet.grid.Grid;
import com.udacity.gamedev.piratefleet.grid.GridLocation;
import com.udacity.gamedev.piratefleet.grid.GridObject;
import com.udacity.gamedev.piratefleet.grid.Ship;

public class GameManager {

    Grid humanGrid;
    Grid comGrid;

    public GameManager() {
        // setup grids
        Array<GridObject> objects = new Array<GridObject>();
        objects.add(new Ship(new GridLocation(4, 0), 3, Ship.Orientation.HORIZONTAL));
        objects.add(new Ship(new GridLocation(2, 6), 3, Ship.Orientation.VERTICAL));
        humanGrid = new Grid(
                new Vector2(Constants.WORLD_SIZE.x / 4, Constants.WORLD_SIZE.y * 5/8),
                objects
        );
        comGrid = new Grid(new Vector2(Constants.WORLD_SIZE.x * 3/4, Constants.WORLD_SIZE.y * 5/8), generateComputerObjects());
    }

    public void render(float delta, ShapeRenderer renderer) {
        humanGrid.render(delta, renderer);
        comGrid.render(delta, renderer);
    }

    public Array<GridObject> generateComputerObjects() {
        Array<GridObject> objects = new Array<GridObject>();
        for (Constants.ShipSize size: Constants.ShipSize.values()) {
            for (int i = 0; i < size.numRequired; ++i) {
                int length = size.length;
                GridLocation location = GridLocation.randomLocation();
                Ship.Orientation orientation = MathUtils.random(0, 1) == 0 ? Ship.Orientation.VERTICAL : Ship.Orientation.HORIZONTAL;
                Ship ship = new Ship(location, length, orientation);
                while(!validObject(ship)) {
                    location = GridLocation.randomLocation();
                    orientation = MathUtils.random(0, 1) == 0 ? Ship.Orientation.VERTICAL : Ship.Orientation.HORIZONTAL;
                    ship = new Ship(location, length, orientation);
                }
                objects.add(ship);
            }
        }
        return objects;
    }

    public boolean validObject(GridObject object) {
        for(GridLocation location: object.locations()) {
            if (location.getRow() >= Constants.GRID_SIZE || location.getColumn() >= Constants.GRID_SIZE) {
                return false;
            }
        }
        return true;
    }
}
