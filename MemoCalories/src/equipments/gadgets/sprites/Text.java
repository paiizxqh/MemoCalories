package equipments.gadgets.sprites;

import java.awt.Color;
import java.awt.Graphics;

import equipments.SpriteDoAble;
import equipments.StageObject;

/**
 * มีหน้าที่แสดงข้อความ
 * 
 * @author ณัชพล จำปานิน
 */
public class Text extends StageObject implements SpriteDoAble {
	private String text = "";

	/**
	 * ตำแหน่งของข้อความตามแนวแกน x
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public double x = 0;

	/**
	 * ตำแหน่งของข้อความตามแนวแกน y
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public double y = 0;
	private int size = 1;
	private Color color = Color.BLACK;

	/**
	 * ความกว้างของกรอบข้อความ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public transient int width = 0;

	/**
	 * ความสูงของกรอบข้อความ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public transient int height = 0;

	/**
	 * จำนวนอักขระของข้อความ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public transient int length = 0;
	private static final int SPACING = 3;
	private static final int NEWLINE = 10;
	private transient boolean open;
	private boolean showGrid = false;
	private transient boolean start;

	/**
	 * ข้อความ
	 * 
	 * @param name ชื่อ Text
	 * @author ณัชพล จำปานิน
	 */
	public Text(final String name) {
		super(name);
	}

	/**
	 * ข้อความเป็น null
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class NullTextException extends Exception {
		private static final long serialVersionUID = 1L;

		NullTextException() {
			super("ข้อความเป็น null ไม่ได้");
		}
	}

	/**
	 * ตั้งค่าข้อความ
	 * 
	 * @param text ข้อความ
	 * @throws NullTextException ข้อความเป็น null
	 * @author ณัชพล จำปานิน
	 */
	public final void setText(String text) throws NullTextException {
		if (text == null)
			throw new NullTextException();
		this.text = text;
	}

	/**
	 * ดึงค่าข้อความ
	 * 
	 * @return ข้อความ
	 * @author ณัชพล จำปานิน
	 */
	public final String getText() {
		return text;
	}
	
	/**
	 * มีไว้เรียกใช้เพื่อกำหนดข้อความ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public final AppendText appending = new AppendText(this);
	
	/**
	 * มีหน้าที่กำหนดข้อความ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public final class AppendText {
		private Text self;
		
		AppendText(Text self) {
			this.self = self;
		}
		
		/**
		 * เริ่มต้นด้วยการล้างข้อความ ใช้ก่อนเมธอด append<br>
		 * 
		 * @return มีไว้ทำให้ใช้ method ต่อเนื่องได้
		 * @author ณัชพล จำปานิน
		 */
		public final AppendText start() {
			self.text = "";
			return appending;
		}
		
		/**
		 * เริ่มต้นด้วยการกำหนดข้อความ ใช้ก่อนเมธอด append<br>
		 * 
		 * @return มีไว้ทำให้ใช้ method ต่อเนื่องได้
		 * @throws NullTextException ข้อความเป็น null
		 * @author ณัชพล จำปานิน
		 */
		public final AppendText start(String text) throws NullTextException {
			if (text == null)
				throw new NullTextException();
			self.text = text;
			return appending;
		}
		
