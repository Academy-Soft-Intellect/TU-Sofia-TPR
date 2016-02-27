import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class StorageSystemUI {

	private JFrame frmStorageSystem;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordDialog dialog = new PasswordDialog();
					dialog.setModal(true);
					dialog.setVisible(true);
					if (dialog.getIsPasswordEntered()) {
						StorageSystemUI window = new StorageSystemUI();
						window.frmStorageSystem.setVisible(true);
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StorageSystemUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStorageSystem = new JFrame();
		frmStorageSystem.setTitle("STORAGE SYSTEM");
		frmStorageSystem.setBounds(100, 100, 750, 430);
		frmStorageSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStorageSystem.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Add New Item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateItem dialog = new CreateItem();
				dialog.setModal(true);
				dialog.setVisible(true);
				Item item = dialog.getItem();
				if (item== null) {

				} else {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[] { item.getName(), item.getBarcode(),
							item.getPrice(), item.getCount(), item.getTotalPrice() });
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(10, 337, 150, 45);
		frmStorageSystem.getContentPane().add(btnNewButton);

		Object[][] rowData = new Object[0][5];
		Object[] columnNames = { "Name", "Barcode", "Price", "Count", "Total Price" };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 714, 312);
		frmStorageSystem.getContentPane().add(scrollPane);

		table = new JTable(new DefaultTableModel(rowData, columnNames));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setRowSelectionAllowed(true);
		scrollPane.setViewportView(table);
		
		JButton btnEditItem = new JButton("Edit Item");
		btnEditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int[] selectedRow = table.getSelectedRows();
				 if (selectedRow.length == 1){
					String name = (String) table.getValueAt(selectedRow[0], 0);
					long barcode = (long) table.getValueAt(selectedRow[0], 1);
					double price = (double) table.getValueAt(selectedRow[0], 2);
					int count = (int) table.getValueAt(selectedRow[0], 3);
					Item item = new Item(name, barcode, price, count);
					EditItem dialog = new EditItem(item);
					dialog.setModal(true);
					dialog.setVisible(true);
					item = dialog.getMainItem();
					table.setValueAt(item.getName(), selectedRow[0], 0);
					table.setValueAt(item.getBarcode(), selectedRow[0], 1);
					table.setValueAt(item.getPrice(), selectedRow[0], 2);
					table.setValueAt(item.getCount(), selectedRow[0], 3);
					table.setValueAt(item.getTotalPrice(), selectedRow[0], 4);

				 }
			
			}
		});
		btnEditItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEditItem.setBounds(172, 337, 150, 45);
		frmStorageSystem.getContentPane().add(btnEditItem);
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel model = (DefaultTableModel) table.getModel();
				   int[] rows = table.getSelectedRows();
				   if(rows.length == 1){
					   model.removeRow(rows[0]);
				   }
			}
		});
		btnDeleteItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteItem.setBounds(332, 337, 150, 45);
		frmStorageSystem.getContentPane().add(btnDeleteItem);
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "sss"));
					fileChooser.showDialog(null, "Choose...");
					
					File file = fileChooser.getSelectedFile();
					
					List<String> fileContent = Files.readAllLines(file.toPath());
					
					for (String line : fileContent) {
						
						NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
						String[] row = line.split(";");
						String name = row[0];
						long barcode = 0;
						double price = 0;
						int count = 0;
						try {
							barcode = numberFormat.parse(row[1]).longValue();
							price = numberFormat.parse(row[2]).doubleValue();
							count = numberFormat.parse(row[3]).intValue();
						} catch (ParseException ex) {
							JOptionPane.showMessageDialog(null, "Error parsing!");
							ex.printStackTrace();
						}
						Item item = new Item(name,barcode, price, count);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.addRow(new Object[] { item.getName(), item.getBarcode(),
								item.getPrice(), item.getCount(), item.getTotalPrice() });

					
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error while loading file!");
				}	
				
				JOptionPane.showMessageDialog(null, "Successfully loaded file!");
			}
		});
		btnImport.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnImport.setBounds(498, 337, 108, 45);
		frmStorageSystem.getContentPane().add(btnImport);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> list = new ArrayList<String>();

		        int rowCount = table.getRowCount();
		        		        
		        for (int i = 0; i < rowCount; i++) {
		        		String line = String.format("%s;%s;%s;%s", table.getValueAt(i, 0),
						        								   table.getValueAt(i, 1), 
						        								   table.getValueAt(i, 2),
						        								   table.getValueAt(i, 3));
				        list.add(line);
		        }
		        
		    	try {
		    		JFileChooser fileChooser = new JFileChooser();
		    		fileChooser.setFileFilter(new FileNameExtensionFilter("Storage System File", "sss"));
		    		fileChooser.showDialog(null, "Choose...");
		    		
		    		File file = fileChooser.getSelectedFile();
		    		if (file == null){
		    			return;
		    		}
		    		String path = file.getPath();
		    		if (path == null){
		    			return;
		    		}
		    		path = path.endsWith(".sss") ? path : path + ".sss";
		    		
		    		FileWriter fileStream = new FileWriter(path);
		    		BufferedWriter writer = new BufferedWriter(fileStream);

		    		
		    		for (String line : list) {
		    			writer.append(line);
		    			writer.newLine();
		    		}
		    		
		    		writer.close();
		    		
		    	} catch (Exception ex) {
		    		JOptionPane.showMessageDialog(null, "Error while saving file!");
		    	}
		    	JOptionPane.showMessageDialog(null, "Successully saved file!");

			}
		});
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExport.setBounds(616, 337, 108, 45);
		frmStorageSystem.getContentPane().add(btnExport);
	

	}
}
