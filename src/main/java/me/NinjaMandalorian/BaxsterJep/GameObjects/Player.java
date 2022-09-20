package me.NinjaMandalorian.BaxsterJep.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import me.NinjaMandalorian.BaxsterJep.GameObject;
import me.NinjaMandalorian.BaxsterJep.HUD;
import me.NinjaMandalorian.BaxsterJep.Handler;
import me.NinjaMandalorian.BaxsterJep.ID;
import me.NinjaMandalorian.BaxsterJep.Main;

public class Player extends GameObject{

	Handler handler;
	HUD hud;
	Random r;
	
	public Player(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		this.hud = hud;
		r = new Random();
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
					// Functions for all enemies
					if(tempObject instanceof StalkerEnemy) {
						int dist = r.nextInt(95-20) + 20;
						tempObject.setX(tempObject.getX() + dist * (x > tempObject.getX() ? -1 : 1));
						tempObject.setY(tempObject.getY() + dist * (y > tempObject.getY() ? -1 : 1));
						HUD.Health -= 4;
					}
					else HUD.Health -= 2; // Else just does 2 damage per tick.
				}
			} else if(tempObject.getId() == ID.Item) {
				if(getBounds().intersects(tempObject.getBounds())) {
					// Functions for all Items
					if(tempObject instanceof Coin) {
						handler.removeObject(tempObject);
						hud.setCoins(hud.getCoins() + 1);
					}
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
