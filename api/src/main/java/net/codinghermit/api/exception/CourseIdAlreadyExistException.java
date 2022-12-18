package net.codinghermit.api.exception;

public class CourseIdAlreadyExistException extends RuntimeException{
    public CourseIdAlreadyExistException() {
        super("Course Id Already Exists");
    }
}
