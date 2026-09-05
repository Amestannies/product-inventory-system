package solution;
import java.io.IOException;
import java.util.List;

/**
 * Represents a strategy interface for reading product data from a file.
 */
interface ProductReader {
    List<Product> read(String filepath);
}