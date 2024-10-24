import java.util.ArrayList;
import java.util.Scanner;

public class  Gprog{

    class Account {
        String firstName;
        String surname;
        String username;
        float balance;
        String password;
    }

    abstract class User {
        abstract void displayMenu();
    }

    class Admin extends User {
        private Scanner scanner = new Scanner(System.in);

        @Override
        void displayMenu() {
            int choice;
            do {
                clearScreen();
                printAdminMenu();
                System.out.print("Enter your choice : ");
                try {
                    choice = scanner.nextInt();
                    handleAdminChoice(choice);
                } catch (Exception e) {
                    scanner.nextLine(); 
                    System.out.println("Invalid input. Please enter a number.");
                    choice = 0;
                }
            } while (choice != 3);
        }

        private void clearScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        private void printAdminMenu() {
            System.out.println("\t\t\t ===================================================================== ");
            System.out.println("\t\t\t                            WELCOME ADMIN!                             ");
            System.out.println("\t\t\t ===================================================================== ");
            System.out.println("\t\t =MENU= ");
            System.out.println("\t |1| Create Account     ");
            System.out.println("\t |2| Show All Accounts  ");
            System.out.println("\t |3| Exit               ");
        }

        private void handleAdminChoice(int choice) {
            switch (choice) {
                case 1:
                    BankingSystem.create();
                    break;
                case 2:
                    BankingSystem.showAllAccounts();
                    break;
                case 3:
                    System.out.println("GOODBYE! ADMIN.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }

    class UserF extends User {
        private Scanner scanner = new Scanner(System.in);
        private Account currentUser;

        @Override
        void displayMenu() {
            int choice;
            do {
                clearScreen();
                printUserMenu();
                System.out.print("Enter your choice : ");
                try {
                    choice = scanner.nextInt();
                    handleUserChoice(choice);
                } catch (Exception e) {
                    scanner.nextLine(); 
                    System.out.println("Invalid input. Please enter a number.");
                    choice = 0;
                }
            } while (choice != 2);
        }

        private void clearScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        private void printUserMenu() {
            System.out.println("\t\t\t ===================================================================== ");
            System.out.println("\t\t\t                       WELCOME TO OUR BANK SYSTEM                      ");
            System.out.println("\t\t\t ===================================================================== ");
            System.out.println("\t\t MENU ");
            System.out.println("\t |1| Login           ");
            System.out.println("\t |2| Exit            ");
        }

        private void handleUserChoice(int choice) {
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("BYE!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }

        private void login() {
            System.out.print("\t Enter your username : ");
            String inputUsername = scanner.next();
            System.out.print("\t Enter your password : ");
            String inputPassword = scanner.next();

            boolean found = false;

            for (Account account : BankingSystem.accounts) {
                if (account.username.equals(inputUsername) && account.password.equals(inputPassword)) {
                    System.out.println("Login successful");
                    currentUser = account;
                    accountMenu();
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        private void accountMenu() {
            int option;
            do {
                printAccountMenu();
                System.out.print("Enter your choice : ");
                try {
                    option = scanner.nextInt();
                    handleAccountOption(option);
                } catch (Exception e) {
                    scanner.nextLine(); 
                    System.out.println("Invalid input. Please enter a number.");
                    option = 0;
                }
            } while (option != 4);
        }

        private void printAccountMenu() {
            System.out.println("\t\t\t =============================================================== ");
            System.out.println("\t\t\t                       ACCOUNT MANAGEMENT                        ");
            System.out.println("\t\t\t =============================================================== ");
            System.out.println("\t\t MENU ");
            System.out.println("\t |1| Deposit           ");
            System.out.println("\t |2| Withdraw          ");
            System.out.println("\t |3| Check Balance     ");
            System.out.println("\t |4| Exit              ");
        }

        private void handleAccountOption(int option) {
            switch (option) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Bye! See you again.");
                    pauseForUser();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }

        private void deposit() {
            System.out.print("\t Enter the amount : ");
            try {
                float amount = scanner.nextFloat();
                if (amount > 0) {
                    currentUser.balance += amount;
                    System.out.println(" Deposit successful! New balance: " + currentUser.balance);
                    BankingSystem.showAllAccounts();
                } else {
                    System.out.println("Invalid amount.");
                }
            } catch (Exception e) {
                scanner.nextLine(); // Clear the invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        private void withdraw() {
            System.out.print("\t Enter the amount to withdraw: ");
            try {
                float amount = scanner.nextFloat();
                if (amount > 0) {
                    if (amount <= currentUser.balance) {
                        currentUser.balance -= amount;
                        System.out.println("Withdrawal successful! New balance: " + currentUser.balance);
                        BankingSystem.showAllAccounts();
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                } else {
                    System.out.println("Invalid amount.");
                }
            } catch (Exception e) {
                scanner.nextLine(); // Clear the invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        private void checkBalance() {
            System.out.println("Balance: " + currentUser.balance);
        }

        private void pauseForUser() {
            System.out.println("Press Enter to continue...");
            scanner.nextLine();  // Consume newline
            scanner.nextLine();  // Wait for Enter
        }
    }

    public static class BankingSystem {
        static ArrayList<Account> accounts = new ArrayList<>();
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            intro();
            char choice;
            do {
                choice = getUserTypeChoice();
                handleUserTypeChoice(choice);
            } while (choice != 'n' && choice != 'N');
        }

        private static void intro() {
            System.out.println("\t\t\t\t ------------------------------------------------- ");
            System.out.println("\t\t\t\t          WELCOME TO OUR BANK SYSTEM               ");
            System.out.println("\t\t\t\t ------------------------------------------------- ");
            System.out.println("\n\n\t\t\t School: Rizal Technological University");
            System.out.println("\n\n\t\t\t Press Enter to continue!");
            scanner.nextLine();
            clearScreen();
        }

        private static void clearScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        private static char getUserTypeChoice() {
            System.out.print("Are you an account holder? (y/n) : ");
            return scanner.next().charAt(0);
        }

        private static void handleUserTypeChoice(char choice) {
            if (choice == 'y' || choice == 'Y') {
                new Gprog().new UserF().displayMenu();
            } else if (choice == 'n' || choice == 'N') {
                System.out.println("Please contact the admin to open an account.");
            } else if (choice == 'a' || choice == 'A') {
                handleAdminLogin();
            } else {
                System.out.println("Invalid choice.");
            }
        }

        private static void handleAdminLogin() {
            Admin admin = new Gprog().new Admin();
            String adminPassword = "12345";
            int attempts = 3;

            System.out.println("Hi admin");

            while (attempts > 0) {
                System.out.print("Please enter admin password (Attempts left: " + attempts + "): ");
                String pass = scanner.next();

                if (adminPassword.equals(pass)) {
                    admin.displayMenu();
                    break;
                } else {
                    System.out.println("Incorrect password. Access denied.");
                    attempts--;
                }
            }

            if (attempts == 0) {
                System.out.println("You have exhausted all attempts. Access denied.");
            }
        }

        static void create() {
            Account newAccount = new Gprog().new Account();
            System.out.print("\t First name : ");
            newAccount.firstName = scanner.next();
            System.out.print("\t Surname : ");
            newAccount.surname = scanner.next();
            System.out.print("\t Create a username : ");
            newAccount.username = scanner.next();
            System.out.print("\t Create a password : ");
            newAccount.password = scanner.next();
            newAccount.balance = 0;
            accounts.add(newAccount);
            System.out.println("Account created successfully.");
        }

        static void showAllAccounts() {
            System.out.println("\t\t\t ===================================================================== ");
            System.out.println("\t\t\t                            ALL ACCOUNTS                             ");
            System.out.println("\t\t\t ===================================================================== ");
            System.out.println("\t\t\t | First Name | Surname | Username | Balance |");
            for (Account account : accounts) {
                System.out.println("\t\t\t | " + account.firstName + " | " + account.surname + " | " + account.username + " | " + account.balance + " |");
            }
            System.out.println("\t\t\t ===================================================================== ");
        }
    }
}