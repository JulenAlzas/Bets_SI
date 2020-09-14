package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gui.MainGUI;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Kuota implements Serializable {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id 
	@GeneratedValue
	private Integer kuotaId;
	private String erantzuna;
	private double zenbatekoKuota;
	private Integer questionId;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> apustulista=new Vector<Bet>();
	
	@XmlIDREF
	private Question erabiltzaile_question;
	
	public Kuota() {
		super();
	}

	public Vector<Bet> getApustulista() {
		return apustulista;
	}
	
	public Kuota(String erantzuna, double zenbatekoKuota, Integer QuestionId) {
		super();
		this.erantzuna = erantzuna;
		this.zenbatekoKuota = zenbatekoKuota;
		this.questionId = QuestionId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getErantzuna() {
		return erantzuna;
	}
	
	public void removeBet(Bet b) {
		this.apustulista.remove(b);
	}

	public void setErantzuna(String erantzuna) {
		this.erantzuna = erantzuna;
	}

	public double getZenbatekoKuota() {
		return zenbatekoKuota;
	}

	public void setZenbatekoKuota(double zenbatekoKuota) {
		this.zenbatekoKuota = zenbatekoKuota;
	}
	
	public Integer getKuotaId() {
		return kuotaId;
	}

	public void setKuotaId(Integer id) {
		this.kuotaId=id;
	}
	


	
	public Bet addBet(Bet b) {
		apustulista.add(b);
        return b;
	}

	public String toString() {
		return "ID:"+this.kuotaId +"  kuota. Erantzuna:"+ this.getErantzuna();
		
	}



	
}
