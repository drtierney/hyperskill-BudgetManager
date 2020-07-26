package budget;

import java.util.ArrayList;

public class BudgetManager {
    private ArrayList<Item> itemsList;
    private double balance;
    private double total;

    public BudgetManager() {
        this.itemsList = new ArrayList<>();
        this.balance = 0.00;
    }

    public void addToBalance(double income) {
        this.balance += income;
        System.out.println("Income was added!\n");
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addItem(String item, double price, int type) {
        this.itemsList.add(new Item(item, price, type));
        this.total += price;
    }

    public void showPurchaseList(String category) {
        double catTotal = 0.00;
        System.out.println();
        System.out.println(category + ":");
        if (this.itemsList.size() > 0) {
            ArrayList<Item> categoryList = new ArrayList<Item>();
            for (Item item : itemsList) {
                if (item.getCategory() == PurchaseCategory.valueOf(category.toUpperCase()))
                    categoryList.add(item);
            }
            if (categoryList.size() == 0) {
                System.out.println("Purchase list is empty\n");
            } else {
                for (Item item : categoryList) {
                    System.out.printf("%s $%.2f\n", item.getName(), item.getPrice());
                    catTotal += item.getPrice();
                }
                System.out.printf("Total sum: $%.2f\n", catTotal);
            }
        } else {
            System.out.println("Purchase list is empty");
        }
    }

    public void showPurchaseList() {
        System.out.println();
        System.out.println("All:");
        if (this.itemsList.size() > 0) {
            for (Item item : itemsList) {
                System.out.printf("%s $%.2f\n", item.getName(), item.getPrice());
            }
            System.out.printf("Total sum: $%.2f\n", this.total);
        } else {
            System.out.println("Purchase list is empty\n");
        }
    }

    public void showBalance() {
        System.out.printf("\nBalance: $%.2f\n\n", this.balance - this.total);
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Item> getItemsList() {
        return itemsList;
    }
}
