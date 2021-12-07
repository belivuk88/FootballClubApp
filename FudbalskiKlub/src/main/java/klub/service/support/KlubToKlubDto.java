package klub.service.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import klub.model.Klub;
import klub.service.web.dto.KlubDTO;

@Component
public class KlubToKlubDto implements Converter<Klub, KlubDTO> {
	
	@Override
	public KlubDTO convert (Klub klub) {
		KlubDTO klubDTO = new KlubDTO();
		klubDTO.setId(klub.getId());
		klubDTO.setNaziv(klub.getNaziv());
		klubDTO.setBudzet(klub.getBudzet());
		
		return klubDTO;
	}
	
	public List<KlubDTO> convert (List<Klub>klubovi){
		List<KlubDTO>kluboviDTO = new ArrayList<>();
		
		for(Klub klub : klubovi) {
			kluboviDTO.add(convert(klub));
		}
		
		return kluboviDTO;
	}

}
