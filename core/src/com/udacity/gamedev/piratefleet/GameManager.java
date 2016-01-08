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

    boolean gameOver;

    public GameManager() {
        // setup human
        humanPlayer = new Player();
        humanPlayer.addMovesRemaining(1);
        humanGrid = new Grid(new Vector2(Constants.WORLD_SIZE.x / 4, Constants.WORLD_SIZE.y * 0.55f));
        generateObjects(humanGrid, true);
        // setup com
        comPlayer = new Player();
        comGrid = new Grid(new Vector2(Constants.WORLD_SIZE.x * 3 / 4, Constants.WORLD_SIZE.y * 0.55f));
        generateObjects(comGrid, Constants.SHOW_COMPUTER_OBJECTS);
        // remaining setup
        gameOver = false;
    }

    public void render(float delta, ShapeRenderer renderer) {
        humanGrid.render(delta, renderer);
        comGrid.render(delta, renderer);
    }

    public Array<GridObject> generateObjects(Grid grid, boolean revealed) {
        Array<GridObject> objects = new Array<GridObject>();
        // generate ships
        for (Constants.ShipSize size : Constants.ShipSize.values()) {
            for (int i = 0; i < size.numRequired; ++i) {
                int length = size.length;
                Cell origin = grid.randomCell();
                Ship.Orientation orientation = MathUtils.random(0, 1) == 0 ? Ship.Orientation.VERTICAL : Ship.Orientation.HORIZONTAL;
                Ship ship = new Ship(grid, origin.getRow(), origin.getColumn(), length, orientation, revealed);
                while (!validShip(ship)) {
                    origin = grid.randomCell();
                    orientation = MathUtils.random(0, 1) == 0 ? Ship.Orientation.VERTICAL : Ship.Orientation.HORIZONTAL;
                    ship = new Ship(grid, origin.getRow(), origin.getColumn(), length, orientation, revealed);
                }
                Gdx.app.log(TAG, ship.toString());
                grid.addObject(ship);
            }
        }
        // generate mines
        for (int i = 0; i < Constants.MINE_LIMIT; i++) {
            Cell origin = grid.randomCell();
            while (grid.cellAtLocation(origin.getRow(), origin.getColumn()).getObject() != null) {
                origin = grid.randomCell();
            }
            Mine mine = new Mine(grid, origin.getRow(), origin.getColumn(), revealed);
            Gdx.app.log(TAG, mine.toString());
            grid.addObject(mine);
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
        for (Cell cell : ship.locations()) {
            if (cell.getObject() != null) {
                return false;
            }
        }
        return true;
    }

    public void handleTouch(Vector2 worldTouch) {
        if (gameOver == false) {

            if (comPlayer.getMovesRemaining() == 0) {

                // human make moves
                if (comGrid.touchInGrid(worldTouch)) {
                    Cell targetCell = comGrid.cellAtTouch(worldTouch);
                    if (targetCell.getState() == Cell.CellState.UNTOUCHED) {
                        GridObject hitObject = humanPlayer.makeMove(comGrid, targetCell);
                        if (hitObject != null) {
                            if (checkGameOver(comGrid)) {
                                gameOver = true;
                                Gdx.app.log(TAG, "game over!");
                            }
                            if (hitObject.getClass() == Mine.class) {
                                comPlayer.addMovesRemaining(1);
                            }
                        }
                    }
                }

                // com make moves
                if (humanPlayer.getMovesRemaining() == 0) {
                    comPlayer.addMovesRemaining(1);

                    while (comPlayer.getMovesRemaining() > 0) {
                        GridObject hitObject = comPlayer.makeRandomMove(humanGrid);
                        if (hitObject != null) {
                            if (checkGameOver(humanGrid)) {
                                gameOver = true;
                                Gdx.app.log(TAG, "game over!");
                            }
                            if (hitObject.getClass() == Mine.class) {
                                humanPlayer.addMovesRemaining(1);
                            }
                        }
                    }

                    humanPlayer.addMovesRemaining(1);
                }
            }
        }

    }

    public boolean checkGameOver(Grid grid) {
        boolean result = true;
        for (GridObject object : grid.getObjects()) {
            if (object.getClass() == Ship.class && object.allLocationsHit() == false) {
                return false;
            }
        }
        return result;
    }
}
