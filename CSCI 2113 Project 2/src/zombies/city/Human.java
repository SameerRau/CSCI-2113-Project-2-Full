package zombies.city;
 
import java.awt.Color;
import java.util.List; 

public class Human extends Actor {
	 
	public static final int CHANCE = 10;
	public static final Color COLOR = Color.WHITE;
	private int run = 0;
	private static int count = 0;
	private int id;
	private boolean isInfected;

	// ==========================================================
	// Constructors
	// ==========================================================

	public Human() {
		super();
		count++;
		this.id = count;
		isInfected = false;
	}

	public Human(int id, int x, int y, Direction d, List<Direction> dirs) {
		this.id = id;
	}

	// ==========================================================
	// Accessors + Mutators
	// ==========================================================

	public int getID() {
		return id;
	}

	public boolean isInfected() {
		return isInfected;
	}
	
	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	// ==========================================================
	// Initialize
	// ==========================================================

	/**
	 * Places this person in a random position within the city by coloring the
	 * appropriate dot. Avoids placing the person if something is already there.
	 * 
	 * @param city
	 */
	public void initialize(City city) {
		super.initialize(city, COLOR);
	}

	// ==========================================================
	// update
	// ==========================================================

	public void update(City city) {
		
		if (this.run>0) {
			if (this.getDir().equals(Direction.NORTH)) {
				isMovingNorth(city, Color.YELLOW);
			}
			
			else if (this.getDir().equals(Direction.SOUTH)) {
				isMovingSouth(city, Color.YELLOW);
			}
			
			else if (this.getDir().equals(Direction.EAST)) {
				isMovingEast(city, Color.YELLOW);
			}
			
			else {
				isMovingWest(city, Color.YELLOW);
			}
			
			run--;
		}
		
		else if (foundZombie(city)) {
			flee(city);
		}
		
		else {
			super.update(city, COLOR, CHANCE);
		}
	}
	
	private boolean foundZombie(City city) {
		
		if (this.getDir() == Direction.NORTH) {
			for (int i = this.getY()-1; i > this.getY()-11; i--) {
				if (i <= 0) {
					break;
				}
				
				else if (city.getColor(getX(), i) == Color.RED) {
					return true;
				}
			}
		} 
		
		else if (this.getDir() == Direction.EAST) {
			for (int i = this.getX()+1; i < this.getX()+11; i++) {
				if (i >= city.getWidth()-1) {
					break;
				}
				
				else if (city.getColor(i, this.getY()) == Color.RED) {
					return true;
				}
			}
		} 
		
		else if (this.getDir() == Direction.SOUTH) {
			for (int i = this.getY()+1; i < this.getY()+11; i++) {
				if (i >= city.getHeight()-1) {
					break;
				}
				
				else if (city.getColor(this.getX(), i) == Color.RED) {
					return true;
				}
			}
		} 
		
		else if (this.getDir() == Direction.WEST) {
			for (int i = this.getX()-1; i > this.getX()-11; i--) {
				if (i <= 0){
					break;
				}
				
				else if (city.getColor(i, this.getY()) == Color.RED) {
					return true;
				}
			}
		} 
		
		return false;	
	}
	
	private void flee(City city){
		
		if (this.getDir().equals(Direction.NORTH)){
			this.setDir(Direction.SOUTH);
			run=2;
			isMovingSouth(city,Color.YELLOW);
		}
		
		else if (this.getDir().equals(Direction.EAST)){
			this.setDir(Direction.WEST);
			run=2;
			isMovingWest(city,Color.YELLOW);
		}
		
		else if (this.getDir().equals(Direction.SOUTH)){
			this.setDir(Direction.NORTH);
			run=2;
			isMovingNorth(city,Color.YELLOW);
		}
		
		else if (this.getDir().equals(Direction.WEST)){
			this.setDir(Direction.EAST);
			run=2;
			isMovingEast(city,Color.YELLOW);
		} 
	}
}
