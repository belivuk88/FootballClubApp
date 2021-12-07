package klub.service;

import java.util.List;
import java.util.Optional;

import klub.model.Transfer;

public interface TransferService {
	
	List<Transfer>getAll();
	
	Optional<Transfer> findOne(Long id);
	
	Transfer save (Transfer transfer);

}
