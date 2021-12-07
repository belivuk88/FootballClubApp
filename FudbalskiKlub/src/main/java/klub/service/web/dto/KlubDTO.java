package klub.service.web.dto;

public class KlubDTO {
	
	private Long id;
	
	private String naziv;
	
	private int budzet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getBudzet() {
		return budzet;
	}

	public void setBudzet(int budzet) {
		this.budzet = budzet;
	}
	
	

}
