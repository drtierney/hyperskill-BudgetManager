package budget;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static boolean exit;
    static BudgetManager budgetManager = new BudgetManager();

    public static void main(String[] args) {
        while (!exit) {
            menu();
            chooseAction();
        }
    }

    private static void menu() {
        String menu = String.join("\n",
                "Choose your action:",
                "1) Add income",
                "2) Add purchase",
                "3) Show list of purchases",
                "4) Balance",
                "0) Exit"
        );
        System.out.println(menu);
    }

    private static void chooseAction() {
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                addIncome();
                break;
            case 2:
                addPurchase();
                break;
            case 3:
                budgetManager.showPurchaseList();
                break;
            case 4:
                budgetManager.showBalance();
                break;
            case 0:
                exit();
        }
    }

    private static void addIncome() {
        System.out.println("\nEnter income:");
        float income = Float.parseFloat(scanner.nextLine());
        budgetManager.addToBalance(income);
    }

    private static void addPurchase() {
        System.out.println("\nEnter purchase name:");
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        Float price = Float.parseFloat(scanner.nextLine());
        budgetManager.addItem(item, price);
    }

    private static void exit() {
        System.out.println("\nBye!");
        exit = true;
    }
}
