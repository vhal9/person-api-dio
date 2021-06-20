package one.digitalinnovation.personapi.exceptions;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(Long id) {
        super("Person not found with ID: " + id);
    }
}
