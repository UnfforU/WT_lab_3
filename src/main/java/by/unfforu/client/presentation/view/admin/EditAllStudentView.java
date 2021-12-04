package by.unfforu.client.presentation.view.admin;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.view.PresentationView;
import by.unfforu.client.service.StudentClientService;
import java.util.List;

/**
 * Edit student form (choose one)
 */
public class EditAllStudentView extends PresentationView
{
    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public EditAllStudentView(StudentClientService studentService, User user)
    {
        super(studentService, user);
    }

    /**
     * Show edit student form (choose one)
     */
    @Override
    public void show()
    {
        // Get all students
        List<Student> studentList = this.studentService.getAll();
        for (Student student : studentList)
        {
            System.out.println(student.getId() + ": " + student.getName());
        }

        System.out.println("Enter 'exit' to go back");
        System.out.println("Select student id: ");
    }

    /**
     * Get user input command
     *
     * @param input user input command
     *
     * @return PresentationView
     */
    @Override
    public PresentationView getInput(String input)
    {
        // Go back case
        if ("exit".equals(input))
        {
            return new AdminView(this.studentService, this.currentUser);
        }

        // Get information about student by id
        int id;
        try
        {
            id = Integer.parseInt(input);
        }
        catch (NumberFormatException ex)
        {
            throw new IllegalArgumentException();
        }

        // Get edit student information page
        return new EditStudentView(this.studentService, this.currentUser, id);
    }
}
