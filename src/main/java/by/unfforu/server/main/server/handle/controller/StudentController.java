package by.unfforu.server.main.server.handle.controller;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.entity.request.StudentRequest;
import by.unfforu.client.entity.response.StudentResponse;
import by.unfforu.client.entity.response.ResponseType;
import by.unfforu.client.entity.user.User;
import by.unfforu.server.service.StudentServerService;
import java.util.List;

/**
 * Student controller
 */
public class StudentController
{
    /**
     * Student service
     */
    private final StudentServerService service;

    /**
     * Constructor
     *
     * @param service student service
     */
    public StudentController(StudentServerService service)
    {
        this.service = service;
    }

    /**
     * Login user
     *
     * @param request serialized user information
     *
     * @return StudentResponse
     */
    public StudentResponse login(StudentRequest request)
    {
        User user;

        // Try to get information about user
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof User body)
        {
            user = body;
        } else
        {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        // Try to login with current user information
        User userResult = this.service.login(user);
        if (userResult != null)
        {
            response.setResponseType(ResponseType.OK);
            response.setBody(userResult);
        } else
        {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Registar a new user
     *
     * @param request serialized user information
     *
     * @return StudentResponse
     */
    public StudentResponse register(StudentRequest request)
    {
        User user;

        // Try to get user information
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof User body)
        {
            user = body;
        }
        else
        {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        // Try to register a new user
        User userResult = this.service.register(user);
        if (userResult != null)
        {
            response.setResponseType(ResponseType.OK);
            response.setBody(userResult);
        }
        else
        {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Get all students information
     *
     * @return StudentResponse
     */
    public StudentResponse getAll()
    {
        List<Student> students = this.service.getAll();

        // Try to get students information
        StudentResponse response = new StudentResponse();
        if (students == null)
        {
            response.setResponseType(ResponseType.ERROR);
            response.setBody(null);
        }
        else
        {
            response.setResponseType(ResponseType.OK);
            response.setBody(students);
        }

        return response;
    }

    /**
     * Get student information by id
     *
     * @param request student id
     *
     * @return StudentResponse
     */
    public StudentResponse get(StudentRequest request)
    {
        int id;

        // Try to get student information (id)
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Integer)
        {
            id = (int) request.getBody();
        }
        else
        {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        // Try to get all student information by id
        Student studentToSend = this.service.get(id);
        if (studentToSend != null)
        {
            response.setResponseType(ResponseType.OK);
            response.setBody(studentToSend);
        }
        else
        {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Edit student
     *
     * @param request student information
     *
     * @return StudentResponse
     */
    public StudentResponse edit(StudentRequest request)
    {
        Student student;

        // Try to get student information
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Student body)
        {
            student = body;
        }
        else
        {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        // Try to edit current student
        if (this.service.edit(student))
        {
            response.setResponseType(ResponseType.OK);
        }
        else
        {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Create a new student
     *
     * @param request student information
     *
     * @return StudentResponse
     */
    public StudentResponse create(StudentRequest request)
    {
        Student student;

        // Try to get student information
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Student body)
        {
            student = body;
        }
        else
        {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        // Try to create a new student
        if (this.service.create(student))
        {
            response.setResponseType(ResponseType.OK);
        }
        else
        {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Process invalid response
     *
     * @return StudentResponse
     */
    public StudentResponse notFound()
    {
        StudentResponse response = new StudentResponse();
        response.setResponseType(ResponseType.NOTFOUND);

        return response;
    }
}
