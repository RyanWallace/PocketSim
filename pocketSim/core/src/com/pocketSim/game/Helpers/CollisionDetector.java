package com.pocketSim.game.Helpers;

import static com.pocketSim.game.Constants.TileCells.BLOCKED;
import static com.pocketSim.game.Constants.TileLayers.BUILDINGS;
import static com.pocketSim.game.Constants.TileLayers.CLIFFS;
import static com.pocketSim.game.Constants.TileLayers.CONFECTIONERY;
import static com.pocketSim.game.Constants.TileLayers.TREES;
import static com.pocketSim.game.Constants.TileLayers.WATER;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.pocketSim.game.Constants.TileCells;
import com.pocketSim.game.Constants.TileLayers;
import com.pocketSim.game.Sprites.Civilian;
import com.pocketSim.game.Sprites.Player;

public class CollisionDetector {

	private Sprite player;
	private TiledMap map;

	public CollisionDetector(TiledMap m, Civilian p) {
		map = m;
		player = p;
	}

	public CollisionDetector(TiledMap m, Player p) {
		map = m;
		player = p;
	}

	public boolean isCellBlocked(float x, float y) {

		boolean isBlocked = false;

		if (checkCollisionLayerForBlockedTile(x, y, (TiledMapTileLayer) map.getLayers().get(BUILDINGS.localName()))) {
			isBlocked = true;
		} else if (checkCollisionLayerForBlockedTile(x, y,
				(TiledMapTileLayer) map.getLayers().get(TREES.localName()))) {
			isBlocked = true;
		} else if (checkCollisionLayerForBlockedTile(x, y,
				(TiledMapTileLayer) map.getLayers().get(CONFECTIONERY.localName()))) {
			isBlocked = true;
		} else if (checkCollisionForBlockedLayer(x, y,
				(TiledMapTileLayer) map.getLayers().get(WATER.localName()))) {
			isBlocked = true;
		} else if (checkCollisionForBlockedLayer(x, y,
				(TiledMapTileLayer) map.getLayers().get(CLIFFS.localName()))) {
			isBlocked = true;
		}

		return isBlocked;
	}

	private boolean checkCollisionLayerForBlockedTile(float x, float y, TiledMapTileLayer layer) {
		TiledMapTileLayer.Cell cell = layer.getCell((int) (x / layer.getTileWidth()),
				(int) (y / layer.getTileHeight()));
		return cell != null && cell.getTile() != null
				&& cell.getTile().getProperties().containsKey(BLOCKED.localName());
	}

	private boolean checkCollisionForBlockedLayer(float x, float y, TiledMapTileLayer layer) {
		TiledMapTileLayer.Cell cell = layer.getCell((int) (x / layer.getTileWidth()),
				(int) (y / layer.getTileHeight()));
		return cell != null && cell.getTile() != null;
	}

	public boolean detectCollision(char keyCode) {
		boolean collisionDetected = false;

		switch (keyCode) {

		case 'w':
			collisionDetected = isCellBlockedUp();
			break;

		case 'a':
			collisionDetected = isCellBlockedLeft();
			break;

		case 's':
			collisionDetected = isCellBlockedDown();
			break;

		case 'd':
			collisionDetected = isCellBlockedRight();
			break;
		}

		return collisionDetected;
	}

	private boolean isCellBlockedLeft() {

		boolean collision = false;
		
		if (isCellBlocked(player.getX() -1f, player.getY()+4f + 8f)) {
			collision = true;
		} 

		return collision;

	}

	private boolean isCellBlockedRight() {
		
		boolean collision = false;

		if (isCellBlocked(player.getX() + 16f, player.getY() +4f + 8f)) {
			collision = true;
		} 

		return collision;

	}

	private boolean isCellBlockedUp() {
		boolean collision = false;
		if (isCellBlocked(player.getX() + 8f, player.getY() + 20f)) {
			collision = true;
		}		
		
		return collision;
	}

	private boolean isCellBlockedDown() {
		boolean collision = false;

		if (isCellBlocked(player.getX() + 8f, player.getY() +3f)) {
			collision = true;
		}
		return collision;
	}

}
