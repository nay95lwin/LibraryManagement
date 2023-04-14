package com.librarymanagement.book;

import java.util.stream.IntStream;

import com.librarymanagement.LibraryManage;
import com.librarymanagement.history.HistoryServiceImpl;
import com.librarymanagement.person.member.Member;
import com.librarymanagement.person.member.MemberServiceImpl;
import com.librarymanagement.utility.Utility;

public class BookServiceImpl implements BookService {

	@Override
	public void addBook() {
		Book lastBook;
		int bookID;

		System.out.println("\n--Add Book--");

		String bookTitle = Utility.getUserInput("new book title");

		if (!LibraryManage.books.isEmpty()) {
			lastBook = LibraryManage.books.get(LibraryManage.books.size() - 1);
			bookID = lastBook.getBookID() + 1;
		} else {
			bookID = 1;
		}

		LibraryManage.books.add(new Book(bookID, bookTitle, 0));
		System.out.println("Book Added Successfully!");
	}

	@Override
	public void editBook() {
		System.out.println("\n--Edit Book--");

		int bookIndex = this.getIndexByBookID();

		String newBookTitle = Utility.getUserInput("new book title");

		LibraryManage.books.get(bookIndex).setBookTitle(newBookTitle);
		System.out.println("Book Edited Successfully!");
	}

	@Override
	public void deleteBook() {
		System.out.println("\n--Delete Book--");

		do {
			int bookIndex = this.getIndexByBookID();
			Book deleteBook = LibraryManage.books.get(bookIndex);

			if (deleteBook.getLendFlag() == 0) {
				LibraryManage.books.remove(bookIndex);
				System.out.println("Book Deleted Successfully!");
				break;
			}

			System.out.println("Sorry! Cannot delete this book. (BookID: " + deleteBook.getBookID() + ")");
		} while (true);

	}

	@Override
	public void showAllBook() {
		System.out.println("\n--Show Book List--");
		LibraryManage.books.forEach(System.out::println);
	}

	@Override
	public void lendBook() {
		System.out.println("\n--Lend Book--");

		HistoryServiceImpl historySerImpl = new HistoryServiceImpl();
		MemberServiceImpl memberSerImpl = new MemberServiceImpl();
		int memberIndex = memberSerImpl.getIndexByMemberID();

		do {
			int bookIndex = this.getIndexByBookID();
			Book lendBook = LibraryManage.books.get(bookIndex);

			if (lendBook.getLendFlag() == 0) {
				LibraryManage.books.get(bookIndex).setLendFlag(1);
				LibraryManage.members.get(memberIndex).setBorrowedBooks(lendBook.getBookID());
				historySerImpl.writeHistory("Lend -> MemberID: " + LibraryManage.members.get(memberIndex).getId()
						+ ", BookID: " + lendBook.getBookID());
				System.out.println("Book Lent Successfully!");
				break;
			}

			System.out.println("Sorry! This book is already lent. (BookID: " + lendBook.getBookID() + ")");
		} while (true);
	}

	@Override
	public void returnBook() {
		System.out.println("\n--Return Book--");

		HistoryServiceImpl historySerImpl = new HistoryServiceImpl();
		MemberServiceImpl memberSerImpl = new MemberServiceImpl();
		int memberIndex = memberSerImpl.getIndexByMemberID();
		Member returnMember = LibraryManage.members.get(memberIndex);

		do {
			int bookIndex = this.getIndexByBookID();
			Book returnBook = LibraryManage.books.get(bookIndex);

			if (returnBook.getLendFlag() == 1
					&& LibraryManage.members.get(memberIndex).removeBorrowedBooks(returnBook.getBookID())) {
				LibraryManage.books.get(bookIndex).setLendFlag(0);
				historySerImpl.writeHistory(
						"Return -> MemberID: " + returnMember.getId() + ", BookID: " + returnBook.getBookID());
				System.out.println("Book Returned Successfully!");
				break;
			}

			System.out.println("Sorry! This book is already returned. (BookID: " + returnBook.getBookID() + ")");
		} while (true);
	}

	@Override
	public int getIndexByBookID() {
		int bookID;
		int bookIndex;

		do {
			try {
				bookID = Integer.parseInt(Utility.getUserInput("book id"));

				bookIndex = this.findIndex(bookID);

				if (bookIndex >= 0) {
					return bookIndex;
				} else {
					System.out.println("Please enter the correct book id.");
					continue;
				}

			} catch (Exception e) {
				System.out.println("Please enter the valid input");
			}
		} while (true);

	}

	@Override
	public int findIndex(int bookID) {
		return IntStream.range(0, LibraryManage.books.size())
				.filter(i -> LibraryManage.books.get(i).getBookID() == bookID).findFirst().orElse(-1);
	}

}
