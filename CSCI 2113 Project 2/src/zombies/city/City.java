package zombies.city;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import zombies.city.ZombieSim;

public class City {
public static final Color CITY_COLOR = Color.BLACK;
	
	private Color colors[][];
	private int width, height;
	private Random rand;
	
	private List<Building> buildings;
	private List<Human> humans;
	private List<Zombies> zombies;
	private LinkedList<Color[][]> history;

	/**
	 * Create a new City and fill it with buildings and people.
	 * 
	 * @param w
	 *            width of city
	 * @param h
	 *            height of city
	 * @param numB
	 *            number of buildings
	 * @param numH
	 *            number of humans
	 */
	public City(int w, int h, int numB, int numH, int numZ) {
		width = w;
		height = h;
		colors = new Color[w][h];
		
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				colors[i][j] = Color.BLACK;
			}
		}
		
		rand = new Random();
		
		buildings = new ArrayList<Building>();
		humans = new LinkedList<Human>();
		zombies = new LinkedList<Zombies>();
		history = new LinkedList<Color[][]>();
		
		for(int i=0; i<numB; i++) {
			Building b = new Building();
			buildings.add(b);
		}

		for(int i=0; i<numH; i++) {
			Human p = new Human();
			humans.add(p);
		}
		
		for(int i=0; i<numZ; i++) {
			Zombies z = new Zombies();
			zombies.add(z);
		}
		
		initialize();
		
		// DEBUG
		// System.out.println(this.toString());
	}
	
	public City(int w, int h, List<Building> bldgs, List<Human> people, List<Zombies> zombie) {
		width = w;
		height = h;
		colors = new Color[w][h];
		
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				colors[i][j] = Color.BLACK;
			}
		}
		
		rand = new Random();
		buildings = bldgs;
		humans = people;
		zombies = zombie;
		initialize();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Random getRand() {
		return rand;
	}
	
	public Color getColor(int x, int y) {
		return colors[x][y];
	}
	
	public void setColor(int x, int y, Color color) {
		colors[x][y] = color;
	}
	
	public void initialize() {
		for(Building b : buildings) {
			b.initialize(this);
		}

		for(Human p : humans) {
			p.initialize(this);
		}		
		
		for(Zombies z : zombies) {
			z.initialize(this);
		}
	}

	/**
	 * Updates the state of the city for a time step.
	 */
	public void update() {
		
		Iterator<Human> it = humans.iterator();
		
		while(it.hasNext()){
			Human h = it.next();
			
			if(isInfected(this,h)){
				convertToZombie(h);
				it.remove();
			}
		}

		for(Human p : humans) {
			p.update(this);
		}
		
		for(Zombies z : zombies) {
			z.update(this);
		}
		
		history.add(colors);
	}
	
	private boolean isInfected(City city, Human h){ 
		
		if(h.getY()+1 <city.getHeight() && city.getColor(h.getX(), h.getY()+1).equals(Color.RED)){
			h.setInfected(true);
			return true;
		}
		
		else if(h.getY()-1 >0 && city.getColor(h.getX(), h.getY()-1).equals(Color.RED)){
			h.setInfected(true);
			return true;
		}
		
		else if(h.getX()+1 <city.getWidth() && city.getColor(h.getX()+1, h.getY()).equals(Color.RED)){
			h.setInfected(true);
			return true;
		}
		
		else if(h.getX()-1 >0 && city.getColor(h.getX()-1, h.getY()).equals(Color.RED)){
			h.setInfected(true);
			return true;
		}
		
		else{
			return false;
		}
	}
	
	private void convertToZombie(Human h){
		Zombies z = new Zombies();
		z.setDir(h.getDir());
		z.setX(h.getX());
		z.setY(h.getY());
		zombies.add(z);
		z.initialize(this, Zombies.COLOR);
	}

	
	/**
	 * Draw the buildings, humans, and zombies.
	 */
	public void draw() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				ZombieSim.dp.setPenColor(colors[x][y]);
				ZombieSim.dp.drawDot(x, y);
			}
		}
	}
	
	public void draw(Color [][] colors) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {	
				ZombieSim.dp.drawDot(x, y, colors[x][y]);
				ZombieSim.dp.repaint();
				ZombieSim.dp.updateUI();
			}
		}
	}
	
	public String toString() {
		
		String colormap = "";
		
		for(int i=0; i<this.getHeight(); i++) {
			for(int j=0; j<getWidth(); j++) {
				if(this.getColor(j, i).equals(Color.WHITE)) {
					colormap += "H";
				}
				
				else if(this.getColor(j, i).equals(Color.DARK_GRAY)) {
					colormap += "x";
				}
				
				else if(this.getColor(j, i).equals(Color.BLACK)) {
					colormap += ".";
				}
			}
			
			colormap += "\n";
			System.out.println(colormap);
		}
		 
		return colormap;
	}	
	
	public void addZombie(int x, int y){
		Zombies z = new Zombies();
		z.setX(x);
		z.setY(y);
		zombies.add(z);
	}
	
	public LinkedList<Color[][]> getHistory(){
		return history;
	}
}
