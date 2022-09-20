package me.NinjaMandalorian.BaxsterJep.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.NinjaMandalorian.BaxsterJep.GameObject;
import me.NinjaMandalorian.BaxsterJep.Handler;
import me.NinjaMandalorian.BaxsterJep.ID;
import me.NinjaMandalorian.BaxsterJep.Main;

public class Coin extends GameObject{

private Handler handler;
	
	public Coin(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		this.handler = handler;
		
		velX = 3;
		velY=-3;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		if(y <= 0 || y >= Main.trueHeight -8) velY *= -1;
		if(x <= 0 || x >= Main.trueWidth -8) velX *= -1;
		x = Main.clamp(x, 0, Main.trueWidth -8);
		y = Main.clamp(y, 0, Main.trueHeight -8);
		
		handler.addObject(new Trail(x,y, ID.Trail, new Color(255,223,0), 8, 8, (float) .035f, handler));
		//System.out.println(Game.Height + " " + y + " " + (Game.Height - y));
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(255,223,0));
		g.fillRect(x, y, 8, 8);
	}
	
}
