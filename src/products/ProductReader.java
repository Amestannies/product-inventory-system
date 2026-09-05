package products;

import java.util.List;

public interface ProductReader {
	
	List<Product> readProducts(String filename);
	
}
