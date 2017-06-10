package com.pocketSim.game.Utils;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.pocketSim.game.Sprites.Player;

public class DebuggerUtil {

	
	public static boolean debugOn = false;
    private static ShapeRenderer shapeDebugger = new ShapeRenderer();
	
	public static float setCameraZoom(char character, OrthographicCamera camera) {
		if(debugOn) {
			if (character == 'z') {
				camera.zoom+=0.01;
			}
			
			if (character == 'x') {
				camera.zoom-=0.01;
			}
			
			if (character == 'c') {
				camera.zoom = 0.1000006f;
			}
			
			if (character == 'v') {
				camera.zoom = 1f;
			}			
		}
    	return camera.zoom;
	}
	
	public static Vector2 setPosition(char character, Player player) {
		if(debugOn) {
			if (character == 'i') {
				player.setPosition(player.getX() - 1, player.getY());
			}
			
			if (character == 'o') {
				player.setPosition(player.getX() + 1, player.getY());
			}
			
			if (character == 'k') {
				player.setPosition(player.getX(), player.getY()-1);
			}
			
			if (character == 'l') {
				player.setPosition(player.getX(), player.getY()+1);
			}
		}
    	return new Vector2(player.getX(), player.getY());
	}
	
	public static void printKeyPresses(ArrayList<String> keyPresses) {
		if(debugOn) {
			String keyPressesToString ="";
	        String keyPressesToString2 ="";
	        for (int i = 0; i < keyPresses.size(); i++) {

	        	keyPressesToString2+="| "+ i + " ";			        		
	        	if (i>=10) {
	        		keyPressesToString+="| "+ keyPresses.get(i) + "  ";			
	        	} else {
	        		keyPressesToString+="| "+ keyPresses.get(i) + " ";			
	        	}
			}
		        
	        keyPressesToString+="|";
	        keyPressesToString2+="|";
	        System.err.println(keyPressesToString2);
	        System.err.println(keyPressesToString);
		}
	}
	
	public static void showPlayerLines(OrthographicCamera camera, Sprite player) {
		if(debugOn) {
			 
	        Gdx.gl30.glLineWidth(2);
	        shapeDebugger.setProjectionMatrix(camera.combined);
	        shapeDebugger.begin(Line);
	        shapeDebugger.setColor(RED);
	        

	        // 0,0 lines 
	        for(float i = player.getX(); i <= (player.getX()+ 16f); i++) {
	        	shapeDebugger.line(i, player.getY(), i, player.getY() +20f);        
	        }
	        

	        for(float i = player.getY(); i <= (player.getY()+ 20f); i++) {
	        	shapeDebugger.line(player.getX(), i, player.getX() + 16f, i);        
	        }
	        
	        shapeDebugger.end();
		}
	}
	
}
