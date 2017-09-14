package com.rea.simulateRobot;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rea.simulateRobot.exception.CannotPlaceRobotException;
import com.rea.simulateRobot.exception.InvalidPositionException;
import com.rea.simulateRobot.exception.MissingPositionSpecException;
import com.rea.simulateRobot.exception.PositionNotSpecifiedException;
import com.rea.simulateRobot.implementation.Simulator;
import com.rea.simulateRobot.model.Direction;
import com.rea.simulateRobot.model.Position;

public class AppTest
{
  
    @Test
    public void checkFinalDirectionWithNormalData() throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException{
    	
    	List<String> list = new ArrayList<String>();
    	list.add("PLACE 1,1,NORTH");
    	list.add("MOVE");
    	list.add("REPORT");
    	Simulator simulator = new Simulator();
    	Position position = simulator.getFinalLocation(list);
    	assertEquals(Direction.NORTH, position.getDirection());
    }
    
    @Test
    public void checkFinalDirectionWithEmptySpaceData() throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException{
    	
    	List<String> list = new ArrayList<String>();
    	list.add("PLACE 1,1,EAST");
    	list.add("MOVE");
    	list.add("");
    	list.add("REPORT");
    	Simulator simulator = new Simulator();
    	Position position = simulator.getFinalLocation(list);
    	assertEquals(Direction.EAST, position.getDirection());
    }
    
    @Test
    public void checkFinalDirectionWithProperData() throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException{
    	
    	List<String> list = new ArrayList<String>();
    	list.add("PLACE 1,2,EAST");
    	list.add("MOVE");
    	list.add("MOVE");
    	list.add("LEFT");
    	list.add("MOVE");
    	list.add("REPORT");
    	Simulator simulator = new Simulator();
    	Position position = simulator.getFinalLocation(list);
    	assertEquals(Direction.NORTH, position.getDirection());
    	assertEquals(3, position.getX_coordinate());
    	assertEquals(3, position.getY_coordinate());;
    }
    
    
    
    /*@Test
    public void checkNullDirectionForExceededCoordinates() throws CannotPlaceRobotException {
    	
    	
    		List<String> list = new ArrayList<String>();
        	list.add("PLACE 6,6,EAST");
        	list.add("MOVE");
        	list.add("");
        	list.add("REPORT");
        	Simulator simulator = new Simulator();
        	Position position = simulator.getFinalLocation(list);
        	
        	thrown.
    	
    }*/
   
}
