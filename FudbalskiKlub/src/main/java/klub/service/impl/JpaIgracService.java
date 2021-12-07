package klub.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import klub.model.Igrac;
import klub.model.Klub;
import klub.model.Transfer;
import klub.repository.IgracRepository;
import klub.repository.KlubRepository;
import klub.service.IgracService;
import klub.service.KlubService;
import klub.service.TransferService;
@Service
public class JpaIgracService implements IgracService {
	
	@Autowired
	IgracRepository igracRepository;
	
	@Autowired
	KlubRepository klubRepository;
	
	@Autowired
	KlubService klubService;
	
	@Autowired
	TransferService transferService;
	
	@Autowired
	IgracService igracService;

	@Override
	public Page<Igrac> pretraga(String pozicija, Long klubId, int page) {
		// TODO Auto-generated method stub
		if(pozicija !=null) {
			pozicija ="%" + pozicija + "%";
		}
		return igracRepository.pretraga(pozicija, klubId, PageRequest.of(page, 2));
	}
	
	@Override
	public List<Igrac> findAll() {
		// TODO Auto-generated method stub
		return igracRepository.findAll();
	}

	@Override
	public Page<Igrac> findAll(int page) {
		// TODO Auto-generated method stub
		return igracRepository.findAll(PageRequest.of(page, 2));
	}

	@Override
	public Optional<Igrac> findOne(Long id) {
		// TODO Auto-generated method stub
		return igracRepository.findById(id);
	}

	@Override
	public Igrac save(Igrac igrac) {
		// TODO Auto-generated method stub
		return igracRepository.save(igrac);
	}

	@Override
	public Igrac update(Igrac igrac) {
		// TODO Auto-generated method stub
		return igracRepository.save(igrac);
	}

	@Override
	public Igrac delete(Long id) {
		Igrac igrac = findOne(id).get();
		if(igrac !=null) {
			igrac.getKlub().getIgraci().remove(igrac);
			igrac.setKlub(null);
			igracRepository.delete(igrac);
			return igrac;
		}
		
		return null;
	}

	@Override
	public Igrac prelazak(Long id, Transfer transfer) {
		// TODO Auto-generated method stub
		Igrac igrac = igracRepository.getOne(id);
		Klub klub = klubRepository.getOne(transfer.getKlub().getId());
		
		if(igrac.isNaProdaju()) {
			transfer.setIgrac(igrac);
			transfer.setKlub(klub);
			transfer.getKlub().setBudzet(klub.getBudzet() - transfer.getCena());
			transferService.save(transfer);
		
			igrac.setKlub(klub);
			
			return igracRepository.save(igrac);
		
		}
		
		return null;
	}

	

}
