package ua.BotsCrew.test;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static Library libraryUser = new Library();
	
	

public static void main(String[] args) throws  SQLException {
	
	try {
		libraryUser.createConnection();
		
		
		
		
		while(true){System.out.println(" ������ 1 ��� �������� ���� �����,"
				+ "\n 2  ��� �������� �����"
				+ "\n 3 ��� ���� ��� �������� ����� ��� �����."
				+ "\n 4 ��� ���� ��� �������� ������ ����� � �������");
		Scanner sc = new Scanner(System.in);
				String sw=sc.next();
		switch(sw){
		//	case "1":{
				
			//	libraryUser.addBook();
		//		break;}
		//	case "2":{
		//		libraryUser.deleteBook();
		//		break;}
		//	case "3":{
		//		libraryUser.updateBook();
		//		break;}
		//	case "4":{
		//		libraryUser.showBook();
		//		break;
			//}
			default: System.out.println("������ �������"); break;
		}
		sc.close();}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally{libraryUser.endConnection();}
	
}

}
