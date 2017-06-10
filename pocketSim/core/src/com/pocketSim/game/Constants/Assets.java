package com.pocketSim.game.Constants;


import com.badlogic.gdx.Gdx;
import com.pocketSim.game.Main;

public enum Assets {

    RED_PLAYER("Sprites/oldRed/playerData.txt"),
    BLUE_PLAYER("Sprites/blue/playerData.txt"),
    GREEN_PLAYER("Sprites/green/playerData.txt"),
    MAP("Maps/Redtown/Map.tmx");

    private static final String desktopFolderLocation = "desktop/assets/";
    private String local;


    Assets(String local) {
        this.local = local;
    }

    public String localName() {
        if(Main.isAndroidApp) {
            return local;
        }
        else {
            return Gdx.files.internal(desktopFolderLocation.concat(local)).file().getAbsolutePath();
        }
    }

}


