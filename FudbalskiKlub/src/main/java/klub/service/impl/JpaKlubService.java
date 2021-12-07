package klub.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klub.model.Klub;
import klub.repository.KlubRepository;
import klub.service.KlubService;
@Service
public class JpaKlubService implements KlubService {
	
	@Autowired
	KlubRepository klubRepository;

	@Override
	public List<Klub> findAll() {
		// TODO Auto-generated method stub
		return klubRepository.findAll();
	}

	@Override
	public Optional<Klub> findOne(Long id) {
		// TODO Auto-generated method stub
		return klubRepository.findById(id);
	}

	@Override
	public Klub save(Klub klub) {
		// TODO Auto-generated method stub
		return klubRepository.save(klub);
	}

	@Override
	public Klub update(Klub klub) {
		// TODO Auto-generated method stub
		return klubRepository.save(klub);
	}

}
