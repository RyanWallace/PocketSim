package com.pocketSim.game.Sprites;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.pocketSim.game.Helpers.CollisionDetector;
import com.pocketSim.game.Maps.Redtown.Redtown;
import com.pocketSim.game.Utils.DebuggerUtil;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

public class Player extends Sprite implements InputProcessor{

	private static final String KEY_UP_BREAK_CHAR = " ";
	private static final float ANIMATION_DELTA = 0.009f;
	private static final float PLAYER_POSITION_DELTA = 0.01f;
	private static final float PLAYER_SPEED = 100f;

    private static TextureAtlas playerAtlas;
    private float animationTime = 0;
    private static Animation still, up, down, left ,right;
    private CollisionDetector collisionDetector;
    private char keyPressed, keyPressedCap;
    public static ArrayList<String> keyPresses = new ArrayList<>();
    private Animation facing;
    private OrthographicCamera camera;

    public Vector2 mouseTouch = new Vector2(0, 0);


    public Player(Animation initialStanceAnimation, TiledMap m) {
        super(initialStanceAnimation.getKeyFrame(0));
        facing = still = initialStanceAnimation;
        renderAnimation();
        collisionDetector = new CollisionDetector(m, this);

    }

    public void setCamera(OrthographicCamera camera) {
    	this.camera = camera;
    }
    
    public static TextureAtlas setPlayerAtlas(String textureAtlas) {
        return playerAtlas = new TextureAtlas(textureAtlas);
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("Key Held");
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyPresses.add(KEY_UP_BREAK_CHAR);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
    	
    	camera.zoom = DebuggerUtil.setCameraZoom(character, camera);
    	Vector2 position = DebuggerUtil.setPosition(character, this);
    	setPosition(position.x, position.y);
    	
    	
    	String stringCharacter = "" + character;
    	
    	if (checkCollision(character)) {
//    		DebuggerUtil.printKeyPresses(keyPresses);
    		keyPresses.clear();
    		
    		return false;
    	}

//		DebuggerUtil.printKeyPresses(keyPresses);

        boolean keyTyped = false;
        System.out.println("Key Typed: " + character);

       if (!KEY_UP_BREAK_CHAR.equals(stringCharacter)) {
    	   keyPressed = character;
       } else {
    	   keyPressedCap=' ';
    	   return false;
       }
        
        switch (character) {

            case 'w' :
            	keyPressedCap = 'W';
               setPlayerVelocity();
                System.out.println("W Typed");
                keyTyped = true;
                break;

            case 'a' :

            	keyPressedCap = 'A';
               setPlayerVelocity();
                System.out.println("A Typed");
                keyTyped = true;
                break;

            case 's' :


            	keyPressedCap = 'S';
               setPlayerVelocity();
                System.out.println("S Typed");
                keyTyped = true;
                break;

            case 'd' :

            	keyPressedCap = 'D';
               setPlayerVelocity();
                System.out.println("D Typed");
                keyTyped = true;
                break;
        }
        return keyTyped;
    }

	private boolean checkCollision(char character) {
		return (collisionDetector.detectCollision(character));
	}


    @Override
    public void draw(Batch batch) {
    	updateAnimation();
    	updatePlayerPosition();
        super.draw(batch);
    }

    private void setAnimation(Animation animation) {
    	facing = animation;
    }
    
	private void updateAnimation() {
		animationTime += ANIMATION_DELTA;
        
		setRegion(facing.getKeyFrame(animationTime));
	}

