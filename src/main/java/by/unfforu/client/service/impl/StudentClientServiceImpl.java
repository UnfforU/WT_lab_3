package by.unfforu.client.service.impl;

import by.unfforu.client.dao.StudentClientDAO;
import by.unfforu.client.entity.student.Student;
import by.unfforu.client.entity.user.User;
import by.unfforu.client.service.StudentClientService;

import java.util.List;

/**
 * Student service class
 */
public class StudentClientServiceImpl implements StudentClientService
{
    /**
     * Student data object
     */
    private final StudentClientDAO studentDAO;

    /**
     * Constructor
     *
     * @param studentDAO student data object
     */
    public StudentClientServiceImpl(StudentClientDAO studentDAO)
    {
        this.studentDAO = studentDAO;
    }

    /**
     * Login user
     *
     * @param user user instance
     *
     * @return User
     */
    @Override
    public User login(User user)
    {
        return this.studentDAO.login(user);
    }

    /**
     * Register new user
     *
     * @param user user instance
     *
     * @return User
     */
    @Override
    public User register(User user)
    {
        return this.studentDAO.register(user);
    }

    /**
     * Get all students
     *
     * @return List<Student>
     */
    @Override
    public List<Student> getAll()
    {
        return this.studentDAO.getAll();
    }

    /**
     * Get user by id
     *
     * @param id student id
     *
     * @return Student
     */
    @Override
    public Student get(int id)
    {
        return this.studentDAO.get(id);
    }

    /**
     *  Edit student
     *
     * @param newValue new student instance
     *
     * @return boolean
     */
    @Override
    public boolean edit(Student newValue)
    {
        return this.studentDAO.edit(newValue);
    }

    /**
     * Create new student
     *
     * @param student student instance
     *
     * @return boolean
     */
    @Override
    public boolean create(Student student)
    {
        return this.studentDAO.create(student);
    }
}
