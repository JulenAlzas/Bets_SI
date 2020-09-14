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

public class ErreplikatutikKenduGUI extends JFrame {

	private JPanel contentPane;
	private JTable tableZeini= new JTable();
	private JScrollPane scrollPaneZeini = new JScrollPane();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private DefaultTableModel tableModelZein;
	private DefaultTableModel tableModelZeini;
	
	private JPanel panel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblErreplikatuaKendu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.lblErreplikatu.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton erreplikatuaKendu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.btnErreplikatu.text")); //$NON-NLS-1$ //$NON-NLS-2$
	
	private String[] columnNamesZein = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("ZeinDesDuplikatu"), 
	};
	
	private String[] columnNamesZeini = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("ZeiniDesDuplikatu"), 
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
	public ErreplikatutikKenduGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.this.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 367);
		contentPane = new JPanel();
		System.out.println("hey");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Vector<User> erabiltzaileak = MainGUI.getBusinessLogic().getErabiltzaileak();
		
		lblErreplikatuaKendu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblErreplikatuaKendu.setBounds(10, 11, 167, 14);
		contentPane.add(lblErreplikatuaKendu);
		contentPane.add(getPanel());
		scrollPaneZeini.setBounds(115, 59, 205, 127);
		
		scrollPaneZeini.setViewportView(tableZeini);
		tableModelZeini = new DefaultTableModel(null, columnNamesZeini);

		tableZeini.setModel(tableModelZeini);
		tableZeini.getColumnModel().getColumn(0);
		
		tableModelZein = new DefaultTableModel(null, columnNamesZein);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		tableModelZein.setDataVector(null, columnNamesZein);
		tableModelZein.setColumnCount(1);
		
		for (User m:erabiltzaileak){
			Vector<Object> row = new Vector<Object>();
			row.add(m.getKorreoa());
			row.add(m);
			tableModelZein.addRow(row); 
		}
		
		this.getContentPane().add(scrollPaneZeini, null);
		
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
			
			erreplikatuaKendu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//						int i=tableZein.getSelectedRow();
//						String gal=(String)tableModelZein.getValueAt(i,0);
//						int j=tableZeini.getSelectedRow();
//						String gal2=(String)tableModelZeini.getValueAt(j,0);
//						MainGUI.getBusinessLogic().erreplikatutikKendu(gal,gal2);
//						JOptionPane.showMessageDialog(null, "Erabiltzailea erreplikatutik kendu da");
//						return;
				}
			});
			erreplikatuaKendu.setBounds(142, 214, 144, 38);
			contentPane.add(erreplikatuaKendu);
			
			
			
			contentPane.add(scrollPaneZeini);
		}
		catch(Exception ex1) {
			//JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Saiatu berriro");
			ex1.printStackTrace();
		}
		
	}
	
	
	public ErreplikatutikKenduGUI(final String text) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 367);
		contentPane = new JPanel();
		System.out.println("hey");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Vector<User> erabiltzaileak = MainGUI.getBusinessLogic().getErabiltzaileak();
		lblErreplikatuaKendu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblErreplikatuaKendu.setBounds(10, 11, 167, 14);
		contentPane.add(lblErreplikatuaKendu);
		contentPane.add(getPanel());
		

		scrollPaneZeini.setBounds(115, 59, 205, 127);
		
		tableModelZeini = new DefaultTableModel(null, columnNamesZeini);
		
		tableModelZeini.setDataVector(null, columnNamesZeini);
		tableModelZeini.setColumnCount(1);
		

		Vector<User> emaitza = new Vector<User>();
		for(User erab:erabiltzaileak) {
			for(User us:erab.geterabiltzaileErreplikatuak()) {
				if(us.getKorreoa().compareTo(text)==0) {
					emaitza.add(erab);
				}
			}
		}

		

		for (User m:emaitza){
				Vector<Object> row = new Vector<Object>();
				row.add(m.getKorreoa());
				row.add(m);
				tableModelZeini.addRow(row); 
		}
		
		scrollPaneZeini.setViewportView(tableZeini);
		

		tableZeini.setModel(tableModelZeini);
		tableZeini.getColumnModel().getColumn(0);
	

		
		this.getContentPane().add(scrollPaneZeini, null);
		
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
			
			erreplikatuaKendu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						int j=tableZeini.getSelectedRow();
						String gal2=(String)tableModelZeini.getValueAt(j,0);
						MainGUI.getBusinessLogic().erreplikatutikKendu(text,gal2);
						JOptionPane.showMessageDialog(null, "Erabiltzailea erreplikatutik kendu da");
						tableModelZeini.removeRow(j);
						scrollPaneZeini.setViewportView(tableZeini);
						return;
				}
			});
			erreplikatuaKendu.setBounds(142, 214, 144, 38);
			contentPane.add(erreplikatuaKendu);

			contentPane.add(scrollPaneZeini);
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
			panel.setBounds(0, 274, 479, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		lblErreplikatuaKendu.setText(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.lblErreplikatu.text"));
		erreplikatuaKendu.setText(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.btnErreplikatu.text"));		
		
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaErreplikatuGUI.this.title"));
	}
}
