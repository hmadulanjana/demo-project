package net.codinghermit.api.exception;

public class StudentIdNotFoundException extends RuntimeException{
    public StudentIdNotFoundException()
    {
        super("Student Id Not Found");
    }
}
