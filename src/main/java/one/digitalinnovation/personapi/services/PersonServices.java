package one.digitalinnovation.personapi.services;

import lombok.AllArgsConstructor;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServices {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return createdMessageResponse(savedPerson.getId(), "Created Person with ID ");

    }

    public List<PersonDTO> listAll() {

        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException{

        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);

    }

    public void deleteById(Long id) throws PersonNotFoundException{

        Person person = verifyIfExists(id);

        personRepository.delete(person);

    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);

        return createdMessageResponse(updatedPerson.getId(), "Update person with ID ");

    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {

        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

    }

    private MessageResponseDTO createdMessageResponse(Long id, String message) {

        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();

    }
}
