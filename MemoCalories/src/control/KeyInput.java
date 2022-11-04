package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import control.Sensor.Signal;

// Unalterable

class KeyInput extends KeyAdapter {

	@Override
	public void keyPressed(final KeyEvent e) {
		switch (e.getKeyCode()) {
		case 8:
			Sensor.startSignal(Signal.KEY_PRESSED_BACKSPACE);
			break;
		case 10:
			Sensor.startSignal(Signal.KEY_PRESSED_ENTER);
			break;
		case 16:
			Sensor.startSignal(Signal.KEY_PRESSED_SHIFT);
			break;
		case 17:
			Sensor.startSignal(Signal.KEY_PRESSED_CTRL);
			break;
		case 18:
			Sensor.startSignal(Signal.KEY_PRESSED_ALT);
			break;
		case 19:
			Sensor.startSignal(Signal.KEY_PRESSED_BREAK);
			break;
		case 20:
			Sensor.startSignal(Signal.KEY_PRESSED_CAPS_LOCK);
			break;
		case 27:
			Sensor.startSignal(Signal.KEY_PRESSED_ESC);
			break;
		case 33:
			Sensor.startSignal(Signal.KEY_PRESSED_PAGE_UP);
			break;
		case 34:
			Sensor.startSignal(Signal.KEY_PRESSED_PAGE_DOWN);
			break;
		case 35:
			Sensor.startSignal(Signal.KEY_PRESSED_END);
			break;
		case 36:
			Sensor.startSignal(Signal.KEY_PRESSED_HOME);
			break;
		case 37:
			Sensor.startSignal(Signal.KEY_PRESSED_LEFT);
			break;
		case 38:
			Sensor.startSignal(Signal.KEY_PRESSED_UP);
			break;
		case 39:
			Sensor.startSignal(Signal.KEY_PRESSED_RIGHT);
			break;
		case 40:
			Sensor.startSignal(Signal.KEY_PRESSED_DOWN);
			break;
		case 96:
			Sensor.startSignal(Signal.KEY_PRESSED_0_NUMPAD);
			break;
		case 97:
			Sensor.startSignal(Signal.KEY_PRESSED_1_NUMPAD);
			break;
		case 98:
			Sensor.startSignal(Signal.KEY_PRESSED_2_NUMPAD);
			break;
		case 99:
			Sensor.startSignal(Signal.KEY_PRESSED_3_NUMPAD);
			break;
		case 100:
			Sensor.startSignal(Signal.KEY_PRESSED_4_NUMPAD);
			break;
		case 101:
			Sensor.startSignal(Signal.KEY_PRESSED_5_NUMPAD);
			break;
		case 102:
			Sensor.startSignal(Signal.KEY_PRESSED_6_NUMPAD);
			break;
		case 103:
			Sensor.startSignal(Signal.KEY_PRESSED_7_NUMPAD);
			break;
		case 104:
			Sensor.startSignal(Signal.KEY_PRESSED_8_NUMPAD);
			break;
		case 105:
			Sensor.startSignal(Signal.KEY_PRESSED_9_NUMPAD);
			break;
		case 106:
			Sensor.startSignal(Signal.KEY_PRESSED_ASTERISK_NUMPAD);
			break;
		case 107:
			Sensor.startSignal(Signal.KEY_PRESSED_PLUS_NUMPAD);
			break;
		case 109:
			Sensor.startSignal(Signal.KEY_PRESSED_HYPHEN_NUMPAD);
			break;
		case 110:
			Sensor.startSignal(Signal.KEY_PRESSED_DOT_NUMPAD);
			break;
		case 111:
			Sensor.startSignal(Signal.KEY_PRESSED_SLASH_NUMPAD);
			break;
		case 127:
			Sensor.startSignal(Signal.KEY_PRESSED_DELETE);
			break;
		case 144:
			Sensor.startSignal(Signal.KEY_PRESSED_NUM_LOCK);
			break;
		case 155:
			Sensor.startSignal(Signal.KEY_PRESSED_INSERT);
			break;
		case 528:
			Sensor.startSignal(Signal.KEY_PRESSED_PRINT_SCREEN);
			break;
		default:
			switch (e.getKeyChar()) {
			case ' ':
				Sensor.startSignal(Signal.KEY_PRESSED_SPACE);
				break;
			case '!':
				Sensor.startSignal(Signal.KEY_PRESSED_EXCLAIMATION);
				break;
			case '\"':
				Sensor.startSignal(Signal.KEY_PRESSED_QUOTES);
				break;
			case '#':
				Sensor.startSignal(Signal.KEY_PRESSED_NUMBER);
				break;
			case '$':
				Sensor.startSignal(Signal.KEY_PRESSED_DOLLAR);
				break;
			case '%':
				Sensor.startSignal(Signal.KEY_PRESSED_PERCENT);
				break;
			case '&':
				Sensor.startSignal(Signal.KEY_PRESSED_AMPERSAND);
				break;
			case '\'':
				Sensor.startSignal(Signal.KEY_PRESSED_QUOTE);
				break;
			case '(':
				Sensor.startSignal(Signal.KEY_PRESSED_OPEN_PARENTHESIS);
				break;
			case ')':
				Sensor.startSignal(Signal.KEY_PRESSED_CLOSE_PARENTHESIS);
				break;
			case '*':
				Sensor.startSignal(Signal.KEY_PRESSED_ASTERISK_KEYPAD);
				break;
			case '+':
				Sensor.startSignal(Signal.KEY_PRESSED_PLUS_KEYPAD);
				break;
			case ',':
				Sensor.startSignal(Signal.KEY_PRESSED_COMMA);
				break;
			case '-':
				Sensor.startSignal(Signal.KEY_PRESSED_HYPHEN_KEYPAD);
				break;
			case '.':
				Sensor.startSignal(Signal.KEY_PRESSED_DOT_KEYPAD);
				break;
			case '/':
				Sensor.startSignal(Signal.KEY_PRESSED_SLASH_KEYPAD);
				break;
			case '0':
				Sensor.startSignal(Signal.KEY_PRESSED_0_KEYPAD);
				break;
			case '1':
				Sensor.startSignal(Signal.KEY_PRESSED_1_KEYPAD);
				break;
			case '2':
				Sensor.startSignal(Signal.KEY_PRESSED_2_KEYPAD);
				break;
			case '3':
				Sensor.startSignal(Signal.KEY_PRESSED_3_KEYPAD);
				break;
			case '4':
				Sensor.startSignal(Signal.KEY_PRESSED_4_KEYPAD);
				break;
			case '5':
				Sensor.startSignal(Signal.KEY_PRESSED_5_KEYPAD);
				break;
			case '6':
				Sensor.startSignal(Signal.KEY_PRESSED_6_KEYPAD);
				break;
			case '7':
				Sensor.startSignal(Signal.KEY_PRESSED_7_KEYPAD);
				break;
			case '8':
				Sensor.startSignal(Signal.KEY_PRESSED_8_KEYPAD);
				break;
			case '9':
				Sensor.startSignal(Signal.KEY_PRESSED_9_KEYPAD);
				break;
			case ':':
				Sensor.startSignal(Signal.KEY_PRESSED_COLON);
				break;
			case ';':
				Sensor.startSignal(Signal.KEY_PRESSED_SEMICOLON);
				break;
			case '<':
				Sensor.startSignal(Signal.KEY_PRESSED_LESS);
				break;
			case '=':
				Sensor.startSignal(Signal.KEY_PRESSED_EQUALS);
				break;
			case '>':
				Sensor.startSignal(Signal.KEY_PRESSED_GREATER);
				break;
			case '?':
				Sensor.startSignal(Signal.KEY_PRESSED_QUESTION);
				break;
			case '@':
				Sensor.startSignal(Signal.KEY_PRESSED_AT);
				break;
			case 'A':
				Sensor.startSignal(Signal.KEY_PRESSED_A);
				break;
			case 'B':
				Sensor.startSignal(Signal.KEY_PRESSED_B);
				break;
			case 'C':
				Sensor.startSignal(Signal.KEY_PRESSED_C);
				break;
			case 'D':
				Sensor.startSignal(Signal.KEY_PRESSED_D);
				break;
			case 'E':
				Sensor.startSignal(Signal.KEY_PRESSED_E);
				break;
			case 'F':
				Sensor.startSignal(Signal.KEY_PRESSED_F);
				break;
			case 'G':
				Sensor.startSignal(Signal.KEY_PRESSED_G);
				break;
			case 'H':
				Sensor.startSignal(Signal.KEY_PRESSED_H);
				break;
			case 'I':
				Sensor.startSignal(Signal.KEY_PRESSED_I);
				break;
			case 'J':
				Sensor.startSignal(Signal.KEY_PRESSED_J);
				break;
			case 'K':
				Sensor.startSignal(Signal.KEY_PRESSED_K);
				break;
			case 'L':
				Sensor.startSignal(Signal.KEY_PRESSED_L);
				break;
			case 'M':
				Sensor.startSignal(Signal.KEY_PRESSED_M);
				break;
			case 'N':
				Sensor.startSignal(Signal.KEY_PRESSED_N);
				break;
			case 'O':
				Sensor.startSignal(Signal.KEY_PRESSED_O);
				break;
			case 'P':
				Sensor.startSignal(Signal.KEY_PRESSED_P);
				break;
			case 'Q':
				Sensor.startSignal(Signal.KEY_PRESSED_Q);
				break;
			case 'R':
				Sensor.startSignal(Signal.KEY_PRESSED_R);
				break;
			case 'S':
				Sensor.startSignal(Signal.KEY_PRESSED_S);
				break;
			case 'T':
				Sensor.startSignal(Signal.KEY_PRESSED_T);
				break;
			case 'U':
				Sensor.startSignal(Signal.KEY_PRESSED_U);
				break;
			case 'V':
				Sensor.startSignal(Signal.KEY_PRESSED_V);
				break;
			case 'W':
				Sensor.startSignal(Signal.KEY_PRESSED_W);
				break;
			case 'X':
				Sensor.startSignal(Signal.KEY_PRESSED_X);
				break;
			case 'Y':
				Sensor.startSignal(Signal.KEY_PRESSED_Y);
				break;
			case 'Z':
				Sensor.startSignal(Signal.KEY_PRESSED_Z);
				break;
			case '[':
				Sensor.startSignal(Signal.KEY_PRESSED_OPEN_BRACKET);
				break;
			case '\\':
				Sensor.startSignal(Signal.KEY_PRESSED_BACKSLASH);
				break;
			case ']':
				Sensor.startSignal(Signal.KEY_PRESSED_CLOSE_BRACKET);
				break;
			case '^':
				Sensor.startSignal(Signal.KEY_PRESSED_CARET);
				break;
			case '_':
				Sensor.startSignal(Signal.KEY_PRESSED_UNDERSCORE);
				break;
			case '`':
				Sensor.startSignal(Signal.KEY_PRESSED_GRAVE);
				break;
			case 'a':
				Sensor.startSignal(Signal.KEY_PRESSED_a);
				break;
			case 'b':
				Sensor.startSignal(Signal.KEY_PRESSED_b);
				break;
			case 'c':
				Sensor.startSignal(Signal.KEY_PRESSED_c);
				break;
			case 'd':
				Sensor.startSignal(Signal.KEY_PRESSED_d);
				break;
			case 'e':
				Sensor.startSignal(Signal.KEY_PRESSED_e);
				break;
			case 'f':
				Sensor.startSignal(Signal.KEY_PRESSED_f);
				break;
			case 'g':
				Sensor.startSignal(Signal.KEY_PRESSED_g);
				break;
			case 'h':
				Sensor.startSignal(Signal.KEY_PRESSED_h);
				break;
			case 'i':
				Sensor.startSignal(Signal.KEY_PRESSED_i);
				break;
			case 'j':
				Sensor.startSignal(Signal.KEY_PRESSED_j);
				break;
			case 'k':
				Sensor.startSignal(Signal.KEY_PRESSED_k);
				break;
			case 'l':
				Sensor.startSignal(Signal.KEY_PRESSED_l);
				break;
			case 'm':
				Sensor.startSignal(Signal.KEY_PRESSED_m);
				break;
			case 'n':
				Sensor.startSignal(Signal.KEY_PRESSED_n);
				break;
			case 'o':
				Sensor.startSignal(Signal.KEY_PRESSED_o);
				break;
			case 'p':
				Sensor.startSignal(Signal.KEY_PRESSED_p);
				break;
			case 'q':
				Sensor.startSignal(Signal.KEY_PRESSED_q);
				break;
			case 'r':
				Sensor.startSignal(Signal.KEY_PRESSED_r);
				break;
			case 's':
				Sensor.startSignal(Signal.KEY_PRESSED_s);
				break;
			case 't':
				Sensor.startSignal(Signal.KEY_PRESSED_t);
				break;
			case 'u':
				Sensor.startSignal(Signal.KEY_PRESSED_u);
				break;
			case 'v':
				Sensor.startSignal(Signal.KEY_PRESSED_v);
				break;
			case 'w':
				Sensor.startSignal(Signal.KEY_PRESSED_w);
				break;
			case 'x':
				Sensor.startSignal(Signal.KEY_PRESSED_x);
				break;
			case 'y':
				Sensor.startSignal(Signal.KEY_PRESSED_y);
				break;
			case 'z':
				Sensor.startSignal(Signal.KEY_PRESSED_z);
				break;
			case '{':
				Sensor.startSignal(Signal.KEY_PRESSED_OPEN_BRACE);
				break;
			case '|':
				Sensor.startSignal(Signal.KEY_PRESSED_BAR);
				break;
			case '}':
				Sensor.startSignal(Signal.KEY_PRESSED_CLOSE_BRACE);
				break;
			case '~':
				Sensor.startSignal(Signal.KEY_PRESSED_TILDE);
				break;
			}
			break;
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		switch (e.getKeyCode()) {
		case 8:
			Sensor.stopSignal(Signal.KEY_PRESSED_BACKSPACE);
			break;
		case 10:
			Sensor.stopSignal(Signal.KEY_PRESSED_ENTER);
			break;
		case 16:
			Sensor.stopSignal(Signal.KEY_PRESSED_SHIFT);
			break;
		case 17:
			Sensor.stopSignal(Signal.KEY_PRESSED_CTRL);
			break;
		case 18:
			Sensor.stopSignal(Signal.KEY_PRESSED_ALT);
			break;
		case 19:
			Sensor.stopSignal(Signal.KEY_PRESSED_BREAK);
			break;
		case 20:
			Sensor.stopSignal(Signal.KEY_PRESSED_CAPS_LOCK);
			break;
		case 27:
			Sensor.stopSignal(Signal.KEY_PRESSED_ESC);
			break;
		case 33:
			Sensor.stopSignal(Signal.KEY_PRESSED_PAGE_UP);
			break;
		case 34:
			Sensor.stopSignal(Signal.KEY_PRESSED_PAGE_DOWN);
			break;
		case 35:
			Sensor.stopSignal(Signal.KEY_PRESSED_END);
			break;
		case 36:
			Sensor.stopSignal(Signal.KEY_PRESSED_HOME);
			break;
		case 37:
			Sensor.stopSignal(Signal.KEY_PRESSED_LEFT);
			break;
		case 38:
			Sensor.stopSignal(Signal.KEY_PRESSED_UP);
			break;
		case 39:
			Sensor.stopSignal(Signal.KEY_PRESSED_RIGHT);
			break;
		case 40:
			Sensor.stopSignal(Signal.KEY_PRESSED_DOWN);
			break;
		case 96:
			Sensor.stopSignal(Signal.KEY_PRESSED_0_NUMPAD);
			break;
		case 97:
			Sensor.stopSignal(Signal.KEY_PRESSED_1_NUMPAD);
			break;
		case 98:
			Sensor.stopSignal(Signal.KEY_PRESSED_2_NUMPAD);
			break;
		case 99:
			Sensor.stopSignal(Signal.KEY_PRESSED_3_NUMPAD);
			break;
		case 100:
			Sensor.stopSignal(Signal.KEY_PRESSED_4_NUMPAD);
			break;
		case 101:
			Sensor.stopSignal(Signal.KEY_PRESSED_5_NUMPAD);
			break;
		case 102:
			Sensor.stopSignal(Signal.KEY_PRESSED_6_NUMPAD);
			break;
		case 103:
			Sensor.stopSignal(Signal.KEY_PRESSED_7_NUMPAD);
			break;
		case 104:
			Sensor.stopSignal(Signal.KEY_PRESSED_8_NUMPAD);
			break;
		case 105:
			Sensor.stopSignal(Signal.KEY_PRESSED_9_NUMPAD);
			break;
		case 106:
			Sensor.stopSignal(Signal.KEY_PRESSED_ASTERISK_NUMPAD);
			break;
		case 107:
			Sensor.stopSignal(Signal.KEY_PRESSED_PLUS_NUMPAD);
			break;
		case 109:
			Sensor.stopSignal(Signal.KEY_PRESSED_HYPHEN_NUMPAD);
			break;
		case 110:
			Sensor.stopSignal(Signal.KEY_PRESSED_DOT_NUMPAD);
			break;
		case 111:
			Sensor.stopSignal(Signal.KEY_PRESSED_SLASH_NUMPAD);
			break;
		case 127:
			Sensor.stopSignal(Signal.KEY_PRESSED_DELETE);
			break;
		case 144:
			Sensor.stopSignal(Signal.KEY_PRESSED_NUM_LOCK);
			break;
		case 155:
			Sensor.stopSignal(Signal.KEY_PRESSED_INSERT);
			break;
		case 528:
			Sensor.stopSignal(Signal.KEY_PRESSED_PRINT_SCREEN);
			break;
		default:
			switch (e.getKeyChar()) {
			case ' ':
				Sensor.stopSignal(Signal.KEY_PRESSED_SPACE);
				break;
			case '!':
				Sensor.stopSignal(Signal.KEY_PRESSED_EXCLAIMATION);
				break;
			case '\"':
				Sensor.stopSignal(Signal.KEY_PRESSED_QUOTES);
				break;
			case '#':
				Sensor.stopSignal(Signal.KEY_PRESSED_NUMBER);
				break;
			case '$':
				Sensor.stopSignal(Signal.KEY_PRESSED_DOLLAR);
				break;
			case '%':
				Sensor.stopSignal(Signal.KEY_PRESSED_PERCENT);
				break;
			case '&':
				Sensor.stopSignal(Signal.KEY_PRESSED_AMPERSAND);
				break;
			case '\'':
				Sensor.stopSignal(Signal.KEY_PRESSED_QUOTE);
				break;
			case '(':
				Sensor.stopSignal(Signal.KEY_PRESSED_OPEN_PARENTHESIS);
				break;
			case ')':
				Sensor.stopSignal(Signal.KEY_PRESSED_CLOSE_PARENTHESIS);
				break;
			case '*':
				Sensor.stopSignal(Signal.KEY_PRESSED_ASTERISK_KEYPAD);
				break;
			case '+':
				Sensor.stopSignal(Signal.KEY_PRESSED_PLUS_KEYPAD);
				break;
			case ',':
				Sensor.stopSignal(Signal.KEY_PRESSED_COMMA);
				break;
			case '-':
				Sensor.stopSignal(Signal.KEY_PRESSED_HYPHEN_KEYPAD);
				break;
			case '.':
				Sensor.stopSignal(Signal.KEY_PRESSED_DOT_KEYPAD);
				break;
			case '/':
				Sensor.stopSignal(Signal.KEY_PRESSED_SLASH_KEYPAD);
				break;
			case '0':
				Sensor.stopSignal(Signal.KEY_PRESSED_0_KEYPAD);
				break;
			case '1':
				Sensor.stopSignal(Signal.KEY_PRESSED_1_KEYPAD);
				break;
			case '2':
				Sensor.stopSignal(Signal.KEY_PRESSED_2_KEYPAD);
				break;
			case '3':
				Sensor.stopSignal(Signal.KEY_PRESSED_3_KEYPAD);
				break;
			case '4':
				Sensor.stopSignal(Signal.KEY_PRESSED_4_KEYPAD);
				break;
			case '5':
				Sensor.stopSignal(Signal.KEY_PRESSED_5_KEYPAD);
				break;
			case '6':
				Sensor.stopSignal(Signal.KEY_PRESSED_6_KEYPAD);
				break;
			case '7':
				Sensor.stopSignal(Signal.KEY_PRESSED_7_KEYPAD);
				break;
			case '8':
				Sensor.stopSignal(Signal.KEY_PRESSED_8_KEYPAD);
				break;
			case '9':
				Sensor.stopSignal(Signal.KEY_PRESSED_9_KEYPAD);
				break;
			case ':':
				Sensor.stopSignal(Signal.KEY_PRESSED_COLON);
				break;
			case ';':
				Sensor.stopSignal(Signal.KEY_PRESSED_SEMICOLON);
				break;
			case '<':
				Sensor.stopSignal(Signal.KEY_PRESSED_LESS);
				break;
			case '=':
				Sensor.stopSignal(Signal.KEY_PRESSED_EQUALS);
				break;
			case '>':
				Sensor.stopSignal(Signal.KEY_PRESSED_GREATER);
				break;
			case '?':
				Sensor.stopSignal(Signal.KEY_PRESSED_QUESTION);
				break;
			case '@':
				Sensor.stopSignal(Signal.KEY_PRESSED_AT);
				break;
			case 'A':
				Sensor.stopSignal(Signal.KEY_PRESSED_A);
				break;
			case 'B':
				Sensor.stopSignal(Signal.KEY_PRESSED_B);
				break;
			case 'C':
				Sensor.stopSignal(Signal.KEY_PRESSED_C);
				break;
			case 'D':
				Sensor.stopSignal(Signal.KEY_PRESSED_D);
				break;
			case 'E':
				Sensor.stopSignal(Signal.KEY_PRESSED_E);
				break;
			case 'F':
				Sensor.stopSignal(Signal.KEY_PRESSED_F);
				break;
			case 'G':
				Sensor.stopSignal(Signal.KEY_PRESSED_G);
				break;
			case 'H':
				Sensor.stopSignal(Signal.KEY_PRESSED_H);
				break;
			case 'I':
				Sensor.stopSignal(Signal.KEY_PRESSED_I);
				break;
			case 'J':
				Sensor.stopSignal(Signal.KEY_PRESSED_J);
				break;
			case 'K':
				Sensor.stopSignal(Signal.KEY_PRESSED_K);
				break;
			case 'L':
				Sensor.stopSignal(Signal.KEY_PRESSED_L);
				break;
			case 'M':
				Sensor.stopSignal(Signal.KEY_PRESSED_M);
				break;
			case 'N':
				Sensor.stopSignal(Signal.KEY_PRESSED_N);
				break;
			case 'O':
				Sensor.stopSignal(Signal.KEY_PRESSED_O);
				break;
			case 'P':
				Sensor.stopSignal(Signal.KEY_PRESSED_P);
				break;
			case 'Q':
				Sensor.stopSignal(Signal.KEY_PRESSED_Q);
				break;
			case 'R':
				Sensor.stopSignal(Signal.KEY_PRESSED_R);
				break;
			case 'S':
				Sensor.stopSignal(Signal.KEY_PRESSED_S);
				break;
			case 'T':
				Sensor.stopSignal(Signal.KEY_PRESSED_T);
				break;
			case 'U':
				Sensor.stopSignal(Signal.KEY_PRESSED_U);
				break;
			case 'V':
				Sensor.stopSignal(Signal.KEY_PRESSED_V);
				break;
			case 'W':
				Sensor.stopSignal(Signal.KEY_PRESSED_W);
				break;
			case 'X':
				Sensor.stopSignal(Signal.KEY_PRESSED_X);
				break;
			case 'Y':
				Sensor.stopSignal(Signal.KEY_PRESSED_Y);
				break;
			case 'Z':
				Sensor.stopSignal(Signal.KEY_PRESSED_Z);
				break;
			case '[':
				Sensor.stopSignal(Signal.KEY_PRESSED_OPEN_BRACKET);
				break;
			case '\\':
				Sensor.stopSignal(Signal.KEY_PRESSED_BACKSLASH);
				break;
			case ']':
				Sensor.stopSignal(Signal.KEY_PRESSED_CLOSE_BRACKET);
				break;
			case '^':
				Sensor.stopSignal(Signal.KEY_PRESSED_CARET);
				break;
			case '_':
				Sensor.stopSignal(Signal.KEY_PRESSED_UNDERSCORE);
				break;
			case '`':
				Sensor.stopSignal(Signal.KEY_PRESSED_GRAVE);
				break;
			case 'a':
				Sensor.stopSignal(Signal.KEY_PRESSED_a);
				break;
			case 'b':
				Sensor.stopSignal(Signal.KEY_PRESSED_b);
				break;
			case 'c':
				Sensor.stopSignal(Signal.KEY_PRESSED_c);
				break;
			case 'd':
				Sensor.stopSignal(Signal.KEY_PRESSED_d);
				break;
			case 'e':
				Sensor.stopSignal(Signal.KEY_PRESSED_e);
				break;
			case 'f':
				Sensor.stopSignal(Signal.KEY_PRESSED_f);
				break;
			case 'g':
				Sensor.stopSignal(Signal.KEY_PRESSED_g);
				break;
			case 'h':
				Sensor.stopSignal(Signal.KEY_PRESSED_h);
				break;
			case 'i':
				Sensor.stopSignal(Signal.KEY_PRESSED_i);
				break;
			case 'j':
				Sensor.stopSignal(Signal.KEY_PRESSED_j);
				break;
			case 'k':
				Sensor.stopSignal(Signal.KEY_PRESSED_k);
				break;
			case 'l':
				Sensor.stopSignal(Signal.KEY_PRESSED_l);
				break;
			case 'm':
				Sensor.stopSignal(Signal.KEY_PRESSED_m);
				break;
			case 'n':
				Sensor.stopSignal(Signal.KEY_PRESSED_n);
				break;
			case 'o':
				Sensor.stopSignal(Signal.KEY_PRESSED_o);
				break;
			case 'p':
				Sensor.stopSignal(Signal.KEY_PRESSED_p);
				break;
			case 'q':
				Sensor.stopSignal(Signal.KEY_PRESSED_q);
				break;
			case 'r':
				Sensor.stopSignal(Signal.KEY_PRESSED_r);
				break;
			case 's':
				Sensor.stopSignal(Signal.KEY_PRESSED_s);
				break;
			case 't':
				Sensor.stopSignal(Signal.KEY_PRESSED_t);
				break;
			case 'u':
				Sensor.stopSignal(Signal.KEY_PRESSED_u);
				break;
			case 'v':
				Sensor.stopSignal(Signal.KEY_PRESSED_v);
				break;
			case 'w':
				Sensor.stopSignal(Signal.KEY_PRESSED_w);
				break;
			case 'x':
				Sensor.stopSignal(Signal.KEY_PRESSED_x);
				break;
			case 'y':
				Sensor.stopSignal(Signal.KEY_PRESSED_y);
				break;
			case 'z':
				Sensor.stopSignal(Signal.KEY_PRESSED_z);
				break;
			case '{':
				Sensor.stopSignal(Signal.KEY_PRESSED_OPEN_BRACE);
				break;
			case '|':
				Sensor.stopSignal(Signal.KEY_PRESSED_BAR);
				break;
			case '}':
				Sensor.stopSignal(Signal.KEY_PRESSED_CLOSE_BRACE);
				break;
			case '~':
				Sensor.stopSignal(Signal.KEY_PRESSED_TILDE);
				break;
			}
			break;
		}
	}

}
