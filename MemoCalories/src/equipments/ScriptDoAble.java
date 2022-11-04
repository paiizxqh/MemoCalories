package equipments;

/**
 * มีไว้อิมพลีเมนต์วัตถุที่ขยาย<br>
 * ด้วย {@link equipments.StageObject StageObject}<br>
 * เพื่อแปลงให้เป็น {@link control.Script Script}
 * 
 * @author ณัชพล จำปานิน
 */
public interface ScriptDoAble {

	/**
	 * เป็นส่วนที่จะถูกทำก่อนเมธอด {@link equipments.StageObject#tick tick} และ
	 * {@link equipments.StageObject#render render}
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public void setup();

	/**
	 * เป็นส่วนที่ทำงานตลอดเวลาที่ไม่มีการสร้างกราฟิก
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public void tick();
}
