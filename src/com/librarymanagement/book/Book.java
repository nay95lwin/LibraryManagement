package com.librarymanagement.book;

public class Book {
	private int bookID;
	private String bookTitle;
	private int lendFlag;

	public Book(int bookID, String bookTitle, int lendFlag) {
		super();
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.lendFlag = lendFlag;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getLendFlag() {
		return lendFlag;
	}

	public void setLendFlag(int lendFlag) {
		this.lendFlag = lendFlag;
	}

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", bookTitle=" + bookTitle + ", lendFlag=" + lendFlag + "]";
	}

	public String toCsvString() {
		return bookID + "," + bookTitle + "," + lendFlag;
	}
}
