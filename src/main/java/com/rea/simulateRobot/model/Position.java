package com.rea.simulateRobot.model;

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

	@Override
	public String toString() {
		return "Position [x_coordinate=" + x_coordinate + ", y_coordinate=" + y_coordinate + ", direction=" + direction
				+ "]";
	}

}
