package com.rea.simulateRobot.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.EnumUtils;

import com.rea.simulateRobot.constants.Constants;
import com.rea.simulateRobot.exception.CannotPlaceRobotException;
import com.rea.simulateRobot.exception.InvalidDirectionException;
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

		for (int i = 0; i < commandList.size(); i++) {
			if (i == 0) {				
				String firstCommand = commandList.get(i);
				setInitialPosition(firstCommand);			
			}
			
			if(commandList.get(i).equals("")){
				continue;
			}
		}
		return null;
	}

	private void setInitialPosition(String firstCommand) {
		String[] commandArr = firstCommand.split(" ");

		try {
			if (commandArr.length > 1) {
				if (!commandArr[0].equals(Constants.PLACE)) {
					throw new CannotPlaceRobotException(Constants.PLACE_EXCEPTION);
				} else {
					Object[] positionArr = commandArr[1].split(",");
					if(positionArr.length < 3){
						throw new MissingPositionSpecException(Constants.POSITION_SPEC_EXCEPTION);
					}else{
						Position position = new Position();
						position.setX_coordinate(Integer.parseInt((String) positionArr[0]));
						position.setY_coordinate(Integer.parseInt((String) positionArr[1]));

						if(!EnumUtils.isValidEnum(Direction.class, (String)positionArr[2])){
							throw new InvalidDirectionException(Constants.DIRECTION_EXCEPTION);
						}else{
							position.setDirection(Enum.valueOf(Direction.class, (String)positionArr[2]));
						}			
						
						System.out.println("position of the robot is : "+position);
					}						
					
				}
			} else {
				throw new PositionNotSpecifiedException(Constants.MISSING_POSITION_EXCEPTION);
			}
		} catch (CannotPlaceRobotException e1) {
			e1.printStackTrace();
		} catch (PositionNotSpecifiedException e2) {
			e2.printStackTrace();
		} catch(MissingPositionSpecException e3) {
			e3.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void isDirectionInEnum(Object object, Class<Direction> enumClass) {
		// TODO Auto-generated method stub
		
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

				// stops taking input from user, when user types report
				if (command.equals(Constants.REPORT))
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
