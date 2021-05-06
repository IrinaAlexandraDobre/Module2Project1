import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GuestsList guestList = new GuestsList();
		guestList.help();

		Commands newCommands = new Commands();
		System.out.println("New command: ");
		String newCommand = sc.nextLine();
		newCommands.selectCommand(newCommand, guestList);

		while (!newCommand.equals("quit")) {
			newCommand = sc.nextLine();
			newCommands.selectCommand(newCommand, guestList);
		}

		sc.close();

	}

}
