package control;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * มีไว้ตรวจจับสัญญาณ ซึ่งเป็นการกดแป้นพิมพ์หรือการกดเมาส์
 * 
 * @author ณัชพล จำปานิน
 */
public class Sensor {
	private static final boolean[] signal = new boolean[166];

	/**
	 * ถ้าปุ่ม Caps Lock ถูกทำงาน จะมีค่าเป็นจริง มิฉะนั้นจะเป็นเท็จ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static transient boolean CAPS_LOCK = false;

	/**
	 * ถ้าปุ่ม Num Lock ถูกทำงาน จะมีค่าเป็นจริง มิฉะนั้นจะเป็นเท็จ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static transient boolean NUM_LOCK = false;

	private enum SignalChange {
		NO_CHANGE, NEWLY_CHANGE, CHANGE
	}

	private static SignalChange signalChange = SignalChange.NO_CHANGE;

	static final void startSignal(final Signal signal) {
		if (!Sensor.signal[signal.index]) {
			Sensor.signal[signal.index] = true;
			Sensor.signalChange = SignalChange.NEWLY_CHANGE;
		}
	}

	static final void stopSignal(final Signal signal) {
		if (Sensor.signal[signal.index]) {
			Sensor.signal[signal.index] = false;
			Sensor.signalChange = SignalChange.NEWLY_CHANGE;
		}
	}

	private static final boolean detect(final Signal signal) {
		return Sensor.signal[signal.index];
	}

	/**
	 * ไม่รู้จักชื่อสัญญาณ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class UnknownSignalException extends Exception {
		private static final long serialVersionUID = 1L;

		UnknownSignalException() {
			super("ไม่รู้จักชื่อสัญญาณนั้น ๆ");
		}
	}

	/**
	 * ตรวจว่ามีสัญญาณหรือไม่
	 * 
	 * @param signal ชื่อสัญญาณ<br>
	 *               _Literary แปลว่า ไม่สนใจ Caps Lock<br>
	 *               _Button แปลว่า สัญญาณจากปุ่มนั้น ๆ ไม่สนใจ shifted หรือ Caps
	 *               Lock
	 * @return ถ้าตรวจพบสัญญาณนั้น จะส่งค่ากลับเป็นจริง มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
	 * @throws UnknownSignalException ไม่รู้จักชื่อสัญญาณนั้น
	 * @author ณัชพล จำปานิน
	 */
	public static final boolean detect(final String signal) throws UnknownSignalException {
		switch (signal) {
		case " ":
			return detect(Signal.KEY_PRESSED_SPACE);
		case "!":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_1_KEYPAD : Signal.KEY_PRESSED_EXCLAIMATION);
		case "!_Literaly":
			return detect(Signal.KEY_PRESSED_EXCLAIMATION);
		case "\"":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_QUOTE : Signal.KEY_PRESSED_QUOTES);
		case "\"_Literary":
			return detect(Signal.KEY_PRESSED_QUOTES);
		case "#":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_3_KEYPAD : Signal.KEY_PRESSED_NUMBER);
		case "#_Literary":
			return detect(Signal.KEY_PRESSED_NUMBER);
		case "$":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_4_KEYPAD : Signal.KEY_PRESSED_DOLLAR);
		case "$_Literary":
			return detect(Signal.KEY_PRESSED_DOLLAR);
		case "%":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_5_KEYPAD : Signal.KEY_PRESSED_PERCENT);
		case "%_Literary":
			return detect(Signal.KEY_PRESSED_PERCENT);
		case "&":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_7_KEYPAD : Signal.KEY_PRESSED_AMPERSAND);
		case "&_Literary":
			return detect(Signal.KEY_PRESSED_AMPERSAND);
		case "\'":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_QUOTES : Signal.KEY_PRESSED_QUOTE);
		case "\'_Literary":
			return detect(Signal.KEY_PRESSED_QUOTE);
		case "\'_Button":
		case "\"_Button":
			return detect(Signal.KEY_PRESSED_QUOTE) || detect(Signal.KEY_PRESSED_QUOTES);
		case "(":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_9_KEYPAD : Signal.KEY_PRESSED_OPEN_PARENTHESIS);
		case "(_Literary":
			return detect(Signal.KEY_PRESSED_OPEN_PARENTHESIS);
		case ")":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_0_KEYPAD : Signal.KEY_PRESSED_CLOSE_PARENTHESIS);
		case ")_Literary":
			return detect(Signal.KEY_PRESSED_CLOSE_PARENTHESIS);
		case "*":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_8_KEYPAD : Signal.KEY_PRESSED_ASTERISK_KEYPAD)
					|| detect(Signal.KEY_PRESSED_ASTERISK_NUMPAD);
		case "*_Literary":
			return detect(Signal.KEY_PRESSED_ASTERISK);
		case "*_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_8_KEYPAD : Signal.KEY_PRESSED_ASTERISK_KEYPAD);
		case "*_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_ASTERISK_KEYPAD);
		case "*_Numpad":
			return detect(Signal.KEY_PRESSED_ASTERISK_NUMPAD);
		case "+":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_EQUALS : Signal.KEY_PRESSED_PLUS_KEYPAD)
					|| detect(Signal.KEY_PRESSED_PLUS_NUMPAD);
		case "+_Literary":
			return detect(Signal.KEY_PRESSED_PLUS);
		case "+_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_EQUALS : Signal.KEY_PRESSED_PLUS_KEYPAD);
		case "+_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_PLUS_KEYPAD);
		case "+_Numpad":
			return detect(Signal.KEY_PRESSED_PLUS_NUMPAD);
		case ",":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_LESS : Signal.KEY_PRESSED_COMMA);
		case ",_Literary":
			return detect(Signal.KEY_PRESSED_COMMA);
		case ",_Button":
		case "<_Button":
			return detect(Signal.KEY_PRESSED_COMMA) || detect(Signal.KEY_PRESSED_LESS);
		case "-":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_UNDERSCORE : Signal.KEY_PRESSED_HYPHEN_KEYPAD)
					|| detect(Signal.KEY_PRESSED_HYPHEN_NUMPAD);
		case "-_Literary":
			return detect(Signal.KEY_PRESSED_HYPHEN);
		case "-_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_UNDERSCORE : Signal.KEY_PRESSED_HYPHEN_KEYPAD);
		case "-_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_HYPHEN_KEYPAD);
		case "-_Numpad":
			return detect(Signal.KEY_PRESSED_HYPHEN_NUMPAD);
		case "-_Keypad_Button":
		case "__Button":
			return detect(Signal.KEY_PRESSED_HYPHEN_KEYPAD) || detect(Signal.KEY_PRESSED_UNDERSCORE);
		case ".":
			return detect(Signal.KEY_PRESSED_DOT_NUMPAD)
					|| detect((CAPS_LOCK) ? Signal.KEY_PRESSED_GREATER : Signal.KEY_PRESSED_DOT_KEYPAD);
		case "._Literary":
			return detect(Signal.KEY_PRESSED_DOT);
		case "._Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_GREATER : Signal.KEY_PRESSED_DOT_KEYPAD);
		case "._Keypad_Literary":
			return detect(Signal.KEY_PRESSED_DOT_KEYPAD);
		case "._Numpad":
			return detect(Signal.KEY_PRESSED_DOT_NUMPAD);
		case "._Keypad_Button":
		case ">_Button":
			return detect(Signal.KEY_PRESSED_DOT_KEYPAD) || detect(Signal.KEY_PRESSED_GREATER);
		case "/":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_QUESTION : Signal.KEY_PRESSED_SLASH_KEYPAD)
					|| detect(Signal.KEY_PRESSED_SLASH_NUMPAD);
		case "/_Literary":
			return detect(Signal.KEY_PRESSED_SLASH);
		case "/_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_QUESTION : Signal.KEY_PRESSED_SLASH_KEYPAD);
		case "/_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_SLASH_KEYPAD);
		case "/_Numpad":
			return detect(Signal.KEY_PRESSED_SLASH_NUMPAD);
		case "/_Keypad_Button":
		case "?_Button":
			return detect(Signal.KEY_PRESSED_SLASH_KEYPAD) || detect(Signal.KEY_PRESSED_QUESTION);
		case "0":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_CLOSE_PARENTHESIS : Signal.KEY_PRESSED_0_KEYPAD)
					|| detect(Signal.KEY_PRESSED_0_NUMPAD);
		case "0_Literary":
			return detect(Signal.KEY_PRESSED_0);
		case "0_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_CLOSE_PARENTHESIS : Signal.KEY_PRESSED_0_KEYPAD);
		case "0_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_0_KEYPAD);
		case "0_Numpad":
			return detect(Signal.KEY_PRESSED_0_NUMPAD);
		case "0_Keypad_Button":
		case ")_Button":
			return detect(Signal.KEY_PRESSED_0_KEYPAD) || detect(Signal.KEY_PRESSED_CLOSE_PARENTHESIS);
		case "1":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_QUESTION : Signal.KEY_PRESSED_1_KEYPAD)
					|| detect(Signal.KEY_PRESSED_1_NUMPAD);
		case "1_Literary":
			return detect(Signal.KEY_PRESSED_1);
		case "1_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_EXCLAIMATION : Signal.KEY_PRESSED_1_KEYPAD);
		case "1_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_1_KEYPAD);
		case "1_Numpad":
			return detect(Signal.KEY_PRESSED_1_NUMPAD);
		case "1_Keypad_Button":
		case "!_Button":
			return detect(Signal.KEY_PRESSED_1_KEYPAD) || detect(Signal.KEY_PRESSED_EXCLAIMATION);
		case "2":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_AT : Signal.KEY_PRESSED_2_KEYPAD)
					|| detect(Signal.KEY_PRESSED_2_NUMPAD);
		case "2_Literary":
			return detect(Signal.KEY_PRESSED_2);
		case "2_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_AT : Signal.KEY_PRESSED_2_KEYPAD);
		case "2_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_2_KEYPAD);
		case "2_Numpad":
			return detect(Signal.KEY_PRESSED_2_NUMPAD);
		case "2_Keypad_Button":
		case "@_Button":
			return detect(Signal.KEY_PRESSED_2_KEYPAD) || detect(Signal.KEY_PRESSED_AT);
		case "3":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_NUMBER : Signal.KEY_PRESSED_3_KEYPAD)
					|| detect(Signal.KEY_PRESSED_3_NUMPAD);
		case "3_Literary":
			return detect(Signal.KEY_PRESSED_3);
		case "3_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_NUMBER : Signal.KEY_PRESSED_3_KEYPAD);
		case "3_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_3_KEYPAD);
		case "3_Numpad":
			return detect(Signal.KEY_PRESSED_3_NUMPAD);
		case "3_Keypad_Button":
		case "#_Button":
			return detect(Signal.KEY_PRESSED_3_KEYPAD) || detect(Signal.KEY_PRESSED_NUMBER);
		case "4":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_DOLLAR : Signal.KEY_PRESSED_4_KEYPAD)
					|| detect(Signal.KEY_PRESSED_4_NUMPAD);
		case "4_Literary":
			return detect(Signal.KEY_PRESSED_4);
		case "4_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_DOLLAR : Signal.KEY_PRESSED_4_KEYPAD);
		case "4_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_4_KEYPAD);
		case "4_Numpad":
			return detect(Signal.KEY_PRESSED_4_NUMPAD);
		case "4_Keypad_Button":
		case "$_Button":
			return detect(Signal.KEY_PRESSED_4_KEYPAD) || detect(Signal.KEY_PRESSED_DOLLAR);
		case "5":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_PERCENT : Signal.KEY_PRESSED_5_KEYPAD)
					|| detect(Signal.KEY_PRESSED_5_NUMPAD);
		case "5_Literary":
			return detect(Signal.KEY_PRESSED_5);
		case "5_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_PERCENT : Signal.KEY_PRESSED_5_KEYPAD);
		case "5_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_5_KEYPAD);
		case "5_Numpad":
			return detect(Signal.KEY_PRESSED_5_NUMPAD);
		case "5_Keypad_Button":
		case "%_Button":
			return detect(Signal.KEY_PRESSED_5_KEYPAD) || detect(Signal.KEY_PRESSED_PERCENT);
		case "6":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_CARET : Signal.KEY_PRESSED_6_KEYPAD)
					|| detect(Signal.KEY_PRESSED_6_NUMPAD);
		case "6_Literary":
			return detect(Signal.KEY_PRESSED_6);
		case "6_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_CARET : Signal.KEY_PRESSED_6_KEYPAD);
		case "6_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_6_KEYPAD);
		case "6_Numpad":
			return detect(Signal.KEY_PRESSED_6_NUMPAD);
		case "6_Keypad_Button":
		case "^_Button":
			return detect(Signal.KEY_PRESSED_6_KEYPAD) || detect(Signal.KEY_PRESSED_NUMPAD);
		case "7":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_AMPERSAND : Signal.KEY_PRESSED_7_KEYPAD)
					|| detect(Signal.KEY_PRESSED_7_NUMPAD);
		case "7_Literary":
			return detect(Signal.KEY_PRESSED_7);
		case "7_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_AMPERSAND : Signal.KEY_PRESSED_7_KEYPAD);
		case "7_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_7_KEYPAD);
		case "7_Numpad":
			return detect(Signal.KEY_PRESSED_7_NUMPAD);
		case "7_Keypad_Button":
		case "&_Button":
			return detect(Signal.KEY_PRESSED_7_KEYPAD) || detect(Signal.KEY_PRESSED_AMPERSAND);
		case "8":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_ASTERISK : Signal.KEY_PRESSED_8_KEYPAD)
					|| detect(Signal.KEY_PRESSED_8_NUMPAD);
		case "8_Literary":
			return detect(Signal.KEY_PRESSED_8);
		case "8_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_ASTERISK_KEYPAD : Signal.KEY_PRESSED_8_KEYPAD);
		case "8_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_8_KEYPAD);
		case "8_Numpad":
			return detect(Signal.KEY_PRESSED_8_NUMPAD);
		case "8_Keypad_Button":
		case "*_Button":
			return detect(Signal.KEY_PRESSED_8_KEYPAD) || detect(Signal.KEY_PRESSED_ASTERISK_KEYPAD);
		case "9":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_OPEN_PARENTHESIS : Signal.KEY_PRESSED_9_KEYPAD)
					|| detect(Signal.KEY_PRESSED_9_NUMPAD);
		case "9_Literary":
			return detect(Signal.KEY_PRESSED_9);
		case "9_Keypad":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_OPEN_PARENTHESIS : Signal.KEY_PRESSED_9_KEYPAD);
		case "9_Keypad_Literary":
			return detect(Signal.KEY_PRESSED_9_KEYPAD);
		case "9_Numpad":
			return detect(Signal.KEY_PRESSED_9_NUMPAD);
		case "9_Keypad_Button":
		case "(_Button":
			return detect(Signal.KEY_PRESSED_9_KEYPAD) || detect(Signal.KEY_PRESSED_OPEN_PARENTHESIS);
		case ":":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_SEMICOLON : Signal.KEY_PRESSED_COLON);
		case ":_Literary":
			return detect(Signal.KEY_PRESSED_COLON);
		case ";":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_COLON : Signal.KEY_PRESSED_SEMICOLON);
		case ";_Literary":
			return detect(Signal.KEY_PRESSED_SEMICOLON);
		case ";_Button":
		case ":_Button":
			return detect(Signal.KEY_PRESSED_SEMICOLON) || detect(Signal.KEY_PRESSED_COLON);
		case "<":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_COMMA : Signal.KEY_PRESSED_LESS);
		case "<_Literary":
			return detect(Signal.KEY_PRESSED_LESS);
		case "=":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_PLUS_KEYPAD : Signal.KEY_PRESSED_EQUALS);
		case "=_Literary":
			return detect(Signal.KEY_PRESSED_EQUALS);
		case "=_Button":
		case "+_Keypad_Button":
			return detect(Signal.KEY_PRESSED_EQUALS) || detect(Signal.KEY_PRESSED_PLUS_KEYPAD);
		case ">":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_DOT_KEYPAD : Signal.KEY_PRESSED_GREATER);
		case ">_Literary":
			return detect(Signal.KEY_PRESSED_GREATER);
		case "?":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_SLASH_KEYPAD : Signal.KEY_PRESSED_QUESTION);
		case "?_Literary":
			return detect(Signal.KEY_PRESSED_QUESTION);
		case "@":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_2_KEYPAD : Signal.KEY_PRESSED_AT);
		case "@_Literary":
			return detect(Signal.KEY_PRESSED_AT);
		case "A":
			return detect(Signal.KEY_PRESSED_A);
		case "B":
			return detect(Signal.KEY_PRESSED_B);
		case "C":
			return detect(Signal.KEY_PRESSED_C);
		case "D":
			return detect(Signal.KEY_PRESSED_D);
		case "E":
			return detect(Signal.KEY_PRESSED_E);
		case "F":
			return detect(Signal.KEY_PRESSED_F);
		case "G":
			return detect(Signal.KEY_PRESSED_G);
		case "H":
			return detect(Signal.KEY_PRESSED_H);
		case "I":
			return detect(Signal.KEY_PRESSED_I);
		case "J":
			return detect(Signal.KEY_PRESSED_J);
		case "K":
			return detect(Signal.KEY_PRESSED_K);
		case "L":
			return detect(Signal.KEY_PRESSED_L);
		case "M":
			return detect(Signal.KEY_PRESSED_M);
		case "N":
			return detect(Signal.KEY_PRESSED_N);
		case "O":
			return detect(Signal.KEY_PRESSED_O);
		case "P":
			return detect(Signal.KEY_PRESSED_P);
		case "Q":
			return detect(Signal.KEY_PRESSED_Q);
		case "R":
			return detect(Signal.KEY_PRESSED_R);
		case "S":
			return detect(Signal.KEY_PRESSED_S);
		case "T":
			return detect(Signal.KEY_PRESSED_T);
		case "U":
			return detect(Signal.KEY_PRESSED_U);
		case "V":
			return detect(Signal.KEY_PRESSED_V);
		case "W":
			return detect(Signal.KEY_PRESSED_W);
		case "X":
			return detect(Signal.KEY_PRESSED_X);
		case "Y":
			return detect(Signal.KEY_PRESSED_Y);
		case "Z":
			return detect(Signal.KEY_PRESSED_Z);
		case "[":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_OPEN_BRACE : Signal.KEY_PRESSED_OPEN_BRACKET);
		case "[_Literary":
			return detect(Signal.KEY_PRESSED_OPEN_BRACKET);
		case "[_Button":
		case "{_Button":
			return detect(Signal.KEY_PRESSED_OPEN_BRACKET) || detect(Signal.KEY_PRESSED_OPEN_BRACE);
		case "\\":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_BAR : Signal.KEY_PRESSED_BACKSLASH);
		case "\\_Literary":
			return detect(Signal.KEY_PRESSED_BACKSLASH);
		case "\\_Button":
		case "|_Button":
			return detect(Signal.KEY_PRESSED_BACKSLASH) || detect(Signal.KEY_PRESSED_BAR);
		case "]":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_CLOSE_BRACE : Signal.KEY_PRESSED_CLOSE_BRACKET);
		case "]_Literary":
			return detect(Signal.KEY_PRESSED_CLOSE_BRACKET);
		case "]_Button":
		case "}_Button":
			return detect(Signal.KEY_PRESSED_CLOSE_BRACKET) || detect(Signal.KEY_PRESSED_CLOSE_BRACE);
		case "^":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_6_KEYPAD : Signal.KEY_PRESSED_CARET);
		case "^_Literary":
			return detect(Signal.KEY_PRESSED_CARET);
		case "_":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_HYPHEN_KEYPAD : Signal.KEY_PRESSED_UNDERSCORE);
		case "__Literary":
			return detect(Signal.KEY_PRESSED_UNDERSCORE);
		case "`":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_TILDE : Signal.KEY_PRESSED_GRAVE);
		case "`_Literary":
			return detect(Signal.KEY_PRESSED_GRAVE);
		case "`_Button":
		case "~_Button":
			return detect(Signal.KEY_PRESSED_GRAVE) || detect(Signal.KEY_PRESSED_TILDE);
		case "a":
			return detect(Signal.KEY_PRESSED_a);
		case "a_Button":
		case "A_Button":
			return detect(Signal.KEY_PRESSED_a) || detect(Signal.KEY_PRESSED_A);
		case "b":
			return detect(Signal.KEY_PRESSED_b);
		case "b_Button":
		case "B_Button":
			return detect(Signal.KEY_PRESSED_b) || detect(Signal.KEY_PRESSED_B);
		case "c":
			return detect(Signal.KEY_PRESSED_c);
		case "c_Button":
		case "C_Button":
			return detect(Signal.KEY_PRESSED_c) || detect(Signal.KEY_PRESSED_C);
		case "d":
			return detect(Signal.KEY_PRESSED_d);
		case "d_Button":
		case "D_Button":
			return detect(Signal.KEY_PRESSED_d) || detect(Signal.KEY_PRESSED_D);
		case "e":
			return detect(Signal.KEY_PRESSED_e);
		case "e_Button":
		case "E_Button":
			return detect(Signal.KEY_PRESSED_e) || detect(Signal.KEY_PRESSED_E);
		case "f":
			return detect(Signal.KEY_PRESSED_f);
		case "f_Button":
		case "F_Button":
			return detect(Signal.KEY_PRESSED_f) || detect(Signal.KEY_PRESSED_F);
		case "g":
			return detect(Signal.KEY_PRESSED_g);
		case "g_Button":
		case "G_Button":
			return detect(Signal.KEY_PRESSED_g) || detect(Signal.KEY_PRESSED_G);
		case "h":
			return detect(Signal.KEY_PRESSED_h);
		case "h_Button":
		case "H_Button":
			return detect(Signal.KEY_PRESSED_h) || detect(Signal.KEY_PRESSED_H);
		case "i":
			return detect(Signal.KEY_PRESSED_i);
		case "i_Button":
		case "I_Button":
			return detect(Signal.KEY_PRESSED_i) || detect(Signal.KEY_PRESSED_I);
		case "j":
			return detect(Signal.KEY_PRESSED_j);
		case "j_Button":
		case "J_Button":
			return detect(Signal.KEY_PRESSED_j) || detect(Signal.KEY_PRESSED_J);
		case "k":
			return detect(Signal.KEY_PRESSED_k);
		case "k_Button":
		case "K_Button":
			return detect(Signal.KEY_PRESSED_k) || detect(Signal.KEY_PRESSED_K);
		case "l":
			return detect(Signal.KEY_PRESSED_l);
		case "l_Button":
		case "L_Button":
			return detect(Signal.KEY_PRESSED_l) || detect(Signal.KEY_PRESSED_L);
		case "m":
			return detect(Signal.KEY_PRESSED_m);
		case "m_Button":
		case "M_Button":
			return detect(Signal.KEY_PRESSED_m) || detect(Signal.KEY_PRESSED_M);
		case "n":
			return detect(Signal.KEY_PRESSED_n);
		case "n_Button":
		case "N_Button":
			return detect(Signal.KEY_PRESSED_n) || detect(Signal.KEY_PRESSED_N);
		case "o":
			return detect(Signal.KEY_PRESSED_o);
		case "o_Button":
		case "O_Button":
			return detect(Signal.KEY_PRESSED_o) || detect(Signal.KEY_PRESSED_O);
		case "p":
			return detect(Signal.KEY_PRESSED_p);
		case "p_Button":
		case "P_Button":
			return detect(Signal.KEY_PRESSED_p) || detect(Signal.KEY_PRESSED_P);
		case "q":
			return detect(Signal.KEY_PRESSED_q);
		case "q_Button":
		case "Q_Button":
			return detect(Signal.KEY_PRESSED_q) || detect(Signal.KEY_PRESSED_Q);
		case "r":
			return detect(Signal.KEY_PRESSED_r);
		case "r_Button":
		case "R_Button":
			return detect(Signal.KEY_PRESSED_r) || detect(Signal.KEY_PRESSED_R);
		case "s":
			return detect(Signal.KEY_PRESSED_s);
		case "s_Button":
		case "S_Button":
			return detect(Signal.KEY_PRESSED_s) || detect(Signal.KEY_PRESSED_S);
		case "t":
			return detect(Signal.KEY_PRESSED_t);
		case "t_Button":
		case "T_Button":
			return detect(Signal.KEY_PRESSED_t) || detect(Signal.KEY_PRESSED_T);
		case "u":
			return detect(Signal.KEY_PRESSED_u);
		case "u_Button":
		case "U_Button":
			return detect(Signal.KEY_PRESSED_u) || detect(Signal.KEY_PRESSED_U);
		case "v":
			return detect(Signal.KEY_PRESSED_v);
		case "v_Button":
		case "V_Button":
			return detect(Signal.KEY_PRESSED_v) || detect(Signal.KEY_PRESSED_V);
		case "w":
			return detect(Signal.KEY_PRESSED_w);
		case "w_Button":
		case "W_Button":
			return detect(Signal.KEY_PRESSED_w) || detect(Signal.KEY_PRESSED_W);
		case "x":
			return detect(Signal.KEY_PRESSED_x);
		case "x_Button":
		case "X_Button":
			return detect(Signal.KEY_PRESSED_x) || detect(Signal.KEY_PRESSED_X);
		case "y":
			return detect(Signal.KEY_PRESSED_y);
		case "y_Button":
		case "Y_Button":
			return detect(Signal.KEY_PRESSED_y) || detect(Signal.KEY_PRESSED_Y);
		case "z":
			return detect(Signal.KEY_PRESSED_z);
		case "z_Button":
		case "Z_Button":
			return detect(Signal.KEY_PRESSED_z) || detect(Signal.KEY_PRESSED_Z);
		case "{":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_OPEN_BRACKET : Signal.KEY_PRESSED_OPEN_BRACE);
		case "{_Literary":
			return detect(Signal.KEY_PRESSED_OPEN_BRACE);
		case "|":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_BACKSLASH : Signal.KEY_PRESSED_BAR);
		case "|_Literary":
			return detect(Signal.KEY_PRESSED_BAR);
		case "}":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_CLOSE_BRACKET : Signal.KEY_PRESSED_CLOSE_BRACE);
		case "}_Literary":
			return detect(Signal.KEY_PRESSED_CLOSE_BRACE);
		case "~":
			return detect((CAPS_LOCK) ? Signal.KEY_PRESSED_GRAVE : Signal.KEY_PRESSED_TILDE);
		case "~_Literary":
			return detect(Signal.KEY_PRESSED_TILDE);
		case "Backspace":
			return detect(Signal.KEY_PRESSED_BACKSPACE);
		case "Enter":
			return detect(Signal.KEY_PRESSED_ENTER);
		case "Shift":
			return detect(Signal.KEY_PRESSED_SHIFT);
		case "Ctrl":
			return detect(Signal.KEY_PRESSED_CTRL);
		case "Alt":
			return detect(Signal.KEY_PRESSED_ALT);
		case "Break":
			return detect(Signal.KEY_PRESSED_BREAK);
		case "CapsLock":
		case "Caps":
			return detect(Signal.KEY_PRESSED_CAPS_LOCK);
		case "Esc":
			return detect(Signal.KEY_PRESSED_ESC);
		case "PageUp":
		case "PgUp":
			return detect(Signal.KEY_PRESSED_PAGE_UP);
		case "PageDown":
		case "PgDn":
			return detect(Signal.KEY_PRESSED_PAGE_DOWN);
		case "End":
			return detect(Signal.KEY_PRESSED_END);
		case "Home":
			return detect(Signal.KEY_PRESSED_HOME);
		case "Left":
		case "<-":
			return detect(Signal.KEY_PRESSED_LEFT);
		case "Up":
		case "^^":
			return detect(Signal.KEY_PRESSED_UP);
		case "Right":
		case "->":
			return detect(Signal.KEY_PRESSED_RIGHT);
		case "Down":
		case "VV":
			return detect(Signal.KEY_PRESSED_DOWN);
		case "Delete":
		case "Del":
			return detect(Signal.KEY_PRESSED_DELETE);
		case "NumLock":
			return detect(Signal.KEY_PRESSED_NUM_LOCK);
		case "Insert":
			return detect(Signal.KEY_PRESSED_INSERT);
		case "PrintScreen":
		case "PrtSc":
			return detect(Signal.KEY_PRESSED_PRINT_SCREEN);
		case "Alphabet":
		case "Alph":
			return detect(Signal.KEY_PRESSED_ALPHABET);
		case "CapitalizedCharacter":
		case "CapChar":
			return detect(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		case "NonCapitalizedCharacter":
		case "NonCapChar":
			return detect(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		case "NumericKeypad":
		case "NumKeypad":
			return detect(Signal.KEY_PRESSED_KEYPAD);
		case "NumericNumpad":
		case "NumNumpad":
			return detect(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		case "Numeric":
		case "Num":
			return detect(Signal.KEY_PRESSED_NUMERIC);
		case "Character":
		case "Char":
			return detect(Signal.KEY_PRESSED_CHARACTER);
		case "ShiftedCharacter":
		case "ShiftedChar":
			return detect(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		case "UnshiftedCharacter":
		case "UnshiftedChar":
			return detect(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		case "Keypad":
			return detect(Signal.KEY_PRESSED_KEYPAD);
		case "Numpad":
			return detect(Signal.KEY_PRESSED_NUMPAD);
		case "Arrow":
		case "<->":
			return detect(Signal.KEY_PRESSED_ARROW);
		case "Function":
		case "Funct":
			return detect(Signal.KEY_PRESSED_FUNCTION);
		case "KeyPressed":
		case "Key":
			return detect(Signal.KEY_PRESSED);
		case "LeftClick":
		case "<O":
			return detect(Signal.MOUSE_PRESSED_LEFT);
		case "MiddleClick":
		case "()":
			return detect(Signal.MOUSE_PRESSED_MIDDLE);
		case "RightClick":
		case "O>":
			return detect(Signal.MOUSE_PRESSED_RIGHT);
		case "Click":
		case "<>":
			return detect(Signal.MOUSE_PRESSED);
		case "MoveUp":
		case "(^)":
			return detect(Signal.MOUSE_WHEEL_MOVED_UP);
		case "MoveDown":
		case "(V)":
			return detect(Signal.MOUSE_WHEEL_MOVED_DOWN);
		case "(|)":
			return detect(Signal.MOUSE_WHEEL_MOVED);
		default:
			throw new UnknownSignalException();
		}
	}

	/**
	 * ตรวจสอบว่าสัญญาณมีการเปลี่ยนแปลงหรือไม่<br>
	 * มีไว้เพิ่มประสิทธิภาพของโปรแกรมโดยการเว้นไม่ให้ทำงาน<br>
	 * เมื่อสัญญาณไม่มีการเปลี่ยนแปลง
	 * 
	 * @return ถ้าสัญญาณมีการเปลี่ยนแปลง<br>
	 *         จะส่งค่ากลับเป็นจริง<br>
	 *         มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
	 * @author ณัชพล จำปานิน
	 */
	public static final boolean isSignalChanged() {
		return signalChange != SignalChange.NO_CHANGE;
	}

	/**
	 * อ่านค่าตำแหน่งเมาส์ในแนวแกน x
	 * 
	 * @return แนวแกน x ของเมาส์ที่อ่านได้
	 * @author ณัชพล จำปานิน
	 */
	public static final double getPointerX() {
		return MouseInput.getPointerX();
	}

	/**
	 * อ่านค่าตำแหน่งเมาส์ในแนวแกน y
	 * 
	 * @return แนวแกน y ของเมาส์ที่อ่านได้
	 * @author ณัชพล จำปานิน
	 */
	public static final double getPointerY() {
		return MouseInput.getPointerY() - Painters.TOP_BORDER;
	}

	/**
	 * เป็นเครื่องจับเวลาที่ตรวจจับสัญญาณ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class Counter {

		/**
		 * เวลาของเครื่องจับเวลามีค่าติดลบ
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class NegativeCounterDurationException extends Exception {
			private static final long serialVersionUID = 1L;

			NegativeCounterDurationException() {
				super("เวลาของเครื่องจับเวลามีค่าติดลบไม่ได้");
			}
		}

		/**
		 * เครื่องจับเวลามีชื่อซ้ำกัน
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class DuplicatedCounterNameException extends Exception {
			private static final long serialVersionUID = 1L;

			DuplicatedCounterNameException() {
				super("เครื่องจับเวลามีชื่อซ้ำกันไม่ได้");
			}
		}

		/**
		 * เครื่องจับเวลามีชื่อที่สงวนไว้ซ้ำกัน
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class DuplicatedReservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			DuplicatedReservedCounterException() {
				super("เครื่องจับเวลามีชื่อซ้ำกันไม่ได้");
			}
		}

		/**
		 * ลบเครื่องจับเวลาที่ไม่รู้จัก
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class NotExistedCounterNameException extends Exception {
			private static final long serialVersionUID = 1L;

			NotExistedCounterNameException() {
				super("ลบเครื่องจับเวลาที่ไม่รู้จักชื่อไม่ได้");
			}
		}

		private static final Counters getCounter(final String name) throws NotExistedCounterNameException {
			for (Counters i : counters) {
				if (i.name.compareTo(name) == 0)
					return i;
			}
			throw new NotExistedCounterNameException();
		}

		private static enum CounterType {
			WAITED, STUCK, TAPPED
		}

		private static final ArrayList<Counters> counters = new ArrayList<Counters>();

		private static final class Counters {
			private String name;
			private CounterType counterType;
			private String signal;
			private int duration;
			private boolean running;
			private boolean timeOut;
			private boolean finished;
			private boolean justFinished;
			private long timer;
			private boolean reserved;

			Counters(final String name, final CounterType counterType, final String signal, final int duration,
					final boolean reserved) throws DuplicatedReservedCounterException, DuplicatedCounterNameException,
					NegativeCounterDurationException {
				for (Counters i : counters)
					if (i.name.compareTo(name) == 0) {
						if (i.isReserved())
							throw new DuplicatedReservedCounterException();
						else
							throw new DuplicatedCounterNameException();
					}
				if (duration < 0)
					throw new NegativeCounterDurationException();
				this.name = name;
				this.counterType = counterType;
				this.signal = signal;
				this.duration = duration;
				running = true;
				justFinished = false;
				timer = 0;
				finished = false;
				this.reserved = reserved;
			}

			final long getTimer() {
				return timer;
			}

			final void change(final int number) {
				duration += number;
			}

			final void start() {
				running = true;
			}

			final void stop() {
				running = false;
			}

			final boolean isFinished() {
				return finished;
			}

			final boolean isJustFinished() {
				return justFinished;
			}

			final void reset() {
				timer = 0;
				finished = false;
			}

			final boolean isReserved() {
				return reserved;
			}

			final void nanoTick() throws UnknownSignalException {
				if (justFinished)
					justFinished = false;
				if (running && !timeOut)
					timer++;
				if (timer > duration)
					timeOut = true;
				else
					timeOut = false;
				switch (counterType) {
				case WAITED:
					if (detect(signal)) {
						if (timeOut)
							finished = true;
						else
							timer = 0;
					} else {
						if (finished)
							reset();
					}
					break;
				case STUCK:
				case TAPPED:
					if (detect(signal)) {
						if (timeOut && !finished) {
							finished = true;
							justFinished = true;
						}
					} else {
						if (finished)
							reset();
						else
							timer = 0;
					}

					break;
				}
			}
		}

		/**
		 * เพิ่มเครื่องจับเวลาแบบ Waited<br>
		 * เครื่องจับเวลาชนิดนี้จะสิ้นสุดเมื่อรอระยะเวลาหนึ่ง<br>
		 * ก่อนที่สัญญาณจะตรวจจับ
		 * 
		 * @param name     ชื่อเครื่องจับเวลา
		 * @param signals  สัญญาณที่ใช้จับ
		 * @param duration ระยะเวลา
		 * @throws NegativeCounterDurationException   ระยะเวลาติดลบ
		 * @throws DuplicatedCounterNameException     ชื่อซ้ำ
		 * @throws DuplicatedReservedCounterException ชื่อซ้ำกับเครื่องจับเวลาที่สงวนไว้
		 * @author ณัชพล จำปานิน
		 */
		public static final void addWaited(final String name, final String signals, final int duration)
				throws DuplicatedCounterNameException, NegativeCounterDurationException,
				DuplicatedReservedCounterException {
			counters.add(new Counters(name, CounterType.WAITED, signals, duration, false));
		}

		static final void addReservedWaited(final String name, final String signals, final int duration)
				throws DuplicatedReservedCounterException, DuplicatedCounterNameException,
				NegativeCounterDurationException {
			counters.add(new Counters(name, CounterType.WAITED, signals, duration, true));
		}

		/**
		 * เพิ่มเครื่องจับเวลาแบบ Stuck<br>
		 * เครื่องจับเวลาชนิดนี้จะสิ้นสุดเมื่อตรวจจับสัญญาณ<br>
		 * เป็นระยะเวลาหนึ่ง
		 * 
		 * @param name     ชื่อเครื่องจับเวลา
		 * @param signals  สัญญาณจับเวลา
		 * @param duration ระยะเวลา
		 * @throws DuplicatedReservedCounterException ชื่อซ้ำกับเครื่องจับเวลาที่สงวนไว้
		 * @throws DuplicatedCounterNameException     ชื่อซ้ำ
		 * @throws NegativeCounterDurationException   ระยะเวลาติดลบ
		 * @author ณัชพล จำปานิน
		 */
		public static final void addStuck(final String name, final String signals, final int duration)
				throws DuplicatedReservedCounterException, DuplicatedCounterNameException,
				NegativeCounterDurationException {
			counters.add(new Counters(name, CounterType.STUCK, signals, duration, false));
		}

		static final void addReservedStuck(final String name, final String signals, final int duration)
				throws DuplicatedReservedCounterException, DuplicatedCounterNameException,
				NegativeCounterDurationException {
			counters.add(new Counters(name, CounterType.STUCK, signals, duration, true));
		}

		/**
		 * เพิ่มเครื่องจับเวลาแบบ Tapped<br>
		 * เครื่องจับเวลาชนิดนี้จะสิ้นสุดเมื่อตรวจจับสัญญาณทันที
		 * 
		 * @param name    ชื่อเครื่องจับเวลา
		 * @param signals สัญญาณจับเวลา
		 * @throws DuplicatedReservedCounterException ชื่อซ้ำกับเครื่องจับเวลาที่สงวนไว้
		 * @throws DuplicatedCounterNameException     ชื่อซ้ำ
		 * @throws NegativeCounterDurationException   ระยะเวลาติดลบ
		 * @author ณัชพล จำปานิน
		 */
		public static final void addTapped(final String name, final String signals)
				throws DuplicatedReservedCounterException, DuplicatedCounterNameException,
				NegativeCounterDurationException {
			counters.add(new Counters(name, CounterType.TAPPED, signals, 0, false));
		}

		static final void addReservedTapped(final String name, final String signals)
				throws DuplicatedReservedCounterException, DuplicatedCounterNameException,
				NegativeCounterDurationException {
			counters.add(new Counters(name, CounterType.TAPPED, signals, 0, true));
		}

		/**
		 * ลบเครื่องจับเวลาที่สงวนไว้
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class RemoveReservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			RemoveReservedCounterException() {
				super("ลบเครื่องจับเวลาที่สงวนไว้ไม่ได้");
			}
		}

		/**
		 * ลบเครื่องจับเวลา
		 * 
		 * @param name ชื่อ
		 * @throws NotExistedCounterNameException ไม่พบเครื่องจับเวลาชื่อนั้น
		 * @throws RemoveReservedCounterException ลบเครื่องจับเวลาสงวน
		 * @author ณัชพล จำปานิน
		 */
		public static final void remove(final String name)
				throws NotExistedCounterNameException, RemoveReservedCounterException {
			if (getCounter(name).isReserved())
				throw new RemoveReservedCounterException();
			else
				counters.remove(getCounter(name));
		}

		static final class RemoveUnreservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			RemoveUnreservedCounterException() {
				super("ลบเครื่องจับเวลาที่ไม่ได้สงวนไม่ได้");
			}
		}

		static final void removeReserved(final String name)
				throws NotExistedCounterNameException, RemoveUnreservedCounterException {
			if (getCounter(name).isReserved())
				counters.remove(getCounter(name));
			else
				throw new RemoveUnreservedCounterException();
		}

		public static final long getTimer(final String name) throws NotExistedCounterNameException {
			return getCounter(name).getTimer();
		}

		/**
		 * เปลี่ยนระยะเวลาเครื่องจับเวลาสงวน
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class ChangeReservedCounterDurationException extends Exception {
			private static final long serialVersionUID = 1L;

			ChangeReservedCounterDurationException() {
				super("เปลี่ยนระยะเวลาเครื่องจับเวลาสงวนไม่ได้");
			}
		}

		/**
		 * เปลี่ยนระยะเวลาเครื่องจับเวลา
		 * 
		 * @param name   ชื่อ
		 * @param change จำนวนที่ต้องการเปลี่ยน
		 * @throws NotExistedCounterNameException         ไม่พบเครื่องจับเวลาชื่อนั้น
		 * @throws ChangeReservedCounterDurationException เปลี่ยนเวลาเครื่องจับเวลาสงวน
		 * @author ณัชพล จำปานิน
		 */
		public static final void changeDuration(final String name, final int change)
				throws NotExistedCounterNameException, ChangeReservedCounterDurationException {
			if (getCounter(name).isReserved())
				throw new ChangeReservedCounterDurationException();
			else
				getCounter(name).change(change);
		}

		static final class ChangeUnreservedCounterDurationException extends Exception {
			private static final long serialVersionUID = 1L;

			ChangeUnreservedCounterDurationException() {
				super("เปลี่ยนเครื่องจัยเวลาที่ไม่ได้สงวนไม่ได้");
			}
		}

		static final void changeReservedDuration(final String name, final int change)
				throws NotExistedCounterNameException, ChangeUnreservedCounterDurationException {
			if (getCounter(name).isReserved())
				getCounter(name).change(change);
			else
				throw new ChangeUnreservedCounterDurationException();
		}

		/**
		 * ทำให้เครื่องจับเวลาสงวนทำงานต่อ
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class ContinueReservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			ContinueReservedCounterException() {
				super("ทำให้เครื่องจับเวลาสงวนทำงานต่อไม่ได้");
			}
		}

		/**
		 * ทำให้เครื่องจับเวลาทำงานต่อ
		 * 
		 * @param name ชื่อ
		 * @throws NotExistedCounterNameException   ไม่พบเครื่องจับเวลาชื่อนั้น
		 * @throws ContinueReservedCounterException ทำให้เครื่องจับเวลาสงวนทำงานต่อ
		 * @author ณัชพล จำปานิน
		 */
		public static final void continues(final String name)
				throws NotExistedCounterNameException, ContinueReservedCounterException {
			if (getCounter(name).isReserved())
				throw new ContinueReservedCounterException();
			else
				getCounter(name).start();
		}

		static final class ContinueUnreservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			ContinueUnreservedCounterException() {
				super("ทำให้เครื่องจับเวลาที่ไม่ได้สงวนทำงานต่อไม่ได้");
			}
		}

		static final void continueReserved(final String name)
				throws NotExistedCounterNameException, ContinueUnreservedCounterException {
			if (getCounter(name).isReserved())
				getCounter(name).start();
			else
				throw new ContinueUnreservedCounterException();
		}

		/**
		 * หยุดเครื่องจับเวลาสงวน
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class PauseReservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			PauseReservedCounterException() {
				super("หยุดเครื่องจับเวลาสงวนไม่ได้");
			}
		}

		/**
		 * หยุดเครื่องจับเวลา
		 * 
		 * @param name ชื่อ
		 * @throws NotExistedCounterNameException ไม่พบเครื่องจับเวลาชื่อนั้น
		 * @throws PauseReservedCounterException  หยุดเครื่องจับเวลาสงวน
		 * @author ณัชพล จำปานิน
		 */
		public static final void pause(final String name)
				throws NotExistedCounterNameException, PauseReservedCounterException {
			if (getCounter(name).isReserved())
				throw new PauseReservedCounterException();
			else
				getCounter(name).stop();
		}

		static final class PauseUnreservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			PauseUnreservedCounterException() {
				super("หยุดเครื่องจับเวลาที่ไม่ได้สงวนไม่ได้");
			}
		}

		static final void pauseReserved(final String name)
				throws NotExistedCounterNameException, PauseUnreservedCounterException {
			if (getCounter(name).isReserved())
				getCounter(name).stop();
			else
				throw new PauseUnreservedCounterException();
		}

		/**
		 * รีเซ็ตเครื่องจับเวลาสงวน
		 * 
		 * @author ณัชพล จำปานิน
		 */
		public static final class ResetReservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			ResetReservedCounterException() {
				super("รีเซ็ตเครื่องจับเวลาสงวนไม่ได้");
			}
		}

		/**
		 * รีเซ็ตเครื่องจับเวลา
		 * 
		 * @param name ชื่อ
		 * @throws NotExistedCounterNameException ไม่พบเครื่องจับเวลาชื่อนั้น
		 * @throws ResetReservedCounterException  รีเซ็ดเครื่องจับเวลาสงวน
		 * @author ณัชพล จำปานิน
		 */
		public static final void reset(final String name)
				throws NotExistedCounterNameException, ResetReservedCounterException {
			if (getCounter(name).isReserved())
				throw new ResetReservedCounterException();
			else
				getCounter(name).reset();
		}

		static final class ResetUnreservedCounterException extends Exception {
			private static final long serialVersionUID = 1L;

			ResetUnreservedCounterException() {
				super("รีเซ็ตเครื่องจับเวลาที่ไม่ได้สงวนไม่ได้");
			}
		}

		static final void resetReserved(final String name)
				throws NotExistedCounterNameException, ResetUnreservedCounterException {
			if (getCounter(name).isReserved())
				getCounter(name).reset();
			else
				throw new ResetUnreservedCounterException();
		}

		/**
		 * ดูว่าเครื่องจับเวลาทำงานเสร็จสิ้นแล้วหรือไม่
		 * 
		 * @param name ชื่อ
		 * @return ถ้าเครื่องจับเวลาทำงานเสร็จสิ้นแล้ว จะส่งค่ากลับเป็นจริง<br>
		 *         มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
		 * @throws NotExistedCounterNameException ไม่พบเครือ่งจับเวลาชื่อนั้น
		 * @author ณัชพล จำปานิน
		 */
		public static final boolean isCounterFinished(final String name) throws NotExistedCounterNameException {
			return getCounter(name).isFinished();
		}

		/**
		 * ดูว่าเครื่องจับเวลาเพิ่งทำงานเสร็จสิ้นแล้วหรือไม่
		 * 
		 * @param name ชื่อ
		 * @return ถ้าเครื่องจับเวลาเพิ่งทำงานเสร็จสิ้นแล้ว จะส่งค่ากลับเป็นจริง<br>
		 *         มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
		 * @throws NotExistedCounterNameException ไม่พบเครือ่งจับเวลาชื่อนั้น
		 * @author ณัชพล จำปานิน
		 */
		public static final boolean isCounterJustFinished(final String name) throws NotExistedCounterNameException {
			return getCounter(name).isJustFinished();
		}

		/**
		 * ดูว่าเครื่องจับเวลานั้นถูกสงวนไว้หรือไม่
		 * 
		 * @param name ชื่อ
		 * @return ถ้าเครื่องจับเวลาถูกสงวนไว้ จะส่งค่ากลับเป็นจริง<br>
		 *         มิฉะนั้นจะส่งค่ากลับเป็นเท็จ
		 * @throws NotExistedCounterNameException ไม่พบเครือ่งจับเวลาชื่อนั้น
		 * @author ณัชพล จำปานิน
		 */
		public static final boolean isCounterReserved(final String name) throws NotExistedCounterNameException {
			return getCounter(name).isReserved();
		}
	}

	static final void tick() throws UnknownSignalException {
		CAPS_LOCK = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		NUM_LOCK = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		if (signalChange == SignalChange.NEWLY_CHANGE)
			signalChange = SignalChange.CHANGE;
		else
			signalChange = SignalChange.NO_CHANGE;
		for (Counter.Counters i : Counter.counters)
			i.nanoTick();

		// Capitalized character
		if (detect(Signal.KEY_PRESSED_A))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_B))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_C))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_D))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_E))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_F))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_G))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_H))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_I))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_J))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_K))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_L))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_M))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_N))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_O))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_P))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_Q))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_R))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_S))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_T))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_U))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_V))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_W))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_X))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_Y))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_Z))
			startSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);
		else
			stopSignal(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER);

		// Non-capitalized Character
		if (detect(Signal.KEY_PRESSED_a))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_b))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_c))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_d))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_e))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_f))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_g))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_h))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_i))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_j))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_k))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_l))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_m))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_n))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_o))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_p))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_q))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_r))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_s))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_t))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_u))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_v))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_w))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_x))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_y))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_z))
			startSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);
		else
			stopSignal(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER);

		// Character
		if (detect(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER))
			startSignal(Signal.KEY_PRESSED_ALPHABET);
		else if (detect(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER))
			startSignal(Signal.KEY_PRESSED_ALPHABET);
		else
			stopSignal(Signal.KEY_PRESSED_ALPHABET);

		// keyPressed_Asterisk
		if (detect(Signal.KEY_PRESSED_ASTERISK_KEYPAD))
			startSignal(Signal.KEY_PRESSED_ASTERISK);
		else if (detect(Signal.KEY_PRESSED_ASTERISK_NUMPAD))
			startSignal(Signal.KEY_PRESSED_ASTERISK);
		else
			stopSignal(Signal.KEY_PRESSED_ASTERISK);

		// keyPressed_Plus
		if (detect(Signal.KEY_PRESSED_PLUS_KEYPAD))
			startSignal(Signal.KEY_PRESSED_PLUS);
		else if (detect(Signal.KEY_PRESSED_PLUS_NUMPAD))
			startSignal(Signal.KEY_PRESSED_PLUS);
		else
			stopSignal(Signal.KEY_PRESSED_PLUS);

		// keyPressed_Hyphen
		if (detect(Signal.KEY_PRESSED_HYPHEN_KEYPAD))
			startSignal(Signal.KEY_PRESSED_HYPHEN);
		else if (detect(Signal.KEY_PRESSED_HYPHEN_NUMPAD))
			startSignal(Signal.KEY_PRESSED_HYPHEN);
		else
			stopSignal(Signal.KEY_PRESSED_HYPHEN);

		// keyPressed_Dot
		if (detect(Signal.KEY_PRESSED_DOT_KEYPAD))
			startSignal(Signal.KEY_PRESSED_DOT);
		else if (detect(Signal.KEY_PRESSED_DOT_NUMPAD))
			startSignal(Signal.KEY_PRESSED_DOT);
		else
			stopSignal(Signal.KEY_PRESSED_DOT);

		// keyPressed_Slash
		if (detect(Signal.KEY_PRESSED_SLASH_KEYPAD))
			startSignal(Signal.KEY_PRESSED_SLASH);
		else if (detect(Signal.KEY_PRESSED_SLASH_NUMPAD))
			startSignal(Signal.KEY_PRESSED_SLASH);
		else
			stopSignal(Signal.KEY_PRESSED_SLASH);

		// keyPressed_0
		if (detect(Signal.KEY_PRESSED_0_KEYPAD))
			startSignal(Signal.KEY_PRESSED_0);
		else if (detect(Signal.KEY_PRESSED_0_NUMPAD))
			startSignal(Signal.KEY_PRESSED_0);
		else
			stopSignal(Signal.KEY_PRESSED_0);

		// keyPressed_1
		if (detect(Signal.KEY_PRESSED_1_KEYPAD))
			startSignal(Signal.KEY_PRESSED_1);
		else if (detect(Signal.KEY_PRESSED_1_NUMPAD))
			startSignal(Signal.KEY_PRESSED_1);
		else
			stopSignal(Signal.KEY_PRESSED_1);

		// keyPressed_2
		if (detect(Signal.KEY_PRESSED_2_KEYPAD))
			startSignal(Signal.KEY_PRESSED_2);
		else if (detect(Signal.KEY_PRESSED_2_NUMPAD))
			startSignal(Signal.KEY_PRESSED_2);
		else
			stopSignal(Signal.KEY_PRESSED_2);

		// keyPressed_3
		if (detect(Signal.KEY_PRESSED_3_KEYPAD))
			startSignal(Signal.KEY_PRESSED_3);
		else if (detect(Signal.KEY_PRESSED_3_NUMPAD))
			startSignal(Signal.KEY_PRESSED_3);
		else
			stopSignal(Signal.KEY_PRESSED_3);

		// keyPressed_4
		if (detect(Signal.KEY_PRESSED_4_KEYPAD))
			startSignal(Signal.KEY_PRESSED_4);
		else if (detect(Signal.KEY_PRESSED_4_NUMPAD))
			startSignal(Signal.KEY_PRESSED_4);
		else
			stopSignal(Signal.KEY_PRESSED_4);

		// keyPressed_5
		if (detect(Signal.KEY_PRESSED_5_KEYPAD))
			startSignal(Signal.KEY_PRESSED_5);
		else if (detect(Signal.KEY_PRESSED_5_NUMPAD))
			startSignal(Signal.KEY_PRESSED_5);
		else
			stopSignal(Signal.KEY_PRESSED_5);

		// keyPressed_6
		if (detect(Signal.KEY_PRESSED_6_KEYPAD))
			startSignal(Signal.KEY_PRESSED_6);
		else if (detect(Signal.KEY_PRESSED_6_NUMPAD))
			startSignal(Signal.KEY_PRESSED_6);
		else
			stopSignal(Signal.KEY_PRESSED_6);

		// keyPressed_7
		if (detect(Signal.KEY_PRESSED_7_KEYPAD))
			startSignal(Signal.KEY_PRESSED_7);
		else if (detect(Signal.KEY_PRESSED_7_NUMPAD))
			startSignal(Signal.KEY_PRESSED_7);
		else
			stopSignal(Signal.KEY_PRESSED_7);

		// keyPressed_8
		if (detect(Signal.KEY_PRESSED_8_KEYPAD))
			startSignal(Signal.KEY_PRESSED_8);
		else if (detect(Signal.KEY_PRESSED_8_NUMPAD))
			startSignal(Signal.KEY_PRESSED_8);
		else
			stopSignal(Signal.KEY_PRESSED_8);

		// keyPressed_9
		if (detect(Signal.KEY_PRESSED_9_KEYPAD))
			startSignal(Signal.KEY_PRESSED_9);
		else if (detect(Signal.KEY_PRESSED_9_NUMPAD))
			startSignal(Signal.KEY_PRESSED_9);
		else
			stopSignal(Signal.KEY_PRESSED_9);

		// keyPressed_Numeric_Keyboard
		if (detect(Signal.KEY_PRESSED_0_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_1_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_2_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_3_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_4_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_5_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_6_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_7_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_8_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_9_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);
		else
			stopSignal(Signal.KEY_PRESSED_NUMERIC_KEYPAD);

		// keyPressed_Numeric_NumPad
		if (detect(Signal.KEY_PRESSED_0_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_1_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_2_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_3_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_4_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_5_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_6_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_7_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_8_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_9_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);
		else
			stopSignal(Signal.KEY_PRESSED_NUMERIC_NUMPAD);

		// KeyPressed_Numeric
		if (detect(Signal.KEY_PRESSED_NUMERIC_KEYPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC);
		else if (detect(Signal.KEY_PRESSED_NUMERIC_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMERIC);
		else
			stopSignal(Signal.KEY_PRESSED_NUMERIC);

		// KeyPressed_Shifted_Character
		if (detect(Signal.KEY_PRESSED_CAPITALIZED_CHARACTER))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_TILDE))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_EXCLAIMATION))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_AT))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_NUMBER))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_DOLLAR))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_PERCENT))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_CARET))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_AMPERSAND))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_ASTERISK))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_OPEN_PARENTHESIS))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_CLOSE_PARENTHESIS))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_UNDERSCORE))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_PLUS))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_OPEN_BRACE))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_CLOSE_BRACE))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_BAR))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_COLON))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_QUOTES))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_LESS))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_GREATER))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_QUESTION))
			startSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);
		else
			stopSignal(Signal.KEY_PRESSED_SHIFTED_CHARACTER);

		// keyPressed_Unshifted_Character
		if (detect(Signal.KEY_PRESSED_NON_CAPITALIZED_CHARACTER))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_GRAVE))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_NUMERIC_KEYPAD))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_HYPHEN_KEYPAD))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_EQUALS))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_OPEN_BRACKET))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_CLOSE_BRACKET))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_BACKSLASH))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_SEMICOLON))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_QUOTE))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_COMMA))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_DOT_KEYPAD))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_SLASH_KEYPAD))
			startSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);
		else
			stopSignal(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER);

		// keyPressed_Keyboard
		if (detect(Signal.KEY_PRESSED_SHIFTED_CHARACTER))
			startSignal(Signal.KEY_PRESSED_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_UNSHIFTED_CHARACTER))
			startSignal(Signal.KEY_PRESSED_KEYPAD);
		else if (detect(Signal.KEY_PRESSED_SPACE))
			startSignal(Signal.KEY_PRESSED_KEYPAD);
		else
			stopSignal(Signal.KEY_PRESSED_KEYPAD);

		// keyPressed_NumPad
		if (detect(Signal.KEY_PRESSED_NUMERIC_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_SLASH_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_ASTERISK_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_HYPHEN_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_PLUS_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMPAD);
		else if (detect(Signal.KEY_PRESSED_DOT_NUMPAD))
			startSignal(Signal.KEY_PRESSED_NUMPAD);
		else
			stopSignal(Signal.KEY_PRESSED_NUMPAD);

		// keyPressed_Character
		if (detect(Signal.KEY_PRESSED_ALPHABET))
			startSignal(Signal.KEY_PRESSED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_NUMERIC))
			startSignal(Signal.KEY_PRESSED_CHARACTER);
		else if (detect(Signal.KEY_PRESSED_SPACE))
			startSignal(Signal.KEY_PRESSED_CHARACTER);
		else
			stopSignal(Signal.KEY_PRESSED_CHARACTER);

		// keyPressed_Arrow
		if (detect(Signal.KEY_PRESSED_LEFT))
			startSignal(Signal.KEY_PRESSED_ARROW);
		else if (detect(Signal.KEY_PRESSED_UP))
			startSignal(Signal.KEY_PRESSED_ARROW);
		else if (detect(Signal.KEY_PRESSED_RIGHT))
			startSignal(Signal.KEY_PRESSED_ARROW);
		else if (detect(Signal.KEY_PRESSED_DOWN))
			startSignal(Signal.KEY_PRESSED_ARROW);
		else
			stopSignal(Signal.KEY_PRESSED_ARROW);

		// keyPressed_Function
		if (detect(Signal.KEY_PRESSED_ARROW))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_PRINT_SCREEN))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_INSERT))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_DELETE))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_HOME))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_PAGE_UP))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_PAGE_DOWN))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_END))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_BACKSPACE))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_NUM_LOCK))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_ENTER))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_CAPS_LOCK))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_SHIFT))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else if (detect(Signal.KEY_PRESSED_CTRL))
			startSignal(Signal.KEY_PRESSED_FUNCTION);
		else
			stopSignal(Signal.KEY_PRESSED_FUNCTION);

		// keyPressed
		if (detect(Signal.KEY_PRESSED_KEYPAD))
			startSignal(Signal.KEY_PRESSED);
		else if (detect(Signal.KEY_PRESSED_NUMPAD))
			startSignal(Signal.KEY_PRESSED);
		else if (detect(Signal.KEY_PRESSED_FUNCTION))
			startSignal(Signal.KEY_PRESSED);
		else
			stopSignal(Signal.KEY_PRESSED);

		// mousePressed
		if (detect(Signal.MOUSE_PRESSED_LEFT))
			startSignal(Signal.MOUSE_PRESSED);
		else if (detect(Signal.MOUSE_PRESSED_MIDDLE))
			startSignal(Signal.MOUSE_PRESSED);
		else if (detect(Signal.MOUSE_PRESSED_RIGHT))
			startSignal(Signal.MOUSE_PRESSED);
		else
			stopSignal(Signal.MOUSE_PRESSED);
	}

	static enum Signal {
		KEY_PRESSED(0), KEY_PRESSED_BACKSPACE(1), KEY_PRESSED_ENTER(2), KEY_PRESSED_SHIFT(3), KEY_PRESSED_CTRL(4),
		KEY_PRESSED_ALT(5), KEY_PRESSED_BREAK(6), KEY_PRESSED_CAPS_LOCK(7), KEY_PRESSED_ESC(8), KEY_PRESSED_SPACE(9),
		KEY_PRESSED_EXCLAIMATION(10), KEY_PRESSED_QUOTES(11), KEY_PRESSED_NUMBER(12), KEY_PRESSED_DOLLAR(13),
		KEY_PRESSED_PERCENT(14), KEY_PRESSED_AMPERSAND(15), KEY_PRESSED_QUOTE(16), KEY_PRESSED_OPEN_PARENTHESIS(17),
		KEY_PRESSED_CLOSE_PARENTHESIS(18), KEY_PRESSED_ASTERISK(19), KEY_PRESSED_ASTERISK_KEYPAD(20),
		KEY_PRESSED_PLUS(21), KEY_PRESSED_PLUS_KEYPAD(22), KEY_PRESSED_COMMA(23), KEY_PRESSED_HYPHEN(24),
		KEY_PRESSED_HYPHEN_KEYPAD(25), KEY_PRESSED_DOT(26), KEY_PRESSED_DOT_KEYPAD(27), KEY_PRESSED_SLASH(28),
		KEY_PRESSED_SLASH_KEYPAD(29),

		KEY_PRESSED_0(30), KEY_PRESSED_1(31), KEY_PRESSED_2(32), KEY_PRESSED_3(33), KEY_PRESSED_4(34),
		KEY_PRESSED_5(35), KEY_PRESSED_6(36), KEY_PRESSED_7(37), KEY_PRESSED_8(38), KEY_PRESSED_9(39),
		KEY_PRESSED_0_KEYPAD(40), KEY_PRESSED_1_KEYPAD(41), KEY_PRESSED_2_KEYPAD(42), KEY_PRESSED_3_KEYPAD(43),
		KEY_PRESSED_4_KEYPAD(44), KEY_PRESSED_5_KEYPAD(45), KEY_PRESSED_6_KEYPAD(46), KEY_PRESSED_7_KEYPAD(47),
		KEY_PRESSED_8_KEYPAD(48), KEY_PRESSED_9_KEYPAD(49), KEY_PRESSED_COLON(50), KEY_PRESSED_SEMICOLON(51),
		KEY_PRESSED_LESS(52), KEY_PRESSED_EQUALS(53), KEY_PRESSED_GREATER(54), KEY_PRESSED_QUESTION(55),
		KEY_PRESSED_AT(56),

		KEY_PRESSED_A(57), KEY_PRESSED_B(58), KEY_PRESSED_C(59), KEY_PRESSED_D(60), KEY_PRESSED_E(61),
		KEY_PRESSED_F(62), KEY_PRESSED_G(63), KEY_PRESSED_H(64), KEY_PRESSED_I(65), KEY_PRESSED_J(66),
		KEY_PRESSED_K(67), KEY_PRESSED_L(68), KEY_PRESSED_M(69), KEY_PRESSED_N(70), KEY_PRESSED_O(71),
		KEY_PRESSED_P(72), KEY_PRESSED_Q(73), KEY_PRESSED_R(74), KEY_PRESSED_S(75), KEY_PRESSED_T(76),
		KEY_PRESSED_U(77), KEY_PRESSED_V(78), KEY_PRESSED_W(79), KEY_PRESSED_X(80), KEY_PRESSED_Y(81),
		KEY_PRESSED_Z(82), KEY_PRESSED_OPEN_BRACKET(83), KEY_PRESSED_BACKSLASH(84), KEY_PRESSED_CLOSE_BRACKET(85),
		KEY_PRESSED_CARET(86), KEY_PRESSED_UNDERSCORE(87), KEY_PRESSED_GRAVE(88),

		KEY_PRESSED_a(89), KEY_PRESSED_b(90), KEY_PRESSED_c(91), KEY_PRESSED_d(92), KEY_PRESSED_e(93),
		KEY_PRESSED_f(94), KEY_PRESSED_g(95), KEY_PRESSED_h(96), KEY_PRESSED_i(97), KEY_PRESSED_j(98),
		KEY_PRESSED_k(99), KEY_PRESSED_l(100), KEY_PRESSED_m(101), KEY_PRESSED_n(102), KEY_PRESSED_o(103),
		KEY_PRESSED_p(104), KEY_PRESSED_q(105), KEY_PRESSED_r(106), KEY_PRESSED_s(107), KEY_PRESSED_t(108),
		KEY_PRESSED_u(109), KEY_PRESSED_v(110), KEY_PRESSED_w(111), KEY_PRESSED_x(112), KEY_PRESSED_y(113),
		KEY_PRESSED_z(114), KEY_PRESSED_OPEN_BRACE(115), KEY_PRESSED_BAR(116), KEY_PRESSED_CLOSE_BRACE(117),
		KEY_PRESSED_TILDE(118),

		KEY_PRESSED_PAGE_UP(119), KEY_PRESSED_PAGE_DOWN(120), KEY_PRESSED_END(121), KEY_PRESSED_HOME(122),
		KEY_PRESSED_LEFT(123), KEY_PRESSED_UP(124), KEY_PRESSED_RIGHT(125), KEY_PRESSED_DOWN(126),
		KEY_PRESSED_0_NUMPAD(127), KEY_PRESSED_1_NUMPAD(128), KEY_PRESSED_2_NUMPAD(129), KEY_PRESSED_3_NUMPAD(130),
		KEY_PRESSED_4_NUMPAD(131), KEY_PRESSED_5_NUMPAD(132), KEY_PRESSED_6_NUMPAD(133), KEY_PRESSED_7_NUMPAD(134),
		KEY_PRESSED_8_NUMPAD(135), KEY_PRESSED_9_NUMPAD(136), KEY_PRESSED_ASTERISK_NUMPAD(137),
		KEY_PRESSED_PLUS_NUMPAD(138), KEY_PRESSED_HYPHEN_NUMPAD(139), KEY_PRESSED_DOT_NUMPAD(140),
		KEY_PRESSED_SLASH_NUMPAD(141), KEY_PRESSED_DELETE(142), KEY_PRESSED_NUM_LOCK(143), KEY_PRESSED_INSERT(144),
		KEY_PRESSED_PRINT_SCREEN(145),

		KEY_PRESSED_ALPHABET(146), KEY_PRESSED_CAPITALIZED_CHARACTER(147), KEY_PRESSED_NON_CAPITALIZED_CHARACTER(148),
		KEY_PRESSED_NUMERIC_KEYPAD(149), KEY_PRESSED_NUMERIC_NUMPAD(150), KEY_PRESSED_NUMERIC(151),
		KEY_PRESSED_CHARACTER(152), KEY_PRESSED_SHIFTED_CHARACTER(153), KEY_PRESSED_UNSHIFTED_CHARACTER(154),
		KEY_PRESSED_KEYPAD(155), KEY_PRESSED_NUMPAD(156), KEY_PRESSED_ARROW(157), KEY_PRESSED_FUNCTION(158),

		MOUSE_PRESSED(159), MOUSE_PRESSED_LEFT(160), MOUSE_PRESSED_MIDDLE(161), MOUSE_PRESSED_RIGHT(162),

		MOUSE_WHEEL_MOVED(163), MOUSE_WHEEL_MOVED_UP(164), MOUSE_WHEEL_MOVED_DOWN(165);

		int index;

		Signal(int index) {
			this.index = index;
		}
	}
}