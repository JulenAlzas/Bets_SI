package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import domain.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TopErabiltzaileGUI extends JFrame {

	private JPanel contentPane;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JLabel lblHauekDira = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TopErabiltzaileDesk"));
	private JLabel lblT = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TopErabiltzaileak"));

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
					TopErabiltzaileGUI frame = new TopErabiltzaileGUI();
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
	public TopErabiltzaileGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("TopErabiltzaileak"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblHauekDira.setBounds(10, 44, 299, 26);
		contentPane.add(lblHauekDira);

		lblT.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblT.setBounds(10, 17, 125, 26);
		contentPane.add(lblT);

		JTextArea lbltop = new JTextArea();
		lbltop.setBounds(67, 102, 299, 91);
		contentPane.add(lbltop);
		lbltop.setEditable(false);
		
		this.getContentPane().add(getPanel());


		try {
			List<User> topUsers = MainGUI.getBusinessLogic().topErabiltzaileak();
			String top = "";
			for(int i=0; i<3; i++) {
				lbltop.setText(top+= + i+1 + ". user: " + topUsers.get(i).getKorreoa() + "\n");
			}
			//System.out.println(top);
			//lbltop.setText(top);
		}
		catch(Exception ex1) {
			JOptionPane.showMessageDialog(null, "Hiru erabiltzaile baina gutxiago daude.");
			//ex1.printStackTrace();
		}

		jButtonClose.setBounds(172, 227, 89, 23);
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
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("TopErabiltzaileak"));
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		lblHauekDira.setText(ResourceBundle.getBundle("Etiquetas").getString("TopErabiltzaileDesk"));
		lblT.setText(ResourceBundle.getBundle("Etiquetas").getString("TopErabiltzaileak"));

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}