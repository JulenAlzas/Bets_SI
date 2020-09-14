package domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Admins implements Serializable {
	
	private String Izena;
	private String Abizena;
	@XmlID
	@Id
	private String Korreoa;
	private String Pasahitza;
	private String PasahitzaBerretsi;
	private Integer Telefonoa;
	private Integer Adina;
	
	public String getIzena() {
		return Izena;
	}

	public void setIzena(String izena) {
		Izena = izena;
	}

	public String getAbizena() {
		return Abizena;
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

	public Admins(String izena, String abizena, String korreoa, String pasahitza, String pasahitzaBerretsi,
			Integer telefonoa, Integer adina) {
		super();
		Izena = izena;
		Abizena = abizena;
		Korreoa = korreoa;
		Pasahitza = pasahitza;
		PasahitzaBerretsi = pasahitzaBerretsi;
		Telefonoa = telefonoa;
		Adina = adina;
	}
	
	public Admins() {
		super();
	}
	
	

	
}
