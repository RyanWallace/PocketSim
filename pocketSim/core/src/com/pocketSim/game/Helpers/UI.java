package com.pocketSim.game.Helpers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class UI extends BitmapFont {

	private Batch batch;
	private OrthogonalTiledMapRenderer renderer;
	
	public UI(OrthogonalTiledMapRenderer renderer) {
		this.renderer = renderer;
		this.batch = renderer.getBatch();
	}
	
	public void uiAddText(String uiTemplateName, String uiTemplateValue, float x, float y) {
		super.draw(batch, uiTemplateName + " : " + uiTemplateValue, x, y);
	}
	
	public void uiAddPlayerTouchText(String uiTemplateName, String uiTemplateValue, float x, float y) {
		batch.begin();
		super.draw(batch, uiTemplateName + " : " + uiTemplateValue, x, y);
		batch.end();
	}

	public void uiAddText(String uiTemplateName, int uiTemplateValue, float x, float y) {
		super.draw(batch, uiTemplateName + " : " + uiTemplateValue, x, y);
	}
	
	public void uiAddText(String uiTemplateName, float uiTemplateValue, float x, float y) {
		super.draw(batch, uiTemplateName + " , " + uiTemplateValue, x, y);
	}
	
}
