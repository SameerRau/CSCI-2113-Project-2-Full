package zombies.city;

import java.awt.Color;
import java.util.List;

public class Zombies extends Actor {
	
	public static final int CHANCE = 20;
	public static final Color COLOR = Color.RED;
	private static int count = 0;
	private int id;

	// ==========================================================
	// Constructors
	// ==========================================================

	public Zombies() {
		super();
		count++;
		this.id = count;
	}

	public Zombies(int id, int x, int y, Direction d, List<Direction> dirs) {
		this.id = id;
	}

	// ==========================================================
	// Accessors
	// ==========================================================

	public int getID() {
		return id;
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

	// Generate numPeople new humans randomly placed around the city.
	public void initialize(City city) {
		
		super.initialize(city, COLOR);
	}

	// ==========================================================
	// update
	// ==========================================================

	public void update(City city) {

		if (findHuman(city)){
			chase(city, COLOR);
		}
		
		else{
			super.update(city, COLOR, CHANCE);
		}
	}
	
	private boolean findHuman(City city) {
		
		if (this.getDir() == Direction.NORTH) {
			for (int i = this.getY()-1; i > this.getY()-11; i--) {
				if (i <= 0) {
					break;
				}
				
				else if (city.getColor(getX(), i) == Color.WHITE||city.getColor(getX(), i) == Color.YELLOW) {
					return true;
				}
			}
		} 
		
		else if (this.getDir() == Direction.EAST) {
			for (int i = this.getX()+1; i < this.getX()+11; i++) {
				if (i >= city.getWidth()-1){
					break;
				}
				
				else if (city.getColor(i, this.getY()) == Color.WHITE||city.getColor(i, this.getY()) == Color.YELLOW) {
					return true;
				}
			}
		} 
		
		else if (this.getDir() == Direction.SOUTH) {
			for (int i = this.getY()+1; i < this.getY()+11; i++) {
				if (i >= city.getHeight()-1){
					break;
				}
				
				else if (city.getColor(this.getX(), i) == Color.WHITE||city.getColor(this.getX(), i) == Color.YELLOW) {
					return true;
				}
			}
		} 
		
		else if (this.getDir() == Direction.WEST) {
			for (int i = this.getX()-1; i > this.getX()-11; i--) {
				if (i <= 0){
					break;
				}
				
				else if (city.getColor(i, this.getY()) == Color.WHITE||city.getColor(i, this.getY()) == Color.YELLOW) {
					return true;
				}
			}
		} 
		 
		return false;	
	}

	private void chase(City city, Color color){

		if (this.getDir().equals(Direction.NORTH)) {
			isMovingNorth(city,color);
		}

		else if (this.getDir().equals(Direction.EAST)) {
			isMovingEast(city,color);
		}

		else if (this.getDir().equals(Direction.SOUTH)) {
			isMovingSouth(city,color);
		}

		else if (this.getDir().equals(Direction.WEST)) {
			isMovingWest(city,color);
		} 
	}
}
