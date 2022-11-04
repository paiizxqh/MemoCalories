package equipments.gadgets.scripts;

import java.awt.Color;
import java.awt.Graphics;

import control.Requester;
import control.Sensor;
import equipments.SpriteDoAble;
import equipments.StageObject;

/**
 * กรอบที่ตรวจจับว่าเมาส์อยู่ในกรอบหรือไม่<br>
 * อีกทั้งยังบอกด้วยว่าเมาส์ได้กดในกรอบนั้นด้วยหรือไม่
 * 
 * @author ณัชพล จำปานิน
 */
public class Frame extends StageObject implements SpriteDoAble {

	/**
	 * ตำแหน่งในแกน x ของกรอบ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public double x = 0;

	/**
	 * ตำแหน่งในแกน y ของกรอบ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public double y = 0;
	private int width = 0;
	private int height = 0;
	private String leftCounter = Requester.GetLeftClickCounter(this);
	private boolean activated = false;
	private transient boolean exited = false;
	private boolean draggable = false;
	private boolean scrollable = false;
	
	/**
	 * ความเร็วในการเลื่อนกรอบด้วยตัวเลื่อนเมาส์<br>
	 * ถ้ามีค่าเป็นบวก จะมีทิศทางเดียวกันกับการเลื่อนเมาส์<br>
	 * มิฉะนั้นจะมีค่าติดลบ
	 */
	public double scrollSpeed = 5;
	
	/**
	 * ทิศทางที่สามารถลากได้ ซึ่งจะมี<br>
	 * <ul>
	 * <li>{@link #BOTH BOTH} ทิศทางทั้งแนวแกน x และแกน y</li><br>
	 * <li>{@link #HORIZONTAL HORIZONTAL} ทิศทางเฉพาะแนวแกน x และ</li><br>
	 * <li>{@link #VERTICAL VERTICAL} ทิศทางเฉพาะแนวแกน y</li>
	 * </ul>
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static enum Direction {
		/**
		 * ทิศทางทั้งแนวแกน x และแกน y
		 * 
		 * @author ณัชพล จำปานิน
		 */
		BOTH,
		
		/**
		 * ทิศทางเฉพาะแนวแกน x
		 * 
		 * @author ณัชพล จำปานิน
		 */
		HORIZONTAL,
		
