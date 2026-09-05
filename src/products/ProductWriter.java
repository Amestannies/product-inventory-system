package products;

import java.util.Iterator;

/**
 * Strategy interface for writing products to a file.
 * Implementations determine the format (e.g. CSV, JSON) used for output.
 */

public interface ProductWriter {
	
	/**
     * Writes the provided products to a file in a specific format.
     * @param products an iterator over the products to be written
     * @param filename the name of the file to write to
     */
	
    void writeProducts(Iterator<Product> products, String filename);
}

