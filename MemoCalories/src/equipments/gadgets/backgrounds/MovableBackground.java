package equipments.gadgets.backgrounds;

import java.awt.Graphics;

import control.Files;
import equipments.BackgroundDoAble;
import equipments.StageObject;

public class MovableBackground extends StageObject implements BackgroundDoAble {

	public double x = 0;
	public double y = 0;
	private String image = "";

	public MovableBackground(String name) {
		super(name);
	}

	public final void setImage(final String image) {
		this.image = image;
	}

	public final String getImage() {
		return image;
	}

	public final void setup() {
		;
	}

	public final void tick() {
		if (x < 0)
			x = WIDTH;
		if (x > WIDTH)
			x = 0;
		if (y < 0)
			y = HEIGHT;
		if (y > HEIGHT)
			y = 0;
	}

	public final void render(Graphics g) {
		if (image.compareTo("") == 0)
			return;
		try {
			Files.paintImage(image, g, (int) x, (int) y);
			if (y > 0) {
				if (x > 0) {
					Files.paintImage(image, g, (int) x - WIDTH, (int) y - HEIGHT);
					Files.paintImage(image, g, (int) x - WIDTH, (int) y);
				}
				Files.paintImage(image, g, (int) x, (int) y - HEIGHT);	
			}
			else if (x > 0)
				Files.paintImage(image, g, (int) x - WIDTH, (int) y);
		} catch (Exception e) {
			
		}
	}
}
