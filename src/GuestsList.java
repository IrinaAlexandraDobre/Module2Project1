import java.util.ArrayList;
import java.util.Scanner;

public class GuestsList {

	private int availableSeats;
	private int maxAvailableSeats;
	private ArrayList<Guest> participantsList;
	private ArrayList<Guest> waitingListInvites;

	public GuestsList() {
		this.maxAvailableSeats = 3;
		this.participantsList = new ArrayList<>(this.maxAvailableSeats);
		for (int i = 0; i < this.participantsList.size(); i++) {
			this.participantsList.add(0, this.participantsList.get(i));
		}
		this.waitingListInvites = new ArrayList<>(this.maxAvailableSeats);
		for (int i = 0; i < this.waitingListInvites.size(); i++) {
			this.waitingListInvites.add(0, this.waitingListInvites.get(i));
		}
	}

	Scanner sc = new Scanner(System.in);

	public Guest newGuest() {
		System.out.println("Enter last name: ");
		String lastName = sc.nextLine();
		System.out.println("Enter first name: ");
		String firstName = sc.nextLine();
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		System.out.println("Enter phone number: ");
		String phoneNumber = sc.nextLine();
		Guest newGuest1 = new Guest(lastName, firstName, email, phoneNumber);
		return newGuest1;
	}

	public int add(String search) {
		this.availableSeats = this.maxAvailableSeats - participantsList.size();
		int result = 0;
		if (check(search)) {
			result = -1;
			System.out.println("This person is already on the list.");
		} else if (!check(search)) {
			Guest addGuest = newGuest();
			if (this.availableSeats > 0) {
				this.participantsList.add(addGuest);
				System.out.println("[" + addGuest + "]"
						+ "Congratulations! Your place at the event is confirmed. We are waiting for you!");
				result = 0;
			} else if (this.availableSeats == 0) {
				this.waitingListInvites.add(addGuest);
				System.out.println("[" + addGuest + "]"
						+ "You have successfully signed up for the waiting list and received the order number: "
						+ getWaitingPersonsNo() + ". We will notify you if a seat becomes available.");
				result = getWaitingPersonsNo();
			}
		}
		return result;
	}

	private Guest searchGuest(String search) {
		Guest foundGuest = new Guest();
		for (Guest guest : this.participantsList) {
			if (search.equalsIgnoreCase(guest.getFullName()) || search.equalsIgnoreCase(guest.getEmail())
					|| search.equalsIgnoreCase(guest.getPhoneNumber())) {
				foundGuest = guest;
				break;
			}
		}
		for (Guest guest : this.waitingListInvites) {
			if (search.equalsIgnoreCase(guest.getFullName()) || search.equalsIgnoreCase(guest.getEmail())
					|| search.equalsIgnoreCase(guest.getPhoneNumber())) {
				foundGuest = guest;
				break;
			}
		}
		return foundGuest;

	}

	public boolean check(String search) {
		if (searchGuest(search).getFullName() == null || searchGuest(search).getEmail() == null
				|| searchGuest(search).getPhoneNumber() == null) {

			return false;
		} else {
			return true;
		}
	}

	public void update(String search, int number, String input) {
		if (check(search)) {
			switch (number) {
			case 1:
				searchGuest(search).setFirstName(input);
				System.out.println("First name changed.");
				break;
			case 2:
				searchGuest(search).setLastName(input);
				System.out.println("Last name changed.");
				break;
			case 3:
				searchGuest(search).setEmail(input);
				System.out.println("Email changed.");
				break;
			case 4:
				searchGuest(search).setPhoneNumber(input);
				System.out.println("Phone number changed.");
				break;
			default:
				System.out.println("Wrong choice.");
				break;

			}
		} else if (!check(search)) {
			System.out.println("Error.");
		}
	}

	public boolean remove(String search) {
		boolean result = false;
		if (check(search)) {
			for (Guest guest : this.participantsList) {
				if (search.equalsIgnoreCase(guest.getFullName()) || search.equalsIgnoreCase(guest.getEmail())
						|| search.equalsIgnoreCase(guest.getPhoneNumber())) {
					this.participantsList.remove(guest);
					System.out.print("The person was deleted.");
					if (this.waitingListInvites.size() > 0) {
						this.participantsList.add(this.waitingListInvites.get(0));
						this.waitingListInvites.remove(this.waitingListInvites.get(0));
					}
					this.availableSeats = this.maxAvailableSeats - participantsList.size();
					result = true;
					break;
				}
			}
			if (!result) {
				this.waitingListInvites.remove(searchGuest(search));
				System.out.print("The person was deleted.");
				result = true;
			}
		} else {
			System.out.print("Error");
			result = false;
		}
		return result;
	}

