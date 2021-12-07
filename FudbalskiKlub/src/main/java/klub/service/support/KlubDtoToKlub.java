package klub.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import klub.model.Klub;
import klub.service.KlubService;
import klub.service.web.dto.KlubDTO;

@Component
public class KlubDtoToKlub implements Converter<KlubDTO, Klub > {
	
	@Autowired
	private KlubService klubService;
	
	@Override
	public Klub convert (KlubDTO dto) {
	
	Klub klub;
	
	if(dto.getId() == null) {
		klub = new Klub();
	}else {
		klub = klubService.findOne(dto.getId()).get();
	}
	if(klub !=null) {
		klub.setNaziv(dto.getNaziv());
		klub.setBudzet(dto.getBudzet());
	}
	
	return klub;

}
}
