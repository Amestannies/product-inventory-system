package products;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JSONProductReaderTest {

	private JSONProductReader reader;

    @BeforeEach
    void setUp() {
        reader = new JSONProductReader();
    }

    @Test
    void testReadProducts() {
        List<Product> products = reader.readProducts("test_products.json");

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
