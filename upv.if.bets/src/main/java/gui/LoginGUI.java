package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import dataAccess.DataAccess;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.persistence.NoResultException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField TFKorreoa;
	private JPasswordField TFPasahitza;
	static BLFacade bl;
	
	private JLabel lblLogin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Login"));
	private JLabel lblErabiltzaile = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Korreoa"));
	private JLabel lblPasahitza = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Pasahitza"));
	private JLabel lblErregistratutaEzZaude = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Erregistratutaalzaude"));

	private JButton btnSartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Sartu"));
	private JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Erregistratu"));
	
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
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		bl = MainGUI.getBusinessLogic();
		System.out.println(bl);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TFKorreoa = new JTextField();
		TFKorreoa.setBounds(114, 90, 86, 20);
		contentPane.add(TFKorreoa);
		TFKorreoa.setColumns(10);
		
		lblErabiltzaile.setBounds(22, 93, 82, 14);
		contentPane.add(lblErabiltzaile);
		
		lblPasahitza.setBounds(22, 138, 82, 14);
		contentPane.add(lblPasahitza);
		
		TFPasahitza = new JPasswordField();
		TFPasahitza.setColumns(10);
		TFPasahitza.setBounds(114, 135, 86, 20);
		contentPane.add(TFPasahitza);
		this.getContentPane().add(getPanel());
		
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				MainGUI.getBusinessLogic().login(TFKorreoa.getText(), TFPasahitza.getText());
				}
				catch(NoResultException ex) {
				     JOptionPane.showMessageDialog(null, "Ez da horrelako erabiltzailerik existitzen");

				}
				catch(Exception e) {
//					e.printStackTrace();
				     JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Saiatu berriro");

				}
				
			}
		});
		btnSartu.setBounds(257, 112, 107, 23);
		contentPane.add(btnSartu);
		
		lblLogin.setBackground(Color.GRAY);
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Sylfaen", Font.BOLD, 16));
		lblLogin.setBounds(191, 28, 68, 20);
		contentPane.add(lblLogin);
		
		lblErregistratutaEzZaude.setBounds(22, 185, 164, 20);
		contentPane.add(lblErregistratutaEzZaude);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new RegisterGUI();

				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(257, 184, 107, 23);
		contentPane.add(btnNewButton);

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
			panel.setBounds(10, 237, 414, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		lblLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		lblErabiltzaile.setText(ResourceBundle.getBundle("Etiquetas").getString("Korreoa"));
		lblPasahitza.setText(ResourceBundle.getBundle("Etiquetas").getString("Pasahitza"));
		lblErregistratutaEzZaude.setText(ResourceBundle.getBundle("Etiquetas").getString("Erregistratutaalzaude"));
		btnSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("Sartu"));
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Erregistratu"));
	}
}
