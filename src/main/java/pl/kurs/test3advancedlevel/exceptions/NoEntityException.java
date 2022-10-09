package pl.kurs.test3advancedlevel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEntityException extends RuntimeException {

    public NoEntityException(String message) {
        super(message);
    }
}
