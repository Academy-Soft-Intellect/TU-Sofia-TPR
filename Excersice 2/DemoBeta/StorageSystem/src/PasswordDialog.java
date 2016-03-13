import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class PasswordDialog extends JDialog {
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	private boolean isPasswordEntered = false;
	
	public boolean getIsPasswordEntered() {
		return isPasswordEntered;
	}

	public PasswordDialog() {

		setTitle("PASSWORD");
		setBounds(100, 100, 250, 150);
		getContentPane().setLayout(null);
		JButton btnNewButton = new JButton("OK");
		getRootPane().setDefaultButton(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					char[] password = passwordField.getPassword();
					String passwordWord = new String(password);
					if (passwordWord.contains("123123")) {
						isPasswordEntered = true;
						setVisible(false);
						
					} else{
						JOptionPane.showMessageDialog(null, "Wrong password. Try again.");
					}
			}
		});
		btnNewButton.setBounds(10, 61, 214, 39);
		getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(10, 11, 214, 39);
		getContentPane().add(passwordField);

	}

}
