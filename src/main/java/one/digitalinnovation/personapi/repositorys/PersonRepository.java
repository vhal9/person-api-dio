package one.digitalinnovation.personapi.repositorys;

import one.digitalinnovation.personapi.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findFirstByCpf(String cpf);
}
