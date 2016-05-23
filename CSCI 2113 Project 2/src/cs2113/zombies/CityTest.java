package cs2113.zombies;

//package cs2113.zombies.city;

//import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

//import static cs2113.zombies.city.Direction.*;
 
//import org.junit.Before;
//import org.junit.Test;

public class CityTest {
	
	City city;
	 
	//@Before
	public void setUp() throws Exception {
		
		Building bldg = new Building(3, 3, 2, 2);
		List<Direction> dirs = new LinkedList<>();
	//	dirs.add(NIL);
    //  dirs.add(NIL);
	//	dirs.add(NIL);
	//	dirs.add(NIL);
	//	Human p = new Human(1, 1, 3, EAST, dirs);
		List<Building> bldgs = new LinkedList<>();
		bldgs.add(bldg);
		List<Human> humans = new LinkedList<>();
	//	humans.add(p);
		city = new City(5, 5, bldgs, humans);
		city.initialize();
	}

	//@Test
	public void testHumanWalksIntoBuilding() {
		String s;
		s = ". . . . . \n" + 
		    ". . . . . \n" +
		    ". . . . . \n" +
			". H . x x \n" +
			". . . x x \n";
	//	assertEquals(city.toString(), s);
		city.update();
		s = ". . . . . \n" + 
	        ". . . . . \n" +
	        ". . . . . \n" +
			". . H x x \n" +
			". . . x x \n";
	//	assertEquals(city.toString(), s);
		city.update();
		s = ". . . . . \n" + 
		    ". . . . . \n" +
		    ". . . . . \n" +
			". . H x x \n" +
			". . . x x \n";
	//	assertEquals(city.toString(), s);
		city.update();
		s = ". . . . . \n" + 
			". . . . . \n" +
			". . . . . \n" +
			". H . x x \n" +
			". . . x x \n";
	//	assertEquals(city.toString(), s);
		city.update();
		s = ". . . . . \n" + 
			". . . . . \n" +
			". . . . . \n" +
			"H . . x x \n" +
			". . . x x \n";
	//	assertEquals(city.toString(), s);		
	}

}
