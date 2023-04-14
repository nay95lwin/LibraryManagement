package com.librarymanagement.book;

import com.librarymanagement.menu.Menu;
import com.librarymanagement.utility.Utility;

public interface BookService {

	public static void displayBookMenu() {
		boolean isBack = false;
		BookServiceImpl bookSerImpl = new BookServiceImpl();

		do {
			System.out.println("\nPlease choose an option >>>");
			System.out.println(Menu.BOOK.getMenu());

			switch (Utility.getUserInput()) {
			case "1":
				bookSerImpl.addBook();
				break;
			case "2":
				bookSerImpl.editBook();
				break;
			case "3":
				bookSerImpl.deleteBook();
				break;
			case "4":
				bookSerImpl.showAllBook();
				break;
			case "5":
				bookSerImpl.lendBook();
				break;
			case "6":
				bookSerImpl.returnBook();
				break;
			case "7":
				isBack = true;
				break;
			default:
				System.out.println("Please enter the valid input.");
			}

		} while (!isBack);
	};

	public void addBook();

	public void editBook();

	public void deleteBook();

	public void showAllBook();

	public void lendBook();

	public void returnBook();

	public int getIndexByBookID();

	public int findIndex(int bookID);

}
