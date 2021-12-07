package klub.service.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import klub.model.Klub;
import klub.service.KlubService;
import klub.service.support.KlubToKlubDto;
import klub.service.web.dto.KlubDTO;

@RestController
@RequestMapping("/api/klubovi")
public class KlubController {

	@Autowired
	private KlubService klubService;
	
	@Autowired
	private KlubToKlubDto toDto;
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<KlubDTO>> getAll(
			@RequestParam(required = false) String naziv){
		
		List<Klub>klubovi = klubService.findAll();
		return new ResponseEntity<>(toDto.convert(klubovi), HttpStatus.OK);
	}
	
}
