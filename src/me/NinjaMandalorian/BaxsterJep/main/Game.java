package me.NinjaMandalorian.BaxsterJep.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
 
	private static final long serialVersionUID = 9047379336320826694L;

	public static final int Width = 640, Height = 480; 
	public static int trueWidth,trueHeight;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		Window window = new Window(Width, Height, "Baxster Jep", this);
		Dimension windowSize = window.getFrameSize();
		trueWidth = windowSize.width;
		trueHeight = windowSize.height;
		
		hud = new HUD();
		
		r = new Random();
		
		handler.addObject(new Player(Width/2 -32 ,Height/2 - 32,ID.Player, handler));
		handler.addObject(new BasicEnemy(r.nextInt(Width),r.nextInt(Height),ID.Enemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt(Width),r.nextInt(Height),ID.Enemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt(Width),r.nextInt(Height),ID.Enemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt(Width),r.nextInt(Height),ID.Enemy, handler));
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
			
			if(timer > 10000) {
				handler.addObject(new BasicEnemy(r.nextInt(Width),r.nextInt(Height),ID.Enemy, handler));
				timer = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, trueWidth + 5, trueHeight + 5);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) 
			return var=max;
		else if(var <= min)
			return var=min;
		else
			return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
