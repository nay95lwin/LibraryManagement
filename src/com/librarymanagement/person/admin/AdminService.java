package com.librarymanagement.person.admin;

import com.librarymanagement.menu.Menu;
import com.librarymanagement.utility.Utility;

public interface AdminService {

	public static void displayAdminMenu() {
		boolean isBack = false;
		AdminServiceImpl adminSerImpl = new AdminServiceImpl();

		do {
			System.out.println("\nPlease choose an option >>>");
			System.out.println(Menu.ADMIN.getMenu());

			switch (Utility.getUserInput()) {
			case "1":
				adminSerImpl.createAdmin();
				break;
			case "2":
				adminSerImpl.editAdmin();
				break;
			case "3":
				adminSerImpl.deleteAdmin();
				break;
			case "4":
				adminSerImpl.showAllAdmin();
				break;
			case "5":
				isBack = true;
				break;
			default:
				System.out.println("Please enter the valid input.");
			}

		} while (!isBack);
	};

	public void createAdmin();

	public void editAdmin();

	public void deleteAdmin();

	public void showAllAdmin();

	public int getIndexByAdminID();

	public int findIndex(int adminID);

}
