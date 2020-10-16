package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Bet;
import domain.Kuota;
import domain.Question;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class ApustuaEzabatuGUI extends JFrame {
	private static final String LOCALE = "Locale: ";

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private  JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private  JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private  JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private  JLabel jLabelKuotak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Kuotak")); 

	private JLabel erab = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEzabatuGUI.lblNewLabel.text"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton btnBorratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEzabatuGUI.btnBorratu.text"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPaneKuota = new JScrollPane();

	private Vector<String> erabiltzaileErreplikatuak =new Vector<String>();


	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableKuotak= new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelKuotak;


	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private String[] columnNamesKuotak = new String[] {
			"Erantzuna", 
			"Apustua",
			"Kuota",

	};


	public ApustuaEzabatuGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public ApustuaEzabatuGUI(String text) {
		try
		{
			jbInit(text);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void jbInit(final String text) {

		JLabel erab = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		erab.setBounds(653, 21, 138, 19);
		getContentPane().add(erab);
		erab.setText(text);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(832, 547));
		this.setTitle("Apustua Ezabatu");

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(10, 247, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);
		jLabelKuotak.setBounds(474, 247, 277, 14);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		this.getContentPane().add(jLabelKuotak);
		this.getContentPane().add(getPanel());

		jButtonClose.setBounds(new Rectangle(184, 421, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));



					try {
						System.out.println(columnNamesEvents);
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() )
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);  
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneKuota.setBounds(474, 271, 307, 116);


		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));



		scrollPaneQueries.setBounds(new Rectangle(10, 273, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i=tableEvents.getSelectedRow();

					domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
					Vector<Question> queries=ev.getQuestions();

					tableModelQueries.setDataVector(null, columnNamesQueries);
					tableModelQueries.setColumnCount(3);

					if (queries.isEmpty())
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
					else 
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

					for (domain.Question q:queries){
						Vector<Object> row = new Vector<Object>();

						row.add(q.getQuestionNumber());
						row.add(q.getQuestion());
						row.add(q);
						tableModelQueries.addRow(row); 
					}
					tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
					tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
					tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2));
				}
				catch(Exception g) {

				}
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i=tableQueries.getSelectedRow();
					domain.Question gal=(domain.Question)tableModelQueries.getValueAt(i,2); // obtain ev object
					Vector<Kuota> kuotaGaldera=gal.getKuotalista();
					tableModelKuotak.setDataVector(null, columnNamesKuotak);
					tableModelKuotak.setColumnCount(4);

					BLFacade facade = MainGUI.getBusinessLogic();
					User erabil = facade.getUser(text);

					Vector<Bet> betErabiltzaile = erabil.getbetlista();
					Double lag;
					Vector<String> ema = new Vector<String>();

					for (domain.Bet b:betErabiltzaile) {
						for (domain.Kuota k:kuotaGaldera) {
							System.out.println(b.getKuotaId()+"="+k.getKuotaId());
							if(b.getKuotaId()!=null && k.getKuotaId()!=null) {
								if(b.getKuotaId().equals(k.getKuotaId())) {
									System.out.println("sartu");
									Vector<Object> row = new Vector<Object>();
									row.add(k.getErantzuna());
									lag = b.getZenbatDiru();
									row.add(lag.toString());
									lag = k.getZenbatekoKuota();
									row.add(lag.toString());
									row.add(b);
									tableModelKuotak.addRow(row); 
								}
							}
						}
					}

					tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(50);
					tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(50);
					tableKuotak.getColumnModel().getColumn(2).setPreferredWidth(50);
					tableKuotak.getColumnModel().removeColumn(tableKuotak.getColumnModel().getColumn(3));
				}
				catch(Exception p) {

				}
			}
		});

		scrollPaneKuota.setViewportView(tableKuotak);
		tableModelKuotak = new DefaultTableModel(null, columnNamesKuotak);

		tableKuotak.setModel(tableModelKuotak);
		tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableKuotak.getColumnModel().getColumn(2).setPreferredWidth(120);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneKuota, null);

		btnBorratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableKuotak.getSelectedRow();
				int j=tableQueries.getSelectedRow();


				if(j==-1 || i==-1) {
					JOptionPane.showMessageDialog(null, "Tabletako errenkadak aukeratu mesedez.");
				}
				else {
					erabiltzaileErreplikatuak.add(text);
					domain.Bet b = (domain.Bet) tableModelKuotak.getValueAt(i,3);
					System.out.println(b.toString());

					BLFacade facade = MainGUI.getBusinessLogic();
					Bet be = facade.getBet(b.getBetId());

					//     for(Integer in:be.getApustuAnitza()) {
					//      System.out.println(in);
					//     }
					if(b.getApustuAnitza().size()==0 && b.getOriginala()==null) {

						Integer id = b.getBetId();
						facade.removeBet(b.getKuotaId(),text,b);
						User u = facade.getUser(text);
						Double dirua = Double.valueOf(tableModelKuotak.getValueAt(i,1).toString());
						for(User erab:u.geterabiltzaileErreplikatuak()) {
							ezabatuErreplikatuetan(erab,id,dirua);
						}
						//facade.getUser(text).setDirua(dirua);
						Double dirukop = MainGUI.getBusinessLogic().diruasartu(text,dirua);
						JOptionPane.showMessageDialog(null, "Diru kopurua: "+ dirukop);
						facade.dirumugimendua(dirua, text,1);
						tableModelKuotak.removeRow(i);
						scrollPaneKuota.setViewportView(tableKuotak);
					}
					else if(b.getOriginala()!=null && b.getApustuAnitza().size()==0) {

						Vector<User> erabiltzaileak = MainGUI.getBusinessLogic().getErabiltzaileak();
						for(User u:erabiltzaileak) {
							for(Bet ap:u.getbetlista()) {
								if(ap.getBetId().compareTo(b.getOriginala())==0) {
									for(User erab:u.geterabiltzaileErreplikatuak()) {
										if(erab.getKorreoa().compareTo(text)==0) {
											JOptionPane.showMessageDialog(null, "Apustu hau ezabatzeko, lehendabizi kendu zaitez "+u.getKorreoa()+" erreplikatutik");
											return;
										}
									}
									break;
								}
							}
						}
						Integer id = b.getBetId();
						facade.removeBet(b.getKuotaId(),text,b);
						User u = facade.getUser(text);
						Double dirua = Double.valueOf(tableModelKuotak.getValueAt(
								i,1).toString());
						for(User erab:u.geterabiltzaileErreplikatuak()) {
							ezabatuErreplikatuetan(erab,id,dirua);
						}
						//facade.getUser(text).setDirua(dirua);
						Double dirukop = MainGUI.getBusinessLogic().diruasartu(text,dirua);
						JOptionPane.showMessageDialog(null, "Diru kopurua: "+ dirukop);
						facade.dirumugimendua(dirua, text,1);
						tableModelKuotak.removeRow(i);
						scrollPaneKuota.setViewportView(tableKuotak);

					}
					else if(b.getApustuAnitza().size()>0 && b.getOriginala()!=null) {

						Vector<User> erabiltzaileak = MainGUI.getBusinessLogic().getErabiltzaileak();
						for(User u:erabiltzaileak) {
							for(Bet ap:u.getbetlista()) {
								if(ap.getBetId().compareTo(b.getOriginala())==0) {
									for(User erab:u.geterabiltzaileErreplikatuak()) {
										if(erab.getKorreoa().compareTo(text)==0) {
											JOptionPane.showMessageDialog(null, "Apustu hau ezabatzeko, lehendabizi erreplikatutik kendu apustua egin duen erabiltzailea");
											return;
										}
									}
									break;
								}
							}
						}
						Integer id = b.getBetId();
						Vector<Integer> apustuBerdinekoak= b.getApustuAnitza();
						facade.removeBet(b.getKuotaId(),text,b);
						for(Integer zenb:apustuBerdinekoak) {

							Bet borratuBet=facade.getBet(zenb);
							facade.removeBet(borratuBet.getKuotaId(),text,borratuBet);
						}
						User u = facade.getUser(text);
						Double dirua = Double.valueOf(tableModelKuotak.getValueAt(i,1).toString());
						for(User erab:u.geterabiltzaileErreplikatuak()) {
							ezabatuErreplikatuetan(erab,id,dirua);
						}

						//facade.getUser(text).setDirua(dirua);
						Double dirukop = MainGUI.getBusinessLogic().diruasartu(text,dirua);
						JOptionPane.showMessageDialog(null, "Diru kopurua: "+ dirukop);
						facade.dirumugimendua(dirua, text,1);
						tableModelKuotak.removeRow(i);
						scrollPaneKuota.setViewportView(tableKuotak);
					}
					else {
						JOptionPane.showMessageDialog(null, "Apustu Anitz baten parte da apustu hau, apustu anitz guztiak borratuko dira.");
						Integer id = b.getBetId();
						Vector<Integer> apustuBerdinekoak= b.getApustuAnitza();
						facade.removeBet(b.getKuotaId(),text,b);
						for(Integer zenb:apustuBerdinekoak) {
							Bet borratuBet=facade.getBet(zenb);
							facade.removeBet(borratuBet.getKuotaId(),text,borratuBet);
						}
						User u = facade.getUser(text);
						Double dirua = Double.valueOf(tableModelKuotak.getValueAt(i,1).toString());
						for(User erab:u.geterabiltzaileErreplikatuak()) {
							ezabatuErreplikatuetan(erab,id,dirua);
						}

						//facade.getUser(text).setDirua(dirua);
						Double dirukop = MainGUI.getBusinessLogic().diruasartu(text,dirua);
						JOptionPane.showMessageDialog(null, "Diru kopurua: "+ dirukop);
						facade.dirumugimendua(dirua, text,1);
						tableModelKuotak.removeRow(i);
						scrollPaneKuota.setViewportView(tableKuotak);
					}


				}
			}
		});
		btnBorratu.setBounds(474, 421, 130, 30);
		getContentPane().add(btnBorratu);

	}


	private void jbInit() throws Exception
	{
		this.setSize(new Dimension(832, 547));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(40, 15, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelQueries.setBounds(10, 247, 406, 14);
		this.getContentPane().add(jLabelQueries);
		jLabelEvents.setBounds(295, 19, 259, 16);
		this.getContentPane().add(jLabelEvents);
		jLabelKuotak.setBounds(474, 247, 277, 14);
		this.getContentPane().add(jLabelKuotak);
		jButtonClose.setBounds(184, 421, 130, 30);

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose);
		jCalendar1.setBounds(40, 50, 225, 150);


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));



					try {
						System.out.println(columnNamesEvents);
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() )
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);  
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1);

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i=tableEvents.getSelectedRow();

					domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
					Vector<Question> queries=ev.getQuestions();

					tableModelQueries.setDataVector(null, columnNamesQueries);
					tableModelQueries.setColumnCount(3);

					if (queries.isEmpty())
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
					else 
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

					for (domain.Question q:queries){
						Vector<Object> row = new Vector<Object>();

						row.add(q.getQuestionNumber());
						row.add(q.getQuestion());
						row.add(q);
						tableModelQueries.addRow(row); 
					}
					tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
					tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
					tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(3));
				}
				catch(Exception g) {

				}
			}
		});
		scrollPaneEvents.setBounds(292, 50, 346, 150);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		scrollPaneQueries.setBounds(10, 273, 406, 116);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i=tableQueries.getSelectedRow();
					domain.Question gal=(domain.Question)tableModelQueries.getValueAt(i,2); // obtain ev object
					Vector<Kuota> kot=gal.getKuotalista();


					tableModelKuotak.setDataVector(null, columnNamesKuotak);
					tableModelKuotak.setColumnCount(3);

					for (domain.Kuota q:kot){
						Vector<Object> row = new Vector<Object>();
						row.add(q.getZenbatekoKuota());
						row.add(q.getErantzuna());
						row.add(q.getKuotaId());
						tableModelKuotak.addRow(row); 
					}
					tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(25);
					tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(268);
					tableKuotak.getColumnModel().removeColumn(tableKuotak.getColumnModel().getColumn(2));
				}
				catch(Exception p) {

				}
			}
		});
		scrollPaneKuota.setBounds(447, 273, 361, 116);

		scrollPaneKuota.setViewportView(tableKuotak);
		tableModelKuotak = new DefaultTableModel(null, columnNamesKuotak);

		tableKuotak.setModel(tableModelKuotak);
		tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents);
		this.getContentPane().add(scrollPaneQueries);
		this.getContentPane().add(scrollPaneKuota);
		erab.setBounds(0, 0, 0, 0);

		getContentPane().add(erab);
		btnBorratu.setBounds(474, 421, 130, 30);

		btnBorratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		getContentPane().add(btnBorratu);


	}


	public void ezabatu(User erab, Integer id,Double dirua) {
		Boolean begiratu = false;
		for(String u:erabiltzaileErreplikatuak) {
			if(u.compareTo(erab.getKorreoa())==0) {
				begiratu=true;
			}
		}
		if(!begiratu) {
			for(Bet b:erab.getbetlista()) {
				if(b.getOriginala()!=null) {
					if(b.getOriginala().compareTo(id)==0) {
						Integer ident= b.getBetId();
						BLFacade facade=MainGUI.getBusinessLogic();
						facade.removeBet(b.getKuotaId(),erab.getKorreoa(),b);
						facade.diruasartu(erab.getKorreoa(),dirua);
						facade.dirumugimendua(dirua, erab.getKorreoa(),1);
						for (User er:erab.geterabiltzaileErreplikatuak()) {
							ezabatuErreplikatuetan(er,ident,dirua);
						}
					}
				}
			}
		}
	}



	public void ezabatuErreplikatuetan(User erab, Integer id,Double dirua) {
		Boolean begiratu = false;
		for(String u:erabiltzaileErreplikatuak) {
			if(u.compareTo(erab.getKorreoa())==0) {
				begiratu=true;
			}
		}
		if(!begiratu) {
			for(Bet b:erab.getbetlista()) {
				if(b.getOriginala()!=null) {
					if(b.getOriginala().compareTo(id)==0) {
						BLFacade facade=MainGUI.getBusinessLogic();
						Vector<Integer> aldamenekoak = b.getApustuAnitza();
						Integer ident = b.getBetId();
						facade.removeBet(b.getKuotaId(),erab.getKorreoa(),b);
						for(Integer in:aldamenekoak) {
							Bet be=facade.getBet(in);
							facade.removeBet(be.getKuotaId(),erab.getKorreoa(),be);
						}
						facade.diruasartu(erab.getKorreoa(),dirua);
						facade.dirumugimendua(dirua, erab.getKorreoa(),1);
						for (User er:erab.geterabiltzaileErreplikatuak()) {
							ezabatuErreplikatuetan(er,ident,dirua);
						}
					}
				}
			}

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
					System.out.println(LOCALE+Locale.getDefault());
					redibujar();    }
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
					System.out.println(LOCALE+Locale.getDefault());
					redibujar();    }
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
					System.out.println(LOCALE+Locale.getDefault());
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
			panel.setBounds(0, 461, 818, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
		jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
		jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events"));
		jLabelKuotak.setText(ResourceBundle.getBundle("Etiquetas").getString("Kuotak"));
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnBorratu.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEzabatuGUI.btnBorratu.text"));
		erab.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEzabatuGUI.lblNewLabel.text"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
	}
}