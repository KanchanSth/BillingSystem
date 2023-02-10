import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements Product{
	
	PreparedStatement ps;

	@Override
	public void saveProduct(ProductFormDAO productFormDAO) {
		
		String sql="INSERT INTO productinventory(productname, quantity,price) VALUES(?,?,?)";
		try {
			ps=DataConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, productFormDAO.getProduct());
			ps.setString(2, productFormDAO.getQuantity());
			ps.setString(3, productFormDAO.getPrice());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ProductFormDAO> getProduct() {
		List<ProductFormDAO>productList=new ArrayList<>();
		String sql="SELECT * FROM productinventory";
		
		
			try {
				ps=DataConnectivity.getConnection().prepareStatement(sql);
				ResultSet rs= ps.executeQuery();
			    while(rs.next())	
			    {
			    	ProductFormDAO productFormDAO=new ProductFormDAO();
			    	productFormDAO.setpId(rs.getInt("pId"));
			    	productFormDAO.setProduct(rs.getString("productname"));
			    	productFormDAO.setQuantity(rs.getString("quantity"));
			    	productFormDAO.setPrice(rs.getString("price"));
			    	productList.add(productFormDAO);
			} 
			}
			
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
		
		// TODO Auto-generated method stub
		return productList;
	
			}

	@Override
	public void editProduct(ProductFormDAO productFormDAO) {
		
		String sql="UPDATE productinventory SET productname=?, quantity=?, price=? WHERE pid=? ";
		
		try {
			ps=DataConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, productFormDAO.getProduct());
			ps.setString(2, productFormDAO.getQuantity());
			ps.setString(3, productFormDAO.getPrice());
			ps.setInt(4, productFormDAO.getpId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int pId) {
		
		String sql="DELETE FROM productinventory WHERE pid=?";
		
		try {
			ps=DataConnectivity.getConnection().prepareStatement(sql);
			ps.setInt(1, pId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProduct(ProductFormDAO productFormDAO) {
		
		String sql="UPDATE productinventory SET quantity=? WHERE pid=? ";
		
		try {
			ps=DataConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, productFormDAO.getQuantity());
			ps.setInt(2, productFormDAO.getpId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}
}
