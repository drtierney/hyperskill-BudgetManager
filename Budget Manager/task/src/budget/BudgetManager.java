package budget;

import java.util.ArrayList;

public class BudgetManager {
    private ArrayList<Item> itemsList;
    private float balance;
    private float total;

    public BudgetManager() {
        this.itemsList = new ArrayList<>();
        this.balance = 0;
    }

    public void addToBalance(Float income) {
        this.balance += income;
        System.out.println("Income was added!\n");
    }

    public void addItem(String item, float price) {
        this.itemsList.add(new Item(item, price));
        this.balance = Math.max(this.balance - price, 0.0f);
        this.total += price;
        System.out.println("Purchase was added!\n");
    }

    public void showPurchaseList() {
        System.out.println();
        if (this.itemsList.size() > 0) {
            for (Item item : itemsList) {
                System.out.printf("%s %.2f\n", item.getName(), item.getPrice());
            }
            System.out.printf("\nTotal sum: %.2f\n", this.total);
        } else {
            System.out.println("Purchase list is empty\n");
        }
    }

    public void showBalance() {
        System.out.printf("\nBalance: $%.2f\n\n", this.balance);
    }
}
