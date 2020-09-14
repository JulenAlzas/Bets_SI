package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Bet;
import domain.Event;
import domain.Kuota;
import domain.Question;
import domain.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EkintzaEzabatuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField ida;
	
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private DefaultTableModel tableModelEvents;
	private JTable tableEvents= new JTable();

	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	
	private JPanel panel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblEkintzaEzabatu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Ekintza_ezabatu"));
	private JButton btnEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ezabatu"));
	private JButton btnClose;
	
	private JScrollPane scrollPaneEvents = new JScrollPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EkintzaEzabatuGUI frame = new EkintzaEzabatuGUI();
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
	public EkintzaEzabatuGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Ekintza_ezabatu")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEkintzaEzabatu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEkintzaEzabatu.setBounds(20, 0, 200, 46);
		contentPane.add(lblEkintzaEzabatu);
		
		JLabel lblNewLabel = new JLabel("______________________________________________________________________________________________________________________________________________________________________________________");
		lblNewLabel.setBounds(10, 31, 744, 13);
		contentPane.add(lblNewLabel);
		
		
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

						
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});
		this.getContentPane().add(jCalendar1, null);
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		this.getContentPane().add(scrollPaneEvents, null);
		
		
		
		
//		scrollPaneEkintzak.setViewportView(tableEvents);
		
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableEvents.getSelectedRow();

				Event gertaera =(Event) tableModelEvents.getValueAt(i, 2);
				System.out.println(gertaera.getDescription());
				
				BLFacade facade=MainGUI.getBusinessLogic();
				facade.ekintzaezabatu(gertaera.getEventNumber());
				tableModelEvents.removeRow(i);
				scrollPaneEvents.setViewportView(tableEvents);
				
			}
			
		});
		btnEzabatu.setBounds(553, 211, 85, 21);
		contentPane.add(btnEzabatu);
		contentPane.add(getPanel());
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButtonClose_actionPerformed(arg0);
			}
		});
		btnClose.setBounds(292, 249, 89, 23);
		contentPane.add(btnClose);
		
		
	}
	private void jButtonClose_actionPerformed(ActionEvent e) {
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
			panel.setBounds(40, 284, 598, 33);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		lblEkintzaEzabatu.setText(ResourceBundle.getBundle("Etiquetas").getString("Ekintza_ezabatu"));
		btnEzabatu.setText(ResourceBundle.getBundle("Etiquetas").getString("Ezabatu"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Ekintza_ezabatu"));
		btnClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	}
}
