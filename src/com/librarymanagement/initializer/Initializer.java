package com.librarymanagement.initializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.librarymanagement.LibraryManage;
import com.librarymanagement.book.Book;
import com.librarymanagement.csvfile.csvFile;
import com.librarymanagement.person.admin.Admin;
import com.librarymanagement.person.member.Member;

public class Initializer {
	public static void initialize() {
		initializeAdmin();
		initializeMember();
		initializeBook();
	}

	public static void initializeAdmin() {
		List<String[]> adminList = csvFile.csvToList("admin.csv", true);

		for (String[] admin : adminList) {
			LibraryManage.admins.add(new Admin(Integer.parseInt(admin[0]), admin[1], admin[2]));
		}
	}

	public static void initializeMember() {
		List<String[]> memberList = csvFile.csvToList("member.csv", true);
		ArrayList<Integer> borrowedBooks;

		for (String[] member : memberList) {
			if (member.length > 2) {
				borrowedBooks = new ArrayList<Integer>(Arrays.asList(member[2].split("-")).stream()
						.map(Integer::valueOf).collect(Collectors.toList()));
			} else {
				borrowedBooks = new ArrayList<Integer>();
			}

			LibraryManage.members.add(new Member(Integer.parseInt(member[0]), member[1], borrowedBooks));
		}
	}

	public static void initializeBook() {
		List<String[]> bookList = csvFile.csvToList("book.csv", true);

		for (String[] book : bookList) {
			LibraryManage.books.add(new Book(Integer.parseInt(book[0]), book[1], Integer.parseInt(book[2])));
		}
	}

}
