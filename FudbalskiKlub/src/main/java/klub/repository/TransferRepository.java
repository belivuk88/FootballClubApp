package klub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import klub.model.Transfer;
@Repository
public interface TransferRepository extends JpaRepository <Transfer, Long> {

}
