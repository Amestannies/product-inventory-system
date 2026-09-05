package solution;
import java.util.List;

public class ProductReaderWriterTest {
	public static void main(String[] args) {
        // File names
        String originalJson = "products.json";
        String jsonToJsonOut = "output_from_json.json";
        String jsonToCsvOut = "output_from_json.csv";

        String originalCSV = "Products.csv";
        String csvToJsonOut = "output_from_csv.json";
        String csvToCsvOut = "output_from_csv.csv";

        // Readers and writers
        ProductReader jsonReader = ProductReaderFactory.getReader("json");
        ProductReader csvReader = ProductReaderFactory.getReader("csv");

        ProductWriter jsonWriter = ProductWriterFactory.getWriter("json");
        ProductWriter csvWriter = ProductWriterFactory.getWriter("csv");

        // Step 1: Read from JSON and write to JSON and CSV
        if (jsonReader != null && jsonWriter != null && csvWriter != null) {
            List<Product> productsFromJson = jsonReader.read(originalJson);
            System.out.println("Read " + productsFromJson.size() + " products from JSON.");
            jsonWriter.write(productsFromJson.iterator(), jsonToJsonOut);
            csvWriter.write(productsFromJson.iterator(), jsonToCsvOut);
            System.out.println("Wrote to " + jsonToJsonOut + " and " + jsonToCsvOut);
        }

        // Step 2: Read from CSV and write to JSON and CSV
        if (csvReader != null && jsonWriter != null && csvWriter != null) {
            List<Product> productsFromCsv = csvReader.read(originalCSV);
            System.out.println("Read " + productsFromCsv.size() + " products from CSV.");
            jsonWriter.write(productsFromCsv.iterator(), csvToJsonOut);
            csvWriter.write(productsFromCsv.iterator(), csvToCsvOut);
            System.out.println("Wrote to " + csvToJsonOut + " and " + csvToCsvOut);
        }
    }
}
