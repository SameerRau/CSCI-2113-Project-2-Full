package zombies.city;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JFrame;
 
public class ZombieSim extends JFrame implements KeyListener, MouseListener {
	private static final long serialVersionUID = -5176170979783243427L;

	/** The Dot Panel object you will draw to */ 
	public static DotPanel dp;

	/* Define constants using static final variables */
	public static final int MAX_X = 80;
	public static final int MAX_Y = 80;
	private static final int DOT_SIZE = 10;
	static final int NUM_HUMANS = 20;
	static final int NUM_BUILDINGS = 10;
	static final int NUM_ZOMBIES = 5;
	private boolean isPaused = false;
	private int stepsBack = 0;
	private boolean back = false;
	private LinkedList<Color[][]> history;
	private City world;
	
	/*
	 * This fills the frame with a "DotPanel", a type of drawing canvas that
	 * allows you to easily draw squares to the screen.
	 */
	public ZombieSim() {
		this.setSize(MAX_X * DOT_SIZE, MAX_Y * DOT_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Braaiinnnnnsss");

		/* Create and set the size of the panel */
		dp = new DotPanel(MAX_X, MAX_Y, DOT_SIZE);

		/* Add the panel to the frame */
		Container cPane = this.getContentPane();
		cPane.add(dp);
		cPane.addKeyListener(this);
		cPane.addMouseListener(this);
		cPane.setFocusable(true);

		/*
		 * Initialize the DotPanel canvas: You CANNOT draw to the panel BEFORE
		 * this code is called. You CANNOT add new widgets to the frame AFTER
		 * this is called.
		 */
		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		this.setVisible(true);

		/* Create our city */
		world = new City(MAX_X, MAX_Y, NUM_BUILDINGS, NUM_HUMANS, NUM_ZOMBIES);

		/*
		 * This is the Run Loop (aka "simulation loop" or "game loop") It will
		 * loop forever, first updating the state of the world (e.g., having
		 * humans take a single step) and then it will draw the newly updated
		 * simulation. Since we don't want the simulation to run too fast for us
		 * to see, it will sleep after repainting the screen. Currently it
		 * sleeps for 33 milliseconds, so the program will update at about 30
		 * frames per second.
		 */
		while (true) {
			
			if(isPaused) {
				history = world.getHistory();
				System.out.println("History.size " + history.size());
				
				while(isPaused) {
					if(back) {
						while(back) {
							System.out.println("Back");
							Color[][]c = history.get(history.size()-1-stepsBack);
							
							for (int x = 0; x < c.length; x++) {
								for (int y = 0; y < c[1].length; y++) {
									if (c[x][y].equals(Color.DARK_GRAY)){
										
										System.out.print("B");
										world.setColor(x, y, Color.DARK_GRAY);
									}
									
									else if (c[x][y].equals(Color.BLACK)){
										
										System.out.print(" ");
										world.setColor(x, y, Color.BLACK);
									}
									
									else if (c[x][y].equals(Color.WHITE)){
										
										System.out.print("H");
										world.setColor(x, y, Color.WHITE);	
									}
									
									else if (c[x][y].equals(Color.RED)){
										
										world.setColor(x, y, Color.RED);	
										System.out.print("Z");
									}
									
									else if (c[x][y].equals(Color.YELLOW)){
										
										world.setColor(x, y, Color.YELLOW);
										System.out.print("Y");
									}
								}
								
								System.out.println();
							}
							
							back=false;
							
							if (stepsBack<history.size()-1) {
								stepsBack++;
							}
							
							System.out.println("Steps back " + stepsBack);
						}
					}
				}
				
			}
	
			// Run update rules for world and everything in it
			world.update();
			// Draw to screen and then refresh
			world.draw();
			dp.repaintAndSleep(300);

		}
	}

	public static void main(String[] args) {
		/* Create a new GUI window */
		new ZombieSim();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			if (isPaused){
				isPaused=false;
			}
			else{
				isPaused=true;
			}
		}
		if (arg0.getKeyCode()==KeyEvent.VK_LEFT){
			back=true;
			dp.setAutoShow(false);
		}
	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if(world.getColor(arg0.getX()/DOT_SIZE, arg0.getY()/DOT_SIZE).equals(Color.BLACK)){
			world.addZombie(arg0.getX()/DOT_SIZE,arg0.getY()/DOT_SIZE);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
