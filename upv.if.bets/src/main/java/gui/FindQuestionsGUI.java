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


public class FindQuestionsGUI extends JFrame {
	private static final String EVENTS = "Events";

	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString(EVENTS)); 
	private final JLabel jLabelKuotak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Kuotak")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ezeztatu"));

	private Double KuotaTotala=0.0;

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPaneKuota = new JScrollPane();
	private JScrollPane scrollPaneBet = new JScrollPane();

	private Vector<Bet> ApustuLista= new Vector<Bet>();
	private Vector<Question> GalderaLista= new Vector<Question>();
	private float minimoa;
	private float minimoMax=0;

	private Vector<String> erabiltzaileErrepikatuak = new Vector<String>();
	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableKuotak= new JTable();
	private JTable tableBet= new JTable();

	private JButton btnApustuaGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnApustuaGehitu.text"));
	private JButton btnApustuaEgin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnApustuaEgin.text")); 
	private JButton btnBorratuApustua = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text"));
	
	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelKuotak;
	private DefaultTableModel tableModelBet;
	
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private String[] columnNamesKuotak = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("KuotaN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Kuota")

	};

	private String[] columnNamesBet = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("ErantzunaN"), 
			ResourceBundle.getBundle("Etiquetas").getString("ApostuaN"),
			ResourceBundle.getBundle("Etiquetas").getString("KuotaN"),
			ResourceBundle.getBundle("Etiquetas").getString("ApustuMin"),
			ResourceBundle.getBundle("Etiquetas").getString("KuotaTotalN")

	};

	private JTextField zenbatDiru;
	JLabel lblZenbat = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.lblZenbat.text")); //$NON-NLS-1$ //$NON-NLS-2$

	private final JLabel lblLaguntza = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$

	public FindQuestionsGUI()
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


	public FindQuestionsGUI(String text) {
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

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(850, 636));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsTitle"));
		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(10, 247, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);
		jLabelKuotak.setBounds(474, 247, 277, 14);


		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		this.getContentPane().add(jLabelKuotak);
		this.getContentPane().add(getPanel());

		jButtonClose.setBounds(new Rectangle(575, 521, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for(Bet apustu:ApustuLista) {
					MainGUI.getBusinessLogic().removeBet(apustu.getKuotaId(), text, apustu);
				}
				ApustuLista.removeAllElements();
				GalderaLista.removeAllElements();
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
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString(EVENTS)+ ": "+dateformat1.format(calendarMio.getTime()));
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

		scrollPaneBet.setBounds(10, 434, 406, 129);




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

		scrollPaneKuota.setViewportView(tableKuotak);
		tableModelKuotak = new DefaultTableModel(null, columnNamesKuotak);

		tableKuotak.setModel(tableModelKuotak);
		tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneBet.setViewportView(tableBet);
		tableModelBet = new DefaultTableModel(null, columnNamesBet);
		tableBet.setModel(tableModelBet);
		tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(93);


		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneKuota, null);
		this.getContentPane().add(scrollPaneBet,null);

		zenbatDiru = new JTextField();
		zenbatDiru.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		zenbatDiru.setBounds(640, 416, 86, 20);
		getContentPane().add(zenbatDiru);
		zenbatDiru.setColumns(10);

		lblZenbat.setBounds(554, 418, 46, 14);
		getContentPane().add(lblZenbat);

		getContentPane().add(lblLaguntza);





		btnApustuaGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i=tableKuotak.getSelectedRow();
				int j=tableQueries.getSelectedRow();

				if(j==-1 || i==-1) {
					JOptionPane.showMessageDialog(null, "Tabletako errenkadak aukeratu mesedez.");
				}
				else {
					Object kuotaId =tableModelKuotak.getValueAt(i, 2);

					domain.Question question =(domain.Question)tableModelQueries.getValueAt(j, 2);

					BLFacade facade = MainGUI.getBusinessLogic();

					Double p=Double.parseDouble(zenbatDiru.getText());

					try {

						Bet b = facade.createBet(p,Integer.parseInt(kuotaId.toString()),null,text);

						ApustuLista.add(b);
						GalderaLista.add(question);

						for(Bet apus:ApustuLista) {
							if(apus.getBetId().compareTo(b.getBetId())!=0) {
								facade.apustuDiruaAldatu(apus.getBetId(),p);
								apus.setZenbatDiru(p);
							}
						}

						System.out.println(ApustuLista.toString());
						tableModelBet.setDataVector(null, columnNamesBet);
						tableModelBet.setColumnCount(6);

						Kuota kuot = MainGUI.getBusinessLogic().getKuota(b.getKuotaId());
						if(KuotaTotala==0.0) {
							KuotaTotala=kuot.getZenbatekoKuota();
						}
						else {
							KuotaTotala=kuot.getZenbatekoKuota()*KuotaTotala;
						}

						for (domain.Bet q:ApustuLista){
							Vector<Object> row = new Vector<Object>();
							Kuota k = MainGUI.getBusinessLogic().getKuota(q.getKuotaId());
							row.add(k.getErantzuna());
							row.add(q.getZenbatDiru());
							row.add(k.getZenbatekoKuota());
							row.add(GalderaLista.get(0).getBetMinimum());
							row.add(KuotaTotala);
							row.add(q);
							tableModelBet.addRow(row); 
						}
						tableBet.getColumnModel().getColumn(0).setPreferredWidth(50);
						tableBet.getColumnModel().getColumn(1).setPreferredWidth(50);
						tableBet.getColumnModel().getColumn(2).setPreferredWidth(50);
						tableBet.getColumnModel().getColumn(3).setPreferredWidth(50);
						tableBet.getColumnModel().getColumn(3).setPreferredWidth(93);
						tableBet.getColumnModel().removeColumn(tableBet.getColumnModel().getColumn(5));

						scrollPaneBet.setViewportView(tableBet);


					}
					catch(Exception e) {

					}
				}
			}

		});
		btnApustuaGehitu.setBounds(428, 446, 190, 26);
		getContentPane().add(btnApustuaGehitu);

		scrollPaneKuota.setViewportView(tableKuotak);
		tableModelKuotak = new DefaultTableModel(null, columnNamesKuotak);

		tableKuotak.setModel(tableModelKuotak);
		tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(268);

		btnApustuaEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//APUSTU BAKARRA

				Double p=Double.parseDouble(zenbatDiru.getText());

				for(Bet apus:ApustuLista) {
					MainGUI.getBusinessLogic().apustuDiruaAldatu(apus.getBetId(),p);
					apus.setZenbatDiru(p);
				}

				tableModelBet.setDataVector(null, columnNamesBet);
				tableModelBet.setColumnCount(6);

				for (domain.Bet q:ApustuLista){
					Vector<Object> row = new Vector<Object>();
					Kuota k = MainGUI.getBusinessLogic().getKuota(q.getKuotaId());
					row.add(k.getErantzuna());
					row.add(q.getZenbatDiru());
					row.add(k.getZenbatekoKuota());
					row.add(GalderaLista.get(0).getBetMinimum());
					row.add(KuotaTotala);
					row.add(q);
					tableModelBet.addRow(row); 
				}
				tableBet.getColumnModel().getColumn(0).setPreferredWidth(50);
				tableBet.getColumnModel().getColumn(1).setPreferredWidth(50);
				tableBet.getColumnModel().getColumn(2).setPreferredWidth(50);
				tableBet.getColumnModel().getColumn(3).setPreferredWidth(50);
				tableBet.getColumnModel().getColumn(3).setPreferredWidth(93);
				tableBet.getColumnModel().removeColumn(tableBet.getColumnModel().getColumn(5));

				scrollPaneBet.setViewportView(tableBet);

				erabiltzaileErrepikatuak.add(text);
				int luzera = ApustuLista.size();
				if(luzera==0) {
					JOptionPane.showMessageDialog(null, "Apustu bakarra egin nahi baduzu, lehendabizi apustua gehitu eta ondoren egin mesedez.");
				}

				if(luzera==1) {

					Double apustua =ApustuLista.get(0).getZenbatDiru();

					float minimoa= GalderaLista.get(0).getBetMinimum();

					if(minimoa<=apustua) {
						if(MainGUI.getBusinessLogic().getUser(text).getDirua()-Integer.parseInt(zenbatDiru.getText()) <0) {
							lblLaguntza.setText("Ez duzu diru nahikoa apustu hori egiteko.");
						}

						//duplikatuentzako
						User u = MainGUI.getBusinessLogic().getUser(text);
						Vector<User> erreplikatuak = u.geterabiltzaileErreplikatuak();

						for (User erab:erreplikatuak) {
							erreplikatuBakarra(erab,ApustuLista.get(0));
						}

						//apustua egiteko

						lblLaguntza.setText("Apustua egin da.");
						MainGUI.getBusinessLogic().dirumugimendua(-Double.parseDouble(zenbatDiru.getText()),text,1);
						MainGUI.getBusinessLogic().apustutakoakendu(text,Double.parseDouble(zenbatDiru.getText()));
					}
					else {
						lblLaguntza.setText("Apustu minimoa " +minimoa+" da. Ez da diru nahikoa!");
						return;
					}
					jButton2_actionPerformed(arg0);
				}

				else if(luzera>1){

					Double apustua =ApustuLista.get(0).getZenbatDiru();

					float minimoa= GalderaLista.get(0).getBetMinimum();

					for(Question q:GalderaLista) {
						minimoa=q.getBetMinimum();
						System.out.println(minimoa);
						if (minimoMax<minimoa){
							minimoMax=minimoa;
						}
					}

					if(minimoMax>apustua) {
						lblLaguntza.setText("Apustu minimoa " +minimoa+" da. Ez da diru nahikoa!");
						
						return;
					}
					if(MainGUI.getBusinessLogic().getUser(text).getDirua()-Integer.parseInt(zenbatDiru.getText()) <0) {
						lblLaguntza.setText("Ez duzu diru nahikoa apustu hori egiteko.");
						return;
					}
					MainGUI.getBusinessLogic().apustutakoakendu(text,Double.parseDouble(zenbatDiru.getText()));
					for(Bet apustuFinal:ApustuLista) {
						Vector<Integer> IdLista = new Vector<Integer>();
						for (Bet besteId:ApustuLista) {
							if(apustuFinal.getBetId()!=besteId.getBetId()) {
								IdLista.add(besteId.getBetId());
							}
						}
						MainGUI.getBusinessLogic().idListaGehitu(apustuFinal, IdLista);
						//Erreplikatuak gehitzeko apustu anitzetan

					}
					User u = MainGUI.getBusinessLogic().getUser(text);
					Vector<User> erreplikatuak = u.geterabiltzaileErreplikatuak();

					for (User erab:erreplikatuak) {
						erreplikatuAnitza(erab,ApustuLista,apustua);
					}
					lblLaguntza.setText("Apustua egin da.");
					MainGUI.getBusinessLogic().dirumugimendua(-Double.parseDouble(zenbatDiru.getText()),text,1);
					return;
				}
			}
		});
		btnApustuaEgin.setBounds(648, 446, 160, 26);
		getContentPane().add(btnApustuaEgin);
		lblLaguntza.setBounds(428, 482, 334, 25);
		btnBorratuApustua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i=tableBet.getSelectedRow();
					Bet apustua =(Bet) tableModelBet.getValueAt(i, 5);
					MainGUI.getBusinessLogic().removeBet(apustua.getKuotaId(), text, apustua);
					ApustuLista.remove(apustua);

					tableModelBet.removeRow(i);

					scrollPaneBet.setViewportView(tableBet);
				}catch(Exception j) {

				}
			}
		});
		btnBorratuApustua.setHorizontalAlignment(SwingConstants.LEFT);
		btnBorratuApustua.setBounds(438, 526, 116, 21);
		getContentPane().add(btnBorratuApustua);





	}


	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(850, 636));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(10, 247, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);
		jLabelKuotak.setBounds(474, 247, 277, 14);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		this.getContentPane().add(jLabelKuotak);

		jButtonClose.setBounds(new Rectangle(575, 521, 130, 30));

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
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString(EVENTS)+ ": "+dateformat1.format(calendarMio.getTime()));
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

		scrollPaneKuota.setBounds(474, 271, 334, 116);


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

		scrollPaneKuota.setViewportView(tableKuotak);
		tableModelKuotak = new DefaultTableModel(null, columnNamesKuotak);

		tableKuotak.setModel(tableModelKuotak);
		tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableKuotak.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneKuota, null);

		zenbatDiru = new JTextField();
		zenbatDiru.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		zenbatDiru.setBounds(640, 416, 86, 20);
		getContentPane().add(zenbatDiru);
		zenbatDiru.setColumns(10);

		JLabel lblZenbat = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.lblZenbat.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblZenbat.setBounds(554, 418, 46, 14);
		getContentPane().add(lblZenbat);

		getContentPane().add(lblLaguntza);

		JButton btnApustuaEgin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnApustuaEgin.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnApustuaEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=tableKuotak.getSelectedRow();

				Object kuotaId =tableModelKuotak.getValueAt(i, 2);

				int j=tableQueries.getSelectedRow();

				domain.Question question =(domain.Question)tableModelQueries.getValueAt(j, 2);

				BLFacade facade = MainGUI.getBusinessLogic();

				Double p=Double.parseDouble(zenbatDiru.getText());

				float minimoa= question.getBetMinimum();

				if(minimoa<p) {
					//					facade.createBet(p,Integer.parseInt(kuotaId.toString()));

					lblLaguntza.setText("Apustua egin da.");

				}
				else {
					lblLaguntza.setText("Apustu minimoa" +minimoa+" da.");
					return;
				}

				

			}
		});
		btnApustuaEgin.setBounds(648, 446, 160, 26);
		getContentPane().add(btnApustuaEgin);
		lblLaguntza.setBounds(428, 482, 334, 25);

		JButton btnApustuaGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnApustuaGehitu.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnApustuaGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnApustuaGehitu.setBounds(428, 446, 190, 26);
		getContentPane().add(btnApustuaGehitu);


		scrollPaneBet.setBounds(10, 434, 406, 129);
		getContentPane().add(scrollPaneBet);

		JButton btnBorratuApustua = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBorratuApustua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorratuApustua.setHorizontalAlignment(SwingConstants.LEFT);
		btnBorratuApustua.setBounds(438, 526, 116, 21);
		getContentPane().add(btnBorratuApustua);

	}

	public void erreplikatuBakarra(User erab, Bet b) {
		Boolean begiratu = false;
		for(String u:erabiltzaileErrepikatuak) {
			if(u.compareTo(erab.getKorreoa())==0) {
				begiratu=true;
			}
		}
		if(!begiratu) {
			erabiltzaileErrepikatuak.add(erab.getKorreoa());
			BLFacade facade=MainGUI.getBusinessLogic();
			if(erab.getDirua()-Integer.parseInt(zenbatDiru.getText())>0) {
				Bet be=facade.createBet(b.getZenbatDiru(),b.getKuotaId(),b.getBetId(),erab.getKorreoa());
				facade.dirumugimendua(-Double.parseDouble(zenbatDiru.getText()),erab.getKorreoa(),1);
				facade.apustutakoakendu(erab.getKorreoa(),Double.parseDouble(zenbatDiru.getText()));
				for (User er:erab.geterabiltzaileErreplikatuak()) {
					erreplikatuBakarra(er,be);
				}
			}
		}
	}

	public void erreplikatuAnitza(User erab, Vector<Bet> apustuLista, Double zenbat) {
		Boolean begiratu = false;
		for(String u:erabiltzaileErrepikatuak) {
			if(u.compareTo(erab.getKorreoa())==0) {
				begiratu=true;
			}
		}
		if(!begiratu) {
			erabiltzaileErrepikatuak.add(erab.getKorreoa());
			if(erab.getDirua()>zenbat) {
				BLFacade facade=MainGUI.getBusinessLogic();
				Vector<Bet> apustuakDuplikatu = new Vector<Bet>();

				for(Bet be:apustuLista) { // originalaren apustu guztien lista->apustuLista
					Bet b = facade.createBet(be.getZenbatDiru(),be.getKuotaId(),be.getBetId(),erab.getKorreoa());
					apustuakDuplikatu.add(b);
				}

				for(Bet apustuFinal:apustuakDuplikatu) {
					Vector<Integer> IdLista = new Vector<Integer>();
					for (Bet besteId:apustuakDuplikatu) {
						if(apustuFinal.getBetId()!=besteId.getBetId()) {
							IdLista.add(besteId.getBetId());
						}
					}
					facade.idListaGehitu(apustuFinal, IdLista);
				}
				facade.dirumugimendua(-Double.parseDouble(zenbatDiru.getText()),erab.getKorreoa(),1);
				facade.apustutakoakendu(erab.getKorreoa(),zenbat);
				for (User er:erab.geterabiltzaileErreplikatuak()) {
					erreplikatuAnitza(er,apustuakDuplikatu,zenbat);
				}
			}

		}
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
			panel.setBounds(0, 587, 816, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsTitle"));
		jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
		jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
		jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString(EVENTS)); 
		jLabelKuotak.setText(ResourceBundle.getBundle("Etiquetas").getString("Kuotak"));
		lblZenbat.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.lblZenbat.text"));
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Ezeztatu"));
		btnApustuaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnApustuaGehitu.text"));
		btnApustuaEgin.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnApustuaEgin.text"));
		btnBorratuApustua.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text"));


	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}