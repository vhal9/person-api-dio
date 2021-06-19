package one.digitalinnovation.personapi.repositorys;

import one.digitalinnovation.personapi.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
