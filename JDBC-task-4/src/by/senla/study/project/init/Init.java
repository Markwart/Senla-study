package by.senla.study.project.init;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import by.senla.study.project.dao.jdbc.impl.util.ConnectionManager;

public class Init {

	public static void main(String[] args)
			throws InterruptedException, FileNotFoundException, SQLException, IOException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Hello! This is application for working with the database \n");

		Common.chooseOperation(scanner);

		ConnectionManager.closeConnection();
		scanner.close();
	}

}
