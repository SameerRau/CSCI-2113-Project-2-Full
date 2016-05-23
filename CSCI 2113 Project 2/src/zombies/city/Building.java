package zombies.city;

import java.awt.Color;

public class Building {
	
	public static final Color BUILDING_COLOR = Color.DARK_GRAY;
	public static final int MAX_SIZE = 30;
	public static final int MIN_SIZE = 10;

	private int x; // left-most x value
	private int y; // top-most y value
	private int width;
	private int height;

	public Building() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	public Building(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void initialize(City city) {
		if (!isInitialized()) {
			for (int k = 0; k < 100; k++) {
				width = city.getRand().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
				height = city.getRand().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
				x = city.getRand().nextInt(city.getWidth() + width - 1) - width
						+ 1;
				y = city.getRand().nextInt(city.getHeight() + height - 1)
						- height + 1;
				if (!this.overlaps(city)) break;
			}
		}
		drawBuilding(city);
	}

	private void drawBuilding(City city) {
		for (int i = x; i < x + width; i++) {
			for (int j = y; j < y + height; j++) {
				if (withinCityLimits(i, j, city)) {
					city.setColor(i, j, Building.BUILDING_COLOR);
				}
			}
		}

	}

	public void update(City city) {
		//
	}

	private boolean overlaps(City city) {

		assert (width != 0);
		assert (height != 0);

		for (int i = x; i < x + width; i++) {
			for (int j = y; j < y + height; j++) {
				if (withinCityLimits(i, j, city) && city.getColor(i, j) == Building.BUILDING_COLOR) {
					return true;
				}
			}
		}
		return false;
	}
 
	private boolean withinCityLimits(int x, int y, City city) {
		return x >= 0 && y >= 0 && x < city.getWidth() && y < city.getHeight();
	}

	private boolean isInitialized() {
		return width != 0 && height != 0;
	}

	public String toString() {
		return "(" + x + ", " + y + ", " + width + ", " + height + ")";
	}

}
