package solution;
import java.io.FileWriter;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Writes product data to a JSON file 
 */
public class JsonProductWriter implements ProductWriter {

    /**
     * Writes product data to a JSON file.
     *
     * @param products an iterator over Product objects to write
     * @param filename the path of the JSON file
     */
	@Override
	public void write(Iterator<Product> products, String filename) {
		  JSONArray jsonArray = new JSONArray();
	        while (products.hasNext()) {
	            Product p = products.next();
	            JSONObject obj = new JSONObject();
	            obj.put("id", p.getId());
	            obj.put("name", p.getName());
	            obj.put("category", p.getCategory());
	            obj.put("supplier", p.getSupplier());
	            obj.put("quantityInStock", p.getQuantityInStock());
	            obj.put("reorderLevel", p.getReorderLevel());
	            obj.put("dateAdded", p.getDateAdded().toString());
	            obj.put("dateUpdated", p.getDateUpdated().toString());
	            jsonArray.add(obj);
	        }
	        try (FileWriter file = new FileWriter(filename)) {
	            file.write(jsonArray.toJSONString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	}

