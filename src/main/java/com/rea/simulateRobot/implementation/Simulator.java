package com.rea.simulateRobot.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.EnumUtils;

import com.rea.simulateRobot.constants.Constants;
import com.rea.simulateRobot.exception.CannotPlaceRobotException;
import com.rea.simulateRobot.exception.InvalidDirectionException;
import com.rea.simulateRobot.exception.InvalidPositionException;
import com.rea.simulateRobot.exception.MissingPositionSpecException;
import com.rea.simulateRobot.exception.PositionNotSpecifiedException;
import com.rea.simulateRobot.model.Direction;
import com.rea.simulateRobot.model.Position;

public class Simulator {

	public void simulate() {
		decorators();
		List<String> commandList = getInputCommands();

		try {
			Position position = getFinalLocation(commandList);
			if (position.getDirection() != null) {
				System.out.println(Constants.OUTPUT + position.getX_coordinate() + "," + position.getY_coordinate() + ","
						+ position.getDirection());
			}
		} catch (CannotPlaceRobotException cpre) {
			
			System.out.println(Constants.PLACE_EXCEPTION);
		}
		

	}

	public Position getFinalLocation(List<String> commandList) throws CannotPlaceRobotException {
		
		Position finalPosition = new Position();
		Position position = new Position();
		boolean isPlaced = false;
		
		for (int i = 0; i < commandList.size(); i++) {

			String[] commandArr = commandList.get(i).split(" ");
			try {

				// checks if command is PLACE
				if (commandArr.length > 1 && commandArr[0].equalsIgnoreCase(Constants.PLACE)) {
					// System.out.println("encountered PLACE command");

					position = setInitialPosition(commandArr);

					// PLACE command gets ignored - next iteration starts since
					// robot co-ordinates exceeds table dimension
					if ((position.getX_coordinate() > 5 || position.getX_coordinate() < 0)
							|| (position.getY_coordinate() > 5 || position.getY_coordinate() < 0)) {
						continue;
					} else {
						// Found PLACE command with proper position coordinates
						finalPosition.setX_coordinate(position.getX_coordinate());
						finalPosition.setY_coordinate(position.getY_coordinate());
						finalPosition.setDirection(position.getDirection());
						isPlaced = true;
					}
				} else {

					if (isPlaced) {

						if (finalPosition.getDirection() != null)
							finalPosition = getLocationAfterMovement(finalPosition, commandList.get(i));
					}
					continue;
				}

				if (commandList.get(i).equals("")) {
					continue;
				}

			} catch (InvalidPositionException ipe) {
				System.out.println(commandList.get(i) + " is not a valid position. " + ipe.getMessage());
			} catch (PositionNotSpecifiedException pnse) {
				System.out.println(pnse.getMessage());
			} catch (MissingPositionSpecException mpse) {
				System.out.println(mpse.getMessage() + "(" + commandArr[1] + ") is invalid.");
			} catch (CannotPlaceRobotException cpre) {
				System.out.println(Constants.PLACE_EXCEPTION);
			} catch (InvalidDirectionException ide) {
				System.out.println(ide.getMessage());
			} catch (NumberFormatException nfe) {
				System.out.println(Constants.NUMBER_FORMAT_EXCEPTION + "(" + commandArr[1] + ") is invalid.");
			}
		}
		
		//System.out.println("final location :: "+finalPosition);
		
		if(!isPlaced)
			
				throw new CannotPlaceRobotException(Constants.PLACE_EXCEPTION);
		
		
		return finalPosition;
	}

	private Position getLocationAfterMovement(Position finalPosition, String movement) {
		
		switch(movement.toLowerCase()) {
		case "move":
			finalPosition = getPositionForMove(finalPosition);
			break;
		case "right":
			finalPosition = getPositionForRight(finalPosition);
			break;
		case "left":
			finalPosition = getPositionForLeft(finalPosition);
			break;
		}
		
		return finalPosition;
	}

	//process position of robot for LEFT command
	private Position getPositionForLeft(Position finalPosition) {
		
		switch(finalPosition.getDirection().toString().toLowerCase()) {
		case "north":
			finalPosition.setDirection(Direction.WEST);
			break;
		case "east":
			finalPosition.setDirection(Direction.NORTH);
			break;
		case "south":
			finalPosition.setDirection(Direction.EAST);
			break;
		case "west":
			finalPosition.setDirection(Direction.SOUTH);
			break;
		}
		return finalPosition;
	}

