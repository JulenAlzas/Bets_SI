package domain;

import java.io.*;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private String result;
	@XmlIDREF
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Kuota> kuotalista=new Vector<Kuota>();

	public Vector<Kuota> getKuotalista() {
		return kuotalista;
	}

	public void setKuotalista(Vector<Kuota> kuotaLista) {
		this.kuotalista = kuotaLista;
	}

	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}
	
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;

		//this.event = event;
	}

	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}


	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}



	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}


	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @return the the query result
	 */
	public String getresult() {
		return result;
	}



	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}



	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}




	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}
	
	public Kuota addKuota(String erantzuna, double zenbatekoKuota, Integer QuestionId)  {
		Kuota k=new Kuota(erantzuna,zenbatekoKuota,QuestionId);
		kuotalista.add(k);
        return k;
	}
	
	public void setresult(String newresut) {
		this.result= newresut;
	}

	
	
}