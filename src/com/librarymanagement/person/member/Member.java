package com.librarymanagement.person.member;

import java.util.List;
import java.util.stream.Collectors;

import com.librarymanagement.person.Person;

public class Member extends Person {
	private List<Integer> borrowedBooks;

	public Member(int id, String name, List<Integer> borrowedBooks) {
		super(id, name);
		this.borrowedBooks = borrowedBooks;
	}

	public List<Integer> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(int borrowedBookID) {
		this.borrowedBooks.add(borrowedBookID);
	}

	public boolean removeBorrowedBooks(int borrowedBookID) {
		try {
			return this.borrowedBooks.removeIf(b -> b == borrowedBookID);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Member [MemberId=" + id + ", MemberName=" + name + ", BorrowedBooks=" + borrowedBooks.toString() + "]";
	}

	public String toCsvString() {
		return id + "," + name + "," + borrowedBooks.stream().map(String::valueOf).collect(Collectors.joining("-"));
	}
}
