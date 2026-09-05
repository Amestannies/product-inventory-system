package products;

import java.util.*;

/**
 * Provides a console-based user interface for interacting with the product inventory.
 * Allows the user to add, remove, update, sort, and categorize products,
 * and save inventory reports.
 */

public class ProductInventoryMenu {
	
	/**
     * Entry point for the inventory application.
     * Prompts for input file and provides menu options for user actions.
     * @param args command-line arguments (not used)
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input filename (CSV or JSON): ");
        String filename = scanner.nextLine();

        ProductInventory products = new ProductInventory(filename);

        char choice;
        do {
            choice = displayMenu(scanner);

            switch (choice) {
            case 'A':
                addProduct(products, scanner);
                break;
            case 'R':
                removeProduct(products, scanner);
                break;
            case 'U':
                updateQuantity(products, scanner);
                break;
            case 'N':
                sortProductName(products);
                break;
            case 'C':
                categorizeProducts(products, scanner);
                break;
            case 'O':
                reorderProducts(products);
                break;
            case 'S':
                sortProductSupplier(products);
                break;
            case 'Q':
                printingReports(products, filename);
                System.out.println("Exiting the program.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }


        } while (choice != 'Q');

        scanner.close();
    }
    
    /**
     * Displays the menu of inventory actions and reads the user's choice.
     * @param scanner the input scanner
     * @return the chosen menu option
     */

    private static char displayMenu(Scanner scanner) {
        System.out.println("\nProduct Inventory Menu:");
        System.out.println("A. Add product");
        System.out.println("R. Remove Product");
        System.out.println("U. Update Quantity");
        System.out.println("N. Print Products Sorted by Name");
        System.out.println("C. Print Products by Category");
        System.out.println("O. Print Products to Reorder");
        System.out.println("S. Print Products Sorted by Supplier");
        System.out.println("Q. Quit and Save");
        System.out.print("Enter your choice: ");
        return scanner.nextLine().toUpperCase().charAt(0);
    }
    
    /**
     * Prompts the user to enter product details and adds it to the inventory.
     * @param inventory the product inventory
     * @param scanner the input scanner
     */

    private static void addProduct(ProductInventory inventory, Scanner scanner) {
        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Category: "); String category = scanner.nextLine();
        System.out.print("Supplier: "); String supplier = scanner.nextLine();
        System.out.print("Quantity in stock: "); int qty = Integer.parseInt(scanner.nextLine());
        System.out.print("Reorder level: "); int reorder = Integer.parseInt(scanner.nextLine());

        Product product = new Product(id, name, category, supplier, qty, reorder);
        inventory.add(product);
        System.out.println("Product added.");
    }
    
    /**
     * Prompts for a product ID and updates its quantity in inventory.
     * @param inventory the product inventory
     * @param scanner the input scanner
     */

    private static void updateQuantity(ProductInventory inventory, Scanner scanner) {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();
        System.out.print("New quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());

        if (inventory.update(id, qty)) {
            System.out.println("Quantity updated.");
        } else {
            System.out.println("Product not found.");
        }
    }
    
    /**
     * Prompts for a product ID and removes the product from inventory.
     * @param inventory the product inventory
     * @param scanner the input scanner
     */

    private static void removeProduct(ProductInventory inventory, Scanner scanner) {
        System.out.print("Enter product ID to remove: ");
        String id = scanner.nextLine();

        if (inventory.remove(id)) {
            System.out.println("Product removed.");
        } else {
            System.out.println("Product not found.");
        }
    }
    
    /**
     * Prompts for a category and prints all matching products.
     * @param inventory the product inventory
     * @param scanner the input scanner
     * @param products the inventory instance (duplicate ref, may be refactored)
     */

    private static void categorize(ProductInventory inventory, Scanner scanner, ProductInventory products) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        ArrayList<Product> result = inventory.categorizeProduct(category);

        if (result.isEmpty()) {
            System.out.println("No products found in category " + category);
        } else {
            printList(result);
        }
    }
    
    /**
     * Displays products sorted by name.
     * @param products the product inventory
     */

    private static void sortProductName(ProductInventory products) {
        System.out.println("Products Sorted by Name:");
        for (Product product : products.sortProductName()) {
            System.out.println(product);
        }
    }
    
    /**
     * Displays products belonging to a specified category.
     * @param products the product inventory
     * @param scanner the input scanner
     */

    private static void categorizeProducts(ProductInventory products, Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        ArrayList<Product> categorized = products.categorizeProduct(category);

        if (categorized.isEmpty()) {
            System.out.println("No products found in category: " + category);
        } else {
            System.out.println("Products in Category: " + category);
            for (Product product : categorized) {
                System.out.println(product);
            }
        }
    }

    /**
     * Displays products that need to be reordered.
     * @param products the product inventory
     */
    
    private static void reorderProducts(ProductInventory products) {
        ArrayList<Product> reorderList = products.reorderProducts();

        if (reorderList.isEmpty()) {
            System.out.println("No products need to be reordered.");
        } else {
            System.out.println("Products to Reorder:");
            for (Product product : reorderList) {
                System.out.println(product);
            }
        }
    }
    
    /**
     * Displays products sorted by supplier name.
     * @param products the product inventory
     */

    private static void sortProductSupplier(ProductInventory products) {
        System.out.println("Products Sorted by Supplier:");
        for (Product product : products.sortProductSupplier()) {
            System.out.println(product);
        }
    }
    
    /**
     * Generates and saves inventory reports to files.
     * @param products the product inventory
     * @param filename the base file to use for saving
     */

    private static void printingReports(ProductInventory products, String filename) {
        System.out.println("Saving current inventory...");
        products.saveProductsToFile(filename);

        System.out.println("Saving 'allproducts.txt'...");
        products.saveProductsToFile("allproducts.txt");

        System.out.println("Saving 'productsbysupplier.txt'...");
        ArrayList<Product> sortedBySupplier = products.sortProductSupplier();
        products.saveProductsToFile("productsbysupplier.txt");

        System.out.println("Reports generated successfully.");
    }
    
    /**
     * Prints a list of products to the console.
     * @param list the list of products to print
     */

    private static void printList(List<Product> list) {
        for (Product p : list) {
            System.out.println(p);
        }
    }
}
