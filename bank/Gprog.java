package bank;
import java.util.*;
import java.io.*;

public  class  Gprog{


    static class Account {
        String firstName;
        String surname;
        String username;
        String password;
        Double balance;
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

            BankingSystem.loadAccounts();

            boolean found = false;

            for (Account account : BankingSystem.accounts) {
                if (account.username.equals(inputUsername) && account.password.equals(inputPassword)) {
                    System.out.println("Login successful");
                    currentUser = account;
                    accountMenu();
                    found = true;
                    break;
                }if (!found) {
                    System.out.println("Invalid username or password.");
                    
                }
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
            } while (isValidInput);
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

        private boolean isValidInput = true;

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
                    isValidInput = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }

        private void deposit() {
            System.out.print("\t Enter the amount : ");
            try {
                double amount = scanner.nextDouble();
                if (amount > 0) {
                    currentUser.balance += amount;
                    System.out.println(" Deposit successful! New balance: " + currentUser.balance);
                    System.out.println("Updating balance.....");
                    BankingSystem.UpdateBalance();
                    System.out.println("Account balance updated successfully.");
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
                double amount = scanner.nextDouble();
                if (amount > 0) {
                    if (amount <= currentUser.balance) {
                        currentUser.balance -= amount;
                        System.out.println("Withdrawal successful! New balance: " + currentUser.balance);
                        System.out.println("Updating balance.....");
                        BankingSystem.UpdateBalance();
                        System.out.println("Account balance updated successfully.");
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
            BankingSystem.loadAccounts();
            char choice;
            do {
                choice = getUserTypeChoice();
                switch (choice) {
                    case 'y':
                    case 'Y':
                        new Gprog().new UserF().displayMenu();                       
                        break;
                    case 'a':
                    case 'A':
                        handleAdminLogin();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
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
        public static File accountsFile;
        static{
            try{
                accountsFile = new File("data.txt"); 
                loadAccounts();
            }catch(Exception e){
                System.err.println("Error loading accounts: " + e.getMessage());
            }

        }

        static void create() {
            Account newAccount = new Account();
            System.out.print("\t First name : ");
            newAccount.firstName = scanner.next();
            System.out.print("\t Surname : ");
            newAccount.surname = scanner.next();
            System.out.print("\t Create a username : ");
            newAccount.username = scanner.next();
            System.out.print("\t Create a password : ");
            newAccount.password = scanner.next();
            System.out.print("\t Initial deposit : ");
            newAccount.balance = scanner.nextDouble();

            boolean exists = false;
            for (Account account : accounts) {
                if (account.username.equals(newAccount.username)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                accounts.add(newAccount);
                saveAccounts();
                System.out.println("Account created successfully.");  
            }else{
                System.out.println("Username already exists. Please try again.");
            }

            
        }

        static void showAllAccounts() {
            System.out.println("\t\t\t ===================================================================== ");
            System.out.println("\t\t\t                            ALL ACCOUNTS                             ");
            System.out.println("\t\t\t ===================================================================== ");
            System.out.println("\t\t\t | First Name | Surname | Username | Balance |");
            System.out.println("\t\t\t ===================================================================== ");
            for (Account account : accounts) {
                System.out.println("\t\t\t | " + account.firstName + " | " + account.surname + " | " + account.username + " | " + account.balance + " |");

            }
            loadAccounts();
            scanner.nextLine();
            clearScreen();
        }

        public static void saveAccounts(){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(accountsFile))){
                for(Account account : accounts){
                    String serializedData = serialized(account);
                    writer.write(serializedData);
                    writer.newLine();
                }
            }catch(IOException e){
                System.err.println("Error saving accounts: " + e.getMessage());
            }
        }

        //account loading/ reading
        public static void loadAccounts(){
            try(BufferedReader reader = new BufferedReader(new FileReader(accountsFile))){
                String line;
                while((line = reader.readLine()) != null){
                    String[] data = line.split(",");
                    accounts.clear();
                    accounts.add(deserialized(data));
                }
            }catch(IOException e){
                System.err.println("Error loading accounts: " + e.getMessage());
            }
        }
        public static void UpdateBalance(){
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(accountsFile));
                for (Account account : accounts) {
                    String serializedData = serialized(account);
                    writer.write(serializedData);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Balance updated successfully.");
            }catch(IOException e){
                System.err.println("Error updating balance: " + e.getMessage());
            }

        }

        public static String serialized(Account account){
            return account.firstName + "," +
                    account.surname + "," +
                    account.username + "," +
                    account.password + "," +
                    account.balance + ",";
        }
        private static Account deserialized(String[] data){
            Account account = new Account();
            account.firstName = data[0];
            account.surname = data[1];
            account.username = data[2];
            account.password = data[3];
            account.balance = Double.parseDouble(data[4]);
            return account;
        }
    }
}
