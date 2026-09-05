package products;

import java.util.Iterator;
import java.util.List;

/**
 * Strategy context for reading from and writing to product files.
 * Allows dynamic selection of file handling behavior.
 */

public class ProductIOContext {
	private ProductReader readerStrategy;
    private ProductWriter writerStrategy;
    
    /**
     * Sets the strategy for reading product data.
     * @param readerStrategy the reader implementation to use
     */

    public void setReaderStrategy(ProductReader readerStrategy) {
        this.readerStrategy = readerStrategy;
    }
    
    /**
     * Sets the strategy for writing product data.
     * @param writerStrategy the writer implementation to use
     */

    public void setWriterStrategy(ProductWriter writerStrategy) {
        this.writerStrategy = writerStrategy;
    }
    
    /**
     * Reads products from a file using the configured reader.
     * @param filename the input file
     * @return the list of products read
     */

    public List<Product> read(String filename) {
        if (readerStrategy == null) {
            throw new IllegalStateException("Reader strategy not set");
        }
        return readerStrategy.readProducts(filename);
    }
    
    /**
     * Writes products to a file using the configured writer.
     * @param products the product iterator
     * @param filename the output file
     */

    public void write(Iterator<Product> products, String filename) {
        if (writerStrategy == null) {
            throw new IllegalStateException("Writer strategy not set");
        }
        writerStrategy.writeProducts(products, filename);
    }

}
