package ua.BotsCrew.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

	public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/botscrew";
	public static final String USER = "root";
	public static final String PASSWORD = "1r6o6c6k3";
	public static final String CREATE_TABLE_BOOKS = "CREATE TABLE IF NOT EXISTS books  (   "
			+ "id INT PRIMARY KEY AUTO_INCREMENT," + "authorsLastname VARCHAR(255)," + "bookname VARCHAR(255))";
	public static final String ADD = "INSERT INTO books(authorsLastname, bookname) VALUES(?,?)";
	public static final String UPDATE_BY_ID = "UPDATE books SET bookname=? WHERE id=?";
	public static final String UPDATE_BY_NAME = "UPDATE books SET bookname=? WHERE bookname LIKE ?";
	public static final String DELETE_BY_ID = "DELETE FROM books WHERE id=?";
	public static final String DELETE_BY_NAME = "DELETE FROM books WHERE bookname LIKE ?";
	public static final String SHOW = "SELECT * FROM books";
	public static final String SHOW_BY_NAME = "SELECT * FROM books WHERE bookname LIKE ? ";
	private static Connection connection;

	public void createConnection() throws SQLException {
		connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);

		System.out.println(" CONNECTION READY!");
		Statement statement = connection.createStatement();
		statement.execute(CREATE_TABLE_BOOKS);
		statement.close();
	}
public void endConnection() throws SQLException {
	connection.close();
}
	public void addBook() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("������ ϲ� ������");
		String authorsLastname = sc.next();
		System.out.println("������ ����� �����");
		String bookname = sc.next();

		PreparedStatement addStatement = connection.prepareStatement(ADD);
		addStatement.setString(1, authorsLastname);
		addStatement.setString(2, bookname);
		addStatement.executeUpdate();
		
		System.out.println("����� ������ ���������!");
		addStatement.close();
	}

	public void deleteBook() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("������ ����� ����� �� ��� �������� �����!");
		PreparedStatement deleteStatementByName = connection.prepareStatement(DELETE_BY_NAME);
		PreparedStatement deleteStatementById = connection.prepareStatement(DELETE_BY_ID);
		PreparedStatement checkStatement = connection.prepareStatement(SHOW_BY_NAME);
		String booksname1 = sc.next();
		checkStatement.setString(1, booksname1);
		ResultSet records = checkStatement.executeQuery();
		int i = 0;
		String temp = "";
		List<Integer> list = new ArrayList();
		while (records.next()) {
			list.add(records.getInt(1));
			i++;

			temp += ("\n id:" + records.getInt(1) + " authorsLastname: " + records.getString(2) + " booksname: "
					+ records.getString(3) + "\n");

		}
		if (i > 1) {
			System.out.println(
					"���� ������� ������ � ����� ������. ��� �� ������ �������?(�������� ����� id �����)" + temp);
			checkStatement.close();
			int tempid;
			while (true) {
				tempid = sc.nextInt();

				if (!list.contains(tempid)) {
					System.out.println("������ Id ����!������ �� ��� �����.");
					continue;
				} else
					break;
			}

			deleteStatementById.setInt(1, tempid);

			deleteStatementById.executeUpdate();
			System.out.println("����� � id: " + tempid + " ������ ��������!");
		} else {

			deleteStatementByName.setString(1, booksname1);
			deleteStatementByName.executeUpdate();
		}

		System.out.println("����� ������ ��������");
		sc.close();
	}

	public void updateBook() throws SQLException {
		Scanner sc = new Scanner(System.in);

		PreparedStatement updateStatementByName = connection.prepareStatement(UPDATE_BY_NAME);
		PreparedStatement updateStatementById = connection.prepareStatement(UPDATE_BY_ID);
		PreparedStatement checkStatement = connection.prepareStatement(SHOW_BY_NAME);
		System.out.println("�� ��� ���� ����� ������ �����?");
		String booksname1 = sc.next();
		checkStatement.setString(1, booksname1);
		ResultSet records = checkStatement.executeQuery();
		int i = 0;
		String temp = "";
		List<Integer> list = new ArrayList();
		while (records.next()) {
			list.add(records.getInt(1));
			i++;

			temp += ("\n id:" + records.getInt(1) + " authorsLastname: " + records.getString(2) + " booksname: "
					+ records.getString(3) + "\n");

		}
		if (i > 1) {
			System.out.println(
					"���� ������� ������ � ����� ������. ��� �� ������ �������?(�������� ����� id �����)" + temp);
			checkStatement.close();
			int tempid;
			while (true) {
				tempid = sc.nextInt();

				if (!list.contains(tempid)) {
					System.out.println("������ Id ����!������ �� ��� �����.");
					continue;
				} else
					break;
			}

			updateStatementById.setInt(1, tempid);
			System.out.println("������ ���� ����� �����");
			String booksname2 = sc.next();
			updateStatementById.setString(1, booksname2);
			updateStatementById.setInt(2, tempid);
			updateStatementById.executeUpdate();
			System.out.println("����� � id: " + tempid + " ������ ��������!");
		} else {

			System.out.println("������ ���� ����� �����");
			String booksname2 = sc.next();

			updateStatementByName.setString(1, booksname2);
			updateStatementByName.setString(2, booksname1);
			updateStatementByName.executeUpdate();
		}

		System.out.println("����� ������ ������");
		 updateStatementByName.close();
		 updateStatementById.close();
		sc.close();
	}

	public void showBook() throws SQLException {

		Statement selectStatement = connection.createStatement();
		ResultSet records = selectStatement.executeQuery(SHOW);
		while (records.next()) {
			System.out.println("\n id:" + records.getInt(1) + " authorsLastname: " + records.getString(2)
					+ " booksname: " + records.getString(3) + "\n");
		}
		 selectStatement.close();

	}
}
