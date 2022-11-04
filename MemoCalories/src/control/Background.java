package control;

import java.awt.Color;
import java.awt.Graphics;

import equipments.StageObject;

/**
 * มีหน้าที่เป็นพื้นหลัง<br>
 * ต้องขยายวัตถุด้วย<br>
 * {@link equipments.StageObject StageObject}<br>
 * และอิมพลีเมนต์ด้วย<br>
 * {@link equipments.BackgroundDoAble BackgroundDoAble}
 * 
 * @author ณัชพล จำปานิน
 */
class Background extends Objects {

	/**
	 * จะถูกโยนเมื่อตั้งชื่อ {@link control.Background Background} ว่า<br>
	 * "defaultBackground" ที่เป็นชื่อสงวนได้
	 * 
	 * @author ณัชพล จำปานิน
	 */
	static final class AddReservedBackgroundNameException extends Exception {
		private static final long serialVersionUID = 1L;

		AddReservedBackgroundNameException() {
			super("ตั้งชื่อ Background เป็น \"defaultBackground\" ซึ่งเป็นชื่อสงวน");
		}
	}

	/**
	 * จะถูกโยนเมื่อตั้งชื่อ {@link control.Background Background}
	 * ซ้ำกับที่มีมาก่อนแล้ว
	 * 
	 * @author ณัชพล จำปานิน
	 */
	static final class AddDuplicatedBackgroundNameException extends Exception {
		private static final long serialVersionUID = 1L;

		AddDuplicatedBackgroundNameException() {
			super("ตั้งชื่อ Background ซ้ำกับที่มีมาก่อนแล้ว");
		}
	}

	/**
	 * จะถูกโยนเมื่อตั้งชื่อ {@link control.Background Background} เป็น null
	 * 
	 * @author ณัชพล จำปานิน
	 */
	static final class AddNullBackgroundNameException extends Exception {
		private static final long serialVersionUID = 1L;

		AddNullBackgroundNameException() {
			super("ตั้งชื่อ Background เป็น null");
		}
	}

	Background(final StageObject stageObject, final String name) throws AddNullBackgroundNameException,
			AddReservedBackgroundNameException, AddDuplicatedBackgroundNameException {
		super(stageObject, name);
		if (name == null)
			throw new AddNullBackgroundNameException();
		else if (name.compareTo("defaultBackground") == 0)
			throw new AddReservedBackgroundNameException();
		for (Background i : Handlers.backgrounds)
			if (i.getName().compareTo(name) == 0)
				throw new AddDuplicatedBackgroundNameException();
	}

	Background() {
		super(null, "defaultBackground");
	}
	
	final void setup() {
		stageObject.setup();
	}
	
	final void tick() {
		if (stageObject.ticking)
			stageObject.tick();
	}

	final void render(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if (name.compareTo("defaultBackground") == 0)
			return;
		else if (stageObject != null && stageObject.rendering)
			stageObject.render(g);
		else
			return;
	}

	final void display() {
		Handlers.showBackground(this);
	}

}