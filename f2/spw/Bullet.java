package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 100;
	public static final int Y_TO_DIE = 0;
	
	private int step = 7;
	private boolean alive = true;
	
	public Bullet(int x, int y) {
		super(x, y, 5, 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}

	public void lost(){
		alive = false;
	}
	public void proceed(){
		y -= step;
		if(y < Y_TO_DIE){
			//alive = false;
			lost();
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}
