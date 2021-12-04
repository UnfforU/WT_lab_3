package by.unfforu.server.service.impl;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.entity.user.User;
import by.unfforu.server.dao.StudentServerDAO;
import by.unfforu.server.service.StudentServerService;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Student service class
 */
public class StudentServerServiceImpl implements StudentServerService
{
    /**
     * Student data object
     */
    private final StudentServerDAO studentDAO;

    /**
     * Constructor
     *
     * @param studentDAO student data object
     */
    public StudentServerServiceImpl(StudentServerDAO studentDAO)
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
        // Check if user exists
        User existedUser  = this.studentDAO.userExists(user);
        if ((existedUser != null) && existedUser.getPassword().equals(user.getPassword()))
        {
            return existedUser;
        }

        return null;
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
        // Check if user exists
        List<User> users = this.studentDAO.getAllUsers();
        if (users.stream().anyMatch(u -> u.getLogin().equals(user.getLogin())))
        {
            return null;
        }

        // Create new user
        if (users.isEmpty())
        {
            user.setId(1);
        }
        else
        {
            User maxIdStudent = Collections.max(users, Comparator.comparing(User::getId));
            user.setId(maxIdStudent.getId() + 1);
        }

        // Add new user
        users.add(user);
        try
        {
            this.studentDAO.rewriteUsers(users);
        } catch (FileNotFoundException e)
        {
            return null;
        }

        return user;
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
        return studentDAO.get(id);
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
        // Find necessary student
        List<Student> students = this.studentDAO.getAll();
        Student toEdit = students.stream().filter(s -> s.getId() == newValue.getId()).findFirst().orElse(null);
        if (toEdit == null)
        {
            return false;
        }

        // Check if student has already modify
        if ((toEdit.getLastModification() != null) && newValue.getLastModification().isBefore(toEdit.getLastModification()))
        {
            return false;
        }

        // Set a new information
        toEdit.setName(newValue.getName());
        toEdit.setBirthday(newValue.getBirthday());
        toEdit.setCharacteristic(newValue.getCharacteristic());
        toEdit.setLastModification(LocalDateTime.now());

        // Save changes
        try
        {
            this.studentDAO.rewriteStudents(students);
        } catch (FileNotFoundException e)
        {
            return false;
        }

        return true;
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
        // Get all students and check if they exists
        List<Student> students = this.studentDAO.getAll();
        if (students.isEmpty())
        {
            student.setId(1);
        } else
        {
            Student maxIdStudent = Collections.max(students, Comparator.comparing(Student::getId));
            student.setId(maxIdStudent.getId() + 1);
        }

        // Add new student
        students.add(student);
        try
        {
            this.studentDAO.rewriteStudents(students);
        } catch (FileNotFoundException e)
        {
            return false;
        }

        return true;
    }
}
