package com.librarymanagement.csvfile;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.librarymanagement.LibraryManage;

public class csvFile {
	public static List<String[]> csvToList(String filename, boolean skipTitle) {
		List<String[]> dataLines = new ArrayList<String[]>();

		try {
			File csvFile = new File("resources/" + filename);

			Scanner myReader = new Scanner(csvFile);
			while (myReader.hasNextLine()) {
				if (skipTitle) {
					myReader.nextLine();
					skipTitle = false;
					continue;
				}

				dataLines.add(myReader.nextLine().split(","));
			}

			myReader.close();
		} catch (Exception e) {
			System.out.println("Cannot read the csv file.");
		}

		return dataLines;
	}

	public static boolean saveListToCsv() {
		System.out.println("Saving.....");

		try {
			File adminFile = new File("resources/admin.csv");
			File adminTmpFile = new File("resources/tmp/adminTmp.csv");

			File memberFile = new File("resources/member.csv");
			File memberTmpFile = new File("resources/tmp/memberTmp.csv");

			File bookFile = new File("resources/book.csv");
			File bookTmpFile = new File("resources/tmp/bookTmp.csv");

			PrintWriter adminPW = new PrintWriter(adminTmpFile);
			PrintWriter memberPW = new PrintWriter(memberTmpFile);
			PrintWriter bookPW = new PrintWriter(bookTmpFile);

			adminPW.println("AdminID,AdminName,AdminPassword");
			memberPW.println("MemberID,MemberName,BorrowedBooks");
			bookPW.println("BookID,BookTitle,LendFlag");

			if (!LibraryManage.admins.isEmpty())
				LibraryManage.admins.stream().map(a -> a.toCsvString()).forEach(adminPW::println);

			if (!LibraryManage.members.isEmpty())
				LibraryManage.members.stream().map(m -> m.toCsvString()).forEach(memberPW::println);

			if (!LibraryManage.books.isEmpty())
				LibraryManage.books.stream().map(b -> b.toCsvString()).forEach(bookPW::println);

			adminPW.close();
			memberPW.close();
			bookPW.close();

			adminFile.delete();
			adminTmpFile.renameTo(adminFile);

			memberFile.delete();
			memberTmpFile.renameTo(memberFile);

			bookFile.delete();
			bookTmpFile.renameTo(bookFile);

			if (adminFile.exists() && memberFile.exists() && bookFile.exists()) {
				System.out.println("Saved Successfully!");
				return true;
			}
		} catch (Exception e) {
			System.out.println("File does not exist or cannot create.");
		}

		System.out.println("Cannot save data.");
		return false;
	}

	public static void readFile(String filename) {
		try {
			File csvFile = new File("resources/" + filename);

			Scanner myReader = new Scanner(csvFile);
			while (myReader.hasNextLine()) {
				System.out.println(myReader.nextLine());
			}

			myReader.close();
		} catch (Exception e) {
			System.out.println("Cannot read the file.");
		}
	}

	public static boolean writeLineToFile(String filename, String msg) {
		try {
			File file = new File("resources/" + filename);
			//PrintWriter writer = new PrintWriter(file);
			FileWriter writer = new FileWriter(file, true);
			writer.append(msg + System.getProperty("line.separator"));

			writer.close();
			return true;
		} catch (Exception e) {
			System.out.println("Cannot write to the file.");
		}

		return false;
	}

	public static boolean deleteFile(String filename) {
		try {
			File file = new File("resources/" + filename);
			return file.delete();
		} catch (Exception e) {
			System.out.println("Cannot delete the file.");
		}
		return false;
	}

	public static boolean createFile(String filename) {
		try {
			File file = new File("resources/" + filename);
			return file.createNewFile();
		} catch (Exception e) {
			System.out.println("Cannot create the file.");
		}
		return false;
	}

}
