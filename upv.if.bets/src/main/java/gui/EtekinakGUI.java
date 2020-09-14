package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.persistence.NoResultException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class EtekinakGUI extends JFrame {

	private JPanel contentPane;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel lblEtekinak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Etekinak"));
	private JLabel lblEtekinakHauekIzan = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Etekin_hauek"));
	
	private JPanel panel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtekinakGUI frame = new EtekinakGUI();
					
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
	public EtekinakGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EtekinakADMIN"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEtekinak.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEtekinak.setBounds(10, 11, 69, 14);
		contentPane.add(lblEtekinak);
		contentPane.add(getPanel());

		lblEtekinakHauekIzan.setBounds(36, 47, 197, 14);
		contentPane.add(lblEtekinakHauekIzan);

		JLabel lbletekina = new JLabel("");
		lbletekina.setBounds(243, 47, 105, 14);
		contentPane.add(lbletekina);
		
		try {	
			Double etekina = MainGUI.getBusinessLogic().etekinakLortu();
			System.out.println(etekina);
			lbletekina.setText(etekina.toString());
			
			jButtonClose.setBounds(192, 81, 89, 33);
			contentPane.add(jButtonClose);		
			jButtonClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButton2_actionPerformed(e);
				}
			});
			this.getContentPane().add(jButtonClose, null);
		}
		catch(Exception ex1) {
			//JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Saiatu berriro");
			ex1.printStackTrace();
		}
		
	}
	
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
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
			panel.setBounds(10, 125, 451, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		lblEtekinak.setText(ResourceBundle.getBundle("Etiquetas").getString("Etekinak"));
		lblEtekinakHauekIzan.setText(ResourceBundle.getBundle("Etiquetas").getString("Etekin_hauek"));
		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EtekinakADMIN"));
	}
	
}

