package pl.kurs.test3advancedlevel.exceptions;


public class WrongIdException extends RuntimeException{
    public WrongIdException(String message) {
        super(message);
    }
}
