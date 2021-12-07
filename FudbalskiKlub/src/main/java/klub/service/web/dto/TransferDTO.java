package klub.service.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TransferDTO {
	
	private Long id;
	
	private Long igracId;
	
	private Long klubId;
	
	@NotNull
	@Positive( message = "Cena mora biti pozitivan broj.")
	private int cena;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIgracId() {
		return igracId;
	}

	public void setIgracId(Long igracId) {
		this.igracId = igracId;
	}

	public Long getKlubId() {
		return klubId;
	}

	public void setKlubId(Long klubId) {
		this.klubId = klubId;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

}
