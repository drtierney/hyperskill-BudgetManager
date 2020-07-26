package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    File file = new File("purchases.txt");

    public FileManager() {
        super();
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create the file: " + this.file.getPath());
        }
    }

    public File getFile() {
        return file;
    }

    public void saveFile(BudgetManager budgetManager) {
        Double balance = budgetManager.getBalance();

        ArrayList<Item> items = budgetManager.getItemsList();
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.printf("Balance:%.2f\n", balance);
            for (Item item : items){
                int category = convertCategory(item.getCategory().toString());
                printWriter.printf("%s,%s,%.2f\n", category, item.getName(), item.getPrice());
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }

        System.out.println("\nPurchases were saved!\n");
    }

    public void loadFile(BudgetManager budgetManager) {
        try (Scanner scanner = new Scanner(file)) {
            String balance = scanner.nextLine();
            if (balance.contains("Balance")) {
                budgetManager.setBalance(Double.parseDouble(balance.split(":")[1]));
            }
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] arr = line.split(",");
                int type = Integer.parseInt(arr[0]);
                String item = arr[1];
                Double price = Double.parseDouble(arr[2]);

                budgetManager.addItem(item, price, type);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + this.file);
        }

        System.out.println("\nPurchases were loaded!\n");
    }

    private int convertCategory(String c){
        switch(c){
            case "FOOD":
                return 1;
            case "CLOTHES":
                return 2;
            case "ENTERTAINMENT":
                return 3;
            case "OTHER":
                return 4;
        }
        return 0;
    }
}
