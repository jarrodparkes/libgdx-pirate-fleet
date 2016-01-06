package com.udacity.gamedev.piratefleet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final Vector2 WORLD_SIZE = new Vector2(16f, 9f);

    public static final int GRID_SIZE = 10;
    public static final Color GRID_CELL_COLOR = Color.WHITE;
    public static final float GRID_CELL_SIZE = WORLD_SIZE.x / 32;

    public static final float HUD_FONT_REFERENCE_SCREEN_WIDTH = 480.0f;

    public static final Color BACKGROUND_COLOR = Color.BLUE;

    public static final float SETTINGS_WORLD_SIZE = 480.0f;
    public static final float SETTINGS_BUBBLE_RADIUS = SETTINGS_WORLD_SIZE / 8.5f;
    public static final Color SETTINGS_BUBBLE_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final float SETTINGS_LABEL_SCALE = 1.5f;

    public static final Vector2 EASY_CENTER = new Vector2(SETTINGS_WORLD_SIZE / 4, SETTINGS_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(SETTINGS_WORLD_SIZE / 2, SETTINGS_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(SETTINGS_WORLD_SIZE * 3 / 4, SETTINGS_WORLD_SIZE / 2);

    public static final String EASY_LABEL = "Easy";
    public static final String MEDIUM_LABEL = "Medium";
    public static final String HARD_LABEL = "Hard";

}
