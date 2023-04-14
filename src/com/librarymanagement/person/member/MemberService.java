package com.librarymanagement.person.member;

import com.librarymanagement.menu.Menu;
import com.librarymanagement.utility.Utility;

public interface MemberService {

	public static void displayMemberMenu() {
		boolean isBack = false;
		MemberServiceImpl memberSerImpl = new MemberServiceImpl();

		do {
			System.out.println("\nPlease choose an option >>>");
			System.out.println(Menu.MEMBER.getMenu());

			switch (Utility.getUserInput()) {
			case "1":
				memberSerImpl.createMember();
				break;
			case "2":
				memberSerImpl.editMember();
				break;
			case "3":
				memberSerImpl.deleteMember();
				break;
			case "4":
				memberSerImpl.showAllMember();
				break;
			case "5":
				isBack = true;
				break;
			default:
				System.out.println("Please enter the valid input.");
			}

		} while (!isBack);
	};

	public void createMember();

	public void editMember();

	public void deleteMember();

	public void showAllMember();

	public int getIndexByMemberID();

	public int findIndex(int memberID);

}
