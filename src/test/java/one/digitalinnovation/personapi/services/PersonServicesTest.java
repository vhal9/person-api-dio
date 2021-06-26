package one.digitalinnovation.personapi.services;

import one.digitalinnovation.personapi.exceptions.PersonAlreadyRegisteredException;
import one.digitalinnovation.personapi.mappers.PersonMapper;
import one.digitalinnovation.personapi.models.dto.request.PersonDTO;
import one.digitalinnovation.personapi.models.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.models.entity.Person;
import one.digitalinnovation.personapi.repositorys.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository personRepository;

    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @InjectMocks
    private PersonServices personServices;

    @Test
    void testGivenPersonDTOThenReturnSuccessSavedMessage() throws PersonAlreadyRegisteredException {

        PersonDTO personDTO = createFakeDTO();
        Person expectedSavePerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavePerson);

        MessageResponseDTO expectedSucessMessage = createExpectedMessageResponse(expectedSavePerson.getId());
        MessageResponseDTO successMessage = personServices.createPerson(personDTO);

        assertEquals(expectedSucessMessage, successMessage);

    }

    @Test
    void testGivenPersonDTOWithRegisteredCPFThenExceptionShouldBeThrow() {

        PersonDTO personDTO = createFakeDTO();
        Person duplicatedPerson = personMapper.toModel(personDTO);

        when(personRepository.findFirstByCpf(personDTO.getCpf())).thenReturn(Optional.of(duplicatedPerson));

        assertThrows(PersonAlreadyRegisteredException.class, () -> personServices.createPerson(personDTO));

    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {

        return MessageResponseDTO
                .builder()
                .message("Created Person with ID " + id)
                .build();

    }

}
