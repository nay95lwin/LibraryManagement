package com.librarymanagement.history;

import com.librarymanagement.menu.Menu;
import com.librarymanagement.utility.Utility;

public interface HistoryService {

	public static void displayHistoryMenu() {
		boolean isBack = false;
		HistoryServiceImpl historySerImpl = new HistoryServiceImpl();

		do {
			System.out.println("\nPlease choose an option >>>");
			System.out.println(Menu.HISTORY.getMenu());

			switch (Utility.getUserInput()) {
			case "1":
				historySerImpl.showHistory();
				break;
			case "2":
				historySerImpl.clearHistory();
				break;
			case "3":
				isBack = true;
				break;
			default:
				System.out.println("Please enter the valid input.");
			}

		} while (!isBack);
	};

	public void showHistory();

	public void clearHistory();
	
	public void writeHistory(String history);

}
