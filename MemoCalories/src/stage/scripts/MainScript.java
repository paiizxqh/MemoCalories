package stage.scripts;

import java.awt.Color;

import control.Files;
import control.Handlers;
import control.Painters;
import control.Sensor;
import equipments.ScriptDoAble;
import equipments.StageObject;
import equipments.gadgets.backgrounds.MovableBackground;
import equipments.gadgets.sprites.Text;

public class MainScript extends StageObject implements ScriptDoAble {
	MovableBackground movableBackground;
	Text text;

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
			text.setColor(Color.GREEN);
			text.setSize(3);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		try {
			text.appending
				.start()
				.append("FPS : " + Painters.getFps() + "\n")
				.append("x : " + Sensor.getPointerX() + "\n")
				.append("y : " + Sensor.getPointerY() + "\n");
			if (Sensor.detect("LeftClick"))
				text.appending.append("Left Clicking");
			movableBackground.y += 5;
			movableBackground.x += 7;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
