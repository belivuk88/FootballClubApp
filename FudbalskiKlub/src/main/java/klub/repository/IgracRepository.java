package klub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import klub.model.Igrac;
@Repository
public interface IgracRepository extends JpaRepository<Igrac, Long> {
	
	@Query("SELECT i FROM Igrac i WHERE " +
		"(:pozicija = NULL OR i.pozicija like :pozicija) AND " +
		"(:klubId = NULL OR i.klub.id = :klubId)")
	Page<Igrac> pretraga(@Param("pozicija") String pozicija, @Param("klubId") Long klubId, Pageable pageable);
	

}
