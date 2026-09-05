package solution;
import java.util.Objects;
import java.time.LocalDateTime;

/**
 * Inventory record for a product
 * The record contains the name of the product, the id, the category, the quantity in stock,
 * the reorder level, the date/time the record was first created and 
 * the date/time that it was last updated
 */
public class Product {
    private String name;
    private String id;
    private String category;
    private String supplier;
    private int quantityInStock;
    private int reorderLevel;
    private LocalDateTime dateAdded;
    private LocalDateTime dateUpdated;

   

    public Product(String id, String name, String category, String supplier, int quantityInStock, int reorderLevel) {
		this.name = name;
		this.id = id;
		this.category = category;
		this.supplier = supplier;
		this.quantityInStock = quantityInStock;
		this.reorderLevel = reorderLevel;
		this.dateAdded = LocalDateTime.now();
		this.dateUpdated = LocalDateTime.now();
		
	}

	public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getSupplier() {
        return supplier;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
     

    public LocalDateTime getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(supplier, other.supplier);
	}

    @Override
	public int hashCode() {
		return Objects.hash(category, id, name, supplier);
	}

	@Override
	public String toString() {
		String ret = "ID : " + getId() + " name: " + getName() + " category: " + getCategory() + "\n";
		ret = ret + "Supplier: " + getSupplier() + " quantity in stock: " + getQuantityInStock() + " reorder level: " + getReorderLevel() + "\n";
		ret = ret + "Date added: " + getDateAdded() + " Date Updated: " + getDateUpdated() + "\n";
        return ret;
	}
    
    
}

