package equipments;

import java.awt.Graphics;

import control.Painters;

/**
 * มีไว้แปลงวัตถุให้เป็น {@link control.Background Background},
 * {@link control.Script Script}<br>
 * หรือ {@link control.Sprite Sprite} จากการขยายด้วยคลาสนี้<br>
 * พร้อมกับอิมพลีเมนต์ด้วย {@link equipments.BackgroundDoAble
 * BackgroundDoAble},<br>
 * {@link equipments.ScriptDoAble ScriptDoAble} หรือ
 * {@link equipments.SpriteDoAble SpriteDoAble}
 * 
 * @author ณัชพล จำปานิน
 */
public abstract class StageObject {
	/**
	 * ความสูงของ {@link java.awt.Canvas Canvas}
	 */
	public static final int HEIGHT = Painters.HEIGHT;

	/**
	 * ความกว้างของ {@link java.awt.Canvas Canvas}
	 */
	public static final int WIDTH = Painters.WIDTH;

	/**
	 * ขอบบนของ {@link java.awt.Canvas Canvas}
	 */
	public static final int TOP_BORDER = Painters.TOP_BORDER;

	private final String name;
	
	/**
	 * ถ้าค่าเป็นจริง method ชื่อ tick จะทำงาน
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public boolean ticking = true;
	
	/**
	 * ถ้าค่าเป็นจริง method ชื่อ render จะทำงาน
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public boolean rendering = true;

	/**
	 * สร้าง {@link equipments.StageObject StageObject}
	 * 
	 * @param name ชื่อที่จะใช้ตั้ง StageObject
	 * @author ณัชพล จำปานิน
	 */
	public StageObject(final String name) {
		this.name = name;
	}

	/**
	 * เป็นส่วนที่จะถูกทำก่อนเมธอด {@link equipments.StageObject#tick tick} และ
	 * {@link equipments.StageObject#render render}
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public void setup() {
		;
	}

	/**
	 * เป็นส่วนที่ทำงานตลอดเวลาที่ไม่มีการสร้างกราฟิก
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public void tick() {
		;
	}

	/**
	 * เป็นส่วนที่ทำงานตลอดเวลาที่มีการสร้างกราฟิก
	 * 
	 * @param g กราฟิก
	 * @author ณัชพล จำปานิน
	 */
	public void render(final Graphics g) {
		;
	}

	/**
	 * ดึงชื่อ {@link equipments.StageObject StageObject}<br>
	 * 
	 * @return ชื่อของ StageObject นี้
	 * @author ณัชพล จำปานิน
	 */
	public String getName() {
		return name;
	}
}
