package me.NinjaMandalorian.BaxsterJep.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.NinjaMandalorian.BaxsterJep.GameObject;
import me.NinjaMandalorian.BaxsterJep.Handler;
import me.NinjaMandalorian.BaxsterJep.ID;
import me.NinjaMandalorian.BaxsterJep.Main;

public class StalkerEnemy extends GameObject{

	private Handler handler;
	private GameObject player;
	
	public StalkerEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		this.handler = handler;
		
		for(int i =0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {player = handler.object.get(i); i = handler.object.size();}
		}
		
		velX = 0;
		velY=-0;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		if(y <= 0 || y >= Main.trueHeight -16) velY *= -1;
		if(x <= 0 || x >= Main.trueWidth -16) velX *= -1;
		x = Main.clamp(x, 0, Main.trueWidth -16);
		y = Main.clamp(y, 0, Main.trueHeight -16);
		
		handler.addObject(new Trail(x,y, ID.Trail, new Color(100,0,0), 16, 16, (float) .015f, handler));
		
		
		float xDist = x - player.getX() - 16;
		float yDist = y - player.getY() - 16;
		float distance = (float) Math.sqrt( (x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()) );
		velX = (int) ((-3.0/distance) * xDist);
		if(Math.abs((-3.0/distance) * xDist) < 1) {velX = (int) (-xDist / Math.abs(xDist));}
		velY = (int) ((-3.0/distance) * yDist);
		if(Math.abs((-3.0/distance) * yDist) < 1) {velY = (int) (-yDist / Math.abs(yDist));}
		
		//velY = (int) ((-1.0/distance) * yDist);
		
		
		//System.out.println(Game.Height + " " + y + " " + (Game.Height - y));
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(100,0,0));
		g.fillRect(x, y, 16, 16);
	}
	

}
