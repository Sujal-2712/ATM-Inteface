import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Password extends Exception {
    public String getMessage() {
        return "Your Password is not follow the criteria";
    }
}

class user {
    public String name;
    public String username;
    public String password;
    public String accountNo;
    float balance = 0f;
    ArrayList<String> transactionHistory = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);

    public Boolean check_password(String password) {
        if (password.length() < 8)
            return false;
        int character_count = 0;
        int num_count = 0;
        int special_ch = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (isNum(ch))
                num_count++;
            else if (isCharacter(ch))
                character_count++;
            else if (isSpecialCh(password))
                special_ch++;
            else
                return false;

        }
        return (character_count >= 1 && num_count >= 1 && special_ch >= 1);
    }

    public static Boolean isNum(Character ch) {
        return (ch >= '0' && ch <= '9');
    }

    public static Boolean isCharacter(Character ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public static Boolean isSpecialCh(String password) {
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher isSpecial = special.matcher(password);
        if (isSpecial.find())
            return true;
        else
            return false;

    }

    public void setPassword() {
        while (true) {
            System.out.print("Enter the password :");
            this.password = sc.nextLine();
            System.out.println();
            if (check_password(password)) {
                System.out.println("Password Set SucessFully !");
                break;
            } else
                System.out.println("Password Not follow the criteria...");
        }
    }

    public void sign_up() {
        System.out.print("Enter your Name: ");
        name = sc.nextLine();
        System.out.print("Enter your Username: ");
        username = sc.nextLine();
        System.out.println();
        System.out.println();
        System.out.print(
                "Password Criteria ::\n" +
                        "1. A password must have at least eight characters.\n" +
                        "2. A password consists of only letters, Special Characters and digits.\n" +
                        "3. A password must contain at least one digits \n" +
                        "4. A password must contain at least one Special Character \n");
        System.out.println();
        setPassword();
        System.out.println();
        System.out.print("Enter your Account No: ");
        accountNo = sc.nextLine();

        System.out.println();

    }

    public Boolean sign_in() {
        System.out.println();
        System.out.println("Login Using Your Username and Password..!");
        Boolean isLogin = false;
        while (!isLogin) {
            System.out.print("Enter your username: ");
            String user = sc.nextLine();
            System.out.print("Enter your Password: ");
            String pass = sc.nextLine();

            if (user.equals(username) && pass.equals(password)) {
                System.out.println();
                System.out.println("Successfully Login..!");
                isLogin = true;
            } else {
                System.out.println();
                System.out.println("Invaild Username OR Password");
                isLogin = false;
            }
        }
        return isLogin;
    }

}

class atm extends user {
    public float checkBalance() {
        return balance;

    }

    public void withdraw() {
        System.out.println();
        System.out.print("Enter the Amount :");
        float enteredAmount = sc.nextFloat();
        if (checkBalance() < enteredAmount) {
            System.out.println("Your Account have not Suffient Balance..");
        } else {
            System.out.println("SucessFully WithDraw...!");
            balance = balance - enteredAmount;
            String history = "Rs." + enteredAmount + " is WithDraw";
            transactionHistory.add(history);
        }

    }

    public void trans_History() {
        System.out.println();
        System.out.println("Your Account Transaction Histoty :");
        for (int i = 0; i < transactionHistory.size(); i++) {
            System.out.println(transactionHistory.get(i));
        }
        System.out.println();
    }

    public void deposit() {
        System.out.println();
        System.out.print("Enter the Amount:");
        float enteredAmount = sc.nextFloat();
        balance += enteredAmount;
        System.out.println();
        System.out.println("SuccessFully Deposited...!");
        String history = "Rs." + enteredAmount + " is Deposited";
        transactionHistory.add(history);

    }

    public void transfer() {
        System.out.println();
        sc.nextLine();
        System.out.print("Enter Receipent's Name: ");
        String receipent = sc.nextLine();
        System.out.print("\nEnter amount to transfer : ");
        float enteredAmount = sc.nextFloat();

        if (balance < enteredAmount) {
            System.out.println("Transfer is Failed");
            System.out.println("Not Suffient Balance to Transfer");
        } else {
            balance -= enteredAmount;
            System.out.println("SuccessFully Transfered to " + receipent);
            String history = "Rs." + enteredAmount + " is Transfered to " + receipent;
            transactionHistory.add(history);

        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        user atmUser = new user();
        System.out.println();
        System.out.println("Welcome To SBI ATM");
        System.out.println();
        System.out.println("Register To YourSelf");
        atmUser.sign_up();
        atmUser.sign_in();
        atm ATM = new atm();
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("1. Check Your Balance ");
            System.out.println("2. Withdraw  ");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History ");
            System.out.println();
            System.out.print("Enter Your Choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n Your Account Balance : " + ATM.checkBalance());
                    break;
                case 2:
                    ATM.withdraw();
                    break;
                case 3:
                    ATM.deposit();
                    break;
                case 4:
                    ATM.transfer();
                    break;
                case 5:
                    ATM.trans_History();
                    break;
                default:
                    System.out.println("Invaild Choice Please Enter Correct Choice");
                    break;
            }

        }

    }
}