package com.librarymanagement.person.member;

import java.util.ArrayList;
import java.util.stream.IntStream;

import com.librarymanagement.LibraryManage;
import com.librarymanagement.utility.Utility;

public class MemberServiceImpl implements MemberService {

	@Override
	public void createMember() {
		Member lastMember;
		int memberID;

		System.out.println("\n--Create Member--");

		String memberName = Utility.getUserInput("new member name");

		if (!LibraryManage.members.isEmpty()) {
			lastMember = LibraryManage.members.get(LibraryManage.members.size() - 1);
			memberID = lastMember.getId() + 1;
		} else {
			memberID = 1;
		}

		LibraryManage.members.add(new Member(memberID, memberName, new ArrayList<Integer>()));
		System.out.println("Member Created Successfully!");
	}

	@Override
	public void editMember() {
		System.out.println("\n--Edit Member--");

		int memberIndex = this.getIndexByMemberID();

		String newMemberName = Utility.getUserInput("new member name");

		LibraryManage.members.get(memberIndex).setName(newMemberName);
		System.out.println("Member Edited Successfully!");
	}

	@Override
	public void deleteMember() {
		System.out.println("\n--Delete Member--");

		do {
			int memberIndex = this.getIndexByMemberID();
			Member deleteMember = LibraryManage.members.get(memberIndex);

			if (deleteMember.getBorrowedBooks().isEmpty()) {
				LibraryManage.members.remove(memberIndex);
				System.out.println("Member Deleted Successfully!");
				break;
			}

			System.out.println("Sorry! Cannot delete this member. (MemberID: " + deleteMember.getId() + ")");
		} while (true);

	}

	@Override
	public void showAllMember() {
		System.out.println("\n--Show Member List--");
		LibraryManage.members.forEach(System.out::println);
	}

	@Override
	public int getIndexByMemberID() {
		int memberID;
		int memberIndex;

		do {
			try {
				memberID = Integer.parseInt(Utility.getUserInput("member id"));

				memberIndex = this.findIndex(memberID);

				if (memberIndex >= 0) {
					return memberIndex;
				} else {
					System.out.println("Please enter the correct member id.");
					continue;
				}

			} catch (Exception e) {
				System.out.println("Please enter the valid input");
			}
		} while (true);

	}

	@Override
	public int findIndex(int memberID) {
		return IntStream.range(0, LibraryManage.members.size())
				.filter(i -> LibraryManage.members.get(i).getId() == memberID).findFirst().orElse(-1);
	}

}
