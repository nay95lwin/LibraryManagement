package com.librarymanagement.library;

import com.librarymanagement.LibraryManage;
import com.librarymanagement.book.BookService;
import com.librarymanagement.csvfile.csvFile;
import com.librarymanagement.history.HistoryService;
import com.librarymanagement.menu.Menu;
import com.librarymanagement.person.admin.Admin;
import com.librarymanagement.person.admin.AdminService;
import com.librarymanagement.person.member.MemberService;
import com.librarymanagement.utility.Utility;

public class LibraryServiceImpl implements LibraryService {

	@Override
	public void startMenu() {

		boolean isLogin;

		do {
			isLogin = false;

			do {
				System.out.println("Welcome from library management system!");
				System.out.println(Menu.START.getMenu());

				switch (Utility.getUserInput()) {
				case "1":
					isLogin = this.login();
					break;
				case "2":
					Utility.exit();
					break;
				default:
					System.out.println("Please enter the valid input.");
				}

			} while (!isLogin);

			this.displayMainMenu();
		} while (true);

	}

	@Override
	public boolean login() {
		String name = Utility.getUserInput("admin name");
		String password = Utility.getUserInput("password");

		for (Admin admin : LibraryManage.admins) {
			if (admin.getName().equals(name) && admin.getPassword().equals(password)) {
				LibraryManage.loginAdmin = admin;
				System.out.println("Login Success!");
				return true;
			}
		}

		System.out.println("Login Fail...");
		return false;
	}

	@Override
	public boolean logout() {
		LibraryManage.loginAdmin = null;
		return true;
	}

	@Override
	public void displayMainMenu() {
		boolean isLogout = false;
		boolean canSave;

		System.out.println("\nWelcome " + LibraryManage.loginAdmin.getName() + "!!!");

		do {
			System.out.println("\nPlease choose the management >>>");
			System.out.println(Menu.MAIN.getMenu());

			switch (Utility.getUserInput()) {
			case "1":
				AdminService.displayAdminMenu();
				break;
			case "2":
				MemberService.displayMemberMenu();
				break;
			case "3":
				BookService.displayBookMenu();
				break;
			case "4":
				HistoryService.displayHistoryMenu();
				break;
			case "5":
				canSave = csvFile.saveListToCsv();
				if (canSave)
					isLogout = this.logout();
				System.out.println();
				break;
			case "6":
				canSave = csvFile.saveListToCsv();
				if (canSave)
					Utility.exit();
				break;
			default:
				System.out.println("Please enter the valid input.");
			}

		} while (!isLogout);

	}

}
