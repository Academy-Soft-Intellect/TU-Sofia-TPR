import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


@SuppressWarnings("serial")
public class CreateItem extends JDialog {

	/**
	 * Create the dialog.
	 */
	
	private Item item;
	
	public Item getItem() {
		return item;
	}

	public CreateItem() {
		item = new Item();
		setTitle("ADD NEW ITEM");
		setBounds(100, 100, 450, 260);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(22, 18, 85, 14);
		getContentPane().add(lblName);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBarcode.setBounds(22, 60, 85, 14);
		getContentPane().add(lblBarcode);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrice.setBounds(22, 99, 71, 14);
		getContentPane().add(lblPrice);
		
		JLabel lblCount = new JLabel("Count:");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCount.setBounds(22, 138, 71, 14);
		getContentPane().add(lblCount);
		
		JFormattedTextField formattedNameTextField = new JFormattedTextField();
		formattedNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		formattedNameTextField.setBounds(135, 18, 273, 28);
		getContentPane().add(formattedNameTextField);
		
		JFormattedTextField formattedBarcodeTextField = new JFormattedTextField("{0,number,#}");
		formattedBarcodeTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		formattedBarcodeTextField.setBounds(135, 53, 273, 28);
		formattedBarcodeTextField.setValue(new Long(0));
		getContentPane().add(formattedBarcodeTextField);
		
		JFormattedTextField formattedPriceTextField = new JFormattedTextField();
		formattedPriceTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		formattedPriceTextField.setBounds(135, 92, 272, 28);
		formattedPriceTextField.setValue(new Double(0.0d));
		getContentPane().add(formattedPriceTextField);
		
		JFormattedTextField formattedCountTextField = new JFormattedTextField();
		formattedCountTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		formattedCountTextField.setBounds(135, 131, 272, 28);
		formattedCountTextField.setValue(new Integer(0));
		getContentPane().add(formattedCountTextField);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
				
				String name = formattedNameTextField.getText();
				long barcode = 0;
				double price = 0;
				int count = 0;
				try {
					barcode = numberFormat.parse(formattedBarcodeTextField.getText()).longValue();
					price = numberFormat.parse(formattedPriceTextField.getText()).doubleValue();
					count = numberFormat.parse(formattedCountTextField.getText()).intValue();
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Error parsing!");
					e.printStackTrace();
				}
				
				
				if (formattedNameTextField.getText() == null || formattedNameTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a name!");
					
				} else if (formattedBarcodeTextField.getText() == null || 
						formattedBarcodeTextField.getText().equals("") ||
						barcode <= 0) {
							JOptionPane.showMessageDialog(null, "Please enter a barcode greater than 0!");	
				} else if (formattedPriceTextField.getText() == null || 
						formattedPriceTextField.getText().equals("") ||
						price < 0)
						 {
					JOptionPane.showMessageDialog(null, "Please enter a valid and positive price!");
					
				} else if (formattedCountTextField.getText() == null || 
						formattedCountTextField.getText().equals("") ||
						count < 0) {
					JOptionPane.showMessageDialog(null, "Please enter a barcode greater than 0!");
					
				} else {
					item.setName(name);
					item.setBarcode(barcode);
					item.setPrice(price);
					item.setCount(count);
					setVisible(false);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(22, 176, 180, 38);
		getContentPane().add(btnNewButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				item = null;
				setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBounds(227, 176, 180, 38);
		getContentPane().add(btnClose);
		


	}
}
