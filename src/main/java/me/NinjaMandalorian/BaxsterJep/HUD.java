package me.NinjaMandalorian.BaxsterJep;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int Health = 100;
	private int greenValue = 255;
	
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		Health = Main.clamp(Health, 0, 100);
		greenValue = Main.clamp(Health*255/100, 0, 255);
		
		score = (int) (score + 1);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(150,greenValue,0));
		g.fillRect(15, 15, Health * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score:  " + score, 10, 64);
		g.drawString("Level:  " + level, 10, 80);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
}
