package by.unfforu.server.dao.impl;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.entity.user.User;
import by.unfforu.server.dao.StudentServerDAO;
import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Student data access object class
 */
public class StudentServerDAOImpl implements StudentServerDAO
{
    /**
     * Path to users.xml
     */
    private static final String USERS_PATH = "src/main/resources/users.xml";

    /**
     * Path to students.xml
     */
    private static final String STUDENTS_PATH = "src/main/resources/students.xml";

    /**
     * Synchronization
     */
    private final ReentrantReadWriteLock usersLock = new ReentrantReadWriteLock(true);

    /**
     * Synchronization
     */
    private final ReentrantReadWriteLock studentsLock = new ReentrantReadWriteLock(true);

    /**
     * Edit users information
     *
     * @param users users list
     *
     * @throws FileNotFoundException
     */
    @Override
    public void rewriteUsers(List<User> users) throws FileNotFoundException
    {
        // Open file with users and read it
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(StudentServerDAOImpl.USERS_PATH)
        )))
        {
            // Synchronize threads and write users new information
            try
            {
                this.studentsLock.writeLock().lock();
                for (User item : users)
                {
                    encoder.writeObject(item);
                }
            }
            finally
            {
                this.studentsLock.writeLock().unlock();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {
            // End of file
        }
    }

    /**
     * Edit students information
     *
     * @param students students list
     *
     * @throws FileNotFoundException
     */
    @Override
    public void rewriteStudents(List<Student> students) throws FileNotFoundException
    {
        // Open file with students and read it
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(StudentServerDAOImpl.STUDENTS_PATH)
        )))
        {
            // Parse date of birthday
            encoder.setPersistenceDelegate(LocalDate.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object localDate, Encoder encoder)
                {
                    return new Expression(localDate, LocalDate.class, "parse",
                            new Object[]{ localDate.toString() });
                }
            });

            // Parse last modification
            encoder.setPersistenceDelegate(LocalDateTime.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object localDateTime, Encoder encoder) {
                    return new Expression(localDateTime, LocalDateTime.class, "parse",
                        new Object[]{ localDateTime.toString() });
                }
            });

            // Synchronize threads and write students new information
            try
            {
                this.studentsLock.writeLock().lock();
                for (Student item : students)
                {
                    encoder.writeObject(item);
                }
            }
            finally
            {
                this.studentsLock.writeLock().unlock();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {
            // End of file
        }
    }

    /**
     * Check if user has already exist
     *
     * @param user user instance
     *
     * @return User
     */
    @Override
    public User userExists(User user)
    {
        User readUser;
        this.usersLock.readLock().lock();

        // Open file with users and read it
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(StudentServerDAOImpl.USERS_PATH)
        ))) {
            // Find user with current login
            do
            {
                readUser = (User) decoder.readObject();
                if (readUser.getLogin().equals(user.getLogin()))
                {
                    return readUser;
                }
            }
            while (readUser != null);
        }
        catch (ArrayIndexOutOfBoundsException | FileNotFoundException ignored)
        {
            // End of file
        }
        finally
        {
            this.usersLock.readLock().unlock();
        }

        return null;
    }

    /**
     * Get all users
     *
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers()
    {
        User user;
        ArrayList<User> users = new ArrayList<>();
        this.usersLock.readLock().lock();

        // Open file with users and read it
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(StudentServerDAOImpl.USERS_PATH)
        ))) {
            do
            {
                user = (User) decoder.readObject();
                users.add(user);
            }
            while (user != null);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            // End of file
        }
        catch (FileNotFoundException e)
        {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        }
        finally
        {
            this.usersLock.readLock().unlock();
        }

        return users;
    }

    /**
     * Get all students
     *
     * @return List<Student>
     */
    @Override
    public List<Student> getAll()
    {
        Student student;
        ArrayList<Student> students = new ArrayList<>();
        this.studentsLock.readLock().lock();

        // Open file with students and read it
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(StudentServerDAOImpl.STUDENTS_PATH)
        ))) {
            do
            {
                student = (Student) decoder.readObject();
                students.add(student);
            }
            while (student != null);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            // End of file
        }
        catch (FileNotFoundException e)
        {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        }
        finally
        {
            this.studentsLock.readLock().unlock();
        }

        return students;
    }

    /**
     * Get student by id
     *
     * @param id student id
     *
     * @return Student
     */
    @Override
    public Student get(int id) {
        Student student;
        this.studentsLock.readLock().lock();

        // Open file with students and read it
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(StudentServerDAOImpl.STUDENTS_PATH)
        ))) {
            do
            {
                // Find student with current id
                student = (Student) decoder.readObject();
                if (student.getId() == id)
                {
                    return student;
                }
            }
            while (student != null);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            // End of file
        }
        catch (FileNotFoundException e)
        {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        }
        finally
        {
            this.studentsLock.readLock().unlock();
        }

        return null;
    }
}