	//process position of robot for RIGHT command
	private Position getPositionForRight(Position finalPosition) {

		switch (finalPosition.getDirection().toString().toLowerCase()) {
		case "north":
			finalPosition.setDirection(Direction.EAST);
			break;
		case "south":
			finalPosition.setDirection(Direction.WEST);
			break;
		case "east":
			finalPosition.setDirection(Direction.SOUTH);
			break;
		case "west":
			finalPosition.setDirection(Direction.NORTH);
			break;
		}
		return finalPosition;
	}

	
	//process position of robot for MOVE command
	private Position getPositionForMove(Position finalPosition) {
		
		int alterPosition = 0;
		
		switch(finalPosition.getDirection().toString().toLowerCase()) {
		case "north":
			alterPosition = finalPosition.getY_coordinate() + 1;
			if(alterPosition >= 0 && alterPosition <= 5){
				finalPosition.setY_coordinate(alterPosition);
			}	
			break;
		case "south":
			alterPosition = finalPosition.getY_coordinate() - 1;
			if(alterPosition >= 0 && alterPosition <= 5){
				finalPosition.setY_coordinate(alterPosition);	
			}	
			break;
		case "east":
			alterPosition = finalPosition.getX_coordinate() + 1;
			if(alterPosition >= 0 && alterPosition <= 5){
				finalPosition.setX_coordinate(alterPosition);
			}
			break;
		case "west":
			alterPosition = finalPosition.getX_coordinate() - 1;
			if(alterPosition >= 0 && alterPosition <= 5){
				finalPosition.setX_coordinate(alterPosition);
			}
			break;
		}
		
		return finalPosition;
	}

	public Position setInitialPosition(String[] placeCommandArr)
			throws CannotPlaceRobotException, PositionNotSpecifiedException, InvalidPositionException, MissingPositionSpecException, InvalidDirectionException {

		Position position = new Position();

		if (placeCommandArr.length == 2) {

			// throws exception when the first command is not PLACE
			if (!placeCommandArr[0].equalsIgnoreCase(Constants.PLACE)) {
				throw new CannotPlaceRobotException(Constants.PLACE_EXCEPTION);
			} else {
				position = processRobotPosition(placeCommandArr[1]);

			}
		} else if (placeCommandArr.length < 2) {
			// throws exception when the position co-ordinates of robot is
			// missing
			throw new PositionNotSpecifiedException(Constants.MISSING_POSITION_EXCEPTION);
		} else {
			throw new InvalidPositionException(Constants.INVALID_POSITION_EXCEPTION);
		}

		return position;

	}

	//setting coordinates from PLACE command to Direction object of Robot
	private Position processRobotPosition(String positionStr) throws MissingPositionSpecException, InvalidDirectionException {

		Position position = new Position();

		Object[] positionArr = positionStr.split(",");
		if (positionArr.length == 3) {

			position.setX_coordinate(Integer.parseInt((String) positionArr[0]));
			position.setY_coordinate(Integer.parseInt((String) positionArr[1]));

			checkIfValidDirection((String) positionArr[2], position);

		} else {
			// throws exception if any of position co-ordinates is
			// missing
			throw new MissingPositionSpecException(Constants.POSITION_SPEC_EXCEPTION);
		}

		return position;

	}

	//checks if user has given valid direction
	private void checkIfValidDirection(String direction, Position position) throws InvalidDirectionException {

		if (EnumUtils.isValidEnum(Direction.class, direction.toUpperCase())) {
			position.setDirection(Enum.valueOf(Direction.class, direction.toUpperCase()));
		} else {
			// throws exception for invalid direction
			throw new InvalidDirectionException(
					direction + " is an invalid direction. " + Constants.DIRECTION_EXCEPTION);
		}

	}

	// gives an introduction to our app with the instructions for using the
	// commands
	public void decorators() {
		System.out.println(Constants.DECORATION);
		System.out.println(Constants.WELCOME);
		System.out.println(Constants.GAME_ESSENCE);
		System.out.println(Constants.DECORATION);
		
		showRules();
		showExample();
		
		System.out.println(Constants.INPUT);
	}

	private void showRules() {
		
		System.out.println(Constants.EMPTY_STRING);
		System.out.println(Constants.STRING_RULE);
		System.out.println(Constants.RULES);
		System.out.println(Constants.RULE_PLACE);
		System.out.println(Constants.EXAMPLE);
		System.out.println(Constants.VALID_DIRECTIONS);
		System.out.println(Constants.TABLE_MEASUREMENT);
		System.out.println(Constants.RULE_MOVE);
		System.out.println(Constants.RULE_LEFTRIGHT);
		System.out.println(Constants.RULE_REPORT);
		System.out.println(Constants.RULE_IGNORE);
		System.out.println(Constants.EMPTY_STRING);
		
	}

	private void showExample() {
		
		System.out.println(Constants.SAMPLE_INPUT);
		System.out.println(Constants.EXAMPLE_LINE1);
		System.out.println(Constants.EXAMPLE_LINE2);
		System.out.println(Constants.EXAMPLE_LINE3);
		System.out.println(Constants.EMPTY_STRING);
		System.out.println(Constants.EXAMPLE_OUTPUT_STRING);
		System.out.println(Constants.EXAMPLE_OUTPUT);
		System.out.println(Constants.EMPTY_STRING);
	}

	//takes inputs from users
	public List<String> getInputCommands() {

		Scanner scanner = new Scanner(System.in);
		List<String> commandList = new ArrayList<String>();

		try {

			String command = null;

			while (true) {
				command = scanner.nextLine();
				commandList.add(command);

				// stops taking input from user, when user types REPORT
				if (command.equalsIgnoreCase(Constants.REPORT))
					break;
			}

			/*System.out.println("commands typed ::");
			for (String i : commandList) {
				System.out.println(i);
			}*/

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			scanner.close();
		}

		return commandList;

	}

}
