package com.pocketSim.game.Constants;

public enum TileLayers {

    WATER("Water"),
    CONFECTIONERY("Confectionery"),
    BUILDINGS("Buildings"),
    TREES("Trees"),
    PATHS("Paths"),
    GRASS("Grass"),
    BACKGROUND("Background"),
    LONG_GRASS("LongGrass"),
    OVER_BUILDINGS("OverBuildings"),
    CLIFFS("Cliffs");

    private String tileLayer;

    TileLayers(String cell) {
        this.tileLayer = cell;
    }

    public String localName() {
        return tileLayer;
    }
}
