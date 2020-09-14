package dataAccess;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Kuota;
import domain.Mugimenduak;
import domain.Admins;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventAlreadyExist;
import exceptions.QuestionAlreadyExist;
import gui.MainGUI;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c;

	public DataAccess(boolean initializeMode)  {

		c=ConfigXML.getInstance();

		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode)
			fileName=fileName+";drop";

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}
	}

	public DataAccess()  {	
		new DataAccess(false);
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();
		try {


			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}  

			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));

			//			User us1=new User("Ibon","Perez","jaja@gmail.com","aa","aa",943728373,16);
			//			db.persist(us1);

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;



			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

			}


			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);


			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			User berria= new User("aa", "aa", "aa@gmail.com", "aa", "aa",111111111, 20);
			db.persist(berria);
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		q.setEvent(event);
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	public Event createEvent(String description, Date eventDate) {

		db.getTransaction().begin();
		Event ev = new Event(description, eventDate);
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return ev;
	}

	public User createUser(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,
			Integer telefonoa, Integer adina) {
		System.out.println(">> DataAccess: createUser=> Izena= "+izena+" Abizena= "+abizena+" korreoa="+korreoa);

		User us = new User(izena,abizena,korreoa,pasahitza,pasahitzaBerretsi,telefonoa,adina);

		db.getTransaction().begin();
		//db.persist(q);
		db.persist(us); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return us;

	}
	public Admins createAdmin(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,
			Integer telefonoa, Integer adina) {
		System.out.println(">> DataAccess: createUser=> Izena= "+izena+" Abizena= "+abizena+" korreoa="+korreoa);

		Admins us = new Admins(izena,abizena,korreoa,pasahitza,pasahitzaBerretsi,telefonoa,adina);

		db.getTransaction().begin();
		//db.persist(q);
		db.persist(us); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return us;

	}

	public void deleteUser( String korreoa) {
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM User u WHERE u.Korreoa=?1");
		query.setParameter(1, korreoa);
		int deteledUsers = query.executeUpdate();
		System.out.println("Deleted pilots " + deteledUsers);
		db.getTransaction().commit();
	}

	public void deleteBet(Integer betId) {
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM Bet u WHERE u.BetId=?1");
		query.setParameter(1, betId);
		query.executeUpdate();
		System.out.println("Bet deleted");
		db.getTransaction().commit();
	}

	public void deleteBetFromUser(Bet b, String korreoa) {
		db.getTransaction().begin();
		User us = db.find(User.class, korreoa);
		us.removeBet(b);
		db.getTransaction().commit();
	}

	public void deleteBetFromKuota(Bet b, Integer kuotaId) {
		db.getTransaction().begin();
		Kuota k = db.find(Kuota.class, kuotaId);
		k.removeBet(b);
		db.getTransaction().commit();
	}



	public User getUser(String korreoa) {
//		Query query = db.createQuery("SELECT u FROM User u WHERE Korreoa= ?1",User.class);
//		query.setParameter(1, korreoa);
//		User user1= (User) query.getSingleResult();
//		System.out.println(user1.toString());
		return db.find(User.class, korreoa);
	}

	public Admins getadmin(String korreoa) {
		Query query = db.createQuery("SELECT a FROM Admins a WHERE Korreoa= ?1",Admins.class);
		query.setParameter(1, korreoa);
		Admins admina= (Admins) query.getSingleResult();
		return admina;

	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {

		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}



	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public Kuota createKuota(String erantzun, double zenbatekoa,int questionNumber) {
		db.getTransaction().begin();

		Question qu = db.find(Question.class, questionNumber);

		//		Kuota bet = new Kuota(erantzun,zenbatekoa,questionNumber);
		// db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		Kuota k = qu.addKuota(erantzun, zenbatekoa, questionNumber);

		db.persist(k);
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)

		db.getTransaction().commit();
		return k;


	}

	public boolean adminetandago(String korreoa) {
		try {
			Query query = db.createQuery("SELECT a FROM Admins a WHERE Korreoa= ?1",Admins.class);
			query.setParameter(1, korreoa);
			query.getSingleResult();
			return true;

		} catch (NoResultException e) {
			return false;
		}



	}

	public Vector<Kuota> getKuotak(Integer questionN) {

		Vector<Kuota> res = new Vector<Kuota>();	
		TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q WHERE q. questionNumber=?1",Question.class);   
		query.setParameter(1, questionN);

		Question q1 = query.getSingleResult();
		res= q1.getKuotalista();

		return res;
	}


	public boolean emaitzaAldatu(Integer questionNumber,String result) {
		Question galdera = db.find(Question.class, questionNumber);
		Vector<Kuota> kuotak=galdera.getKuotalista();
		for(Kuota i:kuotak) {
			if(i.getErantzuna().equals(result)) {
				db.getTransaction().begin();
				galdera.setresult(result);
				db.getTransaction().commit();
				return true;
			}
		}
		return false;
	}


	public void apustuaOrdaindu(Integer questionNumber) {
		Question q = db.find(Question.class, questionNumber);
		String emaitza=q.getresult();
		Vector<Kuota> kuotak=q.getKuotalista();
		for(Kuota k: kuotak) {
			if(k.getErantzuna().equals(emaitza)) {
				Vector<Bet> irabazleak=k.getApustulista();
				for(Bet b:irabazleak) {
					if(b.getApustuAnitza()!=null) {
						Double irabazia=0.0;
						Double zenbat = b.getZenbatDiru();
						Double zenbat2=k.getZenbatekoKuota();
						System.out.println(zenbat.toString());
						System.out.println(zenbat.toString());
						irabazia= zenbat*zenbat2;

						if(b.getApustuAnitza().isEmpty()) {
							//apustu bakarra ordaindu
							List<User> erabiltzaileak=getUserList();
							for(User u:erabiltzaileak) {
								if(u.getbetlista().contains(b)) {
									apustutakoaeman(u.getKorreoa(),irabazia);
									dirumugimendua(irabazia, u.getKorreoa(),1);
									deleteBetFromUser(b,u.getKorreoa());
								}
							}
						}
						else {
							System.out.println("Apustu anitza da, ondoren inplementatuko dugu.");
							List<User> erabiltzaileak=getUserList();
							for(User u:erabiltzaileak) {
								if(u.getbetlista().contains(b)) {
									Integer nunGorde = b.getApustuAnitza().get(0);
									Bet nun = getBet(nunGorde);
									db.getTransaction().begin();
									nun.setZenbatDiru(irabazia);
									db.getTransaction().commit();
									deleteBetFromUser(b,u.getKorreoa());
									deleteBet(b.getBetId());
									for(Integer i: b.getApustuAnitza()) {
										Bet gehitu = getBet(i);
										for(Integer j : gehitu.getApustuAnitza()) {
											if(j.equals(b.getBetId())) {
												db.getTransaction().begin();
												gehitu.getApustuAnitza().remove(j);
												db.getTransaction().commit();
												break;
											}
										}
									}

								}
							}
						}
					}

				}
			}
			else {
				Vector<Bet> galtzaileak=k.getApustulista();
				System.out.println(galtzaileak.toString());
				for(Bet g:galtzaileak) {
					if(g.getApustuAnitza()!=null) {
						if(g.getApustuAnitza().isEmpty()) {
							//apustu bakarrako apustuak galdu
							List<User> erabiltzaileak=getUserList();
							for(User u:erabiltzaileak) {
								if(u.getbetlista().contains(g)) {
									deleteBetFromUser(g,u.getKorreoa());
								}
							}
						}
						else {

							List<User> erabiltzaileak=getUserList();
							for(User u:erabiltzaileak) {
								if(u.getbetlista().contains(g)) {
									deleteBetFromUser(g,u.getKorreoa());
									for(Integer i:g.getApustuAnitza()) {
										deleteBetFromUser(getBet(i),u.getKorreoa());
									}
								}
							}
						}
					}


				}

			}
		}
		//System.out.println(emaitza.toString());



		//Kuota kuota = db.find(Kuota.class, kuotaIrabazlea);


	}

	public void kuotaezarri(Integer questionNumber,String erantzuna, double zenbatekoKuota) {
		Question q = db.find(Question.class, questionNumber);
		Kuota k= new Kuota(erantzuna, zenbatekoKuota,questionNumber);

		db.getTransaction().begin();
		q.getKuotalista().add(k);
		db.getTransaction().commit();

	}


	public Bet createBet(double zenbatDiru, Integer kuotaId,Integer originala, String korreoa) {
		db.getTransaction().begin();
		Vector<Integer> m = new Vector<Integer>();
		User us1= db.find(User.class, korreoa);
		Bet b2=us1.addBet(zenbatDiru,kuotaId,originala,m);
		db.persist(b2);

		Kuota ku = db.find(Kuota.class, kuotaId);
		Bet b = ku.addBet(b2);		

		db.getTransaction().commit();
		return b;
	}


	public Double diruasartu(String text, Double diruaSartu) {
		db.getTransaction().begin();

		User u = db.find(User.class, text);

		u.setDirua(diruaSartu);

		db.getTransaction().commit();

		return u.getDirua();
	}

	public Double diruaLortu(String text) {
		db.getTransaction().begin();

		User u = db.find(User.class, text);

		Double zenbat= u.getDirua();

		db.getTransaction().commit();

		return zenbat;
	}

	public void dirumugimendua(Double zenbatdiru, String erab, Integer mugimenduMota) {

		User u = db.find(User.class, erab);

		Date currentdate = new Date();

		Mugimenduak m= new Mugimenduak(zenbatdiru, currentdate, mugimenduMota);

		db.getTransaction().begin();
		u.getMugimendulista().add(m);
		db.getTransaction().commit();

	}

	public void apustutakoakendu(String email, Double zenbat) {
		User u = db.find(User.class, email);

		db.getTransaction().begin();
		u.setDirua(-zenbat);
		db.getTransaction().commit();

	}

	public void apustutakoaeman(String email, Double zenbat) {
		User u = db.find(User.class, email);

		db.getTransaction().begin();
		u.setDirua(zenbat);
		db.getTransaction().commit();

	}

	public Vector<User> getErabiltzaileak(){
		TypedQuery<User> query = db.createQuery("SELECT m FROM User m",User.class);
		List<User> erabiltzaileak = query.getResultList();
		Vector<User> users= new Vector<User>();
		for(User u:erabiltzaileak) {
			users.add(u);
		}
		return users;
	}

	public Double etekinakLortu() {
		TypedQuery<Mugimenduak> query = db.createQuery("SELECT m FROM Mugimenduak m",Mugimenduak.class);   
		List<Mugimenduak> mugimenduList = query.getResultList();
		//db.getTransaction().begin();
		Double batuketa=0.0;
		for(Mugimenduak m1: mugimenduList) {
			System.out.println(m1.getDirualdaketa());
			if(m1.getMugimenduMota()==1) {
				batuketa+=m1.getDirualdaketa();
			}
		}
		//db.getTransaction().commit();
		return batuketa*(-1);
	}

	public Vector<Kuota> kuotalortu(int galderaID) {
		// TODO Auto-generated method stub
		Question q = db.find(Question.class, galderaID);
		return q.getKuotalista();
	}

	public void addErreplikatua(String zein, String zeini) {
		User u = db.find(User.class, zeini);
		db.getTransaction().begin();
		User u1 = db.find(User.class, zein);
		u.geterabiltzaileErreplikatuak().add(u1);
		db.getTransaction().commit();
	}

	public void addBetUser(User erab, Bet b) {
		System.out.println("--------------------------------------");
		String korreoa = erab.getKorreoa();
		System.out.println("lenagio");
		User u= db.find(User.class, korreoa);
		System.out.println("berandu");
		System.out.println(u.getKorreoa());
		db.getTransaction().begin();
		u.getbetlista().add(b);
		db.getTransaction().commit();
	}

	public void idListaGehitu(Bet apustu, Vector<Integer> lista) {
		Bet b= db.find(Bet.class, apustu.getBetId());
		db.getTransaction().begin();
		b.setApustuAnitza(lista);
		db.getTransaction().commit();
	}

	public void erreplikatutikKendu(String i, String j) {
		User us1= db.find(User.class, i);
		User us2= db.find(User.class, j);
		db.getTransaction().begin();
		us2.geterabiltzaileErreplikatuak().remove(us1);
		db.getTransaction().commit();
	}

	public Bet getBet(Integer id) {
		Bet b = db.find(Bet.class, id);
		return b;
	}

	public void deleteQuestion( Integer questionNumber) {
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM Question q WHERE q.questionNumber=?1");
		query.setParameter(1, questionNumber);
		int deteledqst = query.executeUpdate();
		System.out.println("Deleted questions " + deteledqst);
		db.getTransaction().commit();
	}

	public void deleteKuota( Integer KuotaId) {
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM Kuota k WHERE k.kuotaId=?1");
		query.setParameter(1, KuotaId);
		int deteledkuota = query.executeUpdate();
		System.out.println("Deleted questions " + deteledkuota);
		db.getTransaction().commit();
	}

	public void deleteEvent( Integer eventNumber) {
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM Event e WHERE e.eventNumber=?1");
		query.setParameter(1, eventNumber);
		int deteledevent = query.executeUpdate();
		System.out.println("Deleted event " + deteledevent);
		db.getTransaction().commit();
	}

	public Event getEvent(Integer eventNumber) {
		db.getTransaction().begin();
		Event ev = db.find(Event.class, eventNumber);
		db.getTransaction().commit();
		return ev;
	}

	public List<User> getUserList() {
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u",User.class);   
		List<User> userlist = query.getResultList();
		return userlist;
	}

	public void ekintzaezabatu(Integer eventnumber) {

		Event ev=getEvent(eventnumber);
		List<User> erabLista=getUserList();

		for(Question q: ev.getQuestions()) {
			if(q.getresult()==null) {
				for(Kuota k: q.getKuotalista()) {
					if(k!=null) {
						for(Bet b: k.getApustulista()) {
							if(b!=null) {
								for(User u: erabLista) {
									for(Bet userbet: u.getbetlista()) {
										if(userbet!=null) {
											if(userbet.getBetId().compareTo(b.getBetId())==0) {
												if(userbet.getZenbatDiru()!=null) {
													Double dirua = userbet.getZenbatDiru();
													System.out.println(dirua);
													u.setDirua(dirua);
													System.out.println("pasa da*-***********");
													dirumugimendua(dirua, u.getKorreoa(),1);
													deleteBet(userbet.getBetId());
												}
											}
										}

									}	
								}
							}
						}
						deleteKuota(k.getKuotaId());
					}
				}
			}else {
				for(Kuota k: q.getKuotalista()) {
					if(k!=null){
						for(Bet b1: k.getApustulista()) {
							if(b1!=null) {
								deleteBet(b1.getBetId());
							}
						}
						deleteKuota(k.getKuotaId());
					}
				}
			}
			if(q!=null) {
				deleteQuestion(q.getQuestionNumber());
			}

		}
		deleteEvent(eventnumber);
	}

	public Kuota getKuota(Integer id) {
		Kuota b = db.find(Kuota.class, id);
		return b;
	}

	public void apustuDiruaAldatu(Integer id, Double dirua) {
		db.getTransaction().begin();
		Bet b = db.find(Bet.class, id);
		b.setZenbatDiru(dirua);
		db.getTransaction().commit();
	}

	public List<User> topErabiltzaileak() {
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u",User.class);   
		List<User> userlist = query.getResultList();
		for(User us:userlist) {
			irabaziakGehitu(us);
		}
		Collections.sort(userlist);
		return userlist;
	}

	private void irabaziakGehitu(User u) {
		Double profit=0.0;
		for(Mugimenduak m:u.getMugimendulista()) {
			if(m.getMugimenduMota()==1)
				profit+=m.getDirualdaketa();
		}

		db.getTransaction().begin();
		u.setIrabaziak(profit);
		db.getTransaction().commit();
	}


}
