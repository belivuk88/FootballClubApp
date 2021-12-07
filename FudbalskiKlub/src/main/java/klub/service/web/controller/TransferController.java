package klub.service.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import klub.model.Transfer;
import klub.service.TransferService;
import klub.service.support.TransferToTransferDto;
import klub.service.web.dto.TransferDTO;

@RestController
@RequestMapping(value = "/api/transferi")
public class TransferController {
	
	@Autowired
	private TransferService transferService;
	
	@Autowired
	private TransferToTransferDto toDto;
	
	@GetMapping ResponseEntity<List<TransferDTO>>getAll(){
		List<Transfer> transferi = transferService.getAll();
		
		return new ResponseEntity<>(toDto.convert(transferi), HttpStatus.OK);
	}

}
