package products;

/**
 * Factory class for creating appropriate product readers based on file extension.
 */

public class ProductReaderFactory {
	
	/**
     * Returns a reader capable of reading the given file type.
     * @param filename the name of the file
     * @return a ProductReader for the file format
     * @throws IllegalArgumentException if the file type is unsupported
     */
	
	public ProductReader getProductReader(String filename) {
		String extension = getExtension(filename);
        switch (extension) {
            case "csv":
                return new CSVProductReader();
            case "json":
                return new JSONProductReader();
            default:
                throw new IllegalArgumentException("Unsupported file extension: " + extension);
        }		
	}
	
	/**
     * Extracts the file extension from a filename.
     * @param filename the filename to process
     * @return the lowercase file extension
     * @throws IllegalArgumentException if the file has no valid extension
     */
	
	private String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            throw new IllegalArgumentException("Filename must have a valid extension");
        }
        return filename.substring(dotIndex + 1).toLowerCase();
    }
}
