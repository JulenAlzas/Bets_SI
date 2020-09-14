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
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.persistence.NoResultException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class DiruaSartuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField zenbatdiru;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JPanel panel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JLabel lblDiruaSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.lblDiruaSartu.text"));
	private JButton btnBaieztatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.btnBaieztatu.text"));
	private JLabel lblZenbatDiruSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("zenbatdirusatu"));
	private JLabel label = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("erab"));
	private JLabel label_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Wallet"));
	private JLabel label_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("label"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI();
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
	public DiruaSartuGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		lblDiruaSartu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiruaSartu.setBounds(34, 0, 248, 39);
		contentPane.add(lblDiruaSartu);
		contentPane.add(getPanel());

		
		lblZenbatDiruSartu.setBounds(10, 57, 228, 13);
		contentPane.add(lblZenbatDiruSartu);

		zenbatdiru = new JTextField();
		zenbatdiru.setBounds(282, 53, 96, 19);
		contentPane.add(zenbatdiru);
		zenbatdiru.setColumns(10);

		
		btnBaieztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBaieztatu.setBounds(67, 94, 109, 22);
		contentPane.add(btnBaieztatu);

		JPanel panel = new JPanel();
		panel.setBounds(282, 0, 154, 23);
		contentPane.add(panel);

		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(282, 21, 154, 23);
		contentPane.add(panel_1);

		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(label_1);

		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(label_2);

		jButtonClose.setBounds(253, 94, 109, 22);
		contentPane.add(jButtonClose);		
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		this.getContentPane().add(jButtonClose, null);

		
	}

	public DiruaSartuGUI(final String text, String string) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDiruaSartu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiruaSartu.setBounds(34, 0, 248, 39);
		contentPane.add(lblDiruaSartu);
		contentPane.add(getPanel());

		lblZenbatDiruSartu.setBounds(10, 57, 228, 13);
		contentPane.add(lblZenbatDiruSartu);

		zenbatdiru = new JTextField();
		zenbatdiru.setBounds(282, 53, 96, 19);
		contentPane.add(zenbatdiru);
		zenbatdiru.setColumns(10);

		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(282, 21, 154, 23);
		contentPane.add(panel_1);

		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(label_1);

		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(label_2);

		label.setText(text);
		label_1.setText(string);
		
		jButtonClose.setBounds(253, 94, 109, 22);
		contentPane.add(jButtonClose);		
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		this.getContentPane().add(jButtonClose, null);

		btnBaieztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Integer.parseInt(zenbatdiru.getText()) <=0) {
						JOptionPane.showMessageDialog(null, "Diruak positibo izan behar du");
						return;
					}
					Double dirukop = MainGUI.getBusinessLogic().diruasartu(text,Double.parseDouble(zenbatdiru.getText()));
					label_1.setText(dirukop.toString());
					MainGUI.getBusinessLogic().dirumugimendua(Double.parseDouble(zenbatdiru.getText()),label.getText(),0);
					JOptionPane.showMessageDialog(null, "Dirua sartu da.");
				}
				catch(NoResultException ex) {
					JOptionPane.showMessageDialog(null, "Ez da horrelako erabiltzailerik existitzen");

				}
				catch(Exception ex1) {
					//ex1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Saiatu berriro");

				}
			}
		});
		
		

		btnBaieztatu.setBounds(119, 95, 96, 21);
		contentPane.add(btnBaieztatu);
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
			panel.setBounds(0, 216, 479, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		lblDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.lblDiruaSartu.text"));
		btnBaieztatu.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.btnBaieztatu.text"));
		lblZenbatDiruSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("zenbatdirusatu"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.this.title"));
	}
}

