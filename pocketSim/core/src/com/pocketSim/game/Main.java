package com.pocketSim.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pocketSim.game.Helpers.DayCycle;
import com.pocketSim.game.Utils.DebuggerUtil;

public class Main extends Game {
	public static boolean isAndroidApp = false;

	@Override
	public void create () {
		setScreen(new RenderResources());
		DebuggerUtil.debugOn = false;
	}

	@Override
	public void render () {
		super.render();

		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			try {
				this.setScreen(getScreen().getClass().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			if(DayCycle.isPaused) {
				DayCycle.isPaused = false;
			}else {
				DayCycle.isPaused = true;
			}
		}

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

}
