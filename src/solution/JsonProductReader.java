package solution;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * A reader that reads product data from a JSON file.
 */
public class JsonProductReader implements ProductReader{
    /**
     * Reads product data from a JSON file.
     *
     * @param filepath the path of the JSON file
     * @return a list of Product objects
     */
	 public List<Product> read(String filepath)  {
	        List<Product> products = new ArrayList<>();
	        JSONParser parser = new JSONParser();

	        try (FileReader reader = new FileReader(filepath)) {
	            JSONArray productArray = (JSONArray) parser.parse(reader);
	            for (Object obj : productArray) {
	                JSONObject jsonObj = (JSONObject) obj;
	                Product product = new Product(
	                    (String) jsonObj.get("id"),
	                    (String) jsonObj.get("name"),
	                    (String) jsonObj.get("category"),
	                    (String) jsonObj.get("supplier"),
	                    ((Long) jsonObj.get("quantityInStock")).intValue(),
	                    ((Long) jsonObj.get("reorderLevel")).intValue()
	                );
	                product.setDateAdded(LocalDateTime.parse((String) jsonObj.get("dateAdded")));
	                product.setDateUpdated(LocalDateTime.parse((String) jsonObj.get("dateUpdated")));
	                products.add(product);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return products;
	    }
}
