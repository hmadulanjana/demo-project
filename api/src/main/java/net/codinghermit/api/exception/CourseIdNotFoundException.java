package net.codinghermit.api.exception;

public class CourseIdNotFoundException extends RuntimeException{
    public CourseIdNotFoundException()
    {
        super("Course Id Not Found");
    }
}
