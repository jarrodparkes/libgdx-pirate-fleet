package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.piratefleet.Constants;

public class Cell {

    public static final String TAG = Cell.class.getName();

    GridObject object;

    public Cell() {
        this.object = null;
    }

    public Cell(GridObject object) {
        this.object = object;
    }

    public void render(float delta, ShapeRenderer renderer, Vector2 position) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Constants.GRID_CELL_COLOR);
        renderer.rect(position.x, position.y, Constants.GRID_CELL_SIZE, Constants.GRID_CELL_SIZE);
        renderer.end();

        if (object != null) {
            object.render(delta, renderer, position);
        }
//            renderer.setColor(Color.GREEN);
//            renderer.rect(position.x + Constants.GRID_CELL_SIZE/4, position.y + Constants.GRID_CELL_SIZE/4, Constants.GRID_CELL_SIZE/2, Constants.GRID_CELL_SIZE/2);
//        } else {
//            renderer.setColor(Color.RED);
//            renderer.rect(position.x + Constants.GRID_CELL_SIZE/4, position.y + Constants.GRID_CELL_SIZE/4, Constants.GRID_CELL_SIZE/2, Constants.GRID_CELL_SIZE/2);
//        }
        renderer.setColor(Color.WHITE);
    }
}
