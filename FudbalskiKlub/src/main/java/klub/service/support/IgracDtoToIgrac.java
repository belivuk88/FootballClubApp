package klub.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import klub.model.Igrac;
import klub.model.Klub;
import klub.service.IgracService;
import klub.service.KlubService;
import klub.service.web.dto.IgracDTO;

@Component
public class IgracDtoToIgrac implements Converter<IgracDTO, Igrac> {
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private KlubService klubService;
	
	@Override
	public Igrac convert(IgracDTO dto) {
		
		Klub klub = null;
		if(dto.getKlubId() !=null) {
			klub = klubService.findOne(dto.getKlubId()).get();
		}
		
		if(klub !=null) {
			Long id = dto.getId();
			Igrac igrac = id == null ? new Igrac(): igracService.findOne(id).get();
		
			if(igrac !=null) {
				igrac.setImePrezime(dto.getImePrezime());
				igrac.setPozicija(dto.getPozicija());
				igrac.setBrojDresa(dto.getBrojDresa());
				igrac.setDatumRodjenja(dto.getDatum());
				igrac.setNaProdaju(dto.isNaProdaju());
				igrac.setKlub(klub);
			}
			
			return igrac;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant");
		}
	}

}
