package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 7;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction_X,int direction_Y){
		
		
		x += (step * direction_X);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
			
		y += (step * direction_Y);
		if(y < 0)
			y = 0;
		if(y > 600 - height)
			y = 600 - height;
	}

}
