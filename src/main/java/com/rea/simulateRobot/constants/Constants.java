package com.rea.simulateRobot.constants;

public class Constants {
	
	public static final String DECORATION = "**********************************************************************************************************************";
	public static final String WELCOME = "					WELCOME TO THE REA TOY ROBOT SIMULATOR APP!";
	public static final String GAME_ESSENCE = "				Simulate movement of robot on the table with few commands";
	public static final String STRING_RULE = "RULES :";
	public static final String EMPTY_STRING = "";
	
	
	//RULES
	public static final String RULES = "Here are the few commands to simulate movement to the robot on the table :";
	public static final String RULE_PLACE = "1) PLACE 0,0,F : This command will PLACE your robot on the table at 0,0 co-ordinates facing F direction";
	public static final String EXAMPLE = "For example: PLACE 0,0,NORTH - This command will place your robot on the table at the SOUTH-WEST corner of the table, facing NORTH direction";
	public static final String VALID_DIRECTIONS = "2) Valid directions are : NORTH, EAST, SOUTH, WEST. Any PLACE command with invalid position will be IGNORED";
	public static final String TABLE_MEASUREMENT = "3) Remember that the table's measurement is 5units * 5units!";
	public static final String RULE_MOVE = "4) MOVE : This command will move the robot by 1 unit in the direction it is currently facing";
	public static final String RULE_LEFTRIGHT = "5) LEFT/RIGHT : Either of these commands will rotate the robot 90 degrees. This command will not affect the position of the robot";
	public static final String RULE_REPORT = "6) REPORT : This command will give the final position of the robot";
	public static final String RULE_IGNORE = "7) Any command that makes the robot fall off the table will be ignored!";
	
	//EXAMPLE
	public static final String SAMPLE_INPUT = "EXAMPLE INPUT :";
	public static final String EXAMPLE_LINE1 = "PLACE 1,2,NORTH";
	public static final String EXAMPLE_LINE2 = "MOVE";
	public static final String EXAMPLE_LINE3 = "REPORT";
	public static final String EXAMPLE_OUTPUT_STRING = "EXAMPLE OUTPUT :";
	public static final String OUTPUT = "The final position of robot is : ";
	public static final String EXAMPLE_OUTPUT = OUTPUT + "1,3,NORTH";
	
	
	public static final String INPUT = "Lets move our robot now! Please enter your commands :";
	
	
	//COMMANDS
	public static final String PLACE = "PLACE";
	public static final String MOVE = "MOVE";
	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	public static final String REPORT = "REPORT";		
	
	
	//EXCEPTIONS
	public static final String PLACE_EXCEPTION = "Since you haven't PLACEd your robot on the table, its movement and location cannot be determined. Please try again!";
	public static final String MISSING_POSITION_EXCEPTION = "Since the initial co-ordinates and direction are not specified next to your PLACE command, the robot's directions could not be simulated.";
	public static final String POSITION_SPEC_EXCEPTION = "Robot position undetermined since initial position co-ordinates are not specified properly.";
	public static final String DIRECTION_EXCEPTION = "Robot position undetermined since initial direction specified is invalid.";
	public static final String NUMBER_FORMAT_EXCEPTION = "The co-ordinates given for PLACE command is invalid.";
	public static final String INVALID_POSITION_EXCEPTION = "Robot position is undetermined. Please specify as 'PLACE 4,5,NORTH' for example.";
	
	
	
			

}
