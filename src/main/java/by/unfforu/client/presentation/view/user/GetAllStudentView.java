package by.unfforu.client.presentation.view.user;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.view.PresentationView;
import by.unfforu.client.presentation.view.admin.AdminView;
import by.unfforu.client.service.StudentClientService;
import java.util.List;

/**
 * Get all students form
 */
public class GetAllStudentView extends PresentationView
{
    /**
     * Constructor
     *
     *  @param studentService student service
     *  @param user user instance
     */
    public GetAllStudentView(StudentClientService studentService, User user)
    {
        super(studentService, user);
    }

    /**
     * Show all students form
     */
    @Override
    public void show()
    {
        List<Student> studentList = this.studentService.getAll();
        for (Student student : studentList)
        {
            System.out.println(student.getId() + ": " + student.getName());
        }

        System.out.println("Enter 'exit' to go back");
        System.out.println("Print student id: ");
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
            return switch (this.currentUser.getRole())
            {
                case USER -> new UserView(this.studentService, this.currentUser);
                case ADMIN -> new AdminView(this.studentService, this.currentUser);
                default -> null;
            };
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

        // Get student information page
        return new GetStudentView(this.studentService, this.currentUser, id);
    }
}
