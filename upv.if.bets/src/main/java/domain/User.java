package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User implements Comparable<User>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Izena;
	private String Abizena;
	@XmlID
	@Id
	private String Korreoa;
	private String Pasahitza;
	private String PasahitzaBerretsi;
	private Integer Telefonoa;
	private Integer Adina;
	private Double Dirua;
	private Double Irabaziak;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Mugimenduak> mugimendulista=new Vector<Mugimenduak>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> betlista=new Vector<Bet>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<User> erabiltzaileErreplikatuak=new Vector<User>();
	


	public String getIzena() {
		return Izena;
	}

	public void setIzena(String izena) {
		Izena = izena;
	}

	public String getAbizena() {
		return Abizena;
	}

	public Double getDirua() {
		return Dirua;
	}

	public void setDirua(double d) {
		Dirua += d;
	}

	public void setAbizena(String abizena) {
		Abizena = abizena;
	}

	public String getKorreoa() {
		return Korreoa;
	}

	public void setKorreoa(String korreoa) {
		Korreoa = korreoa;
	}

	public String getPasahitza() {
		return Pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		Pasahitza = pasahitza;
	}

	public String getPasahitzaBerretsi() {
		return PasahitzaBerretsi;
	}

	public void setPasahitzaBerretsi(String pasahitzaBerretsi) {
		PasahitzaBerretsi = pasahitzaBerretsi;
	}

	public Integer getTelefonoa() {
		return Telefonoa;
	}

	public void setTelefonoa(Integer telefonoa) {
		Telefonoa = telefonoa;
	}

	public Integer getAdina() {
		return Adina;
	}

	public void setAdina(Integer adina) {
		Adina = adina;
	}
	
	public Double getIrabaziak() {
		return Irabaziak;
	}
	
	public void setIrabaziak(Double irabaziak) {
		Irabaziak=irabaziak;
	}

	public User(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,
			Integer telefonoa, Integer adina) {
		super();
		Izena = izena;
		Abizena = abizena;
		Korreoa = korreoa;
		Pasahitza = pasahitza;
		PasahitzaBerretsi = pasahitzaBerretsi;
		Telefonoa = telefonoa;
		Adina = adina;
		Dirua=0.0;
	}
	
	public User() {
		super();
	}
	
	public Mugimenduak addMugimendua(Double zenbatdiru, Date data, Integer mugimenduMota)  {
		Mugimenduak m=new Mugimenduak(zenbatdiru, data, mugimenduMota);
		mugimendulista.add(m);
        return m;
	}
	
	public Vector<Mugimenduak> getMugimendulista() {
		return mugimendulista;
	}
	public void setMugimendulista(Vector<Mugimenduak> mugimendulista) {
		this.mugimendulista = mugimendulista;
	}
	
	public Vector<User> geterabiltzaileErreplikatuak() {
		return erabiltzaileErreplikatuak;
	}
	public void seterabiltzaileErreplikatuak(Vector<User> erabiltzaileErreplikatuak) {
		this.erabiltzaileErreplikatuak = erabiltzaileErreplikatuak;
	}

	public Bet addBet(double zenbatDiru,Integer id) {
		Bet b=new Bet(zenbatDiru,id);
		betlista.add(b);
		return b;
	}
	
	
	public Bet addBet(double zenbatDiru,Integer kuotaid, Integer betId,Vector<Integer> m) {
		Bet b=new Bet(zenbatDiru,kuotaid,betId,m);
		betlista.add(b);
		return b;
	}
	
	public void addBetB(Bet b) {
		System.out.println("funtzioan");
		System.out.println(b.getBetId());
		betlista.add(b);
		System.out.println("Sartu da");
		for(Bet be:betlista) {
			System.out.println(be.getBetId());
		}
	}
	
	public Vector<Bet> getbetlista() {
		return betlista;
	}
	public void setbetlista(Vector<Bet> betlista) {
		this.betlista = betlista;
	}
	
	public void removeBet(Bet b) {
		betlista.remove(b);
	}

	public String toString() {
		return this.Korreoa +":"+ this.getDirua()+" : dirua duena.";
		
	}
	
	public int compareTo(User u) {
		return this.Irabaziak.compareTo(u.Irabaziak);
	}
}
