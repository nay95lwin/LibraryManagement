package com.librarymanagement.history;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.librarymanagement.LibraryManage;
import com.librarymanagement.csvfile.csvFile;

public class HistoryServiceImpl implements HistoryService {

	@Override
	public void showHistory() {
		System.out.println("\n--Show History--");
		csvFile.readFile("history.txt");
	}

	@Override
	public void clearHistory() {
		csvFile.deleteFile("history.txt");
		System.out.println(csvFile.createFile("history.txt") ? "Clear History Successfully!" : "Cannot clear history.");
	}

	@Override
	public void writeHistory(String history) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ssZ");
		String date = simpleDateFormat.format(new Date());

		csvFile.writeLineToFile("history.txt",
				history + " - DateTime: " + date + " | Admin: " + LibraryManage.loginAdmin.getName());
	}

}
