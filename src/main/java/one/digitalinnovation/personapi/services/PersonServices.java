package one.digitalinnovation.personapi.services;

import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.mappers.PersonMapper;
import one.digitalinnovation.personapi.models.dto.request.PersonDTO;
import one.digitalinnovation.personapi.models.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.models.entity.Person;
import one.digitalinnovation.personapi.repositorys.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServices {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonServices(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created Person with ID " + savedPerson.getId())
                .build();

    }

    public List<PersonDTO> listAll() {

        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException{

        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson
                .map(personMapper::toDTO)
                .orElseThrow(() -> new PersonNotFoundException(id));

    }
}
