
import java.util.*;
import java.util.regex.*;

public class Address_Book {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Sreenath", 9908447685L, "msreenathreddy2001@gmail.com"));

        while (true) {
            System.out.println("Choose any one option");
            System.out.println("1. Show contact by name");
            System.out.println("2. Show contact by number");
            System.out.println("3. Edit contact by name");
            System.out.println("4. Edit contact by number");
            System.out.println("5. Add new contact");
            System.out.println("6. Delete contact");
            System.out.println("7. Show all contacts");
            System.out.println("8. Exit");

            int choose = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choose) {
                case 1:
                    System.out.println("Enter the name to search:");
                    String searchName = sc.nextLine();
                    showContactByName(contacts, searchName);
                    break;
                case 2:
                    System.out.println("Enter the number to search:");
                    try {
                        long searchNumber = sc.nextLong();
                        showContactByNumber(contacts, searchNumber);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid phone number.");
                        sc.next(); // Clear invalid input
                    }
                    break;
                case 3:
                    System.out.println("Enter the name to edit:");
                    String editName = sc.nextLine();
                    editContactByName(sc, contacts, editName);
                    break;
                case 4:
                    System.out.println("Enter the number to edit:");
                    try {
                        long editNumber = sc.nextLong();
                        sc.nextLine(); // Consume newline
                        editContactByNumber(sc, contacts, editNumber);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid phone number.");
                        sc.next(); // Clear invalid input
                    }
                    break;
                case 5:
                    addNewContact(sc, contacts);
                    break;
                case 6:
                    System.out.println("Enter the name to delete:");
                    String deleteName = sc.nextLine();
                    deleteContactByName(contacts, deleteName);
                    break;
                case 7:
                    showAllContacts(contacts);
                    break;
                case 8:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void showContactByName(List<Contact> contacts, String searchName) {
        Pattern pattern = Pattern.compile(searchName, Pattern.CASE_INSENSITIVE);
        boolean found = false;

        for (Contact contact : contacts) 
        {
            Matcher matcher = pattern.matcher(contact.name);
            if (matcher.find()) 
            {
                if (!found) {
                    System.out.println("Contacts found:");
                }
                System.out.println(contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No contact found with the name containing: " + searchName);
        }
    }

    public static void showContactByNumber(List<Contact> contacts, long searchNumber) {
        boolean found = false;

        for (Contact contact : contacts) {
            if (contact.phoneNumber == searchNumber) {
                if (!found) {
                    System.out.println("Contacts found:");
                }
                System.out.println(contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No contact found with the number: " + searchNumber);
        }
    }

    public static void editContactByName(Scanner sc, List<Contact> contacts, String searchName) {
        Pattern pattern = Pattern.compile(searchName, Pattern.CASE_INSENSITIVE);
        boolean found = false;

        for (Contact contact : contacts) {
            Matcher matcher = pattern.matcher(contact.name);
            if (matcher.find()) {
                System.out.println("Editing contact: " + contact);
                System.out.println("Enter new name:");
                contact.name = sc.nextLine();
                System.out.println("Enter new phone number:");
                try {
                    contact.phoneNumber = sc.nextLong();
                    sc.nextLine(); // Consume newline
                    System.out.println("Enter new email ID:");
                    contact.emailId = sc.nextLine();
                    found = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid phone number.");
                    sc.next(); // Clear invalid input
                }
                break;
            }
        }

        if (!found) {
            System.out.println("No contact found with the name containing: " + searchName);
        }
    }

    public static void editContactByNumber(Scanner sc, List<Contact> contacts, long searchNumber) {
        boolean found = false;

        for (Contact contact : contacts) {
            if (contact.phoneNumber == searchNumber) {
                System.out.println("Editing contact: " + contact);
                System.out.println("Enter new name:");
                contact.name = sc.nextLine();
                System.out.println("Enter new phone number:");
                try {
                    contact.phoneNumber = sc.nextLong();
                    sc.nextLine(); // Consume newline
                    System.out.println("Enter new email ID:");
                    contact.emailId = sc.nextLine();
                    found = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid phone number.");
                    sc.next(); // Clear invalid input
                }
                break;
            }
        }

        if (!found) {
            System.out.println("No contact found with the number: " + searchNumber);
        }
    }

    public static void addNewContact(Scanner sc, List<Contact> contacts) {
        System.out.println("Enter name:");
        String name = sc.nextLine();
        System.out.println("Enter phone number:");
        try {
            long phoneNumber = sc.nextLong();
            sc.nextLine(); // Consume newline
            System.out.println("Enter email ID:");
            String emailId = sc.nextLine();

            // Check if a contact with the same name, phone number, or email ID already exists
            for (Contact contact : contacts) {
                if (contact.name.equalsIgnoreCase(name) || contact.phoneNumber == phoneNumber || contact.emailId.equalsIgnoreCase(emailId)) {
                    System.out.println("Contact already exists with the same name, phone number, or email ID.");
                    return;
                }
            }

            // Add the new contact if no duplicate is found
            contacts.add(new Contact(name, phoneNumber, emailId));
            System.out.println("Contact added.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid phone number.");
            sc.next(); // Clear invalid input
        }
    }

    public static void deleteContactByName(List<Contact> contacts, String searchName) {
        Pattern pattern = Pattern.compile(searchName, Pattern.CASE_INSENSITIVE);
        Iterator<Contact> iterator = contacts.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            Matcher matcher = pattern.matcher(contact.name);
            if (matcher.find()) {
                iterator.remove();
                found = true;
                System.out.println("Contact deleted: " + contact);
                break;
            }
        }

        if (!found) {
            System.out.println("No contact found with the name containing: " + searchName);
        }
    }

    public static void showAllContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            System.out.println("All contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}

class Contact {
    String name;
    long phoneNumber;
    String emailId;

    Contact(String name, long phoneNumber, String emailId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email ID: " + emailId;
    }
}
