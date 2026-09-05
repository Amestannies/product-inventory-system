package products;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads products from a CSV file.
 */

public class CSVProductReader implements ProductReader {
	
	/**
     * Reads products from the specified CSV file.
     * @param filename the input file
     * @return a list of products
     */

    @Override
    public List<Product> readProducts(String filename) {
        List<Product> products = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).withSkipLines(1).build()) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String id = line[0];
                String name = line[1];
                String category = line[2];
                String supplier = line[3];
                int quantity = Integer.parseInt(line[4]);
                int reorderLevel = Integer.parseInt(line[5]);
                LocalDateTime dateAdded = LocalDateTime.parse(line[6]);
                LocalDateTime dateUpdated = LocalDateTime.parse(line[7]);

                Product product = new Product(id, name, category, supplier, quantity, reorderLevel);
                product.setDateAdded(dateAdded);
                product.setDateUpdated(dateUpdated);

                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }

        return products;
    }
}

