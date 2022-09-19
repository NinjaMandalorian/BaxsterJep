package me.NinjaMandalorian.BaxsterJep;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = 1269973246291744381L;
	
	private JFrame frame;
	
	public Window(int width, int height, String title, Main game) {
		frame = new JFrame(title);
		//frame.setPreferredSize(new Dimension(width,height));
		//frame.setMaximumSize(new Dimension(width,height));
		//frame.setMinimumSize(new Dimension(width,height));
		frame.setSize(new Dimension(width, height));
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		System.out.println(frame.getContentPane().getHeight());
		game.start();
		//Game.trueHeight = frame.getContentPane().getHeight();
		//Game.trueWidth = frame.getContentPane().getWidth();
		
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension size = getFrameSize();
				Main.trueWidth = size.width;
				Main.trueHeight = size.height;
			}
		});
	}
	
	public Dimension getFrameSize() {
		return new Dimension(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
	}
}
