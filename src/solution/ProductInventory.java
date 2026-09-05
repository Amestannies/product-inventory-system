package solution;
import java.time.LocalDateTime;
import java.util.*;

/**
 * A list of products
 */
public class ProductInventory {
    private Map<String, Product> products;

    public ProductInventory() {
        this.products = new HashMap<>();
    }

    /**
     * add a product to the list
     * @param product
     */
    public void add(Product product) {
        products.put(product.getId(), product);
    }

    /**
     * remove a product based on its id
     * @param productId
     */
    public void removeProduct(String productId) {
        products.remove(productId);
    }

    /**
     * update the quantity in stock of the product corresponding
     * to the id parameter
     * @param productId
     * @param newQuantity
     */
    public void updateQuantity(String productId, int newQuantity) {
        Product product = products.get(productId);
        if (product != null) {
            product.setQuantityInStock(newQuantity);
            product.setDateUpdated(LocalDateTime.now());
        }
        
    }

    /**
     * return a list of all the products in the inventory
     * @return list
     */
    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    private static class ProductNameComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }
    
    private static class ProductSupplierComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getSupplier().compareTo(p2.getSupplier());
        }
    }

    /**
     * return a list of products sorted by name
     */
    public ArrayList<Product> getProductsSortedByName() {
         ArrayList<Product> sortedList = getAllProducts();
      //  Collections.sort(sortedList, new ProductNameComparator());
        Collections.sort(sortedList, Comparator.comparing(product->product.getName()));
      //  Collections.sort(sortedList, Comparator.comparing(Product::getName));
        return sortedList;
    }

    /**
     * return a list of products of the category passed in the parameter
     */
    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> filteredList = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    /** return a listof products sorted by supplier name
     */
    public ArrayList<Product> getProductsSortedBySupplier() {
        ArrayList<Product> sortedList = getAllProducts();
        Collections.sort(sortedList, new ProductSupplierComparator());
        return sortedList;
    }
    
    /**
     * return a list of products that need to be reordered
     * their quantity in stock is below the reorder quantity
     * @return
     */
   public ArrayList<Product> getProductsToReorder() {
       ArrayList<Product> list = getAllProducts();
       ArrayList<Product> reorderList = new ArrayList<Product> ();
       for (Product current : list) {
    	   if (current.getQuantityInStock() <= current.getReorderLevel()){
    		   reorderList.add(current);
    	   }  
       }
       return reorderList;
   }
    
   
}

