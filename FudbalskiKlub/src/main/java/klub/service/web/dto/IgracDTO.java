package klub.service.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class IgracDTO {
	
	private Long id;
	
	private String imePrezime;
	
	private String pozicija;
	@Min( value = 1)
	@Max( value = 99)
	private int brojDresa;
	
	private String datum;
	
	private boolean naProdaju;
	
	private Long klubId;
	
	private String klubNaziv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getPozicija() {
		return pozicija;
	}

	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}

	public int getBrojDresa() {
		return brojDresa;
	}

	public void setBrojDresa(int brojDresa) {
		this.brojDresa = brojDresa;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public boolean isNaProdaju() {
		return naProdaju;
	}

	public void setNaProdaju(boolean naProdaju) {
		this.naProdaju = naProdaju;
	}

	public Long getKlubId() {
		return klubId;
	}

	public void setKlubId(Long klubId) {
		this.klubId = klubId;
	}

	public String getKlubNaziv() {
		return klubNaziv;
	}

	public void setKlubNaziv(String klubNaziv) {
		this.klubNaziv = klubNaziv;
	}

}
