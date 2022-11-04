package control;

import java.awt.Dimension;

import javax.swing.JFrame;

import stage.Setups;

// Unalterable

class Window {

	Window(final int height, final int width, final String title, final Painters painters) {
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(height, width));
		frame.setMaximumSize(new Dimension(height, width));
		frame.setMinimumSize(new Dimension(height, width));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(painters);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		painters.start();
	}
	
	public static void main(String[] args) throws Exception {
		new Painters();
		new Files().setPath();
		new Setups();
	}
}
