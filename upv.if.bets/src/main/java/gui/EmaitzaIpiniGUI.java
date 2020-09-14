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


public class EmaitzaIpiniGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPaneKuota = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableKuotak= new JTable();
	
	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelKuotak;
	
	private JPanel panel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton kuotasortu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpiniGUI.kuotasortu.text")); 
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	
	private String[] columnNamesKuotak = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("KuotaN") 
	};

	public EmaitzaIpiniGUI()
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

	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(760, 547));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpiniGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(10, 247, 406, 14);
		jLabelEvents.setBounds(362, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		this.getContentPane().add(getPanel());

	
		kuotasortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int i=tableQueries.getSelectedRow();
					domain.Question gal=(domain.Question)tableModelQueries.getValueAt(i,2);
					
					int j=tableKuotak.getSelectedRow();
					
					String emaitza=(String)tableModelKuotak.getValueAt(j,0);
					
					
					Vector<Kuota> Galderaren_KuotaLista=MainGUI.getBusinessLogic().kuotalortu(gal.getQuestionNumber());
					if(MainGUI.getBusinessLogic().emaitzaAldatu(gal.getQuestionNumber(), emaitza)) {
						JOptionPane.showMessageDialog(null, "Galderaren erantzuna ezarri da.");
						MainGUI.getBusinessLogic().apustuaOrdaindu(gal.getQuestionNumber());
						for(Kuota k:Galderaren_KuotaLista) {
							MainGUI.getBusinessLogic().deleteKuota(k.getKuotaId());
						}
						MainGUI.getBusinessLogic().deleteQuestion(gal.getQuestionNumber());
						
					}
				}
				catch(Exception ex) {
					
				}
			}
		});
		kuotasortu.setBounds(331, 432, 85, 21);
		getContentPane().add(kuotasortu);


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


		scrollPaneKuota.setBounds(453, 273, 255, 116);

		scrollPaneEvents.setBounds(new Rectangle(362, 50, 346, 150));



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


		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i=tableQueries.getSelectedRow();
					domain.Question gal=(domain.Question)tableModelQueries.getValueAt(i,2); // obtain ev object
					Vector<Kuota> kot=gal.getKuotalista();
					tableModelKuotak.setDataVector(null, columnNamesKuotak);
					tableModelKuotak.setColumnCount(1);

					for (domain.Kuota q:kot){
						Vector<Object> row = new Vector<Object>();
						row.add(q.getErantzuna());
						tableModelKuotak.addRow(row); 
					}
					tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(340);
					
				}
				catch(Exception p) {

				}
			}
		});

		scrollPaneKuota.setViewportView(tableKuotak);
		tableModelKuotak = new DefaultTableModel(null, columnNamesKuotak);

		tableKuotak.setModel(tableModelKuotak);
		tableKuotak.getColumnModel().getColumn(0).setPreferredWidth(340);
		
		
		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);


		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneKuota, null);

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
			panel.setBounds(131, 464, 479, 33);
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
		kuotasortu.setText(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpiniGUI.kuotasortu.text"));
		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpiniGUI.this.title"));
	}
}