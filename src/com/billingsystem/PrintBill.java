import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrintBill extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField productField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextField amountField;
	private JTextField cnameField;
	private JTable table_1;
	private JTextField total;
	public JTextArea textArea;
	
	Product product=new ProductImpl();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintBill frame = new PrintBill();
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
	public PrintBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 986);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel productLabel = new JLabel("Product");
		productLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		productLabel.setBounds(30, 25, 79, 29);
		contentPane.add(productLabel);
		
		productField = new JTextField();
		productField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
				int row=table.getSelectedRow();
				
				Object productName=tableModel.getValueAt(row, 1);
				Object price=tableModel.getValueAt(row, 3);
				
				productField.setText(productName.toString());
				priceField.setText(price.toString());
			}
		});
		productField.setBounds(30, 61, 96, 29);
		contentPane.add(productField);
		productField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantity.setBounds(158, 25, 79, 29);
		contentPane.add(lblQuantity);
		
		quantityField = new JTextField();
		quantityField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int qty=Integer.parseInt(quantityField.getText());
				int price=Integer.parseInt(priceField.getText());
				
				int total=qty*price;
				
				amountField.setText(String.valueOf(total));
				
				
				DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
				int row=table.getSelectedRow();
				Object quantity=tableModel.getValueAt(row, 2);
				Object id=tableModel.getValueAt(row, 0);
				quantity=Integer.valueOf((String) tableModel.getValueAt(row, 2))-Integer.parseInt(quantityField.getText());
				
				
				ProductFormDAO productFormDAO=new ProductFormDAO();
				int pid=Integer.parseInt(String.valueOf(id));
				productFormDAO.setpId(pid);
				productFormDAO.setQuantity(String.valueOf(quantity));
				product.updateProduct(productFormDAO);
			}
		});
		
		quantityField.setColumns(10);
		quantityField.setBounds(158, 61, 96, 29);
		contentPane.add(quantityField);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(297, 25, 79, 29);
		contentPane.add(lblPrice);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(297, 61, 96, 29);
		contentPane.add(priceField);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAmount.setBounds(420, 25, 79, 29);
		contentPane.add(lblAmount);
		
		amountField = new JTextField();
		amountField.setColumns(10);
		amountField.setBounds(420, 61, 96, 29);
		contentPane.add(amountField);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				model=(DefaultTableModel)table_1.getModel();
				model.addRow(new Object[] {
						productField.getText(),
						quantityField.getText(),
						priceField.getText(),
						amountField.getText(),
						
				});
				
				
				int sum=0;
				
				for(int i=0;i<table_1.getRowCount();i++)
				{
					sum=sum+Integer.parseInt(table_1.getValueAt(i, 3).toString());
				}
				
				total.setText(Integer.toString(sum));
				loadTableData();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(420, 111, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomerName.setBounds(594, 10, 127, 29);
		contentPane.add(lblCustomerName);
		
		cnameField = new JTextField();
		cnameField.setColumns(10);
		cnameField.setBounds(745, 10, 157, 29);
		contentPane.add(cnameField);
		
		
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(579, 68, 375, 341);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product", "Quantity", "Price", "Amount"
			}
		));
		
		JButton btnPrintBill = new JButton("Print Bill");
		btnPrintBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel tableModel= new DefaultTableModel();
				tableModel=(DefaultTableModel) table_1.getModel();
				
				textArea.setText(textArea.getText()+"*****************************************************************\n");
				textArea.setText(textArea.getText()+"************** Nawaranga Restaurant *******************************\n");
				textArea.setText(textArea.getText()+"*****************************************************************\n");
				String cName=cnameField.getText();
				
				textArea.setText(textArea.getText()+"Customer Name: "+cName+"\n");
				textArea.setText(textArea.getText()+"*****************************************************************\n");
				textArea.setText(textArea.getText()+"Product"+"\t"+"Quantity"+"\t"+"Price"+"\t"+"Amount"+"\n");
				
				for(int i=0;i<tableModel.getRowCount();i++)
				{
					String pname=(String)tableModel.getValueAt(i, 0);
					String pqty=(String)tableModel.getValueAt(i, 1);
					String pprice=(String)tableModel.getValueAt(i, 2);
					String pamount=(String)tableModel.getValueAt(i, 3);
					
					textArea.setText(textArea.getText()+pname+"\t"+pqty+"\t"+pprice+"\t"+pamount+"\n");
					textArea.setText(textArea.getText()+"*****************************************************************\n");
				}
				
				String ptotal=total.getText();
				textArea.setText(textArea.getText()+"\t"+"\t"+"\t"+"Total:"+ptotal+"\n");
				textArea.setText(textArea.getText()+"*****************************************************************\n");
				textArea.setText(textArea.getText()+"*************Thank you! Please Visit Again************************\n");
			}
		});
		btnPrintBill.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrintBill.setBounds(729, 508, 127, 21);
		contentPane.add(btnPrintBill);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(767, 431, 57, 29);
		contentPane.add(lblTotal);
		
		total = new JTextField();
		total.setColumns(10);
		total.setBounds(834, 433, 96, 29);
		contentPane.add(total);
		
	    textArea = new JTextArea();
		textArea.setBounds(557, 560, 373, 259);
		contentPane.add(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 194, 469, 575);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PId", "Product", "Quantity", "Price"
			}
			
		));
		
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
