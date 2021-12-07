package klub.service;

import java.util.List;
import java.util.Optional;

import klub.model.Igrac;
import klub.model.Klub;

public interface KlubService {
	
	List<Klub>findAll();
	
	Optional<Klub> findOne (Long id);
	
	Klub save (Klub klub);
	
	Klub update (Klub klub);

}
