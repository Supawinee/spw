package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Enemyadvance> enemieadvances = new ArrayList<Enemyadvance>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateEnemyadvance(){
		Enemyadvance ea = new Enemyadvance((int)(Math.random()*390), 25);
		gp.sprites.add(ea);
		enemieadvances.add(ea);
	}
	
	private void generateBullet(){
		Bullet b = new Bullet((v.x)+(v.width/2)-3, v.y);
		gp.sprites.add(b);
		bullets.add(b);
	}
	
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
			generateEnemyadvance();

			
		}
		
		
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		Iterator<Enemyadvance> ea_iter = enemieadvances.iterator();
		while(ea_iter.hasNext()){
			Enemyadvance ea = ea_iter.next();
			ea.proceed();
			
			if(!ea.isAlive()){
				ea_iter.remove();
				gp.sprites.remove(ea);
				score += 150;
			}
		}
		
		Iterator<Bullet> b_iter = bullets.iterator();
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
				//score += 150;
			}
		}
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double ear;
		Rectangle2D.Double br;
		for(Enemy e : enemies){
			er = e.getRectangle();
			
			for(Bullet b : bullets){
				br = b.getRectangle();
				if(br.intersects(er)){
					b.lost();
					e.lost();
					return;
				}

			
			

				if(er.intersects(vr)){
					die();
				return;
				}
			}
		}
		
		for(Enemyadvance ea : enemieadvances){
			ear = ea.getRectangle();
			
			for(Bullet b : bullets){
				br = b.getRectangle();
				if(br.intersects(ear)){
					b.lost();
					ea.lost();
					return;
				}

			
			

				if(ear.intersects(vr)){
					die();
				return;
				}

			}
		}
		

	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1,0);
			break;
		case KeyEvent.VK_UP:
			v.move(0,-1);
			break;
		case KeyEvent.VK_DOWN:
			v.move(0,1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			generateBullet();
			break;	
		}
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