		/**
		 * ทิศทางเฉพาะแนวแกน y
		 * 
		 * @author ณัชพล จำปานิน
		 */
		VERTICAL;
	}
	private Direction direction = Direction.BOTH;
	private transient double relativeX = 0;
	private transient double relativeY = 0;
	private transient boolean dragged = false;

	public Frame(final String name) {
		super(name);
	}

	/**
	 * ความกว้างมีค่าติดลบ
	 * 
	 * @author ณัพชล จำปานิน
	 */
	public static final class NegativeWidthException extends Exception {
		private static final long serialVersionUID = 1L;

		NegativeWidthException() {
			super("ความกว้างมีค่าติดลบไม่ได้");
		}
	}

	/**
	 * ตั้งค่าความกว้างของกรอบ
	 * 
	 * @param width ความกว้างของกรอบ
	 * @throws NegativeWidthException ความกว้างมีค่าติดลบ
	 * @author ณัชพล จำปานิน
	 */
	public final void setWidth(final int width) throws NegativeWidthException {
		if (width < 0)
			throw new NegativeWidthException();
		this.width = width;
	}

	/**
	 * ดึงค่าความกว้างของกรอบ
	 * 
	 * @return ความกว้างของกรอบ
	 * @author ณัชพล จำปานิน
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * ความสูงมีค่าติดลบ
	 * 
	 * @author ณัพชล จำปานิน
	 */
	public static final class NegativeHeightException extends Exception {
		private static final long serialVersionUID = 1L;

		NegativeHeightException() {
			super("ความสูงมีค่าติดลบไม่ได้");
		}
	}

	/**
	 * ตั้งค่าความกว้างของกรอบ
	 * 
	 * @param width ความกว้างของกรอบ
	 * @throws NegativeWidthException  กรอบมีค่าติดลบ
	 * @throws NegativeHeightException ความสูงมีค่าติดลบ
	 * @author ณัชพล จำปานิน
	 */
	public final void setHeight(final int height) throws NegativeHeightException {
		if (height < 0)
			throw new NegativeHeightException();
		this.height = height;
	}

	/**
	 * ดึงค่าความกว้างของกรอบ
	 * 
	 * @return ความกว้างของกรอบ
	 * @author ณัชพล จำปานิน
	 */
	public final int getHeight() {
		return height;
	}

	/**
	 * ดูว่าเมาส์อยู่ในกรอบหรือไม่
	 * 
	 * @return ถ้าเมาส์อยู่ในกรอบ จะส่งค่ากลับเป็นจริง<br>
	 *         มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public final boolean isTouched() {
		if (activated)
			return (Sensor.getPointerX() > x && Sensor.getPointerX() < x + width)
				&& (Sensor.getPointerY() > y && Sensor.getPointerY() < y + height);
		else
			return false;
	}

	/**
	 * ดูว่าเมาส์ถูกกดอยู่ในกรอบหรือไม่
	 * 
	 * @return ถ้าเมาส์อยู่ในกรอบ จะส่งค่ากลับเป็นจริง<br>
	 *         มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public final boolean isStuck() {
		if (!exited && activated)
			try {
				if (Sensor.Counter.isCounterFinished(leftCounter))
					return isTouched();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return false;
	}

	private final boolean isReallyStuck() {
		try {
			return (Sensor.Counter.isCounterFinished(leftCounter));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ดูว่าเมาส์ถูกกดอยู่ในกรอบหรือไม่ โดยจะคิดแค่ครั้งเดียว
	 * 
	 * @return ถ้าเมาส์อยู่ในกรอบ จะส่งค่ากลับเป็นจริง<br>
	 *         มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public final boolean isTapped() {
		if (activated)
			try {
				if (Sensor.Counter.isCounterJustFinished(leftCounter))
					return isTouched();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return false;
	}
	
	/**
	 * ทำให้กรอบเริ่มทำงาน
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public final void enable() {
		activated = true;
	}
	
	/**
	 * ทำให้กรอบเลิกทำงาน
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public final void disable() {
		activated = false;
	}
	
	/**
	 * ดูว่ากรอบทำงานอยู่หรือไม่
	 * @return ถ้ากรอบทำงาน จะส่งค่ากลับเป็นจริง<br>
	 * มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public final boolean isActived() {
		return activated;
	}
	
	/**
	 * ตั้งค่าให้กรอบสามารถถูกลากได้หรือไม่ได้
	 * 
	 * @param draggable ถ้าค่าเป็นจริง กรอบจะสามารถถูกลากได้<br>
	 * มิฉะนั้น กรอบจะไม่สามารถถูกลากได้
	 * @author ณัชพล จำปานิน
	 */
	public final void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}
	
	/**
	 * ดูว่ากรอบสามารถถูกลากได้หรือไม่
	 * 
	 * @return ถ้ากรอบสามารถถูกลากได้ จะส่งค่ากลับเป็นจริง<br>
	 * มิฉะนั้น จะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public final boolean isDraggable() {
		return draggable;
	}
	
	/**
	 * ตั้งค่าให้กรอบสามารถถูกเลื่อนตามแกน y ด้วยลูกเลื่อนได้ไหม
	 * 
	 * @param scrollable ถ้าค่าเป็นจริง กรอบจะสามารถถูกเลื่อนได้<br>
	 * มิฉะนั้น กรอบจะไม่สามารถถูกเลื่อนได้
	 * @author ณัชพล จำปานิน
	 */
	public final void setScrollable(boolean scrollable) {
		this.scrollable = scrollable;
	}
	
	/**
	 * ดูว่ากรอบสามารถถูกเลื่อนตามแกน y ด้วยลูกเลื่อนได้ไหม
	 * 
	 * @return ถ้ากรอบสามารถถูกเลื่อนได้ จะส่งค่ากลับเป็นจริง<br>
	 * มิฉะนั้น จะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public final boolean isScrollable() {
		return scrollable;
	}
	
	/**
	 * ตั้งค่าทิศทางที่กรอบสามารถถูกลากได้
	 * 
	 * @param direction ทิศทาง
	 * @author ณัชพล จำปานิน
	 */
	public final void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * ดูว่ากรอบถูกตั้งค่าทิศทางที่สามารถถูกลากได้อย่างไร
	 * @return ทิศทาง
	 * @author ณัชพล จำปานิน
	 */
	public final Direction getDirection() {
		return direction;
	}

	public final void tick() {
		if (draggable)
			if (dragged && !exited)
				if (!isReallyStuck() || !isTouched())
					dragged = false;
				else {
					if (direction != Direction.VERTICAL)
						x = Sensor.getPointerX() - relativeX;
					if (direction != Direction.HORIZONTAL)
						y = Sensor.getPointerY() - relativeY;
				}
			else if (isTouched())
				if (isReallyStuck()) {
					if (direction != Direction.VERTICAL)
						relativeX = Sensor.getPointerX() - x;
					if (direction != Direction.HORIZONTAL)
						relativeY = Sensor.getPointerY() - y;
					dragged = true;	
				}
		if (activated)
			try {
				if (isReallyStuck()) {
					if (!isTouched())
						exited = true;
				} else
					exited = false;
				if (direction != Direction.HORIZONTAL)
					if (Sensor.detect("MoveUp"))
						y -= scrollSpeed;
					else if (Sensor.detect("MoveDown"))
						y += scrollSpeed;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public final void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int) x, (int) y, width, height);
	}
}
