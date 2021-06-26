package one.digitalinnovation.personapi.exceptions;

public class PersonAlreadyRegisteredException extends Exception {

    public PersonAlreadyRegisteredException(String cpf) {
        super(String.format("Person with cpf %s already registered in the system.", cpf));
    }

}
