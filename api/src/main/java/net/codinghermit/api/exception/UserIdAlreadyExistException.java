package net.codinghermit.api.exception;

public class UserIdAlreadyExistException extends RuntimeException{
    public UserIdAlreadyExistException() {
        super("User Id Already Exists");
    }
}
