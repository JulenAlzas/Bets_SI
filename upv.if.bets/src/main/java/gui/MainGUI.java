package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton btnEmaitzaIpini =null;
	
	private JButton createKuota=null;
	private JButton btnEtekinak=null;
	private JButton btnEkintzaEzabatu=null;
	private JButton btnTopErabiltzaileak=null;

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jButtonCreateEvent;
	private JButton btnAdminBerria;

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(490, 287);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getPanel());
			jContentPane.add(getJButtonCreateEvent());

		    createKuota = new JButton();
			createKuota.setFont(new Font("Tahoma", Font.BOLD, 15));
			createKuota.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new KuotaEzarriGUI();
					a.setVisible(true);
				}
			});
			createKuota.setBounds(238, 85, 214, 44);
			createKuota.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.createKuota.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jContentPane.add(createKuota);
			jContentPane.add(getBtnAdminBerria());

			btnEmaitzaIpini = new JButton();
			btnEmaitzaIpini.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnEmaitzaIpini.setText(ResourceBundle.getBundle("Etiquetas").getString("SetAnswer")); //$NON-NLS-1$ //$NON-NLS-2$
			btnEmaitzaIpini.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						EmaitzaIpiniGUI frame = new EmaitzaIpiniGUI();
						frame.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			
			
			btnEmaitzaIpini.setBounds(238, 43, 214, 44);
			jContentPane.add(btnEmaitzaIpini);

			btnEtekinak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Etekinak")); //$NON-NLS-1$ //$NON-NLS-2$
			btnEtekinak.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnEtekinak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						EtekinakGUI frame = new EtekinakGUI();
						frame.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btnEtekinak.setBounds(238, 124, 214, 44);
			jContentPane.add(btnEtekinak);
			
			btnEkintzaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GertaeraEzabatu")); //$NON-NLS-1$ //$NON-NLS-2$
			btnEkintzaEzabatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new EkintzaEzabatuGUI();
					a.setVisible(true);
				}
			});
			btnEkintzaEzabatu.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnEkintzaEzabatu.setBounds(0, 167, 228, 39);
			jContentPane.add(btnEkintzaEzabatu);
			
			btnTopErabiltzaileak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Top"));
			btnTopErabiltzaileak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						TopErabiltzaileGUI frame = new TopErabiltzaileGUI();
						frame.setVisible(true);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Arazoren bat gertatu da.");
					}
				}
			});
			btnTopErabiltzaileak.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnTopErabiltzaileak.setBounds(238, 167, 214, 38);
			jContentPane.add(btnTopErabiltzaileak);
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setFont(new Font("Tahoma", Font.BOLD, 15));
			jButtonCreateQuery.setBounds(0, 85, 228, 44);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setFont(new Font("Tahoma", Font.BOLD, 15));
			jButtonQueryQueries.setBounds(0, 127, 228, 44);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotakIkusi"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new KuotakIkusiGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}


	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(112, 1, 243, 44);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
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
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		btnEmaitzaIpini.setText(ResourceBundle.getBundle("Etiquetas").getString("SetAnswer"));
		createKuota.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateFee"));
		btnEtekinak.setText(ResourceBundle.getBundle("Etiquetas").getString("Etekinak"));
		btnEkintzaEzabatu.setText(ResourceBundle.getBundle("Etiquetas").getString("GertaeraEzabatu"));
		btnTopErabiltzaileak.setText(ResourceBundle.getBundle("Etiquetas").getString("Top"));
		btnAdminBerria.setText(ResourceBundle.getBundle("Etiquetas").getString("AdminBerria"));
		jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("EkintzaEzarri"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	private JButton getJButtonCreateEvent() {
		if (jButtonCreateEvent == null) {
			jButtonCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EkintzaEzarri")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonCreateEvent.setFont(new Font("Tahoma", Font.BOLD, 15));
			jButtonCreateEvent.setBounds(0, 43, 228, 44);
			jButtonCreateEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new CreateEventGUI();
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateEvent;
	}
	private JButton getBtnAdminBerria() {
		if (btnAdminBerria == null) {
			btnAdminBerria = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdminBerria")); //$NON-NLS-1$ //$NON-NLS-2$
			btnAdminBerria.setBackground(Color.WHITE);
			btnAdminBerria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new RegisteradminGUI();
					a.setVisible(true);
				}
			});
			btnAdminBerria.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAdminBerria.setBounds(341, 9, 111, 33);
		}
		return btnAdminBerria;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

