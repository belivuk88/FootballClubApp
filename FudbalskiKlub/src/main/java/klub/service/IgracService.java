package klub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import klub.model.Igrac;
import klub.model.Transfer;

public interface IgracService {
	
	Page<Igrac>pretraga(String pozicija, Long klubId, int pageNum);
	
	List<Igrac> findAll();
	
	Page<Igrac> findAll(int page);
	
	Optional<Igrac> findOne(Long id);
	
	Igrac save (Igrac igrac);
	
	Igrac update (Igrac igrac);
	
	Igrac delete (Long id);
	
	Igrac prelazak (Long id, Transfer transfer);

}
