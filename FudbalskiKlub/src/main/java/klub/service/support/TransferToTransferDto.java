package klub.service.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import klub.model.Transfer;
import klub.service.web.dto.TransferDTO;

@Component
public class TransferToTransferDto implements Converter<Transfer, TransferDTO> {
	
	@Override
	public TransferDTO convert(Transfer transfer) {
		
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setId(transfer.getId());
		if(transfer.getIgrac() !=null) {
			transferDTO.setIgracId(transfer.getIgrac().getId());
		}
		if(transfer.getKlub() !=null) {
			transferDTO.setKlubId(transfer.getKlub().getId());
		}
		transferDTO.setCena(transfer.getCena());
		
		return transferDTO;
	}
	
	public List<TransferDTO> convert (List<Transfer>transferi){
		List<TransferDTO> transferiDTO = new ArrayList<>();
		
		for (Transfer transfer : transferi) {
			transferiDTO.add(convert(transfer));
		}
		
		return transferiDTO;
	}

}
