package solution;
import java.io.FileWriter;
import java.util.Iterator;

import com.opencsv.CSVWriter;

/**
 * A writer that writes product data to a CSV file.
 */
public class CSVProductWriter implements ProductWriter{
    /**
     * Writes product data to a CSV file.
     *
     * @param products an iterator over Product objects to write
     * @param filename the path of the CSV file
     */
	   public void write(Iterator<Product> products, String filename) {
	        try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
	            String[] header = {"id", "name", "category", "supplier", "quantityInStock", "reorderLevel", "dateAdded", "dateUpdated"};
	            writer.writeNext(header);
	            while (products.hasNext()) {
	                Product p = products.next();
	                String[] line = {
	                    p.getId(), p.getName(), p.getCategory(), p.getSupplier(),
	                    String.valueOf(p.getQuantityInStock()),
	                    String.valueOf(p.getReorderLevel()),
	                    p.getDateAdded().toString(),
	                    p.getDateUpdated().toString()
	                };
	                writer.writeNext(line);
	            }
	        } catch (Exception e) {
	            System.err.println("Error writing CSV file: " + e.getMessage());
	        }
	    }
}
