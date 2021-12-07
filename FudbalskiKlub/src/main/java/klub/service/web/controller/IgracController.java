package klub.service.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import klub.model.Igrac;
import klub.model.Transfer;
import klub.service.IgracService;
import klub.service.support.IgracDtoToIgrac;
import klub.service.support.IgracToIgracDto;
import klub.service.support.TransferDtoToTransfer;
import klub.service.web.dto.IgracDTO;
import klub.service.web.dto.TransferDTO;

@RestController
@RequestMapping(value = "/api/igraci")
public class IgracController {
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private IgracDtoToIgrac toIgrac;
	
	@Autowired
	private IgracToIgracDto toDto;
	
	@Autowired 
	private TransferDtoToTransfer toTransfer;
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping(value = "/pozicija")
	public ResponseEntity<List<IgracDTO>> getAll(@RequestParam(value = "pozicija", required = false) String pozicija){
		
		List<Igrac>igraci = igracService.findAll();
		return new ResponseEntity<>(toDto.convert(igraci), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<IgracDTO>> get(@RequestParam(value = "pozicija", required = false) String pozicija,
			@RequestParam(value = "klubId", required = false) Long klubId,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
		
		Page<Igrac> page = null;
		
		if(pozicija !=null || klubId !=null) {
			page = igracService.pretraga(pozicija, klubId, pageNo);
		}else {
			page = igracService.findAll(pageNo);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toDto.convert(page.getContent()), headers, HttpStatus.OK);
			
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<IgracDTO> getOne(@PathVariable Long id){
		Optional<Igrac>igraci = igracService.findOne(id);
		if(!igraci.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(igraci.get()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IgracDTO> add(@Validated @RequestBody IgracDTO newDto){
		
		Igrac igrac = toIgrac.convert(newDto);
		Igrac saved = igracService.save(igrac);
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value= "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IgracDTO> edit(@Validated @RequestBody IgracDTO igracDto,
			@PathVariable Long id){
		
		if(!id.equals(igracDto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Igrac igrac = toIgrac.convert(igracDto);
		Igrac saved = igracService.update(igrac);
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Igrac obrisanIgrac = igracService.delete(id);
		
		if(obrisanIgrac !=null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping (value = "/{id}/transfer")
	public ResponseEntity<Igrac> prelazak (@PathVariable Long id, 
			@Validated @RequestBody TransferDTO transferDTO){
	Transfer transfer = toTransfer.convert(transferDTO);
	
	Igrac igrac = igracService.prelazak(id, transfer);
	
	if(igrac !=null) {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
