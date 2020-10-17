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
	DataAccess dbManager;

	public BLFacadeImplementation(DataAccess da) {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();
		}
		dbManager=da;
	}

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

		dbManager.open(false);
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();

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
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	@WebMethod
	public Vector<Kuota> getKuotak(Integer questionN){
		dbManager.open(false);
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
		dbManager.open(false);
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
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@WebMethod
	public User createUser(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,
			Integer telefonoa, Integer adina) {
		dbManager.open(false);
		User qry=null;

		qry=dbManager.createUser(izena, abizena, korreoa,pasahitza,pasahitzaBerretsi,telefonoa,adina);		

		dbManager.close();

		return qry;
	}
	@WebMethod
	public Admins createAdmin(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,Integer telefonoa, Integer adina){
		dbManager.open(false);
		Admins qry=null; 

		qry=dbManager.createAdmin(izena, abizena, korreoa,pasahitza,pasahitzaBerretsi,telefonoa,adina);		

		dbManager.close();

		return qry;
	}

	@WebMethod
	public void register(String izena, String abizena, String korreoa, String pasahitza1, String pasahitza2, int telefonoa, int adina) {
		dbManager.open(false);
		
		dbManager.createUser(izena, abizena, korreoa, pasahitza1, pasahitza2, telefonoa, adina);

	}

	@WebMethod
	public void login(String korreoa, String pasahitza) throws Exception {
		dbManager.open(false);
		boolean adminada=dbManager.adminetandago(korreoa);
		if (!adminada) {
			User qry=dbManager.getUser(korreoa);
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
			Admins qry=dbManager.getadmin(korreoa);
			if(qry.getPasahitza().equals(pasahitza)) {
				JFrame main = new MainGUI();
				main.setVisible(true);
			}else {
				System.out.println("Pasahitz okerra");
			}
			dbManager.close();
		}


	}
	@WebMethod
	public Event createEvent(String description,Date eventDate) {

		dbManager.open(false);
		Event qry=null;

		qry=dbManager.createEvent(description,eventDate);		

		dbManager.close();

		return qry;
	};

	@WebMethod
	public Kuota createKuota(String erantzun, double zenbatekoa,int questionNumber) {
		dbManager.open(false);
		Kuota qry=null;

		qry=dbManager.createKuota(erantzun, zenbatekoa,questionNumber);  

		dbManager.close();
		return qry;

	}
	@WebMethod
	public boolean emaitzaAldatu(Integer questionNumber,String result) {
		dbManager.open(false);

		return dbManager.emaitzaAldatu(questionNumber,result);  

	}
	@WebMethod
	public void kuotaezarri(Integer questionNumber,String erantzuna, double zenbatekoKuota) {
		dbManager.open(false);

		dbManager.kuotaezarri(questionNumber,erantzuna, zenbatekoKuota);  

		dbManager.close();
	}


	@WebMethod
	public Bet createBet(double zenbatDiru, Integer kuotaId,Integer originala,String korreoa) {
		dbManager.open(false);
		Bet qry=null;

		qry=dbManager.createBet(zenbatDiru,kuotaId,originala,korreoa);  

		dbManager.close();
		return qry;
	}


	@WebMethod
	public Double diruasartu(String text, Double zenbatdiru) {
		dbManager.open(false);

		Double erab_dirua= dbManager.diruasartu(text,zenbatdiru);  

		dbManager.close();

		return erab_dirua;
	}

	@WebMethod
	public void dirumugimendua(Double zenbatdiru, String erab, Integer mugimenduMota) {
		dbManager.open(false);

		dbManager.dirumugimendua(zenbatdiru,erab, mugimenduMota);  

		dbManager.close();

	}

	@WebMethod
	public User getUser(String korreoa) {
		dbManager.open(false);

		User user=dbManager.getUser(korreoa);  

		dbManager.close();
		return user;
	}

	@WebMethod
	public void apustutakoakendu(String email, Double zenbat) {
		dbManager.open(false);

		dbManager.apustutakoakendu(email,zenbat);  

		dbManager.close();

	}

	@WebMethod
	public void removeBet(Integer kuotaId, String korreoa, Bet b) {
		dbManager.open(false);
		dbManager.deleteBet(b.getBetId());
		dbManager.deleteBetFromUser(b,korreoa);
		dbManager.deleteBetFromKuota(b,kuotaId);
		dbManager.close();
	}
	@WebMethod
	public Double etekinakLortu() {
		dbManager.open(false);
		Double etekina= dbManager.etekinakLortu();
		dbManager.close();
		return etekina;
	}
	@WebMethod
	public Vector<Kuota> kuotalortu(int GalderaID) {
		dbManager.open(false);
		Vector<Kuota> k= dbManager.kuotalortu(GalderaID);
		dbManager.close();
		return k;
	}

	@WebMethod
	public void addErreplikatua(String zein, String zeini) {
		dbManager.open(false);
		dbManager.addErreplikatua(zein,zeini);
		dbManager.close();

	}

	@WebMethod
	public void idListaGehitu(Bet apustu, Vector<Integer> lista) {
		dbManager.open(false);
		dbManager.idListaGehitu(apustu, lista);
		dbManager.close();

	}

	@WebMethod
	public void addBetUser(User erab, Bet b) {
		dbManager.open(false);
		dbManager.addBetUser(erab, b);
		dbManager.close();
	}

	@WebMethod
	public Bet getBet(Integer id) {
		dbManager.open(false);
		Bet b=dbManager.getBet(id);
		dbManager.close();
		return b;
	}

	@WebMethod
	public Vector<User> getErabiltzaileak() {
		dbManager.open(false);
		Vector<User> b=dbManager.getErabiltzaileak();
		dbManager.close();
		return b;
	}

	@WebMethod
	public Double diruaLortu(String korreoa) {
		dbManager.open(false);

		Double erab_dirua= dbManager.diruaLortu(korreoa);  

		dbManager.close();

		return erab_dirua;
	}

	@WebMethod
	public void erreplikatutikKendu(String i, String j) {
		dbManager.open(false);

		dbManager.erreplikatutikKendu(i,j);  

		dbManager.close();

	}
	@WebMethod
	public void deleteQuestion( Integer questionNumber) {
		dbManager.open(false);

		dbManager.deleteQuestion(questionNumber); 

		dbManager.close();

	}
	@WebMethod
	public void deleteKuota( Integer KuotaId) {
		dbManager.open(false);

		dbManager.deleteKuota(KuotaId); 

		dbManager.close();
	}
	@WebMethod
	public void deleteEvent( Integer eventNumber) {
		dbManager.open(false);

		dbManager.deleteEvent(eventNumber); 

		dbManager.close();
	}
	@WebMethod
	public Event getEvent(Integer eventNumber) {
		dbManager.open(false);

		Event e=dbManager.getEvent(eventNumber); 

		dbManager.close();
		return e;
	}
	@WebMethod
	public List<User> getUserList(){
		dbManager.open(false);

		List<User> lista=dbManager.getUserList(); 

		dbManager.close();
		return lista;
	}
	@WebMethod
	public void ekintzaezabatu(Integer eventnumber) {
		dbManager.open(false);

		dbManager.ekintzaezabatu(eventnumber); 

		dbManager.close();
	}
	@WebMethod
	public Kuota getKuota(Integer id) {
		dbManager.open(false);

		Kuota k = dbManager.getKuota(id);  

		dbManager.close();
		return k;
	}

	@WebMethod
	public void apustuDiruaAldatu(Integer id, Double dirua) {
		dbManager.open(false);

		dbManager.apustuDiruaAldatu(id,dirua);  

		dbManager.close();

	}

	@WebMethod
	public void apustuaOrdaindu(Integer questionNumber) {
		dbManager.open(false);
		dbManager.apustuaOrdaindu(questionNumber);

		dbManager.close();
	}
	@WebMethod
	public List<User> topErabiltzaileak() {
		dbManager.open(false);
		List<User> topUsers= dbManager.topErabiltzaileak();
		dbManager.close();
		return topUsers;
	}

}

