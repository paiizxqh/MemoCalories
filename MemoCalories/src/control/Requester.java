package control;

import equipments.gadgets.scripts.Frame;

public class Requester {
	
	public static final String GetLeftClickCounter(Frame frame) {
		try {
			Sensor.Counter.addReservedTapped(frame.getName() + "_Frame_LeftClick", "LeftClick");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frame.getName() + "_Frame_LeftClick";
	}
}
