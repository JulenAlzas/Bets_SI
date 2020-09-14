package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Kuota;
import domain.Mugimenduak;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.persistence.NoResultException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErabiltzaileaErreplikatuGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPaneZein = new JScrollPane();
	private JTable tableZein= new JTable();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private DefaultTableModel tableModelZein;
	private DefaultTableModel tableModelZeini;
	
	private JPanel panel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JLabel lblErreplikatu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.lblErreplikatu1.text")); 
	private JButton erreplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.btnErreplikatu1.text"));
	private String[] columnNamesZein = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("ZeinDuplikatu"), 
	};
	
	private String[] columnNamesZeini = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("ZeiniDuplikatu"), 
	};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErabiltzaileaErreplikatuGUI frame = new ErabiltzaileaErreplikatuGUI();
					
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
	public ErabiltzaileaErreplikatuGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 354);
		contentPane = new JPanel();
		System.out.println("hey");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Vector<User> erabiltzaileak = MainGUI.getBusinessLogic().getErabiltzaileak();
		
		lblErreplikatu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblErreplikatu.setBounds(10, 11, 248, 14);
		contentPane.add(lblErreplikatu);
		contentPane.add(getPanel());
		
		tableZein.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i=tableZein.getSelectedRow();
					String gal=(String)tableModelZein.getValueAt(i,0);
					
					Vector<User> erabiltzaileak = MainGUI.getBusinessLogic().getErabiltzaileak();
					BLFacade facade = MainGUI.getBusinessLogic();
					
					tableModelZeini.setDataVector(null, columnNamesZeini);
					tableModelZeini.setColumnCount(1);

					for (User m:erabiltzaileak){
						if(m.getKorreoa().compareTo(gal)!=0) {
							Vector<Object> row = new Vector<Object>();
							row.add(m.getKorreoa());
							row.add(m);
							tableModelZeini.addRow(row); 
						}
					}
					
					

				}
				catch(Exception p) {
				}
			}
		});
		
		
		
		scrollPaneZein.setBounds(97, 53, 248, 127); 
		tableModelZeini = new DefaultTableModel(null, columnNamesZeini);
	
		scrollPaneZein.setViewportView(tableZein);
		
		tableModelZein = new DefaultTableModel(null, columnNamesZein);

		tableZein.setModel(tableModelZein);
		tableZein.getColumnModel().getColumn(0);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		tableModelZein.setDataVector(null, columnNamesZein);
		tableModelZein.setColumnCount(1);
		
		for (User m:erabiltzaileak){
			Vector<Object> row = new Vector<Object>();
			row.add(m.getKorreoa());
			row.add(m);
			tableModelZein.addRow(row); 
		}
		
		tableZein.getColumnModel().getColumn(0);

		
		
		this.getContentPane().add(scrollPaneZein, null);
		
		try {	
			Double etekina = MainGUI.getBusinessLogic().etekinakLortu();
			System.out.println(etekina);
			
			jButtonClose.setBounds(337, 230, 89, 23);
			contentPane.add(jButtonClose);		
			jButtonClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButton2_actionPerformed(e);
				}
			});
			this.getContentPane().add(jButtonClose, null);
			
			erreplikatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						
//						int i=tableZein.getSelectedRow();
//						String gal=(String)tableModelZein.getValueAt(i,0);
//						int j=tableZeini.getSelectedRow();
//						String gal2=(String)tableModelZeini.getValueAt(j,0);
//						User u = MainGUI.getBusinessLogic().getUser(gal2);
//						for(User erab:u.geterabiltzaileErreplikatuak()) {
//							if(erab.getKorreoa().compareTo(gal)==0) {
//								JOptionPane.showMessageDialog(null, "Dagoeneko erreplikatuta dago");
//								return;
//							}
//						}
//						
//						MainGUI.getBusinessLogic().addErreplikatua(gal,gal2);
//						JOptionPane.showMessageDialog(null, "Erabiltzailea erreplikatu da");
//						return;
				}
			});
			erreplikatu.setBounds(139, 188, 152, 38);
			contentPane.add(erreplikatu);
			
			
			
			contentPane.add(scrollPaneZein);
		}
		catch(Exception ex1) {
			//JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Saiatu berriro");
			ex1.printStackTrace();
		}
		
	}
	public ErabiltzaileaErreplikatuGUI(final String text) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 354);
		contentPane = new JPanel();
		System.out.println("hey");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Vector<User> erabiltzaileak = MainGUI.getBusinessLogic().getErabiltzaileak();
		lblErreplikatu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblErreplikatu.setBounds(10, 11, 248, 14);
		contentPane.add(lblErreplikatu);
		contentPane.add(getPanel());

		BLFacade facade = MainGUI.getBusinessLogic();

		scrollPaneZein.setBounds(97, 53, 248, 127);


		scrollPaneZein.setViewportView(tableZein);

		tableModelZein = new DefaultTableModel(null, columnNamesZeini);

		tableZein.setModel(tableModelZein);
		tableZein.getColumnModel().getColumn(0);

		tableModelZein.setDataVector(null, columnNamesZeini);
		tableModelZein.setColumnCount(1);
		
		Vector<User> emaitza = new Vector<User>();
		for(User erab:erabiltzaileak) {
			if(erab.getKorreoa().compareTo(text)!=0) {
				Boolean badago = false;
				for(User us:erab.geterabiltzaileErreplikatuak()) {
					
					if(us.getKorreoa().compareTo(text)==0) {
						badago=true;
					}
					
				}
				if(!badago) {
					emaitza.add(erab);
				}
			}
		}

		for (User m:emaitza){
				Vector<Object> row = new Vector<Object>();
				row.add(m.getKorreoa());
				row.add(m);
				tableModelZein.addRow(row); 
		}

		tableZein.getColumnModel().getColumn(0);


		this.getContentPane().add(scrollPaneZein, null);


		try {	
			Double etekina = MainGUI.getBusinessLogic().etekinakLortu();
			System.out.println(etekina);

			jButtonClose.setBounds(325, 230, 89, 23);
			contentPane.add(jButtonClose);		
			jButtonClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButton2_actionPerformed(e);
				}
			});
			this.getContentPane().add(jButtonClose, null);

			erreplikatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int i=tableZein.getSelectedRow();
					String gal=(String)tableModelZein.getValueAt(i,0);
					String korreoa = text;
					User u = MainGUI.getBusinessLogic().getUser(gal);
					for(User erab:u.geterabiltzaileErreplikatuak()) {
						if(erab.getKorreoa().compareTo(korreoa)==0) {
							JOptionPane.showMessageDialog(null, "Dagoeneko erreplikatuta dago");
							return;
						}
					}
					MainGUI.getBusinessLogic().addErreplikatua(korreoa,gal);
					JOptionPane.showMessageDialog(null, "Erabiltzailea erreplikatu da");
					tableModelZein.removeRow(i);
					scrollPaneZein.setViewportView(tableZein);
					return;
				}
			});
			erreplikatu.setBounds(139, 188, 152, 38);
			contentPane.add(erreplikatu);


			contentPane.add(scrollPaneZein);



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
			panel.setBounds(0, 263, 479, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		lblErreplikatu.setText(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.lblErreplikatu1.text"));
		erreplikatu.setText(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.btnErreplikatu1.text"));
		

		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.this.title"));
	}
}
