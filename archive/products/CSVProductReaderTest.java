package products;

import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CSVProductReaderTest {

	private CSVProductReader reader;

    @BeforeEach
    void setUp() {
        reader = new CSVProductReader();
    }

    @Test
    void testReadProducts() {
        List<Product> products = reader.readProducts("test_products.csv");

        assertEquals(1, products.size());
        Product p = products.get(0);
        assertEquals("P001", p.getId());
        assertEquals("Widget", p.getName());
        assertEquals("Tools", p.getCategory());
        assertEquals("Acme", p.getSupplier());
        assertEquals(100, p.getQuantityInStock());
        assertEquals(20, p.getReorderLevel());
    }

}
