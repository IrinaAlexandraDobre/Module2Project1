import java.util.Scanner;

public class Commands {
	Scanner sc = new Scanner(System.in);

	public void selectCommand(String newCommand, GuestsList guestList) {
		switch (newCommand) {
		case "help":
			guestList.help();
			break;
		case "add":
			System.out.println("Enter full name/phone number/email to search the person: ");
			String info = sc.nextLine();
			guestList.add(info);
			break;
		case "check":
			System.out.println("Enter full name/phone number/email to search the person: ");
			String info1 = sc.nextLine();
			System.out.println(guestList.check(info1));
			break;
		case "remove":
			System.out.println("Enter full name/phone number/email to search the person: ");
			String info2 = sc.nextLine();
			guestList.remove(info2);
			break;
		case "update":
			System.out.println("Enter full name/phone number/email to search the person: ");
			String info3 = sc.nextLine();
			System.out.println(
					"Choose a number depending on what you want to update: 1-First name; 2-Last name; 3-Email; 4-Phone number: ");
			int ans = sc.nextInt();
			System.out.println("Enter update: ");
			sc.nextLine();
			String input = sc.nextLine();
			guestList.update(info3, ans, input);
			break;
		case "guests":
			guestList.allParticipatingGuests();
			break;
		case "waitlist":
			guestList.allWaitingGuests();
			break;
		case "available":
			System.out.println("Available seats: " + guestList.availableSeatsNo());
			break;
		case "guests_no":
			System.out.println("Participants List Number: " + guestList.getParticipantsListNo());
			break;
		case "waitlist_no":
			System.out.println("Waiting Persons Number: " + guestList.getWaitingPersonsNo());
			break;
		case "subscribe_no":
			System.out.println("All Participants Number: " + guestList.getAllParticipantsNo());
			break;
		case "search":
			System.out.println("Search: ");
			String search = sc.nextLine();
			guestList.search(search);
			break;
		case "quit":
			System.out.println("App is closing...");
			break;
		default:
			System.out.println("This command doesn't exist");
			break;
		}
	}

}
