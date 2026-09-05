package solution;
import java.util.List;

public class ProductReaderDemo {

	public static void main(String[] args) {
		   String csvFile = "Products.csv";
	        String jsonFile = "products.json";

	        ProductReader csvReader = ProductReaderFactory.getReader("Products.csv");
	        if (csvReader != null) {
	            List<Product> csvProducts = csvReader.read(csvFile);
	            System.out.println("CSV Products:");
	            for (Product p : csvProducts) {
	                System.out.println(p);
	            }
	        }

	        ProductReader jsonReader = ProductReaderFactory.getReader("products.json");
	        if (jsonReader != null) {
	            List<Product> jsonProducts = jsonReader.read(jsonFile);
	            System.out.println("\nJSON Products:");
	            for (Product p : jsonProducts) {
	                System.out.println(p);
	            }
	        }
	}

}
