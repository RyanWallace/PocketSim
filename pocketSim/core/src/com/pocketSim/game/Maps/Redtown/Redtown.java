package com.pocketSim.game.Maps.Redtown;

import static com.pocketSim.game.Constants.Assets.MAP;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;


public class Redtown {

    private TiledMap map;
    private int[]  grass = new int[] {0}, 
    			   paths = new int[] {1},
    			   trees = new int[] {2}, 
    		   buildings = new int[] {3},
           confectionery = new int[] {4}, 
                   water = new int[] {5}, 
              background = new int[] {6},
               longGrass = new int[] {7},
                  cliffs = new int[] {8},
            overBuilding = new int[] {9};

    public Redtown() {
        setMap();
    }

    public void render(OrthogonalTiledMapRenderer renderer) {
        renderer.render(grass);
        renderer.render(paths);
        renderer.render(trees);
        renderer.render(cliffs);
        renderer.render(buildings);
        renderer.render(confectionery);
        renderer.render(water);
        renderer.render(longGrass);
        renderer.render(background);
    }
    
    public void renderOverSpriteLayers(OrthogonalTiledMapRenderer renderer) {
        renderer.render(overBuilding);
    }

    private void setMap() {
        map = new TmxMapLoader().load(MAP.localName());
    }

    public TiledMap getMap() {
        return map;
    }

    public static Vector2 getMapBounds() {
        return new Vector2(2000, 1000);
    }
}
