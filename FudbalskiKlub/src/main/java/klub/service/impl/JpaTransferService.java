package klub.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klub.model.Transfer;
import klub.repository.TransferRepository;
import klub.service.TransferService;
@Service
public class JpaTransferService implements TransferService {
	
	@Autowired
	TransferRepository transferRepository;

	@Override
	public List<Transfer> getAll() {
		// TODO Auto-generated method stub
		return transferRepository.findAll();
	}

	@Override
	public Optional<Transfer> findOne(Long id) {
		// TODO Auto-generated method stub
		return transferRepository.findById(id);
	}

	@Override
	public Transfer save(Transfer transfer) {
		// TODO Auto-generated method stub
		return transferRepository.save(transfer);
	}

}
