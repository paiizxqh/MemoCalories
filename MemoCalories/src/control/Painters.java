package control;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Painters extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 841; // 1080x1920 -> 841x1536
	public static final int WIDTH = 1536;
	public static final int TOP_BORDER = 22;

	private static Thread thread;
	private static boolean running = false;
	private static transient int fps = 0;

	private static final double TICK_AMOUNT = 1000;
	private static final double NANO_SECOND = 1000000000 / TICK_AMOUNT;
	private static BufferStrategy bs;
	private static Graphics g;

	Painters() {
		new Handlers();
		new Sensor();

		this.addKeyListener(new KeyInput());

		MouseInput mouseInput = new MouseInput();
		this.addMouseListener(mouseInput);
		this.addMouseWheelListener(mouseInput);

		new Window(WIDTH, HEIGHT, "MemoCalories ", this);
	}

	final synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	final synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	/**
	 * อย่ายุ่ง!
	 */
	public final void run() {
		if (this.getClass() != Painters.class)
			return;
		int frames = 0;
		long lastTime = System.nanoTime();
		double tickDelay = 0;
		double renderDelay = 0;
		long timer = System.currentTimeMillis();
		long now;
		frames = 0;
		while (running) {
			now = System.nanoTime();
			tickDelay += (now - lastTime) / NANO_SECOND;
			if (tickDelay >= 1) {
				try {
					tick();
				} catch (Exception e) {
					;
				}
				
				tickDelay -= 1;
			}
			renderDelay += (now - lastTime) / NANO_SECOND;
			if (renderDelay >= 5) {
				render();
				renderDelay -= 5;
			}
			lastTime = now;
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * อ่านค่า Frame per second
	 * 
	 * @return ค่า FPS
	 * @author ณัชพล จำปานิน
	 */
	public static final int getFps() {
		return fps;
	}

	private static final void tick() {
		Handlers.tick();
	}

	private final void render() {
		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		Handlers.render(g);
		bs.getDrawGraphics().dispose();
		bs.show();
	}
}
