package ua.BotsCrew.test;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static Library libraryUser = new Library();

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		try {
			libraryUser.createConnection();

			while (true) {
				System.out.println(" Enter 1 to add new book." 
						+ "\n Enter 2 to delete book." 
						+ "\n Enter 3 to upgrade name of book." 
						+ "\n Enter 4 to show all books in database.");

				String sw = sc.next();
				switch (sw) {
				case "1": {

					libraryUser.addBook();
					break;
				}
				case "2": {
					libraryUser.deleteBook();
					break;
				}
				case "3": {
					libraryUser.updateBook();
					break;
				}
				case "4": {
					libraryUser.showBook();
					break;
				}
				default:
					System.out.println("Wrong number. Please try aganin.");
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			libraryUser.endConnection();
		}

	}

}
