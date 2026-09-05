package products;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Manages a collection of products in inventory.
 * Provides operations to add, remove, sort, categorize, and persist products.
 */

public class ProductInventory {
    private HashMap<String, Product> products;
    private ProductIOContext ioContext;

    /**
     * Loads products from a file into the inventory.
     * @param filename the file to load from (CSV or JSON)
     */
	
    
    public ProductInventory(String filename) {
        products = new HashMap<>();
        ioContext = new ProductIOContext();

        
        ProductReaderFactory readerFactory = new ProductReaderFactory();
        ProductReader reader = readerFactory.getProductReader(filename);
        ioContext.setReaderStrategy(reader);

        List<Product> productList = ioContext.read(filename);
        for (Product product : productList) {
            products.put(product.getId(), product);
        }
    }
    
    /**
     * Saves all products to a specified output file.
     * @param filename the file to save to
     * @return true if saving succeeded
     */
    
    public boolean saveProductsToFile(String filename) {
        ProductWriterFactory writerFactory = new ProductWriterFactory();
        ProductWriter writer = writerFactory.getProductWriter(filename);
        ioContext.setWriterStrategy(writer);
        ioContext.write(products.values().iterator(), filename);
        return true;
    }
    
    /**
     * Adds a product to the inventory.
     * @param product the product to add
     */

    public void add(Product product) {
        products.put(product.getId(), product);
        product.setDateAdded(LocalDateTime.now());
    }
    
    /**
     * Updates the stock quantity for a product.
     * @param id the product ID
     * @param newQuantity the updated quantity
     * @return true if the update was successful
     */

    public boolean update(String id, int newQuantity) {
        if (products.containsKey(id)) {
            Product product = products.get(id);
            product.setQuantityInStock(newQuantity);
            product.setDateUpdated(LocalDateTime.now());
            return true;
        }
        return false;
    }
    

	/**
     * Removes a product by ID.
     * @param id the product ID
     * @return true if the product was removed
     */

    public boolean remove(String id) {	
        return products.remove(id) != null;
    }
    
    /**
     * Gets a list of products sorted by name.
     * @return a sorted list of products
     */

    public ArrayList<Product> sortProductName() {
        ArrayList<Product> list = new ArrayList<>(products.values());
        list.sort(Comparator.comparing(Product::getName));
        return list;
    }
    
    /**
     * Gets a list of products sorted by supplier.
     * @return a sorted list of products
     */

    public ArrayList<Product> sortProductSupplier() { 	
        ArrayList<Product> list = new ArrayList<>(products.values());
        list.sort(Comparator.comparing(Product::getSupplier));
        return list;
    }
    
    /**
     * Filters products by category.
     * @param category the category to filter by
     * @return a list of products in the category
     */

    public ArrayList<Product> categorizeProduct(String category) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products.values()) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }
    
    /**
     * Gets products that are below their reorder level.
     * @return a list of products needing reorder
     */

    public ArrayList<Product> reorderProducts() {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products.values()) {
            if (p.getQuantityInStock() < p.getReorderLevel()) {
                result.add(p);
            }
        }
        return result;
    }
}
