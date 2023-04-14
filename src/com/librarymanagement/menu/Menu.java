package com.librarymanagement.menu;

public enum Menu {
	START("1. Login\n2. Exit"), MAIN("1. Admin\n2. Member\n3. Book\n4. History\n5. Logout\n6. Save & Exit"),
	ADMIN("1. Create Admin\n2. Edit Admin\n3. Delete Admin\n4. Show Admin List\n5. Main Menu"),
	MEMBER("1. Create Member\n2. Edit Member\n3. Delete Member\n4. Show Member List\n5. Main Menu"),
	BOOK("1. Add Book\n2. Edit Book\n3. Delete Book\n4. Show Book List\n5. Lend Book\n6. Return Book\n7. Main Menu"),
	HISTORY("1. Show History\n2. Clear History\n3. Main Menu");

	String menu;

	private Menu(String menu) {
		this.menu = menu;
	}

	public String getMenu() {
		return menu;
	}

}
