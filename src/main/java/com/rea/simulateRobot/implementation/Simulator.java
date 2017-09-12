package com.rea.simulateRobot.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rea.simulateRobot.constants.Constants;

public class Simulator {

	public void simulate() {
		decorators();
		getInputCommands();

	}

	// gives an introduction to our app with the instructions for using the
	// commands
	public void decorators() {
		System.out.println(Constants.DECORATION);
		System.out.println(Constants.WELCOME);
		System.out.println(Constants.DECORATION);
		System.out.println(Constants.INPUT);
	}

	public void getInputCommands() {

		Scanner scanner = new Scanner(System.in);

		try {

			List<String> commandList = new ArrayList<String>();
			String command = null;

			while (true) {
				command = scanner.nextLine();
				commandList.add(command);

				//stops taking input from user, when user types report
				if (command.equals(Constants.REPORT))
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

	}

}
