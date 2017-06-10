package com.pocketSim.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.pocketSim.game.Helpers.DayCycle;
import com.pocketSim.game.Helpers.UI;
import com.pocketSim.game.Maps.Redtown.Redtown;
import com.pocketSim.game.Sprites.Civilian;
import com.pocketSim.game.Sprites.Player;
import com.pocketSim.game.Utils.DebuggerUtil;

import java.util.Random;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;
import static com.pocketSim.game.Constants.Assets.BLUE_PLAYER;
import static com.pocketSim.game.Constants.Assets.GREEN_PLAYER;
import static com.pocketSim.game.Constants.Assets.RED_PLAYER;

class RenderResources implements Screen {


    private OrthogonalTiledMapRenderer renderer;
    private static OrthographicCamera camera;
    private Redtown redtown;
    private UI ui;
    private Player player;
    private Civilian blueCivilian;
    private Civilian greenCivilian;
    private DayCycle dayCycle;
    private int i = 0;

    @Override
    public void show() {
        TextureAtlas redPlayerAtlas = Player.setPlayerAtlas(RED_PLAYER.localName());
        TextureAtlas bluePlayerAtlas = new TextureAtlas(BLUE_PLAYER.localName());
        TextureAtlas greenPlayerAtlas = new TextureAtlas(GREEN_PLAYER.localName());

        redtown = new Redtown();
        camera = new OrthographicCamera();
        TiledMap map = redtown.getMap();

        dayCycle = new DayCycle();

        Animation redStill = new Animation(1 / 9f, redPlayerAtlas.findRegions("still"));
        Animation blueStill = new Animation(1 / 9f, bluePlayerAtlas.findRegions("still"));
        Animation greenStill = new Animation(1 / 9f, greenPlayerAtlas.findRegions("still"));

        redStill.setPlayMode(LOOP);
        blueStill.setPlayMode(LOOP);
        greenStill.setPlayMode(LOOP);

        player = new Player(redStill, map);
        player.setCamera(camera);

        greenCivilian = new Civilian(greenStill, map, greenPlayerAtlas);
        greenCivilian.setPosition(559, 381);
        blueCivilian = new Civilian(blueStill, map, bluePlayerAtlas);
        blueCivilian.setPosition(639, 605);


        player.setPosition(640, 604);

        renderer = new OrthogonalTiledMapRenderer(map);

        ui = new UI(renderer);
        ui.setColor(RED);
        ui.getData().setScale(0.6f, 0.6f);

        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void render(float delta) {

        updateNPCsPosition();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();

        renderer.setView(camera);
        player.setCamera(camera);

        if (!DayCycle.isPaused) {
            renderer = dayCycle.run(renderer, player);
        }

        player = (Player) dayCycle.getTintedSprite();

        redtown.render(renderer);

        renderer.getBatch().begin();

        player.draw(renderer.getBatch());
        blueCivilian.draw(renderer.getBatch());
        greenCivilian.draw(renderer.getBatch());

        renderer.getBatch().end();

        redtown.renderOverSpriteLayers(renderer);

        renderer.getBatch().begin();
//          Needed for when resize screen ui is still stuck to camera position
//        ui.uiAdd("Time", dayCycle.getMinute(), camera.position.x - 270, camera.position.y + 140);
        ui.uiAddText("Time", dayCycle.getHour() + " : " + dayCycle.getMinute(), camera.position.x + 75, camera.position.y + 95);
        ui.uiAddText("Position", player.getX() + ", " + player.getY(), camera.position.x - 125, camera.position.y + 95);
        ui.uiAddText("KeyPresses", Player.keyPresses.size(), camera.position.x, camera.position.y + 95);

        ui.uiAddText("", dayCycle.getCurrentShade().r * 255, camera.position.x - 125, camera.position.y - 50);
        ui.uiAddText("", dayCycle.getCurrentShade().g * 255, camera.position.x - 25, camera.position.y - 50);
        ui.uiAddText("", dayCycle.getCurrentShade().b * 255, camera.position.x + 75, camera.position.y - 50);
        ui.uiAddText("Player mouse Touch: (", player.mouseTouch.x + ", " + player.mouseTouch.y + ")", camera.position.x - 125, camera.position.y + 50);

        if (camera.zoom < 1) {
            ui.uiAddText("Time", dayCycle.getHour() + " : " + dayCycle.getMinute(), camera.position.x + 75, camera.position.y);
            ui.uiAddText("Position", player.getX() + ", " + player.getY(), camera.position.x - 125, camera.position.y);
            ui.uiAddText("KeyPresses", Player.keyPresses.size(), camera.position.x, camera.position.y);
        }

        renderer.getBatch().end();


        DebuggerUtil.showPlayerLines(camera, player);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 2.5f;
        camera.viewportHeight = height / 2.5f;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void updateNPCsPosition() {
        Random random = new Random();
        int n = random.nextInt(9) + 1;
        moveNPC(n);
    }

    private boolean moveNPC(int n) {
        String playerName;
        Civilian civilian;
        if (i != 0) {
            i++;
            if (i == 100) {
                i = 0;
            }
            return false;
        }

        if (n > 4) {
            playerName = "BlueNPC";
            civilian = blueCivilian;
        } else {
            playerName = "GreenNPC";
            civilian = greenCivilian;
        }

        playerName += " : " + n;
        switch (n) {
            case 1:
                civilian.keyTyped('w');
                civilian.keyUp(n);
                System.err.println(playerName + " Moves " + 'w');
                break;
            case 2:
                civilian.keyTyped('d');
                civilian.keyUp(n);
                System.err.println(playerName + " Moves " + 'd');
                break;
            case 3:
                civilian.keyTyped('s');
                civilian.keyUp(n);
                System.err.println(playerName + " Moves " + 's');
                break;
            case 4:
                civilian.keyTyped('a');
                civilian.keyUp(n);
                System.err.println(playerName + " Moves " + 'a');
                break;
            case 5:
                civilian.keyTyped('a');
                civilian.keyUp(n);
                System.err.println(playerName + " Moves " + 'w');
                break;
            case 6:
                civilian.keyTyped('s');
                System.err.println(playerName + " Moves " + 'd');
                break;
            case 7:
                civilian.keyTyped('w');
                civilian.keyUp(n);
                System.err.println(playerName + " Moves " + 's');
                break;
            case 8:
                civilian.keyTyped('d');
                civilian.keyUp(n);
                System.err.println(playerName + " Moves " + 'a');
                break;
        }
        i++;

        return true;
    }
}

