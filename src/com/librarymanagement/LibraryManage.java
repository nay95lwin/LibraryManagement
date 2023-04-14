package com.librarymanagement;

import java.util.ArrayList;
import java.util.List;

import com.librarymanagement.book.Book;
import com.librarymanagement.initializer.Initializer;
import com.librarymanagement.library.LibraryServiceImpl;
import com.librarymanagement.person.admin.Admin;
import com.librarymanagement.person.member.Member;

public class LibraryManage {
	public static List<Admin> admins = new ArrayList<Admin>();
	public static List<Member> members = new ArrayList<Member>();
	public static List<Book> books = new ArrayList<Book>();

	public static Admin loginAdmin;

	public static void main(String[] args) {
		// initialize
		Initializer.initialize();

		// start menu
		LibraryServiceImpl librarySerImpl = new LibraryServiceImpl();
		librarySerImpl.startMenu();

	}

}
