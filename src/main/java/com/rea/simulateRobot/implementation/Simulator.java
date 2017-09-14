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
		Position position = getFinalLocation(commandList);

	}

	private Position getFinalLocation(List<String> commandList) {
		
		Position finalPosition = new Position();
		boolean isPlaced = false;

		for (int i = 0; i < commandList.size(); i++) {
			
			String[] commandArr = commandList.get(i).split(" ");
			if(commandArr.length > 1 && commandArr[0].equalsIgnoreCase(Constants.PLACE)){
				System.out.println("encountered PLACE command");
				Position position = setInitialPosition(commandArr);
				if(position.getX_coordinate() > 5 || position.getY_coordinate() > 5){
						continue;
				}else{
					System.out.println("Found PLACE with proper position");
					finalPosition.setX_coordinate(position.getX_coordinate());
					finalPosition.setY_coordinate(position.getY_coordinate());
					finalPosition.setDirection(position.getDirection());
					isPlaced = true;
				}
			} else {
				
				if(isPlaced){
					System.out.println("found PLACE command! Proceeding with "+commandList.get(i)+" command now!");
					finalPosition = getLocationAfterMovement(finalPosition, commandList.get(i));
				}
				continue;
			}
					
			if(commandList.get(i).equals("")){
				continue;
			}
		}
		
		System.out.println("final location :: "+finalPosition);
		return finalPosition;
	}

	private Position getLocationAfterMovement(Position finalPosition, String movement) {
		
		/*switch(movement.toLowerCase()) {
		case "MOVE":
			
		}*/
		return null;
	}

	private Position setInitialPosition(String[] placeCommandArr) {
		
		Position position = new Position();
		
		try {
			if (placeCommandArr.length == 2) {
				
				//throws exception when the first command is not PLACE
				if (!placeCommandArr[0].equalsIgnoreCase(Constants.PLACE)) {
					throw new CannotPlaceRobotException(Constants.PLACE_EXCEPTION);
				} else {					
					position = processRobotPosition(placeCommandArr[1]);	
					
				}
			} else if (placeCommandArr.length < 2) {
				//throws exception when the position co-ordinates of robot is missing
				throw new PositionNotSpecifiedException(Constants.MISSING_POSITION_EXCEPTION);
			} else {
				throw new InvalidPositionException(Constants.INVALID_POSITION_EXCEPTION);
			}
		} catch(NumberFormatException ne) {
			throw new NumberFormatException(Constants.NUMBER_FORMAT_EXCEPTION);
		} catch (CannotPlaceRobotException e1) {
			e1.printStackTrace();
		} catch (PositionNotSpecifiedException e2) {
			e2.printStackTrace();
		} catch (InvalidPositionException e4) {
			e4.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return position;
		
	}

	private Position processRobotPosition(String positionStr) {
		
		Position position = new Position();
		try {
			
			Object[] positionArr = positionStr.split(",");
			if (positionArr.length == 3) {

				position.setX_coordinate(Integer.parseInt((String) positionArr[0]));
				position.setY_coordinate(Integer.parseInt((String) positionArr[1]));

				checkIfValidDirection((String) positionArr[2], position);
				//checkIfValididCoordinates(position);

			} else {
				// throws exception if any of position co-ordinates is
				// missing
				throw new MissingPositionSpecException(Constants.POSITION_SPEC_EXCEPTION);
			}
		} catch (MissingPositionSpecException e) {
			e.printStackTrace();
		}
		
		return position;
		
	}


	private void checkIfValidDirection(String direction, Position position) {

		try {
			if (EnumUtils.isValidEnum(Direction.class, direction)) {
				position.setDirection(Enum.valueOf(Direction.class, direction));
			} else {
				// throws exception for invalid direction
				throw new InvalidDirectionException(Constants.DIRECTION_EXCEPTION);
			}
		} catch (InvalidDirectionException e) {
			e.printStackTrace();
		}

	}

	// gives an introduction to our app with the instructions for using the
	// commands
	public void decorators() {
		System.out.println(Constants.DECORATION);
		System.out.println(Constants.WELCOME);
		System.out.println(Constants.DECORATION);
		System.out.println(Constants.INPUT);
	}

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

			System.out.println("commands typed ::");
			for (String i : commandList) {
				System.out.println(i);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			scanner.close();
		}

		return commandList;

	}

}
