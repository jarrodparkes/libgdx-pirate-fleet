package com.udacity.gamedev.piratefleet;

import com.badlogic.gdx.Game;

public class PirateFleetGame extends Game {

    @Override
    public void create() {
        showPirateFleetScreen();
    }

    public void showSettingsScreen() {
        setScreen(new SettingsScreen(this));
    }

    public void showPirateFleetScreen() {
        setScreen(new PirateFleetScreen(this));
    }
}
