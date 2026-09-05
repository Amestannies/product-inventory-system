package solution;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


/**
 * A reader that reads product data from a CSV file.
 */
public class CSVProductReader implements ProductReader{
    /**
     * Reads product data from a CSV file.
     *
     * @param filepath the path of the CSV file
     * @return a list of Product objects
     */
	public List<Product> read(String filepath)   {
        List<Product> products = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filepath))
                .withSkipLines(1) // Skip header
                .build()) {
            String[] fields;
            while ((fields = reader.readNext()) != null) {
                Product product = new Product(
                    fields[0], // id
                    fields[1], // name
                    fields[2], // category
                    fields[3], // supplier
                    Integer.parseInt(fields[4]), // quantity
                    Integer.parseInt(fields[5])  // reorder level
                );
                product.setDateAdded(LocalDateTime.parse(fields[6]));
                product.setDateUpdated(LocalDateTime.parse(fields[7]));
                products.add(product);
            }
        } catch (Exception e) {
			e.printStackTrace();
		} 
        return products;
    }

}
