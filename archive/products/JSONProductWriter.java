package products;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.Iterator;

/**
 * Writes products to a JSON file.
 */

public class JSONProductWriter implements ProductWriter {
	
	/**
     * Writes products to the specified JSON file.
     * @param products the products to write
     * @param filename the output file
     */
	
    @Override
    public void writeProducts(Iterator<Product> products, String filename) {
        JSONArray array = new JSONArray();

        while (products.hasNext()) {
            Product p = products.next();
            JSONObject obj = new JSONObject();
            obj.put("ID", p.getId());
            obj.put("Name", p.getName());
            obj.put("Category", p.getCategory());
            obj.put("Supplier", p.getSupplier());
            obj.put("QuantityInStock", p.getQuantityInStock());
            obj.put("ReorderLevel", p.getReorderLevel());
            obj.put("DateAdded", p.getDateAdded().toString());
            obj.put("DateUpdatd", p.getDateUpdated().toString());
            array.add(obj);
        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(array.toJSONString());
        } catch (Exception e) {
            System.err.println("Error writing JSON: " + e.getMessage());
        }
    }
}
