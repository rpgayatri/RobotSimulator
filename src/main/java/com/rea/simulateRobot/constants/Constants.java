package com.rea.simulateRobot.constants;

public class Constants {
	
	public static final String DECORATION = "**********************************************************************";
	public static final String WELCOME = "		WELCOME TO THE REA TOY ROBOT SIMULATOR APP!";
	public static final String ENTER_INPUTS = "You can make the robot move on the table with few commands";
	public static final String RULES = "There are few commands to simulate movement to the robot.";
	public static final String RULE_PLACE = "PLACE - This command will place your robot on the table";
	
	
	
	public static final String INPUT = "Lets move our robot now! Please enter your commands";
	
	
	//COMMANDS
	public static final String PLACE = "PLACE";
	public static final String MOVE = "MOVE";
	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	public static final String REPORT = "REPORT";		
	
	
	//EXCEPTIONS
	public static final String PLACE_EXCEPTION = "Since you haven't PLACEd your robot on the table, its movement and location cannot be determined. Please try again!";
	public static final String MISSING_POSITION_EXCEPTION = "Since the initial co-ordinates and direction are not specified next to your PLACE command, the robot's directions could not be simulated. Please try again!";
	public static final String POSITION_SPEC_EXCEPTION = "Robot position undetermined since initial position co-ordinates are not specified properly";
	public static final String DIRECTION_EXCEPTION = "Robot position undetermined since initial direction specified is invalid. Please try again!";
	
			

}
