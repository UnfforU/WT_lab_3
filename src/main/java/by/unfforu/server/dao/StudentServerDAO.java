package by.unfforu.server.dao;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.entity.user.User;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Student data access object api
 */
public interface StudentServerDAO
{
    /**
     * Edit users information
     *
     * @param users users list
     *
     * @throws FileNotFoundException
     */
    void rewriteUsers(List<User> users) throws FileNotFoundException;

    /**
     * Edit students information
     *
     * @param students students list
     *
     * @throws FileNotFoundException
     */
    void rewriteStudents(List<Student> students) throws FileNotFoundException;

    /**
     * Check if user has already exist
     *
     * @param user user instance
     *
     * @return User
     */
    User userExists(User user);

    /**
     * Get all users
     *
     * @return List<User>
     */
    List<User> getAllUsers();

    /**
     * Get all students
     *
     * @return List<Student>
     */
    List<Student> getAll();

    /**
     * Get student by id
     *
     * @param id student id
     *
     * @return Student
     */
    Student get(int id);
}
