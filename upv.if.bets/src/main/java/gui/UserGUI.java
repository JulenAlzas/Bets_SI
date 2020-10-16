package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.User;
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


public class UserGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

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
	private JLabel euro;
	private JLabel lblErabiltzailea;
	private JLabel lblDirua;
	private JButton QueryQuestions;
	private JButton diruasartu;
	private JButton mugimenduakBotoia;
	private JButton btnTopErabiltzaileak;
	private JButton btnEguneratuDirua;
	private JButton apustuaEzabatu;
	private JButton ErabiltzaileaDuplikatu;
	private JButton ErreplikatuaKendu;


	/**
	 * This is the default constructor
	 */
	public UserGUI() {
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



	public UserGUI(User qry) {
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

		initialize(qry);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(User qry) {
		// this.setSize(271, 295);
		this.setSize(543, 335);
		this.setContentPane(getJContentPane(qry));
		String MainTitle = "MainTitle";
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString(MainTitle));
		getEuro();
	}

	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(543, 335);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane(final User qry) {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
//			jContentPane.add(getBoton3());
			jContentPane.add(getPanel());


			JPanel panel_1 = new JPanel();
			panel_1.setBounds(228, 10, 100, 23);
			jContentPane.add(panel_1);

			final JLabel erab = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Erabiltzaile")); //$NON-NLS-1$ //$NON-NLS-2$
			erab.setText(qry.getKorreoa());
			panel_1.add(erab);
			erab.setFont(new Font("Tahoma", Font.PLAIN, 12));
			

			final JPanel panel_2 = new JPanel();
			panel_2.setBounds(413, 10, 99, 23);
			jContentPane.add(panel_2);

			final JLabel money = diruaLortu(qry);
			panel_2.add(money);
			money.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
			
			diruasartu = new JButton();
			diruasartu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JFrame a = new DiruaSartuGUI(erab.getText(),money.getText());
					a.setVisible(true);
				}
			});
			diruasartu.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartu")); //$NON-NLS-1$ //$NON-NLS-2$
			diruasartu.setBounds(23, 112, 220, 38);
			jContentPane.add(diruasartu);
			
			mugimenduakBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak")); //$NON-NLS-1$ //$NON-NLS-2$
			mugimenduakBotoia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new MugimenduakIkusiGUI(erab.getText(),money.getText());

					a.setVisible(true);
				}
			});
			diruasartu.setText(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.diruasartu.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mugimenduakBotoia.setBounds(23, 161, 220, 38);
			jContentPane.add(mugimenduakBotoia);
			
			QueryQuestions = new JButton();
			QueryQuestions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindQuestionsGUI(erab.getText());

					a.setVisible(true);
				}
			});
			QueryQuestions.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin")); //$NON-NLS-1$ //$NON-NLS-2$
			QueryQuestions.setBounds(23, 63, 220, 38);
			jContentPane.add(QueryQuestions);
			
			apustuaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEzabatu")); //$NON-NLS-1$ //$NON-NLS-2$
			apustuaEzabatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ApustuaEzabatuGUI(erab.getText());
					a.setVisible(true);
				}
			});
			apustuaEzabatu.setBounds(282, 64, 220, 37);
			jContentPane.add(apustuaEzabatu);
			
			ErabiltzaileaDuplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Duplikatu")); //$NON-NLS-1$ //$NON-NLS-2$
			ErabiltzaileaDuplikatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ErabiltzaileaErreplikatuGUI(erab.getText());
					a.setVisible(true);
				}
			});
			ErabiltzaileaDuplikatu.setBounds(282, 112, 220, 38);
			jContentPane.add(ErabiltzaileaDuplikatu);
			
			ErreplikatuaKendu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("KenduErrepli")); //$NON-NLS-1$ //$NON-NLS-2$
			ErreplikatuaKendu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ErreplikatutikKenduGUI(erab.getText());
					a.setVisible(true);
				}
			});
			ErreplikatuaKendu.setBounds(282, 162, 220, 38);
			jContentPane.add(ErreplikatuaKendu);
			
			btnEguneratuDirua = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Eguneratu")); //$NON-NLS-1$ //$NON-NLS-2$
			btnEguneratuDirua.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panel_2.removeAll();
					JLabel money = diruaLortu(qry);
					panel_2.add(money);
					money.setFont(new Font("Tahoma", Font.PLAIN, 12));
				}
			});
			btnEguneratuDirua.setBounds(353, 33, 159, 23); 
			jContentPane.add(btnEguneratuDirua);
			
			lblErabiltzailea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Erabiltzaile")); //$NON-NLS-1$ //$NON-NLS-2$
			lblErabiltzailea.setBounds(132, 12, 73, 21);
			jContentPane.add(lblErabiltzailea);
			
			lblDirua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dirua")); //$NON-NLS-1$ //$NON-NLS-2$
			lblDirua.setBounds(361, 13, 42, 19);
			jContentPane.add(lblDirua);
			
			btnTopErabiltzaileak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Top")); //$NON-NLS-1$ //$NON-NLS-2$
			btnTopErabiltzaileak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						TopErabiltzaileGUI frame = new TopErabiltzaileGUI();
						frame.setVisible(true);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Arazo bat gertatu da.");
					}
				}
			});
			btnTopErabiltzaileak.setBounds(23, 210, 220, 38);
			jContentPane.add(btnTopErabiltzaileak);
			
		}
		return jContentPane;
	}

	private JLabel diruaLortu(User qry) {
	    JLabel money = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.money.text")); //$NON-NLS-1$ //$NON-NLS-2$
		Double zenbat =qry.getDirua();
	    money.setText(zenbat.toString());
		return money;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getPanel());

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(228, 10, 100, 23);
			final JLabel erab = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
			panel_1.add(erab);
			erab.setFont(new Font("Tahoma", Font.PLAIN, 12));

			JPanel panel_2 = new JPanel();
			panel_2.setBounds(413, 10, 99, 23);
			jContentPane.add(panel_2);

			JLabel money = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.money.text")); //$NON-NLS-1$ //$NON-NLS-2$
			panel_2.add(money);
			money.setFont(new Font("Tahoma", Font.PLAIN, 12));
			panel_2.add(getEuro());

			JButton diruasartu = new JButton();
			diruasartu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			diruasartu.setText(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.diruasartu.text")); //$NON-NLS-1$ //$NON-NLS-2$
			diruasartu.setBounds(23, 112, 220, 38);
			jContentPane.add(diruasartu);
			
			JButton mugimenduakBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.btnMugimenduakIkusi.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mugimenduakBotoia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			diruasartu.setText(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.diruasartu.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mugimenduakBotoia.setBounds(23, 161, 220, 38);
			jContentPane.add(mugimenduakBotoia);
			
			JButton QueryQuestions = new JButton();
			QueryQuestions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			QueryQuestions.setText(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.QueryQuestions.text")); //$NON-NLS-1$ //$NON-NLS-2$
			QueryQuestions.setBounds(23, 63, 220, 38);
			jContentPane.add(QueryQuestions);
			 
			apustuaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.btnApustuaEzabatu.text")); //$NON-NLS-1$ //$NON-NLS-2$
			apustuaEzabatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			apustuaEzabatu.setBounds(282, 64, 220, 37);
			jContentPane.add(apustuaEzabatu);
			
			JButton btnEguneratuDirua = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.btnEguneratuDirua.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnEguneratuDirua.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnEguneratuDirua.setBounds(353, 33, 159, 23); 
			jContentPane.add(btnEguneratuDirua);
			
			lblErabiltzailea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.lblErabiltzailea.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblErabiltzailea.setBounds(170, 12, 73, 21);
			jContentPane.add(lblErabiltzailea);
			
			lblDirua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.lblDirua.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblDirua.setBounds(361, 13, 42, 19);
			jContentPane.add(lblDirua);
			
			JButton ErabiltzaileaDuplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.btnErabiltzaileaErreplikatu.text")); //$NON-NLS-1$ //$NON-NLS-2$
			ErabiltzaileaDuplikatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			ErabiltzaileaDuplikatu.setBounds(282, 112, 220, 38);
			jContentPane.add(ErabiltzaileaDuplikatu);
			
			JButton ErreplikatuaKendu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.btnErreplikatuaKendu.text")); //$NON-NLS-1$ //$NON-NLS-2$
			ErreplikatuaKendu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			ErreplikatuaKendu.setBounds(282, 162, 220, 38);
			jContentPane.add(ErreplikatuaKendu);
			
			JButton btnTopErabiltzaileak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.btnTopErabiltzaileakIkusi.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnTopErabiltzaileak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						TopErabiltzaileGUI frame = new TopErabiltzaileGUI();
						frame.setVisible(true);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Arazo bat gertatu da.");
					}
				}
			});
			btnTopErabiltzaileak.setBounds(23, 210, 220, 38);
			jContentPane.add(btnTopErabiltzaileak);
			
		}
		return jContentPane;
	}


	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 10, 140, 23);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
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
			panel.setBounds(23, 252, 479, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		lblErabiltzailea.setText(ResourceBundle.getBundle("Etiquetas").getString("Erabiltzaile"));
		lblDirua.setText(ResourceBundle.getBundle("Etiquetas").getString("Dirua"));
		QueryQuestions.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin"));
		diruasartu.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartu"));
		mugimenduakBotoia.setText(ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak"));
		btnTopErabiltzaileak.setText(ResourceBundle.getBundle("Etiquetas").getString("Top"));
		btnEguneratuDirua.setText(ResourceBundle.getBundle("Etiquetas").getString("Eguneratu"));
		apustuaEzabatu.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEzabatu"));
		ErabiltzaileaDuplikatu.setText(ResourceBundle.getBundle("Etiquetas").getString("Duplikatu"));
		ErreplikatuaKendu.setText(ResourceBundle.getBundle("Etiquetas").getString("KenduErrepli"));
//		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JLabel getEuro() {
		if (euro == null) {
			euro = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.label.text_1")); //$NON-NLS-1$ //$NON-NLS-2$
			euro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return euro;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

