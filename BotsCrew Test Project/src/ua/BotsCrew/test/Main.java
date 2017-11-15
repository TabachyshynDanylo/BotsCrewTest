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
				System.out.println(" введіть 1 щоб добавити нову книгу," + "\n 2  щоб видалити книгу"
						+ "\n 3 для того щоб обновити запис про книгу."
						+ "\n 4 для того щоб побачити поточні книги в таблиці");

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
					System.out.println("невірна команда");
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
