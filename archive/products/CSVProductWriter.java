package products;

import java.io.FileWriter;
import java.util.Iterator;

/**
 * Writes products to a CSV file.
 */

public class CSVProductWriter implements ProductWriter {
	
	/**
     * Writes products to the specified CSV file.
     * @param products the products to write
     * @param filename the output file
     */
	
    @Override
    public void writeProducts(Iterator<Product> products, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("ID,Name,Category,Supplier,QuantityInStock,ReorderLevel,DateAdded,DateUpdated\n");

            while (products.hasNext()) {
                Product p = products.next();
                writer.write(String.format("%s,%s,%s,%s,%d,%d,%s,%s\n",
                        p.getId(), p.getName(), p.getCategory(), p.getSupplier(),
                        p.getQuantityInStock(), p.getReorderLevel(),
                        p.getDateAdded(), p.getDateUpdated()));
            }
        } catch (Exception e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}
