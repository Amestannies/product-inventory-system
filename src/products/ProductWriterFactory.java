package products;

import java.util.Iterator;

/**
 * Factory class for creating appropriate product writers based on file extension.
 * Supports formats like CSV and JSON.
 */

public class ProductWriterFactory {
	
	/**
     * Returns a writer capable of saving the given file type.
     * @param filename the name of the file
     * @return a ProductWriter suitable for the file format
     * @throws IllegalArgumentException if the file extension is not supported
     */
	
	public ProductWriter getProductWriter(String filename) {
		 String extension = getExtension(filename);
	        switch (extension) {
	            case "csv":
	                return new CSVProductWriter();
	            case "json":
	                return new JSONProductWriter();
	            default:
	                throw new IllegalArgumentException("Unsupported file extension: " + extension);
	        }
	}
	
	/**
     * Extracts the file extension from the filename.
     * @param filename the name of the file
     * @return the lowercase extension (e.g., "csv", "json")
     * @throws IllegalArgumentException if the filename does not contain a valid extension
     */
               
        private String getExtension(String filename) {
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex == -1 || dotIndex == filename.length() - 1) {
                throw new IllegalArgumentException("Filename must have a valid extension");
            }
            return filename.substring(dotIndex + 1).toLowerCase();    
		
	}
	

}
