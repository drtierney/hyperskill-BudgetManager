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
            System.out.println("Purchase list is empty!");
        }
    }

    public void showPurchaseList() {
        System.out.println();
        if (this.itemsList.size() > 0) {
            System.out.println("All:");
            for (Item item : itemsList) {
                System.out.printf("%s $%.2f\n", item.getName(), item.getPrice());
            }
            System.out.printf("Total sum: $%.2f\n", this.total);
        } else {
            System.out.println("Purchase list is empty!\n");
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

    public void sortAllPurchases() {
        if (this.itemsList.size() < 0) {
            System.out.println("Purchase list is empty!");
        } else {
            itemsList.sort(new PriceSorter());
            showPurchaseList();
        }
    }

    public void SortAllTypes() {
        itemsList.sort(new TypeSorter());
        double sum = 0.00;
        String[] types = {"Food", "Entertainment", "Clothes", "Other"};
        for (String type : types) {
            sum = 0.00;
            ArrayList<Item> categoryList = new ArrayList<Item>();
            for (Item item : itemsList) {
                if (item.getCategory() == PurchaseCategory.valueOf(type.toUpperCase()))
                    categoryList.add(item);
            }
            if (categoryList.size() > 0) {
                for (Item item : categoryList) {
                    sum += item.getPrice();
                }
            }
            if (sum == 0.00){
                System.out.printf("%s - $0\n", type);
            } else {
                System.out.printf("%s - $%.2f\n", type, sum);
            }
        } if (sum == 0.00) {
            System.out.println("Total sum: $0\n");
        } else {
            System.out.printf("Total sum: $%.2f\n", this.total);
        }
    }


    public void sortType(int type) {
        if (this.itemsList.size() == 0) {
            System.out.println("\nPurchase list is empty!\n");
        } else {

            String category = convertCategory(type);
            double sum = 0.00;
            ArrayList<Item> categoryList = new ArrayList<Item>();
            for (Item item : itemsList) {
                if (item.getCategory() == PurchaseCategory.valueOf(category.toUpperCase()))
                    categoryList.add(item);
            }
            System.out.println();
            if (categoryList.size() > 0) {
                categoryList.sort(new PriceSorter());
                for (Item item : categoryList) {
                    System.out.printf("%s $%.2f\n", item.getName(), item.getPrice());
                    sum += item.getPrice();
                }
                System.out.printf("Total sum: $%.2f\n", sum);
            } else {
                System.out.println("\nPurchase list is empty!\n");
            }
        }
    }

    private String convertCategory(int i) {
        switch (i) {
            case 1:
                return "FOOD";
            case 2:
                return "CLOTHES";
            case 3:
                return "ENTERTAINMENT";
            case 4:
                return "OTHER";
        }
        return "";
    }

}
