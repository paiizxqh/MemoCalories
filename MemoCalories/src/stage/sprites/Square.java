package stage.sprites;

import java.awt.Color;
import java.awt.Graphics;

import equipments.SpriteDoAble;
import equipments.StageObject;

public class Square extends StageObject implements SpriteDoAble {
	public int x;
	public int y;
	public Color color = Color.white;

	public Square(String name) {
		super(name);
	}

	public final void setup() {
		;
	}
	
	public final void tick() {
		;
	}
	
	public final void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 100, 100);
	}
}
