package zombies.city;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Actor {
	private Random rand;
	private Direction dir; 
	private int x; 
	private int y; 
	private List<Direction> dirs;
	
	public Actor(int x, int y, Direction d, List<Direction> dirs) {
		this.rand = new Random();
		this.x = x;
		this.y = y; 
		this.dir = d;
		this.dirs = dirs;
	}
	
	public Actor() {
		this.x = 0; 
		this.y = 0;
		this.rand = new Random();
		this.dir = Direction.NIL;
		dirs = new LinkedList<>();
	}
	
	//accessors.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Direction getDir() {
		return dir;
	}
	
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}

	public void initialize(City city, Color color) { 
		if (this.dir.equals(Direction.NIL)) {
			boolean endCondition = false;
			
			while (endCondition != true) {
				this.x = this.rand.nextInt(city.getWidth());
				this.y = this.rand.nextInt(city.getHeight());
				
				if (city.getColor(this.x, this.y).equals(Color.DARK_GRAY) != true && city.getColor(this.x, this.y).equals(Color.WHITE) != true && city.getColor(this.x, this.y).equals(Color.RED)!=true && city.getColor(this.x, this.y).equals(Color.YELLOW)!=true) {
					endCondition = true;
					city.setColor(this.x, this.y, color);
				}
			}
		} 
		
		else {
			city.setColor(this.x, this.y, color);
		}
	}
	
	public void update(City city, Color color, int chance) { 

		int actorChance = rand.nextInt(100);
		int directionChange = rand.nextInt(4);
		boolean updated = false;
		/*
		 * if iterator is empty,we are working with a fully constructed human/zombie
		 * thus we have to implement the 10%/20% chance rule
		 */
		if (this.dirs.size() == 0) {
			if (actorChance < chance) {
				switch (directionChange) {
				case (0):
					this.dir = Direction.NORTH;
					updated=true;
					break;
				case (1):
					this.dir = Direction.SOUTH;
					updated=true;
					break;
				case (2):
					this.dir = Direction.EAST;
					updated=true;
					break;
				case (3):
					this.dir = Direction.WEST;
					updated=true;
					break;
				}
			}
		} 
		
		else { 
			dir=dirs.remove(0);
			updated=true;
		}
		
		if(!updated){
			if (this.dir.equals(Direction.NORTH)) {
				isMovingNorth(city,color);
			}

			else if(this.dir.equals(Direction.EAST)) {
				isMovingEast(city,color);
			}

			else if(this.dir.equals(Direction.SOUTH)) {
				isMovingSouth(city,color);
			}

			else if(this.dir.equals(Direction.WEST)) {
				isMovingWest(city,color);
			}
		}
	}
	
	public void isMovingNorth(City city, Color color){

		if (this.getY() - 1 < 0|| city.getColor(this.getX(), this.getY() - 1).equals(Color.WHITE)|| city.getColor(this.getX(), this.getY() - 1).equals(Color.DARK_GRAY)||city.getColor(this.getX(), this.getY() - 1).equals(Color.RED)||city.getColor(this.getX(), this.getY() - 1).equals(Color.YELLOW)) {
			this.dir = Direction.SOUTH;
		} 
		
		else {

			city.setColor(this.x, this.y, Color.BLACK);
			this.y -= 1;
			city.setColor(this.x, this.y, color);
		}
	} 

	public void isMovingSouth(City city, Color color){

		if (this.getY() + 1 == city.getHeight()|| city.getColor(this.getX(), this.getY() + 1).equals(Color.WHITE)|| city.getColor(this.getX(), this.getY() + 1).equals(Color.DARK_GRAY)||city.getColor(this.getX(), this.getY()+1).equals(Color.RED)||city.getColor(this.getX(), this.getY()+1).equals(Color.YELLOW)) {
			this.dir = Direction.NORTH;
		} 
		
		else {

			city.setColor(this.x, this.y, Color.BLACK);
			this.y += 1;
			city.setColor(this.x, this.y, color);
		}
	}

	
	public void isMovingEast(City city, Color color){

		if (this.getX() + 1 == city.getWidth()|| city.getColor(this.getX() + 1, this.getY()).equals(Color.WHITE)|| city.getColor(this.getX() + 1, this.getY()).equals(Color.DARK_GRAY)||city.getColor(this.getX() + 1, this.getY()).equals(Color.RED)||city.getColor(this.getX() + 1, this.getY()).equals(Color.YELLOW)) {
			this.dir = Direction.WEST;
		} 
		
		else {

			city.setColor(this.x, this.y, Color.BLACK);
			this.x += 1;
			city.setColor(this.x, this.y, color);
		}
	}


	public void isMovingWest(City city, Color color){

		if (this.getX() - 1 < 0|| city.getColor(this.getX() - 1, this.getY()).equals(Color.WHITE)|| city.getColor(this.getX() - 1, this.getY()).equals(Color.DARK_GRAY)||city.getColor(this.getX() - 1, this.getY()).equals(Color.RED)||city.getColor(this.getX() - 1, this.getY()).equals(Color.YELLOW)) {
			this.dir = Direction.EAST;
		} 
		
		else {
			
			city.setColor(this.x, this.y, Color.BLACK);
			this.x = this.x - 1;
			city.setColor(this.x, this.y, color);
		}
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + dir + ")";
	}
}
