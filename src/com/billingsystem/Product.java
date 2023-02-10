import java.util.List;

public interface Product {
	
	public void saveProduct(ProductFormDAO productFormDAO);
	public List<ProductFormDAO>getProduct();
	public void editProduct(ProductFormDAO productFormDAO);
	public void deleteProduct(int pId);
	
	public void updateProduct(ProductFormDAO productFormDAO);
}
