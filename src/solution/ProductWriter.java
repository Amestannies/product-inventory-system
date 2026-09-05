package solution;
import java.util.Iterator;

/**
 * Represents a strategy interface for writing product data to a file.
 */
interface ProductWriter {
    void write(Iterator<Product> products, String filename);
}