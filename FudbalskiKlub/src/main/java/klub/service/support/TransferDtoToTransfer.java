package klub.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import klub.model.Igrac;
import klub.model.Klub;
import klub.model.Transfer;
import klub.service.IgracService;
import klub.service.KlubService;
import klub.service.TransferService;
import klub.service.web.dto.TransferDTO;

@Component
public class TransferDtoToTransfer implements Converter<TransferDTO, Transfer> {
	
	@Autowired
	private TransferService transferService;
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private KlubService klubService;
	
	@Override
	public Transfer convert(TransferDTO dto) {
		
		Igrac igrac = null;
		if(dto.getIgracId() !=null) {
			igrac = igracService.findOne(dto.getIgracId()).get();
		}
		
		Klub klub = null;
		if(dto.getKlubId() !=null) {
			klub = klubService.findOne(dto.getKlubId()).get();
		}
		
		if(igrac !=null) {
			Long id = dto.getId();
			Transfer transfer = id == null ? new Transfer(): transferService.findOne(id).get();
		
			if(transfer !=null) {
				transfer.setIgrac(igrac);
				if(klub !=null) {
				transfer.setKlub(klub);
			}
				transfer.setCena(dto.getCena());
		}
			return transfer;
	}else {
		throw new IllegalStateException("Trying to attach to non-existant");
	}
	}

}
