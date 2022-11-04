package equipments;

import java.awt.Graphics;

/**
 * มีไว้อิมพลีเมนต์วัตถุที่ขยาย<br>
 * ด้วย {@link equipments.StageObject StageObject}<br>
 * เพื่อแปลงให้เป็น {@link control.Background Background}
 * 
 * @author ณัชพล จำปานิน
 */
public interface BackgroundDoAble {
	
	/**
	 * เป็นส่วนที่จะถูกทำก่อนเมธอด {@link equipments.StageObject#tick tick} และ
	 * {@link equipments.StageObject#render render}
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public void setup();

	/**
	 * เป็นส่วนที่ทำงานตลอดเวลาที่มีการสร้างกราฟิก
	 * 
	 * @param g กราฟิก
	 * @author ณัชพล จำปานิน
	 */
	public void render(final Graphics g);
}
