package klub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import klub.model.Klub;
@Repository
public interface KlubRepository extends JpaRepository <Klub, Long> {

}