    private boolean updatePlayerPosition() {
    	if (!keyPresses.isEmpty()) {
    		String keypress = keyPresses.get(0);
    		boolean setAnimation = false;
    		
    		if (keypress.contains("_")) {
    			keypress = keypress.substring(1);
    			setAnimation = true;
    		}
    		if(!KEY_UP_BREAK_CHAR.equals(keypress)) {
    			
    			String currentKeyPress = keypress.toLowerCase();
    			
    			switch (currentKeyPress) {
    			case "w" :
    				
    				if (checkCollision('w')) {
    					return removeKeyPress(currentKeyPress);
    				}

                    float incrementYUp = PLAYER_SPEED * PLAYER_POSITION_DELTA;
                    
                    float newYPositionUp = getY() + incrementYUp;

            		if (newYPositionUp > 14 && newYPositionUp < Redtown.getMapBounds().y) {
            		    setY(newYPositionUp);
            		}
                    
                    if (setAnimation) {
                    	setAnimation(up);
                    }
    				
                    break;

                case "a" :
                	
                	if (checkCollision('a')) {
    					return removeKeyPress(currentKeyPress);
    				}
                	
            		float incrementXLeft = -PLAYER_SPEED * PLAYER_POSITION_DELTA;

                    float newXPositionLeft = getX() + incrementXLeft;

                    if (newXPositionLeft > 14 && newXPositionLeft < Redtown.getMapBounds().x) {
                        setX(newXPositionLeft);
                    }
                    
                    if (setAnimation) {
                    	setAnimation(left);
                    }
                    break;

                case "s" :
                	
                	if (checkCollision('s')) {
    					return removeKeyPress(currentKeyPress);
    				}
                	
                    float incrementYDown = -PLAYER_SPEED * PLAYER_POSITION_DELTA;
                    
                    float newYDownPosition = getY() + incrementYDown;
                    if (newYDownPosition > 14 && newYDownPosition < Redtown.getMapBounds().y) {
                        setY(newYDownPosition);
                    }
                    if (setAnimation) {
                    	setAnimation(down);
                    }
                	
                    break;

                case "d" :
                	
                	if (checkCollision('d')) {
    					return removeKeyPress(currentKeyPress);
    				}
                	
            		float incrementXRight = PLAYER_SPEED * PLAYER_POSITION_DELTA;

                    float newXPositionRight = getX() + incrementXRight;

                    if (newXPositionRight > 14 && newXPositionRight < Redtown.getMapBounds().x) {
                        setX(newXPositionRight);
                    }
                    if (setAnimation) {
                    	setAnimation(right);
                    }
                    break;
            }

    		} else {
    			setAnimation(still);
    		}
    		if(!keyPresses.isEmpty()) {
    			keyPresses.remove(keyPresses.get(0));    			
    		}
    	}
    	return true;
    }
    
	private boolean removeKeyPress(String keyPress) {

		for (int i = 0; i < keyPresses.size();) {
			if (keyPresses.size()>2 && keyPress.equalsIgnoreCase(keyPresses.get(1))) {
				keyPresses.remove(i);
			} else if (keyPresses.size()==2){
				return true;
			} else {
				keyPresses.remove(i);
				return true;
			}
		}
		return false;
	}

    private void setPlayerVelocity() {
    	if(keyPresses.size() < 10) {
    		for(int i=0; i<16; i++) {
    			if (i==0) {
    				if(checkCollision(keyPressed)) {
    					break;
    				} else {    					
    					keyPresses.add("_" + keyPressed);
    				}
    			} else if(i == 15) {
    				keyPresses.add("" + keyPressedCap);    
    			} else {
    				keyPresses.add("" + keyPressed);
    			}
    		}    		
    	}
    	
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (new Vector2(screenX, screenY).equals(mouseTouch)) {
            return false;
        }
        mouseTouch.set(screenX, screenY);

        if (mouseTouch.x < 600 && mouseTouch.y < 800) {
            return keyTyped('a');
        } else if (mouseTouch.x > 1500 && mouseTouch.y < 800) {
            return keyTyped('d');
        } else if (mouseTouch.x < 600 && mouseTouch.y > 800) {
            return keyTyped('s');
        } else if (mouseTouch.x > 1500 && mouseTouch.y > 800) {
            return keyTyped('w');
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    private void renderAnimation() {
        up = new Animation(1 / 15f, playerAtlas.findRegions("up"));
        down = new Animation(1 / 15f, playerAtlas.findRegions("down"));
        left = new Animation(1 / 15f, playerAtlas.findRegions("left"));
        right = new Animation(1 / 15f, playerAtlas.findRegions("right"));

        up.setPlayMode(LOOP);
        down.setPlayMode(LOOP);
        left.setPlayMode(LOOP);
        right.setPlayMode(LOOP);
    }
}
