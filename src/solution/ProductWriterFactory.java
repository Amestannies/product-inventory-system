package solution;
/**
 * Factory class that creates a ProductWriter instance based on file extension.
 */
class ProductWriterFactory {
	
	  /**
     * Returns an appropriate ProductWriter based on the file extension.
     * @param filename the name of the file to write
     * @return a ProductWriter instance or null if the extension is unsupported
     */
    public static ProductWriter getWriter(String filename) {
    	
        try {
        	 if (filename == null || !filename.contains(".")) {
                 throw new IllegalArgumentException("Filename must have an extension");
             }
             String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
            if (extension.equalsIgnoreCase("csv")) {
                return new CSVProductWriter();
            } else if (extension.equalsIgnoreCase("json")) {
                return new JsonProductWriter();
            } else {
                throw new IllegalArgumentException("Unsupported file type: " + extension);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}