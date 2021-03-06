package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemyadvance extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 15;
	private boolean alive = true;
	
	public Enemyadvance(int x, int y) {
		super(x, y, 10, 30);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.PINK);
		g.fillRect(x, y, width, height);
		
	}

	
	public void lost(){
		alive = false;
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			//alive = false;
			lost();
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}
