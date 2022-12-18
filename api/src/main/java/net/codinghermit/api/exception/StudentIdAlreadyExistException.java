package net.codinghermit.api.exception;

public class StudentIdAlreadyExistException extends RuntimeException{
    public StudentIdAlreadyExistException() {
        super("Student Id Already Exists");
    }
}
