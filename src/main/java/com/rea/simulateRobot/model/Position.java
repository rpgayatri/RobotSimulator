package com.rea.simulateRobot.model;

import com.rea.simulateRobot.model.RobotDirection.Direction;

public class Position {
	
	private int x_coordinate;
	private int y_coordinate;
	private Direction direction;
	
	public int getX_coordinate() {
		return x_coordinate;
	}
	public void setX_coordinate(int x_coordinate) {
		this.x_coordinate = x_coordinate;
	}
	public int getY_coordinate() {
		return y_coordinate;
	}
	public void setY_coordinate(int y_coordinate) {
		this.y_coordinate = y_coordinate;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	
	

}
