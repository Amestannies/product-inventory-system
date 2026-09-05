package solution;
/**
 * Factory class that creates a ProductReader instance based on file extension.
 */
public class ProductReaderFactory {
    /**
     * Returns an appropriate ProductReader based on the file extension.
     * @param filename the name of the file to read
     * @return a ProductReader instance or null if the extension is unsupported
     */
    public static ProductReader getReader(String filename) {
    	try {
    		  if (filename == null || !filename.contains(".")) {
                  throw new IllegalArgumentException("Filename must have an extension");
              }
              String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
            if (extension.equals("csv")) {
                return new CSVProductReader();
            } else if (extension.equals("json")) {
                return new JsonProductReader();
            } else {
                throw new IllegalArgumentException("Unsupported file type: " + extension);
            }
        } catch (Exception e) {
            System.err.println("Error creating ProductWriter: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
