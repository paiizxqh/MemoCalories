package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.awt.event.MouseWheelEvent;

import control.Sensor.Signal;

// Unalterable

public class MouseInput extends MouseAdapter {
	private static boolean movingWheel = false;

	@Override
	public void mousePressed(final MouseEvent e) {
		switch (e.getButton()) {
		case 1:
			Sensor.startSignal(Signal.MOUSE_PRESSED_LEFT);
			break;
		case 2:
			Sensor.startSignal(Signal.MOUSE_PRESSED_MIDDLE);
			break;
		case 3:
			Sensor.startSignal(Signal.MOUSE_PRESSED_RIGHT);
			break;
		}
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		switch (e.getButton()) {
		case 1:
			Sensor.stopSignal(Signal.MOUSE_PRESSED_LEFT);
			break;
		case 2:
			Sensor.stopSignal(Signal.MOUSE_PRESSED_MIDDLE);
			break;
		case 3:
			Sensor.stopSignal(Signal.MOUSE_PRESSED_RIGHT);
			break;
		}
	}

	@Override
	public void mouseWheelMoved(final MouseWheelEvent e) {
		movingWheel = true;
		Sensor.startSignal(Signal.MOUSE_WHEEL_MOVED);
		if (e.getWheelRotation() < 0) {
			Sensor.startSignal(Signal.MOUSE_WHEEL_MOVED_UP);
			Sensor.stopSignal(Signal.MOUSE_WHEEL_MOVED_DOWN);
		} else {
			Sensor.startSignal(Signal.MOUSE_WHEEL_MOVED_DOWN);
			Sensor.stopSignal(Signal.MOUSE_WHEEL_MOVED_UP);
		}
	}

	static final void tick() {
		if (movingWheel)
			movingWheel = false;
		else {
			Sensor.stopSignal(Signal.MOUSE_WHEEL_MOVED);
			Sensor.stopSignal(Signal.MOUSE_WHEEL_MOVED_UP);
			Sensor.stopSignal(Signal.MOUSE_WHEEL_MOVED_DOWN);
		}
	}
	
	/**
	 * อ่านค่าตำแหน่งเมาส์ในแนวแกน x
	 * 
	 * @return แนวแกน x ของเมาส์ที่อ่านได้
	 * @author ณัชพล จำปานิน
	 */
	static final double getPointerX() {
		return MouseInfo.getPointerInfo().getLocation().getX();
	}
	
	/**
	 * อ่านค่าตำแหน่งเมาส์ในแนวแกน y
	 * 
	 * @return แนวแกน y ของเมาส์ที่อ่านได้
	 * @author ณัชพล จำปานิน
	 */
	static final double getPointerY() {
		return MouseInfo.getPointerInfo().getLocation().getY();
	}
}