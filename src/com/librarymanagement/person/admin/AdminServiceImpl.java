package com.librarymanagement.person.admin;

import java.util.stream.IntStream;

import com.librarymanagement.LibraryManage;
import com.librarymanagement.utility.Utility;

public class AdminServiceImpl implements AdminService {

	@Override
	public void createAdmin() {
		Admin lastAdmin;
		int adminID;

		System.out.println("\n--Create Admin--");

		String adminName = Utility.getUserInput("new admin name");
		String password = Utility.getUserInput("new password");

		if (!LibraryManage.admins.isEmpty()) {
			lastAdmin = LibraryManage.admins.get(LibraryManage.admins.size() - 1);
			adminID = lastAdmin.getId() + 1;
		} else {
			adminID = 1;
		}

		LibraryManage.admins.add(new Admin(adminID, adminName, password));
		System.out.println("Admin Created Successfully!");
	}

	@Override
	public void editAdmin() {
		System.out.println("\n--Edit Admin--");

		int adminIndex = this.getIndexByAdminID();

		String newAdminName = Utility.getUserInput("new admin name");
		String newPassword = Utility.getUserInput("new password");

		LibraryManage.admins.get(adminIndex).setName(newAdminName);
		LibraryManage.admins.get(adminIndex).setPassword(newPassword);
		System.out.println("Admin Edited Successfully!");
	}

	@Override
	public void deleteAdmin() {
		System.out.println("\n--Delete Admin--");

		do {
			int adminIndex = this.getIndexByAdminID();
			Admin deleteAdmin = LibraryManage.admins.get(adminIndex);

			if (deleteAdmin.getId() != 1 && deleteAdmin.getId() != LibraryManage.loginAdmin.getId()) {
				LibraryManage.admins.remove(adminIndex);
				System.out.println("Admin Deleted Successfully!");
				break;
			}

			System.out.println("Sorry! Cannot delete this admin. (AdminID: " + deleteAdmin.getId() + ")");
		} while (true);

	}

	@Override
	public void showAllAdmin() {
		System.out.println("\n--Show Admin List--");
		LibraryManage.admins.forEach(System.out::println);
	}

	@Override
	public int getIndexByAdminID() {
		int adminID;
		int adminIndex;

		do {
			try {
				adminID = Integer.parseInt(Utility.getUserInput("admin id"));

				adminIndex = this.findIndex(adminID);

				if (adminIndex >= 0) {
					return adminIndex;
				} else {
					System.out.println("Please enter the correct admin id.");
					continue;
				}

			} catch (Exception e) {
				System.out.println("Please enter the valid input");
			}
		} while (true);

	}

	@Override
	public int findIndex(int adminID) {
		return IntStream.range(0, LibraryManage.admins.size())
				.filter(i -> LibraryManage.admins.get(i).getId() == adminID).findFirst().orElse(-1);
	}

}
