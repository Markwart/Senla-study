package by.senla.study.project.init;

import java.util.Scanner;

public class Init {

	public static void main(String[] args) throws InterruptedException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Hello! This is application for working with the database \n");

		Common.chooseOperation(scanner);

		scanner.close();
	}

}
