package me.NinjaMandalorian.BaxsterJep.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.NinjaMandalorian.BaxsterJep.GameObject;
import me.NinjaMandalorian.BaxsterJep.HUD;
import me.NinjaMandalorian.BaxsterJep.Handler;
import me.NinjaMandalorian.BaxsterJep.ID;
import me.NinjaMandalorian.BaxsterJep.Main;

public class Player extends GameObject{

	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		//System.out.println(x + " "+ y);
		x=Main.clamp(x, 0, Main.trueWidth-32);
		y=Main.clamp(y, 0, Main.trueHeight-32);
		checkCollision();
		handler.addObject(new Trail(x,y, ID.Trail, new Color(220,220,220), 32, 32, (float) .010f, handler));
	}

	private void checkCollision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.Health -= 2;
				}
			}
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

	
}
