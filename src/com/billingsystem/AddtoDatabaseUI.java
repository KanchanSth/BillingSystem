import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AddtoDatabaseUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField productnameField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTable table;
	public JLabel lblPrice;
	public JLabel pIdLabel;
	
	Product product= new ProductImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddtoDatabaseUI frame = new AddtoDatabaseUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddtoDatabaseUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(30, 59, 105, 29);
		contentPane.add(lblNewLabel);
		
		productnameField = new JTextField();
		productnameField.setBounds(30, 98, 110, 29);
		contentPane.add(productnameField);
		productnameField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantity.setBounds(30, 160, 105, 29);
		contentPane.add(lblQuantity);
		
		quantityField = new JTextField();
		quantityField.setColumns(10);
		quantityField.setBounds(30, 199, 110, 29);
		contentPane.add(quantityField);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(30, 258, 105, 29);
		contentPane.add(lblPrice);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(30, 297, 110, 29);
		contentPane.add(priceField);
		
		JButton addButton = new JButton("Add");
		addButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName=productnameField.getText();
				String quantity=quantityField.getText();
				String price=priceField.getText();
				String pId=pIdLabel.getText();
				
				
				ProductFormDAO productFormDAO=new ProductFormDAO();
				productFormDAO.setProduct(productName);
				productFormDAO.setQuantity(quantity);
				productFormDAO.setPrice(price);
				
				if(pIdLabel==null||pId.isEmpty())
				{
				product.saveProduct(productFormDAO);
				
				}
				
				else {
					productFormDAO.setpId(Integer.parseInt(pId));
					product.editProduct(productFormDAO);
				}
			   loadTableData();
				
			}
		});
		addButton.setBounds(227, 24, 85, 21);
		contentPane.add(addButton);
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
				int row=table.getSelectedRow();
				Object pId=tableModel.getValueAt(row, 0);
				Object productName=tableModel.getValueAt(row, 1);
				Object price=tableModel.getValueAt(row, 2);
				Object quantity=tableModel.getValueAt(row, 3);
				
				pIdLabel.setText(pId.toString());
				productnameField.setText(productName.toString());
				quantityField.setText(quantity.toString());
				priceField.setText(price.toString());
				
			}
		});
		editButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		editButton.setBounds(355, 24, 85, 21);
		contentPane.add(editButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
				int row=table.getSelectedRow();
				Object pid=tableModel.getValueAt(row, 0);
				product.deleteProduct(Integer.parseInt(pid.toString()));
				loadTableData();
			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteButton.setBounds(467, 24, 85, 21);
		contentPane.add(deleteButton);
		String[] columnName= {"Pid","Product","Price","Quantity"};
		DefaultTableModel tableModel=new DefaultTableModel(columnName,0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 59, 456, 372);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		 pIdLabel = new JLabel("");
		pIdLabel.setBounds(35, 26, 73, 21);
		contentPane.add(pIdLabel);
		
		loadTableData();
		
	}
	
	public void loadTableData()
	{
		List<ProductFormDAO>productList=product.getProduct();
		DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for(ProductFormDAO pro:productList)
		{
			tableModel.addRow(new Object[]
					{
							
							pro.getpId(),pro.getProduct(),pro.getQuantity(),pro.getPrice()
					});
			
		}
	}
	
}
