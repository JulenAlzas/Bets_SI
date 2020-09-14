package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataAccess.DataAccess;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.persistence.RollbackException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisteradminGUI extends JFrame {

	private JPanel contentPane;
	private JTextField TFIzena;
	private JTextField TFAbizena;
	private JTextField TFkorreoa;
	private JTextField TFTelefonoa;
	private JTextField TFAdina;
	private JPasswordField TFPasahitza;
	private JPasswordField TFPasahitza2;
	
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton btnErregistratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Erregistratu"));

	private JLabel lblIzena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Izena"));
	private JLabel lblAbizena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Abizena"));
	private JLabel lblKorreoa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Korreoa"));
	private JLabel lblPasahitza = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Pasahitza"));
	private JLabel lblPasahitzaBerretsi = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PasahitzaBerretsi"));
	private JLabel lblTelefonoa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Telefonoa"));
	private JLabel lblAdina = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Adina"));

	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static boolean isNumeric(String katea){
		try {
			Integer.parseInt(katea);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 * Create the frame.
	 */
	public RegisteradminGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Erregistratu"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.getContentPane().add(getPanel());


		TFIzena = new JTextField();
		TFIzena.setBounds(76, 31, 86, 20);
		contentPane.add(TFIzena);
		TFIzena.setColumns(10);

		lblIzena.setBounds(10, 34, 56, 14);
		contentPane.add(lblIzena);

		
		lblAbizena.setBounds(10, 68, 56, 14);
		contentPane.add(lblAbizena);

		TFAbizena = new JTextField();
		TFAbizena.setColumns(10);
		TFAbizena.setBounds(76, 65, 86, 20);
		contentPane.add(TFAbizena);

		lblKorreoa.setBounds(179, 99, 66, 14);
		contentPane.add(lblKorreoa);

		TFkorreoa = new JTextField();
		TFkorreoa.setColumns(10);
		TFkorreoa.setBounds(302, 96, 95, 20);
		contentPane.add(TFkorreoa);

		lblPasahitza.setBounds(179, 34, 86, 14);
		contentPane.add(lblPasahitza);

		lblPasahitzaBerretsi.setBounds(179, 68, 113, 14);
		contentPane.add(lblPasahitzaBerretsi);

		lblTelefonoa.setBounds(10, 136, 66, 14);
		contentPane.add(lblTelefonoa);

		TFTelefonoa = new JTextField();
		TFTelefonoa.setColumns(10);
		TFTelefonoa.setBounds(76, 133, 86, 20);
		contentPane.add(TFTelefonoa);

		lblAdina.setBounds(10, 99, 46, 14);
		contentPane.add(lblAdina);

		TFAdina = new JTextField();
		TFAdina.setColumns(10);
		TFAdina.setBounds(76, 96, 86, 20);
		contentPane.add(TFAdina);

		TFPasahitza = new JPasswordField();
		TFPasahitza.setBounds(302, 31, 95, 20);
		contentPane.add(TFPasahitza);

		TFPasahitza2 = new JPasswordField();
		TFPasahitza2.setBounds(302, 65, 95, 20);
		contentPane.add(TFPasahitza2);

		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
						"[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
				String telefonoPattern = "^[0-9]{9}$";
				Pattern pattern = Pattern.compile(emailPattern);
				String email = TFkorreoa.getText();
				if (email != null) {
					Matcher matcher = pattern.matcher(email);
					if (!matcher.matches()) {
						JOptionPane.showMessageDialog(null, "Korreoak formatu okerra");
						return;
					}
				}
				if(isNumeric(TFIzena.getText())) {
					JOptionPane.showMessageDialog(null, "Izenak ezin du zenbakirik izan");
					return;
				}
				if(isNumeric(TFAbizena.getText())) {
					JOptionPane.showMessageDialog(null, "Abizenak ezin du zenbakirik izan");
					return;
				}
				if(!isNumeric(TFAdina.getText())) {
					JOptionPane.showMessageDialog(null, "Adinak zenbakia izan behar du");
					return;
				}

				if(Integer.parseInt(TFAdina.getText())<18) {
					JOptionPane.showMessageDialog(null, "Adina 18 edo handiagoa izan behar du");
					return;
				}


				if(!isNumeric(TFTelefonoa.getText())) {
					JOptionPane.showMessageDialog(null, "Telefonoak digituak izan behar ditu");
					return;
				}
				if(TFTelefonoa.getText().length()!=9){
					JOptionPane.showMessageDialog(null, "Telefonoak 9 digitu eduki behar ditu");
					return;
				}
				else {
					try {
						MainGUI.getBusinessLogic().createAdmin(TFIzena.getText(),TFAbizena.getText(),TFkorreoa.getText(),
								TFPasahitza.getText(),TFPasahitza2.getText(), Integer.parseInt(TFTelefonoa.getText()), 
								Integer.parseInt(TFAdina.getText()));
					}catch (RollbackException ex1) {
						JOptionPane.showMessageDialog(null, "Korreo horrek kontua du!!");

					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Arazo bat izan dugu erregistratzean. Saiatu geroago.");
					}		

					JFrame log = new LoginGUI();
					log.setVisible(true);
				}
			}
		});
		btnErregistratu.setBounds(179, 132, 218, 23);
		contentPane.add(btnErregistratu);
		
		jButtonClose.setBounds(179, 176, 95, 33);
		contentPane.add(jButtonClose);		
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		this.getContentPane().add(jButtonClose, null);
	}
	
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 223, 434, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Erregistratu"));
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnErregistratu.setText(ResourceBundle.getBundle("Etiquetas").getString("Erregistratu"));
		lblIzena.setText(ResourceBundle.getBundle("Etiquetas").getString("Izena"));
		lblAbizena.setText(ResourceBundle.getBundle("Etiquetas").getString("Abizena"));
		lblKorreoa.setText(ResourceBundle.getBundle("Etiquetas").getString("Korreoa"));
		lblPasahitza.setText(ResourceBundle.getBundle("Etiquetas").getString("Pasahitza"));
		lblPasahitzaBerretsi.setText(ResourceBundle.getBundle("Etiquetas").getString("PasahitzaBerretsi"));
		lblTelefonoa.setText(ResourceBundle.getBundle("Etiquetas").getString("Telefonoa"));
		lblAdina.setText(ResourceBundle.getBundle("Etiquetas").getString("Adina"));


	}
	
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}