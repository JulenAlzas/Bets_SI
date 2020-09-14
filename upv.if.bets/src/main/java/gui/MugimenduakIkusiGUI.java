package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Mugimenduak;
import domain.User;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MugimenduakIkusiGUI extends JFrame {

	private JScrollPane scrollPaneMugimenduak = new JScrollPane();
	private JTable tableMugimenduak= new JTable();
	private DefaultTableModel tableModelMugimenduak;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panelhizkuntza;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JLabel label_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Wallet"));
	private JLabel lblDiruaSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak"));
	private JLabel lblZenbatDiruSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MugimenduDesk"));




	private String[] columnNamesMugimenduak = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("MugimenduakN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak")
	};
	 
	public MugimenduakIkusiGUI(final String text, String string) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakIkusiTitle")); //$NON-NLS-1$ //$NON-NLS-2$
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 337);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDiruaSartu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiruaSartu.setBounds(10, 0, 134, 39);
		contentPane.add(lblDiruaSartu);

		lblZenbatDiruSartu.setBounds(20, 57, 181, 13);
		contentPane.add(lblZenbatDiruSartu);

		JPanel panel = new JPanel();
		panel.setBounds(282, 0, 154, 23);
		contentPane.add(panel);

		JLabel label = new JLabel("User");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(282, 21, 154, 23);
		contentPane.add(panel_1);

		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("\u20AC");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(label_2);
		
		label.setText(text);
		

		User u =MainGUI.getBusinessLogic().getUser(text);
		final JLabel money = diruaLortu(u);
		panel_1.add(money);
		money.setFont(new Font("Tahoma", Font.PLAIN, 12));

		this.getContentPane().add(getPanel());
		
	
		scrollPaneMugimenduak.setBounds(10, 84, 325, 144);
		
		
		
		scrollPaneMugimenduak.setViewportView(tableMugimenduak);
		
		tableModelMugimenduak = new DefaultTableModel(null, columnNamesMugimenduak);

		tableMugimenduak.setModel(tableModelMugimenduak);
		tableMugimenduak.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableMugimenduak.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		User erabiltzaile = facade.getUser(text);
		
		Vector<Mugimenduak>mugimendulista = erabiltzaile.getMugimendulista();
		tableModelMugimenduak.setDataVector(null, columnNamesMugimenduak);
		tableModelMugimenduak.setColumnCount(3);
		
		for (domain.Mugimenduak m:mugimendulista){
			Vector<Object> row = new Vector<Object>();
			row.add(m.getData());
			System.out.println(m.getData());
			row.add(m.getDirualdaketa());
			System.out.println(m.getDirualdaketa());
			row.add(m);
			tableModelMugimenduak.addRow(row); 
		}
		
		tableMugimenduak.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableMugimenduak.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableMugimenduak.getColumnModel().removeColumn(tableMugimenduak.getColumnModel().getColumn(2));
		
		
		this.getContentPane().add(scrollPaneMugimenduak, null);
		
		jButtonClose.setBounds(176, 238, 89, 23);
		contentPane.add(jButtonClose);		
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		this.getContentPane().add(jButtonClose, null);
		
	}
	
	private JLabel diruaLortu(User qry) {
	    JLabel money = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.money.text")); //$NON-NLS-1$ //$NON-NLS-2$
		Double zenbat =MainGUI.getBusinessLogic().diruaLortu(qry.getKorreoa());
	    money.setText(zenbat.toString());
		return money;
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
		if (panelhizkuntza == null) {
			panelhizkuntza = new JPanel();
			panelhizkuntza.setBounds(0, 260, 414, 33);
			panelhizkuntza.add(getRdbtnNewRadioButton_1());
			panelhizkuntza.add(getRdbtnNewRadioButton_2());
			panelhizkuntza.add(getRdbtnNewRadioButton());
		}
		return panelhizkuntza;
	}

	private void redibujar() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakIkusiTitle"));
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		lblDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak"));
		lblZenbatDiruSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("MugimenduDesk"));
		label_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Wallet"));

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
