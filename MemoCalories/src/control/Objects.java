package control;

import java.awt.Graphics;

import equipments.StageObject;

// Unalterable

abstract class Objects {
	protected static final int HEIGHT = 841, WIDTH = 1536;
	protected static final int TOP_BORDER = 12;
	protected final StageObject stageObject;
	protected final String name;

	Objects(final StageObject stageObject, final String name) {
		this.stageObject = stageObject;
		this.name = name;
	}

	void setup() {
		;
	}

	void tick() {
		;
	}

	void render(final Graphics g) {
		;
	}

	String getName() {
		return name;
	}
}
