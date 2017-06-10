package com.pocketSim.game.Helpers;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import org.joda.time.DateTime;


public class DayCycle {

    private static final Color AM9 = new Color((0xffffa8ff));
    private static final Color PM9 = new Color((0x8aa2ffff));
    private static final Color PM3 = new Color((0xffae80ff));
    private static final Color AM3 = new Color(1, 0, 1, 1);

    private static final Color PM6 = new Color(0xfa8072ff);
    private static final Color AM6 = new Color(0xff69b4ff);

    private static final Color AM12 = new Color(0xa020f0ff);
    private static final Color PM12 = new Color(0xffffffff);
    private DateTime time = new DateTime();
	private int minute, hour;
	private Sprite sprite;
	public static boolean isPaused = false;
	private Color currentShade = PM12;

	public DayCycle() {
		minute = 0;
		hour = 12;
	}

	public OrthogonalTiledMapRenderer run(OrthogonalTiledMapRenderer renderer, Sprite player) {
		sprite = player;
		if ((new DateTime().getMillis() - time.getMillis()) >= 100) {

			tick();
			setShade();

			renderer.getBatch().setColor(currentShade);
			sprite.setColor(currentShade);
			time = new DateTime();

		}

		return renderer;
	}

	private void setShade() {
		float r, g, b;
		float rInc, gInc, bInc;

		if (hour < 3) {
			if (hour == 0 && minute == 0) {
				currentShade = AM12;
			}

			rInc = 95f / 179f;
			gInc = -32f / 179f;
			bInc = 15f / 179f;
			r = currentShade.r * 255;
			g = currentShade.g * 255;
			b = currentShade.b * 255;
			if (checkShadeSet(r, g, b, rInc, gInc, bInc)) {
				r += rInc;
				g += gInc;
				b += bInc;
				currentShade.r = r / 255;
				currentShade.g = g / 255;
				currentShade.b = b / 255;
			}

		}

		if (hour >= 3 && hour < 6) {
			if (hour == 3 && minute == 0) {
				currentShade = AM3;
			}
			rInc = 0f / 179f;
			gInc = 105f / 179f;
			bInc = -75f / 179f;
			r = currentShade.r * 255;
			g = currentShade.g * 255;
			b = currentShade.b * 255;

			if (checkShadeSet(r, g, b, rInc, gInc, bInc)) {
				r += rInc;
				g += gInc;
				b += bInc;
				currentShade.r = r / 255;
				currentShade.g = g / 255;
				currentShade.b = b / 255;
			}
		}

		if (hour >= 6 && hour < 9) {
			if (hour == 6 && minute == 0) {
				currentShade = AM6;
			}
			rInc = 0f / 179f;
			gInc = 150f / 179f;
			bInc = -12f / 179f;
			r = currentShade.r * 255;
			g = currentShade.g * 255;
			b = currentShade.b * 255;

			if (checkShadeSet(r, g, b, rInc, gInc, bInc)) {
				r += rInc;
				g += gInc;
				b += bInc;
				currentShade.r = r / 255;
				currentShade.g = g / 255;
				currentShade.b = b / 255;
			}
		}

		if (hour >= 9 && hour < 12) {
			if (hour == 9 && minute == 0) {
				currentShade = AM9;
			}
			rInc = 0f / 179f;
			gInc = 0f / 179f;
			bInc = 87f / 179f;
			r = currentShade.r * 255;
			g = currentShade.g * 255;
			b = currentShade.b * 255;

			if (checkShadeSet(r, g, b, rInc, gInc, bInc)) {
				r += rInc;
				g += gInc;
				b += bInc;
				currentShade.r = r / 255;
				currentShade.g = g / 255;
				currentShade.b = b / 255;
			}
		}

		if (hour >= 12 && hour < 15) {
			if (hour == 12 && minute == 0) {
				currentShade = PM12;
			}
			rInc = 0f / 179f;
			gInc = -81f / 179f;
			bInc = -127f / 179f;
			r = currentShade.r * 255;
			g = currentShade.g * 255;
			b = currentShade.b * 255;

			if (checkShadeSet(r, g, b, rInc, gInc, bInc)) {
				r += rInc;
				g += gInc;
				b += bInc;
				currentShade.r = r / 255;
				currentShade.g = g / 255;
				currentShade.b = b / 255;
			}
		}

		if (hour >= 15 && hour < 18) {
			if (hour == 15 && minute == 0) {
				currentShade = PM3;
			}
			rInc = -5f / 179f;
			gInc = -54f / 179f;
			bInc = -14f / 179f;
			r = currentShade.r * 255;
			g = currentShade.g * 255;
			b = currentShade.b * 255;

			if (checkShadeSet(r, g, b, rInc, gInc, bInc)) {
				r += rInc;
				g += gInc;
				b += bInc;
				currentShade.r = r / 255;
				currentShade.g = g / 255;
				currentShade.b = b / 255;
			}
		}

		if (hour >= 18 && hour < 21) {
			if (hour == 18 && minute == 0) {
				currentShade = PM6;
			}
			rInc = -112f / 179f;
			gInc = 34f / 179f;
			bInc = 141f / 179f;
			r = currentShade.r * 255;
			g = currentShade.g * 255;
			b = currentShade.b * 255;

			if (checkShadeSet(r, g, b, rInc, gInc, bInc)) {
				r += rInc;
				g += gInc;
				b += bInc;
				currentShade.r = r / 255;
				currentShade.g = g / 255;
				currentShade.b = b / 255;
			}
		}

		if (hour >= 21 && hour < 24) {
			if (hour == 21 && minute == 0) {
				currentShade = PM9;
			}
			rInc = 22f / 179f;
			gInc = -130f / 179f;
			bInc = -15f / 179f;
			r = currentShade.r * 255;
			g = currentShade.g * 255;
			b = currentShade.b * 255;

			if (checkShadeSet(r, g, b, rInc, gInc, bInc)) {
				r += rInc;
				g += gInc;
				b += bInc;
				currentShade.r = r / 255;
				currentShade.g = g / 255;
				currentShade.b = b / 255;
			}
		}
	}

	private boolean checkShadeSet(float r, float g, float b, float rInc, float gInc, float bInc) {
		boolean canSet = true;
		if (r + rInc < 0f || r + rInc > 255f) {
			canSet = false;
		}

		if (g + gInc < 0f || g + gInc > 255f) {
			canSet = false;
		}

		if (b + bInc < 0f || b + bInc > 255f) {
			canSet = false;
		}

		return canSet;
	}

	private void tick() {
		minute++;
		if (minute == 60) {
			minute = 0;
			hour++;
		}
		if (hour == 24) {
			hour = 0;
		}
	}

	public Sprite getTintedSprite() {
		return sprite;
	}

	public String getMinute() {
		if (minute < 10) {
			return "0" + minute;
		} else {
			return "" + minute;
		}
	}

	public String getHour() {
		if (hour < 10) {
			return "0" + hour;
		} else {
			return "" + hour;
		}
	}

	public Color getCurrentShade() {
		return currentShade;
	}

	 // Leave out until Saving/loading from txt file getting location, time,
	 // inventory
	// public void setMinute(int minute) {
	// this.minute = minute;
	// }
	//
	// public void setHour(int hour) {
	// this.hour = hour;
	// }
}
