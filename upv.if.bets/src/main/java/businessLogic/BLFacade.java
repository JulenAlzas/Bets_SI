package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Admins;
import domain.Bet;
import domain.Event;
import domain.Kuota;
import domain.User;
import exceptions.EventAlreadyExist;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	@WebMethod User createUser(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,
			Integer telefonoa, Integer adina);
	
	@WebMethod User getUser( String korreoa);
	
	@WebMethod Kuota getKuota(Integer id);
	
	@WebMethod void apustuDiruaAldatu(Integer id, Double dirua);
	
	@WebMethod void erreplikatutikKendu( String i, String j);
	
	@WebMethod void addBetUser(User erab, Bet b);
	
	@WebMethod Admins createAdmin(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,
			Integer telefonoa, Integer adina);
	
	@WebMethod Event createEvent(String description,Date eventDate);
	
	@WebMethod void addErreplikatua(String zein,String zeini);

	@WebMethod void login(String korreoa, String pasahitza)throws Exception;
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Kuota> getKuotak(Integer questionN);
	
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public void removeBet(Integer kuotaId, String korreoa, Bet b);

	@WebMethod public Kuota createKuota(String erantzun, double zenbatekoa,int questionNumber);
	
	@WebMethod public Bet createBet(double zenbatDiru,Integer kuotaId,Integer originala,String korreoa);
	
	@WebMethod public void idListaGehitu(Bet apustu, Vector<Integer> lista);
	
	@WebMethod public Bet getBet(Integer id);
	
	@WebMethod public boolean emaitzaAldatu(Integer questionNumber,String result);
	
	@WebMethod public void apustuaOrdaindu(Integer questionNumber);
	
	@WebMethod public void kuotaezarri(Integer questionNumber,String erantzuna, double zenbatekoKuota);

	@WebMethod public Double diruasartu(String text, Double zenbatdiru);
	
	@WebMethod public Double diruaLortu(String korreoa);
	
	@WebMethod public Vector<User> getErabiltzaileak();

	@WebMethod public void dirumugimendua(Double zenbatdiru, String erab, Integer mugimenduMota);

	@WebMethod public void apustutakoakendu(String email, Double zenbat);
	
	@WebMethod public Double etekinakLortu();

	@WebMethod public void register(String izena, String abizena, String korreoa, String pasahitza1, String pasahitza2, int telefonoa, int adina);

	@WebMethod public Vector<Kuota> kuotalortu(int GalderaID);
	
	@WebMethod public void deleteQuestion( Integer questionNumber);
	
	@WebMethod public void deleteKuota( Integer KuotaId);
	
	@WebMethod public void deleteEvent( Integer eventNumber);
	
	@WebMethod public Event getEvent(Integer eventNumber);
	
	@WebMethod public List<User> getUserList();

	@WebMethod public void ekintzaezabatu(Integer eventnumber);
	
	@WebMethod public List<User> topErabiltzaileak();
	
	


	
}
