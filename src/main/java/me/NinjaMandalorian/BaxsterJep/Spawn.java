package me.NinjaMandalorian.BaxsterJep;

import java.util.Random;

import me.NinjaMandalorian.BaxsterJep.GameObjects.*;

public class Spawn {

	private Handler handler;
	private HUD hud;
	
	private int timer = 0;
	private int step = 250;
	private Random r = new Random();
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		timer++;
		if(timer >= step) {
			System.out.println("LEVEL UP");
			timer = 0;
			hud.setLevel(hud.getLevel()+ 1);
			
			if(hud.getLevel() == 2) {
				
			} else if(hud.getLevel() == 3) {
				step = 500;
				handler.addObject(new BasicEnemy(r.nextInt(Main.trueWidth), r.nextInt(Main.trueHeight), ID.Enemy, handler));
			} else if(hud.getLevel() == 4) {
				handler.addObject(new BasicEnemy(r.nextInt(Main.trueWidth), r.nextInt(Main.trueHeight), ID.Enemy, handler));
			} else if(hud.getLevel() == 5) {
				handler.addObject(new StalkerEnemy(r.nextInt(Main.trueWidth), r.nextInt(Main.trueHeight), ID.Enemy, handler));
			}
		}
	}
	
}
