package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.RollbackException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.User;
import domain.Kuota;
import domain.Admins;
import domain.Bet;
import domain.Event;
import exceptions.EventAlreadyExist;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import gui.MainGUI;
import gui.RegisterGUI;
import gui.UserGUI;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {


	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();


		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		//The minimum bed must be greater than 0
		DataAccess dBManager=new DataAccess();
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		qry=dBManager.createQuestion(event,question,betMinimum);		

		dBManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod	
	public Vector<Event> getEvents(Date date)  {
		DataAccess dbManager=new DataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	@WebMethod
	public Vector<Kuota> getKuotak(Integer questionN){
		DataAccess dbManager=new DataAccess();
		Vector<Kuota>  kuotak=dbManager.getKuotak(questionN);
		dbManager.close();
		return kuotak;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		DataAccess dbManager=new DataAccess();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}




	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod	
	public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}

	@WebMethod
	public User createUser(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,
			Integer telefonoa, Integer adina) {
		DataAccess dBManager=new DataAccess();
		User qry=null;

		qry=dBManager.createUser(izena, abizena, korreoa,pasahitza,pasahitzaBerretsi,telefonoa,adina);		

		dBManager.close();

		return qry;
	}
	@WebMethod
	public Admins createAdmin(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,Integer telefonoa, Integer adina){
		DataAccess dBManager=new DataAccess();
		Admins qry=null;

		qry=dBManager.createAdmin(izena, abizena, korreoa,pasahitza,pasahitzaBerretsi,telefonoa,adina);		

		dBManager.close();

		return qry;
	}

	@WebMethod
	public void register(String izena, String abizena, String korreoa, String pasahitza1, String pasahitza2, int telefonoa, int adina) {
		DataAccess d1 = new DataAccess();

		d1.createUser(izena, abizena, korreoa, pasahitza1, pasahitza2, telefonoa, adina);

	}

	@WebMethod
	public void login(String korreoa, String pasahitza) throws Exception {
		DataAccess d1 = new DataAccess();
		boolean adminada=d1.adminetandago(korreoa);
		if (!adminada) {
			User qry=d1.getUser(korreoa);
			if(qry==null) {
				throw new Exception();
			}
			if(qry.getPasahitza().equals(pasahitza)) {
				JFrame main = new UserGUI(qry);
				main.setVisible(true);
			}else {
				System.out.println("Pasahitz okerra");
			}
		}else {
			Admins qry=d1.getadmin(korreoa);
			if(qry.getPasahitza().equals(pasahitza)) {
				JFrame main = new MainGUI();
				main.setVisible(true);
			}else {
				System.out.println("Pasahitz okerra");
			}
			d1.close();
		}


	}
	@WebMethod
	public Event createEvent(String description,Date eventDate) {

		DataAccess dBManager=new DataAccess();
		Event qry=null;

		qry=dBManager.createEvent(description,eventDate);		

		dBManager.close();

		return qry;
	};

	@WebMethod
	public Kuota createKuota(String erantzun, double zenbatekoa,int questionNumber) {
		DataAccess dBManager=new DataAccess();
		Kuota qry=null;

		qry=dBManager.createKuota(erantzun, zenbatekoa,questionNumber);  

		dBManager.close();
		return qry;

	}
	@WebMethod
	public boolean emaitzaAldatu(Integer questionNumber,String result) {
		DataAccess dBManager=new DataAccess();

		return dBManager.emaitzaAldatu(questionNumber,result);  

	}
	@WebMethod
	public void kuotaezarri(Integer questionNumber,String erantzuna, double zenbatekoKuota) {
		DataAccess dBManager=new DataAccess();

		dBManager.kuotaezarri(questionNumber,erantzuna, zenbatekoKuota);  

		dBManager.close();
	}


	@WebMethod
	public Bet createBet(double zenbatDiru, Integer kuotaId,Integer originala,String korreoa) {
		DataAccess dBManager=new DataAccess();
		Bet qry=null;

		qry=dBManager.createBet(zenbatDiru,kuotaId,originala,korreoa);  

		dBManager.close();
		return qry;
	}


	@WebMethod
	public Double diruasartu(String text, Double zenbatdiru) {
		DataAccess dBManager=new DataAccess();

		Double erab_dirua= dBManager.diruasartu(text,zenbatdiru);  

		dBManager.close();

		return erab_dirua;
	}

	@WebMethod
	public void dirumugimendua(Double zenbatdiru, String erab, Integer mugimenduMota) {
		DataAccess dBManager=new DataAccess();

		dBManager.dirumugimendua(zenbatdiru,erab, mugimenduMota);  

		dBManager.close();

	}

	@WebMethod
	public User getUser(String korreoa) {
		DataAccess dBManager=new DataAccess();

		User user=dBManager.getUser(korreoa);  

		dBManager.close();
		return user;
	}

	@WebMethod
	public void apustutakoakendu(String email, Double zenbat) {
		DataAccess dBManager=new DataAccess();

		dBManager.apustutakoakendu(email,zenbat);  

		dBManager.close();

	}

	@WebMethod
	public void removeBet(Integer kuotaId, String korreoa, Bet b) {
		DataAccess dBManager=new DataAccess();
		dBManager.deleteBet(b.getBetId());
		dBManager.deleteBetFromUser(b,korreoa);
		dBManager.deleteBetFromKuota(b,kuotaId);
		dBManager.close();
	}
	@WebMethod
	public Double etekinakLortu() {
		DataAccess dBManager=new DataAccess();
		Double etekina= dBManager.etekinakLortu();
		dBManager.close();
		return etekina;
	}
	@WebMethod
	public Vector<Kuota> kuotalortu(int GalderaID) {
		DataAccess dBManager=new DataAccess();
		Vector<Kuota> k= dBManager.kuotalortu(GalderaID);
		dBManager.close();
		return k;
	}

	@WebMethod
	public void addErreplikatua(String zein, String zeini) {
		DataAccess dBManager=new DataAccess();
		dBManager.addErreplikatua(zein,zeini);
		dBManager.close();

	}

	@WebMethod
	public void idListaGehitu(Bet apustu, Vector<Integer> lista) {
		DataAccess dBManager=new DataAccess();
		dBManager.idListaGehitu(apustu, lista);
		dBManager.close();

	}

	@WebMethod
	public void addBetUser(User erab, Bet b) {
		DataAccess dBManager=new DataAccess();
		dBManager.addBetUser(erab, b);
		dBManager.close();
	}

	@WebMethod
	public Bet getBet(Integer id) {
		DataAccess dBManager=new DataAccess();
		Bet b=dBManager.getBet(id);
		dBManager.close();
		return b;
	}

	@WebMethod
	public Vector<User> getErabiltzaileak() {
		DataAccess dBManager=new DataAccess();
		Vector<User> b=dBManager.getErabiltzaileak();
		dBManager.close();
		return b;
	}

	@WebMethod
	public Double diruaLortu(String korreoa) {
		DataAccess dBManager=new DataAccess();

		Double erab_dirua= dBManager.diruaLortu(korreoa);  

		dBManager.close();

		return erab_dirua;
	}

	@WebMethod
	public void erreplikatutikKendu(String i, String j) {
		DataAccess dBManager=new DataAccess();

		dBManager.erreplikatutikKendu(i,j);  

		dBManager.close();

	}
	@WebMethod
	public void deleteQuestion( Integer questionNumber) {
		DataAccess dBManager=new DataAccess();

		dBManager.deleteQuestion(questionNumber); 

		dBManager.close();

	}
	@WebMethod
	public void deleteKuota( Integer KuotaId) {
		DataAccess dBManager=new DataAccess();

		dBManager.deleteKuota(KuotaId); 

		dBManager.close();
	}
	@WebMethod
	public void deleteEvent( Integer eventNumber) {
		DataAccess dBManager=new DataAccess();

		dBManager.deleteEvent(eventNumber); 

		dBManager.close();
	}
	@WebMethod
	public Event getEvent(Integer eventNumber) {
		DataAccess dBManager=new DataAccess();

		Event e=dBManager.getEvent(eventNumber); 

		dBManager.close();
		return e;
	}
	@WebMethod
	public List<User> getUserList(){
		DataAccess dBManager=new DataAccess();

		List<User> lista=dBManager.getUserList(); 

		dBManager.close();
		return lista;
	}
	@WebMethod
	public void ekintzaezabatu(Integer eventnumber) {
		DataAccess dBManager=new DataAccess();

		dBManager.ekintzaezabatu(eventnumber); 

		dBManager.close();
	}
	@WebMethod
	public Kuota getKuota(Integer id) {
		DataAccess dBManager=new DataAccess();

		Kuota k = dBManager.getKuota(id);  

		dBManager.close();
		return k;
	}

	@WebMethod
	public void apustuDiruaAldatu(Integer id, Double dirua) {
		DataAccess dBManager=new DataAccess();

		dBManager.apustuDiruaAldatu(id,dirua);  

		dBManager.close();

	}

	@WebMethod
	public void apustuaOrdaindu(Integer questionNumber) {
		DataAccess dBManager=new DataAccess();

		dBManager.apustuaOrdaindu(questionNumber);

		dBManager.close();
	}
	@WebMethod
	public List<User> topErabiltzaileak() {
		DataAccess dBManager=new DataAccess();
		List<User> topUsers= dBManager.topErabiltzaileak();
		dBManager.close();
		return topUsers;
	}

}

