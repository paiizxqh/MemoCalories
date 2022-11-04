package stage;

import control.Handlers;
import stage.scripts.MainScript;

public class Setups {
	public Setups() {
		try {
			Handlers.addScript(new MainScript("MainScript"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}