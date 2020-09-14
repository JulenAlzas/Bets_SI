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

public class RegisterGUI extends JFrame {

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
	public RegisterGUI() {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		TFIzena = new JTextField();
		TFIzena.setBounds(66, 31, 86, 20);
		contentPane.add(TFIzena);
		TFIzena.setColumns(10);

		lblIzena.setBounds(10, 34, 46, 14);
		contentPane.add(lblIzena);

		lblAbizena.setBounds(162, 34, 56, 14);
		contentPane.add(lblAbizena);

		TFAbizena = new JTextField();
		TFAbizena.setColumns(10);
		TFAbizena.setBounds(218, 31, 86, 20);
		contentPane.add(TFAbizena);

		lblKorreoa.setBounds(10, 65, 56, 14);
		contentPane.add(lblKorreoa);

		TFkorreoa = new JTextField();
		TFkorreoa.setColumns(10);
		TFkorreoa.setBounds(66, 62, 86, 20);
		contentPane.add(TFkorreoa);

		lblPasahitza.setBounds(10, 93, 66, 14);
		contentPane.add(lblPasahitza);

		lblPasahitzaBerretsi.setBounds(169, 93, 110, 14);
		contentPane.add(lblPasahitzaBerretsi);

		lblTelefonoa.setBounds(10, 131, 66, 14);
		contentPane.add(lblTelefonoa);

		TFTelefonoa = new JTextField();
		TFTelefonoa.setColumns(10);
		TFTelefonoa.setBounds(66, 128, 86, 20);
		contentPane.add(TFTelefonoa);

		lblAdina.setBounds(172, 65, 46, 14);
		contentPane.add(lblAdina);

		TFAdina = new JTextField();
		TFAdina.setColumns(10);
		TFAdina.setBounds(218, 62, 86, 20);
		contentPane.add(TFAdina);

		TFPasahitza = new JPasswordField();
		TFPasahitza.setBounds(66, 93, 95, 14);
		contentPane.add(TFPasahitza);

		TFPasahitza2 = new JPasswordField();
		TFPasahitza2.setBounds(281, 93, 95, 14);
		contentPane.add(TFPasahitza2);
		
		this.getContentPane().add(getPanel());


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
				if(!TFPasahitza.getText().equals(TFPasahitza2.getText())){
					JOptionPane.showMessageDialog(null, "Pasahitzak berdinak izan behar dute");
					return;
				}
				else {
					try {
						MainGUI.getBusinessLogic().register(TFIzena.getText(),TFAbizena.getText(),TFkorreoa.getText(),TFPasahitza.getText(),TFPasahitza2.getText(), Integer.parseInt(TFTelefonoa.getText()), Integer.parseInt(TFAdina.getText()));
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
		btnErregistratu.setBounds(169, 170, 105, 23);
		contentPane.add(btnErregistratu);

		jButtonClose.setBounds(178, 227, 89, 23);
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
			panel.setBounds(0, 250, 424, 33);
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