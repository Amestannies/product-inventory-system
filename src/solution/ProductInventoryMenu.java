package solution;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;



public class ProductInventoryMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ProductInventory inventory = new ProductInventory();
     //   String outputFile = "Products.csv";
        
        System.out.print("Enter input file name (with extension): ");
        String inputFile = scanner.nextLine();

        // Use factory to get reader based on file extension
        ProductReader reader = ProductReaderFactory.getReader(inputFile);
        if (reader != null) {
            List<Product> inputProducts = reader.read(inputFile);
            for (Product current : inputProducts) {
                inventory.add(current);
            }
        }

        String choice = getUserChoice();
        while (!(choice.equals("Q"))) {
            handleChoice(choice, inventory);
            choice = getUserChoice();
        }

        System.out.println("Printing reports");
        handleOutput(inventory);
        
        System.out.println("Exiting program.");
        scanner.close();
    }

    private static String getUserChoice() {
        System.out.println("\nProduct Inventory Menu:");
        System.out.println("A. Add Product");
        System.out.println("R. Remove Product");
        System.out.println("U. Update Quantity in Stock");
        System.out.println("N. Print Products Sorted by Name");
        System.out.println("C. Print Products in a Category");
        System.out.println("O. Print Products to Reorder");
        System.out.println("S. Print Products Sorted by Supplier");
        System.out.println("Q. Quit");
        System.out.print("Enter your choice: ");
        return scanner.nextLine().toUpperCase();
    }

    private static void handleChoice(String choice, ProductInventory inventory) {
        switch (choice) {
            case "A":
                addProduct(inventory);
                break;
            case "R":
                removeProduct(inventory);
                break;
            case "U":
                updateQuantity(inventory);
                break;
            case "N":
                printProductsSortedByName(inventory);
                break;
            case "C":
                printProductsByCategory(inventory);
                break;
            case "S":
                printProductsSortedBySupplier(inventory);
                break;
            case "O":
                printReorderList(inventory);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addProduct(ProductInventory inventory) {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter supplier: ");
        String supplier = scanner.nextLine();
        System.out.print("Enter quantity in stock: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter reorder level: ");
        int reorderLevel = Integer.parseInt(scanner.nextLine());

        inventory.add(new Product(id, name, category, supplier, quantity, reorderLevel));
        System.out.println("Product added successfully.");
    }

    private static void removeProduct(ProductInventory inventory) {
        System.out.print("Enter product ID to remove: ");
        String removeId = scanner.nextLine();
        inventory.removeProduct(removeId);
        System.out.println("Product " + removeId + " removed successfully.");
    }

    private static void updateQuantity(ProductInventory inventory) {
        System.out.print("Enter product ID to update quantity: ");
        String id = scanner.nextLine();
        System.out.print("Enter new quantity: ");
        int newQuantity = Integer.parseInt(scanner.nextLine());
        inventory.updateQuantity(id, newQuantity);
        System.out.println("Quantity updated successfully.");
    }

    private static void printProductsSortedByName(ProductInventory inventory) {
        System.out.println("\nProducts Sorted by Name:");
        List<Product> sortedProducts = inventory.getProductsSortedByName();
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    private static void printReorderList(ProductInventory inventory) {
        List<Product> reorders = inventory.getProductsToReorder();
        for (Product product : reorders) {
            System.out.println(product);
        }
    }

    private static void printProductsByCategory(ProductInventory inventory) {
        System.out.print("Enter category: ");
        String searchCategory = scanner.nextLine();
        System.out.println("\nProducts in Category: " + searchCategory);
        List<Product> productsInCategory = inventory.getProductsByCategory(searchCategory);
        for (Product product : productsInCategory) {
            System.out.println(product);
        }
    }

    private static void printProductsSortedBySupplier(ProductInventory inventory) {
        System.out.println("\nProducts Sorted by Supplier:");
        List<Product> sortedProducts = inventory.getProductsSortedBySupplier();
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }
    
    private static void handleOutput(ProductInventory inventory) {
        System.out.print("Enter output file name for all products: ");
        String outputFile = scanner.nextLine();
        ProductWriter writer = ProductWriterFactory.getWriter(outputFile);
        if (writer != null) {
            writer.write(inventory.getAllProducts().iterator(), outputFile);
        }

        System.out.print("Enter output file name for products sorted by name: ");
        String nameFile = scanner.nextLine();
        ProductWriter nameWriter = ProductWriterFactory.getWriter(nameFile);
        if (nameWriter != null) {
            nameWriter.write(inventory.getProductsSortedByName().iterator(), nameFile);
        }

        System.out.print("Enter output file name for products sorted by supplier: ");
        String supplierFile = scanner.nextLine();
        ProductWriter supplierWriter = ProductWriterFactory.getWriter(supplierFile);
        if (supplierWriter != null) {
            supplierWriter.write(inventory.getProductsSortedBySupplier().iterator(), supplierFile);
        }
    }

}
