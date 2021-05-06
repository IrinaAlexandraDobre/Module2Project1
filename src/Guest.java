import java.util.Scanner;

public class Guest {

	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;

	public Guest() {

	}

	public Guest(String lastName, String firstName, String email, String phoneNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		setEmail(email);
		setPhoneNumber(phoneNumber);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	Scanner sc = new Scanner(System.in);

	private int counterAt(String email) {
		int countOfAt = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				countOfAt++;
			}
		}
		return countOfAt;
	}

	private boolean lastCheckEmail(boolean userNameValid, boolean domainNameValid) {
		boolean result = false;
		if (domainNameValid && userNameValid) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	private boolean checkEmail(String email) {
		int counterUser = 0;
		int counterDomain = 0;
		boolean userNameValid = false;
		boolean domainNameValid = false;
		if (email.length() >= 3) {
			counterAt(email);
			if (counterAt(email) == 1) {
				for (int i = 0; i < email.length(); i++) {
					if (email.charAt(i) == '@') {
						break;
					}
					counterUser++;
				}
				if (counterUser >= 2) {
					String userName = email.substring(0, email.indexOf('@'));
					if ((userName.charAt(0) != '.') && (userName.charAt(userName.length() - 1) != '.')) {
						userNameValid = true;
					}
				} else {
					if (email.charAt(0) != '.') {
						userNameValid = true;
					}
				}
				for (int i = email.length() - 1; i >= 0; i--) {
					if (email.charAt(i) == '@') {
						break;
					}
					counterDomain++;
				}
				if (counterDomain >= 2) {
					String domainName = email.substring(email.indexOf('@') + 1, email.length());
					for (int i = 0; i < domainName.length(); i++) {
						if ((domainName.charAt(i) == '.') || (domainName.charAt(i) == '-')) {
							domainNameValid = true;
							break;
						}
					}
				} else {
					if (email.charAt(email.length() - 1) == '.' || email.charAt(email.length() - 1) == '-') {
						domainNameValid = true;
					}
				}
			}
		}
		return lastCheckEmail(userNameValid, domainNameValid);
	}

	public void setEmail(String email) {
		if (checkEmail(email)) {
			this.email = email;
		} else {
			while (!checkEmail(email)) {
				System.out.println("Error - enter email again");
				email = sc.nextLine();
				if (checkEmail(email)) {
					this.email = email;
					System.out.println("email accepted");
				}
			}
		}
	}

	public String getEmail() {
		return this.email;
	}

	private boolean checkPhoneNumber(String phoneNumber) {
		boolean result = true;
		if (phoneNumber.length() == 10) {

			StringBuilder phoneNumber1 = new StringBuilder(phoneNumber);
			for (int i = 0; i < phoneNumber1.length(); i++) {
				char c = phoneNumber1.charAt(i);
				if (!Character.isDigit(c)) {
					result = false;
					break;
				}
			}
		} else {
			result = false;
		}
		return result;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (checkPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			while (!checkPhoneNumber(phoneNumber)) {
				System.out.println("Error - enter phone number again ");
				phoneNumber = sc.nextLine();
				if (checkPhoneNumber(phoneNumber)) {
					this.phoneNumber = phoneNumber;
					System.out.println("phone number accepted");
				}
			}
		}
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	@Override
	public String toString() {
		return "Name: " + this.firstName + " " + this.lastName + ", Email: " + this.email + ", Phone number: "
				+ this.phoneNumber;
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

		Guest guestObj = (Guest) obj;
		if (this.firstName.equals(guestObj.firstName) && this.lastName.equals(guestObj.lastName)
				&& this.email.equals(guestObj.email) && this.phoneNumber.equals(guestObj.phoneNumber)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + firstName.hashCode();
		result = prime * result + lastName.hashCode();
		result = prime * result + email.hashCode();
		result = prime * result + phoneNumber.hashCode();

		return result;
	}

}