		/**
		 * ต่อข้อความ
		 * 
		 * @param text ข้อความ
		 * @return มีไว้ทำให้ใช้ method ต่อเนื่องได้
		 * @throws NullTextException ข้อความเป็น null
		 * @author ณัชพล จำปานิน
		 */
		public final AppendText append(String text) throws NullTextException {
			if (text == null)
				throw new NullTextException();
			self.text += text;
			return appending;
		}
	}

	/**
	 * ขนาดอักขระของข้อความติดลบ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class NotPositiveSizeException extends Exception {
		private static final long serialVersionUID = 1L;

		NotPositiveSizeException() {
			super("ขนาดอักขระของข้อความติดลบไม่ได้");
		}
	}

	/**
	 * กำหนดขนาดอักขระของข้อความ
	 * 
	 * @param size ขนาดอักขระของข้อความ
	 * @throws NotPositiveSizeException ขนาดอักขระของข้อความติดลบ
	 * @author ณัชพล จำปานิน
	 */
	public final void setSize(final int size) throws NotPositiveSizeException {
		if (size <= 0)
			throw new NotPositiveSizeException();
		this.size = size;
	}

	/**
	 * ดึงค่าขนาดอักขระของข้อความ
	 * 
	 * @return ขนาด
	 * @author ณัชพล จำปานิน
	 */
	public final int getSize() {
		return size;
	}

	/**
	 * สีข้อความเป็น null
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class NullColorException extends Exception {
		private static final long serialVersionUID = 1L;

		NullColorException() {
			super("สีข้อความเป็น null ไม่ได้");
		}
	}

	/**
	 * กำหนดสีของข้อความ
	 * 
	 * @param color สีข้อความ
	 * @throws NullColorException สีข้อความเป็น null
	 * @author ณัชพล จำปานิน
	 */
	public final void setColor(Color color) throws NullColorException {
		if (color == null)
			throw new NullColorException();
		this.color = color;
	}

	/**
	 * ดึงค่าสีของข้อความ
	 * 
	 * @return สีข้อความ
	 * @author ณัชพล จำปานิน
	 */
	public final Color getColor() {
		return color;
	}

	/**
	 * แสดงกรอบสีแดงตามขนาดของข้อความ<br>
	 * มีไว้เพื่อให้ออกแบบได้ง่ายขึ้น
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public final void showGrid() {
		showGrid = true;
	}

	/**
	 * ซ่อนกรอบสีแดงรตามขนาดของข้อความ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public final void hideGrid() {
		showGrid = false;
	}

	/**
	 * ดูว่ากรอบสีแดงตามขนาดของข้อความยังคงแสดงอยู่หรือไม่
	 * 
	 * @return ถ้ากรอบสีแดงยังแสดงอยู่ จะส่งค่ากลับเป็นจริง<br>
	 *         มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public final boolean isGridShown() {
		return showGrid;
	}

	public final void tick() {
		if (text.length() == 0) {
			width = size;
			height = (NEWLINE - 2) * size;
			length = 0;
		}
	}

	public final void render(final Graphics g) {
		double shiftedX = x;
		double shiftedY = y;
		open = true;
		int[] shifted;
		int width = 0;
		int height = 9 * size;
		int maxWidth = 0;
		int length = 0;
		start = true;
		if (text.length() == 0) {
			width = size;
			height = (NEWLINE - 2) * size;
			length = 0;
			if (showGrid) {
				g.setColor(Color.RED);
				g.drawRect((int) x, (int) y, width, height);
			}
		} else
			for (char c : text.toCharArray()) {
				shifted = renderLetter(c, (int) shiftedX, (int) shiftedY, g);
				if (start)
					start = false;
				shiftedX += shifted[0];
				length++;
				if (shifted[0] >= 0)
					width += shifted[0];
				else
					width = 0;
				shiftedY += shifted[1];
				height += shifted[1];
				if (width > maxWidth)
					maxWidth = width;
				this.width = maxWidth - size;
				this.height = height - size;
				this.length = length;
			}
	}

	private final int[] renderLetter(final char character, final int x, final int y, final Graphics g) {
		if (showGrid && start) {
			g.setColor(Color.RED);
			g.drawRect((int) this.x, (int) this.y, width, height);
		}
		g.setColor(color);
		switch (character) {
		case ' ':
			return new int[] { SPACING * size, 0 };
		case 'A':
			g.fillRect(x, y + size, size, 6 * size);
			g.fillRect(x + 4 * size, y + size, size, 6 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			return new int[] { 6 * size, 0 };
		case 'B':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + size, size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 3 * size);
			return new int[] { 6 * size, 0 };
		case 'C':
			g.fillRect(x, y + size, size, 5 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + size, size, size);
			g.fillRect(x + 4 * size, y + 5 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'D':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + size, size, 5 * size);
			return new int[] { 6 * size, 0 };
		case 'E':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y, 4 * size, size);
			g.fillRect(x + size, y + 2 * size, 2 * size, size);
			g.fillRect(x + size, y + 6 * size, 4 * size, size);
			return new int[] { 6 * size, 0 };
		case 'F':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y, 4 * size, size);
			g.fillRect(x + size, y + 2 * size, 2 * size, size);
			return new int[] { 6 * size, 0 };
		case 'G':
			g.fillRect(x, y + size, size, 5 * size);
			g.fillRect(x + size, y, 4 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + 2 * size, size, 4 * size);
			g.fillRect(x + 3 * size, y + 2 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'H':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + 4 * size, y, size, 7 * size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			return new int[] { 6 * size, 0 };
		case 'I':
			g.fillRect(x, y, 3 * size, size);
			g.fillRect(x, y + 6 * size, 3 * size, size);
			g.fillRect(x + size, y + size, size, 5 * size);
			return new int[] { 4 * size, 0 };
		case 'J':
			g.fillRect(x, y + 5 * size, size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y, size, 6 * size);
			return new int[] { 6 * size, 0 };
		case 'K':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y + 2 * size, 2 * size, size);
			g.fillRect(x + 3 * size, y + size, size, size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			g.fillRect(x + 4 * size, y, size, size);
			g.fillRect(x + 4 * size, y + 4 * size, size, 3 * size);
			return new int[] { 6 * size, 0 };
		case 'L':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y + 6 * size, 4 * size, size);
			return new int[] { 6 * size, 0 };
		case 'M':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + 4 * size, y, size, 7 * size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + 3 * size, y + size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'N':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + 4 * size, y, size, 7 * size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, size, size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'O':
			g.fillRect(x, y + size, size, 5 * size);
			g.fillRect(x + 4 * size, y + size, size, 5 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			return new int[] { 6 * size, 0 };
		case 'P':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + size, size, size);
			return new int[] { 6 * size, 0 };
		case 'Q':
			g.fillRect(x, y + size, size, 5 * size);
			g.fillRect(x + 4 * size, y + size, size, 4 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 2 * size, size);
			g.fillRect(x + 3 * size, y + 5 * size, size, size);
			g.fillRect(x + 4 * size, y + 6 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'R':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + size, size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 4 * size);
			return new int[] { 6 * size, 0 };
		case 'S':
			g.fillRect(x + size, y, 4 * size, size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x, y + size, size, size);
			g.fillRect(x, y + 5 * size, size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 3 * size);
			return new int[] { 6 * size, 0 };
		case 'T':
			g.fillRect(x, y, 5 * size, size);
			g.fillRect(x + 2 * size, y + size, size, 6 * size);
			return new int[] { 6 * size, 0 };
		case 'U':
			g.fillRect(x, y, size, 6 * size);
			g.fillRect(x + 4 * size, y, size, 6 * size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			return new int[] { 6 * size, 0 };
		case 'V':
			g.fillRect(x, y, size, 4 * size);
			g.fillRect(x + 4 * size, y, size, 4 * size);
			g.fillRect(x + size, y + 4 * size, size, 2 * size);
			g.fillRect(x + 3 * size, y + 4 * size, size, 2 * size);
			g.fillRect(x + 2 * size, y + 6 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'W':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + 4 * size, y, size, 7 * size);
			g.fillRect(x + size, y + 5 * size, size, size);
			g.fillRect(x + 3 * size, y + 5 * size, size, size);
			g.fillRect(x + 2 * size, y + 4 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'X':
			g.fillRect(x, y, size, size);
			g.fillRect(x + 4 * size, y, size, size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + 3 * size, y + size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, size, size);
			g.fillRect(x + size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			g.fillRect(x, y + 4 * size, size, 3 * size);
			g.fillRect(x + 4 * size, y + 4 * size, size, 3 * size);
			return new int[] { 6 * size, 0 };
		case 'Y':
			g.fillRect(x, y, size, size);
			g.fillRect(x + 4 * size, y, size, size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + 3 * size, y + size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, size, 5 * size);
			return new int[] { 6 * size, 0 };
		case 'Z':
			g.fillRect(x, y, 5 * size, size);
			g.fillRect(x, y + 6 * size, 5 * size, size);
			g.fillRect(x, y + 5 * size, size, size);
			g.fillRect(x + size, y + 4 * size, size, size);
			g.fillRect(x + 2 * size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + 2 * size, size, size);
			g.fillRect(x + 4 * size, y + size, size, size);
			return new int[] { 6 * size, 0 };
		case 'a':
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + size, y + 4 * size, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 4 * size);
			g.fillRect(x, y + 5 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'b':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + size, y + 3 * size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, 2 * size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 3 * size);
			return new int[] { 6 * size, 0 };
		case 'c':
			g.fillRect(x, y + 3 * size, size, 3 * size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, size);
			g.fillRect(x + 4 * size, y + 5 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'd':
			g.fillRect(x, y + 3 * size, size, 3 * size);
			g.fillRect(x + size, y + 2 * size, 2 * size, size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y, size, 7 * size);
			return new int[] { 6 * size, 0 };
		case 'e':
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + size, y + 4 * size, 4 * size, size);
			g.fillRect(x + size, y + 6 * size, 4 * size, size);
			g.fillRect(x, y + 3 * size, size, 3 * size);
			g.fillRect(x + 4 * size, y + 3 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'f':
			g.fillRect(x, y + 2 * size, size, size);
			g.fillRect(x + size, y + size, size, 6 * size);
			g.fillRect(x + 2 * size, y, 2 * size, size);
			g.fillRect(x + 2 * size, y + 2 * size, 2 * size, size);
			return new int[] { 5 * size, 0 };
		case 'g':
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + size, y + 5 * size, 3 * size, size);
			g.fillRect(x, y + 7 * size, 4 * size, size);
			g.fillRect(x, y + 3 * size, size, 2 * size);
			g.fillRect(x + 4 * size, y + 2 * size, size, 5 * size);
			return new int[] { 6 * size, 0 };
		case 'h':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y + 3 * size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, 2 * size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 4 * size);
			return new int[] { 6 * size, 0 };
		case 'i':
			g.fillRect(x, y, size, size);
			g.fillRect(x, y + 2 * size, size, 5 * size);
			return new int[] { 2 * size, 0 };
		case 'j':
			g.fillRect(x, y + 5 * size, size, 2 * size);
			g.fillRect(x + size, y + 7 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y, size, size);
			g.fillRect(x + 4 * size, y + 2 * size, size, 5 * size);
			return new int[] { 6 * size, 0 };
		case 'k':
			g.fillRect(x, y, size, 7 * size);
			g.fillRect(x + size, y + 4 * size, size, size);
			g.fillRect(x + 2 * size, y + 3 * size, size, size);
			g.fillRect(x + 2 * size, y + 5 * size, size, size);
			g.fillRect(x + 3 * size, y + 2 * size, size, size);
			g.fillRect(x + 3 * size, y + 6 * size, size, size);
			return new int[] { 5 * size, 0 };
		case 'l':
			g.fillRect(x, y, size, 6 * size);
			g.fillRect(x + size, y + 6 * size, size, size);
			return new int[] { 3 * size, 0 };
		case 'm':
			g.fillRect(x, y + 2 * size, size, 5 * size);
			g.fillRect(x + 2 * size, y + 3 * size, size, 2 * size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 4 * size);
			g.fillRect(x + size, y + 2 * size, size, size);
			g.fillRect(x + 3 * size, y + 2 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'n':
			g.fillRect(x, y + 2 * size, size, 5 * size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 4 * size);
			return new int[] { 6 * size, 0 };
		case 'o':
			g.fillRect(x, y + 3 * size, size, 3 * size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 3 * size);
			g.fillRect(x + size, y + 2 * size, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			return new int[] { 6 * size, 0 };
		case 'p':
			g.fillRect(x, y + 2 * size, size, 6 * size);
			g.fillRect(x + size, y + 3 * size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, 2 * size, size);
			g.fillRect(x + size, y + 5 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 2 * size);
			return new int[] { 6 * size, 0 };
		case 'q':
			g.fillRect(x, y + 3 * size, size, 2 * size);
			g.fillRect(x + size, y + 2 * size, 2 * size, size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			g.fillRect(x + size, y + 5 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + 2 * size, size, 6 * size);
			return new int[] { 6 * size, 0 };
		case 'r':
			g.fillRect(x, y + 2 * size, size, 5 * size);
			g.fillRect(x + size, y + 3 * size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, 2 * size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 's':
			g.fillRect(x + size, y + 2 * size, 4 * size, size);
			g.fillRect(x + size, y + 4 * size, 3 * size, size);
			g.fillRect(x, y + 6 * size, 4 * size, size);
			g.fillRect(x, y + 3 * size, size, size);
			g.fillRect(x + 4 * size, y + 5 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 't':
			g.fillRect(x + size, y, size, 6 * size);
			g.fillRect(x, y + size, size, size);
			g.fillRect(x + 2 * size, y + size, size, size);
			g.fillRect(x + 2 * size, y + 6 * size, size, size);
			return new int[] { 4 * size, 0 };
		case 'u':
			g.fillRect(x, y + 2 * size, size, 4 * size);
			g.fillRect(x + 4 * size, y + 2 * size, size, 4 * size);
			g.fillRect(x + size, y + 6 * size, 4 * size, size);
			return new int[] { 6 * size, 0 };
		case 'v':
			g.fillRect(x, y + 2 * size, size, 3 * size);
			g.fillRect(x + 4 * size, y + 2 * size, size, 3 * size);
			g.fillRect(x + size, y + 5 * size, size, size);
			g.fillRect(x + 3 * size, y + 5 * size, size, size);
			g.fillRect(x + 2 * size, y + 6 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'w':
			g.fillRect(x, y + 2 * size, size, 4 * size);
			g.fillRect(x + 2 * size, y + 4 * size, size, 2 * size);
			g.fillRect(x + 4 * size, y + 2 * size, size, 4 * size);
			g.fillRect(x + size, y + 6 * size, 4 * size, size);
			return new int[] { 6 * size, 0 };
		case 'x':
			g.fillRect(x, y + 2 * size, size, size);
			g.fillRect(x + 4 * size, y + 2 * size, size, size);
			g.fillRect(x + size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			g.fillRect(x + 2 * size, y + 4 * size, size, size);
			g.fillRect(x + size, y + 5 * size, size, size);
			g.fillRect(x + 3 * size, y + 5 * size, size, size);
			g.fillRect(x, y + 6 * size, size, size);
			g.fillRect(x + 4 * size, y + 6 * size, size, size);
			return new int[] { 6 * size, 0 };
		case 'y':
			g.fillRect(x, y + 2 * size, size, 3 * size);
			g.fillRect(x + size, y + 5 * size, 3 * size, size);
			g.fillRect(x, y + 7 * size, 4 * size, size);
			g.fillRect(x + 4 * size, y + 2 * size, size, 5 * size);
			return new int[] { 6 * size, 0 };
		case 'z':
			g.fillRect(x, y + 2 * size, 5 * size, size);
			g.fillRect(x, y + 6 * size, 5 * size, size);
			g.fillRect(x + size, y + 5 * size, size, size);
			g.fillRect(x + 2 * size, y + 4 * size, size, size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			return new int[] { 6 * size, 0 };
		case '0':
			g.fillRect(x, y + size, size, 5 * size);
			g.fillRect(x + 4 * size, y + size, size, 5 * size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + size, y + 4 * size, size, size);
			g.fillRect(x + 2 * size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + 2 * size, size, size);
			return new int[] { 6 * size, 0 };
		case '1':
			g.fillRect(x + 2 * size, y, size, 6 * size);
			g.fillRect(x, y + 6 * size, 5 * size, size);
			g.fillRect(x + size, y + size, size, size);
			return new int[] { 6 * size, 0 };
		case '2':
			g.fillRect(x, y + size, size, size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + 4 * size, y + size, size, 2 * size);
			g.fillRect(x + 2 * size, y + 3 * size, 2 * size, size);
			g.fillRect(x + size, y + 4 * size, size, size);
			g.fillRect(x, y + 5 * size, size, size);
			g.fillRect(x, y + 6 * size, 5 * size, size);
			return new int[] { 6 * size, 0 };
		case '3':
			g.fillRect(x, y + size, size, size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + 4 * size, y + size, size, 2 * size);
			g.fillRect(x + 2 * size, y + 3 * size, 2 * size, size);
			g.fillRect(x + 4 * size, y + 4 * size, size, 2 * size);
			g.fillRect(x, y + 5 * size, size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			return new int[] { 6 * size, 0 };
		case '4':
			g.fillRect(x, y + 4 * size, 4 * size, size);
			g.fillRect(x + 4 * size, y, size, 7 * size);
			g.fillRect(x, y + 3 * size, size, size);
			g.fillRect(x + size, y + 2 * size, size, size);
			g.fillRect(x + 2 * size, y + size, size, size);
			g.fillRect(x + 3 * size, y, size, size);
			return new int[] { 6 * size, 0 };
		case '5':
			g.fillRect(x, y, 5 * size, size);
			g.fillRect(x, y + size, size, size);
			g.fillRect(x, y + 2 * size, 4 * size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, 3 * size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x, y + 5 * size, size, size);
			return new int[] { 6 * size, 0 };
		case '6':
			g.fillRect(x, y + 2 * size, size, 4 * size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + 2 * size, y, 2 * size, size);
			g.fillRect(x + size, y + 3 * size, 3 * size, size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			g.fillRect(x + 4 * size, y + 4 * size, size, 2 * size);
			return new int[] { 6 * size, 0 };
		case '7':
			g.fillRect(x, y, 5 * size, size);
			g.fillRect(x, y + size, size, size);
			g.fillRect(x + 4 * size, y + size, size, 2 * size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			g.fillRect(x + 2 * size, y + 4 * size, size, 3 * size);
			return new int[] { 6 * size, 0 };
		case '8':
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x, y + size, size, 2 * size);
			g.fillRect(x + 4 * size, y + size, size, 2 * size);
			g.fillRect(x + size, y + 3 * size, 3 * size, size);
			g.fillRect(x, y + 4 * size, size, 2 * size);
			g.fillRect(x + 4 * size, y + 4 * size, size, 2 * size);
			g.fillRect(x + size, y + 6 * size, 3 * size, size);
			return new int[] { 6 * size, 0 };
		case '9':
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + size, y + 3 * size, 3 * size, size);
			g.fillRect(x, y + size, size, 2 * size);
			g.fillRect(x + 4 * size, y + size, size, 4 * size);
			g.fillRect(x + 3 * size, y + 5 * size, size, size);
			g.fillRect(x + size, y + 6 * size, 2 * size, size);
			return new int[] { 6 * size, 0 };
		case '.':
			g.fillRect(x, y + 5 * size, size, 2 * size);
			return new int[] { 2 * size, 0 };
		case ',':
			g.fillRect(x + size, y + 5 * size, size, 2 * size);
			g.fillRect(x, y + 7 * size, size, size);
			return new int[] { 3 * size, 0 };
		case ';':
			g.fillRect(x + size, y, size, 2 * size);
			g.fillRect(x + size, y + 5 * size, size, 2 * size);
			g.fillRect(x, y + 7 * size, size, size);
			return new int[] { 3 * size, 0 };
		case ':':
			g.fillRect(x, y, size, 2 * size);
			g.fillRect(x, y + 5 * size, size, 2 * size);
			return new int[] { 2 * size, 0 };
		case '$':
			g.fillRect(x + size, y + size, 4 * size, size);
			g.fillRect(x + size, y + 3 * size, 3 * size, size);
			g.fillRect(x, y + 5 * size, 4 * size, size);
			g.fillRect(x, y + 2 * size, size, size);
			g.fillRect(x + 4 * size, y + 4 * size, size, size);
			g.fillRect(x + 2 * size, y, size, size);
			g.fillRect(x + 2 * size, y + 6 * size, size, size);
			return new int[] { 6 * size, 0 };
		case '#':
			g.fillRect(x + size, y, size, 7 * size);
			g.fillRect(x + 3 * size, y, size, 7 * size);
			g.fillRect(x, y + 2 * size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, size, size);
			g.fillRect(x + 4 * size, y + 2 * size, size, size);
			g.fillRect(x, y + 4 * size, size, size);
			g.fillRect(x + 2 * size, y + 4 * size, size, size);
			g.fillRect(x + 4 * size, y + 4 * size, size, size);
			return new int[] { 6 * size, 0 };
		case '\'':
			g.fillRect(x, y, size, 2 * size);
			return new int[] { 2 * size, 0 };
		case '!':
			g.fillRect(x, y, size, 5 * size);
			g.fillRect(x, y + 6 * size, size, size);
			return new int[] { 2 * size, 0 };
		case '\n':
			return new int[] { (int) this.x - x, size * NEWLINE };
		case '\"':
			if (open) {
				g.fillRect(x, y + size, size, 2 * size);
				g.fillRect(x + 3 * size, y + size, size, 2 * size);
				g.fillRect(x + size, y, size, size);
				g.fillRect(x + 4 * size, y, size, size);
			} else {
				g.fillRect(x + size, y, size, 2 * size);
				g.fillRect(x + 4 * size, y, size, 2 * size);
				g.fillRect(x, y + 2 * size, size, size);
				g.fillRect(x + 3 * size, y + 2 * size, size, size);
			}
			open = !open;
			return new int[] { 6 * size, 0 };
		case '/':
			g.fillRect(x, y + 6 * size, size, size);
			g.fillRect(x + size, y + 4 * size, size, 2 * size);
			g.fillRect(x + 2 * size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + size, size, 2 * size);
			g.fillRect(x + 4 * size, y, size, size);
			return new int[] { 6 * size, 0 };
		case '\\':
			g.fillRect(x, y, size, size);
			g.fillRect(x + size, y + size, size, 2 * size);
			g.fillRect(x + 2 * size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + 4 * size, size, 2 * size);
			g.fillRect(x + 4 * size, y + 6 * size, size, size);
			return new int[] { 6 * size, 0 };
		case '?':
			g.fillRect(x, y + size, size, size);
			g.fillRect(x + size, y, 3 * size, size);
			g.fillRect(x + 4 * size, y + size, size, 2 * size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			g.fillRect(x + 2 * size, y + 4 * size, size, size);
			g.fillRect(x + 2 * size, y + 6 * size, size, size);
			return new int[] { 6 * size, 0 };
		case '%':
			g.fillRect(x, y + 6 * size, size, size);
			g.fillRect(x + size, y + 4 * size, size, 2 * size);
			g.fillRect(x + 2 * size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + size, size, 2 * size);
			g.fillRect(x + 4 * size, y, size, size);
			g.fillRect(x, y, size, 2 * size);
			g.fillRect(x + 4 * size, y + 5 * size, size, 2 * size);
			return new int[] { 6 * size, 0 };
		case '&':
			g.fillRect(x + 2 * size, y, size, size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + 3 * size, y + size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, size, 3 * size);
			g.fillRect(x + size, y + 3 * size, size, size);
			g.fillRect(x + 4 * size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + 4 * size, size, 2 * size);
			g.fillRect(x + 4 * size, y + 6 * size, size, size);
			g.fillRect(x, y + 4 * size, size, 2 * size);
			g.fillRect(x + size, y + 6 * size, 2 * size, size);
			return new int[] { 6 * size, 0 };
		case '(':
			g.fillRect(x, y + 2 * size, size, 4 * size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + size, y + 6 * size, size, size);
			g.fillRect(x + 2 * size, y, 2 * size, size);
			g.fillRect(x + 2 * size, y + 7 * size, 2 * size, size);
			return new int[] { 5 * size, 0 };
		case ')':
			g.fillRect(x, y, 2 * size, size);
			g.fillRect(x, y + 7 * size, 2 * size, size);
			g.fillRect(x + 2 * size, y + size, size, size);
			g.fillRect(x + 2 * size, y + 6 * size, size, size);
			g.fillRect(x + 3 * size, y + 2 * size, size, 4 * size);
			return new int[] { 5 * size, 0 };
		case '[':
			g.fillRect(x, y, size, 8 * size);
			g.fillRect(x + size, y, size, size);
			g.fillRect(x + size, y + 7 * size, size, size);
			return new int[] { 3 * size, 0 };
		case ']':
			g.fillRect(x + size, y, size, 8 * size);
			g.fillRect(x, y, size, size);
			g.fillRect(x, y + 7 * size, size, size);
			return new int[] { 3 * size, 0 };
		case '{':
			g.fillRect(x, y + 3 * size, size, size);
			g.fillRect(x + size, y + size, size, 2 * size);
			g.fillRect(x + size, y + 4 * size, size, 3 * size);
			g.fillRect(x + 2 * size, y, 2 * size, size);
			g.fillRect(x + 2 * size, y + 7 * size, 2 * size, size);
			return new int[] { 5 * size, 0 };
		case '}':
			g.fillRect(x, y, 2 * size, size);
			g.fillRect(x, y + 7 * size, 2 * size, size);
			g.fillRect(x + 2 * size, y + size, size, 2 * size);
			g.fillRect(x + 2 * size, y + 4 * size, size, 3 * size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			return new int[] { 5 * size, 0 };
		case '<':
			g.fillRect(x, y + 3 * size, size, size);
			g.fillRect(x + size, y + 2 * size, size, size);
			g.fillRect(x + size, y + 4 * size, size, size);
			g.fillRect(x + 2 * size, y + size, size, size);
			g.fillRect(x + 2 * size, y + 5 * size, size, size);
			g.fillRect(x + 3 * size, y, size, size);
			g.fillRect(x + 3 * size, y + 6 * size, size, size);
			return new int[] { 5 * size, 0 };
		case '>':
			g.fillRect(x, y, size, size);
			g.fillRect(x, y + 6 * size, size, size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + size, y + 5 * size, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, size, size);
			g.fillRect(x + 2 * size, y + 4 * size, size, size);
			g.fillRect(x + 3 * size, y + 3 * size, size, size);
			return new int[] { 5 * size, 0 };
		case '@':
			g.fillRect(x + 2 * size, y + 2 * size, 2 * size, 2 * size);
			g.fillRect(x + 2 * size, y + 4 * size, 3 * size, size);
			g.fillRect(x, y + size, size, 5 * size);
			g.fillRect(x + 5 * size, y + size, size, 4 * size);
			g.fillRect(x + size, y, 4 * size, size);
			g.fillRect(x + size, y + 6 * size, 5 * size, size);
			return new int[] { 7 * size, 0 };
		case '|':
			g.fillRect(x, y, size, 3 * size);
			g.fillRect(x, y + 4 * size, size, 4 * size);
			return new int[] { 2 * size, 0 };
		case '^':
			g.fillRect(x + 2 * size, y, size, size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + 3 * size, y + size, size, size);
			g.fillRect(x, y + 2 * size, size, size);
			g.fillRect(x + 4 * size, y + 2 * size, size, size);
			return new int[] { 6 * size, 0 };
		case '+':
			g.fillRect(x + 2 * size, y + size, size, 5 * size);
			g.fillRect(x, y + 3 * size, 2 * size, size);
			g.fillRect(x + 3 * size, y + 3 * size, 2 * size, size);
			return new int[] { 6 * size, 0 };
		case '-':
			g.fillRect(x, y + 3 * size, 5 * size, size);
			return new int[] { 6 * size, 0 };
		case '*':
			g.fillRect(x, y, size, size);
			g.fillRect(x, y + 2 * size, size, size);
			g.fillRect(x + size, y + size, size, size);
			g.fillRect(x + 2 * size, y, size, size);
			g.fillRect(x + 2 * size, y + 2 * size, size, size);
			return new int[] { 4 * size, 0 };
		case '=':
			g.fillRect(x, y + 2 * size, 5 * size, size);
			g.fillRect(x, y + 4 * size, 5 * size, size);
			return new int[] { 6 * size, 0 };
		case '_':
			g.fillRect(x, y + 7 * size, 5 * size, size);
			return new int[] { 6 * size, 0 };
		case '`':
			g.fillRect(x, y, size, size);
			g.fillRect(x + size, y + size, size, size);
			return new int[] { 3 * size, 0 };
		case '~':
			g.fillRect(x, y + 3 * size, size, size);
			g.fillRect(x + size, y + 2 * size, size, size);
			g.fillRect(x + 2 * size, y + 3 * size, size, size);
			g.fillRect(x + 3 * size, y + 2 * size, size, size);
			return new int[] { 5 * size, 0 };
		default:
			return new int[] { 0, 0 };
		}
	}
}
