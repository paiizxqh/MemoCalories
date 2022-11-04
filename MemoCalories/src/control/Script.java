package control;

import equipments.StageObject;

/**
 * มีหน้าที่เป็นบทที่คอบควบคุม<br>
 * {@link control.Background Background} และ {@link control.Sprite Sprite}<br>
 * ต้องขยายวัตถุด้วย<br>
 * {@link equipments.StageObject StageObject}<br>
 * และอิมพลีเมนต์ด้วย<br>
 * {@link equipments.ScriptDoAble ScriptDoAble}
 * 
 * @author ณัชพล จำปานิน
 */
class Script extends Objects {

	/**
	 * จะถูกโยนเมื่อตั้งชื่อ {@link control.Script Script} ซ้ำกับที่มีมาก่อนแล้ว
	 * 
	 * @author ณัชพล จำปานิน
	 */
	static class AddDuplicatedScriptNameException extends Exception {
		private static final long serialVersionUID = 1L;

		AddDuplicatedScriptNameException() {
			super("Script\'s name cannot be duplicated");
		}
	}

	/**
	 * จะถูกโยนเมื่อตั้งชื่อ {@link control.Script Script} เป็น null
	 * 
	 * @author ณัชพล จำปานิน
	 */
	static class AddNullScriptNameException extends Exception {
		private static final long serialVersionUID = 1L;

		AddNullScriptNameException() {
			super("Value of added bacckground\'s name cannot be null");
		}
	}

	Script(final StageObject stageObject, final String name)
			throws AddDuplicatedScriptNameException, AddNullScriptNameException {
		super(stageObject, name);
		if (name == null)
			throw new AddNullScriptNameException();
		for (Script i : Handlers.scripts)
			if (i.getName().compareTo(name) == 0)
				throw new AddDuplicatedScriptNameException();
	}

	Script() {
		super(null, null);
	}

	@Override
	void setup() {
		stageObject.setup();
	}

	@Override
	void tick() {
		if (stageObject != null && stageObject.ticking)
			stageObject.tick();
	}
}
