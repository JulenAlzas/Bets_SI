package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class CreateEventGUI extends JFrame {

	private JPanel contentPane;
	private JTextField TFDeskripzioa;
	private JTextField TFData;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	static BLFacade bl;
	private JPanel panel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel Deskripzioa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Deskripzioa"));
	private JLabel Data = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Data"));
	private JButton Ekintzasortu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ekintza_sortu"));
	private JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Ekintzasortulbl"));
	private JLabel lblSakatuHemen = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("klikhemen"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventGUI frame = new CreateEventGUI();
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
	public CreateEventGUI() {
		setTitle("Ekintza Sortu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Deskripzioa.setBounds(43, 100, 87, 14);
		contentPane.add(Deskripzioa);
		Deskripzioa.setVisible(false);

		Data.setBounds(43, 138, 74, 14);
		contentPane.add(Data);
		Data.setVisible(false);
		
		contentPane.add(getPanel());

		TFDeskripzioa = new JTextField();
		TFDeskripzioa.setColumns(10);
		TFDeskripzioa.setBounds(140, 97, 86, 20);
		contentPane.add(TFDeskripzioa);
		TFDeskripzioa.setVisible(false);

		TFData = new JTextField();
		TFData.setText("dd/mm/yyyy");
		TFData.setColumns(10);
		TFData.setBounds(140, 135, 86, 20);
		contentPane.add(TFData);
		TFData.setVisible(false);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateEventGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(174, 11, 87, 20);
		contentPane.add(lblNewLabel);

		Ekintzasortu.setVisible(false);
		Ekintzasortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sDate = TFData.getText();
				Date date1;
				if(TFDeskripzioa.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Deskripzioak ezin du hutsa izan");
					return;
				}
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
					MainGUI.getBusinessLogic().createEvent(TFDeskripzioa.getText(), date1);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Data formatu okerra");
				}
			}
		});
		Ekintzasortu.setBounds(280, 134, 126, 23);
		contentPane.add(Ekintzasortu);

		lblNewLabel_2.setBounds(43, 55, 145, 20);
		contentPane.add(lblNewLabel_2);

		lblSakatuHemen.setForeground(new Color(0, 0, 255));
		lblSakatuHemen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Deskripzioa.setVisible(true);
				TFDeskripzioa.setVisible(true);

				Data.setVisible(true);
				TFData.setVisible(true);

				Ekintzasortu.setVisible(true);

			}
		});
		lblSakatuHemen.setBounds(218, 58, 145, 14);
		contentPane.add(lblSakatuHemen);

		jButtonClose.setBounds(174, 182, 87, 23);
		contentPane.add(jButtonClose);		
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		this.getContentPane().add(jButtonClose, null);
	
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
			panel.setBounds(10, 216, 414, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		Deskripzioa.setText(ResourceBundle.getBundle("Etiquetas").getString("Deskripzioa"));
		Data.setText(ResourceBundle.getBundle("Etiquetas").getString("Data"));
		Ekintzasortu.setText(ResourceBundle.getBundle("Etiquetas").getString("Ekintza_sortu"));
		lblNewLabel_2.setText(ResourceBundle.getBundle("Etiquetas").getString("Ekintzasortulbl"));
		lblSakatuHemen.setText(ResourceBundle.getBundle("Etiquetas").getString("klikhemen"));
		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Ekintza_sortu"));
	}
}