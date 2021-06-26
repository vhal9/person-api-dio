package one.digitalinnovation.personapi.controllers;

import one.digitalinnovation.personapi.exceptions.ApiError;
import one.digitalinnovation.personapi.exceptions.PersonAlreadyRegisteredException;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodNotValidException(MethodArgumentNotValidException ex) {

        List<String> erros = ex.getBindingResult().getAllErrors()
                .stream()
                .map( erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiError(erros);

    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handlePersonNotFoundException(PersonNotFoundException ex) {

        return new ApiError(ex.getMessage());

    }

    @ExceptionHandler(PersonAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlePersonAlreadyRegisteredException(PersonAlreadyRegisteredException ex) {

        return new ApiError(ex.getMessage());

    }

}
