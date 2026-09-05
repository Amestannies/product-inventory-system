package products;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CSVProductWriterTest {

	private Product product;
    private CSVProductWriter writer;

    @BeforeEach
    void setUp() {
        product = new Product("P001", "Widget", "Tools", "Acme", 100, 20);
        product.setDateAdded(LocalDateTime.parse("2024-01-01T10:00:00"));
        product.setDateUpdated(LocalDateTime.parse("2024-01-02T10:00:00"));
        writer = new CSVProductWriter();
    }

    @Test
    void testWriteProducts() throws Exception {
        File file = new File("test_output.csv");
        writer.writeProducts(Collections.singletonList(product).iterator(), file.getPath());

        assertTrue(file.exists());
        String content = Files.readString(file.toPath());
        assertTrue(content.contains("P001,Widget,Tools,Acme,100,20"));
    }
}
