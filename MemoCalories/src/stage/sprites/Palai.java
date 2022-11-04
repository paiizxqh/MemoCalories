package stage.sprites;

import java.awt.Graphics;

import control.Files;
import equipments.SpriteDoAble;
import equipments.StageObject;

public class Palai extends StageObject implements SpriteDoAble {
	public double x = 0;
	public double y = 0;
	public boolean isClicked = false;

	public Palai(String name) {
		super(name);
	}

	public final void setup() {
		try {
			Files.addImage("palai1", "palai1.png");
			Files.addImage("palai2", "palai2.png");
		} catch (Exception e) {
			;
		}
	}
	
	public final void tick() {
		;
	}
	
	public final void render(Graphics g) {
		try {
			if (isClicked)
				Files.paintImage("palai2", g, (int) x, (int) y);
			else
				Files.paintImage("palai1", g, (int) x, (int) y);
		} catch (Exception e) {
			;
		}
	}
}