	private int searchFields(String value, ArrayList<Guest> searchList) {
		int counter = 0;
		for (int i = 0; i < searchList.size(); i++) {
			if (searchList.get(i).getFullName().toLowerCase().contains(value)) {
				System.out.println("\tName: " + searchList.get(i).getFullName() + ", e-mail: "
						+ searchList.get(i).getEmail() + ", phone number: " + searchList.get(i).getPhoneNumber());
				counter++;
			} else if (searchList.get(i).getEmail().toLowerCase().contains(value)) {
				System.out.println("\tName: " + searchList.get(i).getFullName() + ", e-mail: "
						+ searchList.get(i).getEmail() + ", phone number: " + searchList.get(i).getPhoneNumber());
				counter++;
			} else if (searchList.get(i).getPhoneNumber().contains(value)) {
				System.out.println("\tName: " + searchList.get(i).getFullName() + ", e-mail: "
						+ searchList.get(i).getEmail() + ", phone number: " + searchList.get(i).getPhoneNumber());
				counter++;
			}
		}
		return counter;
	}

	public void search(String value) {
		value = value.toLowerCase();
		int counterParticipantsList = 0;
		int counterWaitingListInvites = 0;
		System.out.println("Results:");
		counterParticipantsList = searchFields(value, this.participantsList);
		counterWaitingListInvites = searchFields(value, this.waitingListInvites);
		if (counterWaitingListInvites == 0 && counterParticipantsList == 0) {
			System.out.println("\tThis person doesn't exist on the lists. Please try again");
		}
	}



	public int getWaitingPersonsNo() {
		return this.waitingListInvites.size();
	}

	public int getParticipantsListNo() {
		return this.participantsList.size();
	}

	public int availableSeatsNo() {
		this.availableSeats = this.maxAvailableSeats - participantsList.size();
		return this.availableSeats;
	}

	public int getAllParticipantsNo() {
		return this.participantsList.size() + getWaitingPersonsNo();
	}

	public void allParticipatingGuests() {
		System.out.println("Participants:");
		for (int i = 0; i < this.participantsList.size(); i++) {
			System.out.println(this.participantsList.get(i).toString());
		}
	}

	public void allWaitingGuests() {
		System.out.println("Waiting persons:");
		for (int i = 0; i < this.waitingListInvites.size(); i++) {
			System.out.println(this.waitingListInvites.get(i).toString());
		}
	}

	public void help() {
		System.out.println("help         - Displays command list\n" + "add          - Add a new person (registration)\n"
				+ "check        - Check if a person is registered for the event\n"
				+ "remove       - Delete an existing person from the list\n"
				+ "update       - Updates a person's details\n"
				+ "guests       - List of people participating at the event\n"
				+ "waitlist     - People on the waiting list\n" + "available    - Numarul de locuri libere\n"
				+ "guests_no    - Number of people participating in the event\n"
				+ "waitlist_no  - Number of people on the waiting list\n"
				+ "subscribe_no - Total number of people registered\n"
				+ "search       - Search all invitations according to the string entered\n"
				+ "quit         - Close app");
	}

	@Override
	public String toString() {
		return "The list has " + this.availableSeats + " available seats\n" + "There are " + getParticipantsListNo()
				+ " participants and " + getWaitingPersonsNo() + " waiting persons.";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		GuestsList other = (GuestsList) obj;
		if ((this.availableSeats == other.availableSeats) && (this.maxAvailableSeats == other.maxAvailableSeats)
				&& (this.participantsList.equals(other.participantsList))
				&& (this.waitingListInvites.equals(other.waitingListInvites))) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + availableSeats;
		result = prime * result + maxAvailableSeats;
		result = prime * result + participantsList.hashCode();
		result = prime * result + waitingListInvites.hashCode();

		return result;
	}

}
