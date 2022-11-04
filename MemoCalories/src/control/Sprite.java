package control;

import java.awt.Graphics;

import equipments.StageObject;

/**
 * มีหน้าที่เป็นตัวละคร<br>
 * ต้องขยายวัตถุด้วย<br>
 * {@link equipments.StageObject StageObject}<br>
 * และอิมพลีเมนต์ด้วย<br>
 * {@link equipments.SpriteDoAble SpriteDoAble}
 * 
 * @author ณัชพล จำปานิน
 */
class Sprite extends Objects {

	/**
	 * จะถูกโยนเมื่อตั้งชื่อ {@link control.Sprite Sprite} ซ้ำกับที่มีมาก่อนแล้ว
	 * 
	 * @author ณัชพล จำปานิน
	 */
	static final class AddDuplicatedSpriteNameException extends Exception {
		private static final long serialVersionUID = 1L;

		AddDuplicatedSpriteNameException() {
			super("Sprite\'s name cannot be duplicated");
		}
	}

	/**
	 * จะถูกโยนเมื่อตั้งชื่อ {@link control.Sprite Sprite} เป็น null
	 * 
	 * @author ณัชพล จำปานิน
	 */
	static final class AddNullSpriteNameException extends Exception {
		private static final long serialVersionUID = 1L;

		AddNullSpriteNameException() {
			super("Value of added background\'s name cannot be null");
		}
	}

	Sprite(final StageObject stageObject, final String name)
			throws AddDuplicatedSpriteNameException, AddNullSpriteNameException {
		super(stageObject, name);
		if (name == null)
			throw new AddNullSpriteNameException();
		for (Sprite i : Handlers.sprites)
			if (i.getName().compareTo(name) == 0)
				throw new AddDuplicatedSpriteNameException();
	}

	Sprite() {
		super(null, null);
	}

	@Override
	final void setup() {
		stageObject.setup();
	}

	@Override
	final void tick() {
		if (stageObject != null && stageObject.ticking)
			stageObject.tick();
	}

	@Override
	final void render(final Graphics g) {
		if (stageObject != null && stageObject.rendering)
			stageObject.render(g);
	}
}
