package equipments.gadgets.scripts;

import java.util.LinkedList;

import control.Sensor;
import control.Sensor.UnknownSignalException;
import equipments.SpriteDoAble;
import equipments.StageObject;

/**
 * มีหน้าที่แปลงการกดแป้นพิมพ์ให้เป็นรูปแบบข้อความ
 * 
 * @author ณัชพล จำปานิน
 */
public class TextInput extends StageObject implements SpriteDoAble {
	private String readText = "";
	private LinkedList<Integer> typed = new LinkedList<Integer>();
	private static CharacterPair[] characterPair = null;
	private int repeatingWait = 0;
	private boolean reading = false;

	/**
	 * ตัวอ่านข้อความจากแป้นพิมพ์
	 * 
	 * @param name ชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public TextInput(final String name) {
		super(name);
	}

	/**
	 * อ่านข้อความที่รับได้จากแป้นพิมพ์
	 * 
	 * @return ข้อความที่อ่านได้
	 * @author ณัชพล จำปานิน
	 */
	public final String getReadText() {
		return readText;
	}

	/**
	 * เริ่มอ่านข้อความจากแป้นพิมพ์
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public final void startReading() {
		reading = true;
	}

	/**
	 * หยุดอ่านข้อความจากแป้นพิมพ์
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public final void stopReading() {
		reading = false;
	}

	/**
	 * ดูว่ากำลังอ่านข้อความจากแป้นพิมพ์อยู่หรือไม่
	 * 
	 * @return ถ้ากำลังอ่านข้อความจากแป้นพิมพ์อยู่<br>
	 * จะส่งค่ากลับเป็นจริง มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public final boolean isReading() {
		return reading;
	}

	public final void setup() {
		if (characterPair != null)
			return;
		characterPair = new CharacterPair[113];
		characterPair[0] = new CharacterPair(" ", ' ');
		characterPair[1] = new CharacterPair("!", '!');
		characterPair[2] = new CharacterPair("\"", '\"');
		characterPair[3] = new CharacterPair("#", '#');
		characterPair[4] = new CharacterPair("$", '$');
		characterPair[5] = new CharacterPair("%", '%');
		characterPair[6] = new CharacterPair("&", '&');
		characterPair[7] = new CharacterPair("\'", '\'');
		characterPair[8] = new CharacterPair("(", '(');
		characterPair[9] = new CharacterPair(")", ')');
		characterPair[10] = new CharacterPair("*_Keypad", '*');
		characterPair[11] = new CharacterPair("*_Numpad", '*');
		characterPair[12] = new CharacterPair("+_Keypad", '+');
		characterPair[13] = new CharacterPair("+_Numpad", '+');
		characterPair[14] = new CharacterPair(",", ',');
		characterPair[15] = new CharacterPair("-_Keypad", '-');
		characterPair[16] = new CharacterPair("-_Numpad", '-');
		characterPair[17] = new CharacterPair("._Keypad", '.');
		characterPair[18] = new CharacterPair("._Numpad", '.');
		characterPair[19] = new CharacterPair("/_Keypad", '/');
		characterPair[20] = new CharacterPair("/_Numpad", '/');
		characterPair[21] = new CharacterPair("0_Keypad", '0');
		characterPair[22] = new CharacterPair("0_Numpad", '0');
		characterPair[23] = new CharacterPair("1_Keypad", '1');
		characterPair[24] = new CharacterPair("1_Numpad", '1');
		characterPair[25] = new CharacterPair("2_Keypad", '2');
		characterPair[26] = new CharacterPair("2_Numpad", '2');
		characterPair[27] = new CharacterPair("3_Keypad", '3');
		characterPair[28] = new CharacterPair("3_Numpad", '3');
		characterPair[29] = new CharacterPair("4_Keypad", '4');
		characterPair[30] = new CharacterPair("4_Numpad", '4');
		characterPair[31] = new CharacterPair("5_Keypad", '5');
		characterPair[32] = new CharacterPair("5_Numpad", '5');
		characterPair[33] = new CharacterPair("6_Keypad", '6');
		characterPair[34] = new CharacterPair("6_Numpad", '6');
		characterPair[35] = new CharacterPair("7_Keypad", '7');
		characterPair[36] = new CharacterPair("7_Numpad", '7');
		characterPair[37] = new CharacterPair("8_Keypad", '8');
		characterPair[38] = new CharacterPair("8_Numpad", '8');
		characterPair[39] = new CharacterPair("9_Keypad", '9');
		characterPair[40] = new CharacterPair("9_Numpad", '9');
		characterPair[41] = new CharacterPair(":", ':');
		characterPair[42] = new CharacterPair(";", ';');
		characterPair[43] = new CharacterPair("<", '<');
		characterPair[44] = new CharacterPair("=", '=');
		characterPair[45] = new CharacterPair(">", '>');
		characterPair[46] = new CharacterPair("?", '?');
		characterPair[47] = new CharacterPair("@", '@');
		characterPair[48] = new CharacterPair("A", 'A');
		characterPair[49] = new CharacterPair("B", 'B');
		characterPair[50] = new CharacterPair("C", 'C');
		characterPair[51] = new CharacterPair("D", 'D');
		characterPair[52] = new CharacterPair("E", 'E');
		characterPair[53] = new CharacterPair("F", 'F');
		characterPair[54] = new CharacterPair("G", 'G');
		characterPair[55] = new CharacterPair("H", 'H');
		characterPair[56] = new CharacterPair("I", 'I');
		characterPair[57] = new CharacterPair("J", 'J');
		characterPair[58] = new CharacterPair("K", 'K');
		characterPair[59] = new CharacterPair("L", 'L');
		characterPair[60] = new CharacterPair("M", 'M');
		characterPair[61] = new CharacterPair("N", 'N');
		characterPair[62] = new CharacterPair("O", 'O');
		characterPair[63] = new CharacterPair("P", 'P');
		characterPair[64] = new CharacterPair("Q", 'Q');
		characterPair[65] = new CharacterPair("R", 'R');
		characterPair[66] = new CharacterPair("S", 'S');
		characterPair[67] = new CharacterPair("T", 'T');
		characterPair[68] = new CharacterPair("U", 'U');
		characterPair[69] = new CharacterPair("V", 'V');
		characterPair[70] = new CharacterPair("W", 'W');
		characterPair[71] = new CharacterPair("X", 'X');
		characterPair[72] = new CharacterPair("Y", 'Y');
		characterPair[73] = new CharacterPair("Z", 'Z');
		characterPair[74] = new CharacterPair("[", '[');
		characterPair[75] = new CharacterPair("\\", '\\');
		characterPair[76] = new CharacterPair("]", ']');
		characterPair[77] = new CharacterPair("^", '^');
		characterPair[78] = new CharacterPair("_", '_');
		characterPair[79] = new CharacterPair("`", '`');
		characterPair[80] = new CharacterPair("a", 'a');
		characterPair[81] = new CharacterPair("b", 'b');
		characterPair[82] = new CharacterPair("c", 'c');
		characterPair[83] = new CharacterPair("d", 'd');
		characterPair[84] = new CharacterPair("e", 'e');
		characterPair[85] = new CharacterPair("f", 'f');
		characterPair[86] = new CharacterPair("g", 'g');
		characterPair[87] = new CharacterPair("h", 'h');
		characterPair[88] = new CharacterPair("i", 'i');
		characterPair[89] = new CharacterPair("j", 'j');
		characterPair[90] = new CharacterPair("k", 'k');
		characterPair[91] = new CharacterPair("l", 'l');
		characterPair[92] = new CharacterPair("m", 'm');
		characterPair[93] = new CharacterPair("n", 'n');
		characterPair[94] = new CharacterPair("o", 'o');
		characterPair[95] = new CharacterPair("p", 'p');
		characterPair[96] = new CharacterPair("q", 'q');
		characterPair[97] = new CharacterPair("r", 'r');
		characterPair[98] = new CharacterPair("s", 's');
		characterPair[99] = new CharacterPair("t", 't');
		characterPair[100] = new CharacterPair("u", 'u');
		characterPair[101] = new CharacterPair("v", 'v');
		characterPair[102] = new CharacterPair("w", 'w');
		characterPair[103] = new CharacterPair("x", 'x');
		characterPair[104] = new CharacterPair("y", 'y');
		characterPair[105] = new CharacterPair("z", 'z');
		characterPair[106] = new CharacterPair("{", '{');
		characterPair[107] = new CharacterPair("|", '|');
		characterPair[108] = new CharacterPair("}", '}');
		characterPair[109] = new CharacterPair("~", '~');
		characterPair[110] = new CharacterPair("Enter", '\n');
		characterPair[111] = new CharacterPair("Backspace", '\0');
		characterPair[112] = new CharacterPair("Delete", '\0');
	}

	private static final record CharacterPair(String detect, char type) {
	}

	public final void tick() {
		if (!reading)
			return;
		if (repeatingWait > 0)
			repeatingWait--;
		else if (typed.size() > 0) {
			repeatingWait = 50;
			if (typed.getLast() < 111) {
				readText += characterPair[typed.getLast()].type;
			} else if (typed.getLast() == 111) {
				String newReadText = "";
				for (int j = 0; j < readText.length() - 1; j++)
					newReadText += readText.toCharArray()[j];
				readText = newReadText;
			} else
				readText = "";
		}
		if (Sensor.isSignalChanged())
			for (int i = 0; i < characterPair.length; i++) {
				try {
					if (Sensor.detect(characterPair[i].detect)) {
						if (!typed.contains(i)) {
							if (i < 111) {
								typed.addLast(i);
								readText += characterPair[i].type;
							} else if (i == 111) {
								if (typed.size() > 0)
									typed.removeLast();
								typed.addLast(i);
								String newReadText = "";
								for (int j = 0; j < readText.length() - 1; j++)
									newReadText += readText.toCharArray()[j];
								readText = newReadText;
							} else
								readText = "";
							repeatingWait = 500;
						}
					} else {
						for (int j = 0; j < typed.size(); j++)
							if (typed.get(j) == i) {
								typed.remove(j);
								break;
							}
					}
				} catch (UnknownSignalException e) {
					e.printStackTrace();
				}
			}
	}
}