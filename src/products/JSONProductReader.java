package products;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads products from a JSON file.
 */

public class JSONProductReader implements ProductReader {
	
	/**
     * Reads products from the specified JSON file.
     * @param filename the input file
     * @return a list of products
     */


    @Override
    public List<Product> readProducts(String filename) {
        List<Product> products = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader(filename));
            for (Object obj : array) {
                JSONObject json = (JSONObject) obj;

                String id = (String) json.get("ID");
                String name = (String) json.get("Name");
                String category = (String) json.get("Category");
                String supplier = (String) json.get("Supplier");
                int quantity = ((Long) json.get("QuantityInStock")).intValue();
                int reorderLevel = ((Long) json.get("ReorderLevel")).intValue();
                LocalDateTime dateAdded = LocalDateTime.parse((String) json.get("DateAdded"));
                LocalDateTime dateUpdated = LocalDateTime.parse((String) json.get("DateUpdatd")); // Typo from input

                Product product = new Product(id, name, category, supplier, quantity, reorderLevel);
                product.setDateAdded(dateAdded);
                product.setDateUpdated(dateUpdated);
                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error reading JSON: " + e.getMessage());
        }

        return products;
    }
}
