package stage.scripts;

import java.awt.Color;

import control.Files;
import control.Handlers;
import control.Painters;
import control.Sensor;
import equipments.ScriptDoAble;
import equipments.StageObject;
import equipments.gadgets.backgrounds.MovableBackground;
import equipments.gadgets.scripts.Frame;
import equipments.gadgets.sprites.Text;
import stage.sprites.Palai;
import stage.sprites.Square;

public class MainScript extends StageObject implements ScriptDoAble {
	MovableBackground movableBackground;
	Text text;
	Frame frame;
	Palai palai;
	Square s1, s2, s3, s4, s5;

	public MainScript(String name) {
		super(name);
	}

	public void setup() {
		try {
			Files.addImage("movableBackground", "movableBackground.png");
			
			movableBackground = new MovableBackground("movableBackground");
			movableBackground.setImage("movableBackground");
			Handlers.addBackground(movableBackground);
			Handlers.showBackground("movableBackground");
			
			text = new Text("info");
			Handlers.addSprite(text);
			text.x = 50;
			text.y = HEIGHT - 200;
			text.setSize(3);
			
			palai = (Palai) Handlers.addSprite(new Palai("palai_sprite"));
			
			Handlers.swapSprite("palai_sprite", "info");
			
			frame = new Frame("mouseFrame");
			Handlers.addSprite(frame);
			frame.x = 0;
			frame.y = 0;
			frame.setWidth(500);
			frame.setHeight(500);

			frame.enable();
			frame.setDraggable(true);
			frame.setDirection(Frame.Direction.VERTICAL);
			frame.setScrollable(true);

			s1 = new Square("s1_sprite");
			s1.color = Color.MAGENTA;
			Handlers.addSprite(s1);
			
			s2 = new Square("s2_sprite");
			s2.color = Color.CYAN;
			Handlers.addSprite(s2);
			
			s3 = new Square("s3_sprite");
			s3.color = Color.GREEN;
			Handlers.addSprite(s3);
			
			s4 = new Square("s4_sprite");
			s4.color = Color.YELLOW;
			Handlers.addSprite(s4);
			
			s5 = new Square("s5_sprite");
			s5.color = Color.RED;
			Handlers.addSprite(s5);
			
			
			palai.ticking = false;
			palai.rendering = false;
			
			frame.ticking = false;
			frame.rendering = false;
			
			s1.ticking = false;
			s1.rendering = false;
			s2.ticking = false;
			s2.rendering = false;
			s3.ticking = false;
			s3.rendering = false;
			s4.ticking = false;
			s4.rendering = false;
			s5.ticking = false;
			s5.rendering = false;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		try {
			if (frame.isTouched())
				if (frame.isStuck())
					text.setColor(Color.RED);
				else	
					text.setColor(Color.YELLOW);	
			else
				text.setColor(Color.GREEN);
			
			text.appending
				.start()
				.append("FPS : " + Painters.getFps() + "\n")
				.append("x : " + Sensor.getPointerX() + "\n")
				.append("y : " + Sensor.getPointerY() + "\n");
			
			palai.x = frame.x;
			palai.y = frame.y;
			palai.isClicked = frame.isStuck();
			
			s1.x = 490 + 10 * Handlers.getSpriteLayer("s1_sprite");
			s1.y = 490 + 10 * Handlers.getSpriteLayer("s1_sprite");
			
			s2.x = 490 + 10 * Handlers.getSpriteLayer("s2_sprite");
			s2.y = 490 + 10 * Handlers.getSpriteLayer("s2_sprite");
			
			s3.x = 490 + 10 * Handlers.getSpriteLayer("s3_sprite");
			s3.y = 490 + 10 * Handlers.getSpriteLayer("s3_sprite");
			
			s4.x = 490 + 10 * Handlers.getSpriteLayer("s4_sprite");
			s4.y = 490 + 10 * Handlers.getSpriteLayer("s4_sprite");
			
			s5.x = 490 + 10 * Handlers.getSpriteLayer("s5_sprite");
			s5.y = 490 + 10 * Handlers.getSpriteLayer("s5_sprite");
			
			Handlers.spriteSorting
			.start("s5_sprite", 2)
			.sort("s4_sprite")
			.sort("s3_sprite")
			.sort("s2_sprite")
			.sort("s1_sprite");
			
			if (Sensor.detect("LeftClick"))
				text.appending.append("Left Clicking");
			movableBackground.y += 1;
			movableBackground.x += 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
