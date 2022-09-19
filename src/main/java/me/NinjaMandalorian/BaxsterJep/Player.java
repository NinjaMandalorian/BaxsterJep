package me.NinjaMandalorian.BaxsterJep;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
		x=Game.clamp(x, 0, Game.trueWidth-32);
		y=Game.clamp(y, 0, Game.trueHeight-32);
		checkCollision();
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
