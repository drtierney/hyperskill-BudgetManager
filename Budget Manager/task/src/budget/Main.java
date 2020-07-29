package budget;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static boolean exit;
    static BudgetManager budgetManager = new BudgetManager();
    static FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        java.util.Locale.setDefault(Locale.US);
        //System.out.println(fileManager.getFile());
        while (!exit) {
            menu();
            chooseAction();
        }
    }

    private static void menu() {
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
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
                getPurchases();
                break;
            case 4:
                budgetManager.showBalance();
                break;
            case 5:
                fileManager.saveFile(budgetManager);
                break;
            case 6:
                fileManager.loadFile(budgetManager);
                break;
            case 7:
                chooseSort();
                break;
            case 0:
                exit();
        }
    }

    private static void addIncome() {
        double income = 0.00;
        System.out.println("\nEnter income:");
        if (scanner.hasNextDouble() || scanner.hasNextInt()) {
            income = Double.parseDouble(scanner.nextLine());
            budgetManager.addToBalance(income);
        }
    }

    private static void addPurchase() {
        while (true) {
            String type = "";
            String item = "";
            String price = "";
            System.out.println("\nChoose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) Back");

            type = scanner.nextLine();
            System.out.println();
            if (Integer.parseInt(type) >= 5) {
                System.out.println();
                break;
            }
            System.out.println("Enter purchase name:");
            item = scanner.nextLine();
            System.out.println("Enter its price:");
            price = scanner.nextLine();

            try {
                double dprice = Double.parseDouble(price);
                int itype = Integer.parseInt(type);

                budgetManager.addItem(item, dprice, itype);
                System.out.println("Purchase was added!");
            } catch (Exception e) {
                System.out.println();
                break;
            }
        }
    }

    private static void getPurchases() {
        while (true) {
            System.out.println("\nChoose the type of purchases\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) All\n" +
                    "6) Back");
            int type = Integer.parseInt(scanner.nextLine());
            if (type == 6 || type < 1 || type > 6) {
                System.out.println();
                return;
            } else {
                System.out.println();
                switch (type) {
                    case 1:
                        budgetManager.showPurchaseList("Food");
                        break;
                    case 2:
                        budgetManager.showPurchaseList("Clothes");
                        break;
                    case 3:
                        budgetManager.showPurchaseList("Entertainment");
                        break;
                    case 4:
                        budgetManager.showPurchaseList("Other");
                        break;
                    case 5:
                        budgetManager.showPurchaseList();
                        break;
                }
            }
        }
    }

    private static void chooseSort() {
        while (true) {
            System.out.println("\nHow do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back");
            int method = Integer.parseInt(scanner.nextLine());
                if (method == 4 || method < 1 || method > 4) {
                    System.out.println();
                    return;
                } else {
                    System.out.println();
                    switch (method) {
                        case 1:
                            budgetManager.sortAllPurchases();
                            break;
                        case 2:
                            budgetManager.SortAllTypes();
                            break;
                        case 3:
                            System.out.println("\nChoose the type of purchase\n" +
                                    "1) Food\n" +
                                    "2) Clothes\n" +
                                    "3) Entertainment\n" +
                                    "4) Other");
                            int type = Integer.parseInt(scanner.nextLine());
                            budgetManager.sortType(type);
                            break;
                    }
                }
            }
        }

    private static void exit() {
        System.out.print("\nBye!");
        exit = true;
    }
}
