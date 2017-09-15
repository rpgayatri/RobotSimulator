package com.rea.simulateRobot;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.rea.simulateRobot.exception.CannotPlaceRobotException;
import com.rea.simulateRobot.exception.InvalidDirectionException;
import com.rea.simulateRobot.exception.InvalidPositionException;
import com.rea.simulateRobot.exception.MissingPositionSpecException;
import com.rea.simulateRobot.exception.PositionNotSpecifiedException;
import com.rea.simulateRobot.implementation.Simulator;
import com.rea.simulateRobot.model.Direction;
import com.rea.simulateRobot.model.Position;

public class AppTest {

	@Test
	public void checkFinalDirectionWithNormalData() throws CannotPlaceRobotException {

		List<String> list = Arrays.asList("PLACE 1,1,NORTH","MOVE","report");
		Simulator simulator = new Simulator();
		Position position = simulator.getFinalLocation(list);
		assertEquals(Direction.NORTH, position.getDirection());
	}

	@Test
	public void checkFinalDirectionWithEmptySpaceData() throws CannotPlaceRobotException {

		List<String> list = Arrays.asList("PLACE 1,1,EAST","MOVE","","report");
		Simulator simulator = new Simulator();
		Position position = simulator.getFinalLocation(list);
		assertEquals(Direction.EAST, position.getDirection());
	}

	@Test
	public void checkFinalDirectionWithProperData() throws CannotPlaceRobotException {

		List<String> list = Arrays.asList("PLACE 1,2,EAST","MOVE","MOVE","LEFT","MOVE","REPORT");
		Simulator simulator = new Simulator();
		Position position = simulator.getFinalLocation(list);
		assertEquals(Direction.NORTH, position.getDirection());
		assertEquals(3, position.getX_coordinate());
		assertEquals(3, position.getY_coordinate());
		;
	}
	
	//contains command which make the robot fall. These commands should be ignored
	@Test
	public void checkDirectionWithCommandsToBeIgnored() throws CannotPlaceRobotException {

		List<String> list = Arrays.asList("PLACE 1,2,north","move","place 3,4,north","move","MOVE","report");
		Simulator simulator = new Simulator();
		Position position = simulator.getFinalLocation(list);
		assertEquals(Direction.NORTH, position.getDirection());
		assertEquals(3, position.getX_coordinate());
		assertEquals(5, position.getY_coordinate());
	}
	
	//first invalid command needs to be ignored
	@Test
	public void checkDirectionWithSomeInvalidCommandsToBeIgnored() throws CannotPlaceRobotException {

		List<String> list = Arrays.asList("PLACE qw,as,north","move","place 3,4,north","move","report");
		Simulator simulator = new Simulator();
		Position position = simulator.getFinalLocation(list);
		assertEquals(Direction.NORTH, position.getDirection());
		assertEquals(3, position.getX_coordinate());
		assertEquals(5, position.getY_coordinate());
	}
	
	@Test
	public void checkForinvalidCommands() throws CannotPlaceRobotException {

		List<String> list = Arrays.asList("left","report 0,1,north","right","place 1,2,north","report");
		Simulator simulator = new Simulator();
		Position position = simulator.getFinalLocation(list);
		assertEquals(Direction.NORTH, position.getDirection());
		assertEquals(1, position.getX_coordinate());
		assertEquals(2, position.getY_coordinate());
	}
	
	
	@Test(expected = CannotPlaceRobotException.class)
	public void checkForWrongPlaceCommand() throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException, InvalidDirectionException {

		String[] arr = new String[]{"report","dw,we,north"};
		Simulator simulator = new Simulator();
		simulator.setInitialPosition(arr);
		
	}

	
	//Tests when no valid PLACE command is found
	@Test(expected = CannotPlaceRobotException.class)
	public void checkForNoValidPlaceCommand() throws CannotPlaceRobotException {
		List<String> list = Arrays.asList("left","report 0,1,north","right","right","report");
		Simulator simulator = new Simulator();
		simulator.getFinalLocation(list);
	}
	
	@Test(expected = PositionNotSpecifiedException.class)
	public void checkForMissingPositionPlaceCommand() throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException, InvalidDirectionException {

		String[] arr = new String[]{"place"};
		Simulator simulator = new Simulator();
		simulator.setInitialPosition(arr);
	}
	
	@Test(expected = InvalidDirectionException.class)
	public void checkForInvalidDirection() throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException, InvalidDirectionException {

		String[] arr = new String[]{"place","1,2,asdf"};
		Simulator simulator = new Simulator();
		simulator.setInitialPosition(arr);
	}
	
	@Test(expected = MissingPositionSpecException.class)
	public void checkForMissingPositionSpec() throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException, InvalidDirectionException {

		String[] arr = new String[]{"place","1,2"};
		Simulator simulator = new Simulator();
		simulator.setInitialPosition(arr);
	}
	
	@Test(expected = InvalidPositionException.class)
	public void checkForInvalidPosition() throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException, InvalidDirectionException {

		String[] arr = new String[]{"place","1,2","north"};
		Simulator simulator = new Simulator();
		simulator.setInitialPosition(arr);
	}
	
}
